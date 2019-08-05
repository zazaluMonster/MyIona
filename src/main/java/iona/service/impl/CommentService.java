package iona.service.impl;

import iona.cache.IonaCache;
import iona.dao.ICommentDao;
import iona.exception.IonaException;
import iona.pojo.Comment;
import iona.pojo.Pager;
import iona.service.ICommentService;
import iona.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CommentService implements ICommentService {

    @Autowired
    private ICommentDao commentDao;
    @Autowired
    private IonaCache ionaCache;


    @Override
    public void inserts(List<Comment> items) {
        commentDao.inserts(items);
    }

    @Override
    public void delete(int id) {
        commentDao.delete(id);
    }

    @Override
    public void deletes(List<Integer> ids) {
        commentDao.deletes(ids);
    }

    @Override
    public void update(Comment newComment){
        commentDao.update(newComment);
    }

    @Override
    public void updates(List<Comment> items) {
        commentDao.updates(items);
    }

    @Override
    public List<Comment> selects(Map<String, Object> condition) {
        return commentDao.selects(condition);
    }

    @Override
    public void newComment(Comment comment) {
        comment.setCreateTime(DateUtil.getTimeString(new Date()));

        List<Comment> list = new ArrayList<>();
        list.add(comment);
        commentDao.inserts(list);
    }

    @Override
    public List<Comment> getMessageComment(int messageId) {
        Comment comment = new Comment();
        comment.setMessageId(messageId);
        Map<String,Object> condition = new HashMap<>();
        condition.put("item", comment);
        return commentDao.selects(condition);
    }
}
