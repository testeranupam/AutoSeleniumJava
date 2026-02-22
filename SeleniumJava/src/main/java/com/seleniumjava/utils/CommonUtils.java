package com.seleniumjava.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;
import java.util.Set;

/**
 * CommonUtils class containing utility methods for common operations
 */
public class CommonUtils {
    private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    /**
     * Wait for page to load completely
     * @param driver - WebDriver instance
     */
    public static void waitForPageLoad(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
        logger.info("Page loaded successfully");
    }

    /**
     * Wait for URL to contain specific text
     * @param driver - WebDriver instance
     * @param urlPart - Part of URL to wait for
     */
    public static void waitForUrlContains(WebDriver driver, String urlPart) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains(urlPart));
        logger.info("URL contains: " + urlPart);
    }

    /**
     * Handle JavaScript alert
     * @param driver - WebDriver instance
     * @param action - "accept" to accept, "dismiss" to reject
     */
    public static void handleAlert(WebDriver driver, String action) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            
            if (action.toLowerCase().equals("accept")) {
                alert.accept();
                logger.info("Alert accepted");
            } else if (action.toLowerCase().equals("dismiss")) {
                alert.dismiss();
                logger.info("Alert dismissed");
            }
        } catch (Exception e) {
            logger.error("Error handling alert", e);
        }
    }

    /**
     * Get alert text
     * @param driver - WebDriver instance
     * @return Alert text
     */
    public static String getAlertText(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            return alert.getText();
        } catch (Exception e) {
            logger.error("Error getting alert text", e);
            return "";
        }
    }

    /**
     * Switch to window by index
     * @param driver - WebDriver instance
     * @param windowIndex - Window index (0, 1, 2, etc.)
     */
    public static void switchToWindow(WebDriver driver, int windowIndex) {
        Set<String> windowHandles = driver.getWindowHandles();
        if (windowIndex < windowHandles.size()) {
            String window = (String) windowHandles.toArray()[windowIndex];
            driver.switchTo().window(window);
            logger.info("Switched to window: " + windowIndex);
        } else {
            logger.warn("Window index out of range");
        }
    }

    /**
     * Switch to frame by index
     * @param driver - WebDriver instance
     * @param frameIndex - Frame index
     */
    public static void switchToFrame(WebDriver driver, int frameIndex) {
        driver.switchTo().frame(frameIndex);
        logger.info("Switched to frame: " + frameIndex);
    }

    /**
     * Switch to frame by name or ID
     * @param driver - WebDriver instance
     * @param frameNameOrId - Frame name or ID
     */
    public static void switchToFrame(WebDriver driver, String frameNameOrId) {
        driver.switchTo().frame(frameNameOrId);
        logger.info("Switched to frame: " + frameNameOrId);
    }

    /**
     * Switch to default content (exit frame)
     * @param driver - WebDriver instance
     */
    public static void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
        logger.info("Switched to default content");
    }

    /**
     * Close all windows except main window
     * @param driver - WebDriver instance
     */
    public static void closeOtherWindows(WebDriver driver) {
        String mainWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        
        for (String window : allWindows) {
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window);
                driver.close();
            }
        }
        driver.switchTo().window(mainWindow);
        logger.info("Other windows closed");
    }

    /**
     * Wait for specified milliseconds
     * @param milliseconds - Wait time in milliseconds
     */
    public static void threadWait(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
            logger.info("Waited for: " + milliseconds + " ms");
        } catch (InterruptedException e) {
            logger.error("Thread interrupted", e);
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Get page source
     * @param driver - WebDriver instance
     * @return Page source
     */
    public static String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    /**
     * Execute JavaScript and return result
     * @param driver - WebDriver instance
     * @param script - JavaScript to execute
     * @return Script execution result
     */
    public static Object executeScript(WebDriver driver, String script) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(script);
    }

    /**
     * Get screenshot as base64 (useful for reporting)
     * @param driver - WebDriver instance
     * @return Base64 encoded screenshot
     */
    public static String captureScreenshot(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript(
                "return canvas.toDataURL('image/png');", "");
    }
}
