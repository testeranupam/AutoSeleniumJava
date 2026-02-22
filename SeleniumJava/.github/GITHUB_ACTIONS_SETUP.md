# GitHub Actions - Quick Start Guide

## What Was Created

Three workflow files in `.github/workflows/`:

### 1. **maven-test.yml** - Primary Test Pipeline
```
├─ Triggers: Push, Pull Requests, Manual
├─ Matrix: Java 11 & 17
├─ Sequential Tests ✓
├─ Parallel Tests ✓
├─ Test Reports ✓
└─ Artifact Storage ✓
```

### 2. **selenium-parallel.yml** - Nightly Parallel Tests
```
├─ Schedule: Daily 2 AM UTC
├─ Parallel by Classes
├─ Parallel by Methods
├─ Parallel by Tests
└─ Performance Comparison ✓
```

### 3. **README.md** - Workflow Documentation
Complete reference for configuration and troubleshooting

---

## How to Deploy

### Step 1: Initialize Git Repository
```bash
cd c:\Users\KumarA2\Documents\AutomationJava
git init
git add .
git commit -m "Initial commit with SeleniumJava framework and GitHub Actions workflows"
```

### Step 2: Create GitHub Repository
1. Go to github.com → New Repository
2. Name: `AutomationJava` (or your preferred name)
3. Copy remote URL

### Step 3: Push to GitHub
```bash
git remote add origin https://github.com/YOUR_USERNAME/AutomationJava.git
git branch -M main
git push -u origin main
```

### Step 4: Enable Actions
1. Go to GitHub Repository → Settings → Actions
2. Allow all actions

---

## What Each Workflow Does

### **maven-test.yml** Workflow

**Triggered When:**
- Push to main/develop/master branches
- Pull request to main/develop/master
- Manual trigger (workflow_dispatch)

**Jobs:**
1. **test** - Runs with Java 11 and 17 (matrix)
   - Compiles code
   - Runs all tests
   - Uploads artifacts
   - Publishes TestNG reports

2. **test-parallel** - Runs on PR/main only
   - Runs all *Test classes in parallel
   - Measures parallel performance

3. **build-status** - Final status
   - Reports overall pass/fail

**Output:**
```
✅ Test Results
✅ Screenshots (if tests fail)
✅ Surefire Reports (30-day retention)
```

### **selenium-parallel.yml** Workflow

**Triggered When:**
- Daily at 2 AM UTC
- Manual trigger

**Jobs:**
1. **parallel-by-classes** - Runs class-level parallelization
2. **parallel-by-methods** - Runs method-level parallelization
3. **parallel-by-tests** - Runs test-level parallelization
4. **summary** - Generates comparison report

**Use Case:** Nightly stress testing without blocking PRs

---

## View Results

### In GitHub
```
Repository → Actions → Select Workflow → Click Run
```

### Artifact Download
1. Go to workflow run
2. Scroll to "Artifacts"
3. Download test reports and screenshots

### Test Report
- Automatic TestNG report parsing
- Summary on PR checks
- Linked in PR comments

---

## Environment Variables (Optional)

For sensitive data, add GitHub Secrets:

1. Repository → Settings → Secrets and variables → Actions
2. Create secret: `DATABASE_URL`, `API_KEY`, etc.
3. Use in workflow:
   ```yaml
   - name: Run Tests
     env:
      DATABASE_URL: ${{ secrets.DATABASE_URL }}
     run: mvn test
   ```

---

## Customize Workflows

### Change Java Version
Edit `maven-test.yml`:
```yaml
matrix:
  java-version: [ '11', '17', '21' ]  # Add '21'
```

### Run Specific Tests
Edit `maven-test.yml`:
```yaml
- name: Run API Tests Only
  run: |
    cd SeleniumJava
    mvn test -Dtest=ApiTests,AdvancedApiTests
```

### Change Parallel Schedule
Edit `selenium-parallel.yml`:
```yaml
schedule:
  - cron: '0 0 * * 0'  # Weekly Sunday at 12 AM UTC
```

### Add Email Notifications
```yaml
- name: Send Notification
  if: failure()
  run: |
    echo "Tests failed!" | mail -s "CI Failure" your@email.com
```

---

## Common Issues & Solutions

### ❌ Tests Pass Locally, Fail in Pipeline
**Solution:**
- Check Java version match
- Verify paths use `cd SeleniumJava` first
- Check for absolute paths → use relative
- Review Maven output for warnings

### ❌ Artifacts Not Showing
**Solution:**
```yaml
continue-on-error: true  # Allow report even on failure
```

### ❌ Pipeline Too Slow
**Solution:**
```yaml
cache: maven  # Already enabled, improves speed
# Or use parallel workflow for heavy test suites
```

### ❌ Permission Denied Errors
**Solution:**
- Ensure workflows can read/write artifacts
- Check branch protection rules
- Verify GitHub Actions permissions

---

## Best Practices

✅ **Do:**
- Test on PR before merge
- Use parallel workflows for nightly runs
- Keep artifacts for 30 days minimum
- Cache maven dependencies
- Run on multiple Java versions

❌ **Don't:**
- Hardcode credentials in workflows
- Run long tests on every commit
- Store large binaries as artifacts
- Use localhost/127.0.0.1 in tests
- Run browser GUI in headless pipeline

---

## Next Steps

1. **Push to GitHub** (Step 3 above)
2. **Create PR** to trigger first workflow run
3. **Review Artifacts** to verify everything works
4. **Customize** based on your needs
5. **Setup Notifications** (Slack, Email, etc.)

---

## Matrix Expansion Example

Want to test multiple scenarios? Add matrix:

```yaml
strategy:
  matrix:
    java-version: ['11', '17']
    browser: ['chrome', 'firefox']

steps:
  - run: mvn test -Dbrowser=${{ matrix.browser }}
```

This creates 4 jobs: (11+Chrome), (11+Firefox), (17+Chrome), (17+Firefox)

---

## API Test Specific Config

For REST API tests without WebDriver:

```yaml
- name: Run API Tests
  run: |
    cd SeleniumJava
    mvn test -Dtest=ApiTests,AdvancedApiTests -DskipBrowserTests=true
```

---

## Documentation

For complete workflow reference, see:
- `.github/workflows/README.md` - Workflow details
- `SeleniumJava/docs/COMPLETE_FRAMEWORK_GUIDE.md` - Test framework
- `SeleniumJava/pom.xml` - Maven configuration

---

**Ready to deploy! 🚀**

Questions? Check GitHub Actions documentation: https://docs.github.com/en/actions
