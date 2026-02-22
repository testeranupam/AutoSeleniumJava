package com.seleniumjava.tests;

import com.seleniumjava.base.BaseApiTest;
import com.seleniumjava.models.ApiUser;
import com.seleniumjava.models.Post;
import com.seleniumjava.utils.ApiAssertionUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

/**
 * Example API tests demonstrating best practices
 * Shows proper usage of BaseApiTest and utility methods
 */
public class ExampleApiTestWithBase extends BaseApiTest {
    
    @Test(description = "Example: Create and verify user")
    public void testCreateAndVerifyUser() {
        // Use base class helper to generate unique data
        String uniqueEmail = generateUniqueEmail("testuser");
        String uniqueUsername = generateUniqueUsername("test");
        
        logStep("Creating new user with email: " + uniqueEmail);
        
        // Create user using model class
        ApiUser newUser = new ApiUser();
        newUser.setName("Test User " + getCurrentTimestamp());
        newUser.setEmail(uniqueEmail);
        newUser.setUsername(uniqueUsername);
        
        // Make API call using inherited apiClient
        Response createResponse = apiClient.post("/users", newUser);
        
        // Use assertion utility
        ApiAssertionUtils.assertCreated(createResponse);
        ApiAssertionUtils.assertJsonFieldExists(createResponse, "id");
        
        int userId = createResponse.jsonPath().getInt("id");
        logStep("User created with ID: " + userId);
        
        // Verify the created user
        logStep("Verifying created user");
        Response getResponse = apiClient.get("/users/" + userId);
        ApiAssertionUtils.assertOk(getResponse);
        
        logStep("Test completed successfully");
    }
    
    @Test(description = "Example: Using query parameters")
    public void testWithQueryParameters() {
        logStep("Testing with query parameters");
        
        // Use helper method to create query params
        Map<String, Object> queryParams = createQueryParams();
        addQueryParam(queryParams, "userId", 1);
        addQueryParam(queryParams, "_limit", 5);
        
        logEndpoint("GET", "/posts");
        Response response = apiClient.getWithParams("/posts", queryParams);
        
        ApiAssertionUtils.assertOk(response);
        ApiAssertionUtils.assertJsonArrayNotEmpty(response, "");
        
        int resultCount = response.jsonPath().getList("").size();
        Assert.assertTrue(resultCount <= 5, "Should return max 5 results");
        
        logStep("Query parameters test passed with " + resultCount + " results");
    }
    
    @Test(description = "Example: CRUD operations with base helpers")
    public void testCrudWithHelpers() {
        // CREATE
        logStep("Step 1: Create post");
        Post newPost = new Post();
        newPost.setUserId(1);
        newPost.setTitle(generateTestString("Post"));
        newPost.setBody("This is test content created at: " + getCurrentTimestamp());
        
        Response createResponse = apiClient.post("/posts", newPost);
        ApiAssertionUtils.assertCreated(createResponse);
        int postId = createResponse.jsonPath().getInt("id");
        
        // READ
        logStep("Step 2: Read post");
        Response getResponse = apiClient.get("/posts/" + postId);
        ApiAssertionUtils.assertOk(getResponse);
        
        // UPDATE
        logStep("Step 3: Update post");
        newPost.setId(postId);
        newPost.setTitle(generateTestString("UpdatedPost"));
        Response updateResponse = apiClient.put("/posts/" + postId, newPost);
        ApiAssertionUtils.assertOk(updateResponse);
        
        // DELETE
        logStep("Step 4: Delete post");
        Response deleteResponse = apiClient.delete("/posts/" + postId);
        ApiAssertionUtils.assertOk(deleteResponse);
        
        logStep("CRUD operations completed successfully");
    }
    
