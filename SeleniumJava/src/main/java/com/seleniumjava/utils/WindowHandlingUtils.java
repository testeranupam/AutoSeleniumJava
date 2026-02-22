package com.seleniumjava.utils;

import org.openqa.selenium.*;
import java.util.*;

/**
 * Window Handling Utility Class
 * Manages window and tab switching operations
 */
public class WindowHandlingUtils {
    
    /**
     * Get current window handle
     * @param driver WebDriver instance
     * @return current window handle
     */
    public static String getCurrentWindowHandle(WebDriver driver) {
        return driver.getWindowHandle();
    }
    
    /**
     * Get all window handles
     * @param driver WebDriver instance
     * @return Set of all window handles
     */
    public static Set<String> getAllWindowHandles(WebDriver driver) {
        return driver.getWindowHandles();
    }
    
    /**
     * Get window count
     * @param driver WebDriver instance
     * @return number of open windows/tabs
     */
    public static int getWindowCount(WebDriver driver) {
        return driver.getWindowHandles().size();
    }
    
    /**
     * Switch to window/tab by index
     * @param driver WebDriver instance
     * @param index index of window (0-based)
     */
    public static void switchToWindowByIndex(WebDriver driver, int index) {
        try {
            List<String> handles = new ArrayList<>(driver.getWindowHandles());
            
            if (index < 0 || index >= handles.size()) {
                System.err.println("Invalid window index: " + index);
                return;
            }
            
            driver.switchTo().window(handles.get(index));
            System.out.println("Switched to window: " + index);
        } catch (Exception e) {
            System.err.println("Error switching to window by index: " + index);
            e.printStackTrace();
        }
    }
    
    /**
     * Switch to parent window (first opened)
     * @param driver WebDriver instance
     */
    public static void switchToParentWindow(WebDriver driver) {
        try {
            List<String> handles = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(handles.get(0));
            System.out.println("Switched to parent window");
        } catch (Exception e) {
            System.err.println("Error switching to parent window");
            e.printStackTrace();
        }
    }
    
    /**
     * Switch to child window (last opened)
     * @param driver WebDriver instance
     */
    public static void switchToChildWindow(WebDriver driver) {
        try {
            List<String> handles = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(handles.get(handles.size() - 1));
            System.out.println("Switched to child window");
        } catch (Exception e) {
            System.err.println("Error switching to child window");
            e.printStackTrace();
        }
    }
    
    /**
     * Switch to window by title (partial match)
     * @param driver WebDriver instance
     * @param titleContains text to find in title
     * @return true if window found and switched
     */
    public static boolean switchToWindowByTitle(WebDriver driver, String titleContains) {
        try {
            String currentHandle = driver.getWindowHandle();
            
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
                if (driver.getTitle().contains(titleContains)) {
                    System.out.println("Switched to window with title: " + titleContains);
                    return true;
                }
            }
            
            // Revert to original window if not found
            driver.switchTo().window(currentHandle);
            System.err.println("Window with title not found: " + titleContains);
            return false;
        } catch (Exception e) {
            System.err.println("Error switching to window by title: " + titleContains);
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Switch to window by URL (partial match)
     * @param driver WebDriver instance
     * @param urlContains text to find in URL
     * @return true if window found and switched
     */
    public static boolean switchToWindowByURL(WebDriver driver, String urlContains) {
        try {
            String currentHandle = driver.getWindowHandle();
            
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
                if (driver.getCurrentUrl().contains(urlContains)) {
                    System.out.println("Switched to window with URL: " + urlContains);
                    return true;
                }
            }
            
            // Revert to original window if not found
            driver.switchTo().window(currentHandle);
            System.err.println("Window with URL not found: " + urlContains);
            return false;
        } catch (Exception e) {
            System.err.println("Error switching to window by URL: " + urlContains);
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Close current window/tab
     * @param driver WebDriver instance
     */
    public static void closeCurrentWindow(WebDriver driver) {
        try {
            driver.close();
            System.out.println("Current window closed");
        } catch (Exception e) {
            System.err.println("Error closing current window");
            e.printStackTrace();
        }
    }
    
    /**
     * Close all windows except parent
     * @param driver WebDriver instance
     */
    public static void closeAllWindowsExceptParent(WebDriver driver) {
        try {
            String parentHandle = new ArrayList<>(driver.getWindowHandles()).get(0);
            
            for (String handle : driver.getWindowHandles()) {
                if (!handle.equals(parentHandle)) {
                    driver.switchTo().window(handle);
                    driver.close();
                }
            }
            
            driver.switchTo().window(parentHandle);
            System.out.println("All child windows closed");
        } catch (Exception e) {
            System.err.println("Error closing child windows");
            e.printStackTrace();
        }
    }
    
    /**
     * Get title of specific window
     * @param driver WebDriver instance
     * @param index window index
     * @return window title
     */
    public static String getWindowTitle(WebDriver driver, int index) {
        try {
            List<String> handles = new ArrayList<>(driver.getWindowHandles());
            String currentHandle = driver.getWindowHandle();
            
            driver.switchTo().window(handles.get(index));
            String title = driver.getTitle();
            
            driver.switchTo().window(currentHandle);
            return title;
        } catch (Exception e) {
            System.err.println("Error getting window title");
            e.printStackTrace();
            return "";
        }
    }
    
    /**
     * Get URL of specific window
     * @param driver WebDriver instance
     * @param index window index
     * @return window URL
     */
    public static String getWindowURL(WebDriver driver, int index) {
        try {
            List<String> handles = new ArrayList<>(driver.getWindowHandles());
            String currentHandle = driver.getWindowHandle();
            
            driver.switchTo().window(handles.get(index));
            String url = driver.getCurrentUrl();
            
            driver.switchTo().window(currentHandle);
            return url;
        } catch (Exception e) {
            System.err.println("Error getting window URL");
            e.printStackTrace();
            return "";
        }
    }
}
