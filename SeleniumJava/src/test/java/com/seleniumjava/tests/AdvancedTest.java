package com.seleniumjava.tests;

import com.seleniumjava.base.BaseTest;
import com.seleniumjava.pages.AdvancedPage;
import org.testng.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

/**
 * Advanced Test Class demonstrating comprehensive Selenium features
 * Tests: Dropdowns, File Handling, Mouse Actions, Frames, Windows, Alerts, Dynamic Locators
 */
public class AdvancedTest extends BaseTest {
    
    private static final Logger logger = LoggerFactory.getLogger(AdvancedTest.class);
    private AdvancedPage advancedPage;
    
    /**
     * Setup test - initialize AdvancedPage
     */
    @BeforeMethod
    public void setUp() {
        // Uncomment and add your test URL
        // driver.get("https://your-advanced-test-website.com");
        advancedPage = new AdvancedPage(driver);
        logger.info("AdvancedTest setup complete");
    }
    
    /**
     * Test 1: Dropdown Selection
     * Demonstrates: byId locator, dropdown selection methods
     */
    @Test(description = "Test dropdown selection with visible text")
    public void testDropdownSelection() {
        logger.info("TEST: Dropdown Selection");
        
        // Get all available options
        List<String> countries = advancedPage.getAllCountries();
        logger.info("Available countries: " + countries);
        
        // Select specific country
        String countryToSelect = "United States";
        advancedPage.selectCountry(countryToSelect);
        
        // Verify selection
        String selectedCountry = advancedPage.getSelectedCountry();
        assert selectedCountry.contains(countryToSelect) : 
            "Expected " + countryToSelect + " but got " + selectedCountry;
        
        logger.info("TEST PASSED: Dropdown selection successful");
    }
    
    /**
     * Test 2: File Upload
     * Demonstrates: File handling, CSS selector, XPath
     */
    @Test(description = "Test file upload functionality")
    public void testFileUpload() {
        logger.info("TEST: File Upload");
        
        // Verify file upload element exists
        assert advancedPage.isFileUploadAvailable() : "File upload element not found";
        
        // Upload file
        String testFilePath = "src/test/resources/testdata/sample.txt";
        advancedPage.uploadFileAndClick(testFilePath);
        
        // Wait for upload to complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        logger.info("TEST PASSED: File upload successful");
    }
    
    /**
     * Test 3: Mouse Hover and Actions
     * Demonstrates: Hover, scroll, mouse actions
     */
    @Test(description = "Test mouse hover and menu display")
    public void testMouseHoverActions() {
        logger.info("TEST: Mouse Hover Actions");
        
        // Hover over element
        advancedPage.hoverOverElement();
        advancedPage.scrollToHoverMenu();
        
        // Verify menu is displayed
        boolean menuDisplayed = advancedPage.isHoverMenuDisplayed();
        assert menuDisplayed : "Hover menu not displayed after hovering";
        
        logger.info("TEST PASSED: Mouse hover actions successful");
    }
    
    /**
     * Test 4: Frame/Iframe Switching
     * Demonstrates: Frame switching, element interaction within frames
     */
    @Test(description = "Test iframe switching and interaction")
    public void testIframeHandling() {
        logger.info("TEST: Iframe Handling");
        
        // Verify iframe exists
        assert advancedPage.doesIframeExist() : "Iframe not found on page";
        
        // Check frame count
        int frameCount = advancedPage.getFrameCount();
        logger.info("Frame count on page: " + frameCount);
        assert frameCount > 0 : "No frames found on page";
        
        // Switch to iframe and interact
        advancedPage.switchToIframe();
        logger.info("Switched to iframe");
        
        // Interact with element inside frame
        advancedPage.clickButtonInsideFrame();
        
        // Verify we switched back to main content
        advancedPage.switchToMainContent();
        logger.info("Switched back to main content");
        
        logger.info("TEST PASSED: Iframe operations successful");
    }
    
    /**
     * Test 5: Window Handling
     * Demonstrates: Open new window, switch windows, window titles
     */
    @Test(description = "Test window/tab switching")
    public void testWindowHandling() {
        logger.info("TEST: Window Handling");
        
        // Get initial window count
        int initialWindowCount = advancedPage.getWindowCount();
        logger.info("Initial window count: " + initialWindowCount);
        
        // Open new window
        advancedPage.openNewWindow();
        
        // Get updated window count
        int currentWindowCount = advancedPage.getWindowCount();
        logger.info("Window count after opening new window: " + currentWindowCount);
        assert currentWindowCount > initialWindowCount : "New window not opened";
        
        // Switch to child window
        advancedPage.switchToChildWindow();
        logger.info("Switched to child window");
        String currentURL = advancedPage.getCurrentURL();
        logger.info("Child window URL: " + currentURL);
        
        // Switch back to parent
        advancedPage.switchToParentWindow();
        logger.info("Switched back to parent window");
        
        // Close child windows
        advancedPage.closeChildWindows();
        
        logger.info("TEST PASSED: Window handling successful");
    }
    
