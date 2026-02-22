package Learning.OOP;

/**
 * Polymorphism in Java
 * Polymorphism means "many forms"
 * Two types: Compile-time (Method Overloading) and Runtime (Method Overriding) * 
 * IMPORTANT INFO:
 * 
 * METHOD OVERLOADING (Compile-time Polymorphism):
 * - Same method name with different parameters
 * - Decision made at compile time
 * - Can differ by:
 *   âœ… Number of parameters
 *   âœ… Type of parameters
 *   âœ… Order of parameters
 * - Cannot differ by return type alone
 * - Improves readability (same action, different inputs)
 * - Example: add(int, int), add(int, int, int), add(double, double)
 * 
 * METHOD OVERRIDING (Runtime Polymorphism):
 * - Child class provides specific implementation of parent method
 * - Same method name, same parameters, same return type
 * - @Override annotation recommended
 * - Child method access should be >= Parent method access
 * - Cannot override final methods
 * - Cannot override static methods (method hiding instead)
 * - Example: Parent: eat() { }, Child: eat() { }
 * 
 * UPCASTING vs DOWNCASTING:
 * 
 * Upcasting (Parent reference to Child object):
 * Parent ref = new Child();  // âœ… Always safe
 * ref.parentMethod();        // âœ… Works
 * ref.childMethod();         // âŒ Compilation error
 * 
 * Downcasting (Child reference from Parent reference):
 * if (ref instanceof Child) {
 *     Child child = (Child) ref;  // âœ… Safe
 *     child.childMethod();        // âœ… Works
 * }
 * 
 * ADVANTAGE OF POLYMORPHISM:
 * 1. Code reusability and flexibility
 * 2. Loose coupling between classes
 * 3. Easier to maintain and extend
 * 4. One interface, multiple behaviors
 * 
 * BEST PRACTICES:
 * 1. Use @Override annotation when overriding
 * 2. Don't overload with too many variations
 * 3. Thrown exceptions in override <= parent class exceptions
 * 4. Always check type before downcasting (instanceof)
 * 5. Prefer composition over complex inheritance */

// Method Overloading (Compile-time Polymorphism)
class Calculator {
    
    // Method 1: add two integers
    public int add(int a, int b) {
        System.out.println("Adding two integers");
        return a + b;
    }
    
    // Method 2: add three integers (overloading)
    public int add(int a, int b, int c) {
        System.out.println("Adding three integers");
        return a + b + c;
    }
    
    // Method 3: add two doubles (overloading)
    public double add(double a, double b) {
        System.out.println("Adding two doubles");
        return a + b;
    }
    
    // Method 4: add int and double (overloading)
    public double add(int a, double b) {
        System.out.println("Adding int and double");
        return a + b;
    }
}

// Method Overriding (Runtime Polymorphism)
// Parent class
class Shape {
    
    public void draw() {
        System.out.println("Drawing a shape");
    }
    
    public double getArea() {
        return 0;
    }
}

// Child class 1
class Circle extends Shape {
    double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a circle with radius " + radius);
    }
    
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}

// Child class 2
class Rectangle extends Shape {
    double length, width;
    
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a rectangle with length " + length + " and width " + width);
    }
    
    @Override
    public double getArea() {
        return length * width;
    }
}

// Child class 3
class Triangle extends Shape {
    double base, height;
    
    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a triangle with base " + base + " and height " + height);
    }
    
    @Override
    public double getArea() {
        return 0.5 * base * height;
    }
}

public class Polymorphism {
    
    public static void main(String[] args) {
        System.out.println("=== Compile-time Polymorphism (Method Overloading) ===\n");
        
        Calculator calc = new Calculator();
        
        System.out.println("Result: " + calc.add(10, 20));
        System.out.println("Result: " + calc.add(10, 20, 30));
        System.out.println("Result: " + calc.add(10.5, 20.5));
        System.out.println("Result: " + calc.add(10, 20.5));
        
        System.out.println("\n=== Runtime Polymorphism (Method Overriding) ===\n");
        
        // Creating different shapes
        Circle circle = new Circle(5);
        Rectangle rectangle = new Rectangle(4, 6);
        Triangle triangle = new Triangle(5, 8);
        
        // Using polymorphic references
        Shape shape1 = new Circle(5);
        Shape shape2 = new Rectangle(4, 6);
        Shape shape3 = new Triangle(5, 8);
        
        System.out.println("--- Individual Objects ---");
        circle.draw();
        System.out.println("Area: " + String.format("%.2f", circle.getArea()));
        
        rectangle.draw();
        System.out.println("Area: " + rectangle.getArea());
        
        triangle.draw();
        System.out.println("Area: " + String.format("%.2f", triangle.getArea()));
        
        System.out.println("\n--- Using Parent Class References ---");
        Shape[] shapes = {shape1, shape2, shape3};
        
        for (Shape shape : shapes) {
            shape.draw();
            System.out.println("Area: " + String.format("%.2f", shape.getArea()));
            System.out.println();
        }
        
        System.out.println("=== Benefits of Polymorphism ===");
        System.out.println("1. Makes code flexible and reusable");
        System.out.println("2. Enables loosely coupled code");
        System.out.println("3. Helps in achieving method overriding");
        System.out.println("4. Improves code maintainability");
    }
}

