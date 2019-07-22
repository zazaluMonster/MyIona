package iona.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class IonaLogger {

    private static final Logger logger = LoggerFactory.getLogger(IonaLogger.class);

    public static void trace(String msg){
        logger.trace(msg);
    }

    public static void debug(String msg){
        logger.debug(msg);
    }

    public static void info(String msg){
        logger.info(msg);
    }

    public static void warn(String msg){
        logger.warn(msg);
    }

    public static void error(String msg){
        logger.error(msg);
    }
}
