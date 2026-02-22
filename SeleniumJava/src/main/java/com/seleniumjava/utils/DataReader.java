package com.seleniumjava.utils;

import java.io.*;
import java.util.*;

/**
 * Data Reader Utility
 * Reads test data from CSV, properties, and other data files
 */
public class DataReader {
    
    /**
     * Read CSV file and return as List of Maps
     * First row is treated as headers
     * @param csvFilePath path to CSV file
     * @return List of maps where each map represents a row
     */
    public static List<Map<String, String>> readCSV(String csvFilePath) {
        List<Map<String, String>> data = new ArrayList<>();
        List<String> lines = FileHandlingUtils.readFileLines(csvFilePath);
        
        if (lines.isEmpty()) {
            System.out.println("CSV file is empty: " + csvFilePath);
            return data;
        }
        
        // Split headers
        String[] headers = lines.get(0).split(",");
        
        // Process data rows
        for (int i = 1; i < lines.size(); i++) {
            String[] values = lines.get(i).split(",");
            Map<String, String> row = new LinkedHashMap<>();
            
            for (int j = 0; j < headers.length && j < values.length; j++) {
                row.put(headers[j].trim(), values[j].trim());
            }
            
            data.add(row);
        }
        
        return data;
    }
    
    /**
     * Read CSV with custom delimiter
     * @param csvFilePath path to CSV file
     * @param delimiter delimiter character
     * @return List of maps
     */
    public static List<Map<String, String>> readCSVWithDelimiter(String csvFilePath, String delimiter) {
        List<Map<String, String>> data = new ArrayList<>();
        List<String> lines = FileHandlingUtils.readFileLines(csvFilePath);
        
        if (lines.isEmpty()) {
            System.out.println("CSV file is empty: " + csvFilePath);
            return data;
        }
        
        // Split headers with custom delimiter
        String[] headers = lines.get(0).split(delimiter);
        
        // Process data rows
        for (int i = 1; i < lines.size(); i++) {
            String[] values = lines.get(i).split(delimiter);
            Map<String, String> row = new LinkedHashMap<>();
            
            for (int j = 0; j < headers.length && j < values.length; j++) {
                row.put(headers[j].trim(), values[j].trim());
            }
            
            data.add(row);
        }
        
        return data;
    }
    
    /**
     * Read properties file
     * @param filePath path to properties file
     * @return Properties object
     */
    public static Properties readProperties(String filePath) {
        Properties properties = new Properties();
        
        try (InputStream input = new FileInputStream(filePath)) {
            properties.load(input);
        } catch (IOException e) {
            System.err.println("Error reading properties file: " + filePath);
            e.printStackTrace();
        }
        
        return properties;
    }
    
    /**
     * Read specific property value
     * @param filePath path to properties file
     * @param key property key
     * @return property value
     */
    public static String getPropertyValue(String filePath, String key) {
        Properties properties = readProperties(filePath);
        return properties.getProperty(key, "");
    }
    
    /**
     * Read JSON file as string
     * @param jsonFilePath path to JSON file
     * @return JSON content as string
     */
    public static String readJSON(String jsonFilePath) {
        return FileHandlingUtils.readFileContent(jsonFilePath);
    }
    
    /**
     * Read Excel file using Apache POI (requires POI dependency)
     * Note: Requires org.apache.poi:poi and org.apache.poi:poi-ooxml dependencies
     * @param excelFilePath path to Excel file
     * @param sheetName name of sheet to read
     * @return List of maps representing rows
     */
    public static List<Map<String, String>> readExcel(String excelFilePath, String sheetName) {
        List<Map<String, String>> data = new ArrayList<>();
        
        try {
            // This is a basic structure. Full implementation requires POI dependency
            System.out.println("Excel reading requires Apache POI dependency");
            System.out.println("Add dependencies: ");
            System.out.println("  - org.apache.poi:poi");
            System.out.println("  - org.apache.poi:poi-ooxml");
        } catch (Exception e) {
            System.err.println("Error reading Excel file: " + excelFilePath);
            e.printStackTrace();
        }
        
        return data;
    }
    
    /**
     * Parse delimited file with any separator
     * @param filePath path to file
     * @param delimiter delimiter string
     * @return List of lists (each inner list is a row)
     */
    public static List<List<String>> parseDelimitedFile(String filePath, String delimiter) {
        List<List<String>> data = new ArrayList<>();
        List<String> lines = FileHandlingUtils.readFileLines(filePath);
        
        for (String line : lines) {
            String[] values = line.split(delimiter);
            data.add(Arrays.asList(values));
        }
        
        return data;
    }
    
    /**
     * Get specific value from CSV by row and column
     * @param csvFilePath path to CSV file
     * @param rowIndex row index (0-based, excluding header)
     * @param columnHeader column header name
     * @return value at specified location
     */
    public static String getValueFromCSV(String csvFilePath, int rowIndex, String columnHeader) {
        List<Map<String, String>> data = readCSV(csvFilePath);
        
        if (rowIndex < 0 || rowIndex >= data.size()) {
            return "";
        }
        
        return data.get(rowIndex).getOrDefault(columnHeader, "");
    }
}
