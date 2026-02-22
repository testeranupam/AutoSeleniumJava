package Learning.Basic;

/**
 * Methods in Java
 * 1. Method declaration and invocation
 * 2. Parameters and return types
 * 3. Method overloading
 * 4. Variable arguments (varargs)
 * 
 * KEY INFO:
 * Syntax: returnType methodName(parameters) { body }
 * void: No return value
 * Overloading: Same name, different parameters
 * Varargs: methodName(String... args) - variable arguments
 * Pass by value: Primitives, Pass by reference: Objects
 */
public class Methods {
    
    public static void main(String[] args) {
        System.out.println("=== Method Basics ===");
        greet();
        greetName("Alice");
        
        System.out.println("\n=== Return Values ===");
        int sum = add(10, 20);
        System.out.println("Sum: " + sum);
        
        System.out.println("\n=== Method Overloading ===");
        System.out.println("add(10, 20): " + add(10, 20));
        System.out.println("add(10.5, 20.5): " + add(10.5, 20.5));
        System.out.println("add(10, 20, 30): " + add(10, 20, 30));
        
        System.out.println("\n=== Variable Arguments ===");
        System.out.println("Sum of 1,2,3: " + sumAll(1, 2, 3));
        System.out.println("Sum of 10,20,30,40,50: " + sumAll(10, 20, 30, 40, 50));
        
        System.out.println("\n=== Pass by Value vs Pass by Reference ===");
        passByValueExample();
    }
    
    // Simple method with no parameters and no return value
    public static void greet() {
        System.out.println("Hello, World!");
    }
    
    // Method with parameter and no return value
    public static void greetName(String name) {
        System.out.println("Hello, " + name + "!");
    }
    
    // Method with parameters and return value
    public static int add(int a, int b) {
        return a + b;
    }
    
    // Method overloading: same method name, different parameters (double)
    public static double add(double a, double b) {
        return a + b;
    }
    
    // Method overloading: same method name, different parameters (3 int)
    public static int add(int a, int b, int c) {
        return a + b + c;
    }
    
    // Variable arguments (varargs)
    public static int sumAll(int... numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }
    
    // Method with boolean return
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }
    
    // Method with array parameter
    public static double getAverage(int[] numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return (double) sum / numbers.length;
    }
    
    public static void passByValueExample() {
        int value = 10;
        System.out.println("Before method call: " + value);
        changeValue(value);
        System.out.println("After method call: " + value); // Still 10 (not changed)
        
        int[] array = {1, 2, 3};
        System.out.println("Before changing array: " + java.util.Arrays.toString(array));
        changeArray(array);
        System.out.println("After changing array: " + java.util.Arrays.toString(array)); // Changed
    }
    
    public static void changeValue(int val) {
        val = 20;
    }
    
    public static void changeArray(int[] arr) {
        arr[0] = 100;
    }
}

