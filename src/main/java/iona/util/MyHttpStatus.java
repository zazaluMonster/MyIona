package iona.util;

public enum MyHttpStatus {
    OK(0,"成功"),
    LOGIN_FAIL(1,"用户名或者密码错误"),
    NETWORK_AUTHENTICATION_REQUIRED(2,"Network Authentication Required"),
    REGISTER_FAIL(3, "注册失败,信息重复"),
    REGISTER_SUCCESS(4, "注册成功");

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
