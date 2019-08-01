package iona.modelView;


import iona.util.MyHttpStatus;

public class LikeResponse extends BaseModelView {


    public LikeResponse(MyHttpStatus status) {
        super(status);
    }

    public LikeResponse(MyHttpStatus status, String msg) {
        super(status, msg);
    }
}
