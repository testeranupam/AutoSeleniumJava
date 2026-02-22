package Learning.Advanced;

import java.util.*;
import java.util.stream.*;

/**
 * Streams and Lambda Expressions in Java 8+
 * Streams: Functional way to process collections
 * Lambda: Anonymous functions for functional programming
 * 
 * KEY INFO:
 * Lambda: (parameters) -> expression or { statements }
 * Functional Interface: Interface with single abstract method
 * Stream Operations:
 *   Intermediate: filter(), map(), sorted() - return Stream
 *   Terminal: collect(), forEach(), count() - return result
 * 
 * Common: filter(predicate), map(function), reduce(accumulator)
 * Parallel Streams: parallelStream() - multi-threaded processing
 * Method Reference: Class::method shorthand for lambda
 */

public class StreamsAndLambda {
    
    // Functional interface - moved outside main method
    @FunctionalInterface
    static interface Calculator {
        int calculate(int a, int b);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Lambda Expressions ===\n");
        
        // Traditional way with anonymous class
        Comparator<Integer> oldComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a.compareTo(b);
            }
        };
        
        // Lambda expression
        Comparator<Integer> lambdaComparator = (a, b) -> a.compareTo(b);
        
        Calculator add = (a, b) -> a + b;
        Calculator subtract = (a, b) -> a - b;
        Calculator multiply = (a, b) -> a * b;
        Calculator divide = (a, b) -> b != 0 ? a / b : 0;
        
        System.out.println("10 + 5 = " + add.calculate(10, 5));
        System.out.println("10 - 5 = " + subtract.calculate(10, 5));
        System.out.println("10 * 5 = " + multiply.calculate(10, 5));
        System.out.println("10 / 5 = " + divide.calculate(10, 5));
        
        System.out.println("\n=== Streams - Filter ===\n");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        System.out.println("Original list: " + numbers);
        
        // Filter even numbers
        System.out.println("Even numbers: " + 
            numbers.stream()
                   .filter(n -> n % 2 == 0)
                   .collect(Collectors.toList()));
        
        // Filter numbers greater than 5
        System.out.println("Numbers > 5: " + 
            numbers.stream()
                   .filter(n -> n > 5)
                   .collect(Collectors.toList()));
        
        System.out.println("\n=== Streams - Map ===\n");
        
        // Map to squares
        System.out.println("Squares: " + 
            numbers.stream()
                   .map(n -> n * n)
                   .collect(Collectors.toList()));
        
        // Map to strings
        System.out.println("As strings: " + 
            numbers.stream()
                   .map(n -> "Number: " + n)
                   .collect(Collectors.toList()));
        
        System.out.println("\n=== Streams - Reduce ===\n");
        
        // Sum of all numbers
        @SuppressWarnings("unchecked")
        int sum = numbers.stream()
                         .reduce(0, Integer::sum);
        System.out.println("Sum: " + sum);
        
        // Product of all numbers
        int product = numbers.stream()
                             .reduce(1, (a, b) -> a * b);
        System.out.println("Product: " + product);
        
        // Maximum value
        @SuppressWarnings("unchecked")
        int max = numbers.stream()
                         .reduce(Integer.MIN_VALUE, Integer::max);
        System.out.println("Maximum: " + max);
        
        System.out.println("\n=== Streams - Terminal Operations ===\n");
        
        // ForEach
        System.out.print("ForEach: ");
        numbers.stream()
               .filter(n -> n % 2 == 0)
               .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // Count
        long count = numbers.stream()
                            .filter(n -> n > 5)
                            .count();
        System.out.println("Count of numbers > 5: " + count);
        
        // AnyMatch
        boolean hasEven = numbers.stream()
                                 .anyMatch(n -> n % 2 == 0);
        System.out.println("Has even numbers: " + hasEven);
        
        // AllMatch
        boolean allPositive = numbers.stream()
                                     .allMatch(n -> n > 0);
        System.out.println("All positive: " + allPositive);
        
        // NoneMatch
        boolean noneNegative = numbers.stream()
                                      .noneMatch(n -> n < 0);
        System.out.println("None negative: " + noneNegative);
        
        System.out.println("\n=== Stream Chaining ===\n");
        
        List<String> result = numbers.stream()
                                     .filter(n -> n > 3)
                                     .map(n -> n * 2)
                                     .filter(n -> n > 5)
                                     .map(n -> "Value: " + n)
                                     .collect(Collectors.toList());
        System.out.println("Chained result: " + result);
        
        System.out.println("\n=== Stream on Strings ===\n");
        
        String text = "Java Streams Lambda Expressions";
        
        // Count words
        System.out.println("Word count: " + 
            Arrays.stream(text.split(" ")).count());
        
        // Filter and uppercase
        System.out.println("Words with 'a': " + 
            Arrays.stream(text.split(" "))
                  .filter(word -> word.toLowerCase().contains("a"))
                  .map(String::toUpperCase)
                  .collect(Collectors.toList()));
        
        System.out.println("\n=== Stream on Custom Objects ===\n");
        
        List<Person> people = Arrays.asList(
            new Person("Alice", 25),
            new Person("Bob", 30),
            new Person("Charlie", 20),
            new Person("David", 35)
        );
        
        System.out.println("All people: " + people);
        
        // Filter people over 25
        System.out.println("People over 25: " + 
            people.stream()
                  .filter(p -> p.age > 25)
                  .collect(Collectors.toList()));
        
        // Map to names only
        System.out.println("All names: " + 
            people.stream()
                  .map(p -> p.name)
                  .collect(Collectors.toList()));
        
        // Sort by age
        System.out.println("Sorted by age: " + 
            people.stream()
                  .sorted(Comparator.comparingInt(p -> p.age))
                  .collect(Collectors.toList()));
    }
    
    static class Person {
        String name;
        int age;
        
        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        @Override
        public String toString() {
            return name + " (" + age + ")";
        }
    }
}

