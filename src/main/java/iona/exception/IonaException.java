package iona.exception;

public class IonaException extends Exception {

    public IonaException() {
        super("系统异常,请联系管理员");
    }

    public IonaException(String message) {
        super(message);
    }
}
