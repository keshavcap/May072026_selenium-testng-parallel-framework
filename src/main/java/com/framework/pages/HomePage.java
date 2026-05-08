package com.framework.pages;

import com.framework.base.BasePage;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private final By heading = By.cssSelector("h1.heading");
    private final By subheading = By.cssSelector("h2");
    private final By formAuthLink = By.linkText("Form Authentication");
    private final By exampleLinks = By.cssSelector("ul li a");

    public boolean isLoaded() {
        return isDisplayed(heading);
    }

    public String getHeading() {
        return getText(heading);
    }

    public String getSubheading() {
        return getText(subheading);
    }

    public int getExampleCount() {
        return driver.findElements(exampleLinks).size();
    }

    public void clickFormAuthentication() {
        click(formAuthLink);
    }
}
