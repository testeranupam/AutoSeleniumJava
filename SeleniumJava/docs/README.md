# 🚀 Selenium Java Automation Framework

A production-ready automation framework built with **Selenium WebDriver 4**, **Java 11+**, **TestNG 7.9**, and **Maven**. This framework follows industry best practices and includes comprehensive utilities for advanced automation testing.

## ✨ Key Features

✅ **Page Object Model (POM)** - Maintainable and scalable architecture  
✅ **Comprehensive Locators** - All 8 Selenium locator types with complete XPath documentation (see BasePage.java)  
✅ **TestNG Framework** - Advanced test management with parallel execution  
✅ **Maven Build Tool** - Automated dependency and build management  
✅ **WebDriverManager** - Automatic browser driver management (no manual downloads)  
✅ **18 Utility Classes** - Comprehensive utilities for waits, assertions, file handling, database, API, dropdowns, windows, frames, mouse actions, dynamic locators, screenshots, DataProvider, and POJO support  
✅ **REST API Testing** - Complete REST Assured integration with CRUD utilities  
✅ **API Assertions** - Comprehensive API validation utilities  
✅ **POJO Models** - Pre-built User, Product, and API models with type-safe data handling  
✅ **Database Testing** - SQL operations with 5 database support (MySQL, PostgreSQL, SQLite, SQL Server, Oracle)  
✅ **Data-Driven Testing** - TestNG @DataProvider with CSV, Excel, database, and custom data sources + **POJO support**  
✅ **Hard & Soft Assertions** - Flexible validation strategies  
✅ **Smart Waits** - Implicit, explicit, and fluent wait implementations  
✅ **Screenshot Capture** - Automatic on failure + manual capture  
✅ **Parallel Execution** - Run tests concurrently (tests/methods/classes)  
✅ **Thread-Safe** - Designed for parallel test execution  
✅ **Cross-Browser Support** - Chrome, Firefox, and more

## 📖 Complete Documentation

**📘 [COMPLETE_FRAMEWORK_GUIDE.md](COMPLETE_FRAMEWORK_GUIDE.md)** - Full guide with detailed examples, all utilities, and best practices

## ⚡ Quick Start

### Prerequisites
- **Java 11+** - `java -version`
- **Maven 3.6+** - `mvn -version`
- Optionally: IntelliJ IDEA, Eclipse, or VS Code

### Installation
```bash
cd SeleniumJava
mvn clean install
```

### Run Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=GoogleSearchTest
mvn test -Dtest=ApiTests

# Run tests in parallel
mvn test -DsuiteXmlFile=testng-parallel-methods.xml
mvn test -DsuiteXmlFile=testng-parallel-classes.xml

# Run demo tests
mvn test -Dtest=AssertionDemoTest      # Hard & soft assertions
mvn test -Dtest=WaitsDemoTest          # Different wait strategies
mvn test -Dtest=ScreenshotDemoTest     # Screenshot on failure
mvn test -Dtest=DataDrivenDemoTest     # Data-driven testing
mvn test -Dtest=POJODemoTest           # POJO model usage
mvn test -Dtest=SQLDemoTest            # Database testing

# Run all API tests
mvn test -Dtest=*ApiTests*

# Run tests with specific browser
mvn test -DBrowser=Firefox
```

## 🏗️ Project Structure

```
SeleniumJava/
├── .github/                            # GitHub workflows & CI/CD
├── docs/                               # 📘 Documentation files
├── src/                                # 💻 Source code
│   ├── main/java/com/seleniumjava/
│   │   ├── base/                       # 2 base classes (BaseTest, BaseApiTest)
│   │   ├── models/                     # 6 POJO model classes (User, Product, etc.)
│   │   ├── pages/                      # 3 Page Object Models (BasePage, etc.)
│   │   └── utils/                      # 18 utility classes (Waits, Assertions, etc.)
│   └── test/
│       ├── java/com/seleniumjava/
│       │   ├── tests/                  # 13 test classes
│       │   └── listeners/              # TestNG listeners
│       └── resources/                  # Test resources & configurations
│           ├── config/                 # TestNG XML files
│           ├── environments/           # Environment properties
│           └── testdata/               # CSV, text test data files
├── pom.xml                             # Maven configuration
├── target/                             # Build output (classes, jars, reports)
└── README.md                           # Root documentation
```

## 🎯 Quick Example

### UI Test
```java
// Page Object
public class LoginPage extends BasePage {
    private By usernameField = By.id("username");
    
