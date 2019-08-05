package iona.dao.impl;

import iona.dao.IMessageDao;
import iona.mapper.MessageMapper;
import iona.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class MessageDao extends AbstractDao implements IMessageDao {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public void inserts(List<Message> items) {
        messageMapper.inserts(items);
    }

    @Override
    public void delete(int id) {
        messageMapper.delete(id);
    }

    @Override
    public void deletes(List<Integer> ids) {
        messageMapper.deletes(ids);
    }

    @Override
    public void update(Message item) {
        messageMapper.update(item);
    }

    @Override
    public void updates(List<Message> items) {
        messageMapper.updates(items);
    }

    @Override
    public List<Message> selects(Map<String, Object> condition) {
        return messageMapper.selects(condition);
    }

    @Override
    public List<Message> getFollowingMessage(int id) {
        return messageMapper.getFollowingMessage(id);
    }

    @Override
    public Message getMessageByIdAndCurUserId(int id, int curUserId) {
        return messageMapper.getMessageByIdAndCurUserId(id,curUserId);
    }
}
