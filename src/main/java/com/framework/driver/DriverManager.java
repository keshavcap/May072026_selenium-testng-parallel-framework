package com.framework.driver;

import org.openqa.selenium.WebDriver;

/**
 * ThreadLocal driver storage — this is the key to safe parallel execution.
 * Each TestNG thread gets its own WebDriver instance with no shared state.
 */
public class DriverManager {

    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    private DriverManager() {}

    public static WebDriver getDriver() {
        WebDriver driver = driverThread.get();
        if (driver == null) {
            throw new IllegalStateException("Driver not initialized for this thread. Call setDriver() first.");
        }
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        driverThread.set(driver);
    }

    public static void quitDriver() {
        WebDriver driver = driverThread.get();
        if (driver != null) {
            driver.quit();
            driverThread.remove();
        }
    }
}
