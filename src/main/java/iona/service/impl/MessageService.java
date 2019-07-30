package iona.service.impl;

import iona.cache.IonaCache;
import iona.dao.ICrewDao;
import iona.dao.IMessageDao;
import iona.exception.IonaException;
import iona.logger.IonaLogger;
import iona.pojo.Crew;
import iona.pojo.Message;
import iona.service.ICrewService;
import iona.service.IMessageService;
import iona.util.DateUtil;
import iona.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

@Component
public class MessageService implements IMessageService {

    @Autowired
    private IMessageDao messageDao;
    @Autowired
    private IonaCache ionaCache;


    @Override
    public void inserts(List<Message> items) {
        messageDao.inserts(items);
    }

    @Override
    public void delete(int id) {
        messageDao.delete(id);
    }

    @Override
    public void deletes(List<Integer> ids) {
        messageDao.deletes(ids);
    }

    @Override
    public void update(Message item) throws IonaException {
        messageDao.update(item);
    }

    @Override
    public void updates(List<Message> items) {
        messageDao.updates(items);
    }

    @Override
    public List<Message> selects(Map<String, Object> condition) {
        return messageDao.selects(condition);
    }

    @Override
    public void insert(Message message) {
        if (StringUtils.isEmpty(message.getCreateTime())) {
            message.setCreateTime(DateUtil.getTimeString(new Date()));
        }
        List<Message> list = new ArrayList<>();
        list.add(message);
        messageDao.inserts(list);
    }

    @Override
    public List<Message> getMessageByCreator(int creator) {
        Message message = new Message();
        message.setCreator(creator);
        Map<String,Object> condition = new HashMap<>();
        condition.put("item",message);
        return messageDao.selects(condition);
    }

    @Override
    public List<Message> getMessageRandom() {
        Message message = new Message();
        Map<String,Object> condition = new HashMap<>();
        condition.put("item",message);
        return messageDao.selects(condition);
    }

    @Override
    public List<Message> getFollowingMessage(int curUserId) {

        return messageDao.getFollowingMessage(curUserId);
    }
}
