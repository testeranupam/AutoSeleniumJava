package Learning.OOP;

/**
 * Static Keyword in Java
 * Static means something belongs to the class, not to an object
 * Can be applied to: variables, methods, blocks, classes (inner classes)
 * 
 * IMPORTANT:
 * 1. Static Variables: Shared across all instances
 * 2. Static Methods: Can only access static members
 * 3. Static Blocks: Execute when class is loaded
 * 4. Static Classes: ONLY possible as INNER classes (not top-level)
 *    âœ… CAN create objects: new OuterClass.StaticInnerClass()
 *    âŒ CANNOT inherit: class Child extends StaticInnerClass { } // ERROR
 * 
 * EXAMPLES:
 * 
 * // ===== Creating Objects from Static Inner Classes =====
 * class Outer {
 *     static class StaticInner {
 *         void display() { System.out.println("Static inner"); }
 *     }
 * }
 * 
 * // âœ… THIS WORKS - Creating object from static inner class
 * Outer.StaticInner obj = new Outer.StaticInner();
 * obj.display();
 * 
 * // ===== Inheritance from Static Inner Classes =====
 * 
 * // âŒ THIS DOES NOT WORK - Cannot inherit from static inner class
 * class Child extends Outer.StaticInner {  }  // COMPILE ERROR!
 * 
 * // Reason: static inner classes are not designed for inheritance
 * // They are independent and should not be extended
 * 
 * // ===== Practical Example =====
 * class Database {
 *     static class Connection {
 *         void connect() { System.out.println("Connected"); }
 *     }
 * }
 * 
 * // âœ… Create objects - ALLOWED
 * Database.Connection conn = new Database.Connection();
 * conn.connect();
 * 
 * // âŒ Inheritance - NOT ALLOWED
 * class MyConnection extends Database.Connection { }  // ERROR!
 */

class Counter {
    // Static variable: shared by all instances
    static int totalCount = 0;
    
    // Instance variable: each object has its own copy
    int instanceCount = 0;
    
    String name;
    
    public Counter(String name) {
        this.name = name;
        totalCount++;
        instanceCount = totalCount;
    }
    
    // Static method: can access only static members
    public static void displayTotalCount() {
        System.out.println("Total objects created: " + totalCount);
        // System.out.println(name); // Error: cannot access instance variable
    }
    
    // Instance method: can access both static and instance members
    public void displayInfo() {
        System.out.println("Name: " + name + ", Instance Count: " + instanceCount + ", Total: " + totalCount);
    }
}

class MathUtils {
    // Static final variable (constant)
    public static final double PI = 3.14159;
    public static final double E = 2.71828;
    
    // Static method
    public static int add(int a, int b) {
        return a + b;
    }
    
    public static int subtract(int a, int b) {
        return a - b;
    }
    
    public static int multiply(int a, int b) {
        return a * b;
    }
    
    public static double divide(double a, double b) {
        if (b == 0) {
            System.out.println("Division by zero!");
            return 0;
        }
        return a / b;
    }
}

class StaticBlockExample {
    static int value1;
    static int value2;
    static String message;
    
    // Static block - executes when class is loaded
    static {
        System.out.println("Static block 1 - Class is being loaded");
        value1 = 100;
        value2 = 200;
        message = "Static initialization complete";
    }
    
    // Another static block
    static {
        System.out.println("Static block 2 - Another static initialization");
        value1 = value1 + 50;
    }
    
    public static void displayStaticValues() {
        System.out.println("Value1: " + value1);
        System.out.println("Value2: " + value2);
        System.out.println("Message: " + message);
    }
}

class Company {
    // Static variable
    static int employeeCount = 0;
    static String companyName = "TechCorp";
    
    String employeeName;
    int employeeId;
    
    public Company(String name, int id) {
        this.employeeName = name;
        this.employeeId = id;
        employeeCount++; // Increment shared static variable
    }
    
    // Static method to get company info
    public static void displayCompanyInfo() {
        System.out.println("Company: " + companyName);
        System.out.println("Total Employees: " + employeeCount);
    }
    
    // Instance method
    public void displayEmployeeInfo() {
        System.out.println("Employee: " + employeeName + ", ID: " + employeeId + 
                         ", Company: " + companyName);
    }
}

// ============= STATIC INNER/NESTED CLASSES =============
// Static classes are ONLY possible as inner classes (inside another class)
class OuterClass {
    private String outerVar = "Outer class variable";
    private static String staticOuterVar = "Static outer class variable";
    
    // Static inner class
    static class StaticInnerClass {
        String innerVar = "Static inner class variable";
        static String staticInnerVar = "Static inner variable";
        
        void displayInfo() {
            System.out.println("Inner Variable: " + innerVar);
            System.out.println("Static Inner Variable: " + staticInnerVar);
            System.out.println("Access outer static: " + staticOuterVar);
            // System.out.println(outerVar); // ERROR! Cannot access non-static outer variable
        }
    }
    
