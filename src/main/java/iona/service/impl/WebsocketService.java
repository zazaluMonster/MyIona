package iona.service.impl;

import iona.config.ContantsContext;
import iona.pojo.WebsocketServerMessage;
import iona.service.IWebSocektService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class WebsocketService implements IWebSocektService {

    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public void sendMsg(WebsocketServerMessage message) {
        template.convertAndSend(ContantsContext.PRODUCERPATH, message);
    }

    @Override
    public void sendMsgToUser(String UserId, WebsocketServerMessage message) {
        template.convertAndSendToUser(UserId, ContantsContext.P2PPUSHPATH, message);
    }

    @Override
    public void sendMsgToUsers(List<String> users, WebsocketServerMessage message) {
        users.forEach(userName -> template.convertAndSendToUser(userName, ContantsContext.P2PPUSHPATH, message));
    }
}
