package Learning.Advanced;

import java.util.*;

/**
 * Generics in Java
 * Generics enable types (classes and methods) to operate on objects of various types
 * while providing compile-time type safety
 * Key benefits: Type safety, elimination of casts, enabling programmers to implement 
 * generic algorithms
 * 
 * KEY INFO:
 * Generic Class: class Box<T> { }
 * Generic Method: <T> void method(T param)
 * Bounded: <T extends Number> - T must be Number or subclass
 * Wildcards: <?> any type, <? extends Type> upper bound, <? super Type> lower bound
 * Type Erasure: Generics removed at runtime (backward compatibility)
 * Cannot create generic arrays: new T[10] - ERROR
 */

// Generic class
class Container<T> {
    private T value;
    
    public Container(T value) {
        this.value = value;
    }
    
    public T getValue() {
        return value;
    }
    
    public void setValue(T value) {
        this.value = value;
    }
    
    public void display() {
        System.out.println("Value: " + value + ", Type: " + value.getClass().getName());
    }
}

// Generic class with multiple type parameters
class Pair<K, V> {
    private K key;
    private V value;
    
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    public K getKey() {
        return key;
    }
    
    public V getValue() {
        return value;
    }
    
    public void display() {
        System.out.println("Key: " + key + ", Value: " + value);
    }
}

// Generic class with bounded type parameter
class NumberContainer<T extends Number> {
    private T number;
    
    public NumberContainer(T number) {
        this.number = number;
    }
    
    public double toDouble() {
        return number.doubleValue();
    }
    
    public T getNumber() {
        return number;
    }
}

// Generic interface
interface DataProcessor<T> {
    void process(T data);
    T retrieve();
}

// Implementation of generic interface
class StringProcessor implements DataProcessor<String> {
    private String data;
    
    @Override
    public void process(String data) {
        this.data = data.toUpperCase();
    }
    
    @Override
    public String retrieve() {
        return data;
    }
}

public class Generics {
    
    // Generic method
    public static <T> void printArray(T[] array) {
        System.out.print("Array: ");
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
    
    // Generic method with bounded type
    public static <T extends Number> double sum(T[] numbers) {
        double sum = 0;
        for (T num : numbers) {
            sum += num.doubleValue();
        }
        return sum;
    }
    
    // Generic method with wildcard
    public static void printList(List<?> list) {
        System.out.print("List: ");
        for (Object element : list) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
    
    // Generic method with upper bounded wildcard
    public static void printNumbers(List<? extends Number> list) {
        System.out.print("Numbers: ");
        for (Number num : list) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    // Generic method with lower bounded wildcard
    public static void addNumbers(List<? super Integer> list) {
        list.add(10);
        list.add(20);
        list.add(30);
        System.out.println("Added numbers to list");
    }
    
    public static void main(String[] args) {
        System.out.println("=== Generic Classes ===\n");
        
        // Container with String
        Container<String> stringContainer = new Container<>("Hello, Generics!");
        stringContainer.display();
        
        // Container with Integer
        Container<Integer> intContainer = new Container<>(42);
        intContainer.display();
        
        // Container with Double
        Container<Double> doubleContainer = new Container<>(3.14);
        doubleContainer.display();
        
        System.out.println("\n=== Generic Class with Multiple Type Parameters ===\n");
        
        Pair<String, Integer> pair1 = new Pair<>("Age", 25);
        pair1.display();
        
        Pair<String, Double> pair2 = new Pair<>("Price", 99.99);
        pair2.display();
        
        Pair<Integer, String> pair3 = new Pair<>(1, "First");
        pair3.display();
        
        System.out.println("\n=== Bounded Type Parameters ===\n");
        
        NumberContainer<Integer> intNum = new NumberContainer<>(100);
        System.out.println("Integer as double: " + intNum.toDouble());
        
        NumberContainer<Double> doubleNum = new NumberContainer<>(3.14159);
        System.out.println("Double value: " + doubleNum.toDouble());
        
        // This would cause compilation error:
        // NumberContainer<String> stringNum = new NumberContainer<>("123"); // Error!
        
        System.out.println("\n=== Generic Interface ===\n");
        
        StringProcessor processor = new StringProcessor();
        processor.process("Hello World");
        System.out.println("Processed: " + processor.retrieve());
        
        System.out.println("\n=== Generic Methods ===\n");
        
        Integer[] intArray = {1, 2, 3, 4, 5};
        String[] strArray = {"A", "B", "C"};
        Double[] doubleArray = {1.1, 2.2, 3.3};
        
        printArray(intArray);
        printArray(strArray);
        printArray(doubleArray);
        
        System.out.println("\n=== Bounded Type Method ===\n");
        
        Integer[] numbers = {10, 20, 30, 40};
        System.out.println("Sum of integers: " + sum(numbers));
        
        Double[] decimals = {1.5, 2.5, 3.5};
        System.out.println("Sum of doubles: " + sum(decimals));
        
        System.out.println("\n=== Wildcards ===\n");
        
        List<String> stringList = new ArrayList<>(Arrays.asList("Java", "Generics", "Tutorial"));
        printList(stringList);
        
        List<Integer> intList = new ArrayList<>(Arrays.asList(10, 20, 30));
        printNumbers(intList);
        
        List<Double> doubleList = new ArrayList<>(Arrays.asList(1.1, 2.2, 3.3));
        printNumbers(doubleList);
        
        System.out.println("\n=== Generic Advantages ===");
        System.out.println("1. Type Safety: Errors caught at compile time");
        System.out.println("2. Elimination of casts");
        System.out.println("3. Code reusability");
        System.out.println("4. Better performance");
        System.out.println("5. Implementation of generic algorithms");
    }
}

