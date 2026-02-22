package com.seleniumjava.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;
import java.io.File;
import java.util.Map;

/**
 * REST API Client utility for API testing using REST Assured
 * Provides methods for all CRUD operations with authentication support
 */
public class RestApiClient {
    
    private String baseUrl;
    private RequestSpecification requestSpec;
    
    /**
     * Constructor to initialize REST API client with base URL
     * @param baseUrl Base URL for API endpoints
     */
    public RestApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
        RestAssured.baseURI = baseUrl;
        this.requestSpec = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }
    
    /**
     * Get the current request specification
     * @return RequestSpecification
     */
    public RequestSpecification getRequestSpec() {
        return requestSpec;
    }
    
    /**
     * Reset request specification to default
     */
    public void resetRequestSpec() {
        this.requestSpec = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }
    
    // ==================== Authentication Methods ====================
    
    /**
     * Add Bearer token authentication
     * @param token Bearer token
     * @return RestApiClient instance for method chaining
     */
    public RestApiClient addBearerToken(String token) {
        requestSpec.header("Authorization", "Bearer " + token);
        LoggerUtil.info("Added Bearer token authentication");
        return this;
    }
    
    /**
     * Add Basic authentication
     * @param username Username
     * @param password Password
     * @return RestApiClient instance for method chaining
     */
    public RestApiClient addBasicAuth(String username, String password) {
        requestSpec.auth().preemptive().basic(username, password);
        LoggerUtil.info("Added Basic authentication for user: " + username);
        return this;
    }
    
    /**
     * Add API Key authentication
     * @param keyName API key header name
     * @param keyValue API key value
     * @return RestApiClient instance for method chaining
     */
    public RestApiClient addApiKey(String keyName, String keyValue) {
        requestSpec.header(keyName, keyValue);
        LoggerUtil.info("Added API Key: " + keyName);
        return this;
    }
    
    /**
     * Add custom header
     * @param key Header name
     * @param value Header value
     * @return RestApiClient instance for method chaining
     */
    public RestApiClient addHeader(String key, String value) {
        requestSpec.header(key, value);
        LoggerUtil.info("Added header: " + key);
        return this;
    }
    
    /**
     * Add multiple headers
     * @param headers Map of headers
     * @return RestApiClient instance for method chaining
     */
    public RestApiClient addHeaders(Map<String, String> headers) {
        requestSpec.headers(headers);
        LoggerUtil.info("Added " + headers.size() + " headers");
        return this;
    }
    
    /**
     * Add query parameter
     * @param key Parameter name
     * @param value Parameter value
     * @return RestApiClient instance for method chaining
     */
    public RestApiClient addQueryParam(String key, Object value) {
        requestSpec.queryParam(key, value);
        LoggerUtil.info("Added query parameter: " + key + "=" + value);
        return this;
    }
    
    /**
     * Add multiple query parameters
     * @param params Map of query parameters
     * @return RestApiClient instance for method chaining
     */
    public RestApiClient addQueryParams(Map<String, Object> params) {
        requestSpec.queryParams(params);
        LoggerUtil.info("Added " + params.size() + " query parameters");
        return this;
    }
    
    // ==================== GET Methods ====================
    
    /**
     * Execute GET request
     * @param endpoint API endpoint
     * @return Response object
     */
    public Response get(String endpoint) {
        LoggerUtil.info("GET Request - Endpoint: " + endpoint);
        Response response = requestSpec.when().get(endpoint);
        logResponse(response);
        return response;
    }
    
    /**
     * Execute GET request with query parameters
     * @param endpoint API endpoint
     * @param queryParams Query parameters
     * @return Response object
     */
    public Response getWithParams(String endpoint, Map<String, Object> queryParams) {
        LoggerUtil.info("GET Request with params - Endpoint: " + endpoint);
        Response response = requestSpec.queryParams(queryParams).when().get(endpoint);
        logResponse(response);
        return response;
    }
    
    // ==================== POST Methods ====================
    
    /**
     * Execute POST request
     * @param endpoint API endpoint
     * @param body Request body
     * @return Response object
     */
    public Response post(String endpoint, Object body) {
        LoggerUtil.info("POST Request - Endpoint: " + endpoint);
        Response response = requestSpec.body(body).when().post(endpoint);
        logResponse(response);
        return response;
    }
    
    /**
     * Execute POST request without body
     * @param endpoint API endpoint
     * @return Response object
     */
    public Response post(String endpoint) {
        LoggerUtil.info("POST Request (no body) - Endpoint: " + endpoint);
        Response response = requestSpec.when().post(endpoint);
        logResponse(response);
        return response;
    }
    
    // ==================== PUT Methods ====================
    
    /**
     * Execute PUT request
     * @param endpoint API endpoint
     * @param body Request body
     * @return Response object
     */
    public Response put(String endpoint, Object body) {
        LoggerUtil.info("PUT Request - Endpoint: " + endpoint);
        Response response = requestSpec.body(body).when().put(endpoint);
        logResponse(response);
        return response;
    }
    
    // ==================== PATCH Methods ====================
    
    /**
     * Execute PATCH request
     * @param endpoint API endpoint
     * @param body Request body
     * @return Response object
     */
    public Response patch(String endpoint, Object body) {
        LoggerUtil.info("PATCH Request - Endpoint: " + endpoint);
        Response response = requestSpec.body(body).when().patch(endpoint);
        logResponse(response);
        return response;
    }
    
    // ==================== DELETE Methods ====================
    
    /**
     * Execute DELETE request
     * @param endpoint API endpoint
     * @return Response object
     */
    public Response delete(String endpoint) {
        LoggerUtil.info("DELETE Request - Endpoint: " + endpoint);
        Response response = requestSpec.when().delete(endpoint);
        logResponse(response);
        return response;
    }
    
    /**
     * Execute DELETE request with body
     * @param endpoint API endpoint
     * @param body Request body
     * @return Response object
     */
    public Response deleteWithBody(String endpoint, Object body) {
        LoggerUtil.info("DELETE Request with body - Endpoint: " + endpoint);
        Response response = requestSpec.body(body).when().delete(endpoint);
        logResponse(response);
        return response;
    }
    
    // ==================== File Upload ====================
    
    /**
     * Upload file using multipart form data
     * @param endpoint API endpoint
     * @param filePath Path to file
     * @param fileControlName Form field name for file
     * @return Response object
     */
    public Response uploadFile(String endpoint, String filePath, String fileControlName) {
        LoggerUtil.info("File Upload - Endpoint: " + endpoint + ", File: " + filePath);
        File file = new File(filePath);
        Response response = RestAssured.given()
                .multiPart(fileControlName, file)
                .when()
                .post(baseUrl + endpoint);
        logResponse(response);
        return response;
    }
    
    /**
     * Upload file with default control name "file"
     * @param endpoint API endpoint
     * @param filePath Path to file
     * @return Response object
     */
    public Response uploadFile(String endpoint, String filePath) {
        return uploadFile(endpoint, filePath, "file");
    }
    
    // ==================== Helper Methods ====================
    
    /**
     * Set content type for request
     * @param contentType ContentType enum value
     * @return RestApiClient instance for method chaining
     */
    public RestApiClient setContentType(ContentType contentType) {
        requestSpec.contentType(contentType);
        LoggerUtil.info("Set content type: " + contentType);
        return this;
    }
    
    /**
     * Set accept header
     * @param contentType ContentType enum value
     * @return RestApiClient instance for method chaining
     */
    public RestApiClient setAccept(ContentType contentType) {
        requestSpec.accept(contentType);
        LoggerUtil.info("Set accept type: " + contentType);
        return this;
    }
    
    /**
     * Enable request/response logging
     * @return RestApiClient instance for method chaining
     */
    public RestApiClient enableLogging() {
        requestSpec.log().all();
        LoggerUtil.info("Enabled request/response logging");
        return this;
    }
    
    /**
     * Log response details
     * @param response Response object
     */
    private void logResponse(Response response) {
        LoggerUtil.info("Response Status Code: " + response.getStatusCode());
        LoggerUtil.info("Response Time: " + response.getTime() + "ms");
        if (response.getStatusCode() >= 400) {
            LoggerUtil.error("Error Response Body: " + response.getBody().asString());
        }
    }
    
    /**
     * Get base URL
     * @return Base URL
     */
    public String getBaseUrl() {
        return baseUrl;
    }
    
    /**
     * Set new base URL
     * @param baseUrl New base URL
     */
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        RestAssured.baseURI = baseUrl;
        LoggerUtil.info("Updated base URL to: " + baseUrl);
    }
}