    // Non-static (regular) inner class
    class RegularInnerClass {
        String innerVar = "Regular inner class variable";
        
        void displayInfo() {
            System.out.println("Inner Variable: " + innerVar);
            System.out.println("Outer Variable: " + outerVar);
            System.out.println("Static Outer Variable: " + staticOuterVar);
        }
    }
}

// ============= STATIC CLASS OBJECT CREATION & INHERITANCE =============
class StaticClassExample {
    // âœ… YES - You CAN create objects from static classes
    static class Database {
        String dbName;
        String connection;
        
        public Database(String name, String conn) {
            this.dbName = name;
            this.connection = conn;
        }
        
        void connect() {
            System.out.println("Connecting to " + dbName + " using " + connection);
        }
        
        void query(String sql) {
            System.out.println("Executing: " + sql);
        }
    }
    
    // âŒ NO - You CANNOT inherit from static classes
    // The following would cause a compile error:
    // class Child extends Database {  } // ERROR! Cannot extend static inner class
    
    // However, you can create objects of the static inner class:
    public static void createStaticClassObjects() {
        // Creating object from static inner class
        Database db1 = new Database("MySQL", "jdbc:mysql://localhost:3306");
        Database db2 = new Database("PostgreSQL", "jdbc:postgresql://localhost:5432");
        
        db1.connect();
        db1.query("SELECT * FROM users");
        
        db2.connect();
        db2.query("INSERT INTO products VALUES (...)");
    }
}

// Outside the class, accessing static inner class requires outer class reference
class AccessStaticInnerClass {
    public static void demonstrateStaticInner() {
        // Create object from static inner class
        OuterClass.StaticInnerClass staticInner = new OuterClass.StaticInnerClass();
        staticInner.displayInfo();
        
        // Create object from regular inner class (requires outer class object)
        OuterClass outer = new OuterClass();
        OuterClass.RegularInnerClass regularInner = outer.new RegularInnerClass();
        regularInner.displayInfo();
    }
}

public class StaticKeyword {
    
    public static void main(String[] args) {
        System.out.println("=== Static Variables ===\n");
        
        // Creating counter objects
        Counter c1 = new Counter("Counter1");
        Counter c2 = new Counter("Counter2");
        Counter c3 = new Counter("Counter3");
        
        c1.displayInfo();
        c2.displayInfo();
        c3.displayInfo();
        
        // Accessing static method via class name
        Counter.displayTotalCount();
        
        System.out.println("\n=== Static Methods ===\n");
        
        // Accessing static methods via class name
        System.out.println("10 + 5 = " + MathUtils.add(10, 5));
        System.out.println("10 - 5 = " + MathUtils.subtract(10, 5));
        System.out.println("10 * 5 = " + MathUtils.multiply(10, 5));
        System.out.println("10 / 2 = " + MathUtils.divide(10, 2));
        
        System.out.println("\n=== Static Constants ===\n");
        
        System.out.println("PI = " + MathUtils.PI);
        System.out.println("E = " + MathUtils.E);
        System.out.println("Circle area (r=5): " + (MathUtils.PI * 5 * 5));
        System.out.println("Exponential: " + Math.pow(MathUtils.E, 2));
        
        System.out.println("\n=== Static Blocks ===\n");
        
        StaticBlockExample.displayStaticValues();
        
        System.out.println("\n=== Real-world Example: Company ===\n");
        
        Company emp1 = new Company("John", 101);
        Company emp2 = new Company("Sarah", 102);
        Company emp3 = new Company("Mike", 103);
        Company emp4 = new Company("Emma", 104);
        
        emp1.displayEmployeeInfo();
        emp2.displayEmployeeInfo();
        emp3.displayEmployeeInfo();
        emp4.displayEmployeeInfo();
        
        System.out.println();
        Company.displayCompanyInfo();
        
        System.out.println("\n=== Static vs Instance Members ===");
        System.out.println("1. Static members: Shared by all objects");
        System.out.println("2. Instance members: Each object has its own copy");
        System.out.println("3. Static methods: Cannot access instance members");
        System.out.println("4. Instance methods: Can access static and instance members");
        System.out.println("5. Static blocks: Execute when class is loaded");
        
        System.out.println("\n=== Static Inner Classes ===");
        System.out.println("âœ… Can create objects from static inner classes:");
        StaticClassExample.createStaticClassObjects();
        
        System.out.println("\n=== Accessing Static Inner Class ===");
        AccessStaticInnerClass.demonstrateStaticInner();
        
        System.out.println("\n=== Static Classes vs Inheritance ===");
        System.out.println("âœ… YES - Objects CAN be created from static classes");
        System.out.println("âŒ NO  - Static classes CANNOT be inherited/extended");
    }
}

