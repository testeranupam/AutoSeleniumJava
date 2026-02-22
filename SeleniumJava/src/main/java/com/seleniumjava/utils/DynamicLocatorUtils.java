package com.seleniumjava.utils;

import org.openqa.selenium.*;

/**
 * Dynamic Locator Utility Class
 * Handles dynamic locators with parameters and complex selectors
 * 
 * Provides helper methods for creating dynamic locators using parameter substitution.
 * Useful for elements with dynamic attributes or text that changes at runtime.
 * 
 * For comprehensive information about all locator strategies, XPath types, axes, functions,
 * and patterns, see: @see com.seleniumjava.pages.BasePage
 */
public class DynamicLocatorUtils {
    
    /**
     * Create dynamic XPath with parameter substitution
     * @param xpathTemplate XPath template with {0}, {1}, etc. placeholders
     * @param values values to substitute
     * @return final XPath
     * 
     * Example:
     * xpathTemplate: "//button[contains(text(), '{0}')]"
     * values: ["Click Me"]
     * returns: "//button[contains(text(), 'Click Me')]"
     */
    public static String getDynamicXPath(String xpathTemplate, String... values) {
        String xpath = xpathTemplate;
        
        for (int i = 0; i < values.length; i++) {
            xpath = xpath.replace("{" + i + "}", values[i]);
        }
        
        System.out.println("Dynamic XPath: " + xpath);
        return xpath;
    }
    
    /**
     * Create dynamic CSS selector with parameter substitution
     * @param cssTemplate CSS template with {0}, {1}, etc. placeholders
     * @param values values to substitute
     * @return final CSS selector
     */
    public static String getDynamicCSS(String cssTemplate, String... values) {
        String css = cssTemplate;
        
        for (int i = 0; i < values.length; i++) {
            css = css.replace("{" + i + "}", values[i]);
        }
        
        System.out.println("Dynamic CSS: " + css);
        return css;
    }
    
