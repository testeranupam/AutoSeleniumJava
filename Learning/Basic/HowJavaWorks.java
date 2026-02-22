package Learning.Basic;

/**
 * How Java Works and Garbage Collection
 * 
 * KEY INFO:
 * Java: Platform-independent, compiled to bytecode, runs on JVM
 * JVM: Java Virtual Machine - executes bytecode
 * JRE: Java Runtime Environment - JVM + libraries
 * JDK: Java Development Kit - JRE + development tools
 * Bytecode: Platform-independent intermediate code (.class files)
 * Garbage Collection: Automatic memory management
 */

public class HowJavaWorks {
    
    public static void main(String[] args) {
        System.out.println("================ HOW JAVA WORKS ================\n");
        explainJavaExecution();
        
        System.out.println("\n================ GARBAGE COLLECTION ================\n");
        demonstrateGarbageCollection();
        
        System.out.println("\n================ MEMORY MANAGEMENT ================\n");
        explainMemoryManagement();
    }
    
    /**
     * HOW JAVA EXECUTION WORKS
     * 
     * Step 1: Write Java Source Code (.java file)
     * Example: HelloWorld.java
     * 
     * Step 2: Compile with javac (Java Compiler)
     * Command: javac HelloWorld.java
     * Output: HelloWorld.class (Bytecode)
     * 
     * Step 3: Run with java (JVM)
     * Command: java HelloWorld
     * JVM interprets/compiles bytecode to machine code
     * 
     * WHY PLATFORM INDEPENDENT?
     * - Source code compiles to bytecode (not machine code)
     * - Bytecode is same on all platforms
     * - JVM is platform-specific (Windows JVM, Linux JVM, Mac JVM)
     * - JVM translates bytecode to native machine code
     * - "Write Once, Run Anywhere" (WORA)
     * 
     * JVM COMPONENTS:
     * 1. Class Loader: Loads .class files into memory
     * 2. Bytecode Verifier: Checks code for security violations
     * 3. Interpreter: Executes bytecode line by line
     * 4. JIT Compiler: Compiles frequently used code to native machine code (faster)
     * 5. Garbage Collector: Automatically frees unused memory
     */
    public static void explainJavaExecution() {
        System.out.println("JAVA EXECUTION FLOW:");
        System.out.println("1. Source Code (.java) -> Written by developer");
        System.out.println("2. Java Compiler (javac) -> Compiles to bytecode");
        System.out.println("3. Bytecode (.class) -> Platform-independent intermediate code");
        System.out.println("4. JVM (java) -> Executes bytecode");
        System.out.println("5. Machine Code -> Native code executed by CPU");
        
        System.out.println("\nJVM ARCHITECTURE:");
        System.out.println("- Class Loader: Loads classes");
        System.out.println("- Bytecode Verifier: Security checks");
        System.out.println("- Interpreter: Executes bytecode");
        System.out.println("- JIT Compiler: Optimizes hot code");
        System.out.println("- Garbage Collector: Memory management");
        
        System.out.println("\nWHY JAVA IS PLATFORM INDEPENDENT:");
        System.out.println("âœ… Bytecode is same on all platforms");
        System.out.println("âœ… JVM translates to native code");
        System.out.println("âœ… Write Once, Run Anywhere (WORA)");
    }
    
