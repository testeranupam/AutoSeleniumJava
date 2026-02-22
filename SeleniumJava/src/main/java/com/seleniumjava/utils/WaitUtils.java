package com.seleniumjava.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.function.Function;

/**
 * Wait Utility Class
 * Handles all types of waits: Implicit, Explicit, and Fluent Waits
 */
public class WaitUtils {
    
    // ======================== IMPLICIT WAIT ========================
    
    /**
     * Set Implicit Wait
     * Applied globally to WebDriver - waits for elements to appear
     * @param driver WebDriver instance
     * @param seconds timeout in seconds
     */
    public static void setImplicitWait(WebDriver driver, int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
        System.out.println("Implicit wait set to: " + seconds + " seconds");
    }
    
    /**
     * Remove Implicit Wait
     * @param driver WebDriver instance
     */
    public static void removeImplicitWait(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        System.out.println("Implicit wait removed");
    }
    
    // ======================== EXPLICIT WAIT ========================
    
    /**
     * Wait for element to be visible
     * @param driver WebDriver instance
     * @param locator By locator
     * @param timeoutSeconds timeout in seconds
     * @return WebElement
     */
    public static WebElement waitForElementVisible(WebDriver driver, By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    /**
     * Wait for element to be clickable
     * @param driver WebDriver instance
     * @param locator By locator
     * @param timeoutSeconds timeout in seconds
     * @return WebElement
     */
    public static WebElement waitForElementClickable(WebDriver driver, By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    /**
     * Wait for element to be present
     * @param driver WebDriver instance
     * @param locator By locator
     * @param timeoutSeconds timeout in seconds
     * @return WebElement
     */
    public static WebElement waitForElementPresent(WebDriver driver, By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    /**
     * Wait for element to be invisible
     * @param driver WebDriver instance
     * @param locator By locator
     * @param timeoutSeconds timeout in seconds
     * @return true if invisible
     */
    public static boolean waitForElementInvisible(WebDriver driver, By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    
    /**
     * Wait for element to be selected
     * @param driver WebDriver instance
     * @param locator By locator
     * @param timeoutSeconds timeout in seconds
     * @return true if selected
     */
    public static boolean waitForElementSelected(WebDriver driver, By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.elementToBeSelected(locator));
    }
    
    /**
     * Wait for text to be present in element
     * @param driver WebDriver instance
     * @param locator By locator
     * @param text text to wait for
     * @param timeoutSeconds timeout in seconds
     * @return true if text present
     */
    public static boolean waitForTextPresent(WebDriver driver, By locator, String text, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }
    
    /**
     * Wait for title to contain text
     * @param driver WebDriver instance
     * @param title partial title text
     * @param timeoutSeconds timeout in seconds
     * @return true if title contains text
     */
    public static boolean waitForTitleContains(WebDriver driver, String title, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.titleContains(title));
    }
    
    /**
     * Wait for URL to contain text
     * @param driver WebDriver instance
     * @param urlFragment URL fragment to wait for
     * @param timeoutSeconds timeout in seconds
     * @return true if URL contains fragment
     */
    public static boolean waitForUrlContains(WebDriver driver, String urlFragment, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.urlContains(urlFragment));
    }
    
    /**
     * Wait for alert to be present
     * @param driver WebDriver instance
     * @param timeoutSeconds timeout in seconds
     * @return Alert object
     */
    public static Alert waitForAlert(WebDriver driver, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.alertIsPresent());
    }
    
    /**
     * Wait for frame and switch to it
     * @param driver WebDriver instance
     * @param locator By locator
     * @param timeoutSeconds timeout in seconds
     * @return WebDriver
     */
    public static WebDriver waitForFrameAndSwitch(WebDriver driver, By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }
    
