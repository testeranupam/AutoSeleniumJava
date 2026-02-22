# Environment Setup Guide

## Local Machine Setup

### Prerequisites
- Java 11+ installed
- Maven 3.8.1+ installed
- Git installed
- Browser (Chrome/Firefox)

### Step 1: Clone Repository

```bash
git clone https://github.com/yourusername/SeleniumJava.git
cd SeleniumJava
```

### Step 2: Install Dependencies

```bash
mvn clean install -DskipTests
```

### Step 3: Configure Environment

#### Option A: Using System Properties (Recommended for CI/CD)

```bash
# Run with DEV environment
mvn test -Denv=dev

# Run with TEST environment
mvn test -Denv=test

# Run with STAGING environment
mvn test -Denv=staging

# Run with PROD environment (smoke tests only)
mvn test -Denv=prod
```

#### Option B: Manual Configuration

Edit `src/test/resources/config/config.properties`:

```properties
# Base URL
base.url=http://localhost:8080

# Browser
browser=chrome
headless=false

# Database
db.driver=com.mysql.cj.jdbc.Driver
db.url=jdbc:mysql://localhost:3306/automation_dev
db.user=root
db.password=password
```

#### Option C: Environment Variables

```bash
# Set environment variable
export ENV=dev

# Then run Maven
mvn test
```

---

## Database Setup by Environment

### DEV Environment (Local MySQL)

```sql
-- Create DEV database
CREATE DATABASE automation_dev;

-- Create user
CREATE USER 'dev_user'@'localhost' IDENTIFIED BY 'dev_password';
GRANT ALL PRIVILEGES ON automation_dev.* TO 'dev_user'@'localhost';
FLUSH PRIVILEGES;

-- Create test tables
USE automation_dev;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    active BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id VARCHAR(10) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL,
    in_stock BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert sample data
INSERT INTO users (username, email, password) VALUES 
('john.doe', 'john@example.com', 'Pass123'),
('jane.smith', 'jane@example.com', 'Pass456');

INSERT INTO products (product_id, name, price, quantity) VALUES
('P001', 'Laptop', 899.99, 10),
('P002', 'Mouse', 29.99, 50);
```

### TEST Environment (Remote Database)

Contact your QA lead for:
- Database hostname
- Username and password
- Network access requirements

```properties
db.url=jdbc:mysql://test-db.example.com:3306/automation_test
db.user=test_user
db.password=<contact qa-team>
```

### STAGING Environment

Contact DevOps for:
- Staging database credentials
- Network security group access
- API endpoint access

```properties
base.url=http://staging.example.com
db.url=jdbc:mysql://staging-db.example.com:3306/automation_staging
api.base.url=http://staging.example.com/api
```

### PRODUCTION Environment

⚠️ **PRODUCTION ENVIRONMENT RESTRICTIONS:**

- Smoke tests ONLY
- Read-only database access
- No data modification
- Contact production support for credentials

```bash
# PROD access restricted - contact DevOps
mvn test -Dtest=DataDrivenDemoTest -Denv=prod
```

---

## Running Tests by Environment

### DEV - Local Development

```bash
# Run all tests locally
mvn clean test -Denv=dev

# Run specific test class
mvn test -Dtest=POJODemoTest -Denv=dev

# Run in browser (non-headless)
mvn test -Denv=dev -Dheadless=false

# Run with verbose logging
mvn test -Denv=dev -X
```

### TEST - Continuous Integration

```bash
# Run full test suite
mvn clean test -Denv=test

# Run with parallel execution
mvn test -Denv=test -DsuiteXmlFile=testng-parallel-methods.xml

# Run batch of tests
mvn test -Denv=test -Dtest=DataDrivenDemoTest,POJODemoTest
```

### STAGING - Pre-Production Validation

```bash
# Run all tests on staging
mvn clean test -Denv=staging

# Run performance tests
mvn test -Denv=staging -Dtest=ComprehensiveFeatureTest

# Run with screenshots
mvn test -Denv=staging -Dscreenshot.on.failure=true
```

### PROD - Production Smoke Tests

```bash
# SMOKE TESTS ONLY
mvn test -Dtest=DataDrivenDemoTest -Denv=prod

# With limited retries
mvn test -Dtest=DataDrivenDemoTest -Denv=prod -Dretry.count=3
```

---

## Environment-Specific Test Commands

### Quick Smoke Test
```bash
mvn clean compile && mvn test -Dtest=DataDrivenDemoTest -q
```

### Full Test Suite
```bash
mvn clean test -Denv=test -DsuiteXmlFile=testng-parallel-methods.xml
```

### POJO Tests Only
```bash
mvn test -Dtest=POJODemoTest -Denv=test
```

### Data-Driven Tests Only
```bash
mvn test -Dtest=DataDrivenDemoTest -Denv=test
```

### With HTML Report
```bash
mvn clean test -Denv=test && mvn surefire-report:report
# Report at: target/site/surefire-report.html
```

---

## Environment Variables

### For Local Setup

