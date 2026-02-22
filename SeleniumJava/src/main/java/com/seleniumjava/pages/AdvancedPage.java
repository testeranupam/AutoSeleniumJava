package com.seleniumjava.pages;

import org.openqa.selenium.*;
import com.seleniumjava.utils.*;

/**
 * Advanced Page Object demonstrating comprehensive Selenium features
 * Includes: Dropdowns, File Handling, Mouse Actions, Frames, Windows, Alerts
 * 
 * Uses dynamic locators and various locator strategies. For comprehensive information about:
 * • All locator types (ID, Name, Class, Tag, CSS, XPath, LinkText, PartialLinkText)
 * • XPath syntax, axes, predicates, and functions
 * • Dynamic locator generation with parameter substitution
 * 
 * @see com.seleniumjava.pages.BasePage - Contains complete locator documentation
 * @see com.seleniumjava.utils.DynamicLocatorUtils - Dynamic locator utility methods
 */
public class AdvancedPage extends BasePage {
    
    // ======================== LOCATORS ========================
    
    // Dropdown elements
    private By countryDropdown = byId("country-dropdown");
    @SuppressWarnings("unused")
    private By dropdownOptions = byXPath("//select[@id='country-dropdown']/option");
    
    // File upload
    private By fileInput = byId("file-upload");
    private By uploadButton = byXPath("//button[text()='Upload']");
    
    // Mouse hover elements
    private By hoverElement = byXPath("//div[@class='hover-menu']");
    private By hoverMenu = byCSS(".menu-dropdown");
    
    // Frame/Iframe
    private By iframeElement = byId("embedded-content");
    private By frameButton = byXPath("//button[text()='Click Inside Frame']");
    
    // Window handling
    private By openNewWindowButton = byXPath("//a[text()='Open New Window']");
    
    // Table elements
    private By tableRows = byXPath("//table[@id='data-table']//tr");
    private By tableCells = byXPath("//table[@id='data-table']//td");
    
    // Alert trigger
    private By alertButton = byXPath("//button[@onclick='alert()']");
    
    /**
     * Constructor
     */
    public AdvancedPage(WebDriver driver) {
        super(driver);
    }
    
    // ======================== DROPDOWN OPERATIONS ========================
    
    /**
     * Select country from dropdown by visible text
     */
    public void selectCountry(String country) {
        WebElement dropdown = findElement(countryDropdown);
        DropdownUtils.selectByVisibleText(dropdown, country);
    }
    
    /**
     * Get currently selected country
     */
    public String getSelectedCountry() {
        WebElement dropdown = findElement(countryDropdown);
        return DropdownUtils.getSelectedOptionText(dropdown);
    }
    
    /**
     * Get all available countries
     */
    public java.util.List<String> getAllCountries() {
        WebElement dropdown = findElement(countryDropdown);
        return DropdownUtils.getAllOptions(dropdown);
    }
    
    // ======================== FILE HANDLING ========================
    
    /**
     * Upload file
     */
    public void uploadFile(String filePath) {
        WebElement fileInputElement = findElement(fileInput);
        fileInputElement.sendKeys(filePath);
    }
    
    /**
     * Upload file and verify
     */
    public void uploadFileAndClick(String filePath) {
        uploadFile(filePath);
        click(uploadButton);
    }
    
    /**
     * Read test data from CSV
     */
    public java.util.List<java.util.Map<String, String>> getTestDataFromCSV(String filePath) {
        return DataReader.readCSV(filePath);
    }
    
    /**
     * Write test results to file
     */
    public void writeResultsToFile(String filePath, String results) {
        FileHandlingUtils.writeToFile(filePath, results, true);
    }
    
    // ======================== MOUSE ACTIONS ========================
    
    /**
     * Hover over element to reveal menu
     */
    public void hoverOverElement() {
        MouseActionsUtils.hoverElement(driver, findElement(hoverElement));
    }
    
    /**
     * Scroll to specific element
     */
    public void scrollToHoverMenu() {
        scrollToElement(hoverMenu);
    }
    
    /**
     * Double-click on element
     */
    public void doubleClickElement(By locator) {
        MouseActionsUtils.doubleClick(driver, findElement(locator));
    }
    
    /**
     * Right-click context menu
     */
    public void rightClickElement(By locator) {
        MouseActionsUtils.rightClick(driver, findElement(locator));
    }
    
    /**
     * Drag and drop element
     */
    public void dragAndDropElements(By source, By target) {
        WebElement sourceElement = findElement(source);
        WebElement targetElement = findElement(target);
        MouseActionsUtils.dragAndDrop(driver, sourceElement, targetElement);
    }
    
