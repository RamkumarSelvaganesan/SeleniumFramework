package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.ExtentReportManager;
import utils.ScreenshotUtil;

import java.io.IOException;
import java.lang.reflect.Method;
public class BaseTest {
    protected WebDriver driver;
    protected ConfigReader config;

    @BeforeSuite
    public void setupReport() {
        ExtentReportManager.initReport();
    }

    @BeforeSuite
    public void loadConfig() {
        config = new ConfigReader();
    }

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser, Method method) {
        driver = DriverFactory.initDriver(browser != null && !browser.isEmpty() ? browser : config.getProperty("browser"));
        driver.get(config.getProperty("url"));
        ExtentReportManager.createTest(method.getName());
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
            ExtentReportManager.getTest().fail(result.getThrowable())
                .addScreenCaptureFromPath(screenshotPath);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentReportManager.getTest().pass("Test passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentReportManager.getTest().skip("Test skipped");
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
            ExtentReportManager.getTest().fail(result.getThrowable())
                .addScreenCaptureFromPath(screenshotPath);
        }
        DriverFactory.quitDriver();
    }

    @AfterSuite
    public void flushReport() {
        ExtentReportManager.flushReport();
    }
}