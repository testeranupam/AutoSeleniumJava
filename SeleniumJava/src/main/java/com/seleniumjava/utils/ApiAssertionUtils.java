package com.seleniumjava.utils;

import io.restassured.response.Response;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.Assert;
import java.io.File;
import java.util.Map;

/**
 * API Assertion utility for validating REST API responses
 * Provides comprehensive assertion methods for status codes, headers, content, JSON validation
 */
public class ApiAssertionUtils {
    
    // ==================== Status Code Assertions ====================
    
    /**
     * Assert response status code
     * @param response Response object
     * @param expectedStatusCode Expected status code
     */
    public static void assertStatusCode(Response response, int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(actualStatusCode, expectedStatusCode,
                "Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
        LoggerUtil.info("Status code verification passed: " + actualStatusCode);
    }
    
    /**
     * Assert response is successful (2xx)
     * @param response Response object
     */
    public static void assertSuccess(Response response) {
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode >= 200 && statusCode < 300,
                "Expected success status code (2xx) but got: " + statusCode);
        LoggerUtil.info("Success status code verification passed: " + statusCode);
    }
    
    /**
     * Assert status code is 200 OK
     * @param response Response object
     */
    public static void assertOk(Response response) {
        assertStatusCode(response, 200);
    }
    
    /**
     * Assert status code is 201 Created
     * @param response Response object
     */
    public static void assertCreated(Response response) {
        assertStatusCode(response, 201);
    }
    
    /**
     * Assert status code is 204 No Content
     * @param response Response object
     */
    public static void assertNoContent(Response response) {
        assertStatusCode(response, 204);
    }
    
    /**
     * Assert status code is 400 Bad Request
     * @param response Response object
     */
    public static void assertBadRequest(Response response) {
        assertStatusCode(response, 400);
    }
    
    /**
     * Assert status code is 401 Unauthorized
     * @param response Response object
     */
    public static void assertUnauthorized(Response response) {
        assertStatusCode(response, 401);
    }
    
    /**
     * Assert status code is 403 Forbidden
     * @param response Response object
     */
    public static void assertForbidden(Response response) {
        assertStatusCode(response, 403);
    }
    
    /**
     * Assert status code is 404 Not Found
     * @param response Response object
     */
    public static void assertNotFound(Response response) {
        assertStatusCode(response, 404);
    }
    
    /**
     * Assert status code is 500 Internal Server Error
     * @param response Response object
     */
    public static void assertInternalServerError(Response response) {
        assertStatusCode(response, 500);
    }
    
    // ==================== Content Assertions ====================
    
    /**
     * Assert response body is not empty
     * @param response Response object
     */
    public static void assertContentNotEmpty(Response response) {
        String content = response.getBody().asString();
        Assert.assertFalse(content == null || content.isEmpty(),
                "Response body should not be empty");
        LoggerUtil.info("Content not empty verification passed");
    }
    
    /**
     * Assert response body contains specific text
     * @param response Response object
     * @param expectedText Text to search for
     */
    public static void assertContentContains(Response response, String expectedText) {
        String content = response.getBody().asString();
        Assert.assertTrue(content.contains(expectedText),
                "Response body should contain: " + expectedText);
        LoggerUtil.info("Content contains verification passed for: " + expectedText);
    }
    
    /**
     * Assert response body does not contain specific text
     * @param response Response object
     * @param unexpectedText Text that should not be present
     */
    public static void assertContentNotContains(Response response, String unexpectedText) {
        String content = response.getBody().asString();
        Assert.assertFalse(content.contains(unexpectedText),
                "Response body should not contain: " + unexpectedText);
        LoggerUtil.info("Content not contains verification passed");
    }
    
    /**
     * Assert response body equals expected content
     * @param response Response object
     * @param expectedContent Expected content
     */
    public static void assertContentEquals(Response response, String expectedContent) {
        String actualContent = response.getBody().asString();
        Assert.assertEquals(actualContent, expectedContent,
                "Response body does not match expected content");
        LoggerUtil.info("Content equals verification passed");
    }
    
    // ==================== Header Assertions ====================
    
