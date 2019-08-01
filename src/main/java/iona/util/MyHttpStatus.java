package iona.util;

public enum MyHttpStatus {
    OK(0,"成功"),
    ERROR(-1,"系统严重报错!"),
    IONA_ERROR(-1,"Iona业务系统层报错,未设定预期处理函数,请联系管理员"),
    REQUEST_DATA_EMPTY(-1,"请求数据为空"),
    LOGIN_FAIL(1,"用户名或者密码错误"),
    NETWORK_AUTHENTICATION_REQUIRED(2,"登录失效"),
    REGISTER_FAIL(3, "注册失败,信息重复"),
    REGISTER_SUCCESS(4, "注册成功"),
    PHONE_NUM_ERROR(5, "请先输入手机号"),
    MAIL_ERROR(5, "请先输入邮箱"),
    NO_REGISTER_ERROR(5, "您的信息未注册,请先注册"),
    NO_CREWNAME_ERROR(5, "提交数据异常,请联系管理员"),
    NULL_POINTER_ERROR(5, "空指针异常,请联系管理员"),
    IO_ERROR(5, "IO异常,请联系管理员"),
    OLD_PASSWORD_WRONG(6, "旧密码输入错误");

    private final int value;
    private final String reasonPhrase;

    private MyHttpStatus(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int value() {
        return this.value;
    }

    public int getValue() {
        return value;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }
}
