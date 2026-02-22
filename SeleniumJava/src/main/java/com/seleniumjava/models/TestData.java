package com.seleniumjava.models;

/**
 * TestData POJO (Plain Old Java Object)
 * Generic test data container for parameterized testing
 * 
 * Usage: Map CSV/Excel rows to this object for data-driven testing
 */
public class TestData {
    
    private String testName;
    private String description;
    private String inputData;
    private String expectedResult;
    private boolean shouldPass;
    private String priority;
    private String environment;
    
    // Default constructor
    public TestData() {
    }
    
    // Constructor
    public TestData(String testName, String description, String inputData, 
                    String expectedResult, boolean shouldPass) {
        this.testName = testName;
        this.description = description;
        this.inputData = inputData;
        this.expectedResult = expectedResult;
        this.shouldPass = shouldPass;
        this.priority = "MEDIUM";
    }
    
    // Getters and Setters
    public String getTestName() {
        return testName;
    }
    
    public void setTestName(String testName) {
        this.testName = testName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getInputData() {
        return inputData;
    }
    
    public void setInputData(String inputData) {
        this.inputData = inputData;
    }
    
    public String getExpectedResult() {
        return expectedResult;
    }
    
    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }
    
    public boolean isShouldPass() {
        return shouldPass;
    }
    
    public void setShouldPass(boolean shouldPass) {
        this.shouldPass = shouldPass;
    }
    
    public String getPriority() {
        return priority;
    }
    
    public void setPriority(String priority) {
        this.priority = priority;
    }
    
    public String getEnvironment() {
        return environment;
    }
    
    public void setEnvironment(String environment) {
        this.environment = environment;
    }
    
    // Helper methods
    public boolean isHighPriority() {
        return "HIGH".equalsIgnoreCase(priority) || "CRITICAL".equalsIgnoreCase(priority);
    }
    
    @Override
    public String toString() {
        return "TestData{" +
                "testName='" + testName + '\'' +
                ", description='" + description + '\'' +
                ", inputData='" + inputData + '\'' +
                ", expectedResult='" + expectedResult + '\'' +
                ", shouldPass=" + shouldPass +
                ", priority='" + priority + '\'' +
                ", environment='" + environment + '\'' +
                '}';
    }
}
