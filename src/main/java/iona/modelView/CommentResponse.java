package iona.modelView;


import iona.pojo.Comment;
import iona.util.MyHttpStatus;

import java.util.List;

public class CommentResponse extends BaseModelView {
    private List<Comment> comments;

    public CommentResponse(MyHttpStatus status) {
        super(status);
    }

    public CommentResponse(MyHttpStatus status, List<Comment> comments) {
        super(status);
        this.comments = comments;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
