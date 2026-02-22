package Learning.Basic;
import java.util.Arrays;

/**
 * Strings in Java
 * 1. String creation and manipulation
 * 2. String methods
 * 3. String immutability
 * 4. StringBuilder and StringBuffer
 * 
 * KEY INFO:
 * String Creation & Manipulation:
 *
 * - Literals: `String s = "text";` literals are interned in the String Pool (shared).
 * - Using `new`: `new String("text")` always creates a new object on the heap.
 * - Concatenation: using `+` or `concat()` produces new `String` objects; for
 *   many concatenations prefer `StringBuilder` to avoid repeated allocation.
 * - Interning: `String.intern()` returns the pooled instance (can reduce duplicates).
 *
 * String Methods (common):
 *
 * - `length()`: number of characters.
 * - `charAt(int)`: character at index.
 * - `substring(begin, end)`: extract sub-sequence (returns new `String`).
 * - `indexOf(...)`, `lastIndexOf(...)`: search methods.
 * - `replace(...)`, `toUpperCase()`, `toLowerCase()`, `trim()`, `split(...)`.
 * - `contains(...)`, `startsWith(...)`, `endsWith(...)`, `equals(...)`, `compareTo(...)`, `hashCode()`.
 *
 * String Immutability:
 *
 * - `String` is immutable: modifying operations return new `String` objects and
 *   do not change the original instance. This makes `String` safe to share
 *   between threads but less efficient for repeated mutations.
 * - Since Java 7/8, `substring()` creates a new char array copy instead of
 *   sharing the original backing array, avoiding accidental large-memory retention.
 * - For heavy or frequent modifications, use `StringBuilder` (single-threaded)
 *   or `StringBuffer` (synchronized) to reduce allocations.
 * - Security: avoid storing secrets in `String` (immutable and hard to clear);
 *   prefer `char[]` so you can overwrite contents when finished.
 * 
 * StringBuilder vs StringBuffer:
 * StringBuilder: mutable sequence of characters; methods are not synchronized.
 * StringBuffer: mutable sequence of characters; methods are synchronized (thread-safe).
 *
 * Performance:
 *
 * StringBuilder is faster in single-threaded contexts because it avoids synchronization overhead.
 * StringBuffer is slower due to the cost of synchronization, though modern JVMs reduce some overhead.
 *
 * Thread-safety nuances:
 *
 * StringBuffer makes individual method calls thread-safe (synchronized), but it does NOT make multi-step compound operations atomic. For complex shared usage, external synchronization or other concurrency designs are still needed.
 * StringBuilder should be used when only one thread accesses the instance.
 *
 * When to use which:
 *
 * Use StringBuilder for building/updating strings in local, single-threaded code (most cases).
 * Use StringBuffer only when you must share a mutable character sequence across threads and cannot use higher-level concurrency controls.
 *
 * Other notes:
 *
 * Both provide similar APIs (append, insert, replace, delete, reverse, toString).
 * Prefer String for immutable values and security-sensitive data (or char[] for secrets if you need to clear memory).
 * Common String methods:
 * length(), charAt(), substring(), indexOf(), replace(), toUpperCase(), toLowerCase(), trim(), split(), contains(), startsWith(), endsWith().
 */
public class Strings {
    
    public static void main(String[] args) {
        System.out.println("=== String Creation ===");
        stringCreation();
        
        System.out.println("\n=== String Methods ===");
        stringMethods();
        
        System.out.println("\n=== String Comparison ===");
        stringComparison();
        
        System.out.println("\n=== String Immutability ===");
        stringImmutability();
        
        System.out.println("\n=== StringBuilder ===");
        stringBuilderExample();
    }
    
    public static void stringCreation() {
        // Method 1: Using string literal
        String str1 = "Hello, Java!";
        System.out.println("Literal: " + str1);
        
        // Method 2: Using new keyword
        String str2 = new String("Hello, Java!");
        System.out.println("Using new: " + str2);
        
        // Method 3: Concatenation
        String firstName = "John";
        String lastName = "Doe";
        String fullName = firstName + " " + lastName;
        System.out.println("Concatenation: " + fullName);
    }
    
    public static void stringMethods() {
        String str = "Hello, World!";
        
        System.out.println("Original String: " + str);
        System.out.println("Length: " + str.length());
        System.out.println("Character at index 0: " + str.charAt(0));
        System.out.println("Character at index 7: " + str.charAt(7));
        System.out.println("Substring (0-5): " + str.substring(0, 5));
        System.out.println("Substring from 7: " + str.substring(7));
        System.out.println("Contains 'World': " + str.contains("World"));
        System.out.println("Index of 'o': " + str.indexOf('o'));
        System.out.println("Last index of 'o': " + str.lastIndexOf('o'));
        System.out.println("Starts with 'Hello': " + str.startsWith("Hello"));
        System.out.println("Ends with '!': " + str.endsWith("!"));
        System.out.println("Uppercase: " + str.toUpperCase());
        System.out.println("Lowercase: " + str.toLowerCase());
        System.out.println("Replace 'World' with 'Java': " + str.replace("World", "Java"));
        System.out.println("Trim '  spaces  ': '" + "  spaces  ".trim() + "'");
        System.out.println("Split by ',': " + Arrays.toString(str.split(",")));
    }
    
    public static void stringComparison() {
        String str1 = "Java";
        String str2 = "Java";
        String str3 = new String("Java");
        String str4 = "Python";
        
        System.out.println("str1 == str2 (same reference): " + (str1 == str2));
        System.out.println("str1 == str3 (different reference): " + (str1 == str3));
        System.out.println("str1.equals(str3) (value comparison): " + str1.equals(str3));
        System.out.println("str1.compareTo(str2): " + str1.compareTo(str2));
        System.out.println("str1.compareTo(str4): " + str1.compareTo(str4));
    }
    
    public static void stringImmutability() {
        String original = "Hello";
        System.out.println("Original string: " + original);
        System.out.println("Original hashCode: " + original.hashCode());
        
        // This creates a new string, original remains unchanged
        String modified = original.concat(" World");
        System.out.println("After concat: " + original);
        System.out.println("New string: " + modified);
        System.out.println("Original hashCode: " + original.hashCode());
        System.out.println("New hashCode: " + modified.hashCode());
        System.out.println("Strings are different objects: " + (original != modified));
    }
    
    public static void stringBuilderExample() {
        StringBuilder sb = new StringBuilder("Hello");
        System.out.println("Initial: " + sb);
        
        sb.append(" World");
        System.out.println("After append: " + sb);
        
        sb.insert(5, ",");
        System.out.println("After insert: " + sb);
        
        sb.replace(0, 5, "Hi");
        System.out.println("After replace: " + sb);
        
        sb.reverse();
        System.out.println("After reverse: " + sb);
        
        sb.delete(0, 2);
        System.out.println("After delete: " + sb);
        
        String result = sb.toString();
        System.out.println("Final string: " + result);
    }
}

