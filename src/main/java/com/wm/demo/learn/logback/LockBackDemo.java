package com.wm.demo.learn.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LockBackDemo {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LockBackDemo.class);

    public static void main(String[] args) {
        LOGGER.trace("logback的--trace日志--输出了");
        LOGGER.info("logback的--info日志--输出了");
        LOGGER.debug("logback的--debug日志--输出了");
        LOGGER.warn("logback的--warn日志--输出了");
        LOGGER.error("logback的--error日志--输出了");
    }
}
