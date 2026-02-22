package com.seleniumjava.utils;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

/**
 * Assertion Utility Class
 * Handles both Hard Assertions and Soft Assertions
 * 
 * Hard Assertions: Fail immediately on first failure
 * Soft Assertions: Collect all failures and report at end
 */
public class AssertionUtils {
    
    private static ThreadLocal<SoftAssert> softAssert = new ThreadLocal<>();
    
    /**
     * Initialize Soft Assert for the current thread
     * Call this at the beginning of your test
     */
    public static void initSoftAssert() {
        softAssert.set(new SoftAssert());
    }
    
    /**
     * Get Soft Assert instance
     * @return SoftAssert instance
     */
    public static SoftAssert getSoftAssert() {
        if (softAssert.get() == null) {
            initSoftAssert();
        }
        return softAssert.get();
    }
    
    /**
     * Assert all soft assertions and clear
     * Call this at the end of your test
     */
    public static void assertAll() {
        if (softAssert.get() != null) {
            softAssert.get().assertAll();
            softAssert.remove();
        }
    }
    
    // ======================== HARD ASSERTIONS ========================
    
    /**
     * Hard Assert: Assert true (fails immediately)
     */
    public static void assertTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }
    
    /**
     * Hard Assert: Assert false (fails immediately)
     */
    public static void assertFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }
    
    /**
     * Hard Assert: Assert equals (fails immediately)
     */
    public static void assertEquals(Object actual, Object expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }
    
    /**
     * Hard Assert: Assert not equals (fails immediately)
     */
    public static void assertNotEquals(Object actual, Object expected, String message) {
        Assert.assertNotEquals(actual, expected, message);
    }
    
    /**
     * Hard Assert: Assert null (fails immediately)
     */
    public static void assertNull(Object object, String message) {
        Assert.assertNull(object, message);
    }
    
    /**
     * Hard Assert: Assert not null (fails immediately)
     */
    public static void assertNotNull(Object object, String message) {
        Assert.assertNotNull(object, message);
    }
    
    /**
     * Hard Assert: Assert contains (fails immediately)
     */
    public static void assertContains(String actual, String expected, String message) {
        Assert.assertTrue(actual.contains(expected), 
            message + " - Expected: '" + expected + "' in '" + actual + "'");
    }
    
    // ======================== SOFT ASSERTIONS ========================
    
    /**
     * Soft Assert: Assert true (collects failures)
     */
    public static void softAssertTrue(boolean condition, String message) {
        getSoftAssert().assertTrue(condition, message);
    }
    
    /**
     * Soft Assert: Assert false (collects failures)
     */
    public static void softAssertFalse(boolean condition, String message) {
        getSoftAssert().assertFalse(condition, message);
    }
    
    /**
     * Soft Assert: Assert equals (collects failures)
     */
    public static void softAssertEquals(Object actual, Object expected, String message) {
        getSoftAssert().assertEquals(actual, expected, message);
    }
    
    /**
     * Soft Assert: Assert not equals (collects failures)
     */
    public static void softAssertNotEquals(Object actual, Object expected, String message) {
        getSoftAssert().assertNotEquals(actual, expected, message);
    }
    
    /**
     * Soft Assert: Assert null (collects failures)
     */
    public static void softAssertNull(Object object, String message) {
        getSoftAssert().assertNull(object, message);
    }
    
    /**
     * Soft Assert: Assert not null (collects failures)
     */
    public static void softAssertNotNull(Object object, String message) {
        getSoftAssert().assertNotNull(object, message);
    }
    
    /**
     * Soft Assert: Assert contains (collects failures)
     */
    public static void softAssertContains(String actual, String expected, String message) {
        getSoftAssert().assertTrue(actual.contains(expected), 
            message + " - Expected: '" + expected + "' in '" + actual + "'");
    }
    
    // ======================== COMPARISON HELPERS ========================
    
    /**
     * Compare two numbers (greater than)
     */
    public static void assertGreaterThan(int actual, int expected, String message) {
        Assert.assertTrue(actual > expected, 
            message + " - Expected: " + actual + " > " + expected);
    }
    
    /**
     * Compare two numbers (less than)
     */
    public static void assertLessThan(int actual, int expected, String message) {
        Assert.assertTrue(actual < expected, 
            message + " - Expected: " + actual + " < " + expected);
    }
    
    /**
     * Soft Assert: Greater than
     */
    public static void softAssertGreaterThan(int actual, int expected, String message) {
        getSoftAssert().assertTrue(actual > expected, 
            message + " - Expected: " + actual + " > " + expected);
    }
    
    /**
     * Soft Assert: Less than
     */
    public static void softAssertLessThan(int actual, int expected, String message) {
        getSoftAssert().assertTrue(actual < expected, 
            message + " - Expected: " + actual + " < " + expected);
    }
}
