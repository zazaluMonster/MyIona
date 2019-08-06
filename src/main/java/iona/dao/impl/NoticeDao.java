package iona.dao.impl;

import iona.dao.INoticeDao;
import iona.mapper.NoticeMapper;
import iona.pojo.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class NoticeDao extends AbstractDao implements INoticeDao {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public void inserts(List<Notice> items) {
        noticeMapper.inserts(items);
    }

    @Override
    public void delete(int id) {
        noticeMapper.delete(id);
    }

    @Override
    public void deletes(List<Integer> ids) {
        noticeMapper.deletes(ids);
    }

    @Override
    public void update(Notice item) {
        noticeMapper.update(item);
    }

    @Override
    public void updates(List<Notice> items) {
        noticeMapper.updates(items);
    }

    @Override
    public List<Notice> selects(Map<String, Object> condition) {
        return noticeMapper.selects(condition);
    }
}
