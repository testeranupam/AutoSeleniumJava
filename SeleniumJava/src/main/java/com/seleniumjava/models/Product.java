package com.seleniumjava.models;

/**
 * Product POJO (Plain Old Java Object)
 * Represents a product entity for e-commerce testing
 */
public class Product {
    
    private String productId;
    private String name;
    private String category;
    private double price;
    private int quantity;
    private String description;
    private boolean inStock;
    private String brand;
    private double rating;
    
    // Default constructor
    public Product() {
    }
    
    // Constructor with essential fields
    public Product(String productId, String name, double price, int quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.inStock = quantity > 0;
    }
    
    // Constructor with all fields
    public Product(String productId, String name, String category, double price, 
                   int quantity, String description, boolean inStock, String brand, double rating) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.inStock = inStock;
        this.brand = brand;
        this.rating = rating;
    }
    
    // Getters and Setters
    public String getProductId() {
        return productId;
    }
    
    public void setProductId(String productId) {
        this.productId = productId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.inStock = quantity > 0;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public boolean isInStock() {
        return inStock;
    }
    
    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
    
    public String getBrand() {
        return brand;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    public double getRating() {
        return rating;
    }
    
    public void setRating(double rating) {
        this.rating = rating;
    }
    
    // Helper methods
    
    /**
     * Calculate total value (price * quantity)
     */
    public double getTotalValue() {
        return price * quantity;
    }
    
    /**
     * Apply discount percentage
     */
    public double getDiscountedPrice(double discountPercent) {
        return price * (1 - discountPercent / 100);
    }
    
    /**
     * Check if product is highly rated (rating >= 4.0)
     */
    public boolean isHighlyRated() {
        return rating >= 4.0;
    }
    
    /**
     * Get formatted price
     */
    public String getFormattedPrice() {
        return String.format("$%.2f", price);
    }
    
    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", inStock=" + inStock +
                ", brand='" + brand + '\'' +
                ", rating=" + rating +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Product product = (Product) o;
        return productId != null ? productId.equals(product.productId) : product.productId == null;
    }
    
    @Override
    public int hashCode() {
        return productId != null ? productId.hashCode() : 0;
    }
}
