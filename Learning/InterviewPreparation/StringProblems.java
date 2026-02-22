package Learning.InterviewPreparation;

/**
 * Common String Problems for Interview Preparation
 */

public class StringProblems {
    
    /**
     * 1. Reverse a String
     * Example: "Hello" -> "olleH"
     */
    public static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }
    
    public static String reverseStringManual(String str) {
        String reversed = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed += str.charAt(i);
        }
        return reversed;
    }
    
    /**
     * 2. Check if String is Palindrome
     * Example: "racecar" -> true, "hello" -> false
     */
    public static boolean isPalindrome(String str) {
        String normalized = str.toLowerCase().replaceAll("[^a-z0-9]", "");
        String reversed = new StringBuilder(normalized).reverse().toString();
        return normalized.equals(reversed);
    }
    
    /**
     * 3. Count Vowels in String
     */
    public static int countVowels(String str) {
        int count = 0;
        String vowels = "aeiouAEIOU";
        for (char c : str.toCharArray()) {
            if (vowels.contains(String.valueOf(c))) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * 4. Check if two strings are anagram
     * Example: "listen" and "silent" are anagrams
     */
    public static boolean isAnagram(String str1, String str2) {
        str1 = str1.toLowerCase().replaceAll("\\s", "");
        str2 = str2.toLowerCase().replaceAll("\\s", "");
        
        if (str1.length() != str2.length()) {
            return false;
        }
        
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        
        java.util.Arrays.sort(arr1);
        java.util.Arrays.sort(arr2);
        
        return java.util.Arrays.equals(arr1, arr2);
    }
    
    /**
     * 5. Remove Duplicates from String
     * Example: "programming" -> "progamin"
     */
    public static String removeDuplicates(String str) {
        StringBuilder result = new StringBuilder();
        java.util.Set<Character> seen = new java.util.LinkedHashSet<>();
        
        for (char c : str.toCharArray()) {
            if (seen.add(c)) {
                result.append(c);
            }
        }
        return result.toString();
    }
    
    /**
     * 6. Find First Non-Repeated Character
     */
    public static Character findFirstNonRepeated(String str) {
        java.util.Map<Character, Integer> charCount = new java.util.LinkedHashMap<>();
        
        for (char c : str.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        for (char c : str.toCharArray()) {
            if (charCount.get(c) == 1) {
                return c;
            }
        }
        return null;
    }
    
    /**
     * 7. Find First Repeated Character
     */
    public static Character findFirstRepeated(String str) {
        java.util.Map<Character, Integer> charCount = new java.util.LinkedHashMap<>();
        
        for (char c : str.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        for (char c : str.toCharArray()) {
            if (charCount.get(c) > 1) {
                return c;
            }
        }
        return null;
    }
    
    /**
     * 8. Reverse Words in a String
     * Example: "Hello World" -> "World Hello"
     */
    public static String reverseWords(String str) {
        String[] words = str.split(" ");
        StringBuilder reversed = new StringBuilder();
        
        for (int i = words.length - 1; i >= 0; i--) {
            reversed.append(words[i]);
            if (i > 0) {
                reversed.append(" ");
            }
        }
        return reversed.toString();
    }
    
    /**
     * 9. Check if String is Rotation of Another String
     * Example: "abcd" and "cdab" -> true
     */
    public static boolean isRotation(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        String combined = str1 + str1;
        return combined.contains(str2);
    }
    
    /**
     * 10. Longest Substring Without Repeating Characters
     */
    public static int longestSubstring(String str) {
        java.util.Map<Character, Integer> lastIndex = new java.util.HashMap<>();
        int maxLength = 0;
        int startIndex = 0;
        
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            
            if (lastIndex.containsKey(c)) {
                startIndex = Math.max(startIndex, lastIndex.get(c) + 1);
            }
            
            maxLength = Math.max(maxLength, i - startIndex + 1);
            lastIndex.put(c, i);
        }
        
        return maxLength;
    }
    
    /**
     * 11. Run-Length Encoding
     * Example: "aaabbcccca" -> "a3b2c4a1"
     */
    public static String runLengthEncoding(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        
        StringBuilder encoded = new StringBuilder();
        int count = 1;
        
        for (int i = 0; i < str.length(); i++) {
            if (i >= str.length()-1 || str.charAt(i) != str.charAt(i + 1)) {
                encoded.append(str.charAt(i)).append(count);
                count = 1;            
            } else {
                count++;    
            }
        }
        return encoded.toString();
    }
    
    public static void main(String[] args) {
        System.out.println("=== String Interview Problems ===\n");
        
        System.out.println("1. Reverse String:");
        System.out.println("   'Hello' reversed: " + reverseString("Hello"));
        
        System.out.println("\n2. Check Palindrome:");
        System.out.println("   'racecar' is palindrome: " + isPalindrome("racecar"));
        System.out.println("   'hello' is palindrome: " + isPalindrome("hello"));
        
        System.out.println("\n3. Count Vowels:");
        System.out.println("   Vowels in 'programming': " + countVowels("programming"));
        
        System.out.println("\n4. Check Anagram:");
        System.out.println("   'listen' and 'silent' are anagram: " + isAnagram("listen", "silent"));
        
        System.out.println("\n5. Remove Duplicates:");
        System.out.println("   'programming' -> " + removeDuplicates("programming"));
        
        System.out.println("\n6. First Non-Repeated Character:");
        System.out.println("   In 'programming': " + findFirstNonRepeated("programming"));
        
        System.out.println("\n7. First Repeated Character:");
        System.out.println("   In 'programming': " + findFirstRepeated("programming"));
        
        System.out.println("\n8. Reverse Words:");
        System.out.println("   'Hello World' -> " + reverseWords("Hello World"));
        
        System.out.println("\n9. Check Rotation:");
        System.out.println("   'abcd' rotated to 'cdab': " + isRotation("abcd", "cdab"));
        
        System.out.println("\n10. Longest Substring Without Repeating:");
        System.out.println("    'abcabcbb' -> " + longestSubstring("abcabcbb"));
        System.out.println("    'pwwkew' -> " + longestSubstring("pwwkew"));
        
        System.out.println("\n11. Run-Length Encoding:");
        System.out.println("    'aaabbcccca' -> " + runLengthEncoding("aaabbcccca"));
        System.out.println("    'aabbcc' -> " + runLengthEncoding("aabbcc"));
        System.out.println("    'aaaa' -> " + runLengthEncoding("aaaa"));
    }
}

