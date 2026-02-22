package Learning.Basic;

import java.util.Arrays;

/**
 * Arrays in Java
 * 1. One-dimensional arrays
 * 2. Two-dimensional arrays
 * 3. Array operations (sorting, searching, copying)
 * 
 * KEY INFO:
 * Arrays: Fixed size, store same type, indexed from 0
 * Declaration: int[] arr = new int[5];
 * Initialization: int[] arr = {1, 2, 3};
 * Length: arr.length (property, not method)
 * Arrays.sort(), Arrays.binarySearch(), Arrays.copyOf()
 */
public class ArrayExamples {
    
    public static void main(String[] args) {
        System.out.println("=== One-Dimensional Array ===");
        oneDimensionalArray();
        
        System.out.println("\n=== Two-Dimensional Array ===");
        twoDimensionalArray();
        
        System.out.println("\n=== Array Operations ===");
        arrayOperations();
    }
    
    public static void oneDimensionalArray() {
        // Method 1: Declaring and initializing
        int[] numbers = {10, 20, 30, 40, 50};
        System.out.println("Array: " + Arrays.toString(numbers));
        System.out.println("Length: " + numbers.length);
        System.out.println("First element: " + numbers[0]);
        System.out.println("Last element: " + numbers[numbers.length - 1]);
        
        // Method 2: Declaring and then initializing
        String[] fruits = new String[3];
        fruits[0] = "Apple";
        fruits[1] = "Banana";
        fruits[2] = "Cherry";
        System.out.println("\nFruits array:");
        for (String fruit : fruits) {
            System.out.println("- " + fruit);
        }
        
        // Accessing and modifying
        System.out.println("\nModifying array:");
        numbers[2] = 35;
        System.out.println("Modified array: " + Arrays.toString(numbers));
    }
    
    public static void twoDimensionalArray() {
        // Method 1: Regular 2D array
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        System.out.println("Matrix (2D Array):");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        
        // Method 2: Enhanced for loop
        System.out.println("\nUsing enhanced for loop:");
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        
        // Accessing elements
        System.out.println("\nElement at [1][2]: " + matrix[1][2]);
        System.out.println("Number of rows: " + matrix.length);
        System.out.println("Number of columns in row 0: " + matrix[0].length);
    }
    
    public static void arrayOperations() {
        int[] numbers = {50, 30, 10, 40, 20};
        
        System.out.println("Original array: " + Arrays.toString(numbers));
        
        // Sorting
        Arrays.sort(numbers);
        System.out.println("Sorted array: " + Arrays.toString(numbers));
        
        // Binary search (only works on sorted array)
        int index = Arrays.binarySearch(numbers, 30);
        System.out.println("Index of 30: " + index);
        
        // Copying array
        int[] copiedArray = Arrays.copyOf(numbers, numbers.length);
        System.out.println("Copied array: " + Arrays.toString(copiedArray));
        
        // Copying range
        int[] rangeArray = Arrays.copyOfRange(numbers, 1, 4);
        System.out.println("Array range [1-4): " + Arrays.toString(rangeArray));
        
        // Filling array
        int[] filledArray = new int[5];
        Arrays.fill(filledArray, 99);
        System.out.println("Filled array: " + Arrays.toString(filledArray));
        
        // Checking equality
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {1, 2, 3};
        System.out.println("Arrays are equal: " + Arrays.equals(arr1, arr2));
    }
}

