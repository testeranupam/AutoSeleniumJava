package Learning.Advanced;

import java.util.*;

/**
 * Collections in Java
 * Collections framework provides reusable data structures and algorithms
 * Hierarchy:
 * Collection -> List, Set, Queue
 * Map (separate hierarchy)
 * 
 * List: ArrayList, LinkedList, Vector
 * Set: HashSet, LinkedHashSet, TreeSet
 * Queue: PriorityQueue, Deque
 * 
 * KEY INFO:
 * List: Ordered, allows duplicates (ArrayList, LinkedList)
 * Set: No duplicates (HashSet fast, TreeSet sorted)
 * Map: Key-Value pairs (HashMap fast, TreeMap sorted)
 * Queue: FIFO (PriorityQueue sorted)
 * ArrayList: Fast random access, LinkedList: Fast insert/delete
 * Synchronized: Collections.synchronizedList/Set/Map()
 */

public class CollectionExamples {
    
    public static void main(String[] args) {
        System.out.println("=== List Interface ===\n");
        
        // ArrayList: Dynamic array, index-based access
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Date");
        fruits.add(1, "Apricot"); // Insert at index 1
        
        System.out.println("ArrayList: " + fruits);
        System.out.println("Size: " + fruits.size());
        System.out.println("Element at index 2: " + fruits.get(2));
        System.out.println("Index of 'Cherry': " + fruits.indexOf("Cherry"));
        
        fruits.remove("Apricot");
        System.out.println("After removing 'Apricot': " + fruits);
        
        // LinkedList: Doubly linked list
        LinkedList<String> linkedFruits = new LinkedList<>();
        linkedFruits.add("Orange");
        linkedFruits.add("Mango");
        linkedFruits.addFirst("Grape");
        linkedFruits.addLast("Pineapple");
        
        System.out.println("\nLinkedList: " + linkedFruits);
        System.out.println("First: " + linkedFruits.getFirst());
        System.out.println("Last: " + linkedFruits.getLast());
        System.out.println("Remove first: " + linkedFruits.removeFirst());
        System.out.println("After removing first: " + linkedFruits);
        
        System.out.println("\n=== Set Interface ===\n");
        
        // HashSet: No guaranteed order, no duplicates, fast access
        Set<String> colors = new HashSet<>();
        colors.add("Red");
        colors.add("Green");
        colors.add("Blue");
        colors.add("Red"); // Duplicate
        colors.add("Yellow");
        
        System.out.println("HashSet: " + colors); // Order not guaranteed
        System.out.println("Size: " + colors.size()); // 4, not 5 (no duplicates)
        System.out.println("Contains 'Red': " + colors.contains("Red"));
        
        colors.remove("Green");
        System.out.println("After removing 'Green': " + colors);
        
        // TreeSet: Sorted order, no duplicates
        Set<Integer> numbers = new TreeSet<>();
        numbers.add(50);
        numbers.add(30);
        numbers.add(70);
        numbers.add(20);
        numbers.add(40);
        
        System.out.println("\nTreeSet: " + numbers); // Sorted order
        
        // LinkedHashSet: Insertion order maintained
        Set<String> cities = new LinkedHashSet<>();
        cities.add("New York");
        cities.add("London");
        cities.add("Tokyo");
        cities.add("Paris");
        
        System.out.println("LinkedHashSet: " + cities); // Insertion order maintained
        
        System.out.println("\n=== Map Interface ===\n");
        
        // HashMap: Key-value pairs, unordered
        Map<String, Integer> ages = new HashMap<>();
        ages.put("Alice", 25);
        ages.put("Bob", 30);
        ages.put("Charlie", 35);
        ages.put("David", 28);
        
        System.out.println("HashMap: " + ages);
        System.out.println("Size: " + ages.size());
        System.out.println("Age of Alice: " + ages.get("Alice"));
        System.out.println("Contains 'Bob': " + ages.containsKey("Bob"));
        
        ages.remove("Charlie");
        System.out.println("After removing 'Charlie': " + ages);
        
        // TreeMap: Sorted by key
        Map<String, Integer> scores = new TreeMap<>();
        scores.put("Zoe", 95);
        scores.put("Alice", 88);
        scores.put("Bob", 92);
        scores.put("Charlie", 85);
        
        System.out.println("\nTreeMap (sorted by key): " + scores);
        
        // LinkedHashMap: Insertion order maintained
        Map<String, String> capitals = new LinkedHashMap<>();
        capitals.put("England", "London");
        capitals.put("France", "Paris");
        capitals.put("Japan", "Tokyo");
        capitals.put("Germany", "Berlin");
        
        System.out.println("LinkedHashMap (insertion order): " + capitals);
        
        System.out.println("\n=== Iteration ===\n");
        
        System.out.println("Iterating over List:");
        for (String fruit : fruits) {
            System.out.print(fruit + " ");
        }
        System.out.println();
        
        System.out.println("\nIterating over Set:");
        for (String color : colors) {
            System.out.print(color + " ");
        }
        System.out.println();
        
        System.out.println("\nIterating over Map:");
        for (Map.Entry<String, Integer> entry : ages.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        System.out.println("\n=== Collection Operations ===\n");
        
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(4, 5, 6, 7, 8));
        
        System.out.println("List 1: " + list1);
        System.out.println("List 2: " + list2);
        
        // Union
        list1.addAll(list2);
        System.out.println("Union: " + list1);
        
        // Intersection
        list1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        list1.retainAll(list2);
        System.out.println("Intersection: " + list1);
        
        System.out.println("\n=== Sorting and Searching ===\n");
        
        List<Integer> unsorted = new ArrayList<>(Arrays.asList(50, 30, 70, 20, 40));
        System.out.println("Unsorted: " + unsorted);
        
        Collections.sort(unsorted);
        System.out.println("Sorted (ascending): " + unsorted);
        
        Collections.reverse(unsorted);
        System.out.println("Reversed: " + unsorted);
        
        Collections.sort(unsorted, Collections.reverseOrder());
        System.out.println("Sorted (descending): " + unsorted);
        
        Collections.shuffle(unsorted);
        System.out.println("Shuffled: " + unsorted);
        
        Collections.sort(unsorted);
        System.out.println("Index of 30: " + Collections.binarySearch(unsorted, 30));
    }
}

