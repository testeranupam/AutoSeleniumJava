package com.seleniumjava.utils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * POJO Utility Class
 * Provides helper methods for working with Plain Old Java Objects (POJOs)
 * 
 * Features:
 * - Convert Map to POJO
 * - Convert POJO to Map
 * - Convert CSV data to POJO list
 * - Print POJO details
 * - Clone POJOs
 * - Compare POJOs
 * 
 * Usage Examples:
 * User user = POJOUtils.mapToPOJO(dataMap, User.class);
 * Map<String, String> userMap = POJOUtils.pojoToMap(user);
 */
public class POJOUtils {
    
    /**
     * Convert Map to POJO object
     * Maps keys to POJO fields (case-insensitive)
     * 
     * @param dataMap Source map with field values
     * @param clazz Target POJO class
     * @param <T> POJO type
     * @return New POJO instance populated with data
     */
    public static <T> T mapToPOJO(Map<String, String> dataMap, Class<T> clazz) {
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();
            
            for (Map.Entry<String, String> entry : dataMap.entrySet()) {
                String fieldName = entry.getKey();
                String fieldValue = entry.getValue();
                
                if (fieldValue == null || fieldValue.trim().isEmpty()) {
                    continue;
                }
                
                // Find field (case-insensitive)
                Field field = findField(clazz, fieldName);
                if (field != null) {
                    field.setAccessible(true);
                    Object convertedValue = convertValue(fieldValue, field.getType());
                    field.set(instance, convertedValue);
                }
            }
            
            return instance;
        } catch (Exception e) {
            System.err.println("Error converting Map to POJO: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Convert POJO to Map
     * All fields are converted to String values
     * 
     * @param pojo Source POJO object
     * @return Map with field names as keys and values as strings
     */
    public static Map<String, String> pojoToMap(Object pojo) {
        Map<String, String> map = new LinkedHashMap<>();
        
        try {
            Class<?> clazz = pojo.getClass();
            Field[] fields = clazz.getDeclaredFields();
            
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(pojo);
                map.put(field.getName(), value != null ? value.toString() : "");
            }
        } catch (Exception e) {
            System.err.println("Error converting POJO to Map: " + e.getMessage());
            e.printStackTrace();
        }
        
        return map;
    }
    
    /**
     * Convert List of Maps to List of POJOs
     * Useful for converting CSV/Excel data to objects
     * 
     * @param dataList List of maps (from CSV/Excel)
     * @param clazz Target POJO class
     * @param <T> POJO type
     * @return List of POJO objects
     */
    public static <T> List<T> mapListToPOJOList(List<Map<String, String>> dataList, Class<T> clazz) {
        List<T> pojoList = new ArrayList<>();
        
        for (Map<String, String> dataMap : dataList) {
            T pojo = mapToPOJO(dataMap, clazz);
            if (pojo != null) {
                pojoList.add(pojo);
            }
        }
        
        System.out.println("Converted " + pojoList.size() + " records to " + clazz.getSimpleName() + " objects");
        return pojoList;
    }
    
    /**
     * Convert CSV file to List of POJOs
     * 
     * @param csvFilePath Path to CSV file
     * @param clazz Target POJO class
     * @param <T> POJO type
     * @return List of POJO objects
     */
    public static <T> List<T> csvToPOJOList(String csvFilePath, Class<T> clazz) {
        List<Map<String, String>> csvData = DataReader.readCSV(csvFilePath);
        return mapListToPOJOList(csvData, clazz);
    }
    
    /**
     * Print POJO details in formatted way
     * 
     * @param pojo POJO object to print
     */
    public static void printPOJO(Object pojo) {
        if (pojo == null) {
            System.out.println("Null POJO");
            return;
        }
        
        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║  " + pojo.getClass().getSimpleName() + " Details");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        
        try {
            Field[] fields = pojo.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(pojo);
                System.out.printf("║  %-20s : %s%n", field.getName(), value);
            }
        } catch (Exception e) {
            System.err.println("Error printing POJO: " + e.getMessage());
        }
        
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
    }
    
    /**
     * Print list of POJOs in table format
     * 
     * @param pojoList List of POJO objects
     */
    public static void printPOJOList(List<?> pojoList) {
        if (pojoList == null || pojoList.isEmpty()) {
            System.out.println("Empty POJO list");
            return;
        }
        
        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║  " + pojoList.get(0).getClass().getSimpleName() + " List (" + pojoList.size() + " records)");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝\n");
        
