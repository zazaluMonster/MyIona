package iona.service;


import iona.exception.IonaException;
import iona.pojo.Message;

import java.util.List;

public interface IMessageService extends BaseService<Message> {
    void insert(Message message);
    void retweet(Message oldMessage,int retweetorId);
    void cancelRetweet(int retweetorId, int retweetMessageId) throws IonaException;
    @Deprecated
    List<Message> getMessageByCreator(int creator);
    List<Message> getMyMessage(int curLoginUserId);
    List<Message> getUserMessage(int curLoginUserId,int curUserCardId);
    List<Message> getMessageRandom(int curUserId);
    List<Message> getFollowingMessage(int curUserId);
    Message getByRetweetorIdAndRetweetMessageId(int retweetorId, int retweetMessageId) throws IonaException;
    Message getMessageByIdAndCurUserId(int id,int curUserId);
}
