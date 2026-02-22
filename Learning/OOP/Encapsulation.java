package Learning.OOP;

/**
 * Encapsulation in Java
 * Encapsulation is the bundling of data (attributes) and methods that operate on the data
 * into a single unit (class), and hiding the internal details from the outside world.
 * Key principles:
 * 1. Keep fields private
 * 2. Provide public getter and setter methods
 * 3. Validate data in setters
 * 
 * KEY INFO:
 * Data Hiding: Mark variables private, access via getters/setters
 * Getter: public Type getVar() { return var; }
 * Setter: public void setVar(Type v) { validate; this.var = v; }
 * Benefits: Data protection, flexibility, security
 * Immutable: final variables, no setters (String, Integer)
 */

class Student {
    // Private variables (data hiding)
    private String name;
    private int age;
    private double gpa;
    private String studentId;
    
    // Constructor
    public Student(String name, int age, double gpa, String studentId) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
        this.studentId = studentId;
    }
    
    // Getter methods
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public double getGpa() {
        return gpa;
    }
    
    public String getStudentId() {
        return studentId;
    }
    
    // Setter methods with validation
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Invalid name!");
        }
    }
    
    public void setAge(int age) {
        if (age > 0 && age < 100) {
            this.age = age;
        } else {
            System.out.println("Invalid age! Age must be between 1 and 99");
        }
    }
    
    public void setGpa(double gpa) {
        if (gpa >= 0.0 && gpa <= 4.0) {
            this.gpa = gpa;
        } else {
            System.out.println("Invalid GPA! GPA must be between 0.0 and 4.0");
        }
    }
    
    public void setStudentId(String studentId) {
        if (studentId != null && studentId.length() > 0) {
            this.studentId = studentId;
        } else {
            System.out.println("Invalid Student ID!");
        }
    }
    
    // Business logic methods
    public boolean isEligibleForScholarship() {
        return gpa >= 3.5;
    }
    
    public void displayStudentInfo() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("GPA: " + gpa);
    }
}

// Another example: BankAccount
class BankAccount {
    private String accountNumber;
    private double balance;
    private String accountHolder;
    private String accountType; // Savings, Checking, etc.
    
    public BankAccount(String accountNumber, String accountHolder, 
                      String accountType, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.accountType = accountType;
        this.balance = initialBalance;
    }
    
    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public String getAccountHolder() {
        return accountHolder;
    }
    
    public String getAccountType() {
        return accountType;
    }
    
    // Setters with validation
    public void setAccountHolder(String accountHolder) {
        if (accountHolder != null && !accountHolder.isEmpty()) {
            this.accountHolder = accountHolder;
        }
    }
    
    // Business logic methods
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
            System.out.println("New Balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }
    
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
            System.out.println("New Balance: $" + balance);
        } else {
            System.out.println("Invalid withdrawal! Insufficient funds or invalid amount.");
        }
    }
    
    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Type: " + accountType);
        System.out.println("Balance: $" + balance);
    }
}

public class Encapsulation {
    
    public static void main(String[] args) {
        System.out.println("=== Student Encapsulation Example ===\n");
        
        Student student = new Student("Alice", 20, 3.8, "STU001");
        
        System.out.println("--- Initial Information ---");
        student.displayStudentInfo();
        
        System.out.println("\n--- Checking Scholarship Eligibility ---");
        if (student.isEligibleForScholarship()) {
            System.out.println(student.getName() + " is eligible for scholarship!");
        } else {
            System.out.println(student.getName() + " is not eligible for scholarship.");
        }
        
        System.out.println("\n--- Updating Information ---");
        student.setGpa(3.9);
        student.setAge(21);
        student.displayStudentInfo();
        
        System.out.println("\n--- Attempting Invalid Updates ---");
        student.setGpa(5.0); // Invalid! GPA > 4.0
        student.setAge(150); // Invalid! Age too high
        student.setName(""); // Invalid! Empty name
        
        System.out.println("\n=== BankAccount Encapsulation Example ===\n");
        
        BankAccount account = new BankAccount("123456789", "John Doe", 
                                             "Savings", 1000);
        
        System.out.println("--- Initial Account Info ---");
        account.displayAccountInfo();
        
        System.out.println("\n--- Deposit Operation ---");
        account.deposit(500);
        
        System.out.println("\n--- Withdrawal Operation ---");
        account.withdraw(200);
        
        System.out.println("\n--- Attempting Invalid Withdrawal ---");
        account.withdraw(2000); // Insufficient funds
        
        System.out.println("\n--- Current Account Info ---");
        account.displayAccountInfo();
        
        System.out.println("\n=== Benefits of Encapsulation ===");
        System.out.println("1. Data hiding and protection");
        System.out.println("2. Validation of inputs");
        System.out.println("3. Control over how data is accessed");
        System.out.println("4. Flexibility in implementation changes");
        System.out.println("5. Maintainability and reusability");
    }
}

