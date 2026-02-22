package Learning.Basic;

/**
 * Loops in Java
 * 1. for loop (traditional, enhanced)
 * 2. while loop
 * 3. do-while loop
 * 4. break and continue statements
 * 
 * KEY INFO:
 * for: Known iterations, for(init; condition; update)
 * while: Condition before execution, may not run
 * do-while: Condition after execution, runs at least once
 * for-each: for(Type item : collection) - read only
 * break: Exit loop, continue: Skip to next iteration
 */
public class Loops {
    
    public static void main(String[] args) {
        System.out.println("=== Traditional for Loop ===");
        traditionalForLoop();
        
        System.out.println("\n=== Enhanced for Loop (for-each) ===");
        enhancedForLoop();
        
        System.out.println("\n=== while Loop ===");
        whileLoopExample();
        
        System.out.println("\n=== do-while Loop ===");
        doWhileLoopExample();
        
        System.out.println("\n=== break Statement ===");
        breakExample();
        
        System.out.println("\n=== continue Statement ===");
        continueExample();
        
        System.out.println("\n=== Nested Loops ===");
        nestedLoopsExample();
    }
    
    public static void traditionalForLoop() {
        System.out.println("Printing numbers from 1 to 5:");
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    
    public static void enhancedForLoop() {
        int[] numbers = {10, 20, 30, 40, 50};
        System.out.println("Array elements:");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    public static void whileLoopExample() {
        int count = 1;
        System.out.println("Counting from 1 to 5 using while loop:");
        while (count <= 5) {
            System.out.print(count + " ");
            count++;
        }
        System.out.println();
    }
    
    public static void doWhileLoopExample() {
        int count = 1;
        System.out.println("Counting from 1 to 5 using do-while loop:");
        do {
            System.out.print(count + " ");
            count++;
        } while (count <= 5);
        System.out.println();
    }
    
    public static void breakExample() {
        System.out.println("Breaking out of loop when i = 3:");
        for (int i = 1; i <= 6; i++) {
            if (i == 3) {
                break;
            }
            System.out.print(i + " ");
        }
        System.out.println("\nLoop terminated");
    }
    
    public static void continueExample() {
        System.out.println("Skipping even numbers:");
        for (int i = 1; i <= 6; i++) {
            if (i % 2 == 0) {
                continue;
            }
            System.out.print(i + " ");
        }
        System.out.println();
    }
    
    public static void nestedLoopsExample() {
        System.out.println("Multiplication table (3x3):");
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                System.out.print((i * j) + " ");
            }
            System.out.println();
        }
    }
}

