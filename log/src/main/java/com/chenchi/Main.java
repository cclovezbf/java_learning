package com.chenchi;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static final Logger logger = LoggerFactory.getLogger(Main.class.getName());
    public static void main(String[] args) {
            logger.info("Hello World");
            logger.warn("Hello World");
            logger.error("Hello World");

    }
}