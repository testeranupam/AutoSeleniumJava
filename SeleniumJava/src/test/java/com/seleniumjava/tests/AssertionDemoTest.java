package com.seleniumjava.tests;

import com.seleniumjava.base.BaseTest;
import com.seleniumjava.utils.AssertionUtils;
import org.testng.annotations.Test;

/**
 * Demonstration of Hard and Soft Assertions
 * 
 * Hard Assertions: Test fails immediately when assertion fails
 * Soft Assertions: Test continues and reports all failures at the end
 * 
 * Run with: mvn test -Dtest=AssertionDemoTest
 */
public class AssertionDemoTest extends BaseTest {

    @Test(description = "Demonstrate Hard Assertions - Fail Fast")
    public void testHardAssertions() {
        System.out.println("\n=== Hard Assertions Test ===");
        
        // Hard assertions fail immediately
        AssertionUtils.assertTrue(5 > 3, "5 should be greater than 3");
        System.out.println("✓ Assertion 1 passed");
        
        AssertionUtils.assertEquals(10, 10, "Values should be equal");
        System.out.println("✓ Assertion 2 passed");
        
        AssertionUtils.assertNotNull("Hello", "String should not be null");
        System.out.println("✓ Assertion 3 passed");
        
        AssertionUtils.assertContains("Hello World", "World", "String should contain 'World'");
        System.out.println("✓ Assertion 4 passed");
        
        System.out.println("✓ All hard assertions passed!");
    }

    @Test(description = "Demonstrate Soft Assertions - Collect All Failures")
    public void testSoftAssertions() {
        System.out.println("\n=== Soft Assertions Test ===");
        
        // Initialize soft assertions for this test
        AssertionUtils.initSoftAssert();
        
        // All assertions are collected, test doesn't fail immediately
        AssertionUtils.softAssertTrue(5 > 3, "5 should be greater than 3");
        System.out.println("  Assertion 1 executed");
        
        AssertionUtils.softAssertEquals(10, 10, "Values should be equal");
        System.out.println("  Assertion 2 executed");
        
        AssertionUtils.softAssertFalse(false, "Value should be false");
        System.out.println("  Assertion 3 executed");
        
        AssertionUtils.softAssertNull(null, "Value should be null");
        System.out.println("  Assertion 4 executed");
        
        // Report all failures at the end
        System.out.println("✓ All soft assertions executed, checking results...");
        AssertionUtils.assertAll(); // Will fail if any assertion failed
    }

    @Test(description = "Demonstrate Soft Assertions with Failures")
    public void testSoftAssertionsWithFailures() {
        System.out.println("\n=== Soft Assertions with Failures Test ===");
        
        AssertionUtils.initSoftAssert();
        
        // Mix of passing and failing assertions
        AssertionUtils.softAssertTrue(5 > 3, "✓ This will pass");
        AssertionUtils.softAssertEquals(10, 20, "✗ This will fail - values don't match");
        AssertionUtils.softAssertFalse(true, "✗ This will fail - value is true");
        AssertionUtils.softAssertNull("Not Null", "✗ This will fail - value is not null");
        
        System.out.println("  All assertions executed, now reporting...");
        
        // This will report all 3 failures
        AssertionUtils.assertAll();
    }

    @Test(description = "Demonstrate Numeric Assertions")
    public void testNumericAssertions() {
        System.out.println("\n=== Numeric Assertions Test ===");
        
        int score = 85;
        int minPassScore = 40;
        int maxScore = 100;
        
        AssertionUtils.assertGreaterThan(score, minPassScore, "Score should be greater than minimum");
        System.out.println("✓ Score (" + score + ") is greater than minimum (" + minPassScore + ")");
        
        AssertionUtils.assertLessThan(score, maxScore, "Score should be less than maximum");
        System.out.println("✓ Score (" + score + ") is less than maximum (" + maxScore + ")");
        
        AssertionUtils.assertTrue(score >= minPassScore && score <= maxScore, 
            "Score should be within valid range");
        System.out.println("✓ Score is within valid range");
    }

    @Test(description = "Demonstrate String Assertions")
    public void testStringAssertions() {
        System.out.println("\n=== String Assertions Test ===");
        
        String email = "test@example.com";
        String welcomeMessage = "Welcome to Selenium Automation Framework";
        
        AssertionUtils.assertContains(email, "@", "Email should contain @");
        System.out.println("✓ Email contains @");
        
        AssertionUtils.assertContains(email, ".com", "Email should contain domain");
        System.out.println("✓ Email contains domain");
        
        AssertionUtils.assertContains(welcomeMessage, "Selenium", "Message should contain 'Selenium'");
        System.out.println("✓ Welcome message contains 'Selenium'");
        
        AssertionUtils.assertNotNull(email, "Email should not be null");
        AssertionUtils.assertNotEquals(email, "", "Email should not be empty");
        System.out.println("✓ Email is valid (not null or empty)");
    }

    @Test(description = "Compare Hard vs Soft Assertions", enabled = false)
    public void testHardVsSoftComparison() {
        System.out.println("\n=== Hard vs Soft Assertions Comparison ===");
        System.out.println("This test is disabled by default (set enabled=true to run)");
        System.out.println("\nHard Assertion behavior:");
        System.out.println("  - Fails immediately on first failure");
        System.out.println("  - Subsequent assertions are not executed");
        System.out.println("  - Use for critical validations");
        System.out.println("\nSoft Assertion behavior:");
        System.out.println("  - Collects all failures");
        System.out.println("  - All assertions are executed");
        System.out.println("  - Reports all failures at the end");
        System.out.println("  - Use for comprehensive validation");
    }
}
