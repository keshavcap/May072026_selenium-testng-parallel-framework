package com.framework.pages;

import com.framework.base.BasePage;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By flashMessage = By.id("flash");
    private final By securePageHeader = By.cssSelector("h2");

    public void enterUsername(String username) {
        type(usernameField, username);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void clickLogin() {
        click(loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getFlashMessage() {
        return getText(flashMessage).trim();
    }

    public boolean isFlashDisplayed() {
        return isDisplayed(flashMessage);
    }

    public String getPageHeader() {
        return getText(securePageHeader);
    }
}
