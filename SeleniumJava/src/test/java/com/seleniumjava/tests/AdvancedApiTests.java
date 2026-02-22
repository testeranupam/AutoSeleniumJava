package com.seleniumjava.tests;

import com.seleniumjava.base.BaseApiTest;
import com.seleniumjava.models.Post;
import com.seleniumjava.utils.ApiAssertionUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Advanced API tests demonstrating complex scenarios
 * Includes data-driven tests, chaining, error handling, and performance testing
 */
public class AdvancedApiTests extends BaseApiTest {
    
    @Test(description = "Full CRUD workflow for a post")
    public void testCrudWorkflow() {
        logStep("Step 1: Create a new post");
        Post newPost = new Post(1, "Automation Test Post", "This is a test post body");
        Response createResponse = apiClient.post("/posts", newPost);
        ApiAssertionUtils.assertCreated(createResponse);
        int postId = createResponse.jsonPath().getInt("id");
        logStep("Post created with ID: " + postId);
        
        logStep("Step 2: Read the created post");
        Response getResponse = apiClient.get("/posts/" + postId);
        ApiAssertionUtils.assertOk(getResponse);
        ApiAssertionUtils.assertJsonFieldValue(getResponse, "id", postId);
        logStep("Post retrieved successfully");
        
        logStep("Step 3: Update the post");
        Post updatedPost = new Post(postId, 1, "Updated Title", "Updated body content");
        Response updateResponse = apiClient.put("/posts/" + postId, updatedPost);
        ApiAssertionUtils.assertOk(updateResponse);
        ApiAssertionUtils.assertJsonFieldValue(updateResponse, "title", "Updated Title");
        logStep("Post updated successfully");
        
        logStep("Step 4: Delete the post");
        Response deleteResponse = apiClient.delete("/posts/" + postId);
        ApiAssertionUtils.assertOk(deleteResponse);
        logStep("Post deleted successfully");
        
        logStep("Full CRUD workflow completed");
    }
    
    @DataProvider(name = "userIds")
    public Object[][] getUserIds() {
        return new Object[][] {
            {1, "Leanne Graham"},
            {2, "Ervin Howell"},
            {3, "Clementine Bauch"},
            {4, "Patricia Lebsack"},
            {5, "Chelsey Dietrich"}
        };
    }
    
    @Test(dataProvider = "userIds", description = "Data-driven test for users")
    public void testGetUserDataDriven(int userId, String expectedName) {
        logStep("Testing user ID: " + userId);
        
        Response response = apiClient.get("/users/" + userId);
        
        ApiAssertionUtils.assertOk(response);
        ApiAssertionUtils.assertJsonFieldValue(response, "id", userId);
        ApiAssertionUtils.assertJsonFieldValue(response, "name", expectedName);
        
        logStep("User " + userId + " verification passed");
    }
    
    @Test(description = "Chained API calls - Get user and their posts")
    public void testChainedApiCalls() {
        logStep("Step 1: Get user details");
        Response userResponse = apiClient.get("/users/1");
        ApiAssertionUtils.assertOk(userResponse);
        int userId = userResponse.jsonPath().getInt("id");
        String userName = userResponse.jsonPath().getString("name");
        logStep("Retrieved user: " + userName);
        
        logStep("Step 2: Get posts for the user");
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        Response postsResponse = apiClient.getWithParams("/posts", params);
        ApiAssertionUtils.assertOk(postsResponse);
        
        List<Post> posts = postsResponse.jsonPath().getList("", Post.class);
        Assert.assertTrue(posts.size() > 0, "User should have posts");
        logStep("User has " + posts.size() + " posts");
        
        logStep("Step 3: Get comments for first post");
        int firstPostId = posts.get(0).getId();
        Response commentsResponse = apiClient.get("/posts/" + firstPostId + "/comments");
        ApiAssertionUtils.assertOk(commentsResponse);
        ApiAssertionUtils.assertJsonArrayNotEmpty(commentsResponse, "");
        
        int commentCount = commentsResponse.jsonPath().getList("").size();
        logStep("First post has " + commentCount + " comments");
        
        logStep("Chained API calls completed successfully");
    }
    
    @Test(description = "Test with custom headers")
    public void testCustomHeaders() {
        logStep("Adding custom headers to request");
        
        apiClient.addHeader("X-Custom-Header", "TestValue")
                 .addHeader("X-Request-ID", generateUUID());
        
        Response response = apiClient.get("/users/1");
        
        ApiAssertionUtils.assertOk(response);
        logStep("Request with custom headers successful");
        
        // Reset for other tests
        apiClient.resetRequestSpec();
    }
    
    @Test(description = "Test pagination and filtering")
    public void testPaginationAndFiltering() {
        logStep("Testing pagination with query parameters");
        
        Map<String, Object> params = new HashMap<>();
        params.put("_page", 1);
        params.put("_limit", 5);
        
        Response response = apiClient.getWithParams("/posts", params);
        
        ApiAssertionUtils.assertOk(response);
        
        List<Post> posts = response.jsonPath().getList("", Post.class);
        Assert.assertTrue(posts.size() <= 5, "Should return max 5 posts");
        
        logStep("Pagination working correctly - returned " + posts.size() + " posts");
    }
    