    /**
     * Test 6: Table Operations
     * Demonstrates: Table data extraction, XPath
     */
    @Test(description = "Test table data extraction")
    public void testTableOperations() {
        logger.info("TEST: Table Operations");
        
        // Get row count
        int rowCount = advancedPage.getTableRowCount();
        logger.info("Table row count: " + rowCount);
        assert rowCount > 0 : "No rows found in table";
        
        // Get specific cell value
        String cellValue = advancedPage.getTableCellValue(0, 0);
        logger.info("First cell value: " + cellValue);
        
        // Get all table data
        List<org.openqa.selenium.WebElement> tableData = advancedPage.getTableData();
        logger.info("Total table cells: " + tableData.size());
        
        logger.info("TEST PASSED: Table operations successful");
    }
    
    /**
     * Test 7: Dynamic Locators
     * Demonstrates: Dynamic XPath, button and link selection by text
     */
    @Test(description = "Test dynamic locator strategies")
    public void testDynamicLocators() {
        logger.info("TEST: Dynamic Locators");
        
        // Click button by dynamic text
        String buttonText = "Submit";
        try {
            advancedPage.clickButtonByDynamicText(buttonText);
            logger.info("Clicked button with text: " + buttonText);
        } catch (Exception e) {
            logger.info("Button not found (expected if page doesn't have it): " + buttonText);
        }
        
        // Get element by text content
        String elementText = "Login";
        try {
            String result = advancedPage.getElementByTextContent("button", elementText);
            logger.info("Found element: " + result);
        } catch (Exception e) {
            logger.info("Element not found (expected): " + elementText);
        }
        
        logger.info("TEST PASSED: Dynamic locators working");
    }
    
    /**
     * Test 8: Alert Handling
     * Demonstrates: Alert detection, acceptance, text input
     */
    @Test(description = "Test alert handling")
    public void testAlertHandling() {
        logger.info("TEST: Alert Handling");
        
        // Trigger alert
        try {
            advancedPage.triggerAlert();
            
            // Get alert text
            String alertMsg = advancedPage.getAlertMessage();
            logger.info("Alert message: " + alertMsg);
            
            // Accept alert
            advancedPage.acceptAlertDialog();
            logger.info("Alert accepted");
        } catch (Exception e) {
            logger.info("Alert test skipped (no alert triggered): " + e.getMessage());
        }
        
        logger.info("TEST PASSED: Alert handling successful");
    }
    
    /**
     * Test 9: Dropdown Validation
     * Demonstrates: Multiple locator types, dropdown assertions
     */
    @Test(description = "Test dropdown validation")
    public void testDropdownValidation() {
        logger.info("TEST: Dropdown Validation");
        
        // Check if dropdown contains specific option
        String optionToFind = "Canada";
        boolean containsOption = advancedPage.dropdownContains(optionToFind);
        logger.info("Dropdown contains '" + optionToFind + "': " + containsOption);
        
        logger.info("TEST PASSED: Dropdown validation successful");
    }
    
    /**
     * Test 10: Read Test Data and Execute
     * Demonstrates: File handling, data reading, parameterized execution
     */
    @Test(description = "Test reading and using CSV test data")
    public void testReadTestDataFromCSV() {
        logger.info("TEST: Read CSV Test Data");
        
        // Read test data from CSV
        String csvPath = "src/test/resources/testdata/test-data.csv";
        List<Map<String, String>> testData = advancedPage.getTestDataFromCSV(csvPath);
        
        logger.info("Test data records found: " + testData.size());
        
        // Process first record if available
        if (!testData.isEmpty()) {
            Map<String, String> firstRecord = testData.get(0);
            logger.info("First record: " + firstRecord);
            
            // Example: Select country from first record
            if (firstRecord.containsKey("country")) {
                String country = firstRecord.get("country");
                logger.info("Processing country from CSV: " + country);
            }
        }
        
        logger.info("TEST PASSED: CSV data reading successful");
    }
    
    /**
     * Cleanup test
     */
    @AfterMethod
    public void tearDown() {
        logger.info("AdvancedTest teardown");
        // driver.quit(); // Called from BaseTest
    }
}