    // ======================== FRAME/IFRAME OPERATIONS ========================
    
    /**
     * Switch to iframe
     */
    public void switchToIframe() {
        FrameSwitchingUtils.switchToFrameByLocator(driver, iframeElement);
    }
    
    /**
     * Click button inside frame
     */
    public void clickButtonInsideFrame() {
        switchToIframe();
        click(frameButton);
        FrameSwitchingUtils.switchToDefaultContent(driver);
    }
    
    /**
     * Switch back to main content
     */
    public void switchToMainContent() {
        FrameSwitchingUtils.switchToDefaultContent(driver);
    }
    
    /**
     * Get frame count
     */
    public int getFrameCount() {
        return FrameSwitchingUtils.getFrameCount(driver);
    }
    
    // ======================== WINDOW HANDLING ========================
    
    /**
     * Open new window
     */
    public void openNewWindow() {
        click(openNewWindowButton);
    }
    
    /**
     * Get window count
     */
    public int getWindowCount() {
        return WindowHandlingUtils.getWindowCount(driver);
    }
    
    /**
     * Switch to child window
     */
    public void switchToChildWindow() {
        WindowHandlingUtils.switchToChildWindow(driver);
    }
    
    /**
     * Switch to parent window
     */
    public void switchToParentWindow() {
        WindowHandlingUtils.switchToParentWindow(driver);
    }
    
    /**
     * Switch to window by title
     */
    public boolean switchToWindowByTitle(String title) {
        return WindowHandlingUtils.switchToWindowByTitle(driver, title);
    }
    
    /**
     * Close child windows
     */
    public void closeChildWindows() {
        WindowHandlingUtils.closeAllWindowsExceptParent(driver);
    }
    
    // ======================== TABLE OPERATIONS ========================
    
    /**
     * Get table row count
     */
    public int getTableRowCount() {
        return getElementCount(tableRows);
    }
    
    /**
     * Get cell value by row and column index
     */
    public String getTableCellValue(int rowIndex, int columnIndex) {
        String xpath = "(" + tableRows.toString().replace("By.xpath: ", "") + ")[" + (rowIndex + 1) + 
                      "]//td[" + (columnIndex + 1) + "]";
        return getText(byXPath(xpath));
    }
    
    /**
     * Get all table data
     */
    public java.util.List<WebElement> getTableData() {
        return findElements(tableCells);
    }
    
    // ======================== DYNAMIC LOCATORS ========================
    
    /**
     * Click button by dynamic text
     */
    public void clickButtonByDynamicText(String buttonText) {
        String xpath = DynamicLocatorUtils.getButtonByPartialText(buttonText);
        click(byXPath(xpath));
    }
    
    /**
     * Click link by dynamic text
     */
    public void clickLinkByDynamicText(String linkText) {
        String xpath = DynamicLocatorUtils.getLinkByPartialText(linkText);
        click(byXPath(xpath));
    }
    
    /**
     * Find element by text content
     */
    public String getElementByTextContent(String tagName, String textContent) {
        String xpath = DynamicLocatorUtils.getElementByTextContains(tagName, textContent);
        return getText(byXPath(xpath));
    }
    
    // ======================== ALERT OPERATIONS ========================
    
    /**
     * Trigger alert
     */
    public void triggerAlert() {
        click(alertButton);
    }
    
    /**
     * Accept alert
     */
    public void acceptAlertDialog() {
        acceptAlert();
    }
    
    /**
     * Get alert text
     */
    public String getAlertMessage() {
        return getAlertText();
    }
    
    /**
     * Type text in alert and accept
     */
    public void typeInAlertAndAccept(String text) {
        typeInAlert(text);
        acceptAlert();
    }
    
    // ======================== VALIDATION METHODS ========================
    
    /**
     * Verify dropdown contains option
     */
    public boolean dropdownContains(String option) {
        WebElement dropdown = findElement(countryDropdown);
        java.util.List<String> options = DropdownUtils.getAllOptions(dropdown);
        return options.contains(option);
    }
    
    /**
     * Verify file upload input exists
     */
    public boolean isFileUploadAvailable() {
        return isElementPresent(fileInput);
    }
    
    /**
     * Verify hover menu displays
     */
    public boolean isHoverMenuDisplayed() {
        hoverOverElement();
        return isElementDisplayed(hoverMenu);
    }
    
    /**
     * Verify iframe exists
     */
    public boolean doesIframeExist() {
        return isElementPresent(iframeElement);
    }
}
