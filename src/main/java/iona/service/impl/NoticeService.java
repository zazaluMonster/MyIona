package iona.service.impl;

import iona.async.RunnerQueue;
import iona.async.asyncRunner.ProduceNoticeRunner;
import iona.config.ContantsContext;
import iona.dao.INoticeDao;
import iona.pojo.Notice;
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
    public void registerNotice(int crewId) {
        String content = ContantsContext.FIRST_REGISTER;
        String url = ContantsContext.FIRST_REGISTER_URL;

        ProduceNoticeRunner produceNoticeRunner = applicationContext.getBean(ProduceNoticeRunner.class, crewId, content, url);
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

}
