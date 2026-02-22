package com.seleniumjava.listeners;

import com.seleniumjava.utils.ScreenshotUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestNG Listener for capturing test execution events
 * Automatically captures screenshots on test failure
 * 
 * Usage in testng.xml:
 * <listeners>
 *     <listener class-name="com.seleniumjava.listeners.TestListener"/>
 * </listeners>
 * 
 * Usage with @Listeners annotation:
 * @Listeners(TestListener.class)
 * public class YourTestClass extends BaseTest { }
 */
public class TestListener implements ITestListener {

    /**
     * Invoked when test starts
     */
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("▶ Starting Test: " + result.getName());
        System.out.println("  Class: " + result.getTestClass().getName());
        System.out.println("  Thread: " + Thread.currentThread().getName());
    }

    /**
     * Invoked when test succeeds
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✓ Test Passed: " + result.getName());
        System.out.println("  Duration: " + (result.getEndMillis() - result.getStartMillis()) + "ms\n");
    }

    /**
     * Invoked when test fails
     * Automatically captures screenshot
     */
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("✗ Test Failed: " + result.getName());
        System.out.println("  Reason: " + result.getThrowable().getMessage());
        
        // Capture screenshot on failure
        try {
            Object testInstance = result.getInstance();
            
            // Try to get driver from test instance
            WebDriver driver = getDriverFromTestInstance(testInstance);
            
            if (driver != null) {
                String screenshotPath = ScreenshotUtils.captureFailureScreenshot(driver, result.getName());
                System.out.println("  📸 Screenshot saved: " + screenshotPath);
                
                // Attach screenshot to TestNG report
                // result.setAttribute("screenshotPath", screenshotPath);
            } else {
                System.out.println("  ⚠ Could not capture screenshot: WebDriver is null");
            }
        } catch (Exception e) {
            System.out.println("  ⚠ Failed to capture screenshot: " + e.getMessage());
        }
        
        System.out.println();
    }

    /**
     * Invoked when test is skipped
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⊘ Test Skipped: " + result.getName());
        if (result.getThrowable() != null) {
            System.out.println("  Reason: " + result.getThrowable().getMessage());
        }
        System.out.println();
    }

    /**
     * Invoked when test fails due to timeout
     */
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("⚠ Test Failed within Success Percentage: " + result.getName());
    }

    /**
     * Invoked before test suite starts
     */
    @Override
    public void onStart(ITestContext context) {
        System.out.println("\n╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  Starting Test Suite: " + context.getName());
        System.out.println("║  Total Tests: " + context.getAllTestMethods().length);
        System.out.println("║  Parallel: " + context.getSuite().getParallel());
        System.out.println("║  Thread Count: " + context.getSuite().getXmlSuite().getThreadCount());
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝\n");
    }

    /**
     * Invoked after all tests have run
     */
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("\n╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  Test Suite Completed: " + context.getName());
        System.out.println("║  ✓ Passed: " + context.getPassedTests().size());
        System.out.println("║  ✗ Failed: " + context.getFailedTests().size());
        System.out.println("║  ⊘ Skipped: " + context.getSkippedTests().size());
        System.out.println("║  Duration: " + (context.getEndDate().getTime() - context.getStartDate().getTime()) + "ms");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝\n");
    }

    /**
     * Extract WebDriver from test instance using reflection
     */
    private WebDriver getDriverFromTestInstance(Object testInstance) {
        try {
            // Try to get 'driver' field from test class
            java.lang.reflect.Field driverField = testInstance.getClass().getSuperclass().getDeclaredField("driver");
            driverField.setAccessible(true);
            return (WebDriver) driverField.get(testInstance);
        } catch (Exception e) {
            try {
                // Try to get 'driver' field directly from class
                java.lang.reflect.Field driverField = testInstance.getClass().getDeclaredField("driver");
                driverField.setAccessible(true);
                return (WebDriver) driverField.get(testInstance);
            } catch (Exception ex) {
                return null;
            }
        }
    }
}
