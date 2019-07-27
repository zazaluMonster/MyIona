package iona.exception.handler;

import iona.exception.IonaException;
import iona.modelView.GeneralResponse;
import iona.util.MyHttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class IonaExceptionHandler {

    /**
     * 所有controller层抛出的IonaException异常都会再此统一处理
     */
    @ExceptionHandler(IonaException.class)
    @ResponseBody
    public GeneralResponse errorHandler(Exception e) {
        return new GeneralResponse(MyHttpStatus.ERROR);
    }

    /**
     * 所有controller层抛出的IOException异常都会再此统一处理
     */
    @ExceptionHandler(IOException.class)
    @ResponseBody
    public GeneralResponse errorHandlerIO(Exception e) {
        return new GeneralResponse(MyHttpStatus.ERROR);
    }
}
