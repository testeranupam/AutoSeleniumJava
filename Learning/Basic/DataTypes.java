package Learning.Basic;

/**
 * Data Types in Java
 * Java has two types of data types:
 * 1. Primitive Data Types: byte, short, int, long, float, double, char, boolean
 * 2. Non-primitive Data Types: String, Arrays, Classes, Interfaces
 * 
 * KEY INFO:
 * Primitives: Stored in stack, have default values, fast
 * Non-primitives: Stored in heap, default is null, slower
 * Wrapper Classes: Integer, Double, Boolean (convert primitives to objects)
 * Autoboxing: int -> Integer, Unboxing: Integer -> int
 */
public class DataTypes {

    public static void main(String[] args) {
        // Primitive Data Types
        
        // 1. byte: 8-bit signed integer, range: -128 to 127
        byte byteVar = 100;
        System.out.println("Byte: " + byteVar + ", Memory: 1 byte");
        
        // 2. short: 16-bit signed integer, range: -32,768 to 32,767
        short shortVar = 30000;
        System.out.println("Short: " + shortVar + ", Memory: 2 bytes");
        
        // 3. int: 32-bit signed integer, range: -2,147,483,648 to 2,147,483,647
        int intVar = 2000000000;
        System.out.println("Int: " + intVar + ", Memory: 4 bytes");
        
        // 4. long: 64-bit signed integer, range: -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807
        long longVar = 9000000000000000L; // L denotes long
        System.out.println("Long: " + longVar + ", Memory: 8 bytes");
        
        // 5. float: 32-bit floating point, single precision
        float floatVar = 3.14f; // f denotes float
        System.out.println("Float: " + floatVar + ", Memory: 4 bytes");
        
        // 6. double: 64-bit floating point, double precision (default for decimal numbers)
        double doubleVar = 3.14159265359;
        System.out.println("Double: " + doubleVar + ", Memory: 8 bytes");
        
        // 7. char: 16-bit Unicode character, range: 0 to 65,535
        char charVar = 'A';
        System.out.println("Char: " + charVar + ", Memory: 2 bytes");
        
        // 8. boolean: true or false
        boolean boolVar = true;
        System.out.println("Boolean: " + boolVar + ", Memory: 1 bit");
        
        System.out.println("\n--- Type Casting ---");
        // Type Casting: Widening (automatic) and Narrowing (manual)
        
        // Widening: smaller type to larger type (automatic)
        int myInt = 100;
        long myLong = myInt; // Widening
        System.out.println("Widening - Int to Long: " + myLong);
        
        // Narrowing: larger type to smaller type (manual)
        double myDouble = 9.99;
        int myIntFromDouble = (int) myDouble; // Narrowing
        System.out.println("Narrowing - Double to Int: " + myIntFromDouble);
        
        System.out.println("\n--- Size of Data Types ---");
        System.out.println("Byte max: " + Byte.MAX_VALUE + ", Min: " + Byte.MIN_VALUE);
        System.out.println("Short max: " + Short.MAX_VALUE + ", Min: " + Short.MIN_VALUE);
        System.out.println("Int max: " + Integer.MAX_VALUE + ", Min: " + Integer.MIN_VALUE);
        System.out.println("Long max: " + Long.MAX_VALUE + ", Min: " + Long.MIN_VALUE);
        System.out.println("Float max: " + Float.MAX_VALUE + ", Min: " + Float.MIN_VALUE);
        System.out.println("Double max: " + Double.MAX_VALUE + ", Min: " + Double.MIN_VALUE);
    }
}

