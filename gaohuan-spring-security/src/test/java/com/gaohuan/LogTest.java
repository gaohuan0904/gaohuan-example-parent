package com.gaohuan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gh on 2016/5/18 0018.
 */
public class LogTest {
    public static final Logger logger = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args) {
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
    }
}

