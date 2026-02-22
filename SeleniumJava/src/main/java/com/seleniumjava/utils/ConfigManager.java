package com.seleniumjava.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigManager class for loading and accessing configuration properties
 */
public class ConfigManager {
    private static Properties properties;
    private static final Logger logger = LoggerFactory.getLogger(ConfigManager.class);

    static {
        properties = new Properties();
        loadProperties();
    }

    /**
     * Load properties from config file
     */
    private static void loadProperties() {
        try {
            String configPath = System.getProperty("user.dir") + File.separator 
                    + "src" + File.separator + "test" + File.separator + "resources" 
                    + File.separator + "config" + File.separator + "config.properties";
            
            File file = new File(configPath);
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                properties.load(fileInputStream);
                fileInputStream.close();
                logger.info("Configuration loaded from: " + configPath);
            } else {
                logger.warn("Config file not found at: " + configPath);
            }
        } catch (IOException e) {
            logger.error("Error loading configuration file", e);
        }
    }

    /**
     * Get property value by key
     * @param key - Property key
     * @return Property value
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Get property value with default value
     * @param key - Property key
     * @param defaultValue - Default value if key not found
     * @return Property value or default value
     */
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Get base URL
     * @return Base URL
     */
    public static String getBaseUrl() {
        return getProperty("base.url", "https://www.google.com");
    }

    /**
     * Get browser type
     * @return Browser type (chrome, firefox)
     */
    public static String getBrowser() {
        return getProperty("browser", "chrome");
    }

    /**
     * Get implicit wait time in seconds
     * @return Wait time in seconds
     */
    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait", "10"));
    }

    /**
     * Get explicit wait time in seconds
     * @return Wait time in seconds
     */
    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait", "10"));
    }

    /**
     * Check if headless mode is enabled
     * @return true if headless mode is enabled
     */
    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless", "false"));
    }
}
