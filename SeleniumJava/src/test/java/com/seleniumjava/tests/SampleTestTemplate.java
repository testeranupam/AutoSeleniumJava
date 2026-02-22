package com.seleniumjava.tests;

import com.seleniumjava.base.BaseTest;
import com.seleniumjava.pages.BasePage;  // Replace with your page object
import com.seleniumjava.utils.CommonUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Template for creating new test classes
 * 
 * Instructions:
 * 1. Copy this file and rename: YourFeatureTest.java
 * 2. Update class name: public class YourFeatureTest extends BaseTest
 * 3. Import your page object: import com.seleniumjava.pages.YourPageObject;
 * 4. Replace BasePage with your page object class
 * 5. Update @BeforeMethod and test methods
 * 6. Add test methods following the @Test pattern
 * 7. Update testng.xml to include this test class
 * 8. Run: mvn test -Dtest=YourFeatureTest
 */
public class SampleTestTemplate extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(SampleTestTemplate.class);
    
    // Replace this with your page object
    @SuppressWarnings("unused")
    private BasePage samplePage;

    /**
     * Setup method - runs before each test
     * Initialize WebDriver and Page Object
     */
    @BeforeMethod
    public void setUp() {
        logger.info("================ Starting Test Setup ================");
        
        // Initialize browser (default: chrome)
        setup("chrome");
        
        // Initialize your page object
        // Example: samplePage = new YourPageObject(driver);
        samplePage = new BasePage(driver);
        
        // Navigate to application
        navigateTo("https://your-app.com");
        
        logger.info("================ Test Setup Complete ================");
    }

    /**
     * Teardown method - runs after each test
     * Close browser and clean up resources
     */
    @AfterMethod
    public void tearDown() {
        logger.info("================ Starting Test Teardown ================");
        
        // Close WebDriver
        super.tearDown();
        
        logger.info("================ Test Teardown Complete ================");
    }

    /**
     * Test Case 1: Basic test template
     * 
     * Test Steps:
     * 1. Step 1 description
     * 2. Step 2 description
     * 3. Step 3 description
     * 
     * Expected Result:
     * Describe what should happen if test passes
     */
    @Test(description = "Verify basic functionality")
    public void testBasicFunctionality() {
        logger.info("TEST: testBasicFunctionality");
        
        // Step 1: Perform action
        // Example: samplePage.clickButton();
        
        // Step 2: Wait and verify
        CommonUtils.waitForPageLoad(driver);
        
        // Step 3: Assert expected result
        // Example: Assert.assertTrue(samplePage.isMessageDisplayed());
        
        logger.info("TEST PASSED: Functionality working as expected");
    }

    /**
     * Test Case 2: Data-driven test example
     * Uses parameters from TestNG XML
     */
    @Test(description = "Test with parameters")
    @Parameters({"param1", "param2"})
    public void testWithParameters(String param1, String param2) {
        logger.info("TEST: testWithParameters");
        logger.info("Parameter 1: " + param1 + ", Parameter 2: " + param2);
        
        // Use parameters in test
        // Example: samplePage.enterData(param1);
        
        logger.info("TEST PASSED");
    }

    /**
     * Test Case 3: Test with retry on failure
     * Retry if test fails
     */
    @Test(description = "Test with retry")
    // Note: Add retryAnalyzer = RetryAnalyzer.class to enable retry functionality
    public void testWithRetry() {
        logger.info("TEST: testWithRetry");
        
        // Your test code here
        
        logger.info("TEST PASSED");
    }

    /**
     * Test Case 4: Grouped test example
     * Group related tests together
     */
    @Test(groups = {"smoke"}, description = "Smoke test example")
    public void testSmokeTest() {
        logger.info("TEST: testSmokeTest");
        
        // Quick smoke test
        
        logger.info("TEST PASSED");
    }

    /**
     * Test Case 5: Test with expected exception
     * Verify exception is thrown
     */
    @Test(expectedExceptions = IllegalArgumentException.class, 
          description = "Test exception handling")
    public void testExceptionHandling() {
        logger.info("TEST: testExceptionHandling");
        
        // Code that should throw exception
        throw new IllegalArgumentException("Test exception");
    }

    /**
     * Test Case 6: Dependent test
     * Run only if another test passes
     */
    @Test(dependsOnMethods = {"testBasicFunctionality"},
          description = "Dependent test example")
    public void testDependentTest() {
        logger.info("TEST: testDependentTest");
        
        // This runs only if testBasicFunctionality passes
        
        logger.info("TEST PASSED");
    }

    // ==================== Helper Methods ====================

    /**
     * Custom helper method for repeated actions
     */
    @SuppressWarnings("unused")
    private void performCommonAction() {
        logger.info("Performing common action");
        // Your common action code here
    }

    /**
     * Wait for specific condition
     */
    @SuppressWarnings("unused")
    private void waitForCondition() {
        CommonUtils.threadWait(2000);  // Wait 2 seconds
    }

    /**
     * Verify expected result
     */
    @SuppressWarnings("unused")
    private void verifyExpectedBehavior() {
        // Add assertions
        // Example: Assert.assertTrue(condition, "Error message");
    }
}

/*
 * ==================== USAGE INSTRUCTIONS ====================
 * 
 * 1. SETUP:
 *    - Copy this file and create YourFeatureTest.java
 *    - Update class name
 *    - Import your page object
 *    - Update setUp() to initialize your page object
 * 
 * 2. WRITE TESTS:
 *    - Add @Test methods following the template
 *    - Use page object methods for interactions
 *    - Add assertions to verify results
 *    - Add descriptive log messages
 * 
 * 3. ADD TO TESTSUITE:
 *    - Edit testng.xml
 *    - Add <class name="com.seleniumjava.tests.YourFeatureTest"/>
 * 
 * 4. RUN TESTS:
 *    - mvn test (all tests)
 *    - mvn test -Dtest=YourFeatureTest (single class)
 *    - mvn test -Dtest=YourFeatureTest#testMethodName (single method)
 * 
 * 5. COMMON ASSERTIONS:
 *    Assert.assertTrue(condition, "Message")
 *    Assert.assertFalse(condition, "Message")
 *    Assert.assertEquals(actual, expected, "Message")
 *    Assert.assertNotNull(object, "Message")
 *    Assert.assertNull(object, "Message")
 */
