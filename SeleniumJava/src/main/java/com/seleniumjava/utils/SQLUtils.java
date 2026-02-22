package com.seleniumjava.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * SQL Utility for Database Operations
 * 
 * Supports: MySQL, PostgreSQL, Oracle, SQL Server, SQLite
 * 
 * Features:
 * - Connection management
 * - Execute queries (SELECT, INSERT, UPDATE, DELETE)
 * - Prepared statements
 * - Batch operations
 * - Transaction management
 * - Result set handling
 * 
 * Usage:
 * SQLUtils sql = new SQLUtils("jdbc:mysql://localhost:3306/testdb", "user", "pass");
 * List<Map<String, String>> results = sql.executeQuery("SELECT * FROM users");
 * sql.close();
 */
public class SQLUtils {

    private Connection connection;
    private String jdbcUrl;
    private String username;
    private String password;
    private boolean autoCommit = true;

    /**
     * Constructor with connection details
     */
    public SQLUtils(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
    }

    /**
     * Constructor with Properties
     */
    public SQLUtils(Properties props) {
        this.jdbcUrl = props.getProperty("db.url");
        this.username = props.getProperty("db.username");
        this.password = props.getProperty("db.password");
    }

    /**
     * Establish database connection
     */
    public Connection connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            connection.setAutoCommit(autoCommit);
            System.out.println("✓ Database connected: " + jdbcUrl);
        }
        return connection;
    }

    /**
     * Connect with custom properties
     */
    public Connection connect(Properties connectionProps) throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(jdbcUrl, connectionProps);
            connection.setAutoCommit(autoCommit);
            System.out.println("✓ Database connected: " + jdbcUrl);
        }
        return connection;
    }

    /**
     * Close database connection
     */
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✓ Database connection closed");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }

    /**
     * Execute SELECT query - Returns List of Maps
     */
    public List<Map<String, String>> executeQuery(String query) throws SQLException {
        connect();
        List<Map<String, String>> results = new ArrayList<>();
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            while (rs.next()) {
                Map<String, String> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    String value = rs.getString(i);
                    row.put(columnName, value);
                }
                results.add(row);
            }
            
            System.out.println("✓ Query executed: " + results.size() + " rows returned");
        }
        
        return results;
    }

    /**
     * Execute SELECT query with parameters (Prepared Statement)
     */
    public List<Map<String, String>> executeQuery(String query, Object... params) throws SQLException {
        connect();
        List<Map<String, String>> results = new ArrayList<>();
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            // Set parameters
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            
            try (ResultSet rs = pstmt.executeQuery()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                
                while (rs.next()) {
                    Map<String, String> row = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = metaData.getColumnName(i);
                        String value = rs.getString(i);
                        row.put(columnName, value);
                    }
                    results.add(row);
                }
            }
            
            System.out.println("✓ Prepared query executed: " + results.size() + " rows returned");
        }
        
        return results;
    }

    /**
     * Execute UPDATE, INSERT, DELETE query
     * Returns number of affected rows
     */
    public int executeUpdate(String query) throws SQLException {
        connect();
        
        try (Statement stmt = connection.createStatement()) {
            int affectedRows = stmt.executeUpdate(query);
            System.out.println("✓ Update executed: " + affectedRows + " rows affected");
            return affectedRows;
        }
    }

    /**
     * Execute UPDATE, INSERT, DELETE with parameters
     */
    public int executeUpdate(String query, Object... params) throws SQLException {
        connect();
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            // Set parameters
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            
            int affectedRows = pstmt.executeUpdate();
            System.out.println("✓ Prepared update executed: " + affectedRows + " rows affected");
            return affectedRows;
        }
    }

    /**
     * Execute batch updates
     */
    public int[] executeBatch(String query, List<Object[]> batchParams) throws SQLException {
        connect();
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            for (Object[] params : batchParams) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
                pstmt.addBatch();
            }
            
            int[] results = pstmt.executeBatch();
            System.out.println("✓ Batch executed: " + results.length + " statements");
            return results;
        }
    }

    /**
     * Execute stored procedure
     */
    public ResultSet executeStoredProcedure(String procedureName, Object... params) throws SQLException {
        connect();
        
        StringBuilder call = new StringBuilder("{call " + procedureName + "(");
        for (int i = 0; i < params.length; i++) {
            call.append("?");
            if (i < params.length - 1) call.append(",");
        }
        call.append(")}");
        
        CallableStatement cstmt = connection.prepareCall(call.toString());
        
        for (int i = 0; i < params.length; i++) {
            cstmt.setObject(i + 1, params[i]);
        }
        
        boolean hasResults = cstmt.execute();
        if (hasResults) {
            System.out.println("✓ Stored procedure executed with results");
            return cstmt.getResultSet();
        } else {
            System.out.println("✓ Stored procedure executed: " + cstmt.getUpdateCount() + " rows affected");
            return null;
        }
    }

    /**
     * Check if record exists
     */
    public boolean recordExists(String query) throws SQLException {
        List<Map<String, String>> results = executeQuery(query);
        return !results.isEmpty();
    }

    /**
     * Check if record exists with parameters
     */
    public boolean recordExists(String query, Object... params) throws SQLException {
        List<Map<String, String>> results = executeQuery(query, params);
        return !results.isEmpty();
    }

    /**
     * Get single value from query
     */
    public String getSingleValue(String query) throws SQLException {
        List<Map<String, String>> results = executeQuery(query);
        if (!results.isEmpty()) {
            Map<String, String> firstRow = results.get(0);
            return firstRow.values().iterator().next();
        }
        return null;
    }

    /**
     * Get single value with parameters
     */
    public String getSingleValue(String query, Object... params) throws SQLException {
        List<Map<String, String>> results = executeQuery(query, params);
        if (!results.isEmpty()) {
            Map<String, String> firstRow = results.get(0);
            return firstRow.values().iterator().next();
        }
        return null;
    }

    /**
     * Get row count
     */
    public int getRowCount(String tableName) throws SQLException {
        String query = "SELECT COUNT(*) FROM " + tableName;
        String count = getSingleValue(query);
        return count != null ? Integer.parseInt(count) : 0;
    }

    /**
     * Get row count with condition
     */
    public int getRowCount(String tableName, String condition) throws SQLException {
        String query = "SELECT COUNT(*) FROM " + tableName + " WHERE " + condition;
        String count = getSingleValue(query);
        return count != null ? Integer.parseInt(count) : 0;
    }

    /**
     * Delete all records from table
     */
    public int deleteAllRecords(String tableName) throws SQLException {
        String query = "DELETE FROM " + tableName;
        return executeUpdate(query);
    }

    /**
     * Delete records with condition
     */
    public int deleteRecords(String tableName, String condition) throws SQLException {
        String query = "DELETE FROM " + tableName + " WHERE " + condition;
        return executeUpdate(query);
    }

    /**
     * Truncate table (faster than DELETE)
     */
    public void truncateTable(String tableName) throws SQLException {
        String query = "TRUNCATE TABLE " + tableName;
        executeUpdate(query);
    }

    /**
     * Insert record
     */
    public int insertRecord(String tableName, Map<String, Object> values) throws SQLException {
        StringBuilder columns = new StringBuilder();
        StringBuilder placeholders = new StringBuilder();
        List<Object> params = new ArrayList<>();
        
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            if (columns.length() > 0) {
                columns.append(", ");
                placeholders.append(", ");
            }
            columns.append(entry.getKey());
            placeholders.append("?");
            params.add(entry.getValue());
        }
        
        String query = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + placeholders + ")";
        return executeUpdate(query, params.toArray());
    }

    /**
     * Update record
     */
    public int updateRecord(String tableName, Map<String, Object> values, String condition) throws SQLException {
        StringBuilder setClause = new StringBuilder();
        List<Object> params = new ArrayList<>();
        
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            if (setClause.length() > 0) {
                setClause.append(", ");
            }
            setClause.append(entry.getKey()).append(" = ?");
            params.add(entry.getValue());
        }
        
        String query = "UPDATE " + tableName + " SET " + setClause + " WHERE " + condition;
        return executeUpdate(query, params.toArray());
    }

    /**
     * Transaction Management - Begin Transaction
     */
    public void beginTransaction() throws SQLException {
        connect();
        connection.setAutoCommit(false);
        autoCommit = false;
        System.out.println("✓ Transaction started");
    }

    /**
     * Commit transaction
     */
    public void commit() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.commit();
            System.out.println("✓ Transaction committed");
        }
    }

    /**
     * Rollback transaction
     */
    public void rollback() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.rollback();
                System.out.println("✓ Transaction rolled back");
            }
        } catch (SQLException e) {
            System.err.println("Error rolling back: " + e.getMessage());
        }
    }

    /**
     * Get table column names
     */
    public List<String> getColumnNames(String tableName) throws SQLException {
        connect();
        List<String> columns = new ArrayList<>();
        
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet rs = metaData.getColumns(null, null, tableName, null)) {
            while (rs.next()) {
                columns.add(rs.getString("COLUMN_NAME"));
            }
        }
        
        return columns;
    }

    /**
     * Get all table names
     */
    public List<String> getTableNames() throws SQLException {
        connect();
        List<String> tables = new ArrayList<>();
        
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet rs = metaData.getTables(null, null, "%", new String[]{"TABLE"})) {
            while (rs.next()) {
                tables.add(rs.getString("TABLE_NAME"));
            }
        }
        
        return tables;
    }

    /**
     * Check if table exists
     */
    public boolean tableExists(String tableName) throws SQLException {
        List<String> tables = getTableNames();
        return tables.contains(tableName.toUpperCase()) || tables.contains(tableName.toLowerCase());
    }

    /**
     * Get database metadata information
     */
    public Map<String, String> getDatabaseInfo() throws SQLException {
        connect();
        Map<String, String> info = new HashMap<>();
        
        DatabaseMetaData metaData = connection.getMetaData();
        info.put("DatabaseProductName", metaData.getDatabaseProductName());
        info.put("DatabaseProductVersion", metaData.getDatabaseProductVersion());
        info.put("DriverName", metaData.getDriverName());
        info.put("DriverVersion", metaData.getDriverVersion());
        info.put("URL", metaData.getURL());
        info.put("UserName", metaData.getUserName());
        
        return info;
    }

    /**
     * Print result set in tabular format
     */
    public void printResults(List<Map<String, String>> results) {
        if (results.isEmpty()) {
            System.out.println("No results found");
            return;
        }
        
        // Get column names from first row
        Map<String, String> firstRow = results.get(0);
        List<String> columns = new ArrayList<>(firstRow.keySet());
        
        // Print header
        System.out.println("\n" + "=".repeat(80));
        for (String column : columns) {
            System.out.printf("%-20s | ", column);
        }
        System.out.println("\n" + "=".repeat(80));
        
        // Print rows
        for (Map<String, String> row : results) {
            for (String column : columns) {
                String value = row.get(column);
                System.out.printf("%-20s | ", value != null ? value : "NULL");
            }
            System.out.println();
        }
        
        System.out.println("=".repeat(80));
        System.out.println("Total rows: " + results.size());
    }

    /**
     * Get connection object (for advanced operations)
     */
    public Connection getConnection() throws SQLException {
        connect();
        return connection;
    }

    /**
     * Check if connected
     */
    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}
