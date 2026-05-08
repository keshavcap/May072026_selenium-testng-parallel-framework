package com.framework.tests;

import com.framework.base.BaseTest;
import com.framework.pages.HomePage;
import com.framework.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Verify successful login with valid credentials")
    public void testValidLogin() {
        new HomePage().clickFormAuthentication();
        LoginPage loginPage = new LoginPage();
        loginPage.login("tomsmith", "SuperSecretPassword!");

        Assert.assertTrue(loginPage.isFlashDisplayed(), "Success flash message should appear");
        Assert.assertTrue(loginPage.getFlashMessage().contains("You logged into a secure area"),
                "Flash message should confirm successful login");
        Assert.assertEquals(loginPage.getPageHeader(), "Secure Area");
    }

    @Test(description = "Verify login fails with invalid username")
    public void testInvalidUsername() {
        new HomePage().clickFormAuthentication();
        LoginPage loginPage = new LoginPage();
        loginPage.login("wronguser", "SuperSecretPassword!");

        Assert.assertTrue(loginPage.isFlashDisplayed());
        Assert.assertTrue(loginPage.getFlashMessage().contains("Your username is invalid"),
                "Flash message should mention invalid username");
    }

    @Test(description = "Verify login fails with invalid password")
    public void testInvalidPassword() {
        new HomePage().clickFormAuthentication();
        LoginPage loginPage = new LoginPage();
        loginPage.login("tomsmith", "wrongpassword");

        Assert.assertTrue(loginPage.isFlashDisplayed());
        Assert.assertTrue(loginPage.getFlashMessage().contains("Your password is invalid"),
                "Flash message should mention invalid password");
    }
}
