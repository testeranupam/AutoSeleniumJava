# GitHub Actions CI/CD Pipeline

This directory contains GitHub Actions workflows for automated testing via CI/CD.

## Workflows

### 1. **maven-test.yml** - Main Test Workflow
**Trigger:** Push to main/develop/master, Pull Requests, Manual trigger

**Features:**
- Runs on Ubuntu Latest
- Tests with Java 11 and 17 (matrix strategy)
- Sequential test execution
- Parallel test execution (PR/main only)
- Artifact upload (test reports, screenshots)
- TestNG report parsing

**On Success:** ✅ All tests pass  
**On Failure:** ⚠️ Artifacts available for debugging

### 2. **selenium-parallel.yml** - Parallel Execution Workflow
**Trigger:** Daily schedule (2 AM UTC), Manual trigger

**Features:**
- Runs 3 parallel strategies simultaneously:
  - By Classes
  - By Methods
  - By Tests
- Compares performance
- Generates parallel execution summary

**Use Case:** Nightly stress testing and performance benchmarking

## Workflow Status

View status at: `https://github.com/YOUR_REPO/actions`

## Manual Workflow Trigger

In GitHub Actions tab → Select workflow → "Run workflow" button

## Artifacts

Test results are stored for 30 days:
- `target/surefire-reports/` - TestNG XML reports
- `target/screenshots/` - Auto-captured screenshots

## Environment Setup

All workflows:
1. Check out code
2. Set up Java (JDK 11 or 17)
3. Cache Maven dependencies
4. Run `mvn clean test`

## Configuration

**To modify:**
- Change `java-version` matrix
- Add branches to `on.push.branches`
- Adjust `schedule` for parallel workflow
- Modify Maven flags in `run` commands

## Example: Run Specific Tests

```yaml
mvn test -Dtest=AdvancedApiTests
mvn test -Dtest=ApiTests,GoogleSearchTest
```

## Parallel Execution Modes

### testng-parallel-classes.xml
```
Runs multiple test classes in parallel
Best for: Large test suites
```

### testng-parallel-methods.xml
```
Runs test methods in parallel
Best for: Independent tests
```

### testng-parallel-tests.xml
```
Runs <test> tags in parallel
Best for: Different browser configs
```

## Troubleshooting

**Tests Failing in Pipeline but Passing Locally:**
- Check Java version match
- Verify file paths (SeleniumJava/ prefix)
- Check for hardcoded paths
- Review logs in GitHub Actions UI

**Artifacts Not Generated:**
- Ensure test failure continues with `continue-on-error: true`
- Check path: `SeleniumJava/target/surefire-reports/`

**Slow Execution:**
- Use parallel workflows
- Increase `thread-count` in TestNG XML
- Split tests across multiple jobs

## Security Notes

- No hardcoded credentials in workflows
- Use GitHub Secrets for sensitive data:
  ```yaml
  env:
    DATABASE_URL: ${{ secrets.DATABASE_URL }}
    API_KEY: ${{ secrets.API_KEY }}
  ```

## Next Steps

1. Push to GitHub
2. Enable Actions in repository settings
3. View workflow runs in Actions tab
4. Set up branch protection (require checks to pass)
5. Configure email/Slack notifications

---

**Last Updated:** February 2026