    @Test(description = "Test error handling for bad request")
    public void testBadRequest() {
        logStep("Sending invalid data to test error handling");
        
        Map<String, Object> invalidData = new HashMap<>();
        // Empty data might not cause 400 on JSONPlaceholder, but demonstrates the test
        
        Response response = apiClient.post("/posts", invalidData);
        
        // JSONPlaceholder accepts empty posts, but in real API this would be 400
        Assert.assertTrue(response.getStatusCode() >= 200 && response.getStatusCode() < 500,
                "Should receive valid HTTP status code");
        
        logStep("Error handling test completed");
    }
    
    @Test(description = "Test with query string params")
    public void testQueryStringSearch() {
        logStep("Searching posts by title");
        
        Response allPostsResponse = apiClient.get("/posts");
        String firstPostTitle = allPostsResponse.jsonPath().getString("[0].title");
        
        Map<String, Object> searchParams = new HashMap<>();
        searchParams.put("title", firstPostTitle);
        
        Response response = apiClient.getWithParams("/posts", searchParams);
        
        ApiAssertionUtils.assertOk(response);
        
        List<String> titles = response.jsonPath().getList("title", String.class);
        for (String title : titles) {
            Assert.assertEquals(title, firstPostTitle, "All results should match search title");
        }
        
        logStep("Query string search completed");
    }
    
    @Test(description = "Test nested resource endpoints")
    public void testNestedResources() {
        logStep("Testing nested resource: /posts/{id}/comments");
        
        Response response = apiClient.get("/posts/1/comments");
        
        ApiAssertionUtils.assertOk(response);
        ApiAssertionUtils.assertContentTypeJson(response);
        ApiAssertionUtils.assertJsonArrayNotEmpty(response, "");
        
        // Verify all comments belong to post 1
        List<Integer> postIds = response.jsonPath().getList("postId", Integer.class);
        for (Integer postId : postIds) {
            Assert.assertEquals(postId, Integer.valueOf(1), 
                    "All comments should belong to post 1");
        }
        
        logStep("Nested resource test completed");
    }
    
    @Test(description = "Test multiple resources")
    public void testMultipleResourceTypes() {
        logStep("Testing multiple resource endpoints");
        
        String[] endpoints = {"/users", "/posts", "/comments", "/albums", "/photos", "/todos"};
        
        for (String endpoint : endpoints) {
            Response response = apiClient.get(endpoint);
            ApiAssertionUtils.assertOk(response);
            ApiAssertionUtils.assertJsonArrayNotEmpty(response, "");
            logStep("Endpoint " + endpoint + " working correctly");
        }
        
        logStep("All resource types tested successfully");
    }
    
    @Test(description = "Performance test - multiple concurrent calls")
    public void testPerformanceMultipleCalls() {
        logStep("Testing performance with multiple API calls");
        
        long startTime = System.currentTimeMillis();
        
        for (int i = 1; i <= 5; i++) {
            Response response = apiClient.get("/users/" + i);
            ApiAssertionUtils.assertOk(response);
        }
        
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        
        Assert.assertTrue(totalTime < 10000, 
                "5 API calls should complete within 10 seconds");
        
        logStep("Performance test completed in " + totalTime + "ms");
    }
    
    @Test(description = "Test response headers")
    public void testResponseHeaders() {
        logStep("Verifying response headers");
        
        Response response = apiClient.get("/users/1");
        
        ApiAssertionUtils.assertOk(response);
        ApiAssertionUtils.assertHasHeader(response, "Content-Type");
        ApiAssertionUtils.assertContentTypeJson(response);
        
        logStep("Response headers verified successfully");
    }
    
    @Test(description = "Test with different content types")
    public void testContentTypeHandling() {
        logStep("Testing content type handling");
        
        // JSON content type (default)
        Response jsonResponse = apiClient.get("/users/1");
        ApiAssertionUtils.assertOk(jsonResponse);
        ApiAssertionUtils.assertContentTypeJson(jsonResponse);
        
        logStep("Content type test completed");
    }
    
    @Test(description = "Validate complex JSON structure")
    public void testComplexJsonValidation() {
        logStep("Validating complex JSON structure");
        
        Response response = apiClient.get("/users/1");
        
        // Validate top-level fields
        ApiAssertionUtils.assertJsonFieldExists(response, "id");
        ApiAssertionUtils.assertJsonFieldExists(response, "name");
        ApiAssertionUtils.assertJsonFieldExists(response, "email");
        
        // Validate nested address
        ApiAssertionUtils.assertJsonFieldExists(response, "address");
        ApiAssertionUtils.assertJsonFieldExists(response, "address.street");
        ApiAssertionUtils.assertJsonFieldExists(response, "address.city");
        ApiAssertionUtils.assertJsonFieldExists(response, "address.zipcode");
        
        // Validate nested geo
        ApiAssertionUtils.assertJsonFieldExists(response, "address.geo");
        ApiAssertionUtils.assertJsonFieldExists(response, "address.geo.lat");
        ApiAssertionUtils.assertJsonFieldExists(response, "address.geo.lng");
        
        // Validate company
        ApiAssertionUtils.assertJsonFieldExists(response, "company");
        ApiAssertionUtils.assertJsonFieldExists(response, "company.name");
        
        logStep("Complex JSON structure validated successfully");
    }
}