    /**
     * Assert response has specific header
     * @param response Response object
     * @param headerName Header name
     */
    public static void assertHasHeader(Response response, String headerName) {
        String headerValue = response.getHeader(headerName);
        Assert.assertNotNull(headerValue,
                "Response should have header: " + headerName);
        LoggerUtil.info("Header exists verification passed: " + headerName);
    }
    
    /**
     * Assert header value matches expected value
     * @param response Response object
     * @param headerName Header name
     * @param expectedValue Expected header value
     */
    public static void assertHeaderValue(Response response, String headerName, String expectedValue) {
        String actualValue = response.getHeader(headerName);
        Assert.assertEquals(actualValue, expectedValue,
                "Header " + headerName + " value mismatch");
        LoggerUtil.info("Header value verification passed: " + headerName + "=" + expectedValue);
    }
    
    /**
     * Assert Content-Type is application/json
     * @param response Response object
     */
    public static void assertContentTypeJson(Response response) {
        String contentType = response.getContentType();
        Assert.assertTrue(contentType != null && contentType.contains("application/json"),
                "Expected Content-Type: application/json but got: " + contentType);
        LoggerUtil.info("Content-Type JSON verification passed");
    }
    
    /**
     * Assert Content-Type is XML
     * @param response Response object
     */
    public static void assertContentTypeXml(Response response) {
        String contentType = response.getContentType();
        Assert.assertTrue(contentType != null && 
                (contentType.contains("application/xml") || contentType.contains("text/xml")),
                "Expected Content-Type: XML but got: " + contentType);
        LoggerUtil.info("Content-Type XML verification passed");
    }
    
    // ==================== JSON Path Assertions ====================
    
    /**
     * Assert JSON field exists in response
     * @param response Response object
     * @param jsonPath JSON path expression
     */
    public static void assertJsonFieldExists(Response response, String jsonPath) {
        Object value = response.jsonPath().get(jsonPath);
        Assert.assertNotNull(value,
                "JSON field should exist: " + jsonPath);
        LoggerUtil.info("JSON field exists verification passed: " + jsonPath);
    }
    
    /**
     * Assert JSON field value matches expected value
     * @param response Response object
     * @param jsonPath JSON path expression
     * @param expectedValue Expected value
     */
    public static void assertJsonFieldValue(Response response, String jsonPath, Object expectedValue) {
        Object actualValue = response.jsonPath().get(jsonPath);
        Assert.assertEquals(actualValue, expectedValue,
                "JSON field " + jsonPath + " value mismatch");
        LoggerUtil.info("JSON field value verification passed: " + jsonPath + "=" + expectedValue);
    }
    
    /**
     * Assert JSON field is not null
     * @param response Response object
     * @param jsonPath JSON path expression
     */
    public static void assertJsonFieldNotNull(Response response, String jsonPath) {
        Object value = response.jsonPath().get(jsonPath);
        Assert.assertNotNull(value,
                "JSON field should not be null: " + jsonPath);
        LoggerUtil.info("JSON field not null verification passed: " + jsonPath);
    }
    
    /**
     * Assert JSON array length
     * @param response Response object
     * @param jsonPath JSON path to array
     * @param expectedLength Expected array length
     */
    public static void assertJsonArrayLength(Response response, String jsonPath, int expectedLength) {
        int actualLength = response.jsonPath().getList(jsonPath).size();
        Assert.assertEquals(actualLength, expectedLength,
                "JSON array length mismatch at: " + jsonPath);
        LoggerUtil.info("JSON array length verification passed: " + jsonPath + " length=" + expectedLength);
    }
    
    /**
     * Assert JSON array is not empty
     * @param response Response object
     * @param jsonPath JSON path to array
     */
    public static void assertJsonArrayNotEmpty(Response response, String jsonPath) {
        int length = response.jsonPath().getList(jsonPath).size();
        Assert.assertTrue(length > 0,
                "JSON array should not be empty: " + jsonPath);
        LoggerUtil.info("JSON array not empty verification passed: " + jsonPath);
    }
    
