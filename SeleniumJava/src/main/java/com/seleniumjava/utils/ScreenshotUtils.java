package com.seleniumjava.utils;

import org.openqa.selenium.*;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Screenshot Utility Class
 * Handles screenshot capture for test failures and specific scenarios
 */
public class ScreenshotUtils {
    
    private static final String SCREENSHOT_DIR = "target/screenshots/";
    
    /**
     * Take screenshot and save to default directory
     * @param driver WebDriver instance
     * @param testName name of test for filename
     * @return screenshot file path
     */
    public static String takeScreenshot(WebDriver driver, String testName) {
        try {
            // Create screenshots directory if not exists
            File screenshotDir = new File(SCREENSHOT_DIR);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }
            
            // Generate filename with timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = testName + "_" + timestamp + ".png";
            String filePath = SCREENSHOT_DIR + fileName;
            
            // Take screenshot
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File(filePath);
            
            // Copy to destination
            FileUtils.copyFile(source, destination);
            
            System.out.println("Screenshot saved: " + filePath);
            return filePath;
        } catch (IOException e) {
            System.err.println("Failed to take screenshot: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Take screenshot and save to custom directory
     * @param driver WebDriver instance
     * @param testName name of test
     * @param customDir custom directory path
     * @return screenshot file path
     */
    public static String takeScreenshot(WebDriver driver, String testName, String customDir) {
        try {
            // Create custom directory if not exists
            File screenshotDir = new File(customDir);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }
            
            // Generate filename with timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = testName + "_" + timestamp + ".png";
            String filePath = customDir + File.separator + fileName;
            
            // Take screenshot
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File(filePath);
            
            // Copy to destination
            FileUtils.copyFile(source, destination);
            
            System.out.println("Screenshot saved: " + filePath);
            return filePath;
        } catch (IOException e) {
            System.err.println("Failed to take screenshot: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Take screenshot as byte array (for TestNG reports)
     * @param driver WebDriver instance
     * @return screenshot as byte array
     */
    public static byte[] takeScreenshotAsBytes(WebDriver driver) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            return ts.getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            System.err.println("Failed to take screenshot as bytes: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Take screenshot as Base64 string
     * @param driver WebDriver instance
     * @return screenshot as Base64 string
     */
    public static String takeScreenshotAsBase64(WebDriver driver) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            return ts.getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            System.err.println("Failed to take screenshot as Base64: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Take screenshot of specific element
     * @param element WebElement to capture
     * @param testName name of test
     * @return screenshot file path
     */
    public static String takeElementScreenshot(WebElement element, String testName) {
        try {
            // Create screenshots directory if not exists
            File screenshotDir = new File(SCREENSHOT_DIR);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }
            
            // Generate filename with timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = testName + "_element_" + timestamp + ".png";
            String filePath = SCREENSHOT_DIR + fileName;
            
            // Take element screenshot
            File source = element.getScreenshotAs(OutputType.FILE);
            File destination = new File(filePath);
            
            // Copy to destination
            FileUtils.copyFile(source, destination);
            
            System.out.println("Element screenshot saved: " + filePath);
            return filePath;
        } catch (IOException e) {
            System.err.println("Failed to take element screenshot: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Take screenshot on test failure (for TestNG listeners)
     * @param driver WebDriver instance
     * @param methodName test method name
     * @return screenshot file path
     */
    public static String captureFailureScreenshot(WebDriver driver, String methodName) {
        String fileName = "FAILURE_" + methodName;
        return takeScreenshot(driver, fileName);
    }
    
    /**
     * Take screenshot with custom filename
     * @param driver WebDriver instance
     * @param fileName custom filename (without extension)
     * @return screenshot file path
     */
    public static String takeScreenshotWithCustomName(WebDriver driver, String fileName) {
        try {
            // Create screenshots directory if not exists
            File screenshotDir = new File(SCREENSHOT_DIR);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }
            
            String filePath = SCREENSHOT_DIR + fileName + ".png";
            
            // Take screenshot
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File(filePath);
            
            // Copy to destination
            FileUtils.copyFile(source, destination);
            
            System.out.println("Screenshot saved: " + filePath);
            return filePath;
        } catch (IOException e) {
            System.err.println("Failed to take screenshot: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get default screenshot directory
     * @return screenshot directory path
     */
    public static String getScreenshotDirectory() {
        return SCREENSHOT_DIR;
    }
    
    /**
     * Delete all screenshots in default directory
     */
    public static void cleanupScreenshots() {
        try {
            File screenshotDir = new File(SCREENSHOT_DIR);
            if (screenshotDir.exists()) {
                FileUtils.cleanDirectory(screenshotDir);
                System.out.println("Screenshots cleaned up");
            }
        } catch (IOException e) {
            System.err.println("Failed to cleanup screenshots: " + e.getMessage());
        }
    }
    
    /**
     * Count screenshots in directory
     * @return number of screenshots
     */
    public static int getScreenshotCount() {
        File screenshotDir = new File(SCREENSHOT_DIR);
        if (screenshotDir.exists() && screenshotDir.isDirectory()) {
            File[] files = screenshotDir.listFiles((dir, name) -> name.endsWith(".png"));
            return files != null ? files.length : 0;
        }
        return 0;
    }
}