    public void login(String user, String pass) {
        type(usernameField, user);
        click(loginButton);
    }
}

// Test
public class LoginTest extends BaseTest {
    @Test
    public void testLogin() {
        new LoginPage(driver).login("user", "pass");
        WaitUtils.waitForUrlContains(driver, "/dashboard", 10);
        AssertionUtils.assertTrue(isLoggedIn(), "Login successful");
    }
}
```

### API Test
```java
public class UserApiTests extends BaseApiTest {
    @Test
    public void testCreateUser() {
        ApiUser newUser = new ApiUser("John", "john@test.com");
        Response response = apiClient.post("/users", newUser);
        
        ApiAssertionUtils.assertCreated(response);
        ApiAssertionUtils.assertJsonFieldExists(response, "id");
    }
}
```

## � Complete File Documentation

### Base Classes (src/main/java/com/seleniumjava/base/)
- **BaseTest.java** - UI test foundation with WebDriver setup, teardown, browser management
- **BaseApiTest.java** - API test foundation with REST client initialization

### Page Object Models (src/main/java/com/seleniumjava/pages/)
- **BasePage.java** - Core page class with 8 Selenium locator strategies and common web interactions
- **GoogleSearchPage.java** - Example page object for Google Search automation
- **AdvancedPage.java** - Advanced interactions (frame switching, window handling, dynamic content)

### POJO Models (src/main/java/com/seleniumjava/models/)
- **User.java** - User entity (id, name, email, username, phone)
- **Product.java** - Product entity (id, name, price, category, description)
- **ApiUser.java** - API-specific user model
- **Post.java** - Blog post model for API testing
- **Comment.java** - Comment model for nested JSON testing
- **TestData.java** - Generic test data wrapper

### Utility Classes (src/main/java/com/seleniumjava/utils/) - 18 Total

**Waits:** WaitUtils.java  
**Assertions:** AssertionUtils.java, ApiAssertionUtils.java  
**UI Interactions:** MouseActionsUtils.java, DropdownUtils.java, FrameSwitchingUtils.java, WindowHandlingUtils.java  
**File & Data:** FileHandlingUtils.java, DataReader.java, DataProviderUtils.java, POJOUtils.java  
**Configuration:** ConfigManager.java, LoggerUtil.java  
**Database & API:** SQLUtils.java, RestApiClient.java  
**Advanced:** DynamicLocatorUtils.java, ScreenshotUtils.java, CommonUtils.java

### Test Classes (src/test/java/com/seleniumjava/tests/) - 13 Total
**Basic:** GoogleSearchTest.java, SampleTestTemplate.java  
**Demo:** AssertionDemoTest.java, WaitsDemoTest.java, ScreenshotDemoTest.java, DataDrivenDemoTest.java  
**Advanced:** AdvancedTest.java, ComprehensiveFeatureTest.java, POJODemoTest.java, SQLDemoTest.java  
**API:** ApiTests.java, AdvancedApiTests.java, ExampleApiTestWithBase.java

### Test Resources (src/test/resources/)
**Config:** config.properties, testng.xml, testng-parallel-*.xml  
**Environments:** dev.properties, test.properties, staging.properties, prod.properties  
**Test Data:** users.csv, products.csv, test-data.csv, sample.txt

### Documentation (docs/)
- **README.md** - Project overview (this file)
- **COMPLETE_FRAMEWORK_GUIDE.md** - Comprehensive guide with all utilities and examples
- **API_TESTING_GUIDE.md** - Complete REST API testing guide
- **API_QUICK_REFERENCE.md** - Quick API reference
- **ENVIRONMENT_SETUP.md** - Environment setup instructions
- **WINDOWS_AGENTS_SETUP.md** - Windows CI/CD setup

## �📦 Key Technologies

- **Selenium WebDriver** 4.15.0
- **REST Assured** 5.4.0 (API testing)
- **TestNG** 7.9.0 (parallel execution, listeners, assertions)
- **WebDriverManager** 5.6.3 (auto browser driver setup)
- **Apache Commons** (file ops, CSV, configuration)
- **Maven** 3.6+ (build automation)

## � Documentation Guides

### Setup & Configuration
📍 **[ENVIRONMENT_SETUP.md](ENVIRONMENT_SETUP.md)** - Complete setup guide
- JDK 11+ and Maven installation
- IDE setup (IntelliJ, Eclipse, VS Code)
- WebDriverManager configuration
- Running first test

🪟 **[WINDOWS_AGENTS_SETUP.md](WINDOWS_AGENTS_SETUP.md)** - Windows CI/CD setup
- Windows agent configuration
- GitHub Actions and Jenkins integration

### Framework Documentation
📘 **[COMPLETE_FRAMEWORK_GUIDE.md](COMPLETE_FRAMEWORK_GUIDE.md)** - Full detailed guide
- Setup & installation
- All 18 Utility Classes with examples
- BaseTest and BaseApiTest usage
- Page Object Model implementation
- POJO models and data-driven testing
- TestNG advanced features (parallel execution, listeners)
- Troubleshooting and optimization

### API Testing
🌐 **[API_TESTING_GUIDE.md](API_TESTING_GUIDE.md)** - REST API testing guide
- REST Assured fundamentals
- RestApiClient usage for CRUD operations
- Authentication methods
- API assertions and validation
- JSON payload handling with POJO models
- Data-driven API testing

⚡ **[API_QUICK_REFERENCE.md](API_QUICK_REFERENCE.md)** - Quick reference
- Common patterns and code snippets
- Assertion methods and usage
- REST client configuration

### Related Resources
📚 **Learning Folder** - Java learning repository with 26 programs covering Basic, OOP, Advanced, and Interview Preparation

## 💡 Best Practices

✅ Always extend **BaseTest** for UI tests - ensures proper WebDriver setup  
✅ Always extend **BaseApiTest** for API tests - provides REST client and utilities  
✅ Use **WaitUtils** instead of Thread.sleep() - more reliable and faster  
✅ Use **soft assertions** for multiple validations - test continues on assertion failure  
✅ Enable **TestListener** for auto-screenshots - captures on test failures  
✅ Use **Page Object Model** - keeps tests maintainable  
✅ Use **POJO models** for test data - type-safe data management  
✅ Run tests in **parallel** - faster execution using TestNG  
✅ Use **environment properties** - different configs for dev/test/staging/prod  
✅ Read [COMPLETE_FRAMEWORK_GUIDE.md](COMPLETE_FRAMEWORK_GUIDE.md) for detailed examples  

## 🎯 Example Code

### UI Test Example
```java
@Test
public void testSearch() {
    GoogleSearchPage page = new GoogleSearchPage(driver);
    page.searchFor("Selenium");
    WaitUtils.waitForElement(driver, By.xpath("//h3"), 10);
    AssertionUtils.assertTrue(page.hasResults(), "Results should display");
}
```

### API Test Example
```java
@Test
public void testCreateUser() {
    ApiUser user = new ApiUser("John", "john@test.com");
    Response response = RestApiClient.post("/users", user);
    ApiAssertionUtils.assertStatusCode(response, 201);
}
```

### Data-Driven Test Example
```java
@DataProvider
public Object[][] loginData() {
    return new Object[][] { {"user1", "pass1"}, {"user2", "pass2"} };
}

@Test(dataProvider = "loginData")
public void testLogin(String user, String pass) {
    // Test code
}
```

---

**Version:** 1.0-SNAPSHOT | **Status:** Production Ready ✅ | **Last Updated:** February 2026

👉 **Get Started:** [COMPLETE_FRAMEWORK_GUIDE.md](COMPLETE_FRAMEWORK_GUIDE.md)  
👉 **API Testing:** [API_TESTING_GUIDE.md](API_TESTING_GUIDE.md)  
👉 **Quick Reference:** [API_QUICK_REFERENCE.md](API_QUICK_REFERENCE.md)
