package iona.service.impl;

import iona.async.RunnerQueue;
import iona.async.asyncRunner.ProduceNoticeRunner;
import iona.config.ContantsContext;
import iona.dao.ICrewDao;
import iona.dao.INoticeDao;
import iona.exception.IonaException;
import iona.logger.IonaLogger;
import iona.pojo.Crew;
import iona.pojo.Message;
import iona.pojo.Notice;
import iona.service.ICrewService;
import iona.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NoticeService implements INoticeService {

    @Autowired
    private INoticeDao noticeDao;
    @Autowired
    private ICrewService crewService;
    @Autowired
    RunnerQueue runnerQueue;
    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void inserts(List<Notice> items) {
        noticeDao.inserts(items);
    }

    @Override
    public void delete(int id) {
        noticeDao.delete(id);
    }

    @Override
    public void deletes(List<Integer> ids) {
        noticeDao.deletes(ids);
    }

    @Override
    public void update(Notice item) {
        noticeDao.update(item);
    }

    @Override
    public void updates(List<Notice> items) {
        noticeDao.updates(items);
    }

    @Override
    public List<Notice> selects(Map<String, Object> condition) {
        return noticeDao.selects(condition);
    }

    @Override
    public void produceNotice(int crewId, int noticeType, int messageId, int followerId) throws IonaException {

        String content = "";
        String url = "";
        int notifierId = crewId;

        Crew crew = crewService.getById(crewId);

        switch (noticeType){
            case Notice.NoticeType.REGISTER: {
                content = ContantsContext.FIRST_REGISTER;
                url = ContantsContext.FIRST_REGISTER_URL;
                break;
            }
            case Notice.NoticeType.COMMENT: {
                content = ContantsContext.COMMENT;
                url =  ContantsContext.COMMENT_PREFIX_URL + "/" + messageId;
                break;
            }
            case Notice.NoticeType.FOLLOW: {
                content = ContantsContext.FOLLOW;
                url =  ContantsContext.FOLLOW_PREFIX_URL + "/" + followerId;
                break;
            }
            case Notice.NoticeType.LIKE: {
                content = ContantsContext.LIKE;
                url =  ContantsContext.LIKE_PREFIX_URL + "/" + messageId;
                break;
            }
            case Notice.NoticeType.NEW_MESSAGE: {
                content = "*" + crew.getCrewName() + "* " +ContantsContext.NEW_MESSAGE_PREFIX;
                url =  ContantsContext.NEW_MESSAGE_PREFIX_URL + "/" + messageId;
                break;
            }
            default: {
                throw new IonaException("注册通知异常");
            }
        }

        ProduceNoticeRunner produceNoticeRunner = applicationContext.getBean(ProduceNoticeRunner.class, notifierId, content, url);
        runnerQueue.addNewRunner(produceNoticeRunner);
    }

    @Override
    public List<Notice> getCurUserNewNotice(int crewId) {
        Notice notice = new Notice();
        notice.setIsRead(ContantsContext.I_FALSE);
        notice.setNotifierId(crewId);

        Map<String,Object> condition = new HashMap<>();
        condition.put("item",notice);

        return noticeDao.selects(condition);
    }

    @Override
    public void readNotice(int id) throws IonaException {
        Notice notice = getById(id);
        notice.setIsRead(ContantsContext.I_TRUE);
        update(notice);
    }

    @Override
    public Notice getById(int id) throws IonaException {
        Notice notice = new Notice();
        notice.setId(id);

        Map<String,Object> condition = new HashMap<>();
        condition.put("item",notice);

        List<Notice> notices =  noticeDao.selects(condition);
        if(notices == null || notices.size() <= 0){
            return null;
        }else if(notices.size() > 1){
            throw new IonaException();
        }else{
            return notices.get(0);
        }
    }
}
