package Learning.InterviewPreparation;

import java.util.*;

/**
 * Array and Sorting Problems for Interview Preparation
 */

public class ArraySortingProblems {
    
    /**
     * 1. Find Maximum and Minimum in Array
     */
    public static int[] findMinMax(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        
        for (int num : arr) {
            if (num < min) min = num;
            if (num > max) max = num;
        }
        
        return new int[]{min, max};
    }
    
    /**
     * 2. Find Second Largest Element
     */
    public static int findSecondLargest(int[] arr) {
        if (arr.length < 2) return -1;
        
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        
        for (int num : arr) {
            if (num > largest) {
                secondLargest = largest;
                largest = num;
            } else if (num > secondLargest && num != largest) {
                secondLargest = num;
            }
        }
        
        return secondLargest;
    }
    
    /**
     * 3. Bubble Sort
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    
    /**
     * 4. Selection Sort
     */
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            
            // Swap
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
    
    /**
     * 5. Insertion Sort
     */
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
    
    /**
     * 6. Remove Duplicates from Sorted Array
     */
    public static int removeDuplicates(int[] arr) {
        if (arr.length == 0) return 0;
        
        int p1 = 0;
        int p2 = 1;
        
        while (p2 < arr.length) {
            if (arr[p1] != arr[p2]) {
                p1++;
                arr[p1] = arr[p2];
            }
            p2++;
        }
        
        return p1 + 1;
    }
    
    /**
     * 7. Move Zeros to End
     */
    public static void moveZerosToEnd(int[] arr) {
        int nonZeroIndex = 0;
        
        // Move all non-zero elements to the beginning
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[nonZeroIndex++] = arr[i];
            }
        }
        
        // Fill remaining with zeros
        while (nonZeroIndex < arr.length) {
            arr[nonZeroIndex++] = 0;
        }
    }
    
    /**
     * 8. Find Pair with Given Sum
     */
    public static boolean findPairWithSum(int[] arr, int targetSum) {
        Set<Integer> seen = new HashSet<>();
        
        for (int num : arr) {
            if (seen.contains(targetSum - num)) {
                return true;
            }
            seen.add(num);
        }
        
        return false;
    }
    
    /**
     * 9. Find Two Sum (return indices)
     */
    public static int[] twoSum(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];
            
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(arr[i], i);
        }
        
        return new int[]{};
    }
    
    /**
     * 10. Merge Two Sorted Arrays
     */
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int[] merged = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;
        
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                merged[k++] = arr1[i++];
            } else {
                merged[k++] = arr2[j++];
            }
        }
        
        while (i < arr1.length) {
            merged[k++] = arr1[i++];
        }
        
        while (j < arr2.length) {
            merged[k++] = arr2[j++];
        }
        
        return merged;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Array and Sorting Interview Problems ===\n");
        
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        
        System.out.println("Original Array: " + Arrays.toString(arr));
        
        System.out.println("\n1. Find Min and Max:");
        int[] minMax = findMinMax(arr);
        System.out.println("   Min: " + minMax[0] + ", Max: " + minMax[1]);
        
        System.out.println("\n2. Second Largest Element:");
        System.out.println("   " + findSecondLargest(arr));
        
        System.out.println("\n3. Bubble Sort:");
        int[] arrBubble = arr.clone();
        bubbleSort(arrBubble);
        System.out.println("   " + Arrays.toString(arrBubble));
        
        System.out.println("\n4. Selection Sort:");
        int[] arrSelection = arr.clone();
        selectionSort(arrSelection);
        System.out.println("   " + Arrays.toString(arrSelection));
        
        System.out.println("\n5. Insertion Sort:");
        int[] arrInsertion = arr.clone();
        insertionSort(arrInsertion);
        System.out.println("   " + Arrays.toString(arrInsertion));
        
        System.out.println("\n6. Remove Duplicates:");
        int[] sortedArr = {1, 1, 2, 2, 3, 4, 4};
        int length = removeDuplicates(sortedArr);
        System.out.println("   New length: " + length + ", Array: " + 
                          Arrays.toString(Arrays.copyOf(sortedArr, length)));
        
        System.out.println("\n7. Move Zeros to End:");
        int[] arrWithZeros = {1, 0, 3, 0, 5, 0, 7};
        moveZerosToEnd(arrWithZeros);
        System.out.println("   " + Arrays.toString(arrWithZeros));
        
        System.out.println("\n8. Find Pair with Given Sum:");
        int[] testArr = {1, 5, 7, -1, 5};
        System.out.println("   Has pair with sum 6: " + findPairWithSum(testArr, 6));
        System.out.println("   Has pair with sum 100: " + findPairWithSum(testArr, 100));
        
        System.out.println("\n9. Two Sum (return indices):");
        int[] indices = twoSum(testArr, 6);
        System.out.println("   Pair with sum 6 at indices: " + Arrays.toString(indices));
        
        System.out.println("\n10. Merge Two Sorted Arrays:");
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {2, 4, 6, 8};
        int[] merged = mergeSortedArrays(arr1, arr2);
        System.out.println("   " + Arrays.toString(arr1) + " + " + Arrays.toString(arr2));
        System.out.println("   = " + Arrays.toString(merged));
    }
}

