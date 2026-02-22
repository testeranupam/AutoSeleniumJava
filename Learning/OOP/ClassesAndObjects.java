package Learning.OOP;

/**
 * Classes and Objects in Java
 * This demonstrates the fundamental concepts of OOP
 * 
 * KEY INFO:
 * Class: Blueprint/template, Object: Instance of class
 * Constructor: Same name as class, no return type, initializes objects
 * this keyword: Refers to current object (this.var, this.method())
 * new keyword: Allocates memory in heap, calls constructor
 * Garbage Collection: Automatic memory cleanup for unused objects
 */

// A class is a blueprint for creating objects
class Car {
    // Attributes (data members)
    String color;
    String model;
    int year;
    
    // Constructor (special method to initialize objects)
    public Car(String color, String model, int year) {
        this.color = color;
        this.model = model;
        this.year = year;
    }
    
    // Methods (behaviors)
    public void displayInfo() {
        System.out.println("Car: " + model + ", Color: " + color + ", Year: " + year);
    }
    
    public void drive() {
        System.out.println(model + " is driving...");
    }
}

// Another class example
class Person {
    private String name; // Private: only accessible within class
    private int age; // Private
    
    // Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Getter methods
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    // Setter methods
    public void setName(String name) {
        this.name = name;
    }
    
    public void setAge(int age) {
        if (age > 0 && age < 150) {
            this.age = age;
        }
    }
    
    public void introduce() {
        System.out.println("Hi, I'm " + name + " and I'm " + age + " years old.");
    }
}

public class ClassesAndObjects {
    
    public static void main(String[] args) {
        System.out.println("=== Creating Objects from Classes ===");
        
        // Creating Car objects
        Car car1 = new Car("Red", "Tesla Model S", 2023);
        Car car2 = new Car("Black", "BMW 7 Series", 2022);
        
        car1.displayInfo();
        car1.drive();
        
        car2.displayInfo();
        car2.drive();
        
        System.out.println("\n=== Using Person Class ===");
        
        // Creating Person objects
        Person person1 = new Person("John", 25);
        Person person2 = new Person("Sarah", 30);
        
        person1.introduce();
        person2.introduce();
        
        System.out.println("\n=== Using Getters and Setters ===");
        System.out.println("Person 1 Name: " + person1.getName());
        System.out.println("Person 1 Age: " + person1.getAge());
        
        person1.setAge(26);
        System.out.println("After setting age to 26: " + person1.getAge());
        
        System.out.println("\n=== Object References ===");
        Car car3 = car1; // Both reference same object
        System.out.println("car1 and car3 reference same object: " + (car1 == car3));
        System.out.println("car1 and car2 reference different objects: " + (car1 == car2));
        
        // But they have equal values:
        System.out.println("car1 and car2 have equal values: " + (car1.model.equals(car2.model)));
    }
}

