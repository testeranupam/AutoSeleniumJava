package com.seleniumjava.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import com.seleniumjava.utils.WaitUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GoogleSearchPage - Page Object for Google Search page
 * Demonstrates Page Object Model pattern
 * 
 * Uses various locator strategies for element identification.
 * For comprehensive information about locator types and XPath syntax:
 * @see com.seleniumjava.pages.BasePage - Contains complete locator documentation and XPath reference
 */
public class GoogleSearchPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(GoogleSearchPage.class);

    // Locators
    private By searchBox = By.name("q");
    @SuppressWarnings("unused")
    private By searchButton = By.xpath("//input[@name='btnK']");
    @SuppressWarnings("unused")
    private By fortuneButton = By.xpath("//input[@value='I\\'m Feeling Lucky']");
    private By searchResults = By.xpath("//div[@class='g']");
    @SuppressWarnings("unused")
    private By resultLink = By.xpath("//a/h3");

    /**
     * Constructor
     * @param driver - WebDriver instance
     */
    public GoogleSearchPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Search for text in Google search box
     * @param searchText - Text to search
     */
    public void searchFor(String searchText) {
        logger.info("Searching for: " + searchText);
        
        // Wait for search box to be visible
        WaitUtils.waitForElementVisible(driver, searchBox, 10);
        
        // Type search text and press Enter (more reliable than clicking button)
        type(searchBox, searchText);
        findElement(searchBox).sendKeys(Keys.ENTER);
        
        // Wait for search results to load
        WaitUtils.waitForElementVisible(driver, searchResults, 10);
        
        logger.info("Search executed successfully");
    }

    /**
     * Get number of search results
     * @return Number of results
     */
    public int getSearchResultsCount() {
        int count = getElements(searchResults).size();
        logger.info("Found " + count + " search results");
        return count;
    }

    /**
     * Verify search has results
     * @return true if results are present
     */
    public boolean areSearchResultsPresent() {
        try {
            // Wait for results with timeout
            WaitUtils.waitForElementVisible(driver, searchResults, 10);
            boolean isPresent = getElements(searchResults).size() > 0;
            logger.info("Search results present: " + isPresent + " (Found " + getElements(searchResults).size() + " results)");
            return isPresent;
        } catch (Exception e) {
            logger.error("Error checking search results: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get search page title
     * @return Page title
     */
    public String getSearchPageTitle() {
        return getPageTitle();
    }

    /**
     * Navigate to Google home page
     */
    public void navigateToGoogle() {
        driver.navigate().to("https://www.google.com");
        logger.info("Navigated to Google home page");
        
        // Wait for search box to be ready
        WaitUtils.waitForElementVisible(driver, searchBox, 10);
    }
}
