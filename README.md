# Selenium TestNG Parallel Framework

Cross-browser parallel test automation framework using Selenium 4, TestNG, Maven, and Java 17.

## Tech Stack
- **Java 17**
- **Selenium 4.21** (Selenium Manager auto-downloads drivers — no manual setup)
- **TestNG 7.10**
- **ExtentReports 5.1** (HTML reports with screenshots on failure)
- **Logback + SLF4J** (structured logging)
- **Page Object Model** design pattern

## Project Structure
```
selenium-framework/
├── pom.xml
├── src/
│   ├── main/java/com/framework/
│   │   ├── base/              BasePage, BaseTest
│   │   ├── config/            ConfigReader
│   │   ├── driver/            DriverFactory, DriverManager (ThreadLocal)
│   │   ├── listeners/         TestListener (Extent + screenshots)
│   │   ├── pages/             LoginPage, InventoryPage
│   │   └── utils/             ExtentManager, ScreenshotUtil
│   └── test/
│       ├── java/com/framework/tests/   LoginTest
│       └── resources/          testng.xml, config.properties, logback.xml
└── test-output/                Generated reports, screenshots, logs
```

## How Parallel Execution Works

The framework runs Chrome, Firefox, and Edge **simultaneously** using:

1. **`testng.xml`** — `parallel="tests"` + `thread-count="3"` makes each `<test>` tag run on its own thread.
2. **`ThreadLocal<WebDriver>`** in `DriverManager` — ensures every thread has its own isolated driver instance (no cross-contamination).
3. **`@Parameters({"browser"})`** in `BaseTest` — each `<test>` tag passes its browser name into setup.

## Running Tests

### Run the full parallel suite (all 3 browsers)
```bash
mvn clean test
```

### Run a single browser
Edit `testng.xml` to comment out unwanted `<test>` blocks, or pass a custom suite:
```bash
mvn clean test -Dsuite.xml=src/test/resources/testng-chrome.xml
```

### Run headless (CI/CD)
```bash
mvn clean test -Dheadless=true
```

### Override base URL
```bash
mvn clean test -Dbase.url=https://staging.example.com
```

## Reports

After execution, find:
- **HTML report:** `test-output/ExtentReport_<timestamp>.html`
- **Screenshots (failures only):** `test-output/screenshots/`
- **Logs:** `test-output/logs/test-execution.log`

## Adding New Tests

1. Create a Page Object in `src/main/java/com/framework/pages/` extending `BasePage`.
2. Create a test class in `src/test/java/com/framework/tests/` extending `BaseTest`.
3. Add the class to `testng.xml` under each browser's `<classes>` block.

## Prerequisites
- Java 17+
- Maven 3.8+
- Chrome, Firefox, and Edge installed locally (Selenium Manager downloads matching drivers automatically on first run)
