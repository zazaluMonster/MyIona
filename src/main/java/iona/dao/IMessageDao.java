package iona.dao;


import iona.pojo.Message;

import java.util.List;

public interface IMessageDao extends BaseDao<Message> {
    List<Message> getFollowingMessage(int id);
}
