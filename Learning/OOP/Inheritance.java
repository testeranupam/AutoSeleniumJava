package Learning.OOP;

/**
 * Inheritance in Java
 * Inheritance allows a class to inherit properties and methods from another class
 * Key Concepts:
 * 1. Parent/Super class
 * 2. Child/Sub class
 * 3. IS-A relationship
 * 4. Method overriding
 * 5. super keyword
 * 
 * IMPORTANT INFO:
 * 
 * TYPES OF INHERITANCE:
 * 
 * 1. SINGLE INHERITANCE:
 *    class Child extends Parent { }
 *    One child, one parent
 * 
 * 2. MULTILEVEL INHERITANCE:
 *    class A { }
 *    class B extends A { }
 *    class C extends B { }
 *    Chain of inheritance
 * 
 * 3. HIERARCHICAL INHERITANCE:
 *    class Child1 extends Parent { }
 *    class Child2 extends Parent { }
 *    Multiple children, one parent
 * 
 * 4. MULTIPLE INHERITANCE (NOT SUPPORTED):
 *    âŒ class Child extends Parent1, Parent2 { }  // ERROR!
 *    â†’ Use interfaces instead: class Child implements Interface1, Interface2 { }
 * 
 * SUPER KEYWORD USAGE:
 * 
 * super.methodName();        // Call parent method
 * super.variableName;        // Access parent variable
 * super(param1, param2);     // Call parent constructor
 * 
 * IMPORTANT RULES:
 * âœ… Child inherits all public/protected members
 * âŒ Cannot access parent private members directly
 * âœ… super() must be first statement in child constructor
 * âœ… If parent has parameterized constructor, child must call it
 * âœ… Child can override parent methods
 * âŒ Cannot override final methods
 * âŒ Cannot override static methods (method hiding instead)
 * âœ… Overridden method access must be >= parent method access
 * 
 * METHOD OVERRIDING RULES:
 * 1. Same method name
 * 2. Same parameters (type, number, order)
 * 3. Same return type (or subtype - covariant)
 * 4. Cannot throw more checked exceptions
 * 5. Must use @Override annotation (recommended)
 * 
 * CONSTRUCTOR INHERITANCE:
 * âŒ Constructors are NOT inherited
 * âœ… Can call parent constructor using super()
 * âœ… If parent has no-arg constructor, it's called automatically
 * 
 * IS-A RELATIONSHIP:
 * Dog IS-A Animal
 * Car IS-A Vehicle
 * Student IS-A Person
 * 
 * BEST PRACTICES:
 * 1. Use inheritance for IS-A relationships
 * 2. Prefer composition over inheritance when possible
 * 3. Use final keyword to prevent unwanted inheritance
 * 4. Call super() in child constructors
 * 5. Use @Override annotation
 * 6. Keep hierarchy shallow (max 3-4 levels)
 */

// Parent class (Super class)
class Animal {
    String name;
    String color;
    
    public Animal(String name, String color) {
        this.name = name;
        this.color = color;
    }
    
    public void eat() {
        System.out.println(name + " is eating...");
    }
    
    public void sleep() {
        System.out.println(name + " is sleeping...");
    }
    
    public void displayInfo() {
        System.out.println("Name: " + name + ", Color: " + color);
    }
}

// Child class 1
class Dog extends Animal {
    String breed;
    
    public Dog(String name, String color, String breed) {
        super(name, color); // Calling parent constructor
        this.breed = breed;
    }
    
    // Overriding parent method
    @Override
    public void eat() {
        System.out.println(name + " (Dog) is eating dog food...");
    }
    
    public void bark() {
        System.out.println(name + " is barking: Woof! Woof!");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo(); // Calling parent method
        System.out.println("Breed: " + breed);
    }
}

// Child class 2
class Cat extends Animal {
    String color_pattern;
    
    public Cat(String name, String color, String color_pattern) {
        super(name, color);
        this.color_pattern = color_pattern;
    }
    
    @Override
    public void eat() {
        System.out.println(name + " (Cat) is eating cat food...");
    }
    
    public void meow() {
        System.out.println(name + " is meowing: Meow! Meow!");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Pattern: " + color_pattern);
    }
}

// Bird class
class Bird extends Animal {
    
    public Bird(String name, String color) {
        super(name, color);
    }
    
    @Override
    public void eat() {
        System.out.println(name + " (Bird) is eating seeds...");
    }
    
    public void fly() {
        System.out.println(name + " is flying in the sky...");
    }
}

public class Inheritance {
    
    public static void main(String[] args) {
        System.out.println("=== Inheritance Example ===\n");
        
        // Creating Dog object
        Dog dog = new Dog("Buddy", "Brown", "Golden Retriever");
        System.out.println("--- Dog ---");
        dog.displayInfo();
        dog.eat();
        dog.sleep();
        dog.bark();
        
        System.out.println("\n--- Cat ---");
        Cat cat = new Cat("Whiskers", "Orange", "Striped");
        cat.displayInfo();
        cat.eat();
        cat.sleep();
        cat.meow();
        
        System.out.println("\n--- Bird ---");
        Bird bird = new Bird("Tweety", "Yellow");
        bird.displayInfo();
        bird.eat();
        bird.sleep();
        bird.fly();
        
        System.out.println("\n=== Polymorphism with Inheritance ===");
        // Parent class reference can hold child class objects
        Animal myPet1 = new Dog("Max", "Black", "Labrador");
        Animal myPet2 = new Cat("Mittens", "White", "Spotted");
        Animal myPet3 = new Bird("Polly", "Green");
        
        System.out.println("Using parent reference:");
        myPet1.eat(); // Dog's eat method
        myPet2.eat(); // Cat's eat method
        myPet3.eat(); // Bird's eat method
        
        System.out.println("\n=== instanceof Operator ===");
        System.out.println("myPet1 instanceof Dog: " + (myPet1 instanceof Dog));
        System.out.println("myPet1 instanceof Animal: " + (myPet1 instanceof Animal));
        System.out.println("myPet2 instanceof Cat: " + (myPet2 instanceof Cat));
    }
}

