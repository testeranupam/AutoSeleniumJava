# Windows Agents Setup Guide

## GitHub Actions with Windows Runners

This framework is configured to run on **Windows agents** (`windows-latest`) in GitHub Actions.

### Runner Specifications

**Windows Latest Agent Includes:**
- Windows Server 2022
- .NET Framework & .NET Core pre-installed
- Python 3.x pre-installed
- Node.js pre-installed
- Git pre-installed
- Maven 3.9.x pre-installed
- Java (multiple versions via `setup-java@v3`)
- Chrome, Firefox browsers
- Edge browser

### Default Shell

Windows runners use **PowerShell 7** by default, but workflows specify **cmd** shell for Maven/Java commands.

---

## Setup & Configuration

### Prerequisites for Windows Agents

No additional setup needed - GitHub provides fully configured Windows agents.

**For Local Development (Windows Machine):**
```powershell
# Install Java
choco install openjdk11

# Install Maven
choco install maven

# Install Git
choco install git

# Verify installations
java -version
mvn -v
git --version
```

### GitHub Actions Configuration

All workflows are configured with:
- `runs-on: windows-latest` ✅
- `shell: cmd` for Maven commands ✅
- `shell: powershell` for PowerShell scripts ✅

---

## Running Tests on Windows Agents

### Test.yml (Main Pipeline)

```yaml
runs-on: windows-latest

env:
  ENVIRONMENT: test

steps:
  - name: Setup Java
    uses: actions/setup-java@v3
    with:
      java-version: 11
      distribution: 'temurin'
  
  - name: Run Tests
    run: mvn test -Denv=test -Dheadless=true
    shell: cmd
```

**Triggers automatically on:**
- Push to `main` or `develop`
- Pull request to `main` or `develop`
- Scheduled daily at 10 AM UTC

### Multi-Env-Test.yml (Environment-Specific)

Runs on Windows for 4 environments:
- **DEV** - Development & local testing
- **TEST** - CI/CD automated testing
- **STAGING** - Pre-production validation
- **PROD** - Production smoke tests

Each environment has its own Windows job.

### Manual-Test.yml (On-Demand)

```yaml
runs-on: windows-latest

inputs:
  environment: dev | test | staging | prod
  test_class: (optional test class name)
  parallel: true | false
  headless: true | false
  java_version: 11 | 17 | 21
```

---

## Environment Variables on Windows Agents

### Setting Variables in Workflows

**In YAML:**
```yaml
env:
  ENVIRONMENT: test
  BASE_URL: http://test.example.com

steps:
  - name: Run Tests
    run: mvn test -Denv=%ENVIRONMENT%
    shell: cmd
```

**In PowerShell Steps:**
```yaml
- name: Set Variables
  run: |
    $env:ENVIRONMENT="test"
    $env:BASE_URL="http://test.example.com"
  shell: powershell
```

### Using GitHub Secrets

```yaml
steps:
  - name: Run with Secrets
    run: mvn test -Denv=test
    env:
      DB_USER: ${{ secrets.TEST_DB_USER }}
      DB_PASSWORD: ${{ secrets.TEST_DB_PASSWORD }}
```

---

## Path Handling on Windows

### Differences from Linux

| Aspect | Linux | Windows |
|--------|-------|---------|
| Path separator | `/` | `\` or `/` |
| Home directory | `$HOME` | `%USERPROFILE%` |
| Temp directory | `/tmp` | `%TEMP%` |
| Command separator | `;` | `&` or `&& ` |

### Artifact Paths

```yaml
# Works on both Windows and Linux
- name: Upload Artifacts
  uses: actions/upload-artifact@v3
  with:
    path: target/surefire-reports/
```

---

## Performance Considerations

### Windows Agent Performance

**Advantages:**
- Native Windows application support
- Faster for .NET projects
- Direct Windows API access
- Better for UI automation with Windows controls

**Considerations:**
- Larger agent spin-up time (~2-3 minutes)
- Higher CPU usage during tests
- More disk space for dependencies

### Optimization Tips

1. **Cache Maven Dependencies**
   ```yaml
   - uses: actions/cache@v3
     with:
       path: ~/.m2
       key: ${{ runner.os }}-maven-${{ hashFiles('**/pom.xml') }}
   ```

2. **Use Job Matrices Efficiently**
   ```yaml
   strategy:
     matrix:
       java-version: ['11', '17']
     max-parallel: 2  # Limit parallel jobs
   ```

3. **Parallel Test Execution**
   ```yaml
   run: mvn test -DsuiteXmlFile=testng-parallel-methods.xml
   ```

---

## Troubleshooting

### Maven Not Found

**Error:** `'mvn' is not recognized as an internal or external command`

**Solution:**
```yaml
- name: Setup Maven
  uses: stCarolas/setup-maven@v4.4
  with:
    maven-version: 3.9.1
