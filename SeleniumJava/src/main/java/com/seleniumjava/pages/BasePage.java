package com.seleniumjava.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;
import java.util.List;

/**
 * Enhanced BasePage with comprehensive locator types and methods
 * Supports all Selenium locator strategies and advanced interactions
 * Includes support for: ID, Name, Class, Tag, CSS, XPath, LinkText, PartialLinkText
 * Also supports: Dropdowns, Windows, Frames, Mouse Actions, File Handling, Alerts
 * 
 * ======================== LOCATOR STRATEGIES ========================
 * 1. ID             - By.id("elementId")
 * 2. Name           - By.name("fieldName")
 * 3. Class Name     - By.className("className")
 * 4. Tag Name       - By.tagName("button")
 * 5. CSS Selector   - By.cssSelector("#id > .class")
 * 6. XPath          - By.xpath("//button[@id='submit']")
 * 7. Link Text      - By.linkText("Click Here")
 * 8. Partial Link Text - By.partialLinkText("Click")
 * 
 * ======================== XPATH TYPES & SYNTAX ========================
 * 
 * 1. XPATH TYPES:
 *    a) Absolute XPath: Starts from root element
 *       - Syntax: /html/body/div/button
 *       - Starts with single slash (/)
 *       - Brittle: breaks if DOM structure changes
 *    
 *    b) Relative XPath: Starts from any element in DOM
 *       - Syntax: //button or //div[@id='login']
 *       - Starts with double slash (//)
 *       - Recommended: more flexible and maintainable
 * 
 * 2. XPATH AXES (Navigation paths):
 *    - ancestor         → Selects all ancestors of current node
 *    - ancestor-or-self → Selects ancestors + current node
 *    - child            → Selects all children
 *    - descendant       → Selects all descendants
 *    - descendant-or-self → Selects descendants + current node
 *    - following        → All nodes after current element
 *    - following-sibling → All siblings after current element
 *    - parent           → Direct parent
 *    - preceding        → All nodes before current element
 *    - preceding-sibling → All siblings before current element
 *    - self             → Current node
 * 
 * 3. XPATH PREDICATES (Filters with [...]):
 *    - [@id='login']              → Select by attribute value
 *    - [@type='submit']           → Select by type attribute
 *    - [text()='Click Me']        → Select by exact text
 *    - [contains(text(), 'Click')] → Select by partial text
 *    - [contains(@class, 'btn')]  → Select by partial attribute
 *    - [1]                        → Select first element
 *    - [last()]                   → Select last element
 *    - [position()>2]             → Select elements at position > 2
 * 
 * 4. XPATH FUNCTIONS:
 *    - text()             → Gets element text content
 *    - @attribute         → Gets attribute value
 *    - contains()         → Checks if string contains substring
 *    - starts-with()      → Checks if string starts with value
 *    - substring()        → Extracts substring
 *    - normalize-space()  → Removes extra whitespace
 *    - concat()           → Combines strings
 *    - not()              → Negation operator
 * 
 * 5. COMMON XPATH PATTERNS:
 *    • By ID            → //*[@id='elementId']
 *    • By Class         → //div[@class='className']
 *    • By Name          → //input[@name='fieldName']
 *    • By Text          → //button[text()='Submit']
 *    • By Partial Text  → //a[contains(text(), 'Login')]
 *    • By Tag           → //input[@type='submit']
 *    • By Multiple Attr → //button[@class='btn'][@type='submit']
 *    • Parent-Child     → //form/input[@name='email']
 *    • Sibling          → //label[text()='Email']/following-sibling::input[1]
 *    • By AND logic     → //button[@class='btn' and @type='submit']
 *    • By OR logic      → //*[@id='btn1' or @id='btn2']
 * 
 * 6. XPATH ADVANCED:
 *    • Not condition    → //input[not(@type='hidden')]
 *    • Starts with      → //input[starts-with(@name, 'txt_')]
 *    • Position         → //button[3] (3rd button)
 *    • Last element     → //button[last()]
 *    • Normalized text  → //button[normalize-space()='Click Me']
 * 
 * ======================== USAGE IN SELECTORS ========================
 * CSS is faster, XPath is more powerful. Use XPath for complex selections.
 * See: com.seleniumjava.utils.DynamicLocatorUtils for dynamic locator generation
 */
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    private static final int WAIT_TIME = 10;

    /**
     * Constructor to initialize WebDriver and WebDriverWait
     * @param driver WebDriver instance
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME));
    }

    // ======================== LOCATOR STRATEGIES ========================
    
    /**
     * Creates a locator By ID
     * @param id The ID of the element
     * @return By locator with ID strategy
     */
    protected By byId(String id) {
        return By.id(id);
    }
    
    /**
     * Creates a locator By Name attribute
     * @param name The name attribute value
     * @return By locator with Name strategy
     */
    protected By byName(String name) {
        return By.name(name);
    }
    
    /**
     * Creates a locator By Class Name
     * @param className The class name of the element
     * @return By locator with className strategy
     */
    protected By byClassName(String className) {
        return By.className(className);
    }
    
    /**
     * Creates a locator By Tag Name
     * @param tagName The HTML tag name (e.g., 'button', 'input', 'div')
     * @return By locator with tagName strategy
     */
    protected By byTagName(String tagName) {
        return By.tagName(tagName);
    }
    
    /**
     * Creates a locator By CSS Selector
     * @param css The CSS selector path
     * @return By locator with CSS strategy
     */
    protected By byCSS(String css) {
        return By.cssSelector(css);
    }
    
    /**
     * Creates a locator By XPath
     * @param xpath The XPath expression
     * @return By locator with XPath strategy
     */
    protected By byXPath(String xpath) {
        return By.xpath(xpath);
    }
    
    /**
     * Creates a locator By Link Text (exact match)
     * @param linkText The exact text of the link
     * @return By locator with linkText strategy
     */
    protected By byLinkText(String linkText) {
        return By.linkText(linkText);
    }
    
    /**
     * Creates a locator By Partial Link Text (partial match)
     * @param partialLinkText Partial text of the link to find
     * @return By locator with partialLinkText strategy
     */
    protected By byPartialLinkText(String partialLinkText) {
        return By.partialLinkText(partialLinkText);
    }

    // ======================== WAIT METHODS ========================
    
    /**
     * Waits for an element to be visible in the DOM
     * @param locator The element locator
     * @return WebElement once visible
     */
    protected WebElement waitForElementToBeVisible(By locator) {
        logger.info("Waiting for element visibility: " + locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits for an element to be clickable (visible and enabled)
     * @param locator The element locator
     * @return WebElement once clickable
     */
    protected WebElement waitForElementToBeClickable(By locator) {
        logger.info("Waiting for element clickable: " + locator);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits for an element to be present in the DOM
     * @param locator The element locator
     */
    protected void waitForElementPresence(By locator) {
        logger.info("Waiting for element presence: " + locator);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Waits for an element to become invisible in the DOM
     * @param locator The element locator
     */
    protected void waitForElementInvisibility(By locator) {
        logger.info("Waiting for element invisibility: " + locator);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Waits for an element with custom timeout
     * @param locator The element locator
     * @param timeoutSeconds Custom timeout in seconds
     * @return WebElement once visible
     */
    protected WebElement waitForElement(By locator, int timeoutSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        logger.info("Waiting for element with timeout: " + timeoutSeconds);
        return customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // ======================== CLICK METHODS ========================
    
    /**
     * Clicks on an element
     * @param locator The element locator
     */
    public void click(By locator) {
        try {
            WebElement element = waitForElementToBeClickable(locator);
            element.click();
            logger.info("Clicked element: " + locator);
        } catch (Exception e) {
            logger.error("Error clicking element: " + locator, e);
        }
    }

    /**
     * Performs a double-click on an element
     * @param locator The element locator
     */
    public void doubleClick(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            org.openqa.selenium.interactions.Actions actions = 
                new org.openqa.selenium.interactions.Actions(driver);
            actions.doubleClick(element).perform();
            logger.info("Double-clicked: " + locator);
        } catch (Exception e) {
            logger.error("Error double-clicking: " + locator, e);
        }
    }

    /**
     * Performs a right-click (context click) on an element
     * @param locator The element locator
     */
    public void rightClick(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            org.openqa.selenium.interactions.Actions actions = 
                new org.openqa.selenium.interactions.Actions(driver);
            actions.contextClick(element).perform();
            logger.info("Right-clicked: " + locator);
        } catch (Exception e) {
            logger.error("Error right-clicking: " + locator, e);
        }
    }

    // ======================== TEXT INPUT METHODS ========================
    
    /**
     * Types text into an element (after clearing existing text)
     * @param locator The element locator
     * @param text The text to type
     */
    public void type(By locator, String text) {
        try {
            WebElement element = waitForElementToBeVisible(locator);
            element.clear();
            element.sendKeys(text);
            logger.info("Typed text: " + text);
        } catch (Exception e) {
            logger.error("Error typing text: " + text, e);
        }
    }

    /**
     * Gets the text content of an element
     * @param locator The element locator
     * @return The element's text content
     */
    public String getText(By locator) {
        try {
            WebElement element = waitForElementToBeVisible(locator);
            String text = element.getText();
            logger.info("Got text: " + text);
            return text;
        } catch (Exception e) {
            logger.error("Error getting text: " + locator, e);
            return "";
        }
    }

    /**
     * Gets an attribute value from an element
     * @param locator The element locator
     * @param attributeName The attribute name (e.g., 'value', 'placeholder', 'href')
     * @return The attribute value
     */
    public String getAttribute(By locator, String attributeName) {
        try {
            WebElement element = driver.findElement(locator);
            String value = element.getAttribute(attributeName);
            logger.info("Attribute " + attributeName + " = " + value);
            return value;
        } catch (Exception e) {
            logger.error("Error getting attribute: " + attributeName, e);
            return "";
        }
    }

    // ======================== ELEMENT STATE METHODS ========================
    
    /**
     * Checks if an element is displayed/visible
     * @param locator The element locator
     * @return true if the element is displayed, false otherwise
     */
    public boolean isElementDisplayed(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.isDisplayed();
        } catch (Exception e) {
            logger.info("Element not displayed: " + locator);
            return false;
        }
    }

    /**
     * Checks if an element is enabled
     * @param locator The element locator
     * @return true if the element is enabled, false otherwise
     */
    public boolean isElementEnabled(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if an element is selected (for checkboxes and radio buttons)
     * @param locator The element locator
     * @return true if the element is selected, false otherwise
     */
    public boolean isElementSelected(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.isSelected();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if an element is present in the DOM
     * @param locator The element locator
     * @return true if the element is present, false otherwise
     */
    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // ======================== ELEMENT FIND METHODS ========================
    
    /**
     * Finds a single element using the provided locator
     * @param locator The element locator
     * @return The WebElement
     */
    protected WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    /**
     * Finds multiple elements using the provided locator
     * @param locator The element locator
     * @return A list of WebElements
     */
    protected List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    /**
     * Gets multiple elements using the provided locator
     * @param locator The element locator
     * @return A list of WebElements
     */
    protected List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    /**
     * Gets the count of elements matching the locator
     * @param locator The element locator
     * @return The number of elements found
     */
    public int getElementCount(By locator) {
        return findElements(locator).size();
    }

    // ======================== SCROLL & NAVIGATION METHODS ========================
    
    /**
     * Scrolls the page to make an element visible
     * @param locator The element locator
     */
    public void scrollToElement(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            logger.info("Scrolled to element");
        } catch (Exception e) {
            logger.error("Error scrolling: " + locator, e);
        }
    }

    /**
     * Scrolls the page by a specific number of pixels
     * @param pixels The number of pixels to scroll (positive = down, negative = up)
     */
    public void scrollPage(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, " + pixels + ");");
        logger.info("Scrolled by " + pixels + " pixels");
    }

    /**
     * Scrolls the page to the top
     */
    public void scrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
    }

    /**
     * Scrolls the page to the bottom
     */
    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    /**
     * Gets the page title
     * @return The title of the current page
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Gets the current page URL
     * @return The URL of the current page
     */
    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    /**
     * Refreshes the current page
     */
    public void refreshPage() {
        driver.navigate().refresh();
        logger.info("Page refreshed");
    }

    // ======================== JAVASCRIPT METHODS ========================
    
    /**
     * Executes JavaScript code in the browser
     * @param script The JavaScript code to execute
     * @param args Optional arguments to pass to the script
     * @return The result of the script execution
     */
    protected Object executeScript(String script, Object... args) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript(script, args);
    }

    /**
     * Highlights an element with a red border (useful for debugging)
     * @param locator The element locator
     */
    public void highlightElement(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.border='3px solid red'", element);
            logger.info("Element highlighted");
        } catch (Exception e) {
            logger.error("Error highlighting element", e);
        }
    }

    // ======================== ALERT METHODS ========================
    
    /**
     * Accepts an alert dialog (clicks OK)
     */
    public void acceptAlert() {
        try {
            WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            alertWait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
            logger.info("Alert accepted");
        } catch (Exception e) {
            logger.error("Error accepting alert", e);
        }
    }

    /**
     * Dismisses an alert dialog (clicks Cancel)
     */
    public void dismissAlert() {
        try {
            driver.switchTo().alert().dismiss();
            logger.info("Alert dismissed");
        } catch (Exception e) {
            logger.error("Error dismissing alert", e);
        }
    }

    /**
     * Gets the text content of an alert dialog
     * @return The alert message text
     */
    public String getAlertText() {
        try {
            return driver.switchTo().alert().getText();
        } catch (Exception e) {
            logger.error("Error getting alert text", e);
            return "";
        }
    }

    /**
     * Types text into an alert dialog (for prompt alerts)
     * @param text The text to type in the alert
     */
    public void typeInAlert(String text) {
        try {
            driver.switchTo().alert().sendKeys(text);
            logger.info("Typed in alert: " + text);
        } catch (Exception e) {
            logger.error("Error typing in alert", e);
        }
    }
}
