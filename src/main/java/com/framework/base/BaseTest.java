package com.framework.base;

import com.framework.config.ConfigReader;
import com.framework.driver.DriverFactory;
import com.framework.driver.DriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class BaseTest {

    protected static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) {
        log.info("[Thread {}] Initializing {} driver", Thread.currentThread().getId(), browser);
        DriverManager.setDriver(DriverFactory.createDriver(browser));
        DriverManager.getDriver().get(ConfigReader.get("base.url"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        log.info("[Thread {}] Quitting driver", Thread.currentThread().getId());
        DriverManager.quitDriver();
    }
}