    /**
     * Assert JSON array contains specific value
     * @param response Response object
     * @param jsonPath JSON path to array
     * @param expectedValue Value to check for
     */
    public static void assertJsonArrayContains(Response response, String jsonPath, Object expectedValue) {
        boolean contains = response.jsonPath().getList(jsonPath).contains(expectedValue);
        Assert.assertTrue(contains,
                "JSON array should contain value: " + expectedValue + " at path: " + jsonPath);
        LoggerUtil.info("JSON array contains verification passed");
    }
    
    // ==================== JSON Schema Validation ====================
    
    /**
     * Validate response against JSON schema file
     * @param response Response object
     * @param schemaFilePath Path to JSON schema file
     */
    public static void assertJsonSchema(Response response, String schemaFilePath) {
        File schemaFile = new File(schemaFilePath);
        Assert.assertTrue(schemaFile.exists(), 
                "Schema file not found: " + schemaFilePath);
        
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(schemaFile));
        LoggerUtil.info("JSON schema validation passed");
    }
    
    /**
     * Validate response against JSON schema string
     * @param response Response object
     * @param schemaString JSON schema as string
     */
    public static void assertJsonSchemaString(Response response, String schemaString) {
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaString));
        LoggerUtil.info("JSON schema validation passed");
    }
    
    // ==================== Performance Assertions ====================
    
    /**
     * Assert response time is less than specified milliseconds
     * @param response Response object
     * @param maxResponseTime Maximum acceptable response time in milliseconds
     */
    public static void assertResponseTime(Response response, long maxResponseTime) {
        long actualTime = response.getTime();
        Assert.assertTrue(actualTime <= maxResponseTime,
                "Response time exceeded: Expected <= " + maxResponseTime + "ms but got " + actualTime + "ms");
        LoggerUtil.info("Response time verification passed: " + actualTime + "ms");
    }
    
    /**
     * Assert response time is within range
     * @param response Response object
     * @param minTime Minimum expected time
     * @param maxTime Maximum expected time
     */
    public static void assertResponseTimeInRange(Response response, long minTime, long maxTime) {
        long actualTime = response.getTime();
        Assert.assertTrue(actualTime >= minTime && actualTime <= maxTime,
                "Response time out of range: Expected " + minTime + "-" + maxTime + "ms but got " + actualTime + "ms");
        LoggerUtil.info("Response time range verification passed: " + actualTime + "ms");
    }
    
    // ==================== Cookie Assertions ====================
    
    /**
     * Assert response has specific cookie
     * @param response Response object
     * @param cookieName Cookie name
     */
    public static void assertHasCookie(Response response, String cookieName) {
        String cookieValue = response.getCookie(cookieName);
        Assert.assertNotNull(cookieValue,
                "Response should have cookie: " + cookieName);
        LoggerUtil.info("Cookie exists verification passed: " + cookieName);
    }
    
    /**
     * Assert cookie value matches expected value
     * @param response Response object
     * @param cookieName Cookie name
     * @param expectedValue Expected cookie value
     */
    public static void assertCookieValue(Response response, String cookieName, String expectedValue) {
        String actualValue = response.getCookie(cookieName);
        Assert.assertEquals(actualValue, expectedValue,
                "Cookie " + cookieName + " value mismatch");
        LoggerUtil.info("Cookie value verification passed: " + cookieName);
    }
    
    // ==================== Multiple Assertions ====================
    
    /**
     * Assert multiple JSON fields and values at once
     * @param response Response object
     * @param expectedFields Map of JSON paths and expected values
     */
    public static void assertMultipleJsonFields(Response response, Map<String, Object> expectedFields) {
        for (Map.Entry<String, Object> entry : expectedFields.entrySet()) {
            assertJsonFieldValue(response, entry.getKey(), entry.getValue());
        }
        LoggerUtil.info("Multiple JSON fields verification passed");
    }
    
    /**
     * Assert response is successful and content type is JSON
     * @param response Response object
     */
    public static void assertSuccessJsonResponse(Response response) {
        assertSuccess(response);
        assertContentTypeJson(response);
        assertContentNotEmpty(response);
        LoggerUtil.info("Success JSON response verification passed");
    }
}
