package com.seleniumjava.utils;

import org.testng.annotations.DataProvider;
import java.util.*;

/**
 * Data Provider Utility for TestNG Data-Driven Testing
 * Provides reusable DataProvider methods for CSV, Excel, Database, and custom data sources
 * 
 * Usage Examples:
 * 
 * CSV DataProvider:
 * @Test(dataProvider = "csvDataProvider", dataProviderClass = DataProviderUtils.class)
 * public void testWithCSVData(Map<String, String> testData) { }
 * 
 * Excel DataProvider:
 * @Test(dataProvider = "excelDataProvider", dataProviderClass = DataProviderUtils.class)
 * public void testWithExcelData(Map<String, String> testData) { }
 * 
 * Database DataProvider:
 * @Test(dataProvider = "databaseDataProvider", dataProviderClass = DataProviderUtils.class)
 * public void testWithDBData(Map<String, String> testData) { }
 * 
 * Simple Array DataProvider:
 * @Test(dataProvider = "simpleDataProvider", dataProviderClass = DataProviderUtils.class)
 * public void testWithSimpleData(String username, String password) { }
 */
public class DataProviderUtils {
    
    // Configuration for data sources
    private static final String DEFAULT_CSV_PATH = "src/test/resources/testdata/test-data.csv";
    private static final String DEFAULT_EXCEL_PATH = "src/test/resources/testdata/test-data.xlsx";
    private static final String DEFAULT_SHEET_NAME = "Sheet1";
    
    /**
     * CSV DataProvider - Reads data from CSV file
     * Returns each row as a Map<String, String> where keys are column headers
     * 
     * @return Object[][] array where each row contains one Map
     */
    @DataProvider(name = "csvDataProvider")
    public static Object[][] csvDataProvider() {
        return csvDataProviderFromFile(DEFAULT_CSV_PATH);
    }
    
    /**
     * CSV DataProvider with custom file path
     * 
     * @param csvFilePath path to CSV file
     * @return Object[][] array of test data
     */
    public static Object[][] csvDataProviderFromFile(String csvFilePath) {
        List<Map<String, String>> csvData = DataReader.readCSV(csvFilePath);
        
        if (csvData.isEmpty()) {
            System.err.println("Warning: CSV file is empty or not found: " + csvFilePath);
            return new Object[0][0];
        }
        
        // Convert List<Map> to Object[][]
        Object[][] data = new Object[csvData.size()][1];
        for (int i = 0; i < csvData.size(); i++) {
            data[i][0] = csvData.get(i);
        }
        
        System.out.println("CSV DataProvider loaded " + csvData.size() + " records from: " + csvFilePath);
        return data;
    }
    
    /**
     * Excel DataProvider - Reads data from Excel file
     * Returns each row as a Map<String, String>
     * 
     * @return Object[][] array of test data
     */
    @DataProvider(name = "excelDataProvider")
    public static Object[][] excelDataProvider() {
        return excelDataProviderFromFile(DEFAULT_EXCEL_PATH, DEFAULT_SHEET_NAME);
    }
    
    /**
     * Excel DataProvider with custom file and sheet
     * 
     * @param excelFilePath path to Excel file
     * @param sheetName sheet name to read
     * @return Object[][] array of test data
     */
    public static Object[][] excelDataProviderFromFile(String excelFilePath, String sheetName) {
        List<Map<String, String>> excelData = DataReader.readExcel(excelFilePath, sheetName);
        
        if (excelData.isEmpty()) {
            System.err.println("Warning: Excel file is empty or not found: " + excelFilePath);
            return new Object[0][0];
        }
        
        Object[][] data = new Object[excelData.size()][1];
        for (int i = 0; i < excelData.size(); i++) {
            data[i][0] = excelData.get(i);
        }
        
        System.out.println("Excel DataProvider loaded " + excelData.size() + " records from: " + excelFilePath);
        return data;
    }
    
