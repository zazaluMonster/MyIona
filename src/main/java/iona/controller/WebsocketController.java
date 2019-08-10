package iona.controller;

import iona.cache.IonaCache;
import iona.config.ContantsContext;
import iona.logger.IonaLogger;
import iona.pojo.ChatMessage;
import iona.pojo.WebsocketClientMessage;
import iona.pojo.WebsocketServerMessage;
import iona.service.IWebSocektService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    IonaCache ionaCache;

    //测试用
    @MessageMapping(ContantsContext.FORETOSERVERPATH)//@MessageMapping和@RequestMapping功能类似，用于设置URL映射地址，浏览器向服务器发起请求，需要通过该地址。
    @SendTo(ContantsContext.PRODUCERPATH)//如果服务器接受到了消息，就会对订阅了@SendTo括号中的地址传送消息。
    public WebsocketServerMessage say(WebsocketClientMessage message) throws Exception {
        List<String> users = new ArrayList<>();
        users.add("1");//此处写死只是为了方便测试,此值需要对应页面中订阅个人消息的userId
        webSocketService.sendMsgToUsers(users, new WebsocketServerMessage("zazalu hello"));

        return new WebsocketServerMessage("欢迎, " + message.getName() + "!");
    }

    @MessageMapping(ContantsContext.WEBSOCKET_CHAT_ROOM_SERVER_PATH)
    @SendTo(ContantsContext.WEBSOCKET_CHAT_ROOM_PRODUCE_PATH)
    public WebsocketServerMessage chatCenter(WebsocketClientMessage message) throws Exception {
        //处理在线用户
        String curChatUserStr = ionaCache.getCacheValue(ContantsContext.CACHE_ONLINE_USER_KEY);
        if(curChatUserStr == null){
            curChatUserStr = "";
        }
        if(message.getType() == WebsocketClientMessage.TYPE.DISCONNET){
            message.setMessage(new ChatMessage(message.getName(),"离开了房间"));
            curChatUserStr = curChatUserStr.replaceAll(message.getName() + ",","");
            ionaCache.addCache(ContantsContext.CACHE_ONLINE_USER_KEY,curChatUserStr);
        }
        else if(message.getType() == WebsocketClientMessage.TYPE.CONNET){
            if(curChatUserStr.contains(message.getName())){
                IonaLogger.info(message.getName() + ",已经在线");
            }else{
                message.setMessage(new ChatMessage(message.getName(),"加入了房间"));
                curChatUserStr += message.getName() + ",";
                ionaCache.addCache(ContantsContext.CACHE_ONLINE_USER_KEY,curChatUserStr);
            }
        }
        //向所有聊天室内的人员发送广播消息
        return new WebsocketServerMessage(message.getMessage(),curChatUserStr);
    }
}
