package Learning.OOP;

/**
 * Access Modifiers and Keywords in Java
 * 
 * ACCESS MODIFIERS:
 * 1. public    - Accessible from anywhere
 * 2. private   - Accessible only within the class
 * 3. protected - Accessible within package and subclasses
 * 4. default   - Accessible only within the same package (no modifier)
 * 
 * OTHER MODIFIERS/KEYWORDS:
 * 1. static    - Belongs to class, not instance
 * 2. final     - Cannot be modified, overridden, or extended
 * 3. abstract  - Must be implemented by subclasses
 * 4. synchronized - Thread-safe, one thread at a time
 * 5. volatile  - Not cached by threads
 */

// ============= ACCESS MODIFIERS EXAMPLES =============

// Example 1: Using PUBLIC access modifier
class PublicExample {
    public String publicVar = "Accessible from anywhere";
    public int publicNumber = 100;
    
    public void publicMethod() {
        System.out.println("Public method - accessible everywhere");
    }
}

// Example 2: Using PRIVATE access modifier
class PrivateExample {
    private String privateVar = "Accessible only within this class";
    private int privateNumber = 200;
    
    private void privateMethod() {
        System.out.println("Private method - accessible only here");
    }
    
    // Public getter to access private variable
    public String getPrivateVar() {
        return privateVar;
    }
    
    // Public setter to modify private variable
    public void setPrivateVar(String value) {
        this.privateVar = value;
    }
}

// Example 3: Using PROTECTED access modifier
class ProtectedExample {
    protected String protectedVar = "Accessible in package and subclasses";
    protected int protectedNumber = 300;
    
    protected void protectedMethod() {
        System.out.println("Protected method - accessible in package and subclasses");
    }
}

// Example 4: Using DEFAULT (package-private) access modifier
class DefaultExample {
    // No modifier = default/package-private
    String defaultVar = "Accessible only within the same package";
    int defaultNumber = 400;
    
    void defaultMethod() {
        System.out.println("Default method - accessible only in same package");
    }
}

// Example 5: Child class extending ProtectedExample
class ChildOfProtected extends ProtectedExample {
    public void accessParentProtected() {
        System.out.println("Accessing parent protected: " + protectedVar);
        protectedMethod(); // Can access protected method
    }
}

// ============= STATIC KEYWORD =============
class StaticKeywordExample {
    static int staticCounter = 0;        // Shared by all objects
    int instanceCounter = 0;              // Each object has its own
    
    static String staticVar = "Belongs to class";
    
    // Static method - can only access static variables
    static void staticMethod() {
        staticCounter++;
        System.out.println("Static method called. Counter: " + staticCounter);
        // System.out.println(instanceCounter); // ERROR! Can't access instance variable
    }
    
    // Instance method - can access both static and instance variables
    void instanceMethod() {
        instanceCounter++;
        staticCounter++;
        System.out.println("Instance method called. Static counter: " + staticCounter + 
                         ", Instance counter: " + instanceCounter);
    }
    
    // Static block - executed once when class is loaded
    static {
        System.out.println("Static block executed - class initialization");
        staticCounter = 0;
    }
}

// ============= FINAL KEYWORD =============
final class FinalClassExample {
    // Cannot extend this class - it's final
    
    final int finalVariable = 100;          // Cannot be modified
    final String finalString = "Constant";  // Must be initialized
    
    final void finalMethod() {
        System.out.println("Final method - cannot be overridden");
    }
}

// Attempting to extend final class - WOULD CAUSE COMPILE ERROR
// class ChildOfFinal extends FinalClassExample { } // ERROR!

// ============= ABSTRACT KEYWORD =============
abstract class AbstractAnimal {
    String name;
    
    abstract void makeSound();  // Abstract method - must be implemented by subclasses
    
    abstract void move();       // Another abstract method
    
    // Concrete method - implemented
    public void sleep() {
        System.out.println(name + " is sleeping...");
    }
}

class ConcreteAnimal extends AbstractAnimal {
    public ConcreteAnimal(String name) {
        this.name = name;
    }
    
    @Override
    void makeSound() {
        System.out.println(name + " makes a sound");
    }
    
    @Override
    void move() {
        System.out.println(name + " is moving");
    }
}

// Abstract class with abstract and concrete methods
abstract class Vehicle {
    abstract void start();
    abstract void stop();
    
    void refuel() {
        System.out.println("Refueling vehicle...");
    }
}

class Car extends Vehicle {
    @Override
    void start() {
        System.out.println("Car engine started");
    }
    
    @Override
    void stop() {
        System.out.println("Car engine stopped");
    }
}

// ============= SYNCHRONIZED KEYWORD (Thread-safe) =============
class Counter {
    private int count = 0;
    
