package com.framework.tests;

import com.framework.base.BaseTest;
import com.framework.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test(description = "Verify the home page loads with expected heading")
    public void testHomePageLoads() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isLoaded(), "Home page should be loaded");
        Assert.assertEquals(homePage.getHeading(), "Welcome to the-internet");
    }

    @Test(description = "Verify the available examples subheading is displayed")
    public void testSubheadingDisplayed() {
        HomePage homePage = new HomePage();
        Assert.assertEquals(homePage.getSubheading(), "Available Examples");
    }

    @Test(description = "Verify the page lists multiple example links")
    public void testExamplesArePresent() {
        HomePage homePage = new HomePage();
        int count = homePage.getExampleCount();
        Assert.assertTrue(count > 30, "Expected more than 30 example links, got: " + count);
    }
}
