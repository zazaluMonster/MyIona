package iona.service;


import iona.pojo.WebsocketServerMessage;

import java.util.List;

public interface IWebSocektService {
    void sendMsg(WebsocketServerMessage message);

    void sendMsgToUser(String UserId,WebsocketServerMessage message);

    void sendMsgToUsers(List<String> users, WebsocketServerMessage message);
}
