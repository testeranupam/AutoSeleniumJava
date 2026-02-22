package com.seleniumjava.tests;

import com.seleniumjava.base.BaseTest;
import com.seleniumjava.utils.AssertionUtils;
import com.seleniumjava.utils.WaitUtils;
import com.seleniumjava.utils.ScreenshotUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Comprehensive test demonstrating ALL new features together:
 * - Hard and Soft Assertions
 * - Multiple Wait strategies
 * - Screenshot capture
 * - Parallel execution support
 * 
 * This test is designed to run in parallel with other tests.
 * Use testng-parallel-methods.xml to run methods in parallel.
 * 
 * Run with: mvn test -DsuiteXmlFile=testng-parallel-methods.xml
 */
@Listeners(com.seleniumjava.listeners.TestListener.class)
public class ComprehensiveFeatureTest extends BaseTest {

    @Test(description = "Test with Hard Assertions and Explicit Waits", priority = 1)
    public void testHardAssertionsWithWaits() {
        System.out.println("\n[Thread: " + Thread.currentThread().getName() + "] === Test 1: Hard Assertions + Waits ===");
        
        // Navigate
        driver.get("https://www.google.com");
        
        // Wait for element and assert
        WebElement searchBox = WaitUtils.waitForElementVisible(driver, By.name("q"), 10);
        AssertionUtils.assertNotNull(searchBox, "Search box should be visible");
        System.out.println("  ✓ Search box found with explicit wait");
        
        // Wait for clickable and assert
        WebElement clickableBox = WaitUtils.waitForElementClickable(driver, By.name("q"), 10);
        AssertionUtils.assertNotNull(clickableBox, "Search box should be clickable");
        System.out.println("  ✓ Search box is clickable");
        
        // Type and take screenshot
        searchBox.sendKeys("Selenium Parallel Testing");
        ScreenshotUtils.takeScreenshot(driver, "Test1_AfterTyping");
        System.out.println("  ✓ Screenshot captured");
        
        // Assert with title wait
        boolean titleOk = WaitUtils.waitForTitleContains(driver, "Google", 10);
        AssertionUtils.assertTrue(titleOk, "Title should contain Google");
        System.out.println("  ✓ Title verified: " + driver.getTitle());
    }

    @Test(description = "Test with Soft Assertions and Fluent Waits", priority = 2)
    public void testSoftAssertionsWithFluentWaits() {
        System.out.println("\n[Thread: " + Thread.currentThread().getName() + "] === Test 2: Soft Assertions + Fluent Waits ===");
        
        // Initialize soft assertions
        AssertionUtils.initSoftAssert();
        
        driver.get("https://www.google.com");
        
        // Fluent wait for element
        WebElement searchBox = WaitUtils.fluentWaitForElement(driver, By.name("q"), 10, 1);
        AssertionUtils.softAssertNotNull(searchBox, "Search box should be found with fluent wait");
        System.out.println("  ✓ Fluent wait executed");
        
        // Multiple soft assertions
        AssertionUtils.softAssertTrue(driver.getTitle().contains("Google"), "Title should contain Google");
        AssertionUtils.softAssertTrue(driver.getCurrentUrl().contains("google"), "URL should contain google");
        AssertionUtils.softAssertNotNull(searchBox.getAttribute("name"), "Search box should have name attribute");
        System.out.println("  ✓ Multiple soft assertions executed");
        
        // Take screenshot before assertAll
        ScreenshotUtils.takeScreenshot(driver, "Test2_BeforeAssertAll");
        
        // Report all assertions
        AssertionUtils.assertAll();
        System.out.println("  ✓ All soft assertions passed");
    }

    @Test(description = "Test with Implicit Waits and Element Screenshots", priority = 3)
    public void testImplicitWaitsWithElementScreenshots() {
        System.out.println("\n[Thread: " + Thread.currentThread().getName() + "] === Test 3: Implicit Waits + Element Screenshots ===");
        
        // Set implicit wait
        WaitUtils.setImplicitWait(driver, 10);
        System.out.println("  ✓ Implicit wait set");
        
        driver.get("https://www.google.com");
        
        // Find elements (implicit wait applies)
        WebElement searchBox = driver.findElement(By.name("q"));
        AssertionUtils.assertNotNull(searchBox, "Search box should be found");
        
        // Take element screenshot
        String elementPath = ScreenshotUtils.takeElementScreenshot(searchBox, "Test3_SearchBoxElement");
        AssertionUtils.assertNotNull(elementPath, "Element screenshot should be captured");
        System.out.println("  ✓ Element screenshot: " + elementPath);
        
        // Type text
        searchBox.sendKeys("WebDriver Screenshots");
        
        // Take full page screenshot
        String fullPath = ScreenshotUtils.takeScreenshot(driver, "Test3_FullPage");
        AssertionUtils.assertNotNull(fullPath, "Full page screenshot should be captured");
        System.out.println("  ✓ Full page screenshot: " + fullPath);
        
        // Remove implicit wait
        WaitUtils.removeImplicitWait(driver);
        System.out.println("  ✓ Implicit wait removed");
    }

