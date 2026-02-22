package Learning.OOP;

/**
 * Interfaces in Java
 * Interface is a contract that specifies what a class should do.
 * Key features:
 * 1. Cannot be instantiated
 * 2. Methods are abstract (no body)
 * 3. Variables are final and static
 * 4. A class can implement multiple interfaces
 * 
 * IMPORTANT INFO:
 * 
 * INTERFACE BASICS:
 * - Define contract/behavior without implementation
 * - All methods are public abstract by default
 * - All variables are public static final (constants)
 * - Cannot have constructors
 * - Cannot have instance variables
 * - Syntax: interface InterfaceName { }
 * - Implement using: class ClassName implements InterfaceName { }
 * 
 * MULTIPLE INTERFACE IMPLEMENTATION:
 * class MyClass implements Interface1, Interface2, Interface3 { }
 * A class can implement multiple interfaces but extend only one class
 * 
 * DEFAULT METHODS (Java 8+):
 * - Interface can have concrete methods with 'default' keyword
 * - Example: default void myMethod() { implementation }
 * - Provides backward compatibility
 * 
 * STATIC METHODS IN INTERFACES (Java 8+):
 * - Can define static methods
 * - Must be called with interface name: Interface.staticMethod()
 * - Cannot be overridden by implementing class
 * 
 * PRIVATE METHODS IN INTERFACES (Java 9+):
 * - Can have private helper methods
 * - Default methods can call private methods
 * - Cannot be called from outside
 * 
 * IMPLEMENTING INTERFACE:
 * âœ… Must override all abstract methods
 * âœ… Can override default methods (optional)
 * âŒ Cannot inherit static methods (can hide them)
 * 
 * MARKER INTERFACES:
 * - Interfaces with no methods (empty)
 * - Example: Serializable, Cloneable
 * - Used to mark/tag classes
 * 
 * BEST PRACTICES:
 * 1. Use meaningful interface names (adjectives: Runnable, Comparable)
 * 2. Keep interfaces focused (single responsibility)
 * 3. Use default methods sparingly
 * 4. Document interface contracts clearly
 * 5. Prefer interfaces for API definitions
 */

// Interface 1: For animals that can walk
interface Walkable {
    void walk();
}

// Interface 2: For animals that can swim
interface Swimmable {
    void swim();
}

// Interface 3: For animals that can fly
interface Flyable {
    void fly();
}

// Interface 4: Complete animal behavior
interface AnimalBehavior {
    void eat();
    void sleep();
    String getSpecies();
}

// Concrete class implementing single interface
class Dog implements Walkable, AnimalBehavior {
    private String name;
    
    public Dog(String name) {
        this.name = name;
    }
    
    @Override
    public void walk() {
        System.out.println(name + " is walking on four legs");
    }
    
    @Override
    public void eat() {
        System.out.println(name + " is eating dog food");
    }
    
    @Override
    public void sleep() {
        System.out.println(name + " is sleeping in a cozy bed");
    }
    
    @Override
    public String getSpecies() {
        return "Canine";
    }
}

// Concrete class implementing multiple interfaces
class Duck implements Walkable, Swimmable, Flyable, AnimalBehavior {
    private String name;
    
    public Duck(String name) {
        this.name = name;
    }
    
    @Override
    public void walk() {
        System.out.println(name + " is walking with webbed feet");
    }
    
    @Override
    public void swim() {
        System.out.println(name + " is swimming in the pond");
    }
    
    @Override
    public void fly() {
        System.out.println(name + " is flying in V formation");
    }
    
    @Override
    public void eat() {
        System.out.println(name + " is eating insects and plants");
    }
    
    @Override
    public void sleep() {
        System.out.println(name + " is sleeping on water");
    }
    
    @Override
    public String getSpecies() {
        return "Waterfowl";
    }
}

// Concrete class implementing multiple interfaces
class Penguin implements Swimmable, AnimalBehavior {
    private String name;
    
    public Penguin(String name) {
        this.name = name;
    }
    
    @Override
    public void swim() {
        System.out.println(name + " is swimming underwater like a torpedo");
    }
    
    @Override
    public void eat() {
        System.out.println(name + " is eating fish");
    }
    
    @Override
    public void sleep() {
        System.out.println(name + " is sleeping on the ice");
    }
    
    @Override
    public String getSpecies() {
        return "Aquatic Bird";
    }
}

// Another interface example - for vehicles
interface Drivable {
    void drive();
    void park();
    int getMaxSpeed();
}

class Tesla implements Drivable {
    private String model;
    private int maxSpeed = 200;
    
    public Tesla(String model) {
        this.model = model;
    }
    
    @Override
    public void drive() {
        System.out.println("Tesla " + model + " is driving autonomously");
    }
    
    @Override
    public void park() {
        System.out.println("Tesla " + model + " is parking automatically");
    }
    
    @Override
    public int getMaxSpeed() {
        return maxSpeed;
    }
}

public class Interfaces {
    
    public static void main(String[] args) {
        System.out.println("=== Interfaces in Java ===\n");
        
        System.out.println("--- Dog (Single Interface) ---");
        Dog dog = new Dog("Buddy");
        dog.walk();
        dog.eat();
        dog.sleep();
        System.out.println("Species: " + dog.getSpecies());
        
        System.out.println("\n--- Duck (Multiple Interfaces) ---");
        Duck duck = new Duck("Donald");
        duck.walk();
        duck.swim();
        duck.fly();
        duck.eat();
        duck.sleep();
        System.out.println("Species: " + duck.getSpecies());
        
        System.out.println("\n--- Penguin (Multiple Interfaces) ---");
        Penguin penguin = new Penguin("Pingu");
        penguin.swim();
        penguin.eat();
        penguin.sleep();
        System.out.println("Species: " + penguin.getSpecies());
        
        System.out.println("\n=== Polymorphism with Interfaces ===\n");
        
        // Using interface as type
        Walkable[] walkers = {dog, duck};
        System.out.println("--- All walkers ---");
        for (Walkable walker : walkers) {
            walker.walk();
        }
        
        Swimmable[] swimmers = {duck, penguin};
        System.out.println("\n--- All swimmers ---");
        for (Swimmable swimmer : swimmers) {
            swimmer.swim();
        }
        
        AnimalBehavior[] animals = {dog, duck, penguin};
        System.out.println("\n--- All animals eat ---");
        for (AnimalBehavior animal : animals) {
            animal.eat();
        }
        
        System.out.println("\n--- Vehicle Interface Example ---");
        Tesla car = new Tesla("Model S");
        car.drive();
        car.park();
        System.out.println("Max Speed: " + car.getMaxSpeed() + " km/h");
        
        System.out.println("\n=== Interface Advantages ===");
        System.out.println("1. Supports multiple inheritance in Java");
        System.out.println("2. Defines contract/protocol for implementations");
        System.out.println("3. Promotes loose coupling");
        System.out.println("4. Enables polymorphism");
        System.out.println("5. Improves code flexibility and maintainability");
        System.out.println("6. Allows unrelated classes to implement same interface");
    }
}

