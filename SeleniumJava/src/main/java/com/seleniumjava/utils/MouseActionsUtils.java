package com.seleniumjava.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.*;

/**
 * Mouse Actions Utility Class
 * Handles mouse events: hover, click, drag-drop, double-click, etc.
 */
public class MouseActionsUtils {
    
    /**
     * Hover over an element
     * @param driver WebDriver instance
     * @param element WebElement to hover over
     */
    public static void hoverElement(WebDriver driver, WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).build().perform();
            System.out.println("Hovered over element");
        } catch (Exception e) {
            System.err.println("Error hovering over element");
            e.printStackTrace();
        }
    }
    
    /**
     * Double-click on an element
     * @param driver WebDriver instance
     * @param element WebElement to double-click
     */
    public static void doubleClick(WebDriver driver, WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.doubleClick(element).build().perform();
            System.out.println("Double-clicked element");
        } catch (Exception e) {
            System.err.println("Error double-clicking element");
            e.printStackTrace();
        }
    }
    
    /**
     * Right-click (context click) on an element
     * @param driver WebDriver instance
     * @param element WebElement to right-click
     */
    public static void rightClick(WebDriver driver, WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.contextClick(element).build().perform();
            System.out.println("Right-clicked element");
        } catch (Exception e) {
            System.err.println("Error right-clicking element");
            e.printStackTrace();
        }
    }
    
    /**
     * Drag and drop element to target
     * @param driver WebDriver instance
     * @param source source WebElement
     * @param target target WebElement
     */
    public static void dragAndDrop(WebDriver driver, WebElement source, WebElement target) {
        try {
            Actions actions = new Actions(driver);
            actions.dragAndDrop(source, target).build().perform();
            System.out.println("Dragged and dropped element");
        } catch (Exception e) {
            System.err.println("Error dragging and dropping element");
            e.printStackTrace();
        }
    }
    
    /**
     * Drag element by offset
     * @param driver WebDriver instance
     * @param element element to drag
     * @param xOffset horizontal offset
     * @param yOffset vertical offset
     */
    public static void dragByOffset(WebDriver driver, WebElement element, int xOffset, int yOffset) {
        try {
            Actions actions = new Actions(driver);
            actions.dragAndDropBy(element, xOffset, yOffset).build().perform();
            System.out.println("Dragged element by offset: x=" + xOffset + ", y=" + yOffset);
        } catch (Exception e) {
            System.err.println("Error dragging element by offset");
            e.printStackTrace();
        }
    }
    
    /**
     * Move to element
     * @param driver WebDriver instance
     * @param element element to move to
     */
    public static void moveToElement(WebDriver driver, WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).build().perform();
            System.out.println("Moved to element");
        } catch (Exception e) {
            System.err.println("Error moving to element");
            e.printStackTrace();
        }
    }
    
    /**
     * Move to specific coordinates
     * @param driver WebDriver instance
     * @param xOffset x coordinate
     * @param yOffset y coordinate
     */
    public static void moveByOffset(WebDriver driver, int xOffset, int yOffset) {
        try {
            Actions actions = new Actions(driver);
            actions.moveByOffset(xOffset, yOffset).build().perform();
            System.out.println("Moved by offset: x=" + xOffset + ", y=" + yOffset);
        } catch (Exception e) {
            System.err.println("Error moving by offset");
            e.printStackTrace();
        }
    }
    
    /**
     * Click and hold on element
     * @param driver WebDriver instance
     * @param element element to click and hold
     */
    public static void clickAndHold(WebDriver driver, WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.clickAndHold(element).build().perform();
            System.out.println("Clicked and held element");
        } catch (Exception e) {
            System.err.println("Error clicking and holding element");
            e.printStackTrace();
        }
    }
    
    /**
     * Release held element
     * @param driver WebDriver instance
     * @param element element to release
     */
    public static void release(WebDriver driver, WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.release(element).build().perform();
            System.out.println("Released element");
        } catch (Exception e) {
            System.err.println("Error releasing element");
            e.printStackTrace();
        }
    }
    
    /**
     * Scroll to element into view
     * @param driver WebDriver instance
     * @param element element to scroll to
     */
    public static void scrollToElement(WebDriver driver, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            System.out.println("Scrolled to element");
        } catch (Exception e) {
            System.err.println("Error scrolling to element");
            e.printStackTrace();
        }
    }
    
    /**
     * Scroll page vertically
     * @param driver WebDriver instance
     * @param pixels pixels to scroll (positive = down, negative = up)
     */
    public static void scrollPage(WebDriver driver, int pixels) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, " + pixels + ");");
            System.out.println("Scrolled page by: " + pixels + " pixels");
        } catch (Exception e) {
            System.err.println("Error scrolling page");
            e.printStackTrace();
        }
    }
    
    /**
     * Scroll to top of page
     * @param driver WebDriver instance
     */
    public static void scrollToTop(WebDriver driver) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, 0);");
            System.out.println("Scrolled to top");
        } catch (Exception e) {
            System.err.println("Error scrolling to top");
            e.printStackTrace();
        }
    }
    
    /**
     * Scroll to bottom of page
     * @param driver WebDriver instance
     */
    public static void scrollToBottom(WebDriver driver) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            System.out.println("Scrolled to bottom");
        } catch (Exception e) {
            System.err.println("Error scrolling to bottom");
            e.printStackTrace();
        }
    }
    
    /**
     * Get element coordinates
     * @param driver WebDriver instance
     * @param element WebElement
     * @return array [x, y] coordinates
     */
    public static int[] getElementCoordinates(WebDriver driver, WebElement element) {
        try {
            int x = element.getLocation().getX();
            int y = element.getLocation().getY();
            System.out.println("Element coordinates: x=" + x + ", y=" + y);
            return new int[]{x, y};
        } catch (Exception e) {
            System.err.println("Error getting element coordinates");
            e.printStackTrace();
            return new int[]{0, 0};
        }
    }
    
    /**
     * Get element size
     * @param driver WebDriver instance
     * @param element WebElement
     * @return array [width, height]
     */
    public static int[] getElementSize(WebDriver driver, WebElement element) {
        try {
            int width = element.getSize().getWidth();
            int height = element.getSize().getHeight();
            System.out.println("Element size: width=" + width + ", height=" + height);
            return new int[]{width, height};
        } catch (Exception e) {
            System.err.println("Error getting element size");
            e.printStackTrace();
            return new int[]{0, 0};
        }
    }
    
    /**
     * Slow down mouse movements
     * @param driver WebDriver instance
     * @param element element to hover
     * @param duration duration in milliseconds
     */
    public static void slowMoveToElement(WebDriver driver, WebElement element, long duration) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element)
                   .pause(duration)
                   .build()
                   .perform();
            System.out.println("Slowly moved to element");
        } catch (Exception e) {
            System.err.println("Error during slow movement");
            e.printStackTrace();
        }
    }
}
