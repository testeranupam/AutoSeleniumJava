package com.seleniumjava.tests;

import com.seleniumjava.base.BaseTest;
import com.seleniumjava.utils.AssertionUtils;
import com.seleniumjava.utils.DataProviderUtils;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

/**
 * Data-Driven Testing Demo
 * Demonstrates TestNG DataProvider with different data sources:
 * - CSV files
 * - Excel files
 * - Database queries
 * - Hardcoded data
 * - Properties files
 * 
 * Key Concepts:
 * 1. @DataProvider - Supplies test data to test methods
 * 2. dataProvider attribute - Links test method to data provider
 * 3. dataProviderClass - Specifies class containing DataProvider (if external)
 * 4. Multiple iterations - Same test runs with different data sets
 * 
 * Run Commands:
 * mvn test -Dtest=DataDrivenDemoTest
 * mvn test -Dtest=DataDrivenDemoTest#testWithCSVData
 * mvn test -Dtest=DataDrivenDemoTest#testWithDatabaseData -Ddb.url=jdbc:mysql://localhost:3306/testdb -Ddb.user=root -Ddb.password=password
 */
public class DataDrivenDemoTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(DataDrivenDemoTest.class);
    
    /**
     * Test 1: CSV DataProvider
     * Reads test data from CSV file and executes test for each row
     * 
     * CSV Structure:
     * username,password,expectedResult,testDescription
     * user1@test.com,Pass123,true,Valid user login
     * 
     * Each row becomes one test iteration
     */
    @Test(dataProvider = "csvDataProvider", dataProviderClass = DataProviderUtils.class,
          description = "Data-driven test using CSV file")
    public void testWithCSVData(Map<String, String> testData) {
        logger.info("═══════════════════════════════════════════════════════");
        logger.info("TEST ITERATION: CSV Data-Driven Test");
        logger.info("═══════════════════════════════════════════════════════");
        
        // Extract data from Map
        String username = testData.get("username");
        String password = testData.get("password");
        String expectedResult = testData.get("expectedResult");
        String description = testData.get("testDescription");
        
        logger.info("Test Description: " + description);
        logger.info("Username: " + username);
        logger.info("Password: " + (password != null ? "****" : "null"));
        logger.info("Expected Result: " + expectedResult);
        
        // Simulate test logic
        boolean actualResult = performLogin(username, password);
        boolean expected = Boolean.parseBoolean(expectedResult);
        
        logger.info("Actual Result: " + actualResult);
        logger.info("Expected: " + expected);
        
        // Verify
        AssertionUtils.assertEquals(actualResult, expected, 
            "Login result verification for: " + description);
        
        logger.info("✓ TEST ITERATION PASSED for: " + username);
        logger.info("═══════════════════════════════════════════════════════\n");
    }
    
    /**
     * Test 2: Simple DataProvider
     * Uses hardcoded data from DataProviderUtils
     * Each row has separate parameters (not Map)
     */
    @Test(dataProvider = "simpleDataProvider", dataProviderClass = DataProviderUtils.class,
          description = "Simple data-driven test with hardcoded data")
    public void testWithSimpleData(String username, String password) {
        logger.info("─────────────────────────────────────────────────────");
        logger.info("Simple Data Test - User: " + username);
        
        // Test logic
        boolean result = performLogin(username, password);
        logger.info("Login attempt result: " + result);
        
        // Verify username is not empty
        AssertionUtils.assertTrue(!username.isEmpty(), "Username should not be empty");
        
        logger.info("✓ Simple data test passed for: " + username);
        logger.info("─────────────────────────────────────────────────────\n");
    }
    
    /**
     * Test 3: Login DataProvider
     * Dedicated data provider for login scenarios with expected results
     */
    @Test(dataProvider = "loginDataProvider", dataProviderClass = DataProviderUtils.class,
          description = "Login test with various credential combinations")
    public void testLoginWithDataProvider(String username, String password, boolean expectedSuccess) {
        logger.info("─────────────────────────────────────────────────────");
        logger.info("Login Test - User: " + username + ", Expected: " + 
                   (expectedSuccess ? "SUCCESS" : "FAILURE"));
        
        // Perform login
        boolean actualSuccess = performLogin(username, password);
        
        logger.info("Actual Result: " + (actualSuccess ? "SUCCESS" : "FAILURE"));
        
        // Verify
        AssertionUtils.assertEquals(actualSuccess, expectedSuccess,
            "Login success verification for user: " + username);
        
        logger.info("✓ Login test passed for: " + username);
        logger.info("─────────────────────────────────────────────────────\n");
    }
    
    /**
     * Test 4: Search DataProvider
     * Tests search functionality with multiple search terms
     */
    @Test(dataProvider = "searchDataProvider", dataProviderClass = DataProviderUtils.class,
          description = "Search test with multiple keywords")
    public void testSearchWithDataProvider(String searchKeyword) {
        logger.info("─────────────────────────────────────────────────────");
        logger.info("Search Test - Keyword: " + searchKeyword);
        
        // Simulate search
        int resultsCount = performSearch(searchKeyword);
        
        logger.info("Search results found: " + resultsCount);
        
        // Verify search returned results
        AssertionUtils.assertTrue(resultsCount > 0, 
            "Search should return results for: " + searchKeyword);
        
        logger.info("✓ Search test passed for keyword: " + searchKeyword);
        logger.info("─────────────────────────────────────────────────────\n");
    }
    
    /**
     * Test 5: Calculation DataProvider
     * Tests mathematical operations with multiple data sets
     */
    @Test(dataProvider = "calculationDataProvider", dataProviderClass = DataProviderUtils.class,
          description = "Calculation test with multiple number combinations")
    public void testCalculationWithDataProvider(int num1, int num2, int expectedSum) {
        logger.info("─────────────────────────────────────────────────────");
        logger.info("Calculation Test - " + num1 + " + " + num2 + " = ?");
        
        // Perform calculation
        int actualSum = num1 + num2;
        
        logger.info("Calculated Sum: " + actualSum);
        logger.info("Expected Sum: " + expectedSum);
        
        // Verify
        AssertionUtils.assertEquals(actualSum, expectedSum,
            "Sum calculation verification for " + num1 + " + " + num2);
        
        logger.info("✓ Calculation test passed");
        logger.info("─────────────────────────────────────────────────────\n");
    }
    
    /**
     * Test 6: Database DataProvider (Disabled by default)
     * Reads test data from database using SQL query
     * 
     * Enable and run with:
     * mvn test -Dtest=DataDrivenDemoTest#testWithDatabaseData 
     *          -Ddb.url=jdbc:mysql://localhost:3306/testdb 
     *          -Ddb.user=root 
     *          -Ddb.password=password
     *          -Ddb.query="SELECT username, password, expectedResult FROM test_data"
     */
    @Test(dataProvider = "databaseDataProvider", dataProviderClass = DataProviderUtils.class,
          description = "Data-driven test using database query",
          enabled = false)  // Disabled by default - enable when database is configured
    public void testWithDatabaseData(Map<String, String> testData) {
        logger.info("═══════════════════════════════════════════════════════");
        logger.info("TEST ITERATION: Database Data-Driven Test");
        logger.info("═══════════════════════════════════════════════════════");
        
        // Extract data from database result
        String username = testData.get("username");
        String password = testData.get("password");
        String expectedResult = testData.get("expectedResult");
        
        logger.info("Data from Database:");
        logger.info("  Username: " + username);
        logger.info("  Password: " + (password != null ? "****" : "null"));
        logger.info("  Expected Result: " + expectedResult);
        
        // Perform test
        boolean actualResult = performLogin(username, password);
        boolean expected = Boolean.parseBoolean(expectedResult);
        
        // Verify
        AssertionUtils.assertEquals(actualResult, expected,
            "Database-driven login result verification");
        
        logger.info("✓ Database test iteration passed for: " + username);
        logger.info("═══════════════════════════════════════════════════════\n");
    }
    
    /**
     * Test 7: Cross-Browser DataProvider
     * Runs same test on multiple browsers
     * Note: Requires browser switching implementation in BaseTest
     */
    @Test(dataProvider = "browserDataProvider", dataProviderClass = DataProviderUtils.class,
          description = "Cross-browser test execution",
          enabled = false)  // Disabled by default - requires browser switching setup
    public void testCrossBrowser(String browserName) {
        logger.info("═══════════════════════════════════════════════════════");
        logger.info("Cross-Browser Test - Browser: " + browserName.toUpperCase());
        logger.info("═══════════════════════════════════════════════════════");
        
        // Note: Browser initialization with specific browser would go here
        // For now, just log the browser being tested
        logger.info("Testing on browser: " + browserName);
        
        // Perform cross-browser test
        boolean pageLoaded = true; // Simulate page load check
        
        AssertionUtils.assertTrue(pageLoaded, 
            "Page should load successfully on " + browserName);
        
        logger.info("✓ Cross-browser test passed on: " + browserName);
        logger.info("═══════════════════════════════════════════════════════\n");
    }
    
    /**
     * Test 8: Custom CSV DataProvider with specific file
     * Demonstrates using custom CSV file path
     */
    @Test(description = "Custom CSV file data-driven test", enabled = false)
    public void testWithCustomCSV() {
        // Example of using custom CSV file
        String customCsvPath = "src/test/resources/testdata/custom-test-data.csv";
        Object[][] data = DataProviderUtils.csvDataProviderFromFile(customCsvPath);
        
        logger.info("Custom CSV Data Provider loaded " + data.length + " records");
        
        // Process each record
        for (Object[] row : data) {
            @SuppressWarnings("unchecked")
            Map<String, String> testData = (Map<String, String>) row[0];
            logger.info("Processing record: " + testData);
            
            // Your test logic here
        }
        
        logger.info("✓ Custom CSV test completed");
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // HELPER METHODS - Simulate test operations
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Simulates login operation
     * In real test, this would interact with UI or API
     * 
     * @param username user to login
     * @param password password to use
     * @return true if login successful, false otherwise
     */
    private boolean performLogin(String username, String password) {
        // Simulate login logic
        if (username == null || username.trim().isEmpty()) {
            logger.info("  → Login failed: Empty username");
            return false;
        }
        
        if (password == null || password.trim().isEmpty()) {
            logger.info("  → Login failed: Empty password");
            return false;
        }
        
        // Simple validation: username contains '@' and password length > 5
        // Reject specific invalid cases for demo
        if (username.toLowerCase().contains("invalid") || 
            password.toLowerCase().contains("wrong")) {
            logger.info("  → Login failed: Invalid credentials");
            return false;
        }
        
        boolean success = username.contains("@") && password.length() >= 5;
        
        if (success) {
            logger.info("  → Login successful");
        } else {
            logger.info("  → Login failed: Invalid credentials");
        }
        
        return success;
    }
    
    /**
     * Simulates search operation
     * 
     * @param keyword search term
     * @return number of results found
     */
    private int performSearch(String keyword) {
        // Simulate search results based on keyword length
        if (keyword == null || keyword.trim().isEmpty()) {
            logger.info("  → Search returned 0 results: Empty keyword");
            return 0;
        }
        
        // Simulate: longer keywords return more results
        int resultsCount = keyword.length() * 10;
        logger.info("  → Search returned " + resultsCount + " results");
        
        return resultsCount;
    }
    
    /**
     * Info Test: Shows data provider configuration examples
     */
    @Test(description = "DataProvider configuration examples", enabled = false)
    public void showDataProviderExamples() {
        logger.info("═══════════════════════════════════════════════════════");
        logger.info("DATA PROVIDER EXAMPLES");
        logger.info("═══════════════════════════════════════════════════════");
        
        logger.info("\n1. CSV DataProvider:");
        logger.info("   @Test(dataProvider = \"csvDataProvider\", dataProviderClass = DataProviderUtils.class)");
        logger.info("   public void test(Map<String, String> testData) { }");
        
        logger.info("\n2. Simple DataProvider:");
        logger.info("   @Test(dataProvider = \"simpleDataProvider\", dataProviderClass = DataProviderUtils.class)");
        logger.info("   public void test(String username, String password) { }");
        
        logger.info("\n3. Database DataProvider:");
        logger.info("   @Test(dataProvider = \"databaseDataProvider\", dataProviderClass = DataProviderUtils.class)");
        logger.info("   Run with: -Ddb.url=... -Ddb.user=... -Ddb.password=...");
        
        logger.info("\n4. Custom CSV File:");
        logger.info("   Object[][] data = DataProviderUtils.csvDataProviderFromFile(\"path/to/file.csv\");");
        
        logger.info("\n5. Custom Database Query:");
        logger.info("   Object[][] data = DataProviderUtils.databaseDataProviderFromQuery(");
        logger.info("       \"jdbc:mysql://...\", \"user\", \"pass\", \"SELECT * FROM test_data\");");
        
        logger.info("\n6. Excel DataProvider:");
        logger.info("   @Test(dataProvider = \"excelDataProvider\", dataProviderClass = DataProviderUtils.class)");
        logger.info("   public void test(Map<String, String> testData) { }");
        
        logger.info("\n7. Filtered CSV Data:");
        logger.info("   Object[][] data = DataProviderUtils.filteredCsvDataProvider(");
        logger.info("       \"file.csv\", \"columnName\", \"filterValue\");");
        
        logger.info("\n═══════════════════════════════════════════════════════");
        logger.info("NOTE: Update CSV path in DataProviderUtils if needed:");
        logger.info("  DEFAULT_CSV_PATH = \"src/test/resources/testdata/test-data.csv\"");
        logger.info("═══════════════════════════════════════════════════════\n");
    }
}
