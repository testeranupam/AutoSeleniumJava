package com.seleniumjava.utils;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * File Handling Utility Class
 * Handles file operations: read, write, delete, copy, and exist check
 */
public class FileHandlingUtils {
    
    /**
     * Read all lines from a file
     * @param filePath absolute or relative path to file
     * @return List of lines from file
     */
    public static List<String> readFileLines(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    /**
     * Read entire file as single string
     * @param filePath absolute or relative path to file
     * @return file content as string
     */
    public static String readFileContent(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
            return "";
        }
    }
    
    /**
     * Write content to a file
     * @param filePath absolute or relative path
     * @param content content to write
     * @param append true to append, false to overwrite
     */
    public static void writeToFile(String filePath, String content, boolean append) {
        try {
            Files.write(
                Paths.get(filePath),
                content.getBytes(),
                append ? StandardOpenOption.APPEND : StandardOpenOption.CREATE,
                StandardOpenOption.WRITE
            );
            System.out.println("File written successfully: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filePath);
            e.printStackTrace();
        }
    }
    
    /**
     * Write content to file (overwrites existing)
     * @param filePath absolute or relative path
     * @param content content to write
     */
    public static void writeToFile(String filePath, String content) {
        writeToFile(filePath, content, false);
    }
    
    /**
     * Append content to file
     * @param filePath absolute or relative path
     * @param content content to append
     */
    public static void appendToFile(String filePath, String content) {
        writeToFile(filePath, content, true);
    }
    
    /**
     * Check if file exists
     * @param filePath absolute or relative path
     * @return true if file exists
     */
    public static boolean fileExists(String filePath) {
        return Files.exists(Paths.get(filePath));
    }
    
    /**
     * Delete a file
     * @param filePath absolute or relative path
     * @return true if deleted successfully
     */
    public static boolean deleteFile(String filePath) {
        try {
            return Files.deleteIfExists(Paths.get(filePath));
        } catch (IOException e) {
            System.err.println("Error deleting file: " + filePath);
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Copy file from source to destination
     * @param sourcePath source file path
     * @param destPath destination file path
     * @return true if copied successfully
     */
    public static boolean copyFile(String sourcePath, String destPath) {
        try {
            Files.copy(
                Paths.get(sourcePath),
                Paths.get(destPath),
                java.nio.file.StandardCopyOption.REPLACE_EXISTING
            );
            System.out.println("File copied: " + sourcePath + " -> " + destPath);
            return true;
        } catch (IOException e) {
            System.err.println("Error copying file: " + sourcePath);
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Create a new directory
     * @param dirPath directory path to create
     * @return true if created successfully
     */
    public static boolean createDirectory(String dirPath) {
        try {
            Files.createDirectories(Paths.get(dirPath));
            System.out.println("Directory created: " + dirPath);
            return true;
        } catch (IOException e) {
            System.err.println("Error creating directory: " + dirPath);
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Get file size in bytes
     * @param filePath absolute or relative path
     * @return file size in bytes
     */
    public static long getFileSize(String filePath) {
        try {
            return Files.size(Paths.get(filePath));
        } catch (IOException e) {
            System.err.println("Error getting file size: " + filePath);
            e.printStackTrace();
            return -1;
        }
    }
    
    /**
     * Get file extension
     * @param filePath absolute or relative path
     * @return file extension (e.g., "txt", "csv")
     */
    public static String getFileExtension(String filePath) {
        String filename = new File(filePath).getName();
        int lastDot = filename.lastIndexOf('.');
        return lastDot > 0 ? filename.substring(lastDot + 1) : "";
    }
    
    /**
     * List all files in a directory
     * @param dirPath directory path
     * @return array of File objects
     */
    public static File[] listFilesInDirectory(String dirPath) {
        File dir = new File(dirPath);
        return dir.listFiles();
    }
}
