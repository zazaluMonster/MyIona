package iona.service;


import iona.pojo.Message;

import java.util.List;

public interface IMessageService extends BaseService<Message> {
    void insert(Message message);
    List<Message> getMessageByCreator(int creator);
    List<Message> getMessageRandom();
    List<Message> getFollowingMessage(int curUserId);
}
