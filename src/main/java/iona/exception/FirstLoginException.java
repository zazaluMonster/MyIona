package iona.exception;

/**
 * 首次登录异常
 */
public class FirstLoginException extends Exception {
    /*
     * serialVersionUID
     */
    private static final long serialVersionUID = 9017748501441430756L;


    public FirstLoginException(String reason) {
        super (reason);
    }
}
