package iona.modelView;


import iona.util.MyHttpStatus;

import java.io.Serializable;

public class BaseModelView implements Serializable {
    private MyHttpStatus status;
    private String msg;

    public BaseModelView(MyHttpStatus status) {
        this.status = status;
        this.msg = status.getReasonPhrase();
    }

    public BaseModelView(MyHttpStatus status,String msg) {
        this.status = status;
        this.msg = msg;
    }

    public MyHttpStatus getStatus() {
        return status;
    }

    public void setStatus(MyHttpStatus status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