    @Test(description = "Example: Custom headers usage")
    public void testWithCustomHeaders() {
        logStep("Adding custom headers");
        
        // Get common headers from base class
        Map<String, String> headers = getCommonHeaders();
        headers.put("X-Custom-Header", "CustomValue");
        headers.put("X-Request-ID", generateUUID());
        
        // Add headers to client
        apiClient.addHeaders(headers);
        
        Response response = apiClient.get("/users/1");
        ApiAssertionUtils.assertOk(response);
        
        logStep("Custom headers test completed");
        
        // Clean up - reset request spec
        apiClient.resetRequestSpec();
    }
    
    @Test(description = "Example: Error handling")
    public void testErrorHandling() {
        logStep("Testing error scenarios");
        
        // Test 404 - Resource not found
        Response notFoundResponse = apiClient.get("/users/999999");
        ApiAssertionUtils.assertNotFound(notFoundResponse);
        logStep("404 test passed");
        
        // Test invalid endpoint
        Response invalidEndpoint = apiClient.get("/invalidendpoint");
        Assert.assertTrue(invalidEndpoint.getStatusCode() >= 400, 
                "Invalid endpoint should return error status");
        logStep("Invalid endpoint test passed");
        
        logStep("Error handling tests completed");
    }
    
    @Test(description = "Example: Response time validation")
    public void testResponseTimeValidation() {
        logStep("Testing response time");
        
        long startTime = System.currentTimeMillis();
        Response response = apiClient.get("/users");
        long endTime = System.currentTimeMillis();
        
        ApiAssertionUtils.assertOk(response);
        
        // Use assertion utility for response time
        ApiAssertionUtils.assertResponseTime(response, 5000);
        
        long actualTime = endTime - startTime;
        logStep("Response time: " + actualTime + "ms");
        logStep("Response time validation passed");
    }
    
    @Test(description = "Example: Multiple assertions")
    public void testMultipleAssertions() {
        logStep("Testing multiple assertions");
        
        Response response = apiClient.get("/users/1");
        
        // Multiple field assertions
        Map<String, Object> expectedFields = new HashMap<>();
        expectedFields.put("id", 1);
        expectedFields.put("name", "Leanne Graham");
        expectedFields.put("username", "Bret");
        expectedFields.put("email", "Sincere@april.biz");
        
        ApiAssertionUtils.assertMultipleJsonFields(response, expectedFields);
        
        // Additional validations
        ApiAssertionUtils.assertJsonFieldExists(response, "address");
        ApiAssertionUtils.assertJsonFieldExists(response, "company");
        
        logStep("All assertions passed");
    }
    
    @Test(description = "Example: Using wait in tests")
    public void testWithWait() {
        logStep("Creating resource");
        
        Post newPost = new Post(1, "Test Post", "Test Body");
        Response createResponse = apiClient.post("/posts", newPost);
        ApiAssertionUtils.assertCreated(createResponse);
        
        // Wait before verification (if needed)
        logStep("Waiting before verification");
        waitFor(1000); // Wait 1 second
        
        logStep("Verifying resource");
        int postId = createResponse.jsonPath().getInt("id");
        Response getResponse = apiClient.get("/posts/" + postId);
        ApiAssertionUtils.assertOk(getResponse);
        
        logStep("Test with wait completed");
    }
    
    @Test(description = "Example: Nested data validation")
    public void testNestedDataValidation() {
        logStep("Validating nested JSON data");
        
        Response response = apiClient.get("/users/1");
        
        // Validate nested address fields
        ApiAssertionUtils.assertJsonFieldExists(response, "address");
        ApiAssertionUtils.assertJsonFieldExists(response, "address.street");
        ApiAssertionUtils.assertJsonFieldExists(response, "address.city");
        ApiAssertionUtils.assertJsonFieldExists(response, "address.geo.lat");
        ApiAssertionUtils.assertJsonFieldExists(response, "address.geo.lng");
        
        // Validate nested company fields
        ApiAssertionUtils.assertJsonFieldExists(response, "company.name");
        ApiAssertionUtils.assertJsonFieldNotNull(response, "company.name");
        
        logStep("Nested data validation completed");
    }
}
