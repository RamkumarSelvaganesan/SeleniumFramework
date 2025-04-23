package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ExtentReportManager;
import utils.RetryAnalyzer;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void invalidLoginShowsError() {
        loginPage = new LoginPage(driver);
        loginPage.login("test123", "test");
    }
}