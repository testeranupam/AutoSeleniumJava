package com.seleniumjava.base;

import com.seleniumjava.utils.ConfigManager;
import com.seleniumjava.utils.LoggerUtil;
import com.seleniumjava.utils.RestApiClient;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Base class for API tests
 * Provides common setup and utility methods for REST API testing
 */
public class BaseApiTest {
    
    protected RestApiClient apiClient;
    protected String baseUrl;
    protected String authToken;
    
    /**
     * Setup method to initialize API client before tests
     */
    @BeforeClass
    public void apiSetup() {
        try {
            // Load API configuration
            baseUrl = ConfigManager.getProperty("api.baseurl");
            authToken = ConfigManager.getProperty("api.token");
            
            // Initialize API client
            apiClient = new RestApiClient(baseUrl);
            
            // Add authentication if token is available
            if (authToken != null && !authToken.isEmpty() && !authToken.equals("your_bearer_token")) {
                apiClient.addBearerToken(authToken);
                LoggerUtil.info("API client initialized with authentication");
            } else {
                LoggerUtil.info("API client initialized without authentication");
            }
            
            LoggerUtil.info("API Test Setup completed for: " + this.getClass().getSimpleName());
            
        } catch (Exception e) {
            LoggerUtil.error("Failed to setup API test: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Cleanup method after tests complete
     */
    @AfterClass
    public void apiTearDown() {
        try {
            // Reset request specification
            if (apiClient != null) {
                apiClient.resetRequestSpec();
            }
            LoggerUtil.info("API Test Teardown completed for: " + this.getClass().getSimpleName());
        } catch (Exception e) {
            LoggerUtil.error("Error during API teardown: " + e.getMessage());
        }
    }
    
    // ==================== Helper Methods ====================
    
    /**
     * Get common headers for API requests
     * @return Map of common headers
     */
    protected Map<String, String> getCommonHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        return headers;
    }
    
    /**
     * Generate unique email for testing
     * @return Unique email address
     */
    protected String generateUniqueEmail() {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return "test_" + timestamp + "@example.com";
    }
    
    /**
     * Generate unique email with prefix
     * @param prefix Email prefix
     * @return Unique email address
     */
    protected String generateUniqueEmail(String prefix) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return prefix + "_" + timestamp + "@example.com";
    }
    
    /**
     * Generate unique username
     * @return Unique username
     */
    protected String generateUniqueUsername() {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return "user_" + timestamp;
    }
    
    /**
     * Generate unique username with prefix
     * @param prefix Username prefix
     * @return Unique username
     */
    protected String generateUniqueUsername(String prefix) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return prefix + "_" + timestamp;
    }
    
    /**
     * Generate UUID
     * @return Random UUID string
     */
    protected String generateUUID() {
        return UUID.randomUUID().toString();
    }
    
    /**
     * Generate test string with timestamp
     * @param prefix String prefix
     * @return Test string with timestamp
     */
    protected String generateTestString(String prefix) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return prefix + "_" + timestamp;
    }
    
    /**
     * Generate random integer between min and max (inclusive)
     * @param min Minimum value
     * @param max Maximum value
     * @return Random integer
     */
    protected int generateRandomInt(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
    
    /**
     * Wait for specified milliseconds
     * @param milliseconds Time to wait
     */
    protected void waitFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            LoggerUtil.error("Wait interrupted: " + e.getMessage());
        }
    }
    
    /**
     * Get current timestamp
     * @return Current timestamp string
     */
    protected String getCurrentTimestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
    
    /**
     * Get current date in specified format
     * @param format Date format pattern
     * @return Formatted date string
     */
    protected String getCurrentDate(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }
    
    /**
     * Create query parameters map
     * @return Empty query parameters map
     */
    protected Map<String, Object> createQueryParams() {
        return new HashMap<>();
    }
    
    /**
     * Add query parameter
     * @param params Query parameters map
     * @param key Parameter name
     * @param value Parameter value
     * @return Updated query parameters map
     */
    protected Map<String, Object> addQueryParam(Map<String, Object> params, String key, Object value) {
        params.put(key, value);
        return params;
    }
    
    /**
     * Log test step
     * @param stepDescription Description of the test step
     */
    protected void logStep(String stepDescription) {
        LoggerUtil.info("TEST STEP: " + stepDescription);
    }
    
    /**
     * Log API endpoint being tested
     * @param method HTTP method
     * @param endpoint API endpoint
     */
    protected void logEndpoint(String method, String endpoint) {
        LoggerUtil.info("API CALL: " + method + " " + baseUrl + endpoint);
    }
    
    /**
     * Get API client instance
     * @return RestApiClient instance
     */
    protected RestApiClient getApiClient() {
        return apiClient;
    }
    
    /**
     * Get base URL
     * @return Base URL string
     */
    protected String getBaseUrl() {
        return baseUrl;
    }
}