    // Non-synchronized - multiple threads can access simultaneously
    public void increment() {
        count++;
    }
    
    // Synchronized - only one thread can execute at a time
    public synchronized void incrementSafe() {
        count++;
    }
    
    // Synchronized block - more granular control
    public void incrementSafeBlock() {
        synchronized(this) {
            count++;
        }
    }
    
    public synchronized int getCount() {
        return count;
    }
}

// ============= VOLATILE KEYWORD (Thread visibility) =============
class VolatileExample {
    volatile boolean flag = false;  // Changes visible to all threads immediately
    
    void checkFlag() {
        while(!flag) {
            // Without volatile, this might loop infinitely
            // With volatile, changes are immediately visible
        }
        System.out.println("Flag is now true!");
    }
}

// ============= COMBINED EXAMPLE =============
class Employee {
    // Access modifiers + keywords combination
    private static int totalEmployees = 0;      // private + static
    private final String employeeId;            // private + final
    protected static final String COMPANY = "TechCorp"; // protected + static + final
    
    private String name;
    private double salary;
    private final boolean isPermanent;          // final variable
    
    // Constructor
    public Employee(String id, String name, double salary, boolean isPermanent) {
        this.employeeId = id;
        this.name = name;
        this.salary = salary;
        this.isPermanent = isPermanent;
        totalEmployees++;
    }
    
    // Public method
    public void displayInfo() {
        System.out.println("ID: " + employeeId + ", Name: " + name + ", Salary: " + salary);
    }
    
    // Static method - can be called without object
    public static int getTotalEmployees() {
        return totalEmployees;
    }
    
    // Ternary operator in method
    public String getEmploymentType() {
        return isPermanent ? "Permanent" : "Contract";
    }
    
    // Synchronized method - thread-safe
    public synchronized void giveBonusIfEligible() {
        double bonus = (salary > 50000) ? (salary * 0.10) : 0;
        System.out.println("Bonus: " + bonus);
    }
}

// ============= MAIN METHOD =============
public class AccessModifiersAndKeywords {
    
    public static void main(String[] args) {
        System.out.println("================== ACCESS MODIFIERS ==================\n");
        
        // PUBLIC access
        PublicExample pub = new PublicExample();
        System.out.println("Public Variable: " + pub.publicVar);
        pub.publicMethod();
        
        // PRIVATE access
        PrivateExample priv = new PrivateExample();
        System.out.println("Private Variable (via getter): " + priv.getPrivateVar());
        priv.setPrivateVar("Modified value");
        System.out.println("After setter: " + priv.getPrivateVar());
        
        // PROTECTED access through inheritance
        ChildOfProtected child = new ChildOfProtected();
        child.accessParentProtected();
        
        System.out.println("\n================== STATIC KEYWORD ==================\n");
        
        StaticKeywordExample.staticMethod();
        
        StaticKeywordExample obj1 = new StaticKeywordExample();
        obj1.instanceMethod();
        
        StaticKeywordExample obj2 = new StaticKeywordExample();
        obj2.instanceMethod();
        
        System.out.println("Static counter is shared: " + StaticKeywordExample.staticCounter);
        
        System.out.println("\n================== FINAL KEYWORD ==================\n");
        
        FinalClassExample finalObj = new FinalClassExample();
        System.out.println("Final variable: " + finalObj.finalVariable);
        // finalObj.finalVariable = 200; // ERROR! Final variable cannot be changed
        finalObj.finalMethod();
        
        System.out.println("\n================== ABSTRACT KEYWORD ==================\n");
        
        // Cannot instantiate abstract class
        // AbstractAnimal animal = new AbstractAnimal(); // ERROR!
        
        ConcreteAnimal dog = new ConcreteAnimal("Dog");
        dog.makeSound();
        dog.move();
        dog.sleep();
        
        Vehicle car = new Car();
        car.start();
        car.refuel();
        car.stop();
        
        System.out.println("\n================== SYNCHRONIZED KEYWORD ==================\n");
        
        Counter counter = new Counter();
        counter.incrementSafe();
        counter.incrementSafeBlock();
        System.out.println("Safe Counter: " + counter.getCount());
        
        System.out.println("\n================== COMBINED EXAMPLE ==================\n");
        
        Employee emp1 = new Employee("E001", "John", 60000, true);
        Employee emp2 = new Employee("E002", "Sarah", 45000, false);
        
        emp1.displayInfo();
        System.out.println("Employment Type: " + emp1.getEmploymentType());
        emp1.giveBonusIfEligible();
        
        emp2.displayInfo();
        System.out.println("Employment Type: " + emp2.getEmploymentType());
        emp2.giveBonusIfEligible();
        
        System.out.println("Total Employees: " + Employee.getTotalEmployees());
        System.out.println("Company: " + Employee.COMPANY);
    }
}

