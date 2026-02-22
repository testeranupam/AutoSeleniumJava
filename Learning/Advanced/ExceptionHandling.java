package Learning.Advanced;

/**
 * Exception Handling in Java
 * Checked exceptions: IOException, SQLException (must be caught)
 * Unchecked exceptions: RuntimeException, NullPointerException (can be avoided)
 * Error: Serious problems, should not catch
 * 
 * Try-catch-finally, throw, throws keywords
 * 
 * KEY INFO:
 * try-catch: Handle exceptions
 * finally: Always executes (cleanup code)
 * throw: Throw exception manually
 * throws: Declare method may throw exception
 * Custom Exceptions: Extend Exception or RuntimeException
 * Multi-catch: catch(IOException | SQLException e)
 * Try-with-resources: Auto-close resources (Java 7+)
 */

import java.io.*;

public class ExceptionHandling {
    
    // Custom exception
    static class InsufficientFundsException extends Exception {
        public InsufficientFundsException(String message) {
            super(message);
        }
    }
    
    static class BankAccount {
        private String accountNumber;
        private double balance;
        
        public BankAccount(String accountNumber, double balance) {
            this.accountNumber = accountNumber;
            this.balance = balance;
        }
        
        // Method that throws checked exception
        public void withdraw(double amount) throws InsufficientFundsException {
            if (amount > balance) {
                throw new InsufficientFundsException(
                    "Insufficient funds! Balance: " + balance + ", Withdrawal: " + amount);
            }
            balance -= amount;
            System.out.println("Withdrawn: $" + amount + ", New Balance: $" + balance);
        }
        
        public double getBalance() {
            return balance;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Try-Catch Block ===\n");
        
        tryCatchExample();
        
        System.out.println("\n=== Multiple Catch Blocks ===\n");
        
        multipleCatchExample();
        
        System.out.println("\n=== Try-Catch-Finally ===\n");
        
        tryCatchFinallyExample();
        
        System.out.println("\n=== Try-with-Resources ===\n");
        
        tryWithResourcesExample();
        
        System.out.println("\n=== Throwing Exceptions ===\n");
        
        throwingExceptionsExample();
        
        System.out.println("\n=== Custom Exceptions ===\n");
        
        customExceptionExample();
        
        System.out.println("\n=== Exception Chain ===\n");
        
        exceptionChainExample();
    }
    
    public static void tryCatchExample() {
        try {
            int result = 10 / 0; // ArithmeticException
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero!");
            System.out.println("Exception message: " + e.getMessage());
        }
        System.out.println("Program continues after exception handling");
    }
    
    public static void multipleCatchExample() {
        try {
            int[] numbers = {1, 2, 3};
            System.out.println(numbers[10]); // ArrayIndexOutOfBoundsException
            
            String str = null;
            int len = str.length(); // NullPointerException
            
            int result = Integer.parseInt("abc"); // NumberFormatException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Array index out of bounds!");
        } catch (NullPointerException e) {
            System.out.println("Error: Null pointer exception!");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format!");
        }
    }
    
    public static void tryCatchFinallyExample() {
        try {
            System.out.println("In try block");
            int result = 10 / 2;
            System.out.println("Result: " + result);
            return; // Return statement
        } catch (ArithmeticException e) {
            System.out.println("In catch block");
        } finally {
            // Finally block always executes
            System.out.println("In finally block - always executes!");
        }
    }
    
    public static void tryWithResourcesExample() {
        // Try-with-resources automatically closes resources
        try (BufferedReader br = new BufferedReader(
                new StringReader("Hello\nWorld\nJava"))) {
            String line;
            System.out.println("Reading from BufferedReader:");
            while ((line = br.readLine()) != null) {
                System.out.println("- " + line);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } // Resource is automatically closed here
    }
    
    public static void throwingExceptionsExample() {
        try {
            validateAge(15);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }
    
    public static void validateAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("Age must be at least 18!");
        }
        System.out.println("Age is valid: " + age);
    }
    
    public static void customExceptionExample() {
        BankAccount account = new BankAccount("ACC001", 1000);
        
        try {
            System.out.println("Current balance: $" + account.getBalance());
            account.withdraw(500);
            account.withdraw(700); // This will throw InsufficientFundsException
        } catch (InsufficientFundsException e) {
            System.out.println("Caught custom exception: " + e.getMessage());
        }
    }
    
    public static void exceptionChainExample() {
        try {
            try {
                int[] arr = {1, 2, 3};
                System.out.println(arr[10]);
            } catch (ArrayIndexOutOfBoundsException e) {
                // Wrap the exception with more context
                throw new RuntimeException(
                    "Unexpected array access error", e);
            }
        } catch (RuntimeException e) {
            System.out.println("Caught exception: " + e.getMessage());
            System.out.println("Cause: " + e.getCause());
        }
    }
}

// Helper class for try-with-resources example
class StringReader implements AutoCloseable {
    private String content;
    private int position = 0;
    
    public StringReader(String content) {
        this.content = content;
    }
    
    public String readLine() {
        if (position >= content.length()) return null;
        
        int newlineIndex = content.indexOf('\n', position);
        String line;
        if (newlineIndex == -1) {
            line = content.substring(position);
            position = content.length();
        } else {
            line = content.substring(position, newlineIndex);
            position = newlineIndex + 1;
        }
        return line;
    }
    
    @Override
    public void close() {
        System.out.println("StringReader closed");
    }
}

class BufferedReader implements AutoCloseable {
    private StringReader reader;
    
    public BufferedReader(StringReader reader) {
        this.reader = reader;
    }
    
    public String readLine() throws IOException {
        return reader.readLine();
    }
    
    @Override
    public void close() throws IOException {
        reader.close();
    }
}

