package Learning.Basic;

/**
 * Control Flow Statements in Java
 * 1. if-else
 * 2. switch-case
 * 3. Nested conditions
 * 
 * KEY INFO:
 * if-else: Boolean condition, multiple branches
 * switch: Multiple values, works with int/String/enum
 * Ternary: result = condition ? value1 : value2;
 * break: Exit switch/loop, continue: Skip iteration
 */
public class ControlFlow {
    
    public static void main(String[] args) {
        System.out.println("=== if-else Statement ===");
        ifElseExample();
        
        System.out.println("\n=== if-else if-else Statement ===");
        ifElseIfExample();
        
        System.out.println("\n=== Nested if Statement ===");
        nestedIfExample();
        
        System.out.println("\n=== switch-case Statement ===");
        switchCaseExample();
    }
    
    public static void ifElseExample() {
        int age = 20;
        
        if (age >= 18) {
            System.out.println("You are an adult");
        } else {
            System.out.println("You are a minor");
        }
    }
    
    public static void ifElseIfExample() {
        int marks = 75;
        
        if (marks >= 90) {
            System.out.println("Grade: A");
        } else if (marks >= 80) {
            System.out.println("Grade: B");
        } else if (marks >= 70) {
            System.out.println("Grade: C");
        } else if (marks >= 60) {
            System.out.println("Grade: D");
        } else {
            System.out.println("Grade: F");
        }
    }
    
    public static void nestedIfExample() {
        int age = 20;
        boolean hasLicense = true;
        
        if (age >= 18) {
            if (hasLicense) {
                System.out.println("You can drive");
            } else {
                System.out.println("You need a license to drive");
            }
        } else {
            System.out.println("You are too young to drive");
        }
    }
    
    public static void switchCaseExample() {
        int day = 3;
        String dayName;
        
        switch (day) {
            case 1:
                dayName = "Monday";
                break;
            case 2:
                dayName = "Tuesday";
                break;
            case 3:
                dayName = "Wednesday";
                break;
            case 4:
                dayName = "Thursday";
                break;
            case 5:
                dayName = "Friday";
                break;
            case 6:
                dayName = "Saturday";
                break;
            case 7:
                dayName = "Sunday";
                break;
            default:
                dayName = "Invalid day";
        }
        
        System.out.println("Day " + day + " is " + dayName);
    }
}

