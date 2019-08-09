package iona.controller;

import iona.async.RunnerQueue;
import iona.exception.IonaException;
import iona.modelView.CommentResponse;
import iona.modelView.CrewResponse;
import iona.pojo.Comment;
import iona.pojo.Notice;
import iona.service.ICommentService;
import iona.service.INoticeService;
import iona.util.MyHttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/comment")
@ResponseBody
public class CommentController {

    @Autowired
    private ICommentService commentService;
    @Autowired
    private INoticeService noticeService;
    @Autowired
    RunnerQueue runnerQueue;
    @Autowired
    ApplicationContext applicationContext;

    /**
     * 添加回复
     * @param comment 回复数据
     */
    @RequestMapping(value = "/newComment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CommentResponse newComment(@RequestBody Comment comment) throws IonaException {
        MyHttpStatus status = MyHttpStatus.OK;

        commentService.newComment(comment);

        noticeService.produceNotice(comment.getMessageCreator(), Notice.NoticeType.COMMENT,comment.getMessageId(),0);

        return new CommentResponse(status);
    }

    /**
     * 根据MessageId获取回复list
     */
    @RequestMapping(value = "/commentList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CommentResponse commentList(@RequestBody Comment comment) throws IonaException {
        MyHttpStatus status = MyHttpStatus.OK;

        List<Comment> comments = commentService.getMessageComment(comment.getMessageId());

        return new CommentResponse(status,comments);
    }




}