    /**
     * Wait for number of windows
     * @param driver WebDriver instance
     * @param numberOfWindows expected number of windows
     * @param timeoutSeconds timeout in seconds
     * @return true if condition met
     */
    public static boolean waitForNumberOfWindows(WebDriver driver, int numberOfWindows, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.numberOfWindowsToBe(numberOfWindows));
    }
    
    /**
     * Wait for attribute to contain value
     * @param driver WebDriver instance
     * @param locator By locator
     * @param attribute attribute name
     * @param value expected value
     * @param timeoutSeconds timeout in seconds
     * @return true if attribute contains value
     */
    public static boolean waitForAttributeContains(WebDriver driver, By locator, 
                                                   String attribute, String value, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.attributeContains(locator, attribute, value));
    }
    
    // ======================== FLUENT WAIT ========================
    
    /**
     * Create Fluent Wait with custom conditions
     * @param driver WebDriver instance
     * @param timeoutSeconds timeout in seconds
     * @param pollingIntervalSeconds polling interval in seconds
     * @return FluentWait object
     */
    public static FluentWait<WebDriver> createFluentWait(WebDriver driver, 
                                                        int timeoutSeconds, 
                                                        int pollingIntervalSeconds) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofSeconds(pollingIntervalSeconds))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }
    
    /**
     * Fluent Wait for element to be visible
     * @param driver WebDriver instance
     * @param locator By locator
     * @param timeoutSeconds timeout in seconds
     * @param pollingIntervalSeconds polling interval in seconds
     * @return WebElement
     */
    public static WebElement fluentWaitForElement(WebDriver driver, By locator, 
                                                 int timeoutSeconds, int pollingIntervalSeconds) {
        FluentWait<WebDriver> wait = createFluentWait(driver, timeoutSeconds, pollingIntervalSeconds);
        
        return wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement element = driver.findElement(locator);
                if (element.isDisplayed()) {
                    System.out.println("Element found: " + locator);
                    return element;
                }
                return null;
            }
        });
    }
    
    /**
     * Fluent Wait for element to be clickable
     * @param driver WebDriver instance
     * @param locator By locator
     * @param timeoutSeconds timeout in seconds
     * @param pollingIntervalSeconds polling interval in seconds
     * @return WebElement
     */
    public static WebElement fluentWaitForClickable(WebDriver driver, By locator, 
                                                   int timeoutSeconds, int pollingIntervalSeconds) {
        FluentWait<WebDriver> wait = createFluentWait(driver, timeoutSeconds, pollingIntervalSeconds);
        
        return wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement element = driver.findElement(locator);
                if (element.isDisplayed() && element.isEnabled()) {
                    System.out.println("Element clickable: " + locator);
                    return element;
                }
                return null;
            }
        });
    }
    
    /**
     * Fluent Wait with custom condition
     * @param driver WebDriver instance
     * @param condition custom condition function
     * @param timeoutSeconds timeout in seconds
     * @param pollingIntervalSeconds polling interval in seconds
     * @return result of condition
     */
    public static <T> T fluentWaitForCondition(WebDriver driver, 
                                              Function<WebDriver, T> condition,
                                              int timeoutSeconds, 
                                              int pollingIntervalSeconds) {
        FluentWait<WebDriver> wait = createFluentWait(driver, timeoutSeconds, pollingIntervalSeconds);
        return wait.until(condition);
    }
    
    // ======================== THREAD SLEEP (USE SPARINGLY) ========================
    
    /**
     * Thread sleep - use only when absolutely necessary
     * @param milliseconds milliseconds to sleep
     */
    public static void threadSleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
            System.out.println("Thread slept for: " + milliseconds + " ms");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread sleep interrupted");
        }
    }
    
    /**
     * Page load timeout
     * @param driver WebDriver instance
     * @param seconds timeout in seconds
     */
    public static void setPageLoadTimeout(WebDriver driver, int seconds) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(seconds));
        System.out.println("Page load timeout set to: " + seconds + " seconds");
    }
    
    /**
     * Script timeout
     * @param driver WebDriver instance
     * @param seconds timeout in seconds
     */
    public static void setScriptTimeout(WebDriver driver, int seconds) {
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(seconds));
        System.out.println("Script timeout set to: " + seconds + " seconds");
    }
}
