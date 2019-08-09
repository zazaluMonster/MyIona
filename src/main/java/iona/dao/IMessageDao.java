package iona.dao;


import iona.pojo.Message;
import iona.pojo.Pager;

import java.util.List;

public interface IMessageDao extends BaseDao<Message> {
    List<Message> getFollowingMessage(int id, Pager pager);
    Message getMessageByIdAndCurUserId(int id,int curUserId);
}
