package com.seleniumjava.tests;

import com.seleniumjava.utils.SQLUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Demonstration of SQL Utility for Database Operations
 * 
 * This test requires a database connection.
 * Update connection details in @BeforeClass method.
 * 
 * Supported Databases:
 * - MySQL: jdbc:mysql://localhost:3306/dbname
 * - PostgreSQL: jdbc:postgresql://localhost:5432/dbname
 * - SQLite: jdbc:sqlite:path/to/database.db
 * - SQL Server: jdbc:sqlserver://localhost:1433;databaseName=dbname
 * - Oracle: jdbc:oracle:thin:@localhost:1521:dbname
 * 
 * Run with: mvn test -Dtest=SQLDemoTest
 */
public class SQLDemoTest {

    private SQLUtils sql;
    
    // Update these connection details for your database
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    @BeforeClass
    public void setupDatabase() {
        System.out.println("\n=== SQL Utility Demo Tests ===");
        System.out.println("Note: Update connection details in SQLDemoTest.java");
        sql = new SQLUtils(JDBC_URL, USERNAME, PASSWORD);
    }

    @AfterClass
    public void closeDatabase() {
        if (sql != null) {
            sql.close();
        }
    }

    @Test(description = "Test database connection", enabled = false)
    public void testDatabaseConnection() throws SQLException {
        System.out.println("\n--- Test 1: Database Connection ---");
        
        sql.connect();
        
        // Get database info
        Map<String, String> dbInfo = sql.getDatabaseInfo();
        System.out.println("Database Product: " + dbInfo.get("DatabaseProductName"));
        System.out.println("Database Version: " + dbInfo.get("DatabaseProductVersion"));
        System.out.println("Driver: " + dbInfo.get("DriverName"));
        System.out.println("Connected User: " + dbInfo.get("UserName"));
        
        System.out.println("✓ Connection successful!");
    }

    @Test(description = "Execute SELECT query", enabled = false)
    public void testSelectQuery() throws SQLException {
        System.out.println("\n--- Test 2: SELECT Query ---");
        
        String query = "SELECT * FROM users LIMIT 5";
        List<Map<String, String>> results = sql.executeQuery(query);
        
        System.out.println("Found " + results.size() + " records");
        sql.printResults(results);
        
        // Access individual values
        if (!results.isEmpty()) {
            Map<String, String> firstRow = results.get(0);
            System.out.println("\nFirst user ID: " + firstRow.get("id"));
            System.out.println("First user name: " + firstRow.get("name"));
        }
    }

    @Test(description = "Execute query with parameters", enabled = false)
    public void testPreparedStatement() throws SQLException {
        System.out.println("\n--- Test 3: Prepared Statement ---");
        
        String query = "SELECT * FROM users WHERE id = ? AND status = ?";
        List<Map<String, String>> results = sql.executeQuery(query, 1, "active");
        
        System.out.println("Found " + results.size() + " records");
        sql.printResults(results);
    }

    @Test(description = "Insert new record", enabled = false)
    public void testInsertRecord() throws SQLException {
        System.out.println("\n--- Test 4: INSERT Record ---");
        
        Map<String, Object> values = new HashMap<>();
        values.put("name", "Test User");
        values.put("email", "test@example.com");
        values.put("age", 25);
        values.put("status", "active");
        
        int rowsAffected = sql.insertRecord("users", values);
        System.out.println("Inserted " + rowsAffected + " record(s)");
        
        // Verify insertion
        String query = "SELECT * FROM users WHERE email = ?";
        List<Map<String, String>> results = sql.executeQuery(query, "test@example.com");
        sql.printResults(results);
    }

    @Test(description = "Update existing record", enabled = false)
    public void testUpdateRecord() throws SQLException {
        System.out.println("\n--- Test 5: UPDATE Record ---");
        
        Map<String, Object> values = new HashMap<>();
        values.put("name", "Updated Name");
        values.put("age", 30);
        
        String condition = "email = 'test@example.com'";
        int rowsAffected = sql.updateRecord("users", values, condition);
        System.out.println("Updated " + rowsAffected + " record(s)");
        
        // Verify update
        String query = "SELECT * FROM users WHERE email = 'test@example.com'";
        List<Map<String, String>> results = sql.executeQuery(query);
        sql.printResults(results);
    }

    @Test(description = "Delete record", enabled = false)
    public void testDeleteRecord() throws SQLException {
        System.out.println("\n--- Test 6: DELETE Record ---");
        
        int beforeCount = sql.getRowCount("users");
        System.out.println("Records before delete: " + beforeCount);
        
        int rowsAffected = sql.deleteRecords("users", "email = 'test@example.com'");
        System.out.println("Deleted " + rowsAffected + " record(s)");
        
        int afterCount = sql.getRowCount("users");
        System.out.println("Records after delete: " + afterCount);
    }

    @Test(description = "Check if record exists", enabled = false)
    public void testRecordExists() throws SQLException {
        System.out.println("\n--- Test 7: Record Exists Check ---");
        
        String query = "SELECT * FROM users WHERE id = ?";
        boolean exists = sql.recordExists(query, 1);
        
        System.out.println("User with ID 1 exists: " + exists);
        
        if (exists) {
            System.out.println("✓ Record found");
        } else {
            System.out.println("✗ Record not found");
        }
    }

