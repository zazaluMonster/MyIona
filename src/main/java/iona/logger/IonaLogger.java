package iona.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class IonaLogger {

    private static final Logger logger = LoggerFactory.getLogger(IonaLogger.class);

    private static String IONA_LOGGER_HEADER = "Iona: ";

    public static void trace(String msg){
        logger.trace(IONA_LOGGER_HEADER + msg);
    }

    public static void debug(String msg){
        logger.debug(IONA_LOGGER_HEADER + msg);
    }

    public static void info(String msg){
        logger.info(IONA_LOGGER_HEADER + msg);
    }

    public static void warn(String msg){
        logger.warn(IONA_LOGGER_HEADER + msg);
    }

    public static void error(String msg){
        logger.error(IONA_LOGGER_HEADER + msg);
    }
}
