package iona.service.impl;

import iona.cache.IonaCache;
import iona.dao.ICrewDao;
import iona.dao.IMessageDao;
import iona.exception.IonaException;
import iona.logger.IonaLogger;
import iona.pojo.Crew;
import iona.pojo.Follow;
import iona.pojo.Message;
import iona.service.ICrewService;
import iona.service.IMessageService;
import iona.util.DateUtil;
import iona.util.TokenUtil;
import org.springframework.beans.BeanUtils;
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
    public List<Message> getMyMessage(int curLoginUserId) {
        Message message = new Message();
        message.setCreator(curLoginUserId);
        message.setCurUserId(curLoginUserId);
        Map<String,Object> condition = new HashMap<>();
        condition.put("item",message);
        condition.put("OrRetweetorIdIsMy",curLoginUserId);//转推id不是我的排除掉,不展示在我的伊文中
        return messageDao.selects(condition);
    }

    @Override
    public List<Message> getUserMessage(int curLoginUserId, int curUserCardId) {
        Message message = new Message();
        message.setCreator(curUserCardId);
        message.setCurUserId(curLoginUserId);
        Map<String,Object> condition = new HashMap<>();
        condition.put("item",message);
        condition.put("OrRetweetorIdIsMy",curLoginUserId);
        return messageDao.selects(condition);
    }

    @Override
    public List<Message> getMessageRandom(int curUserId) {
        Message message = new Message();
        message.setCurUserId(curUserId);
        Map<String,Object> condition = new HashMap<>();
        condition.put("item",message);
        return messageDao.selects(condition);
    }

    @Override
    public List<Message> getFollowingMessage(int curLoginUserId) {
        return messageDao.getFollowingMessage(curLoginUserId);
    }

    @Override
    public Message getByRetweetorIdAndRetweetMessageId(int retweetorId, int retweetMessageId) throws IonaException {
        Message message = new Message();
        message.setRetweetorId(retweetorId);
        message.setRetweetMessageId(retweetMessageId);
        Map<String,Object> condition = new HashMap<>();
        condition.put("item",message);
        List<Message> messages =  messageDao.selects(condition);
        if(messages == null || messages.size() <= 0){
            return null;
        }else if(messages.size() > 1){
            IonaLogger.info("检测到数据库数据关系异常,retweetorId+retweetMessageId组合不唯一,retweetorId:" +retweetorId+ ",retweetMessageId" + retweetMessageId);
            throw new IonaException();
        }else{
            return messages.get(0);
        }
    }

    @Override
    public void retweet(Message oldMessage, int retweetorId) {
        Message newRetweetMessage = new Message();
        BeanUtils.copyProperties(oldMessage,newRetweetMessage,"id");
        newRetweetMessage.setRetweetorId(retweetorId);
        newRetweetMessage.setRetweetMessageId(oldMessage.getId());
        newRetweetMessage.setRetweetTime(DateUtil.getTimeString(new Date()));
        newRetweetMessage.setCreateTime(newRetweetMessage.getRetweetTime());
        List<Message> list = new ArrayList<>();
        list.add(newRetweetMessage);
        messageDao.inserts(list);
    }

    @Override
    public void cancelRetweet(int retweetorId, int retweetMessageId) throws IonaException {
        Message message = getByRetweetorIdAndRetweetMessageId(retweetorId,retweetMessageId);
        messageDao.delete(message.getId());
    }

    @Override
    public Message getMessageByIdAndCurUserId(int id, int curUserId) {
        return messageDao.getMessageByIdAndCurUserId(id,curUserId);
    }
}
