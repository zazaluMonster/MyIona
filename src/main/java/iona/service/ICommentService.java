package iona.service;


import iona.pojo.Comment;

import java.util.List;

public interface ICommentService extends BaseService<Comment> {
    void newComment(Comment comment);
    List<Comment> getMessageComment(int messageId);
}
