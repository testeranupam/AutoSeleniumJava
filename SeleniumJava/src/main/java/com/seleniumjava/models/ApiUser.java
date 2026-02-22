package com.seleniumjava.models;

/**
 * API User model for API testing
 */
public class ApiUser {
    private Integer id;
    private String name;
    private String email;
    private String username;
    private String phone;
    private String website;
    private Address address;
    private Company company;
    
    // Constructors
    public ApiUser() {}
    
    public ApiUser(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    public ApiUser(Integer id, String name, String email, String username) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
    }
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getWebsite() {
        return website;
    }
    
    public void setWebsite(String website) {
        this.website = website;
    }
    
    public Address getAddress() {
        return address;
    }
    
    public void setAddress(Address address) {
        this.address = address;
    }
    
    public Company getCompany() {
        return company;
    }
    
    public void setCompany(Company company) {
        this.company = company;
    }
    
    // Nested Address class
    public static class Address {
        private String street;
        private String suite;
        private String city;
        private String zipcode;
        private Geo geo;
        
        public String getStreet() {
            return street;
        }
        
        public void setStreet(String street) {
            this.street = street;
        }
        
        public String getSuite() {
            return suite;
        }
        
        public void setSuite(String suite) {
            this.suite = suite;
        }
        
        public String getCity() {
            return city;
        }
        
        public void setCity(String city) {
            this.city = city;
        }
        
        public String getZipcode() {
            return zipcode;
        }
        
        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }
        
        public Geo getGeo() {
            return geo;
        }
        
        public void setGeo(Geo geo) {
            this.geo = geo;
        }
    }
    
    // Nested Geo class
    public static class Geo {
        private String lat;
        private String lng;
        
        public String getLat() {
            return lat;
        }
        
        public void setLat(String lat) {
            this.lat = lat;
        }
        
        public String getLng() {
            return lng;
        }
        
        public void setLng(String lng) {
            this.lng = lng;
        }
    }
    
    // Nested Company class
    public static class Company {
        private String name;
        private String catchPhrase;
        private String bs;
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public String getCatchPhrase() {
            return catchPhrase;
        }
        
        public void setCatchPhrase(String catchPhrase) {
            this.catchPhrase = catchPhrase;
        }
        
        public String getBs() {
            return bs;
        }
        
        public void setBs(String bs) {
            this.bs = bs;
        }
    }
    
    @Override
    public String toString() {
        return "ApiUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
