package iona.controller;

import iona.config.ContantsContext;
import iona.pojo.WebsocketClientMessage;
import iona.pojo.WebsocketServerMessage;
import iona.service.IWebSocektService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Controller
@ResponseBody
public class WebsocketController {

    @Resource
    IWebSocektService webSocketService;

    @MessageMapping(ContantsContext.FORETOSERVERPATH)//@MessageMapping和@RequestMapping功能类似，用于设置URL映射地址，浏览器向服务器发起请求，需要通过该地址。
    @SendTo(ContantsContext.PRODUCERPATH)//如果服务器接受到了消息，就会对订阅了@SendTo括号中的地址传送消息。
    public WebsocketServerMessage say(WebsocketClientMessage message) throws Exception {
        List<String> users = new ArrayList<>();
        users.add("1");//此处写死只是为了方便测试,此值需要对应页面中订阅个人消息的userId
        webSocketService.sendMsgToUsers(users, new WebsocketServerMessage("zazalu hello"));

        return new WebsocketServerMessage("欢迎, " + message.getName() + "!");
    }

}