    /**
     * GARBAGE COLLECTION (GC)
     * 
     * WHAT IS GARBAGE COLLECTION?
     * - Automatic memory management in Java
     * - Identifies and removes unused objects
     * - Frees up heap memory
     * - Prevents memory leaks
     * - Developer doesn't need to manually free memory (unlike C/C++)
     * 
     * HOW IT WORKS:
     * 1. Object is created in heap memory
     * 2. When no references point to object, it becomes eligible for GC
     * 3. GC runs periodically and removes unreachable objects
     * 4. Memory is reclaimed and available for new objects
     * 
     * MAKING OBJECTS ELIGIBLE FOR GC:
     * 
     * 1. Nullifying Reference:
     *    MyClass obj = new MyClass();
     *    obj = null;  // Now eligible for GC
     * 
     * 2. Reassigning Reference:
     *    MyClass obj1 = new MyClass();
     *    obj1 = new MyClass();  // First object eligible for GC
     * 
     * 3. Object created inside method:
     *    void method() {
     *        MyClass obj = new MyClass();
     *    }  // obj is eligible after method ends
     * 
     * 4. Anonymous objects:
     *    new MyClass().display();  // Eligible immediately after use
     * 
     * REQUESTING GARBAGE COLLECTION:
     * - System.gc() or Runtime.getRuntime().gc()
     * - Only a request, not guaranteed to run immediately
     * - JVM decides when to run GC
     * 
     * FINALIZE METHOD (Deprecated in Java 9):
     * - Called by GC before destroying object
     * - Used for cleanup (closing files, connections)
     * - protected void finalize() throws Throwable { }
     * 
     * TYPES OF GARBAGE COLLECTORS:
     * 1. Serial GC: Single-threaded, small applications
     * 2. Parallel GC: Multi-threaded, default for server applications
     * 3. CMS (Concurrent Mark-Sweep): Low pause times
     * 4. G1 GC (Garbage First): Large heaps, predictable pause times
     * 5. ZGC: Very low latency, large heaps (Java 11+)
     * 
     * GENERATIONAL GARBAGE COLLECTION:
     * Heap divided into generations:
     * 
     * 1. Young Generation:
     *    - Eden Space: New objects created here
     *    - Survivor Spaces (S0, S1): Objects that survive minor GC
     *    - Minor GC: Fast, frequent, cleans young generation
     * 
     * 2. Old Generation (Tenured):
     *    - Long-lived objects promoted here
     *    - Major GC (Full GC): Slower, cleans old + young generation
     * 
     * 3. Permanent Generation/Metaspace (Java 8+):
     *    - Class metadata, static variables
     *    - Not subject to regular GC
     */
    public static void demonstrateGarbageCollection() {
        System.out.println("CREATING OBJECTS:");
        
        // Create objects
        MyClass obj1 = new MyClass("Object1");
        MyClass obj2 = new MyClass("Object2");
        MyClass obj3 = new MyClass("Object3");
        
        System.out.println("Created 3 objects");
        
        // Making objects eligible for GC
        System.out.println("\nMAKING OBJECTS ELIGIBLE FOR GC:");
        
        // Method 1: Nullifying reference
        obj1 = null;
        System.out.println("obj1 set to null - eligible for GC");
        
        // Method 2: Reassigning reference
        obj2 = new MyClass("Object4");
        System.out.println("obj2 reassigned - old Object2 eligible for GC");
        
        // Method 3: Anonymous object
        new MyClass("Object5").display();
        System.out.println("Anonymous object eligible for GC after use");
        
        // Request garbage collection (not guaranteed)
        System.out.println("\nRequesting Garbage Collection...");
        System.gc();
        
        try {
            Thread.sleep(1000); // Give GC time to run
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\nGC may have run (JVM decides)");
        System.out.println("Note: System.gc() is just a request, not a command");
    }
    
    /**
     * MEMORY MANAGEMENT IN JAVA
     * 
     * MEMORY AREAS:
     * 
     * 1. HEAP MEMORY:
     *    - Stores objects and instance variables
     *    - Shared among all threads
     *    - Garbage collection happens here
     *    - Size: -Xms (initial) -Xmx (maximum)
     *    - Example: java -Xms512m -Xmx2048m MyApp
     * 
     * 2. STACK MEMORY:
     *    - Stores local variables and method calls
     *    - Each thread has its own stack
     *    - LIFO (Last In First Out)
     *    - Automatically managed (no GC needed)
     *    - Size: -Xss (stack size per thread)
     * 
     * 3. METHOD AREA/METASPACE (Java 8+):
     *    - Stores class metadata, static variables, constants
     *    - Shared among all threads
     *    - Part of heap prior to Java 8
     *    - Separate native memory in Java 8+
     * 
     * 4. PC REGISTER (Program Counter):
     *    - Stores address of current instruction
     *    - Each thread has its own PC register
     * 
     * 5. NATIVE METHOD STACK:
     *    - For native (C/C++) methods
     * 
     * MEMORY ALLOCATION:
     * 
     * Primitive types -> Stack
     * int x = 10;  // Stored in stack
     * 
     * Objects -> Heap
     * MyClass obj = new MyClass();  // Object in heap, reference in stack
     * 
     * Static variables -> Method Area/Metaspace
     * static int count = 0;  // Stored in method area
     * 
     * MEMORY ERRORS:
     * 
     * 1. OutOfMemoryError: Java heap space
     *    - Too many objects created
     *    - Memory leak (objects not garbage collected)
     *    - Solution: Increase heap size, fix memory leaks
     * 
     * 2. StackOverflowError:
     *    - Too many method calls (deep recursion)
     *    - Solution: Increase stack size, fix recursion
     * 
     * 3. OutOfMemoryError: Metaspace
     *    - Too many classes loaded
     *    - Solution: Increase metaspace size
     */
    public static void explainMemoryManagement() {
        System.out.println("MEMORY AREAS IN JVM:");
        System.out.println("1. Heap: Objects, instance variables (GC happens here)");
        System.out.println("2. Stack: Local variables, method calls (per thread)");
        System.out.println("3. Metaspace: Class metadata, static variables");
        System.out.println("4. PC Register: Current instruction address");
        System.out.println("5. Native Stack: Native method calls");
        
        System.out.println("\nMEMORY ALLOCATION:");
        // Primitive in stack
        int number = 100;
        System.out.println("Primitive (int): Stack - " + number);
        
        // Object in heap, reference in stack
        MyClass obj = new MyClass("HeapObject");
        System.out.println("Object: Heap (reference in Stack)");
        
        // Static in metaspace
        System.out.println("Static variables: Metaspace");
        
        System.out.println("\nHEAP MEMORY STRUCTURE:");
        System.out.println("- Young Generation:");
        System.out.println("  â€¢ Eden Space (new objects)");
        System.out.println("  â€¢ Survivor Space S0");
        System.out.println("  â€¢ Survivor Space S1");
        System.out.println("- Old Generation (tenured objects)");
        
        System.out.println("\nGARBAGE COLLECTION TYPES:");
        System.out.println("- Minor GC: Cleans Young Generation (fast)");
        System.out.println("- Major GC: Cleans Old Generation + Young (slower)");
        System.out.println("- Full GC: Cleans entire heap (slowest)");
        
        System.out.println("\nBEST PRACTICES:");
        System.out.println("âœ… Set objects to null when no longer needed");
        System.out.println("âœ… Avoid memory leaks (close resources)");
        System.out.println("âœ… Use try-with-resources for auto-closing");
        System.out.println("âœ… Don't call System.gc() explicitly");
        System.out.println("âœ… Use profilers to detect memory issues");
    }
}

/**
 * Helper class for GC demonstration
 */
class MyClass {
    private String name;
    
    public MyClass(String name) {
        this.name = name;
        System.out.println("  â†’ " + name + " created");
    }
    
    public void display() {
        System.out.println("  â†’ Displaying: " + name);
    }
    
    // finalize method (deprecated in Java 9+)
    // Called by GC before destroying object
    @Override
    protected void finalize() throws Throwable {
        System.out.println("  â†’ GC: " + name + " is being destroyed");
        super.finalize();
    }
}