        for (int i = 0; i < pojoList.size(); i++) {
            System.out.println("Record #" + (i + 1) + ":");
            printPOJO(pojoList.get(i));
            System.out.println();
        }
    }
    
    /**
     * Clone a POJO (shallow copy)
     * 
     * @param pojo Source POJO
     * @param <T> POJO type
     * @return Cloned POJO
     */
    @SuppressWarnings("unchecked")
    public static <T> T clonePOJO(T pojo) {
        try {
            Class<?> clazz = pojo.getClass();
            T clone = (T) clazz.getDeclaredConstructor().newInstance();
            
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(pojo);
                field.set(clone, value);
            }
            
            return clone;
        } catch (Exception e) {
            System.err.println("Error cloning POJO: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Compare two POJOs field by field
     * 
     * @param pojo1 First POJO
     * @param pojo2 Second POJO
     * @return Map of differences (fieldName -> {pojo1Value, pojo2Value})
     */
    public static Map<String, String[]> comparePOJOs(Object pojo1, Object pojo2) {
        Map<String, String[]> differences = new LinkedHashMap<>();
        
        try {
            if (pojo1.getClass() != pojo2.getClass()) {
                System.err.println("POJOs are of different types");
                return differences;
            }
            
            Field[] fields = pojo1.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value1 = field.get(pojo1);
                Object value2 = field.get(pojo2);
                
                if (!Objects.equals(value1, value2)) {
                    differences.put(field.getName(), new String[]{
                        value1 != null ? value1.toString() : "null",
                        value2 != null ? value2.toString() : "null"
                    });
                }
            }
        } catch (Exception e) {
            System.err.println("Error comparing POJOs: " + e.getMessage());
        }
        
        return differences;
    }
    
    /**
     * Validate POJO - Check if required fields are not null/empty
     * 
     * @param pojo POJO object
     * @param requiredFields Array of required field names
     * @return true if all required fields have values
     */
    public static boolean validatePOJO(Object pojo, String... requiredFields) {
        try {
            Class<?> clazz = pojo.getClass();
            
            for (String fieldName : requiredFields) {
                Field field = findField(clazz, fieldName);
                if (field == null) {
                    System.err.println("Field not found: " + fieldName);
                    return false;
                }
                
                field.setAccessible(true);
                Object value = field.get(pojo);
                
                if (value == null) {
                    System.err.println("Required field is null: " + fieldName);
                    return false;
                }
                
                if (value instanceof String && ((String) value).trim().isEmpty()) {
                    System.err.println("Required field is empty: " + fieldName);
                    return false;
                }
            }
            
            return true;
        } catch (Exception e) {
            System.err.println("Error validating POJO: " + e.getMessage());
            return false;
        }
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // HELPER METHODS
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Find field in class by name (case-insensitive)
     */
    private static Field findField(Class<?> clazz, String fieldName) {
        try {
            // Try exact match first
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            // Try case-insensitive match
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getName().equalsIgnoreCase(fieldName)) {
                    return field;
                }
            }
            return null;
        }
    }
    
    /**
     * Convert String value to target type
     */
    private static Object convertValue(String value, Class<?> targetType) {
        try {
            if (targetType == String.class) {
                return value;
            } else if (targetType == int.class || targetType == Integer.class) {
                return Integer.parseInt(value);
            } else if (targetType == long.class || targetType == Long.class) {
                return Long.parseLong(value);
            } else if (targetType == double.class || targetType == Double.class) {
                return Double.parseDouble(value);
            } else if (targetType == float.class || targetType == Float.class) {
                return Float.parseFloat(value);
            } else if (targetType == boolean.class || targetType == Boolean.class) {
                return Boolean.parseBoolean(value);
            } else if (targetType == short.class || targetType == Short.class) {
                return Short.parseShort(value);
            } else if (targetType == byte.class || targetType == Byte.class) {
                return Byte.parseByte(value);
            } else if (targetType == char.class || targetType == Character.class) {
                return value.charAt(0);
            }
            return value;
        } catch (Exception e) {
            System.err.println("Error converting value '" + value + "' to type " + targetType.getSimpleName());
            return null;
        }
    }
}
