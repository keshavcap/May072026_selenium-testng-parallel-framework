package com.framework.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.framework.utils.ExtentManager;
import com.framework.utils.ScreenshotUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static final Logger log = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onStart(ITestContext context) {
        log.info("Starting test suite: {}", context.getName());
        ExtentManager.getInstance();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String browser = getBrowserParam(result);
        String testName = result.getMethod().getMethodName() + " [" + browser + "]";
        ExtentTest test = ExtentManager.getInstance().createTest(testName);
        test.assignCategory(browser);
        ExtentManager.setTest(test);
        log.info("Starting test: {}", testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.getTest().pass("Test passed");
        log.info("Test passed: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        log.error("Test failed: {}", testName, result.getThrowable());

        String screenshotPath = ScreenshotUtil.capture(testName);
        ExtentTest test = ExtentManager.getTest();
        test.fail(result.getThrowable());
        if (screenshotPath != null) {
            try {
                test.addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                log.error("Failed to attach screenshot to report", e);
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentManager.getTest().skip(result.getThrowable() != null
                ? result.getThrowable().getMessage()
                : "Test skipped");
        log.warn("Test skipped: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Finishing test suite: {}", context.getName());
        ExtentManager.getInstance().flush();
        ExtentManager.removeTest();
    }

    private String getBrowserParam(ITestResult result) {
        Object[] params = result.getParameters();
        if (params != null && params.length > 0 && params[0] != null) {
            return params[0].toString();
        }
        String browser = result.getTestContext().getCurrentXmlTest().getParameter("browser");
        return browser != null ? browser : "unknown";
    }
}
