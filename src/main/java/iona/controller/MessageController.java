package iona.controller;

import iona.async.RunnerQueue;
import iona.exception.IonaException;
import iona.logger.IonaLogger;
import iona.modelView.MessageResponse;
import iona.pojo.Message;
import iona.service.IFollowService;
import iona.service.IMessageService;
import iona.util.DateUtil;
import iona.util.MyHttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/message")
@ResponseBody
public class MessageController {

    @Autowired
    private IMessageService messageService;

    @Autowired
    RunnerQueue runnerQueue;
    @Autowired
    ApplicationContext applicationContext;

    /**
     * 新增伊文
     */
    @RequestMapping(value = "/newMessage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public MessageResponse newMessage(@RequestBody Message message) {
        MyHttpStatus status = MyHttpStatus.OK;

        messageService.insert(message);

        MessageResponse result = new MessageResponse(status);
        return result;
    }

    /**
     * 新增转推伊文
     */
    @RequestMapping(value = "/newReTweetMessage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public MessageResponse newReTweetMessage(@RequestBody Message message) {
        MyHttpStatus status = MyHttpStatus.OK;

        messageService.retweet(message,message.getCurUserId());

        MessageResponse result = new MessageResponse(status);
        return result;
    }

    /**
     * 取消转推伊文
     */
    @RequestMapping(value = "/cancelReTweetMessage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public MessageResponse cancelReTweetMessage(@RequestBody Message message) throws IonaException {
        MyHttpStatus status = MyHttpStatus.OK;

        messageService.cancelRetweet(message.getCurUserId(),message.getRetweetMessageId());

        MessageResponse result = new MessageResponse(status);
        return result;
    }

    /**
     * 获取我的伊文
     */
    @RequestMapping(value = "/getMyMessage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public MessageResponse getMyMessage(@RequestBody Message message) {
        MyHttpStatus status = MyHttpStatus.OK;

        List<Message> list = messageService.getMyMessage(message.getCurUserId());

        MessageResponse result = new MessageResponse(status, list, DateUtil.getTimeString(new Date()));
        return result;
    }

    /**
     * 获取某用户的伊文
     */
    @RequestMapping(value = "/getUserMessage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public MessageResponse getUserMessage(@RequestBody Message message) {
        MyHttpStatus status = MyHttpStatus.OK;

        List<Message> list = messageService.getUserMessage(message.getCurUserId(),message.getCreator());

        MessageResponse result = new MessageResponse(status, list, DateUtil.getTimeString(new Date()));
        return result;
    }

    /**
     * 获取我关注的(若没有任何关注或者关注内容返回空则启动“随便看看”服务)
     */
    @RequestMapping(value = "/getFriendMessage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public MessageResponse getFriendMessage(@RequestBody Message message) {
        MyHttpStatus status = MyHttpStatus.OK;
        List<Message> list = null;
        int curUserId = message.getId();

        list = messageService.getFollowingMessage(curUserId);

        if (list == null || list.size() == 0) {
            IonaLogger.info("无关注内容,自动执行随便看看服务");
            list = messageService.getMessageRandom(curUserId);
        }

        MessageResponse result = new MessageResponse(status, list, DateUtil.getTimeString(new Date()));
        return result;
    }
}
