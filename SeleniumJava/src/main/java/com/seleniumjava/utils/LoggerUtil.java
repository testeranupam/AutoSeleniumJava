package com.seleniumjava.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logger utility for consistent logging across the framework
 * Updated to use SLF4J with Reload4j backend instead of deprecated Log4j
 */
public class LoggerUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);
    
    /**
     * Log info message
     * @param message Message to log
     */
    public static void info(String message) {
        logger.info(message);
    }
    
    /**
     * Log debug message
     * @param message Message to log
     */
    public static void debug(String message) {
        logger.debug(message);
    }
    
    /**
     * Log warning message
     * @param message Message to log
     */
    public static void warn(String message) {
        logger.warn(message);
    }
    
    /**
     * Log error message
     * @param message Message to log
     */
    public static void error(String message) {
        logger.error(message);
    }
    
    /**
     * Log error message with exception
     * @param message Message to log
     * @param throwable Exception to log
     */
    public static void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }
    
    /**
     * Log fatal/critical message (mapped to error in SLF4J)
     * @param message Message to log
     */
    public static void fatal(String message) {
        logger.error(message);
    }
    
    /**
     * Log fatal/critical message with exception (mapped to error in SLF4J)
     * @param message Message to log
     * @param throwable Exception to log
     */
    public static void fatal(String message, Throwable throwable) {
        logger.error(message, throwable);
    }
}
