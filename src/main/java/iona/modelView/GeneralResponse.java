package iona.modelView;

import iona.util.MyHttpStatus;

public class GeneralResponse extends BaseModelView {

    private String verifyCode;

    public GeneralResponse(MyHttpStatus status, String verifyCode) {
        super(status);
        this.verifyCode = verifyCode;
    }

    public GeneralResponse(MyHttpStatus status) {
        super(status);
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

}
