package com.gaohuan.log;


import org.apache.log4j.Logger;

/**
 * Created by gh on 2016/4/28 0028.
 */
public class LogUtils {

    public static final void debug(Logger logger, String msg, Object... args) {
        logger.debug(String.format(msg, args));
    }

    public static final void info(Logger logger, String msg, Object... args) {
        logger.debug(String.format(msg, args));
    }

    public static final void error(Logger logger, String msg, Throwable t, Object... args) {
        logger.debug(String.format(msg, args), t);
    }
}