```

### Java Version Issues

**Error:** Wrong Java version being used

**Solution:**
```yaml
- name: Set Up Java
  uses: actions/setup-java@v3
  with:
    java-version: 11
    distribution: 'temurin'
    architecture: x64  # or x32
```

### Long Paths

Windows has path length limitations (260 characters default).

**Solution:**
```powershell
# Enable long path support
New-ItemProperty -Path "HKLM:\SYSTEM\CurrentControlSet\Control\FileSystem" -Name "LongPathsEnabled" -Value 1 -PropertyType DWORD -Force
```

### Timeout Issues

Increase timeout for long-running tests:
```yaml
jobs:
  test:
    timeout-minutes: 60  # Default is 360
```

### Browser Driver Issues

Windows agents pre-install browsers, but drivers might not match:
```yaml
- name: Download ChromeDriver
  uses: nanasess/github-action-webdriver@master
```

---

## Local Development on Windows

### Run Tests Locally (Windows Machine)

```powershell
# Navigate to project
cd C:\Users\YourName\Documents\AutomationJava\SeleniumJava

# DEV environment (local)
mvn test -Denv=dev

# TEST environment
mvn test -Denv=test -DsuiteXmlFile=testng-parallel-methods.xml

# PROD smoke tests
mvn test -Dtest=DataDrivenDemoTest -Denv=prod
```

### IDE Setup

**Visual Studio Code:**
```json
{
  "maven.terminal.customEnvVars": {
    "ENV": "dev",
    "MAVEN_OPTS": "-Xmx1024m"
  }
}
```

**IntelliJ IDEA:**
1. File → Settings → Build, Execution, Deployment → Maven
2. VM options: `-Denv=dev -Dheadless=false`

---

## Cost Considerations

**GitHub Actions Pricing (Windows Agents):**
- Free tier: 2,000 minutes/month
- Windows runners: 2 credits per minute
- 2,000 minutes test budget = 1,000 minutes Windows usage
- Roughly 33 full test runs per month

**Cost Optimization:**
- Use Ubuntu agents for non-UI tests
- Limit concurrent Windows job runs
- Cache dependencies aggressively
- Use matrix strategy efficiently

---

## Monitoring & Logs

### View Workflow Logs

1. Go to GitHub repo → Actions
2. Select workflow run
3. Click job name
4. View `Run tests` section
5. Expand failed steps for logs

### Download Artifacts

1. Go to workflow run summary
2. Scroll to "Artifacts" section
3. Download test results or HTML reports

### Real-Time Monitoring

```bash
# Watch workflow in real-time
gh run watch <run-id>
```

---

## Windows-Specific Test Configuration

### For UI Automation

```properties
# Windows-specific settings
browser=chrome
headless=true

# Windows driver paths
driver.download.path=%TEMP%\drivers

# Screenshot path (Windows)
screenshot.path=target\screenshots
```

### For Cross-Platform Compatibility

```java
// Use Java NIO for file paths (works on all OS)
Path path = Paths.get("target", "screenshots", "screenshot.png");

// Instead of:
// String path = "target\\screenshots\\screenshot.png";  // Windows only
```

---

## Quick Reference

### Common Commands on Windows Agents

```bash
# Run specific test
mvn test -Dtest=POJODemoTest

# Run with Java 11
mvn test -Denv=test

# Run with parallel execution
mvn test -DsuiteXmlFile=testng-parallel-methods.xml

# Generate report
mvn surefire-report:report

# Clean build
mvn clean install
```

### Environment Variables

```powershell
# Set in PowerShell
$env:ENV = "test"
$env:BASE_URL = "http://test.example.com"

# View variable
echo %ENV%
```

---

## Support

For Windows agents-specific issues:
1. Check GitHub Actions documentation: https://docs.github.com/en/actions/using-github-hosted-runners/about-github-hosted-runners
2. Review Maven Windows setup: https://maven.apache.org/install.html
3. Check project workflow files: `.github/workflows/*.yml`
4. Review logs in GitHub Actions dashboard

---

**Last Updated:** February 16, 2026  
**Platform:** Windows Agents (windows-latest)  
**Status:** Fully Configured ✅
