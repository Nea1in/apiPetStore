package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtility {
    private final Logger logger;

    public LoggerUtility(Class<?> clazz) {
        this.logger = LogManager.getLogger(clazz);
    }

    public void info(String message, Object... params) {
        logger.info(message, params);
    }

    public void warn(String message, Object... params) {
        logger.warn(message, params);
    }

    public void error(String message, Object... params) {
        logger.error(message, params);
    }

    public void debug(String message, Object... params) {
        logger.debug(message, params);
    }
}