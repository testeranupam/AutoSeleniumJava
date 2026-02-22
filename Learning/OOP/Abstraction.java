package Learning.OOP;

/**
 * Abstraction in Java
 * Abstraction is the process of hiding the internal details and showing only the functionality.
 * It can be achieved through:
 * 1. Abstract classes
 * 2. Interfaces
 * 
 * KEY INFO:
 * Abstract Class: Cannot instantiate, can have abstract + concrete methods
 * Abstract Method: No body, must override in child class
 * Abstract vs Interface: Abstract has instance vars + single inheritance, Interface has multiple inheritance
 * Use Abstract: When sharing code among related classes
 * Use Interface: When defining contract for unrelated classes
 */

// Abstract class (cannot be instantiated)
abstract class Vehicle {
    // Abstract method (no body)
    abstract void start();
    abstract void stop();
    abstract void accelerate();
    
    // Concrete method
    public void sound() {
        System.out.println("Vehicle is making some sound");
    }
    
    // Concrete method
    public void displayType() {
        System.out.println("This is a Vehicle");
    }
}

// Concrete class 1
class Car extends Vehicle {
    
    @Override
    void start() {
        System.out.println("Car is starting with an engine sound: Vroom!");
    }
    
    @Override
    void stop() {
        System.out.println("Car is stopping with brake sound: Screeech!");
    }
    
    @Override
    void accelerate() {
        System.out.println("Car is accelerating...");
    }
    
    @Override
    public void sound() {
        System.out.println("Car is making: Honk Honk!");
    }
}

// Concrete class 2
class Motorcycle extends Vehicle {
    
    @Override
    void start() {
        System.out.println("Motorcycle is starting with a loud engine sound: Vrooommmm!");
    }
    
    @Override
    void stop() {
        System.out.println("Motorcycle is stopping: Screeech!");
    }
    
    @Override
    void accelerate() {
        System.out.println("Motorcycle is accelerating quickly...");
    }
    
    @Override
    public void sound() {
        System.out.println("Motorcycle is making: Vroom Vroom!");
    }
}

// Concrete class 3
class Bicycle extends Vehicle {
    
    @Override
    void start() {
        System.out.println("Bicycle is starting by pedaling");
    }
    
    @Override
    void stop() {
        System.out.println("Bicycle is stopping by braking");
    }
    
    @Override
    void accelerate() {
        System.out.println("Bicycle is accelerating by pedaling faster");
    }
    
    @Override
    public void sound() {
        System.out.println("Bicycle is making: Ring Ring!");
    }
}

// Interface 1
interface ElectricVehicle {
    void charge();
    void getRange();
}

// Hybrid Vehicle - implements both abstract class and interface
class ElectricCar extends Vehicle implements ElectricVehicle {
    double batteryCapacity;
    
    public ElectricCar(double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }
    
    @Override
    void start() {
        System.out.println("Electric car is starting silently with electric motor");
    }
    
    @Override
    void stop() {
        System.out.println("Electric car is stopping smoothly");
    }
    
    @Override
    void accelerate() {
        System.out.println("Electric car is accelerating instantly with torque");
    }
    
    @Override
    public void charge() {
        System.out.println("Electric car is charging with battery capacity: " + batteryCapacity + " kWh");
    }
    
    @Override
    public void getRange() {
        System.out.println("Electric car range: approximately " + (batteryCapacity * 5) + " miles");
    }
}

public class Abstraction {
    
    public static void main(String[] args) {
        System.out.println("=== Abstraction with Abstract Classes ===\n");
        
        // Cannot instantiate abstract class directly
        // Vehicle vehicle = new Vehicle(); // Compilation error
        
        // Create concrete class instances
        Vehicle car = new Car();
        Vehicle motorcycle = new Motorcycle();
        Vehicle bicycle = new Bicycle();
        
        System.out.println("--- Car Operations ---");
        car.start();
        car.accelerate();
        car.sound();
        car.stop();
        
        System.out.println("\n--- Motorcycle Operations ---");
        motorcycle.start();
        motorcycle.accelerate();
        motorcycle.sound();
        motorcycle.stop();
        
        System.out.println("\n--- Bicycle Operations ---");
        bicycle.start();
        bicycle.accelerate();
        bicycle.sound();
        bicycle.stop();
        
        System.out.println("\n=== Polymorphism with Abstract Classes ===\n");
        
        // Array of abstract class reference
        Vehicle[] vehicles = {
            new Car(),
            new Motorcycle(),
            new Bicycle(),
            new ElectricCar(100)
        };
        
        System.out.println("--- Operating Multiple Vehicles ---");
        for (Vehicle v : vehicles) {
            v.start();
            v.accelerate();
            v.sound();
            v.stop();
            
            // Check if it's electric
            if (v instanceof ElectricVehicle) {
                ElectricVehicle electric = (ElectricVehicle) v;
                electric.charge();
                electric.getRange();
            }
            System.out.println();
        }
        
        System.out.println("=== Abstraction Benefits ===");
        System.out.println("1. Simplifies complexity");
        System.out.println("2. Defines contract for subclasses");
        System.out.println("3. Improves code maintainability");
        System.out.println("4. Enables polymorphism");
        System.out.println("5. Focuses on what an object does, not how it does it");
    }
}

