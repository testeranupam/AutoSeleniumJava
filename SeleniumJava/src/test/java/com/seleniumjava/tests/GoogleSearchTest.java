package com.seleniumjava.tests;

import com.seleniumjava.base.BaseTest;
import com.seleniumjava.pages.GoogleSearchPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GoogleSearchTest - Sample test class for Google Search
 * Demonstrates TestNG annotations and test execution
 * 
 * ⚠️ NOTE: Tests are DISABLED by default because Google often requires CAPTCHA verification
 * for automated browser access, which blocks automated testing.
 * 
 * To enable these tests:
 * 1. Remove 'enabled = false' from @Test annotations
 * 2. Consider using a test website instead (e.g., https://duckduckgo.com)
 * 3. Or use Google Search Console API for legitimate automated testing
 */
public class GoogleSearchTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(GoogleSearchTest.class);
    private GoogleSearchPage googleSearchPage;

    @BeforeMethod
    public void setUp() {
        logger.info("================ Starting Test Setup ================");
        setup("chrome");
        googleSearchPage = new GoogleSearchPage(driver);
        googleSearchPage.navigateToGoogle();
        logger.info("================ Test Setup Complete ================");
    }

    @AfterMethod
    public void tearDown() {
        logger.info("================ Starting Test Teardown ================");
        super.tearDown();
        logger.info("================ Test Teardown Complete ================");
    }

    /**
     * Test Case 1: Verify Google Search basic functionality
     * DISABLED: Google requires CAPTCHA verification for automated browsers
     */
    @Test(description = "Verify Google search functionality", enabled = false)
    public void testGoogleSearchFunctionality() {
        logger.info("TEST: testGoogleSearchFunctionality");
        
        // Perform search
        googleSearchPage.searchFor("Selenium WebDriver");
        
        // Wait for results to load
        com.seleniumjava.utils.CommonUtils.waitForPageLoad(driver);
        
        // Verify results are displayed
        boolean resultsPresent = googleSearchPage.areSearchResultsPresent();
        Assert.assertTrue(resultsPresent, "Search results should be displayed");
        
        logger.info("TEST PASSED: Google search returned results");
    }

    /**
     * Test Case 2: Verify search results count
     * DISABLED: Google requires CAPTCHA verification for automated browsers
     */
    @Test(description = "Verify search results count is greater than zero", enabled = false)
    public void testSearchResultsCount() {
        logger.info("TEST: testSearchResultsCount");
        
        // Perform search
        googleSearchPage.searchFor("Java Selenium");
        
        // Wait for results
        com.seleniumjava.utils.CommonUtils.waitForPageLoad(driver);
        
        // Get results count
        int resultsCount = googleSearchPage.getSearchResultsCount();
        
        // Verify count is greater than 0
        Assert.assertTrue(resultsCount > 0, "At least one search result should be present");
        
        logger.info("TEST PASSED: Found " + resultsCount + " search results");
    }

    /**
     * Test Case 3: Verify search result title contains search keyword
     * DISABLED: Google requires CAPTCHA verification for automated browsers
     */
    @Test(description = "Verify search page title is updated after search", enabled = false)
    public void testSearchPageTitle() {
        logger.info("TEST: testSearchPageTitle");
        
        String searchKeyword = "TestNG Framework";
        
        // Perform search
        googleSearchPage.searchFor(searchKeyword);
        
        // Wait for page load
        com.seleniumjava.utils.CommonUtils.waitForPageLoad(driver);
        
        // Get page title
        String pageTitle = googleSearchPage.getSearchPageTitle();
        
        // Verify title contains search keyword
        Assert.assertTrue(pageTitle.contains(searchKeyword), 
                "Page title should contain search keyword");
        
        logger.info("TEST PASSED: Page title contains search keyword: " + pageTitle);
    }
}
