package iona.modelView;


import iona.util.MyHttpStatus;

import java.io.Serializable;

public class BaseModelView implements Serializable {
    private MyHttpStatus status;
    private String msg;
    private String errMsg = "";

    public BaseModelView(MyHttpStatus status, String msg, String errMsg) {
        this.status = status;
        this.msg = msg;
        this.errMsg = errMsg;
    }

    public BaseModelView(MyHttpStatus status) {
        this.status = status;
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

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
