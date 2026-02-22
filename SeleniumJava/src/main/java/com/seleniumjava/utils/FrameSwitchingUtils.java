package com.seleniumjava.utils;

import org.openqa.selenium.*;

/**
 * Frame Switching Utility Class
 * Handles iframe and frame switching operations
 */
public class FrameSwitchingUtils {
    
    /**
     * Switch to frame by index
     * @param driver WebDriver instance
     * @param frameIndex index of frame (0-based)
     */
    public static void switchToFrameByIndex(WebDriver driver, int frameIndex) {
        try {
            driver.switchTo().frame(frameIndex);
            System.out.println("Switched to frame by index: " + frameIndex);
        } catch (Exception e) {
            System.err.println("Error switching to frame by index: " + frameIndex);
            e.printStackTrace();
        }
    }
    
    /**
     * Switch to frame by name or ID
     * @param driver WebDriver instance
     * @param nameOrId name or id attribute of frame
     */
    public static void switchToFrameByNameOrId(WebDriver driver, String nameOrId) {
        try {
            driver.switchTo().frame(nameOrId);
            System.out.println("Switched to frame: " + nameOrId);
        } catch (Exception e) {
            System.err.println("Error switching to frame: " + nameOrId);
            e.printStackTrace();
        }
    }
    
    /**
     * Switch to frame by WebElement
     * @param driver WebDriver instance
     * @param frameElement WebElement of the frame/iframe
     */
    public static void switchToFrameByElement(WebDriver driver, WebElement frameElement) {
        try {
            driver.switchTo().frame(frameElement);
            System.out.println("Switched to frame element");
        } catch (Exception e) {
            System.err.println("Error switching to frame element");
            e.printStackTrace();
        }
    }
    
    /**
     * Switch to frame by locator
     * @param driver WebDriver instance
     * @param frameLocator By locator for the frame element
     */
    public static void switchToFrameByLocator(WebDriver driver, By frameLocator) {
        try {
            WebElement frameElement = driver.findElement(frameLocator);
            driver.switchTo().frame(frameElement);
            System.out.println("Switched to frame by locator");
        } catch (Exception e) {
            System.err.println("Error switching to frame by locator");
            e.printStackTrace();
        }
    }
    
    /**
     * Switch to parent frame
     * @param driver WebDriver instance
     */
    public static void switchToParentFrame(WebDriver driver) {
        try {
            driver.switchTo().parentFrame();
            System.out.println("Switched to parent frame");
        } catch (Exception e) {
            System.err.println("Error switching to parent frame");
            e.printStackTrace();
        }
    }
    
    /**
     * Switch to default content (main page, outside all frames)
     * @param driver WebDriver instance
     */
    public static void switchToDefaultContent(WebDriver driver) {
        try {
            driver.switchTo().defaultContent();
            System.out.println("Switched to default content");
        } catch (Exception e) {
            System.err.println("Error switching to default content");
            e.printStackTrace();
        }
    }
    
    /**
     * Switch to nested frame (frame within frame)
     * @param driver WebDriver instance
     * @param frameIndices array of frame indices (parent to child)
     */
    public static void switchToNestedFrame(WebDriver driver, int... frameIndices) {
        try {
            driver.switchTo().defaultContent();
            
            for (int index : frameIndices) {
                driver.switchTo().frame(index);
            }
            
            System.out.println("Switched to nested frame");
        } catch (Exception e) {
            System.err.println("Error switching to nested frame");
            e.printStackTrace();
        }
    }
    
    /**
     * Get frame count on current page
     * @param driver WebDriver instance
     * @return number of frames
     */
    public static int getFrameCount(WebDriver driver) {
        try {
            driver.switchTo().defaultContent();
            return driver.findElements(By.tagName("iframe")).size() + 
                   driver.findElements(By.tagName("frame")).size();
        } catch (Exception e) {
            System.err.println("Error getting frame count");
            e.printStackTrace();
            return 0;
        }
    }
    
    /**
     * Check if element is in an iframe
     * @param driver WebDriver instance
     * @param elementLocator locator of element to check
     * @param frameLocator locator of potential iframe
     * @return true if element is found in iframe
     */
    public static boolean isElementInFrame(WebDriver driver, By elementLocator, By frameLocator) {
        try {
            driver.switchTo().defaultContent();
            WebElement iframe = driver.findElement(frameLocator);
            driver.switchTo().frame(iframe);
            
            WebElement element = driver.findElement(elementLocator);
            driver.switchTo().defaultContent();
            
            return element != null;
        } catch (Exception e) {
            driver.switchTo().defaultContent();
            return false;
        }
    }
    
    /**
     * Find which iframe contains a specific element
     * @param driver WebDriver instance
     * @param elementLocator locator of element to search
     * @return index of iframe containing element, or -1 if not found
     */
    public static int findFrameWithElement(WebDriver driver, By elementLocator) {
        try {
            driver.switchTo().defaultContent();
            java.util.List<WebElement> frames = driver.findElements(By.tagName("iframe"));
            
            for (int i = 0; i < frames.size(); i++) {
                driver.switchTo().frame(i);
                
                try {
                    driver.findElement(elementLocator);
                    System.out.println("Element found in frame: " + i);
                    driver.switchTo().defaultContent();
                    return i;
                } catch (NoSuchElementException e) {
                    driver.switchTo().defaultContent();
                }
            }
            
            System.err.println("Element not found in any frame");
            return -1;
        } catch (Exception e) {
            System.err.println("Error finding frame with element");
            e.printStackTrace();
            return -1;
        }
    }
}
