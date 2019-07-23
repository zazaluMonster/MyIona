package iona.modelView;

import iona.util.MyHttpStatus;

public class GeneralModelView extends BaseModelView {
    public String verifyCode;

    public GeneralModelView(MyHttpStatus status, String msg, String errMsg, String verifyCode) {
        super(status, msg, errMsg);
        this.verifyCode = verifyCode;
    }

    public GeneralModelView(MyHttpStatus status, String verifyCode) {
        super(status);
        this.verifyCode = verifyCode;
    }


    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

}
