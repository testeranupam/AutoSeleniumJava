package Learning.Basic;

/**
 * Variables and Operators in Java
 * 1. Variables: Local, Instance, Static
 * 2. Operators: Arithmetic, Relational, Logical, Bitwise, Assignment
 */
public class VariablesAndOperators {
    
    // Instance variable (belongs to object)
    int instanceVar = 10;
    
    // Static variable (belongs to class)
    static int staticVar = 20;
    
    public static void main(String[] args) {
        System.out.println("=== Variables ===");
        
        // Local variable (belongs to method)
        int localVar = 30;
        System.out.println("Local Variable: " + localVar);
        
        // Instance and Static Variables
        VariablesAndOperators obj = new VariablesAndOperators();
        System.out.println("Instance Variable: " + obj.instanceVar);
        System.out.println("Static Variable: " + staticVar);
        
        System.out.println("\n=== Arithmetic Operators ===");
        int a = 20, b = 10;
        System.out.println("Addition: " + a + " + " + b + " = " + (a + b));
        System.out.println("Subtraction: " + a + " - " + b + " = " + (a - b));
        System.out.println("Multiplication: " + a + " * " + b + " = " + (a * b));
        System.out.println("Division: " + a + " / " + b + " = " + (a / b));
        System.out.println("Modulus: " + a + " % " + b + " = " + (a % b));
        
        System.out.println("\n=== Increment and Decrement ===");
        int x = 5;
        System.out.println("Post-increment: x = " + x + ", x++ = " + x++);
        System.out.println("After post-increment: x = " + x);
        System.out.println("Pre-increment: ++x = " + ++x);
        System.out.println("Post-decrement: x = " + x + ", x-- = " + x--);
        System.out.println("After post-decrement: x = " + x);
        System.out.println("Pre-decrement: --x = " + --x);
        
        System.out.println("\n=== Relational Operators ===");
        int p = 15, q = 15;
        System.out.println("p = " + p + ", q = " + q);
        System.out.println("p == q: " + (p == q));
        System.out.println("p != q: " + (p != q));
        System.out.println("p > q: " + (p > q));
        System.out.println("p < q: " + (p < q));
        System.out.println("p >= q: " + (p >= q));
        System.out.println("p <= q: " + (p <= q));
        
        System.out.println("\n=== Logical Operators ===");
        boolean condition1 = true, condition2 = false;
        System.out.println("condition1: " + condition1 + ", condition2: " + condition2);
        System.out.println("condition1 && condition2 (AND): " + (condition1 && condition2));
        System.out.println("condition1 || condition2 (OR): " + (condition1 || condition2));
        System.out.println("!condition1 (NOT): " + (!condition1));
        
        System.out.println("\n=== Bitwise Operators ===");
        int num1 = 5, num2 = 3;
        System.out.println("num1 = " + num1 + " (Binary: " + Integer.toBinaryString(num1) + ")");
        System.out.println("num2 = " + num2 + " (Binary: " + Integer.toBinaryString(num2) + ")");
        System.out.println("num1 & num2 (AND): " + (num1 & num2));
        System.out.println("num1 | num2 (OR): " + (num1 | num2));
        System.out.println("num1 ^ num2 (XOR): " + (num1 ^ num2));
        System.out.println("~num1 (NOT): " + (~num1));
        System.out.println("num1 << 1 (Left Shift): " + (num1 << 1));
        System.out.println("num1 >> 1 (Right Shift): " + (num1 >> 1));
        
        System.out.println("\n=== Assignment Operators ===");
        int num = 10;
        System.out.println("num = " + num);
        num += 5;
        System.out.println("num += 5: " + num);
        num -= 3;
        System.out.println("num -= 3: " + num);
        num *= 2;
        System.out.println("num *= 2: " + num);
        num /= 4;
        System.out.println("num /= 4: " + num);
        
        System.out.println("\n=== Ternary Operator ===");
        // Syntax: condition ? valueIfTrue : valueIfFalse
        int age = 20;
        String status = (age >= 18) ? "Adult" : "Minor";
        System.out.println("Age: " + age + ", Status: " + status);
        
        // Multiple conditions (nested ternary)
        int marks = 75;
        String grade = (marks >= 90) ? "A" : 
                      (marks >= 80) ? "B" : 
                      (marks >= 70) ? "C" : "F";
        System.out.println("Marks: " + marks + ", Grade: " + grade);
        
        // With different data types
        int number = 15;
        String description = (number > 10) ? "Greater than 10" : "Less than or equal to 10";
        System.out.println("Number: " + number + " is " + description);
    }
}