    @Test(description = "Get single value", enabled = false)
    public void testGetSingleValue() throws SQLException {
        System.out.println("\n--- Test 8: Get Single Value ---");
        
        String query = "SELECT COUNT(*) FROM users";
        String count = sql.getSingleValue(query);
        System.out.println("Total users: " + count);
        
        String nameQuery = "SELECT name FROM users WHERE id = ?";
        String name = sql.getSingleValue(nameQuery, 1);
        System.out.println("User name for ID 1: " + name);
    }

    @Test(description = "Get row count", enabled = false)
    public void testRowCount() throws SQLException {
        System.out.println("\n--- Test 9: Row Count ---");
        
        int totalCount = sql.getRowCount("users");
        System.out.println("Total records: " + totalCount);
        
        int activeCount = sql.getRowCount("users", "status = 'active'");
        System.out.println("Active users: " + activeCount);
    }

    @Test(description = "Batch operations", enabled = false)
    public void testBatchOperations() throws SQLException {
        System.out.println("\n--- Test 10: Batch Operations ---");
        
        String query = "INSERT INTO users (name, email, age) VALUES (?, ?, ?)";
        
        List<Object[]> batchParams = List.of(
            new Object[]{"User 1", "user1@example.com", 25},
            new Object[]{"User 2", "user2@example.com", 30},
            new Object[]{"User 3", "user3@example.com", 35}
        );
        
        int[] results = sql.executeBatch(query, batchParams);
        System.out.println("Batch executed: " + results.length + " statements");
        
        // Verify batch insertion
        String selectQuery = "SELECT * FROM users WHERE email LIKE 'user%@example.com'";
        List<Map<String, String>> inserted = sql.executeQuery(selectQuery);
        System.out.println("Inserted records:");
        sql.printResults(inserted);
    }

    @Test(description = "Transaction management", enabled = false)
    public void testTransactions() throws SQLException {
        System.out.println("\n--- Test 11: Transaction Management ---");
        
        try {
            // Begin transaction
            sql.beginTransaction();
            
            // Insert first record
            Map<String, Object> user1 = new HashMap<>();
            user1.put("name", "Transaction User 1");
            user1.put("email", "txn1@example.com");
            sql.insertRecord("users", user1);
            
            // Insert second record
            Map<String, Object> user2 = new HashMap<>();
            user2.put("name", "Transaction User 2");
            user2.put("email", "txn2@example.com");
            sql.insertRecord("users", user2);
            
            // Commit transaction
            sql.commit();
            System.out.println("✓ Transaction committed successfully");
            
        } catch (SQLException e) {
            // Rollback on error
            sql.rollback();
            System.out.println("✗ Transaction rolled back: " + e.getMessage());
            throw e;
        }
    }

    @Test(description = "Get table and column information", enabled = false)
    public void testTableMetadata() throws SQLException {
        System.out.println("\n--- Test 12: Table Metadata ---");
        
        // Get all tables
        List<String> tables = sql.getTableNames();
        System.out.println("Tables in database:");
        for (String table : tables) {
            System.out.println("  - " + table);
        }
        
        // Check if specific table exists
        boolean usersExists = sql.tableExists("users");
        System.out.println("\nTable 'users' exists: " + usersExists);
        
        // Get column names for a table
        if (usersExists) {
            List<String> columns = sql.getColumnNames("users");
            System.out.println("\nColumns in 'users' table:");
            for (String column : columns) {
                System.out.println("  - " + column);
            }
        }
    }

    @Test(description = "SQLite example", enabled = false)
    public void testSQLiteDatabase() throws SQLException {
        System.out.println("\n--- Test 13: SQLite Database ---");
        
        // Create SQLite in-memory database
        SQLUtils sqlite = new SQLUtils("jdbc:sqlite::memory:", "", "");
        
        try {
            // Create table
            String createTable = "CREATE TABLE test_users (id INTEGER PRIMARY KEY, name TEXT, age INTEGER)";
            sqlite.executeUpdate(createTable);
            System.out.println("✓ Table created");
            
            // Insert data
            String insert = "INSERT INTO test_users (name, age) VALUES (?, ?)";
            sqlite.executeUpdate(insert, "Alice", 25);
            sqlite.executeUpdate(insert, "Bob", 30);
            System.out.println("✓ Data inserted");
            
            // Query data
            List<Map<String, String>> results = sqlite.executeQuery("SELECT * FROM test_users");
            System.out.println("\nSQLite Results:");
            sqlite.printResults(results);
            
        } finally {
            sqlite.close();
        }
    }

    @Test(description = "Info: Connection String Examples", enabled = false)
    public void testConnectionStringExamples() {
        System.out.println("\n=== Database Connection String Examples ===");
        System.out.println("\nMySQL:");
        System.out.println("  jdbc:mysql://localhost:3306/database_name");
        System.out.println("  new SQLUtils(url, \"username\", \"password\")");
        
        System.out.println("\nPostgreSQL:");
        System.out.println("  jdbc:postgresql://localhost:5432/database_name");
        System.out.println("  new SQLUtils(url, \"username\", \"password\")");
        
        System.out.println("\nSQLite:");
        System.out.println("  jdbc:sqlite:path/to/database.db");
        System.out.println("  jdbc:sqlite::memory: (in-memory)");
        System.out.println("  new SQLUtils(url, \"\", \"\")");
        
        System.out.println("\nSQL Server:");
        System.out.println("  jdbc:sqlserver://localhost:1433;databaseName=database_name");
        System.out.println("  new SQLUtils(url, \"username\", \"password\")");
        
        System.out.println("\nOracle:");
        System.out.println("  jdbc:oracle:thin:@localhost:1521:database_name");
        System.out.println("  new SQLUtils(url, \"username\", \"password\")");
    }
}
