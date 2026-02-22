package com.seleniumjava.tests;

import com.seleniumjava.base.BaseTest;
import com.seleniumjava.utils.ScreenshotUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Demonstration of Screenshot Capture
 * 
 * Screenshots can be taken:
 * 1. Manually during test execution
 * 2. Automatically on test failure (using TestListener)
 * 3. For specific elements only
 * 4. In different formats (file, bytes, base64)
 * 
 * Run with: mvn test -Dtest=ScreenshotDemoTest
 */
@Listeners(com.seleniumjava.listeners.TestListener.class)
public class ScreenshotDemoTest extends BaseTest {

    @Test(description = "Demonstrate Manual Screenshot Capture")
    public void testManualScreenshot() {
        System.out.println("\n=== Manual Screenshot Test ===");
        
        driver.get("https://www.google.com");
        
        // Take screenshot manually
        String screenshotPath = ScreenshotUtils.takeScreenshot(driver, "GoogleHomePage");
        Assert.assertNotNull(screenshotPath, "Screenshot should be captured");
        System.out.println("✓ Screenshot saved at: " + screenshotPath);
        
        // Search for something
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium WebDriver");
        
        // Take another screenshot
        String searchScreenshot = ScreenshotUtils.takeScreenshot(driver, "AfterTypingSearch");
        Assert.assertNotNull(searchScreenshot, "Search screenshot should be captured");
        System.out.println("✓ Search screenshot saved at: " + searchScreenshot);
    }

    @Test(description = "Demonstrate Element Screenshot")
    public void testElementScreenshot() {
        System.out.println("\n=== Element Screenshot Test ===");
        
        driver.get("https://www.google.com");
        
        // Find search box element
        WebElement searchBox = driver.findElement(By.name("q"));
        Assert.assertNotNull(searchBox, "Search box should be found");
        
        // Take screenshot of specific element only
        String elementScreenshot = ScreenshotUtils.takeElementScreenshot(searchBox, "SearchBoxElement");
        Assert.assertNotNull(elementScreenshot, "Element screenshot should be captured");
        System.out.println("✓ Element screenshot saved at: " + elementScreenshot);
    }

    @Test(description = "Demonstrate Custom Directory Screenshot")
    public void testCustomDirectoryScreenshot() {
        System.out.println("\n=== Custom Directory Screenshot Test ===");
        
        driver.get("https://www.google.com");
        
        // Take screenshot in custom directory
        String customDir = "target/screenshots/custom";
        String screenshotPath = ScreenshotUtils.takeScreenshot(driver, "CustomLocation", customDir);
        Assert.assertNotNull(screenshotPath, "Screenshot should be saved in custom directory");
        System.out.println("✓ Screenshot saved in custom directory: " + screenshotPath);
    }

    @Test(description = "Demonstrate Screenshot Formats")
    public void testScreenshotFormats() {
        System.out.println("\n=== Screenshot Formats Test ===");
        
        driver.get("https://www.google.com");
        
        // Get screenshot as bytes (for TestNG reports)
        byte[] screenshotBytes = ScreenshotUtils.takeScreenshotAsBytes(driver);
        Assert.assertNotNull(screenshotBytes, "Screenshot bytes should not be null");
        Assert.assertTrue(screenshotBytes.length > 0, "Screenshot bytes should have content");
        System.out.println("✓ Screenshot captured as bytes: " + screenshotBytes.length + " bytes");
        
        // Get screenshot as Base64 string (for HTML reports)
        String base64Screenshot = ScreenshotUtils.takeScreenshotAsBase64(driver);
        Assert.assertNotNull(base64Screenshot, "Base64 screenshot should not be null");
        Assert.assertTrue(base64Screenshot.length() > 0, "Base64 screenshot should have content");
        System.out.println("✓ Screenshot captured as Base64: " + base64Screenshot.length() + " characters");
    }

    @Test(description = "Demonstrate Screenshot Utilities")
    public void testScreenshotUtilities() {
        System.out.println("\n=== Screenshot Utilities Test ===");
        
        // Get screenshot directory
        String directory = ScreenshotUtils.getScreenshotDirectory();
        Assert.assertNotNull(directory, "Screenshot directory should be configured");
        System.out.println("✓ Screenshot directory: " + directory);
        
        // Get screenshot count
        int count = ScreenshotUtils.getScreenshotCount();
        System.out.println("✓ Screenshots in directory: " + count);
        
        // Take some screenshots
        driver.get("https://www.google.com");
        ScreenshotUtils.takeScreenshot(driver, "Test1");
        ScreenshotUtils.takeScreenshot(driver, "Test2");
        
        int newCount = ScreenshotUtils.getScreenshotCount();
        Assert.assertTrue(newCount >= count + 2, "Screenshot count should increase");
        System.out.println("✓ New screenshot count: " + newCount);
    }

    @Test(description = "This test will FAIL to demonstrate automatic screenshot", enabled = false)
    public void testAutomaticScreenshotOnFailure() {
        System.out.println("\n=== Automatic Screenshot on Failure Test ===");
        System.out.println("This test is disabled by default - enables it to see auto screenshot");
        System.out.println("When enabled, TestListener will automatically capture screenshot on failure");
        
        driver.get("https://www.google.com");
        
        // This will fail intentionally
        Assert.fail("Intentional failure to trigger automatic screenshot");
    }

    @Test(description = "Demonstrate Screenshot Best Practices", enabled = false)
    public void testScreenshotBestPractices() {
        System.out.println("\n=== Screenshot Best Practices ===");
        System.out.println("This test is disabled by default (informational only)");
        System.out.println("\n1. Automatic Screenshots:");
        System.out.println("   - Use TestListener for auto-capture on failures");
        System.out.println("   - No manual calls needed in test code");
        System.out.println("   - Keeps tests clean and maintainable");
        System.out.println("\n2. Manual Screenshots:");
        System.out.println("   - Use for documenting test steps");
        System.out.println("   - Capture important UI states");
        System.out.println("   - Use meaningful names");
        System.out.println("\n3. Element Screenshots:");
        System.out.println("   - Smaller file size");
        System.out.println("   - Focus on specific component");
        System.out.println("   - Good for detailed analysis");
        System.out.println("\n4. Screenshot Organization:");
        System.out.println("   - Default: target/screenshots/");
        System.out.println("   - Timestamped filenames");
        System.out.println("   - Cleanup old screenshots periodically");
        System.out.println("\n5. Different Formats:");
        System.out.println("   - File: For local analysis");
        System.out.println("   - Bytes: For TestNG reports");
        System.out.println("   - Base64: For HTML reports");
    }
}
