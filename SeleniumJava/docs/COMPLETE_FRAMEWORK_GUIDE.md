# 🚀 Selenium Java Automation Framework - Complete Guide

**Version:** 1.0-SNAPSHOT  
**Last Updated:** February 16, 2026

---

## 📑 TABLE OF CONTENTS

1. [Quick Start](#1-quick-start)
2. [Setup & Installation](#2-setup--installation)
3. [Framework Structure](#3-framework-structure)
4. [Basic Usage](#4-basic-usage)
5. [Advanced Utilities](#5-advanced-utilities)
   - 5.1 File Handling
   - 5.2 Data Reading
   - 5.3 Dropdown Handling
   - 5.4 Window Handling
   - 5.5 Frame Switching
   - 5.6 Mouse Actions
   - 5.7 Dynamic Locators
   - 5.8 SQL Database Operations
   - **5.9 POJO Models & Utilities** ✨ NEW
6. [TestNG Features](#6-testng-features)
7. [Parallel Execution](#7-parallel-execution)
8. [Troubleshooting](#8-troubleshooting)
9. [Quick Reference](#9-quick-reference)

---

## 1. QUICK START

### Run Your First Test

```bash
# Clone/navigate to project
cd C:\Users\KumarA2\Documents\AutomationJava\SeleniumJava

# Install dependencies
mvn clean install

# Run tests
mvn test

# Run specific test
mvn test -Dtest=GoogleSearchTest

# Run in parallel
mvn test -DsuiteXmlFile=testng-parallel-methods.xml
```

### Project Features
- ✅ Selenium WebDriver 4.15.0
- ✅ TestNG 7.9.0 with advanced features
- ✅ Maven build automation
- ✅ Page Object Model (POM)
- ✅ 10+ utility classes
- ✅ Parallel execution support
- ✅ Auto-screenshot on failure
- ✅ Comprehensive waits (implicit, explicit, fluent)
- ✅ Hard & soft assertions

---

## 2. SETUP & INSTALLATION

### Prerequisites
- **Java 11+** - `java -version`
- **Maven 3.6+** - `mvn -version`
- **Chrome/Firefox browser**

### Windows Installation

**Install Java:**
```powershell
# Download from: https://www.oracle.com/java/technologies/downloads/
# Verify
java -version
```

**Install Maven (automated):**
```powershell
# Run the install script
.\install-maven.ps1
# Or manually from: https://maven.apache.org/download.cgi
```

**Install Dependencies:**
```bash
mvn clean install
```

### Configuration

**Edit config.properties:**
```properties
# src/test/resources/config/config.properties
browser=chrome
baseUrl=https://www.google.com
timeout=10
headless=false
```

---

## 3. FRAMEWORK STRUCTURE

```
SeleniumJava/
├── docs/                                    # Documentation
│   ├── COMPLETE_FRAMEWORK_GUIDE.md          # Full framework guide
│   ├── ENVIRONMENT_SETUP.md                 # Setup instructions
│   ├── WINDOWS_AGENTS_SETUP.md              # Windows CI/CD setup
│   └── README.md                            # Project overview
├── src/main/java/com/automation/
│   ├── base/
│   │   └── BaseTest.java                    # Base test class
│   ├── pages/
│   │   ├── BasePage.java                    # Base page with all locators
│   │   ├── GoogleSearchPage.java            # Sample page object
│   │   └── AdvancedPage.java                # Advanced sample
│   └── utils/
│       ├── AssertionUtils.java              # Hard & soft assertions
│       ├── WaitUtils.java                   # All wait types
│       ├── ScreenshotUtils.java             # Screenshot capture
│       ├── FileHandlingUtils.java           # File operations
│       ├── DataReader.java                  # CSV/JSON/properties reader
│       ├── DropdownUtils.java               # Dropdown handling
│       ├── WindowHandlingUtils.java         # Window switching
│       ├── FrameSwitchingUtils.java         # Frame operations
│       ├── MouseActionsUtils.java           # Mouse events
│       ├── DynamicLocatorUtils.java         # Dynamic XPath/CSS
│       ├── ConfigManager.java               # Config management
│       └── CommonUtils.java                 # Common utilities
├── src/test/java/com/automation/
│   ├── tests/
│   │   ├── GoogleSearchTest.java            # Sample test
│   │   ├── AdvancedTest.java                # Advanced demo
│   │   ├── AssertionDemoTest.java           # Assertion examples
│   │   ├── WaitsDemoTest.java               # Wait examples
│   │   ├── ScreenshotDemoTest.java          # Screenshot examples
│   │   └── ComprehensiveFeatureTest.java    # All features
│   └── listeners/
│       └── TestListener.java                # Auto-screenshot on fail
├── config/                                  # TestNG configurations
│   ├── testng.xml                           # Default suite
│   ├── testng-parallel-tests.xml            # Parallel tests
│   ├── testng-parallel-methods.xml          # Parallel methods
│   └── testng-parallel-classes.xml          # Parallel classes
├── reports/                                 # Test reports & logs
│   └── automation.log                       # Execution logs
├── pom.xml                                  # Maven config
└── target/                                  # Build output
```

---

## 4. BASIC USAGE

### Create a Page Object

```java
package com.seleniumjava.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    // Locators
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login");
    
    // Actions
    public void enterUsername(String username) {
        type(usernameField, username);
    }
    
    public void enterPassword(String password) {
        type(passwordField, password);
    }
    
    public void clickLogin() {
        click(loginButton);
    }
    
    // Business method
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}
```

### Create a Test

```java
package com.seleniumjava.tests;

import com.seleniumjava.base.BaseTest;
import com.seleniumjava.pages.LoginPage;
import com.seleniumjava.utils.AssertionUtils;
import com.seleniumjava.utils.WaitUtils;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    
    @Test
    public void testValidLogin() {
        driver.get("https://example.com/login");
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("testuser", "password123");
        
        // Wait for dashboard
        WaitUtils.waitForUrlContains(driver, "/dashboard", 10);
        
        // Assert
        AssertionUtils.assertTrue(
            driver.getCurrentUrl().contains("/dashboard"),
            "Should navigate to dashboard"
        );
    }
}
```

---

## 5. ADVANCED UTILITIES

### 5.1 File Handling

```java
import com.seleniumjava.utils.FileHandlingUtils;

// Read file
String content = FileHandlingUtils.readFile("path/to/file.txt");

// Write file
FileHandlingUtils.writeFile("output.txt", "content");

// Delete file
FileHandlingUtils.deleteFile("temp.txt");

// Copy file
FileHandlingUtils.copyFile("source.txt", "destination.txt");
```

### 5.2 Data Reading

```java
import com.seleniumjava.utils.DataReader;

// Read CSV
List<Map<String, String>> data = DataReader.readCSV("testdata.csv");

// Read properties
String value = DataReader.getProperty("config.properties", "key");

// Read JSON
String jsonValue = DataReader.readJSON("data.json", "$.user.name");
```

### 5.3 Dropdown Handling

```java
import com.seleniumjava.utils.DropdownUtils;

// Select by visible text
DropdownUtils.selectByVisibleText(driver, By.id("dropdown"), "Option 1");

// Select by value
DropdownUtils.selectByValue(driver, By.id("dropdown"), "value1");

// Get all options
List<String> options = DropdownUtils.getAllOptions(driver, By.id("dropdown"));

// Get selected option
String selected = DropdownUtils.getSelectedOption(driver, By.id("dropdown"));
```

### 5.4 Window Handling

```java
import com.seleniumjava.utils.WindowHandlingUtils;

// Switch to new window
WindowHandlingUtils.switchToNewWindow(driver);

// Switch by title
WindowHandlingUtils.switchToWindowByTitle(driver, "Window Title");

// Close current and switch to parent
WindowHandlingUtils.closeCurrentAndSwitchToParent(driver);

// Get window count
int count = WindowHandlingUtils.getWindowCount(driver);
```

### 5.5 Frame Switching

```java
import com.seleniumjava.utils.FrameSwitchingUtils;

// Switch by index
FrameSwitchingUtils.switchToFrameByIndex(driver, 0);

// Switch by name/ID
FrameSwitchingUtils.switchToFrameByNameOrId(driver, "frameName");

// Switch by element
WebElement frame = driver.findElement(By.id("frame"));
FrameSwitchingUtils.switchToFrameByElement(driver, frame);

// Switch to parent
FrameSwitchingUtils.switchToParentFrame(driver);

// Switch to default content
FrameSwitchingUtils.switchToDefaultContent(driver);
```

### 5.6 Mouse Actions

```java
import com.seleniumjava.utils.MouseActionsUtils;

WebElement element = driver.findElement(By.id("target"));

// Hover
MouseActionsUtils.hover(driver, element);

// Double click
MouseActionsUtils.doubleClick(driver, element);

// Right click
MouseActionsUtils.rightClick(driver, element);

// Drag and drop
WebElement source = driver.findElement(By.id("source"));
WebElement target = driver.findElement(By.id("target"));
MouseActionsUtils.dragAndDrop(driver, source, target);

// Click and hold
MouseActionsUtils.clickAndHold(driver, element);
```

### 5.7 Dynamic Locators

Dynamic locators are useful for elements with dynamic attributes or IDs that change at runtime. 
Use parameter substitution to build locators dynamically.

**For comprehensive locator strategy and XPath documentation, see the following files:**
- `BasePage.java` - Contains all 8 locator types, XPath types, axes, predicates, functions, and patterns
- `DynamicLocatorUtils.java` - Dynamic locator generation methods with parameter substitution

```java
import com.seleniumjava.utils.DynamicLocatorUtils;

// Dynamic XPath with parameter substitution
String xpath = DynamicLocatorUtils.getDynamicXPath("//button[contains(@class, '{0}')]", "btn-primary");
// Returns: //button[contains(@class, 'btn-primary')]

// Find element by dynamic XPath
WebElement button = DynamicLocatorUtils.findByDynamicXPath(driver, "//button[text()='{0}']", "Submit");

// Click using dynamic XPath
DynamicLocatorUtils.clickByDynamicXPath(driver, "//input[@placeholder='{0}']", "Email");

// Type with dynamic XPath
DynamicLocatorUtils.typeByDynamicXPath(driver, "//input[@name='{0}']", "user@example.com", "email");

// Dynamic CSS selector
String css = DynamicLocatorUtils.getDynamicCSS("#user-{0}", "123");
// Returns: #user-123

// Common patterns (see DynamicLocatorUtils for more methods)
String buttonByText = DynamicLocatorUtils.getButtonByText("Click Me");
String linkWithText = DynamicLocatorUtils.getLinkByPartialText("Login");
String elementByAttr = DynamicLocatorUtils.getElementByAttribute("input", "type", "submit");

// Advanced patterns
String withAnd = DynamicLocatorUtils.getElementByAndCondition("button", "@class='btn'", "@type='submit'");
String withOr = DynamicLocatorUtils.getElementByOrCondition("div", "@id='main'", "@id='content'");
String withNot = DynamicLocatorUtils.getElementByNotCondition("input", "@type='hidden'");
String withStarts = DynamicLocatorUtils.getElementByStartsWith("input", "name", "txt_");
```

### 5.8 SQL Database Operations

```java
import com.seleniumjava.utils.SQLUtils;

// Connect to database
SQLUtils sql = new SQLUtils("jdbc:mysql://localhost:3306/testdb", "user", "password");

// Execute SELECT query
List<Map<String, String>> results = sql.executeQuery("SELECT * FROM users");

// Execute with parameters (prepared statement)
List<Map<String, String>> users = sql.executeQuery("SELECT * FROM users WHERE id = ?", 1);

// Execute UPDATE/INSERT/DELETE
int rowsAffected = sql.executeUpdate("UPDATE users SET status = 'active' WHERE id = ?", 1);

// Insert record
Map<String, Object> values = new HashMap<>();
values.put("name", "John Doe");
values.put("email", "john@example.com");
sql.insertRecord("users", values);

// Update record
Map<String, Object> updates = new HashMap<>();
updates.put("name", "Jane Doe");
sql.updateRecord("users", updates, "email = 'john@example.com'");

// Check if record exists
boolean exists = sql.recordExists("SELECT * FROM users WHERE email = ?", "john@example.com");

// Get single value
String count = sql.getSingleValue("SELECT COUNT(*) FROM users");

// Get row count
int totalUsers = sql.getRowCount("users");
int activeUsers = sql.getRowCount("users", "status = 'active'");

// Transaction management
sql.beginTransaction();
try {
    sql.executeUpdate("INSERT INTO users (name) VALUES ('User1')");
    sql.executeUpdate("INSERT INTO users (name) VALUES ('User2')");
    sql.commit();
} catch (SQLException e) {
    sql.rollback();
}

// Batch operations
String query = "INSERT INTO users (name, email) VALUES (?, ?)";
List<Object[]> batchParams = List.of(
    new Object[]{"User1", "user1@example.com"},
    new Object[]{"User2", "user2@example.com"}
);
sql.executeBatch(query, batchParams);

// Get table metadata
List<String> tables = sql.getTableNames();
List<String> columns = sql.getColumnNames("users");
boolean tableExists = sql.tableExists("users");

// Print results in table format
sql.printResults(results);

// Close connection
sql.close();
```

**Supported Databases:**
- MySQL: `jdbc:mysql://localhost:3306/dbname`
- PostgreSQL: `jdbc:postgresql://localhost:5432/dbname`
- SQLite: `jdbc:sqlite:path/to/database.db`
- SQL Server: `jdbc:sqlserver://localhost:1433;databaseName=dbname`
- Oracle: `jdbc:oracle:thin:@localhost:1521:dbname`

---

### 5.9 POJO Models & Utilities

**POJO (Plain Old Java Object)** classes provide type-safe, structured models for test data. The framework includes pre-built POJOs and comprehensive utilities for conversion and manipulation.

#### Available POJO Models

**User.java** - User entity with authentication and profile fields:
```java
import com.seleniumjava.models.User;

// Create with all fields
User user = new User("john.doe", "Pass123", "john@example.com", 
                     "John", "Doe", 30, true, "USER");

// Create with essential fields (password defaults, active=true, role=USER)
User user = new User("john.doe", "Pass123", "john@example.com");

// Create with default constructor and setters
User user = new User();
user.setUsername("john.doe");
user.setPassword("Pass123");
user.setEmail("john@example.com");

// Helper methods
String fullName = user.getFullName();        // "John Doe"
boolean isAdmin = user.isAdmin();            // Check role == ADMIN
String masked = user.getMaskedPassword();    // "****" for logging
```

**Product.java** - E-commerce product with inventory and pricing:
```java
import com.seleniumjava.models.Product;

// Create product
Product product = new Product("P001", "Laptop", 899.99, 10);

// Set additional fields
product.setCategory("Electronics");
product.setBrand("Dell");
product.setRating(4.5);

// Business logic methods
double total = product.getTotalValue();           // price * quantity
double discounted = product.getDiscountedPrice(10); // 10% off
boolean inStock = product.isHighlyRated();       // rating >= 4.0
String formatted = product.getFormattedPrice();  // "$899.99"

// Auto-updates inStock field
product.setQuantity(0);  // inStock = false
product.setQuantity(5);  // inStock = true
```

#### POJOUtils - POJO Operations Utility

**Map to POJO Conversion:**
```java
import com.seleniumjava.utils.POJOUtils;
import com.seleniumjava.models.User;

// Convert Map to POJO (case-insensitive field matching)
Map<String, String> data = new LinkedHashMap<>();
data.put("username", "john.doe");
data.put("password", "Pass123");
data.put("email", "john@example.com");
data.put("firstName", "John");
data.put("age", "30");

User user = POJOUtils.mapToPOJO(data, User.class);
```

**POJO to Map Conversion:**
```java
// Convert POJO to Map<String, String>
User user = new User("john.doe", "Pass123", "john@example.com");
Map<String, String> map = POJOUtils.pojoToMap(user);

map.get("username");  // "john.doe"
map.get("email");     // "john@example.com"
```

**CSV to POJO Conversion:**
```java
import com.seleniumjava.models.User;
import com.seleniumjava.models.Product;

// Load users from CSV
List<User> users = POJOUtils.csvToPOJOList("src/test/resources/testdata/users.csv", User.class);

// Load products from CSV
List<Product> products = POJOUtils.csvToPOJOList("src/test/resources/testdata/products.csv", Product.class);

// Use in tests
for (User user : users) {
    System.out.println(user.getUsername() + " - " + user.getEmail());
}
```

**Bulk Map List Conversion:**
```java
// Convert list of maps to POJO list
List<Map<String, String>> dataList = new ArrayList<>();
// ... populate maps ...

List<User> users = POJOUtils.mapListToPOJOList(dataList, User.class);
```

**POJO Display/Printing:**
```java
import com.seleniumjava.models.Product;

Product product = new Product("P001", "Laptop", 899.99, 10);

// Print single POJO with formatted output
POJOUtils.printPOJO(product);
// Output:
// ╔════════════════════════╗
// ║     Product Class      ║
// ╠════════════════════════╣
// ║ productId   : P001     ║
// ║ name        : Laptop   ║
// ║ price       : 899.99   ║
// ... more fields ...

// Print list of POJOs
List<Product> products = POJOUtils.csvToPOJOList("path/to/products.csv", Product.class);
POJOUtils.printPOJOList(products);
```

**POJO Validation:**
```java
// Validate required fields
User user = new User("john.doe", "Pass123", "john@example.com");

boolean isValid = POJOUtils.validatePOJO(user, "username", "password", "email");

if (!isValid) {
    System.out.println("Validation failed - check logs for missing fields");
}
```

**POJO Comparison:**
```java
// Compare two POJOs
User user1 = new User("john.doe", "Pass123", "john@example.com", "John", "Doe", 30, true, "USER");
User user2 = new User("john.doe", "Pass456", "john@example.com", "John", "Smith", 35, true, "ADMIN");

// Get differences
Map<String, String[]> differences = POJOUtils.comparePOJOs(user1, user2);
// Result: 
// password: [Pass123, Pass456]
// lastName: [Doe, Smith]
// age: [30, 35]
// role: [USER, ADMIN]

for (Map.Entry<String, String[]> diff : differences.entrySet()) {
    System.out.println(diff.getKey() + ": " + diff.getValue()[0] + " --> " + diff.getValue()[1]);
}
```

**POJO Cloning:**
```java
// Create shallow copy of POJO
User original = new User("john.doe", "Pass123", "john@example.com");
User clone = POJOUtils.clonePOJO(original);

// Modify clone without affecting original
clone.setUsername("jane.doe");
// original.getUsername() still returns "john.doe"
```

#### Data-Driven Testing with POJOs

**Using POJOs in @DataProvider:**
```java
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.seleniumjava.models.User;
import com.seleniumjava.utils.POJOUtils;

public class LoginTest {
    
    @DataProvider(name = "userCredentials")
    public Object[][] getUsersFromCSV() {
        List<User> users = POJOUtils.csvToPOJOList("src/test/resources/testdata/users.csv", User.class);
        Object[][] data = new Object[users.size()][1];
        for (int i = 0; i < users.size(); i++) {
            data[i][0] = users.get(i);
        }
        return data;
    }
    
    @Test(dataProvider = "userCredentials", description = "Data-driven login test with POJOs")
    public void testLoginWithPOJO(User user) {
        // Direct access to type-safe User object
        System.out.println("Testing login for: " + user.getUsername());
        
        // Type-safe access to properties
        if (user.isActive()) {
            performLogin(user.getUsername(), user.getPassword());
        } else {
            System.out.println("User account is inactive, skipping login");
        }
    }
}
```

**Sample CSV Data Files:**
- `src/test/resources/testdata/users.csv` - 5 user records with username, password, email, firstName, lastName, age, active, role
- `src/test/resources/testdata/products.csv` - 5 product records with productId, name, category, price, quantity, description, inStock, brand, rating

#### Complete POJO Example

```java
import com.seleniumjava.models.User;
import com.seleniumjava.utils.POJOUtils;
import java.util.List;
import java.util.Map;

public class POJOExample {
    public static void main(String[] args) {
        // 1. Load data from CSV
        List<User> users = POJOUtils.csvToPOJOList("users.csv", User.class);
        
        // 2. Print loaded data
        POJOUtils.printPOJOList(users);
        
        // 3. Process users
        for (User user : users) {
            // Direct type-safe access
            if (user.isActive()) {
                System.out.println("Active user: " + user.getFullName());
            }
            
            // Validate required fields
            boolean valid = POJOUtils.validatePOJO(user, "username", "password", "email");
            assert valid : "User data validation failed";
            
            // Convert to Map for API calls
            Map<String, String> userMap = POJOUtils.pojoToMap(user);
            sendToAPI(userMap);
        }
    }
    
    private static void sendToAPI(Map<String, String> data) {
        // Send to REST API
    }
}
```

**POJOUtils Method Summary:**
| Method | Purpose | Returns |
|--------|---------|---------|
| `mapToPOJO()` | Convert Map to POJO | T (Generic type) |
| `pojoToMap()` | Convert POJO to Map | Map<String, String> |
| `mapListToPOJOList()` | Bulk convert Map list to POJO list | List<T> |
| `csvToPOJOList()` | Load CSV and convert to POJO list | List<T> |
| `printPOJO()` | Print formatted POJO with borders | void |
| `printPOJOList()` | Print list of POJOs in table format | void |
| `clonePOJO()` | Create shallow copy of POJO | T |
| `comparePOJOs()` | Find differences between POJOs | Map<String, String[]> |
| `validatePOJO()` | Check required fields not null/empty | boolean |

---

## 6. TESTNG FEATURES

### 6.1 Assertions

**Hard Assertions (fail immediately):**
```java
import com.seleniumjava.utils.AssertionUtils;

AssertionUtils.assertTrue(condition, "message");
AssertionUtils.assertEquals(actual, expected, "message");
AssertionUtils.assertNotNull(object, "message");
AssertionUtils.assertContains("Hello World", "World", "message");
AssertionUtils.assertGreaterThan(85, 40, "score check");
```

**Soft Assertions (collect all failures):**
```java
// Initialize
AssertionUtils.initSoftAssert();

// Perform assertions
AssertionUtils.softAssertTrue(condition1, "check 1");
AssertionUtils.softAssertEquals(actual, expected, "check 2");
AssertionUtils.softAssertNotNull(object, "check 3");

// Validate all
AssertionUtils.assertAll(); // Reports all failures
```

### 6.2 Wait Strategies

**Implicit Wait:**
```java
import com.seleniumjava.utils.WaitUtils;

WaitUtils.setImplicitWait(driver, 10);
// ... find elements
WaitUtils.removeImplicitWait(driver);
```

**Explicit Waits:**
```java
// Wait for visible
WebElement element = WaitUtils.waitForElementVisible(driver, By.id("btn"), 10);

// Wait for clickable
WebElement button = WaitUtils.waitForElementClickable(driver, By.id("submit"), 10);

// Wait for present (in DOM)
WebElement hidden = WaitUtils.waitForElementPresent(driver, By.id("hidden"), 10);

// Wait for invisible
boolean gone = WaitUtils.waitForElementInvisible(driver, By.id("loading"), 10);

// Wait for title
boolean titleOk = WaitUtils.waitForTitleContains(driver, "Dashboard", 10);

// Wait for URL
boolean urlOk = WaitUtils.waitForUrlContains(driver, "/dashboard", 10);

// Wait for alert
Alert alert = WaitUtils.waitForAlert(driver, 10);
```

**Fluent Waits:**
```java
// Basic fluent wait (10 sec timeout, 1 sec polling)
WebElement element = WaitUtils.fluentWaitForElement(driver, By.id("dynamic"), 10, 1);

// Custom condition
String result = WaitUtils.fluentWaitForCondition(driver, 10, 1, d -> {
    WebElement el = d.findElement(By.id("status"));
    return el.getText().equals("Complete") ? "Done" : null;
});
```

### 6.3 Screenshots

**Manual Screenshots:**
```java
import com.seleniumjava.utils.ScreenshotUtils;

// Full page screenshot
String path = ScreenshotUtils.takeScreenshot(driver, "TestName");

// Element screenshot
WebElement element = driver.findElement(By.id("logo"));
String elPath = ScreenshotUtils.takeElementScreenshot(element, "Logo");

// Custom directory
String custom = ScreenshotUtils.takeScreenshot(driver, "Test", "custom/dir");

// Get as bytes (for reports)
byte[] bytes = ScreenshotUtils.takeScreenshotAsBytes(driver);

// Get as base64 (for HTML)
String base64 = ScreenshotUtils.takeScreenshotAsBase64(driver);
```

**Automatic Screenshot on Failure:**
```java
// In testng.xml
<listeners>
    <listener class-name="com.seleniumjava.listeners.TestListener"/>
</listeners>

// Or with annotation
@Listeners(com.seleniumjava.listeners.TestListener.class)
public class MyTest extends BaseTest {
    @Test
    public void test() {
        // Auto-screenshot if fails
    }
}
```

### 6.4 Data-Driven Testing

**TestNG DataProvider - Run same test with multiple data sets:**

**CSV DataProvider - Read from CSV file:**
```java
import com.seleniumjava.utils.DataProviderUtils;

@Test(dataProvider = "csvDataProvider", dataProviderClass = DataProviderUtils.class)
public void testWithCSVData(Map<String, String> testData) {
    String username = testData.get("username");
    String password = testData.get("password");
    
    // Your test logic
    login(username, password);
}
```

**Custom CSV File:**
```java
@Test
public void testCustomCSV() {
    Object[][] data = DataProviderUtils.csvDataProviderFromFile("path/to/test-data.csv");
    
    for (Object[] row : data) {
        Map<String, String> testData = (Map<String, String>) row[0];
        // Process test data
    }
}
```

**Simple DataProvider - Hardcoded data:**
```java
@Test(dataProvider = "simpleDataProvider", dataProviderClass = DataProviderUtils.class)
public void testLogin(String username, String password) {
    // Test runs 4 times with different credentials
    login(username, password);
}
```

**Login DataProvider - With expected results:**
```java
@Test(dataProvider = "loginDataProvider", dataProviderClass = DataProviderUtils.class)
public void testLoginScenarios(String user, String pass, boolean expectedSuccess) {
    boolean result = login(user, pass);
    AssertionUtils.assertEquals(result, expectedSuccess, "Login result");
}
```

**Database DataProvider - Read from database:**
```java
@Test(dataProvider = "databaseDataProvider", dataProviderClass = DataProviderUtils.class)
public void testWithDatabaseData(Map<String, String> testData) {
    // Data loaded from database via SQL query
    String username = testData.get("username");
    String password = testData.get("password");
    
    login(username, password);
}
```

**Run with database configuration:**
```bash
mvn test -Dtest=DataDrivenDemoTest#testWithDatabaseData \
         -Ddb.url=jdbc:mysql://localhost:3306/testdb \
         -Ddb.user=root \
         -Ddb.password=password \
         -Ddb.query="SELECT * FROM test_data"
```

**Excel DataProvider - Read from Excel:**
```java
@Test(dataProvider = "excelDataProvider", dataProviderClass = DataProviderUtils.class)
public void testWithExcelData(Map<String, String> testData) {
    String username = testData.get("username");
    // Test with Excel data
}
```

**Custom Database Query:**
```java
Object[][] data = DataProviderUtils.databaseDataProviderFromQuery(
    "jdbc:mysql://localhost:3306/testdb",
    "username",
    "password",
    "SELECT username, password FROM test_users WHERE active = true"
);
```

**Filtered CSV Data:**
```java
// Only read rows where 'testType' column equals 'smoke'
Object[][] data = DataProviderUtils.filteredCsvDataProvider(
    "test-data.csv", 
    "testType", 
    "smoke"
);
```

**Combined Data Sources:**
```java
// Merge data from CSV and Database
Object[][] data = DataProviderUtils.combinedDataProvider(
    "test-data.csv",
    "jdbc:mysql://localhost:3306/testdb",
    "username",
    "password",
    "SELECT * FROM additional_test_data"
);
```

**Available DataProviders:**
- `csvDataProvider` - Default CSV file (test-data.csv)
- `excelDataProvider` - Default Excel file (test-data.xlsx)
- `databaseDataProvider` - Database query (via system properties)
- `simpleDataProvider` - Hardcoded username/password combinations
- `loginDataProvider` - Login scenarios with expected results
- `searchDataProvider` - Search keywords
- `browserDataProvider` - Browser names for cross-browser testing
- `calculationDataProvider` - Number combinations for math tests

**Create CSV Test Data File:**
```csv
username,password,expectedResult,testDescription
user1@test.com,Pass123,true,Valid user login
user2@test.com,Pass456,true,Valid admin login
invaliduser,wrongpass,false,Invalid credentials
```

**Custom DataProvider in Test Class:**
```java
@DataProvider(name = "myCustomData")
public Object[][] myDataProvider() {
    return new Object[][] {
        {"data1", "value1"},
        {"data2", "value2"},
        {"data3", "value3"}
    };
}

@Test(dataProvider = "myCustomData")
public void testWithCustomData(String data, String value) {
    // Test logic
}
```

---

## 7. PARALLEL EXECUTION

### 7.1 Parallel Modes

**Parallel Methods (recommended):**
```xml
<!-- testng-parallel-methods.xml -->
<suite name="Suite" parallel="methods" thread-count="3">
    <test name="Tests">
        <classes>
            <class name="com.seleniumjava.tests.MyTest"/>
        </classes>
    </test>
</suite>
```

**Parallel Tests:**
```xml
<!-- testng-parallel-tests.xml -->
<suite name="Suite" parallel="tests" thread-count="3">
    <test name="Chrome Tests">
        <parameter name="browser" value="chrome"/>
        <classes><class name="com.seleniumjava.tests.MyTest"/></classes>
    </test>
    <test name="Firefox Tests">
        <parameter name="browser" value="firefox"/>
        <classes><class name="com.seleniumjava.tests.MyTest"/></classes>
    </test>
</suite>
```

**Parallel Classes:**
```xml
<!-- testng-parallel-classes.xml -->
<suite name="Suite" parallel="classes" thread-count="3">
    <test name="Tests">
        <classes>
            <class name="com.seleniumjava.tests.Test1"/>
            <class name="com.seleniumjava.tests.Test2"/>
        </classes>
    </test>
</suite>
```

### 7.2 Running Parallel Tests

```bash
# Parallel methods (most common)
mvn test -DsuiteXmlFile=testng-parallel-methods.xml

# Parallel tests (cross-browser)
mvn test -DsuiteXmlFile=testng-parallel-tests.xml

# Parallel classes
mvn test -DsuiteXmlFile=testng-parallel-classes.xml

# Custom thread count
mvn test -DsuiteXmlFile=testng-parallel-methods.xml -DthreadCount=5
```

### 7.3 Thread Safety

✅ **Framework is fully thread-safe:**
- Each test gets its own WebDriver instance
- SoftAssert uses ThreadLocal
- Screenshots have unique timestamped names
- No shared state between tests

**Thread-Safe Test Example:**
```java
@Test(priority = 1)
public void test1() {
    System.out.println("Thread: " + Thread.currentThread().getName());
    driver.get("https://example.com");
    // Each test has isolated driver
}
```

---

## 8. TROUBLESHOOTING

### Common Issues & Solutions

**Issue 1: Tests fail with "driver is null"**
```java
// Solution: Extend BaseTest
public class MyTest extends BaseTest {
    // driver is automatically initialized
}
```

**Issue 2: Element not found**
```java
// Use explicit waits
WebElement element = WaitUtils.waitForElementVisible(driver, By.id("btn"), 10);
```

**Issue 3: Tests flaky in parallel**
```java
// Ensure no shared state
// Each test should be independent
// Use @BeforeMethod, not @BeforeClass for driver setup
```

**Issue 4: OutOfMemoryError in parallel**
```bash
# Reduce thread count or increase heap
set MAVEN_OPTS=-Xmx2048m
mvn test
```

**Issue 5: Screenshots not captured**
```xml
<!-- Enable TestListener in testng.xml -->
<listeners>
    <listener class-name="com.seleniumjava.listeners.TestListener"/>
</listeners>
```

**Issue 6: Maven command not found**
```powershell
# Run install script
.\install-maven.ps1

# Or manually add to PATH
$env:Path += ";C:\apache-maven-3.9.6\bin"
```

### Build Errors

**Error: Tests not compiling**
```bash
mvn clean compile test-compile
```

**Error: Dependency not found**
```bash
mvn clean install -U  # Force update
```

**Error: Port already in use**
```bash
# Kill existing browser processes
taskkill /F /IM chromedriver.exe
taskkill /F /IM chrome.exe
```

---

## 9. QUICK REFERENCE

### Commands
```bash
# Build & Install
mvn clean install

# Compile
mvn clean compile
mvn test-compile

# Run Tests
mvn test                                    # All tests
mvn test -Dtest=TestClass                   # Specific class
mvn test -Dtest=TestClass#testMethod        # Specific method
mvn test -DsuiteXmlFile=testng-file.xml     # Custom suite

# Parallel Execution
mvn test -DsuiteXmlFile=testng-parallel-methods.xml

# Skip Tests
mvn clean install -DskipTests
```

### Assertions
```java
// Hard
AssertionUtils.assertTrue(condition, "msg");
AssertionUtils.assertEquals(actual, expected, "msg");
AssertionUtils.assertNotNull(object, "msg");

// Soft
AssertionUtils.initSoftAssert();
AssertionUtils.softAssertTrue(condition, "msg");
AssertionUtils.assertAll();
```

### Waits
```java
// Implicit
WaitUtils.setImplicitWait(driver, 10);

// Explicit
WaitUtils.waitForElementVisible(driver, locator, 10);
WaitUtils.waitForElementClickable(driver, locator, 10);
WaitUtils.waitForTitleContains(driver, "title", 10);

// Fluent
WaitUtils.fluentWaitForElement(driver, locator, 10, 1);
```

### Screenshots
```java
ScreenshotUtils.takeScreenshot(driver, "TestName");
ScreenshotUtils.takeElementScreenshot(element, "Element");
```

### Utilities
```java
// File
FileHandlingUtils.readFile("path");
FileHandlingUtils.writeFile("path", "content");

// Data
DataReader.readCSV("file.csv");
DataReader.getProperty("file.properties", "key");

// Dropdown
DropdownUtils.selectByVisibleText(driver, locator, "text");

// Window
WindowHandlingUtils.switchToNewWindow(driver);

// Frame
FrameSwitchingUtils.switchToFrameByIndex(driver, 0);

// Mouse
MouseActionsUtils.hover(driver, element);
MouseActionsUtils.dragAndDrop(driver, source, target);

// SQL Database
SQLUtils sql = new SQLUtils("jdbc:mysql://host:3306/db", "user", "pass");
sql.executeQuery("SELECT * FROM users WHERE id = ?", 1);
sql.executeUpdate("UPDATE users SET status = ?", "active");
sql.insertRecord("users", valuesMap);
sql.close();
```

### Data-Driven Testing
```java
// CSV DataProvider
@Test(dataProvider = "csvDataProvider", dataProviderClass = DataProviderUtils.class)
public void test(Map<String, String> testData) {
    String username = testData.get("username");
}

// Simple DataProvider
@Test(dataProvider = "simpleDataProvider", dataProviderClass = DataProviderUtils.class)
public void test(String username, String password) { }

// Login DataProvider
@Test(dataProvider = "loginDataProvider", dataProviderClass = DataProviderUtils.class)
public void test(String user, String pass, boolean expectedSuccess) { }

// Database DataProvider
@Test(dataProvider = "databaseDataProvider", dataProviderClass = DataProviderUtils.class)
public void test(Map<String, String> testData) { }

// Custom CSV file
Object[][] data = DataProviderUtils.csvDataProviderFromFile("path/to/file.csv");

// Custom database query
Object[][] data = DataProviderUtils.databaseDataProviderFromQuery(
    "jdbc:mysql://localhost:3306/db", "user", "pass", "SELECT * FROM test_data");
```

### Demo Tests
```bash
mvn test -Dtest=AssertionDemoTest        # Assertion examples
mvn test -Dtest=WaitsDemoTest            # Wait examples
mvn test -Dtest=ScreenshotDemoTest       # Screenshot examples
mvn test -Dtest=ComprehensiveFeatureTest # All features
mvn test -Dtest=AdvancedTest             # Advanced utilities
mvn test -Dtest=SQLDemoTest              # SQL database operations
mvn test -Dtest=DataDrivenDemoTest       # Data-driven testing (CSV, Excel, DB)
```

---

## 📚 Additional Resources

**Framework Files:**
- `pom.xml` - Maven dependencies
- `testng.xml` - Test suite configuration
- `src/test/resources/config/config.properties` - Test configuration

**Test Reports:**
- `target/surefire-reports/index.html` - TestNG reports
- `target/screenshots/` - Test screenshots
- `reports/automation.log` - Execution logs

**Documentation & Learning:**
- `docs/` - All markdown documentation
- `config/` - TestNG configuration files
- `../Learning/` - 24 Java programs covering basics to advanced (separate parent folder)

---

## 🎯 Best Practices

1. **Page Object Model** - Separate page logic from test logic
2. **Explicit Waits** - Use WaitUtils instead of Thread.sleep()
3. **Soft Assertions** - For comprehensive validation
4. **Parallel Execution** - Start with 2-3 threads
5. **Screenshots** - Enable TestListener for auto-capture
6. **Test Independence** - Each test should run alone
7. **Meaningful Names** - Clear test and method names
8. **Log Everything** - Use System.out or logger for debugging

---

## ✅ Framework Checklist

- [x] Selenium WebDriver 4.15.0
- [x] TestNG 7.9.0
- [x] Maven 3.9.6
- [x] Page Object Model
- [x] 12 Utility classes (File, Data, Dropdown, Window, Frame, Mouse, DynamicLocator, Assertion, Wait, Screenshot, SQL, DataProvider)
- [x] Hard & soft assertions
- [x] Implicit, explicit, fluent waits
- [x] Screenshot capture (manual & auto)
- [x] Parallel execution (3 modes)
- [x] TestNG listener
- [x] Cross-browser support
- [x] Configuration management
- [x] **Data-driven testing** (CSV, Excel, Database, custom DataProviders)
- [x] **SQL database operations** (5 database support)
- [x] Comprehensive documentation
- [x] Demo tests for all features

---

**Framework Version:** 1.0-SNAPSHOT  
**Author:** Selenium Automation Team  
**Last Updated:** February 16, 2026  
**Status:** Production Ready ✅

**Happy Testing! 🎉**
