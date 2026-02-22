# API Testing - Quick Reference

## Initialize Client
```java
RestApiClient apiClient = new RestApiClient("https://api.example.com");
apiClient.addBearerToken("token");              // Bearer auth
apiClient.addBasicAuth("user", "pass");          // Basic auth
apiClient.addApiKey("X-API-Key", "key");         // API Key
```

## CRUD Operations
```java
// GET
Response response = apiClient.get("/users");
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
// Status Codes
ApiAssertionUtils.assertSuccess(response);                    // 2xx
ApiAssertionUtils.assertOk(response);                         // 200
ApiAssertionUtils.assertCreated(response);                    // 201
ApiAssertionUtils.assertNotFound(response);                   // 404

// Content & Headers
ApiAssertionUtils.assertContentNotEmpty(response);
ApiAssertionUtils.assertContentContains(response, "text");
ApiAssertionUtils.assertContentTypeJson(response);

// JSON Fields
ApiAssertionUtils.assertJsonFieldExists(response, "data.id");
ApiAssertionUtils.assertJsonFieldValue(response, "name", "John");
ApiAssertionUtils.assertJsonArrayLength(response, "users", 10);

// Performance
ApiAssertionUtils.assertResponseTime(response, 2000);  // Max 2 seconds
```

## Complete Test Example
```java
public class UserApiTests extends BaseApiTest {
    
    @Test
    public void testCreateAndVerifyUser() {
        // Create
        ApiUser newUser = new ApiUser("John", "john@test.com");
        newUser.setUsername(generateUniqueUsername());
        Response response = apiClient.post("/users", newUser);
        
        ApiAssertionUtils.assertCreated(response);
        ApiAssertionUtils.assertJsonFieldExists(response, "id");
        
        // Verify
        int userId = response.jsonPath().getInt("id");
        Response getResponse = apiClient.get("/users/" + userId);
        
        ApiAssertionUtils.assertOk(getResponse);
        ApiAssertionUtils.assertJsonFieldValue(getResponse, "name", "John");
    }
}
```

## Configuration
```properties
# src/test/resources/config/config.properties
api.baseurl=https://api.example.com
api.token=your_bearer_token
```

```java
// Read in test
String baseUrl = ConfigManager.getProperty("api.baseurl");
```

## Run Tests
```bash
mvn test -Dtest=ApiTests                         # All API tests
mvn test -Dtest=AdvancedApiTests                 # Specific class
mvn clean test                                    # Clean and test
```

## Response Properties
```java
response.getStatusCode()        // HTTP status code
response.getBody().asString()   // Response body as string
response.jsonPath().get("id")   // Extract JSON field
response.getTime()              // Request duration in ms
response.getHeaders()           // All headers
```

## BaseApiTest Helper Methods
```java
generateUniqueEmail()           // test_20240219120530@example.com
generateUniqueUsername()        // user_20240219120530
generateUUID()                  // Random UUID
generateTestString("prefix")    // prefix_20240219120530
generateRandomInt(1, 100)       // Random int between 1-100
getCurrentTimestamp()           // 2024-02-19 12:05:30
logStep("Step description")     // Log test step
```

**📚 Full Examples:** See `ApiTests.java`, `AdvancedApiTests.java`, `ExampleApiTestWithBase.java`  
**📖 Complete Guide:** `docs/API_TESTING_GUIDE.md`
