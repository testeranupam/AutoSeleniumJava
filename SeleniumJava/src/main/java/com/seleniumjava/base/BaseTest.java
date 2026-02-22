package com.seleniumjava.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;

/**
 * BaseTest class containing common WebDriver initialization and teardown logic
 * This is the foundation for all test classes
 */
public class BaseTest {
    protected WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    private static final int WAIT_TIME = 10;

    /**
     * Initialize WebDriver before each test
     * Default browser: Chrome
     */
    public void setup() {
        initializeBrowser("chrome");
        logger.info("WebDriver initialized successfully");
    }

    /**
     * Initialize WebDriver with specific browser
     * @param browser - Browser type: "chrome" or "firefox"
     */
    public void setup(String browser) {
        initializeBrowser(browser.toLowerCase());
        logger.info("WebDriver initialized for browser: " + browser);
    }

    /**
     * Initialize the browser based on browser type
     * @param browser - Browser name
     */
    private void initializeBrowser(String browser) {
        if (browser.equals("chrome")) {
            initializeChrome();
        } else if (browser.equals("firefox")) {
            initializeFirefox();
        } else {
            logger.warn("Invalid browser: " + browser + ". Defaulting to Chrome");
            initializeChrome();
        }
        configureDriver();
    }

    /**
     * Initialize Chrome browser with WebDriverManager
     */
    private void initializeChrome() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        // Uncomment below for headless mode
        // options.addArguments("--headless");
        driver = new ChromeDriver(options);
        logger.info("Chrome browser initialized");
    }

    /**
     * Initialize Firefox browser with WebDriverManager
     */
    private void initializeFirefox() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--width=1920", "--height=1080");
        // Uncomment below for headless mode
        // options.addArguments("--headless");
        driver = new FirefoxDriver(options);
        logger.info("Firefox browser initialized");
    }

    /**
     * Configure common WebDriver settings
     */
    private void configureDriver() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT_TIME));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(WAIT_TIME));
        logger.info("WebDriver configured with wait times: " + WAIT_TIME + " seconds");
    }

    /**
     * Navigate to a specific URL
     * @param url - The URL to navigate to
     */
    public void navigateTo(String url) {
        driver.navigate().to(url);
        logger.info("Navigated to URL: " + url);
    }

    /**
     * Close the WebDriver and release resources
     */
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("WebDriver closed successfully");
        }
    }

    /**
     * Get current WebDriver instance
     * @return WebDriver instance
     */
    public WebDriver getDriver() {
        return driver;
    }
}
