package com.seleniumjava.models;

/**
 * User POJO (Plain Old Java Object)
 * Represents a user entity for testing
 * 
 * Usage Examples:
 * - Test data modeling
 * - API request/response
 * - Database entity mapping
 * - CSV/Excel data conversion
 */
public class User {
    
    // Private fields
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private int age;
    private boolean active;
    private String role;
    
    // Default constructor (required for frameworks like Jackson, Gson)
    public User() {
    }
    
    // Constructor with all fields
    public User(String username, String password, String email, String firstName, 
                String lastName, int age, boolean active, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.active = active;
        this.role = role;
    }
    
    // Constructor with essential fields
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.active = true;
        this.role = "USER";
    }
    
    // Getters and Setters
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    // Helper methods
    
    /**
     * Get full name
     */
    public String getFullName() {
        if (firstName != null && lastName != null) {
            return firstName + " " + lastName;
        } else if (firstName != null) {
            return firstName;
        } else if (lastName != null) {
            return lastName;
        }
        return username;
    }
    
    /**
     * Check if user is admin
     */
    public boolean isAdmin() {
        return "ADMIN".equalsIgnoreCase(role);
    }
    
    /**
     * Mask password for logging
     */
    public String getMaskedPassword() {
        if (password == null || password.isEmpty()) {
            return "";
        }
        return "****";
    }
    
    // Override toString for easy logging
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", active=" + active +
                ", role='" + role + '\'' +
                ", password='" + getMaskedPassword() + '\'' +
                '}';
    }
    
    // Override equals and hashCode for object comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        User user = (User) o;
        
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        return email != null ? email.equals(user.email) : user.email == null;
    }
    
    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
