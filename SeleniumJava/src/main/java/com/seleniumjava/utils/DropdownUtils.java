package com.seleniumjava.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Dropdown/Select Utility Class
 * Handles all dropdown operations (select elements)
 */
public class DropdownUtils {
    
    /**
     * Select by visible text
     * @param element WebElement (select element)
     * @param visibleText text to select
     */
    public static void selectByVisibleText(WebElement element, String visibleText) {
        try {
            Select select = new Select(element);
            select.selectByVisibleText(visibleText);
            System.out.println("Selected: " + visibleText);
        } catch (Exception e) {
            System.err.println("Error selecting option: " + visibleText);
            e.printStackTrace();
        }
    }
    
    /**
     * Select by value attribute
     * @param element WebElement (select element)
     * @param value value attribute to select
     */
    public static void selectByValue(WebElement element, String value) {
        try {
            Select select = new Select(element);
            select.selectByValue(value);
            System.out.println("Selected value: " + value);
        } catch (Exception e) {
            System.err.println("Error selecting by value: " + value);
            e.printStackTrace();
        }
    }
    
    /**
     * Select by index
     * @param element WebElement (select element)
     * @param index index position
     */
    public static void selectByIndex(WebElement element, int index) {
        try {
            Select select = new Select(element);
            select.selectByIndex(index);
            System.out.println("Selected index: " + index);
        } catch (Exception e) {
            System.err.println("Error selecting by index: " + index);
            e.printStackTrace();
        }
    }
    
    /**
     * Get currently selected option text
     * @param element WebElement (select element)
     * @return currently selected text
     */
    public static String getSelectedOptionText(WebElement element) {
        try {
            Select select = new Select(element);
            return select.getFirstSelectedOption().getText();
        } catch (Exception e) {
            System.err.println("Error getting selected option");
            e.printStackTrace();
            return "";
        }
    }
    
    /**
     * Get all available options in dropdown
     * @param element WebElement (select element)
     * @return List of visible texts
     */
    public static List<String> getAllOptions(WebElement element) {
        try {
            Select select = new Select(element);
            return select.getOptions()
                    .stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error getting dropdown options");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    /**
     * Get all option values
     * @param element WebElement (select element)
     * @return List of option values
     */
    public static List<String> getAllOptionValues(WebElement element) {
        try {
            Select select = new Select(element);
            return select.getOptions()
                    .stream()
                    .map(opt -> opt.getAttribute("value"))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error getting dropdown option values");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    /**
     * Deselect all options (multiselect)
     * @param element WebElement (select element)
     */
    public static void deselectAll(WebElement element) {
        try {
            Select select = new Select(element);
            select.deselectAll();
            System.out.println("All options deselected");
        } catch (Exception e) {
            System.err.println("Error deselecting all options");
            e.printStackTrace();
        }
    }
    
    /**
     * Deselect by visible text (multiselect)
     * @param element WebElement (select element)
     * @param visibleText text to deselect
     */
    public static void deselectByVisibleText(WebElement element, String visibleText) {
        try {
            Select select = new Select(element);
            select.deselectByVisibleText(visibleText);
            System.out.println("Deselected: " + visibleText);
        } catch (Exception e) {
            System.err.println("Error deselecting option: " + visibleText);
            e.printStackTrace();
        }
    }
    
    /**
     * Check if dropdown supports multiselect
     * @param element WebElement (select element)
     * @return true if multiselect
     */
    public static boolean isMultiSelect(WebElement element) {
        try {
            Select select = new Select(element);
            return select.isMultiple();
        } catch (Exception e) {
            System.err.println("Error checking multiselect");
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Handle custom dropdown (not SELECT tag) with WebDriver
     * Click dropdown, find and click option
     * @param driver WebDriver instance
     * @param dropdownLocator locator of dropdown element
     * @param optionLocator locator of option elements (must find by partial text)
     * @param optionText option text to click
     */
    public static void selectCustomDropdown(WebDriver driver, By dropdownLocator, 
                                           By optionLocator, String optionText) {
        try {
            WebElement dropdown = driver.findElement(dropdownLocator);
            dropdown.click();
            
            Thread.sleep(500); // Wait for options to appear
            
            List<WebElement> options = driver.findElements(optionLocator);
            for (WebElement option : options) {
                if (option.getText().trim().equals(optionText)) {
                    option.click();
                    System.out.println("Custom dropdown option selected: " + optionText);
                    return;
                }
            }
            
            System.err.println("Option not found: " + optionText);
        } catch (Exception e) {
            System.err.println("Error selecting custom dropdown option: " + optionText);
            e.printStackTrace();
        }
    }
    
    /**
     * Get count of options in dropdown
     * @param element WebElement (select element)
     * @return number of options
     */
    public static int getOptionCount(WebElement element) {
        try {
            Select select = new Select(element);
            return select.getOptions().size();
        } catch (Exception e) {
            System.err.println("Error getting option count");
            e.printStackTrace();
            return 0;
        }
    }
}