    /**
     * Find element by dynamic XPath
     * @param driver WebDriver instance
     * @param xpathTemplate XPath template
     * @param values parameter values
     * @return WebElement
     */
    public static WebElement findByDynamicXPath(WebDriver driver, String xpathTemplate, String... values) {
        try {
            String xpath = getDynamicXPath(xpathTemplate, values);
            return driver.findElement(By.xpath(xpath));
        } catch (NoSuchElementException e) {
            System.err.println("Element not found with dynamic XPath");
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Find element by dynamic CSS
     * @param driver WebDriver instance
     * @param cssTemplate CSS template
     * @param values parameter values
     * @return WebElement
     */
    public static WebElement findByDynamicCSS(WebDriver driver, String cssTemplate, String... values) {
        try {
            String css = getDynamicCSS(cssTemplate, values);
            return driver.findElement(By.cssSelector(css));
        } catch (NoSuchElementException e) {
            System.err.println("Element not found with dynamic CSS");
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Click element using dynamic XPath
     * @param driver WebDriver instance
     * @param xpathTemplate XPath template
     * @param values parameter values
     */
    public static void clickByDynamicXPath(WebDriver driver, String xpathTemplate, String... values) {
        try {
            WebElement element = findByDynamicXPath(driver, xpathTemplate, values);
            if (element != null) {
                element.click();
                System.out.println("Clicked element using dynamic XPath");
            }
        } catch (Exception e) {
            System.err.println("Error clicking dynamic XPath element");
            e.printStackTrace();
        }
    }
    
    /**
     * Type text using dynamic XPath
     * @param driver WebDriver instance
     * @param xpathTemplate XPath template
     * @param text text to type
     * @param values parameter values
     */
    public static void typeByDynamicXPath(WebDriver driver, String xpathTemplate, String text, String... values) {
        try {
            WebElement element = findByDynamicXPath(driver, xpathTemplate, values);
            if (element != null) {
                element.clear();
                element.sendKeys(text);
                System.out.println("Typed text using dynamic XPath");
            }
        } catch (Exception e) {
            System.err.println("Error typing with dynamic XPath");
            e.printStackTrace();
        }
    }
    
    /**
     * Common dynamic XPath patterns
     */
    
    /**
     * XPath to find button by text
     * @param buttonText button text
     * @return XPath string
     */
    public static String getButtonByText(String buttonText) {
        return "//button[text()='" + buttonText + "']";
    }
    
    /**
     * XPath to find button by partial text
     * @param partialText partial button text
     * @return XPath string
     */
    public static String getButtonByPartialText(String partialText) {
        return "//button[contains(text(), '" + partialText + "')]";
    }
    
    /**
     * XPath to find link by text
     * @param linkText link text
     * @return XPath string
     */
    public static String getLinkByText(String linkText) {
        return "//a[text()='" + linkText + "']";
    }
    
    /**
     * XPath to find link by partial text
     * @param partialText partial link text
     * @return XPath string
     */
    public static String getLinkByPartialText(String partialText) {
        return "//a[contains(text(), '" + partialText + "')]";
    }
    
    /**
     * XPath to find element by class name
     * @param elementTag tag name (e.g., "div", "span")
     * @param className class name
     * @return XPath string
     */
    public static String getElementByClass(String elementTag, String className) {
        return "//" + elementTag + "[contains(@class, '" + className + "')]";
    }
    
    /**
     * XPath to find element by text contains
     * @param elementTag tag name
     * @param textContains text to search
     * @return XPath string
     */
    public static String getElementByTextContains(String elementTag, String textContains) {
        return "//" + elementTag + "[contains(text(), '" + textContains + "')]";
    }
    
    /**
     * XPath to find element by attribute value
     * @param elementTag tag name
     * @param attribute attribute name
     * @param value attribute value
     * @return XPath string
     */
    public static String getElementByAttribute(String elementTag, String attribute, String value) {
        return "//" + elementTag + "[@" + attribute + "='" + value + "']";
    }
    
    /**
     * XPath to find element by partial attribute value
     * @param elementTag tag name
     * @param attribute attribute name
     * @param partialValue partial attribute value
     * @return XPath string
     */
    public static String getElementByPartialAttribute(String elementTag, String attribute, String partialValue) {
        return "//" + elementTag + "[contains(@" + attribute + ", '" + partialValue + "')]";
    }
    
    /**
     * XPath for table cell by row and column text
     * @param rowText text in row header
     * @param columnText text in column header
     * @return XPath string
     */
    public static String getTableCellByRowColumn(String rowText, String columnText) {
        return "//tr[contains(., '" + rowText + "')]//td[contains(., '" + columnText + "')]";
    }
    
    /**
     * XPath to find element with multiple attributes
     * @param elementTag tag name
     * @param attributes attribute pairs (attr1, value1, attr2, value2, ...)
     * @return XPath string
     */
    public static String getElementByMultipleAttributes(String elementTag, String... attributes) {
        StringBuilder xpath = new StringBuilder("//" + elementTag);
        
        for (int i = 0; i < attributes.length; i += 2) {
            if (i + 1 < attributes.length) {
                xpath.append("[@").append(attributes[i]).append("='")
                     .append(attributes[i + 1]).append("']");
            }
        }
        
        return xpath.toString();
    }
    
    /**
     * Escape special characters in XPath text
     * @param text text to escape
     * @return escaped text safe for XPath
     */
    public static String escapeXPathText(String text) {
        if (!text.contains("'")) {
            return "'" + text + "'";
        } else if (!text.contains("\"")) {
            return "\"" + text + "\"";
        } else {
            // Handle both quotes with concat
            return "concat('" + text.replace("'", "', \"'\", '") + "')";
        }
    }
    
    // ======================== ADDITIONAL XPATH HELPER METHODS ========================
    
    /**
     * XPath to find element by ID
     * Absolute XPath: Uses By ID, most reliable
     * @param elementId the id attribute value
     * @return XPath string
     * Example: //*[@id='formLogin']
     */
    public static String getElementById(String elementId) {
        return "//*[@id='" + elementId + "']";
    }
    
    /**
     * XPath to find parent element by child element
     * Axis: parent - selects direct parent
     * @param childLocator XPath of child element
     * @return XPath of parent element
     * Example: (//input[@name='email'])/parent::form
     */
    public static String getParentOfElement(String childLocator) {
        return "(" + childLocator + ")/parent::*";
    }
    
    /**
     * XPath to find sibling element using following-sibling axis
     * @param elementLocator current element XPath
     * @param siblingTag tag name of sibling element
     * @return XPath string
     * Example: (//label[text()='Email'])/following-sibling::input[1]
     */
    public static String getFollowingSibling(String elementLocator, String siblingTag) {
        return "(" + elementLocator + ")/following-sibling::" + siblingTag + "[1]";
    }
    
    /**
     * XPath to find sibling element using preceding-sibling axis
     * @param elementLocator current element XPath
     * @param siblingTag tag name of sibling element
     * @return XPath string
     * Example: (//input[@id='submit'])/preceding-sibling::input[1]
     */
    public static String getPrecedingSibling(String elementLocator, String siblingTag) {
        return "(" + elementLocator + ")/preceding-sibling::" + siblingTag + "[1]";
    }
    
    /**
     * XPath with AND condition (both conditions must be true)
     * @param elementTag tag name
     * @param condition1 first condition (e.g., "@type='submit'")
     * @param condition2 second condition (e.g., "@id='btn1'")
     * @return XPath string
     * Example: //button[@type='submit' and @class='primary']
     */
    public static String getElementByAndCondition(String elementTag, String condition1, String condition2) {
        return "//" + elementTag + "[" + condition1 + " and " + condition2 + "]";
    }
    
    /**
     * XPath with OR condition (at least one condition must be true)
     * @param elementTag tag name
     * @param condition1 first condition (e.g., "@id='btn1'")
     * @param condition2 second condition (e.g., "@id='btn2'")
     * @return XPath string
     * Example: //*[@id='login' or @id='signin']
     */
    public static String getElementByOrCondition(String elementTag, String condition1, String condition2) {
        return "//" + elementTag + "[" + condition1 + " or " + condition2 + "]";
    }
    
    /**
     * XPath with NOT condition (negation)
     * @param elementTag tag name
     * @param condition condition to negate (e.g., "@type='hidden'")
     * @return XPath string
     * Example: //input[not(@type='hidden')]
     */
    public static String getElementByNotCondition(String elementTag, String condition) {
        return "//" + elementTag + "[not(" + condition + ")]";
    }
    
    /**
     * XPath using starts-with() function
     * Useful for attributes that start with known prefix
     * @param elementTag tag name
     * @param attribute attribute name
     * @param startsWith value the attribute starts with
     * @return XPath string
     * Example: //input[starts-with(@name, 'txt_')]
     */
    public static String getElementByStartsWith(String elementTag, String attribute, String startsWith) {
        return "//" + elementTag + "[starts-with(@" + attribute + ", '" + startsWith + "')]";
    }
    
    /**
     * XPath to select element at specific position
     * @param elementTag tag name
     * @param position position number (1-based)
     * @return XPath string
     * Example: //button[3] - selects 3rd button
     */
    public static String getElementByPosition(String elementTag, int position) {
        return "//(" + elementTag + ")[" + position + "]";
    }
    
    /**
     * XPath to select last element
     * @param elementTag tag name
     * @return XPath string
     * Example: //button[last()] - selects last button
     */
    public static String getLastElement(String elementTag) {
        return "//(" + elementTag + ")[last()]";
    }
    
    /**
     * XPath with text normalization (removes extra whitespace)
     * Useful when element text has irregular spacing
     * @param elementTag tag name
     * @param text text to match
     * @return XPath string
     * Example: //button[normalize-space()='Click Me']
     */
    public static String getElementByNormalizedText(String elementTag, String text) {
        return "//" + elementTag + "[normalize-space()='" + text + "']";
    }
}