    /**
     * Database DataProvider - Reads data from database using SQL query
     * 
     * Usage:
     * Set system properties before running tests:
     * -Ddb.url=jdbc:mysql://localhost:3306/testdb
     * -Ddb.user=root
     * -Ddb.password=password
     * -Ddb.query=SELECT * FROM test_data
     * 
     * @return Object[][] array of test data from database
     */
    @DataProvider(name = "databaseDataProvider")
    public static Object[][] databaseDataProvider() {
        String dbUrl = System.getProperty("db.url", "");
        String dbUser = System.getProperty("db.user", "");
        String dbPassword = System.getProperty("db.password", "");
        String dbQuery = System.getProperty("db.query", "SELECT * FROM test_data");
        
        if (dbUrl.isEmpty()) {
            System.err.println("Warning: Database URL not provided. Use -Ddb.url system property");
            return new Object[0][0];
        }
        
        return databaseDataProviderFromQuery(dbUrl, dbUser, dbPassword, dbQuery);
    }
    
    /**
     * Database DataProvider with custom connection and query
     * 
     * @param jdbcUrl JDBC connection URL
     * @param username database username
     * @param password database password
     * @param query SQL query to fetch test data
     * @return Object[][] array of test data
     */
    public static Object[][] databaseDataProviderFromQuery(String jdbcUrl, String username, 
                                                            String password, String query) {
        SQLUtils sqlUtils = null;
        try {
            sqlUtils = new SQLUtils(jdbcUrl, username, password);
            List<Map<String, String>> dbData = sqlUtils.executeQuery(query);
            
            if (dbData.isEmpty()) {
                System.err.println("Warning: Database query returned no results: " + query);
                return new Object[0][0];
            }
            
            Object[][] data = new Object[dbData.size()][1];
            for (int i = 0; i < dbData.size(); i++) {
                data[i][0] = dbData.get(i);
            }
            
            System.out.println("Database DataProvider loaded " + dbData.size() + " records");
            return data;
            
        } catch (Exception e) {
            System.err.println("Error in Database DataProvider: " + e.getMessage());
            e.printStackTrace();
            return new Object[0][0];
        } finally {
            if (sqlUtils != null) {
                sqlUtils.close();
            }
        }
    }
    
    /**
     * Simple DataProvider - Returns hardcoded test data
     * Each row has multiple parameters (username, password)
     * 
     * Example:
     * @Test(dataProvider = "simpleDataProvider")
     * public void testLogin(String username, String password) { }
     * 
     * @return Object[][] array with username and password combinations
     */
    @DataProvider(name = "simpleDataProvider")
    public static Object[][] simpleDataProvider() {
        return new Object[][] {
            {"user1", "password1"},
            {"user2", "password2"},
            {"user3", "password3"},
            {"admin", "admin123"}
        };
    }
    
    /**
     * Login Credentials DataProvider
     * Returns username and password combinations for login tests
     * 
     * @return Object[][] array of login credentials
     */
    @DataProvider(name = "loginDataProvider")
    public static Object[][] loginDataProvider() {
        return new Object[][] {
            {"validuser@example.com", "ValidPass123", true},
            {"invaliduser@example.com", "WrongPass", false},
            {"testuser@example.com", "TestPass456", true},
            {"", "password", false},
            {"user@example.com", "", false}
        };
    }
    
    /**
     * Search Keywords DataProvider
     * Returns search terms for search functionality tests
     * 
     * @return Object[][] array of search keywords
     */
    @DataProvider(name = "searchDataProvider")
    public static Object[][] searchDataProvider() {
        return new Object[][] {
            {"Selenium WebDriver"},
            {"TestNG Framework"},
            {"Java Automation"},
            {"Data Driven Testing"},
            {"Page Object Model"}
        };
    }
    
    /**
     * Browser DataProvider for Cross-Browser Testing
     * Returns browser names to run tests on different browsers
     * 
     * @return Object[][] array of browser names
     */
    @DataProvider(name = "browserDataProvider")
    public static Object[][] browserDataProvider() {
        return new Object[][] {
            {"chrome"},
            {"firefox"},
            {"edge"}
        };
    }
    
    /**
     * Numeric DataProvider for Calculation Tests
     * Returns numbers for mathematical operation tests
     * 
     * @return Object[][] array with number1, number2, expectedResult
     */
    @DataProvider(name = "calculationDataProvider")
    public static Object[][] calculationDataProvider() {
        return new Object[][] {
            {5, 3, 8},
            {10, 5, 15},
            {100, 200, 300},
            {-5, 5, 0},
            {0, 0, 0}
        };
    }
    
