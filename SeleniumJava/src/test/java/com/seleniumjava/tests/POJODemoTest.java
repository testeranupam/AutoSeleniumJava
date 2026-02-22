package com.seleniumjava.tests;

import com.seleniumjava.base.BaseTest;
import com.seleniumjava.models.*;
import com.seleniumjava.utils.*;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

/**
 * POJO (Plain Old Java Object) Demo Test
 * Demonstrates how to use POJOs with test automation framework
 * 
 * Features Demonstrated:
 * 1. Map to POJO conversion
 * 2. CSV to POJO conversion
 * 3. POJO to Map conversion
 * 4. POJO validation
 * 5. POJO comparison
 * 6. POJO cloning
 * 7. Data-driven testing with POJOs
 * 
 * Run: mvn test -Dtest=POJODemoTest
 */
public class POJODemoTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(POJODemoTest.class);
    
    /**
     * Test 1: Create POJO using constructors
     */
    @Test(description = "Create POJOs using different constructors")
    public void testPOJOCreation() {
        logger.info("═══════════════════════════════════════════════════════");
        logger.info("TEST: POJO Creation");
        logger.info("═══════════════════════════════════════════════════════");
        
        // Create User with all fields constructor
        User user1 = new User("testuser", "Pass123", "test@example.com", 
                              "Test", "User", 25, true, "USER");
        logger.info("Created user with all fields: " + user1);
        
        // Create User with essential fields constructor
        User user2 = new User("simpleuser", "Pass456", "simple@example.com");
        logger.info("Created user with essential fields: " + user2);
        
        // Create User with default constructor and setters
        User user3 = new User();
        user3.setUsername("manualuser");
        user3.setPassword("Pass789");
        user3.setEmail("manual@example.com");
        user3.setFirstName("Manual");
        user3.setAge(30);
        logger.info("Created user with setters: " + user3);
        
        // Create Product
        Product product = new Product("P001", "Laptop", 899.99, 10);
        product.setCategory("Electronics");
        product.setBrand("Dell");
        product.setRating(4.5);
        logger.info("Created product: " + product);
        
        // Test helper methods
        logger.info("User full name: " + user1.getFullName());
        logger.info("User is admin: " + user1.isAdmin());
        logger.info("Product total value: $" + product.getTotalValue());
        logger.info("Product discounted price (10% off): " + product.getFormattedPrice());
        logger.info("Product is highly rated: " + product.isHighlyRated());
        
        AssertionUtils.assertTrue(user1.getUsername().equals("testuser"), "Username verification");
        AssertionUtils.assertTrue(product.getTotalValue() == 8999.90, "Product value calculation");
        
        logger.info("✓ TEST PASSED: POJO creation successful");
        logger.info("═══════════════════════════════════════════════════════\n");
    }
    
    /**
     * Test 2: Convert Map to POJO
     */
    @Test(description = "Convert Map data to POJO objects")
    public void testMapToPOJO() {
        logger.info("═══════════════════════════════════════════════════════");
        logger.info("TEST: Map to POJO Conversion");
        logger.info("═══════════════════════════════════════════════════════");
        
        // Create data map (simulates data from CSV/Excel/Database)
        Map<String, String> userData = new LinkedHashMap<>();
        userData.put("username", "mapuser");
        userData.put("password", "MapPass123");
        userData.put("email", "mapuser@example.com");
        userData.put("firstName", "Map");
        userData.put("lastName", "User");
        userData.put("age", "28");
        userData.put("active", "true");
        userData.put("role", "ADMIN");
        
        logger.info("Source Map: " + userData);
        
        // Convert Map to User POJO
        User user = POJOUtils.mapToPOJO(userData, User.class);
        
        logger.info("Converted POJO:");
        POJOUtils.printPOJO(user);
        
        // Verify conversion
        AssertionUtils.assertEquals(user.getUsername(), "mapuser", "Username from map");
        AssertionUtils.assertEquals(user.getAge(), 28, "Age conversion");
        AssertionUtils.assertTrue(user.isActive(), "Active boolean conversion");
        AssertionUtils.assertTrue(user.isAdmin(), "Role conversion");
        
        logger.info("✓ TEST PASSED: Map to POJO conversion successful");
        logger.info("═══════════════════════════════════════════════════════\n");
    }
    
    /**
     * Test 3: Convert POJO to Map
     */
    @Test(description = "Convert POJO objects to Map")
    public void testPOJOToMap() {
        logger.info("═══════════════════════════════════════════════════════");
        logger.info("TEST: POJO to Map Conversion");
        logger.info("═══════════════════════════════════════════════════════");
        
        // Create User POJO
        User user = new User("pojouser", "PojoPass123", "pojo@example.com", 
                            "Pojo", "User", 32, true, "USER");
        
        logger.info("Source POJO: " + user);
        
        // Convert POJO to Map
        Map<String, String> userMap = POJOUtils.pojoToMap(user);
        
        logger.info("\nConverted to Map:");
        for (Map.Entry<String, String> entry : userMap.entrySet()) {
            logger.info("  " + entry.getKey() + " = " + entry.getValue());
        }
        
        // Verify map contains all fields
        AssertionUtils.assertTrue(userMap.containsKey("username"), "Map contains username");
        AssertionUtils.assertEquals(userMap.get("username"), "pojouser", "Username in map");
        AssertionUtils.assertEquals(userMap.get("age"), "32", "Age in map");
        
        logger.info("✓ TEST PASSED: POJO to Map conversion successful");
        logger.info("═══════════════════════════════════════════════════════\n");
    }
    
    /**
     * Test 4: Load POJOs from CSV file
     */
    @Test(description = "Load POJO objects from CSV files")
    public void testCSVToPOJO() {
        logger.info("═══════════════════════════════════════════════════════");
        logger.info("TEST: CSV to POJO Conversion");
        logger.info("═══════════════════════════════════════════════════════");
        
        // Load Users from CSV
        String usersCsvPath = "src/test/resources/testdata/users.csv";
        List<User> users = POJOUtils.csvToPOJOList(usersCsvPath, User.class);
        
        logger.info("\n📄 Loaded " + users.size() + " users from CSV:");
        for (User user : users) {
            logger.info("  → " + user.getUsername() + " (" + user.getEmail() + ") - Role: " + user.getRole());
        }
        
        // Load Products from CSV
        String productsCsvPath = "src/test/resources/testdata/products.csv";
        List<Product> products = POJOUtils.csvToPOJOList(productsCsvPath, Product.class);
        
        logger.info("\n📦 Loaded " + products.size() + " products from CSV:");
        for (Product product : products) {
            logger.info("  → " + product.getName() + " ($" + product.getPrice() + ") - Stock: " + product.getQuantity());
        }
        
        // Verify data loaded correctly
        AssertionUtils.assertTrue(users.size() > 0, "Users loaded from CSV");
        AssertionUtils.assertTrue(products.size() > 0, "Products loaded from CSV");
        
        // Verify first user
        User firstUser = users.get(0);
        AssertionUtils.assertEquals(firstUser.getUsername(), "john.doe", "First user username");
        AssertionUtils.assertEquals(firstUser.getFirstName(), "John", "First user first name");
        
        // Verify first product
        Product firstProduct = products.get(0);
        AssertionUtils.assertEquals(firstProduct.getProductId(), "P001", "First product ID");
        AssertionUtils.assertTrue(firstProduct.getPrice() > 0, "Product has valid price");
        
        logger.info("✓ TEST PASSED: CSV to POJO loading successful");
        logger.info("═══════════════════════════════════════════════════════\n");
    }
    
    /**
     * Test 5: POJO Validation
     */
    @Test(description = "Validate POJO required fields")
    public void testPOJOValidation() {
        logger.info("═══════════════════════════════════════════════════════");
        logger.info("TEST: POJO Validation");
        logger.info("═══════════════════════════════════════════════════════");
        
        // Valid user
        User validUser = new User("validuser", "Pass123", "valid@example.com");
        validUser.setFirstName("Valid");
        
        boolean isValid = POJOUtils.validatePOJO(validUser, "username", "password", "email");
        logger.info("Valid user validation: " + (isValid ? "✓ PASSED" : "✗ FAILED"));
        AssertionUtils.assertTrue(isValid, "Valid user should pass validation");
        
        // Invalid user (missing required fields)
        User invalidUser = new User();
        invalidUser.setUsername("invaliduser");
        // Missing password and email
        
        boolean isInvalid = POJOUtils.validatePOJO(invalidUser, "username", "password", "email");
        logger.info("Invalid user validation: " + (isInvalid ? "✗ PASSED (SHOULD FAIL)" : "✓ FAILED AS EXPECTED"));
        AssertionUtils.assertFalse(isInvalid, "Invalid user should fail validation");
        
        logger.info("✓ TEST PASSED: POJO validation working correctly");
        logger.info("═══════════════════════════════════════════════════════\n");
    }
    
    /**
     * Test 6: POJO Comparison
     */
    @Test(description = "Compare two POJO objects")
    public void testPOJOComparison() {
        logger.info("═══════════════════════════════════════════════════════");
        logger.info("TEST: POJO Comparison");
        logger.info("═══════════════════════════════════════════════════════");
        
        // Create two similar users with some differences
        User user1 = new User("compareuser", "Pass123", "compare@example.com", 
                             "Compare", "User", 30, true, "USER");
        
        User user2 = new User("compareuser", "DifferentPass", "compare@example.com", 
                             "Compare", "Smith", 35, true, "ADMIN");
        
        logger.info("User 1: " + user1);
        logger.info("User 2: " + user2);
        
        // Compare POJOs
        Map<String, String[]> differences = POJOUtils.comparePOJOs(user1, user2);
        
        logger.info("\n🔍 Differences found: " + differences.size());
        for (Map.Entry<String, String[]> diff : differences.entrySet()) {
            logger.info("  Field: " + diff.getKey());
            logger.info("    User1: " + diff.getValue()[0]);
            logger.info("    User2: " + diff.getValue()[1]);
        }
        
        AssertionUtils.assertTrue(differences.size() > 0, "Differences detected");
        AssertionUtils.assertTrue(differences.containsKey("password"), "Password difference detected");
        AssertionUtils.assertTrue(differences.containsKey("lastName"), "LastName difference detected");
        
        logger.info("✓ TEST PASSED: POJO comparison successful");
        logger.info("═══════════════════════════════════════════════════════\n");
    }
    
    /**
     * Test 7: POJO Cloning
     */
    @Test(description = "Clone POJO objects")
    public void testPOJOCloning() {
        logger.info("═══════════════════════════════════════════════════════");
        logger.info("TEST: POJO Cloning");
        logger.info("═══════════════════════════════════════════════════════");
        
        // Create original user
        User original = new User("original", "Pass123", "original@example.com", 
                                "Original", "User", 25, true, "USER");
        
        logger.info("Original User: " + original);
        
        // Clone user
        User clone = POJOUtils.clonePOJO(original);
        
        logger.info("Cloned User: " + clone);
        
        // Verify clone has same values
        AssertionUtils.assertEquals(clone.getUsername(), original.getUsername(), "Cloned username");
        AssertionUtils.assertEquals(clone.getEmail(), original.getEmail(), "Cloned email");
        AssertionUtils.assertEquals(clone.getAge(), original.getAge(), "Cloned age");
        
        // Modify clone
        clone.setUsername("modified");
        clone.setAge(30);
        
        logger.info("\nAfter modifying clone:");
        logger.info("Original User: " + original);
        logger.info("Modified Clone: " + clone);
        
        // Verify original is not affected
        AssertionUtils.assertNotEquals(original.getUsername(), clone.getUsername(), 
                                      "Original not affected by clone modification");
        
        logger.info("✓ TEST PASSED: POJO cloning successful");
        logger.info("═══════════════════════════════════════════════════════\n");
    }
    
    /**
     * Test 8: Data-Driven Testing with POJOs
     * Uses POJO objects instead of Maps for cleaner, type-safe testing
     */
    @Test(description = "Data-driven testing using POJO objects")
    public void testDataDrivenWithPOJOs() {
        logger.info("═══════════════════════════════════════════════════════");
        logger.info("TEST: Data-Driven Testing with POJOs");
        logger.info("═══════════════════════════════════════════════════════");
        
        // Load test data from CSV as POJOs
        String csvPath = "src/test/resources/testdata/users.csv";
        List<User> testUsers = POJOUtils.csvToPOJOList(csvPath, User.class);
        
        logger.info("Running tests for " + testUsers.size() + " users:\n");
        
        int passCount = 0;
        int failCount = 0;
        
        // Execute test for each user
        for (User user : testUsers) {
            logger.info("──────────────────────────────────────────────────────");
            logger.info("Testing User: " + user.getUsername());
            
            // Simulate test logic
            boolean loginSuccess = simulateLogin(user);
            
            if (loginSuccess) {
                logger.info("  ✓ Login test PASSED for: " + user.getUsername());
                passCount++;
            } else {
                logger.info("  ✗ Login test FAILED for: " + user.getUsername());
                failCount++;
            }
        }
        
        logger.info("──────────────────────────────────────────────────────");
        logger.info("\n📊 Test Summary:");
        logger.info("  Total Tests: " + testUsers.size());
        logger.info("  Passed: " + passCount);
        logger.info("  Failed: " + failCount);
        
        AssertionUtils.assertTrue(passCount > 0, "At least some tests should pass");
        
        logger.info("✓ TEST PASSED: Data-driven testing with POJOs successful");
        logger.info("═══════════════════════════════════════════════════════\n");
    }
    
    /**
     * Test 9: Print POJO List
     */
    @Test(description = "Print formatted POJO lists")
    public void testPOJOListPrinting() {
        logger.info("═══════════════════════════════════════════════════════");
        logger.info("TEST: POJO List Printing");
        logger.info("═══════════════════════════════════════════════════════\n");
        
        // Load products
        String productsPath = "src/test/resources/testdata/products.csv";
        List<Product> products = POJOUtils.csvToPOJOList(productsPath, Product.class);
        
        // Print formatted list
        POJOUtils.printPOJOList(products);
        
        logger.info("✓ TEST PASSED: POJO list printing successful");
        logger.info("═══════════════════════════════════════════════════════\n");
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // HELPER METHODS
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Simulate login operation using User POJO
     */
    private boolean simulateLogin(User user) {
        // Simulate validation logic
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            logger.info("    → Failed: Empty username");
            return false;
        }
        
        if (user.getPassword() == null || user.getPassword().length() < 6) {
            logger.info("    → Failed: Invalid password");
            return false;
        }
        
        if (!user.isActive()) {
            logger.info("    → Failed: User account is inactive");
            return false;
        }
        
        logger.info("    → Success: Credentials valid, user active");
        return true;
    }
}
