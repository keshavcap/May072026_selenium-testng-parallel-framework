package com.framework.utils;

import com.framework.driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {

    private static final Logger log = LoggerFactory.getLogger(ScreenshotUtil.class);
    private static final String SCREENSHOT_DIR = "test-output/screenshots/";

    private ScreenshotUtil() {}

    public static String capture(String testName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
            File source = ts.getScreenshotAs(OutputType.FILE);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String filePath = SCREENSHOT_DIR + testName + "_" + timestamp + ".png";
            File dest = new File(filePath);
            FileUtils.copyFile(source, dest);
            return dest.getAbsolutePath();
        } catch (IOException e) {
            log.error("Screenshot capture failed", e);
            return null;
        }
    }
}