```bash
# Windows (PowerShell)
$env:ENV = "dev"
$env:BASE_URL = "http://localhost:8080"
$env:DB_HOST = "localhost"

# Windows (CMD)
SET ENV=dev
SET BASE_URL=http://localhost:8080

# Linux/Mac
export ENV=dev
export BASE_URL=http://localhost:8080
export DB_HOST=localhost
```

### For GitHub Actions (Secrets)

Set in **Settings → Secrets and variables → Actions:**

```
DEV_BASE_URL = http://localhost:8080
DEV_DB_HOST = localhost
DEV_DB_USER = dev_user
DEV_DB_PASSWORD = dev_password

TEST_BASE_URL = http://test.example.com
TEST_DB_HOST = test-db.example.com
TEST_DB_USER = test_user
TEST_DB_PASSWORD = <your_password>

STAGING_BASE_URL = http://staging.example.com
STAGING_DB_HOST = staging-db.example.com
STAGING_DB_USER = staging_user
STAGING_DB_PASSWORD = <your_password>

PROD_BASE_URL = http://www.example.com
PROD_DB_HOST = prod-db.example.com
PROD_DB_USER = prod_user
PROD_DB_PASSWORD = <your_password>
```

---

## Troubleshooting Environment Issues

### Connection to DEV Database

```bash
# Test MySQL connection
mysql -h localhost -u dev_user -p dev_password

# If connection fails:
# 1. Ensure MySQL is running
# 2. Check credentials in config.properties
# 3. Verify database exists: SHOW DATABASES;
```

### Connection to TEST/STAGING/PROD

```bash
# Test remote database
mysql -h test-db.example.com -u test_user -p test_password

# Port specification
mysql -h test-db.example.com -P 3306 -u test_user -p test_password

# If connection fails:
# 1. Verify VPN is connected
# 2. Check firewall rules
# 3. Verify credentials
# 4. Contact DevOps/QA team
```

### Credentials Not Found

```bash
# Check if environment variable is set
echo $ENV  # Linux/Mac
echo %ENV% # Windows CMD
$env:ENV  # Windows PowerShell

# If not set, configure it
export ENV=dev  # Linux/Mac
SET ENV=dev     # Windows CMD
$env:ENV = "dev" # Windows PowerShell
```

### Tests Failing Due to Environment Config

```bash
# Verify which config file is loaded
mvn test -Denv=test -X | grep "properties"

# Check config.properties values
cat src/test/resources/config/config.properties
cat src/test/resources/config/test.properties

# Override specific property
mvn test -Dbase.url=http://custom.example.com
```

---

## IDE Configuration

### IntelliJ IDEA

1. **Run → Edit Configurations**
2. **Add New → TestNG**
3. Under **VM options**:
   ```
   -Denv=dev
   -Dheadless=false
   ```
4. Click **Apply** and run

### Eclipse

1. **Run → Run Configurations**
2. **Arguments → VM arguments**:
   ```
   -Denv=dev
   -Dheadless=false
   ```
3. Click **Apply** and run

### VS Code

Use Maven commands in the integrated terminal:

```bash
# Run tests with DEV environment
mvn test -Denv=dev

# Run specific test
mvn test -Dtest=POJODemoTest -Denv=dev

# Run tests in headless mode
mvn test -Dheadless=true -Denv=test
```

---

## Environment Comparison Table

| Aspect | DEV | TEST | STAGING | PROD |
|--------|-----|------|---------|------|
| **Base URL** | localhost:8080 | test.example.com | staging.example.com | www.example.com |
| **Database** | Local MySQL | Test server | Staging DB | Production DB |
| **Headless** | false (default) | true | true | true |
| **Parallel** | No | Yes (5) | Yes (8) | Limited (3) |
| **Logging** | DEBUG | INFO | INFO | WARN |
| **Screenshots** | On failure | On failure | On success/failure | On failure |
| **Test Data** | Reset daily | Reset per run | Preserved | ✗ NO CHANGES |
| **Access** | Everyone | Team | Team leads | DevOps only |

---

## Security Best Practices

1. **Never Commit Credentials**
   ```bash
   # Add to .gitignore
   src/test/resources/environments/*.properties
   .env
   credentials.txt
   ```

2. **Use Environment Variables for Secrets**
   ```java
   String dbPassword = System.getenv("DB_PASSWORD");
   // or
   String dbPassword = System.getProperty("db.password");
   ```

3. **Rotate Credentials Regularly**
   - Update DB passwords quarterly
   - Rotate API keys
   - Update service account credentials

4. **Limit Access by Environment**
   - DEV: Everyone
   - TEST: Team members
   - STAGING: Team leads only
   - PROD: DevOps team only

5. **Audit Trail**
   - Log test execution
   - Monitor database changes
   - Review production access logs

---

## Support

For environment-related issues:
1. Check this guide
2. Review workflow logs in GitHub Actions
3. Contact your QA lead (TEST/STAGING)
4. Contact DevOps team (PROD)

---

**Last Updated:** February 16, 2026  
**Version:** 1.0
