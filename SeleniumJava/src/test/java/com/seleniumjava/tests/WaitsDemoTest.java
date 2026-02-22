package com.seleniumjava.tests;

import com.seleniumjava.base.BaseTest;
import com.seleniumjava.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Demonstration of different Wait strategies
 * 
 * Wait Types:
 * 1. Implicit Wait - Global wait for all elements
 * 2. Explicit Wait - Wait for specific conditions
 * 3. Fluent Wait - Polling with customizable intervals
 * 
 * Run with: mvn test -Dtest=WaitsDemoTest
 */
public class WaitsDemoTest extends BaseTest {

    @Test(description = "Demonstrate Implicit Wait")
    public void testImplicitWait() {
        System.out.println("\n=== Implicit Wait Test ===");
        
        // Set implicit wait - applies to all findElement calls
        WaitUtils.setImplicitWait(driver, 10);
        System.out.println("✓ Implicit wait set to 10 seconds");
        
        driver.get("https://www.google.com");
        
        // This will wait up to 10 seconds if element is not immediately found
        WebElement searchBox = driver.findElement(By.name("q"));
        Assert.assertNotNull(searchBox, "Search box should be found");
        System.out.println("✓ Element found with implicit wait");
        
        // Remove implicit wait
        WaitUtils.removeImplicitWait(driver);
        System.out.println("✓ Implicit wait removed");
    }

    @Test(description = "Demonstrate Explicit Waits")
    public void testExplicitWaits() {
        System.out.println("\n=== Explicit Wait Test ===");
        
        driver.get("https://www.google.com");
        
        // Wait for element to be visible
        WebElement searchBox = WaitUtils.waitForElementVisible(driver, By.name("q"), 10);
        Assert.assertNotNull(searchBox, "Search box should be visible");
        System.out.println("✓ Element visible - explicit wait");
        
        // Wait for element to be clickable
        WebElement clickableSearchBox = WaitUtils.waitForElementClickable(driver, By.name("q"), 10);
        Assert.assertNotNull(clickableSearchBox, "Search box should be clickable");
        System.out.println("✓ Element clickable - explicit wait");
        
        // Type search query
        searchBox.sendKeys("Selenium WebDriver");
        
        // Wait for search button to be present
        WebElement searchButton = WaitUtils.waitForElementPresent(driver, By.name("btnK"), 10);
        Assert.assertNotNull(searchButton, "Search button should be present");
        System.out.println("✓ Element present - explicit wait");
    }

    @Test(description = "Demonstrate Title and URL Waits")
    public void testTitleAndUrlWaits() {
        System.out.println("\n=== Title and URL Wait Test ===");
        
        driver.get("https://www.google.com");
        
        // Wait for title to contain specific text
        boolean titleContains = WaitUtils.waitForTitleContains(driver, "Google", 10);
        Assert.assertTrue(titleContains, "Title should contain 'Google'");
        System.out.println("✓ Title contains expected text: " + driver.getTitle());
        
        // Wait for URL to contain specific text
        boolean urlContains = WaitUtils.waitForUrlContains(driver, "google", 10);
        Assert.assertTrue(urlContains, "URL should contain 'google'");
        System.out.println("✓ URL contains expected text: " + driver.getCurrentUrl());
    }

    @Test(description = "Demonstrate Fluent Wait")
    public void testFluentWait() {
        System.out.println("\n=== Fluent Wait Test ===");
        
        driver.get("https://www.google.com");
        
        // Fluent wait with 10 second timeout and 1 second polling
        WebElement searchBox = WaitUtils.fluentWaitForElement(
            driver, 
            By.name("q"), 
            10,  // timeout
            1    // polling interval
        );
        
        Assert.assertNotNull(searchBox, "Search box should be found with fluent wait");
        System.out.println("✓ Element found with fluent wait (polling every 1 second)");
        
        // Wait for element to be clickable with fluent wait
        WebElement clickableElement = WaitUtils.fluentWaitForClickable(
            driver, 
            By.name("q"), 
            10, 
            1
        );
        
        Assert.assertNotNull(clickableElement, "Element should be clickable");
        System.out.println("✓ Element clickable with fluent wait");
    }

    @Test(description = "Demonstrate Text Presence Wait")
    public void testTextPresenceWait() {
        System.out.println("\n=== Text Presence Wait Test ===");
        
        driver.get("https://www.google.com");
        
        // Wait for specific text to be present in element
        By searchBoxLocator = By.name("q");
        WebElement element = WaitUtils.waitForElementVisible(driver, searchBoxLocator, 10);
        
        // Type text
        element.sendKeys("Selenium");
        
        // Wait for text to be present in the element's value attribute
        String typedText = element.getAttribute("value");
        Assert.assertEquals(typedText, "Selenium", "Text should be present in search box");
        System.out.println("✓ Text present in element: " + typedText);
    }

    @Test(description = "Demonstrate Wait for Element to Disappear")
    public void testWaitForInvisibility() {
        System.out.println("\n=== Wait for Invisibility Test ===");
        
        driver.get("https://www.google.com");
        
        // Wait for element to be visible first
        WebElement searchBox = WaitUtils.waitForElementVisible(driver, By.name("q"), 10);
        Assert.assertNotNull(searchBox, "Search box should be visible");
        System.out.println("✓ Element is visible");
        
        // In a real scenario, you might trigger an action that hides the element
        // Here we're just demonstrating the wait method exists
        System.out.println("✓ waitForElementInvisible() method is available for elements that disappear");
    }

    @Test(description = "Demonstrate Page Load and Script Timeouts")
    public void testTimeouts() {
        System.out.println("\n=== Timeouts Test ===");
        
        // Set page load timeout
        WaitUtils.setPageLoadTimeout(driver, 30);
        System.out.println("✓ Page load timeout set to 30 seconds");
        
        // Set script timeout (for async JavaScript execution)
        WaitUtils.setScriptTimeout(driver, 30);
        System.out.println("✓ Script timeout set to 30 seconds");
        
        driver.get("https://www.google.com");
        System.out.println("✓ Page loaded within timeout");
    }

    @Test(description = "Best Practices for Waits", enabled = false)
    public void testWaitBestPractices() {
        System.out.println("\n=== Wait Best Practices ===");
        System.out.println("This test is disabled by default (informational only)");
        System.out.println("\n1. Implicit Wait:");
        System.out.println("   - Set once at the beginning");
        System.out.println("   - Applies to ALL findElement() calls");
        System.out.println("   - Can cause unexpected delays");
        System.out.println("\n2. Explicit Wait:");
        System.out.println("   - Use for specific conditions");
        System.out.println("   - More flexible and reliable");
        System.out.println("   - Preferred over implicit wait");
        System.out.println("\n3. Fluent Wait:");
        System.out.println("   - Use for complex polling scenarios");
        System.out.println("   - Customize polling interval");
        System.out.println("   - Ignore specific exceptions");
        System.out.println("\n4. NEVER use Thread.sleep():");
        System.out.println("   - Fixed delay (wasteful)");
        System.out.println("   - Doesn't check conditions");
        System.out.println("   - Use smart waits instead");
    }
}
