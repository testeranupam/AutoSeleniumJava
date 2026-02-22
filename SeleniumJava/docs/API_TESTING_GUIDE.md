# API Testing Guide

REST API testing using **REST Assured** with comprehensive utilities.

## Setup

```properties
# src/test/resources/config/config.properties
api.baseurl=https://jsonplaceholder.typicode.com
api.token=your_bearer_token_here
```

```java
// Initialize client
RestApiClient apiClient = new RestApiClient("https://api.example.com");

// Authentication options
apiClient.addBearerToken("token");
apiClient.addBasicAuth("user", "pass");
apiClient.addApiKey("X-API-Key", "key");
```

## CRUD Operations

```java
// GET
Response response = apiClient.get("/users");
Response user = apiClient.get("/users/1");
Response response = apiClient.getWithParams("/users", queryParams);

// POST
Response created = apiClient.post("/users", newUser);

// PUT & PATCH
Response updated = apiClient.put("/users/1", updatedUser);
Response response = apiClient.patch("/users/1", partialUpdate);

// DELETE
Response response = apiClient.delete("/users/1");

// File Upload
Response response = apiClient.uploadFile("/upload", "file.jpg");
```

## Assertions

```java
// Status codes
ApiAssertionUtils.assertSuccess(response);     // 2xx
ApiAssertionUtils.assertOk(response);          // 200
ApiAssertionUtils.assertCreated(response);     // 201
ApiAssertionUtils.assertNotFound(response);    // 404

// Content
ApiAssertionUtils.assertContentNotEmpty(response);
ApiAssertionUtils.assertContentContains(response, "text");
ApiAssertionUtils.assertContentTypeJson(response);

// JSON
ApiAssertionUtils.assertJsonFieldExists(response, "data.id");
ApiAssertionUtils.assertJsonFieldValue(response, "data.name", "John");
ApiAssertionUtils.assertJsonArrayLength(response, "data.users", 10);

// Performance
ApiAssertionUtils.assertResponseTime(response, 2000);
```

## Example Tests

```java
public class UserApiTests extends BaseApiTest {
    
    @Test
    public void testCrudOperations() {
        // CREATE
        ApiUser newUser = new ApiUser("John", "john@test.com");
        newUser.setUsername(generateUniqueUsername());
        Response createResponse = apiClient.post("/users", newUser);
        ApiAssertionUtils.assertCreated(createResponse);
        
        // READ
        int userId = createResponse.jsonPath().getInt("id");
        Response getResponse = apiClient.get("/users/" + userId);
        ApiAssertionUtils.assertOk(getResponse);
        
        // UPDATE
        newUser.setId(userId);
        newUser.setName("John Updated");
        Response updateResponse = apiClient.put("/users/" + userId, newUser);
        ApiAssertionUtils.assertOk(updateResponse);
        
        // DELETE
        Response deleteResponse = apiClient.delete("/users/" + userId);
        ApiAssertionUtils.assertOk(deleteResponse);
    }
    
    @Test
    @DataProvider(name = "userIds")
    public Object[][] getUserIds() {
        return new Object[][] {
            {1, "Leanne Graham"},
            {2, "Ervin Howell"}
        };
    }
    
    @Test(dataProvider = "userIds")
    public void testGetUserDataDriven(int userId, String expectedName) {
        Response response = apiClient.get("/users/" + userId);
        ApiAssertionUtils.assertJsonFieldValue(response, "name", expectedName);
    }
    
    @Test
    public void testErrorHandling() {
        Response response = apiClient.get("/users/999999");
        ApiAssertionUtils.assertNotFound(response);
    }
}
```

## Running Tests

```bash
# All API tests
mvn test -Dtest=ApiTests

# Specific test class
mvn test -Dtest=AdvancedApiTests

# All tests
mvn test
```

## Best Practices

1. **Inherit from BaseApiTest** - handles setup automatically
2. **Use helper methods** - generateUniqueEmail(), logStep(), etc.
3. **Create model classes** - for reusable request/response objects
4. **Add logging** - use LoggerUtil for debugging
5. **Clean up test data** - reset client after tests
6. **Test negative scenarios** - invalid inputs, 404s, errors
7. **Use TestNG features** - DataProvider for data-driven tests
8. **Validate response schema** - check all required fields exist

**📚 More Examples:** See `com/automation/tests/ApiTests.java`, `AdvancedApiTests.java`, `ExampleApiTestWithBase.java`  
**📖 Quick Reference:** `docs/API_QUICK_REFERENCE.md`