    /**
     * JSON DataProvider - Reads data from JSON file
     * Note: Requires JSON parsing library (Gson or Jackson)
     * 
     * @param jsonFilePath path to JSON file
     * @return Object[][] array of test data
     */
    public static Object[][] jsonDataProviderFromFile(String jsonFilePath) {
        // JSON parsing can be implemented using Gson or Jackson
        System.out.println("JSON DataProvider reading from: " + jsonFilePath);
        String jsonContent = DataReader.readJSON(jsonFilePath);
        
        // Basic implementation - can be enhanced with Gson parsing
        if (jsonContent.isEmpty()) {
            System.err.println("Warning: JSON file is empty or not found: " + jsonFilePath);
            return new Object[0][0];
        }
        
        // For now, return empty array - full implementation requires JSON library
        System.out.println("Note: Full JSON parsing requires Gson or Jackson library");
        return new Object[0][0];
    }
    
    /**
     * Properties File DataProvider
     * Reads test data from properties file
     * 
     * @param propertiesFilePath path to properties file
     * @return Object[][] array where each row contains one property key-value pair
     */
    public static Object[][] propertiesDataProviderFromFile(String propertiesFilePath) {
        Properties props = DataReader.readProperties(propertiesFilePath);
        
        if (props.isEmpty()) {
            System.err.println("Warning: Properties file is empty or not found: " + propertiesFilePath);
            return new Object[0][0];
        }
        
        Object[][] data = new Object[props.size()][2];
        int index = 0;
        for (Map.Entry<Object, Object> entry : props.entrySet()) {
            data[index][0] = entry.getKey().toString();
            data[index][1] = entry.getValue().toString();
            index++;
        }
        
        System.out.println("Properties DataProvider loaded " + props.size() + " properties from: " + propertiesFilePath);
        return data;
    }
    
    /**
     * Combined DataProvider - Merges data from multiple sources
     * Useful when you need test data from both CSV and Database
     * 
     * @param csvPath path to CSV file
     * @param jdbcUrl database URL
     * @param username database username
     * @param password database password
     * @param query SQL query
     * @return Object[][] combined data from CSV and Database
     */
    public static Object[][] combinedDataProvider(String csvPath, String jdbcUrl, 
                                                   String username, String password, String query) {
        List<Map<String, String>> csvData = DataReader.readCSV(csvPath);
        
        SQLUtils sqlUtils = null;
        List<Map<String, String>> dbData = new ArrayList<>();
        try {
            sqlUtils = new SQLUtils(jdbcUrl, username, password);
            dbData = sqlUtils.executeQuery(query);
        } catch (Exception e) {
            System.err.println("Error reading database data: " + e.getMessage());
        } finally {
            if (sqlUtils != null) {
                sqlUtils.close();
            }
        }
        
        // Combine both data sources
        List<Map<String, String>> combinedData = new ArrayList<>();
        combinedData.addAll(csvData);
        combinedData.addAll(dbData);
        
        Object[][] data = new Object[combinedData.size()][1];
        for (int i = 0; i < combinedData.size(); i++) {
            data[i][0] = combinedData.get(i);
        }
        
        System.out.println("Combined DataProvider loaded " + combinedData.size() + " total records");
        System.out.println("  CSV: " + csvData.size() + " records");
        System.out.println("  Database: " + dbData.size() + " records");
        
        return data;
    }
    
    /**
     * Filtered DataProvider - Filters CSV data based on condition
     * 
     * @param csvPath path to CSV file
     * @param columnName column to filter on
     * @param value value to match
     * @return Object[][] filtered data
     */
    public static Object[][] filteredCsvDataProvider(String csvPath, String columnName, String value) {
        List<Map<String, String>> csvData = DataReader.readCSV(csvPath);
        List<Map<String, String>> filteredData = new ArrayList<>();
        
        for (Map<String, String> row : csvData) {
            if (row.containsKey(columnName) && row.get(columnName).equals(value)) {
                filteredData.add(row);
            }
        }
        
        Object[][] data = new Object[filteredData.size()][1];
        for (int i = 0; i < filteredData.size(); i++) {
            data[i][0] = filteredData.get(i);
        }
        
        System.out.println("Filtered DataProvider loaded " + filteredData.size() + " records where " 
                          + columnName + " = " + value);
        return data;
    }
}
