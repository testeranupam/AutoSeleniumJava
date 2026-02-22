package com.seleniumjava.tests;

import com.seleniumjava.base.BaseApiTest;
import com.seleniumjava.models.ApiUser;
import com.seleniumjava.models.Post;
import com.seleniumjava.utils.ApiAssertionUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Basic API tests demonstrating CRUD operations
 * Using JSONPlaceholder fake REST API: https://jsonplaceholder.typicode.com
 */
public class ApiTests extends BaseApiTest {
    
    @Test(priority = 1, description = "Get all users")
    public void testGetAllUsers() {
        logStep("Fetching all users from API");
        
        Response response = apiClient.get("/users");
        
        ApiAssertionUtils.assertOk(response);
        ApiAssertionUtils.assertContentTypeJson(response);
        ApiAssertionUtils.assertContentNotEmpty(response);
        
        List<ApiUser> users = response.jsonPath().getList("", ApiUser.class);
        Assert.assertTrue(users.size() > 0, "Users list should not be empty");
        
        logStep("Successfully retrieved " + users.size() + " users");
    }
    
    @Test(priority = 2, description = "Get user by ID")
    public void testGetUserById() {
        logStep("Fetching user with ID 1");
        
        Response response = apiClient.get("/users/1");
        
        ApiAssertionUtils.assertOk(response);
        ApiAssertionUtils.assertJsonFieldExists(response, "id");
        ApiAssertionUtils.assertJsonFieldExists(response, "name");
        ApiAssertionUtils.assertJsonFieldExists(response, "email");
        ApiAssertionUtils.assertJsonFieldValue(response, "id", 1);
        
        logStep("Successfully retrieved user");
    }
    
    @Test(priority = 3, description = "Get users with query parameters")
    public void testGetUsersWithParams() {
        logStep("Fetching users with query parameters");
        
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", 1);
        
        Response response = apiClient.getWithParams("/users", queryParams);
        
        ApiAssertionUtils.assertOk(response);
        ApiAssertionUtils.assertJsonArrayLength(response, "", 1);
        ApiAssertionUtils.assertJsonFieldValue(response, "[0].id", 1);
        
        logStep("Successfully retrieved filtered users");
    }
    
    @Test(priority = 4, description = "Create new user (POST)")
    public void testCreateUser() {
        logStep("Creating new user");
        
        // Create user object
        ApiUser newUser = new ApiUser();
        newUser.setName("Test User");
        newUser.setEmail(generateUniqueEmail());
        newUser.setUsername(generateUniqueUsername());
        
        Response response = apiClient.post("/users", newUser);
        
        ApiAssertionUtils.assertCreated(response);
        ApiAssertionUtils.assertJsonFieldExists(response, "id");
        ApiAssertionUtils.assertJsonFieldNotNull(response, "id");
        
        logStep("User created successfully with ID: " + response.jsonPath().get("id"));
    }
    
    @Test(priority = 5, description = "Create new post")
    public void testCreatePost() {
        logStep("Creating new post");
        
        Post newPost = new Post(1, "Test Title", "Test Body Content");
        
        Response response = apiClient.post("/posts", newPost);
        
        ApiAssertionUtils.assertCreated(response);
        ApiAssertionUtils.assertJsonFieldExists(response, "id");
        ApiAssertionUtils.assertJsonFieldValue(response, "userId", 1);
        ApiAssertionUtils.assertJsonFieldValue(response, "title", "Test Title");
        
        logStep("Post created successfully");
    }
    
    @Test(priority = 6, description = "Update user (PUT)")
    public void testUpdateUser() {
        logStep("Updating user with ID 1");
        
        ApiUser updatedUser = new ApiUser(1, "Updated Name", "updated@test.com", "updateduser");
        
        Response response = apiClient.put("/users/1", updatedUser);
        
        ApiAssertionUtils.assertOk(response);
        ApiAssertionUtils.assertJsonFieldValue(response, "name", "Updated Name");
        ApiAssertionUtils.assertJsonFieldValue(response, "email", "updated@test.com");
        
        logStep("User updated successfully");
    }
    
    @Test(priority = 7, description = "Partial update (PATCH)")
    public void testPartialUpdateUser() {
        logStep("Partially updating user");
        
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "Patched Name");
        
        Response response = apiClient.patch("/users/1", updates);
        
        ApiAssertionUtils.assertOk(response);
        ApiAssertionUtils.assertJsonFieldValue(response, "name", "Patched Name");
        
        logStep("User patched successfully");
    }
    
    @Test(priority = 8, description = "Delete user")
    public void testDeleteUser() {
        logStep("Deleting user with ID 1");
        
        Response response = apiClient.delete("/users/1");
        
        ApiAssertionUtils.assertOk(response);
        
        logStep("User deleted successfully");
    }
    
    @Test(priority = 9, description = "Get non-existent user (404)")
    public void testGetNonExistentUser() {
        logStep("Attempting to fetch non-existent user");
        
        Response response = apiClient.get("/users/999999");
        
        ApiAssertionUtils.assertNotFound(response);
        
        logStep("404 error returned as expected");
    }
    
    @Test(priority = 10, description = "Verify response time")
    public void testResponseTime() {
        logStep("Testing API response time");
        
        Response response = apiClient.get("/users");
        
        ApiAssertionUtils.assertOk(response);
        ApiAssertionUtils.assertResponseTime(response, 3000);
        
        logStep("Response time is within acceptable limit: " + response.getTime() + "ms");
    }
    
    @Test(priority = 11, description = "Get all posts")
    public void testGetAllPosts() {
        logStep("Fetching all posts");
        
        Response response = apiClient.get("/posts");
        
        ApiAssertionUtils.assertOk(response);
        ApiAssertionUtils.assertJsonArrayNotEmpty(response, "");
        
        List<Post> posts = response.jsonPath().getList("", Post.class);
        Assert.assertTrue(posts.size() == 100, "Should retrieve 100 posts");
        
        logStep("Successfully retrieved " + posts.size() + " posts");
    }
    
    @Test(priority = 12, description = "Get posts by user ID")
    public void testGetPostsByUserId() {
        logStep("Fetching posts for user ID 1");
        
        Map<String, Object> params = new HashMap<>();
        params.put("userId", 1);
        
        Response response = apiClient.getWithParams("/posts", params);
        
        ApiAssertionUtils.assertOk(response);
        ApiAssertionUtils.assertJsonArrayNotEmpty(response, "");
        
        // Verify all posts belong to user 1
        List<Integer> userIds = response.jsonPath().getList("userId", Integer.class);
        for (Integer userId : userIds) {
            Assert.assertEquals(userId, Integer.valueOf(1), "All posts should belong to user 1");
        }
        
        logStep("Successfully retrieved posts for user");
    }
    
    @Test(priority = 13, description = "Multiple assertions on single response")
    public void testMultipleAssertions() {
        logStep("Testing multiple assertions");
        
        Response response = apiClient.get("/users/1");
        
        Map<String, Object> expectedFields = new HashMap<>();
        expectedFields.put("id", 1);
        expectedFields.put("name", "Leanne Graham");
        expectedFields.put("username", "Bret");
        expectedFields.put("email", "Sincere@april.biz");
        
        ApiAssertionUtils.assertMultipleJsonFields(response, expectedFields);
        
        logStep("All assertions passed");
    }
}
