package iona.modelView;


import iona.pojo.Follow;
import iona.util.MyHttpStatus;

public class FollowResponse extends BaseModelView {

    private Follow follow;

    public FollowResponse(MyHttpStatus status) {
        super(status);
    }

    public FollowResponse(MyHttpStatus status, Follow follow) {
        super(status);
        this.follow = follow;
    }

    public Follow getFollow() {
        return follow;
    }

    public void setFollow(Follow follow) {
        this.follow = follow;
    }
}