    @Test(description = "Test with Mixed Assertions and Multiple Waits", priority = 4)
    public void testMixedAssertionsAndWaits() {
        System.out.println("\n[Thread: " + Thread.currentThread().getName() + "] === Test 4: Mixed Assertions + Multiple Waits ===");
        
        driver.get("https://www.google.com");
        
        // Explicit wait for visible
        WebElement searchBox = WaitUtils.waitForElementVisible(driver, By.name("q"), 10);
        AssertionUtils.assertNotNull(searchBox, "Search box should be visible");
        
        // Hard assertion for immediate validation
        AssertionUtils.assertTrue(searchBox.isDisplayed(), "Search box must be displayed");
        System.out.println("  ✓ Hard assertion passed");
        
        // Initialize soft assertions for multiple checks
        AssertionUtils.initSoftAssert();
        
        // Multiple soft assertions
        AssertionUtils.softAssertEquals(searchBox.getTagName(), "textarea", "Should be textarea");
        AssertionUtils.softAssertEquals(searchBox.getAttribute("name"), "q", "Name should be 'q'");
        AssertionUtils.softAssertTrue(searchBox.isEnabled(), "Should be enabled");
        
        // Wait for title and assert
        boolean titleContains = WaitUtils.waitForTitleContains(driver, "Google", 5);
        AssertionUtils.softAssertTrue(titleContains, "Title should contain Google");
        
        System.out.println("  ✓ Multiple soft assertions collected");
        
        // Take screenshot before validation
        ScreenshotUtils.takeScreenshot(driver, "Test4_BeforeValidation");
        
        // Validate all soft assertions
        AssertionUtils.assertAll();
        System.out.println("  ✓ All validations passed");
    }

    @Test(description = "Test with Numeric Assertions and Timeouts", priority = 5)
    public void testNumericAssertionsAndTimeouts() {
        System.out.println("\n[Thread: " + Thread.currentThread().getName() + "] === Test 5: Numeric Assertions + Timeouts ===");
        
        // Set timeouts
        WaitUtils.setPageLoadTimeout(driver, 30);
        WaitUtils.setScriptTimeout(driver, 30);
        System.out.println("  ✓ Timeouts configured");
        
        long startTime = System.currentTimeMillis();
        driver.get("https://www.google.com");
        long loadTime = System.currentTimeMillis() - startTime;
        
        System.out.println("  ✓ Page loaded in: " + loadTime + "ms");
        
        // Numeric assertions on load time (using assertTrue for long values)
        AssertionUtils.assertTrue(loadTime > 0, "Load time should be positive");
        AssertionUtils.assertTrue(loadTime < 30000, "Load time should be under 30 seconds");
        System.out.println("  ✓ Load time within acceptable range");
        
        // Wait for element and measure
        long waitStart = System.currentTimeMillis();
        WebElement element = WaitUtils.waitForElementVisible(driver, By.name("q"), 10);
        long waitTime = System.currentTimeMillis() - waitStart;
        
        AssertionUtils.assertNotNull(element, "Element should be found");
        AssertionUtils.assertTrue(waitTime < 10000, "Wait time should be under 10 seconds");
        System.out.println("  ✓ Element found in: " + waitTime + "ms");
        
        // Take final screenshot
        ScreenshotUtils.takeScreenshot(driver, "Test5_Final");
    }

    @Test(description = "Stress test with all features", priority = 6)
    public void testAllFeaturesStress() {
        System.out.println("\n[Thread: " + Thread.currentThread().getName() + "] === Test 6: Stress Test - All Features ===");
        
        // Initialize soft assertions
        AssertionUtils.initSoftAssert();
        
        // Set implicit wait
        WaitUtils.setImplicitWait(driver, 5);
        
        try {
            driver.get("https://www.google.com");
            
            // Screenshot 1 - Home page
            ScreenshotUtils.takeScreenshot(driver, "Test6_Step1_HomePage");
            
            // Find and verify search box with explicit wait
            WebElement searchBox = WaitUtils.waitForElementClickable(driver, By.name("q"), 10);
            AssertionUtils.softAssertNotNull(searchBox, "Search box should exist");
            
            // Type text and screenshot
            searchBox.sendKeys("Selenium Comprehensive Testing");
            ScreenshotUtils.takeScreenshot(driver, "Test6_Step2_AfterTyping");
            
            // Verify text was entered
            String typedText = searchBox.getAttribute("value");
            AssertionUtils.softAssertEquals(typedText, "Selenium Comprehensive Testing", "Text should match");
            
            // Take element screenshot
            ScreenshotUtils.takeElementScreenshot(searchBox, "Test6_Step3_SearchBox");
            
            // Verify page properties
            AssertionUtils.softAssertTrue(driver.getTitle().contains("Google"), "Title check");
            AssertionUtils.softAssertTrue(driver.getCurrentUrl().contains("google"), "URL check");
            
            // Final screenshot
            ScreenshotUtils.takeScreenshot(driver, "Test6_Step4_Final");
            
            System.out.println("  ✓ All operations completed successfully");
            
        } finally {
            // Remove implicit wait
            WaitUtils.removeImplicitWait(driver);
            
            // Validate all soft assertions
            AssertionUtils.assertAll();
        }
    }

    @Test(description = "Info: Parallel Execution Guide", enabled = false)
    public void testParallelExecutionInfo() {
        System.out.println("\n=== Parallel Execution Guide ===");
        System.out.println("Run these tests in parallel using:");
        System.out.println("  mvn test -DsuiteXmlFile=testng-parallel-tests.xml");
        System.out.println("  mvn test -DsuiteXmlFile=testng-parallel-methods.xml");
        System.out.println("  mvn test -DsuiteXmlFile=testng-parallel-classes.xml");
        System.out.println("\nThread Safety:");
        System.out.println("  - Each test gets its own WebDriver instance");
        System.out.println("  - SoftAssert uses ThreadLocal for thread safety");
        System.out.println("  - Screenshots have unique timestamped names");
        System.out.println("  - No shared state between tests");
    }
}
