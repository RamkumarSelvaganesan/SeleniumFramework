package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

public class ElementActions {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    public void click(By locator) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
            ExtentReportManager.getTest().info("Clicked on: " + locator);
        } catch (Exception e) {
            ExtentReportManager.getTest().fail("Click failed: " + locator + " - " + e.getMessage());
            throw e;
        }
    }

    public void type(By locator, String text) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.clear();
            element.sendKeys(text);
            ExtentReportManager.getTest().info("Typed '" + text + "' into: " + locator);
        } catch (Exception e) {
            ExtentReportManager.getTest().fail("Typing failed: " + locator + " - " + e.getMessage());
            throw e;
        }
    }

    public String getText(By locator) {
        try {
            String text = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
            ExtentReportManager.getTest().info("Fetched text '" + text + "' from: " + locator);
            return text;
        } catch (Exception e) {
            ExtentReportManager.getTest().fail("Get text failed: " + locator + " - " + e.getMessage());
            throw e;
        }
    }

    public boolean isDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            ExtentReportManager.getTest().info("Element not displayed: " + locator);
            return false;
        }
    }

    public void clickWithJS(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            ExtentReportManager.getTest().info("Clicked using JS: " + locator);
        } catch (Exception e) {
            ExtentReportManager.getTest().fail("JS Click failed: " + locator + " - " + e.getMessage());
            throw e;
        }
    }

    public void hoverOver(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            actions.moveToElement(element).perform();
            ExtentReportManager.getTest().info("Hovered over: " + locator);
        } catch (Exception e) {
            ExtentReportManager.getTest().fail("Hover failed: " + locator + " - " + e.getMessage());
            throw e;
        }
    }

    public void selectDropdownByVisibleText(By locator, String text) {
        try {
            WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            new Select(dropdown).selectByVisibleText(text);
            ExtentReportManager.getTest().info("Selected '" + text + "' from dropdown: " + locator);
        } catch (Exception e) {
            ExtentReportManager.getTest().fail("Dropdown selection failed: " + locator + " - " + e.getMessage());
            throw e;
        }
    }

    public void waitForElementToDisappear(By locator) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            ExtentReportManager.getTest().info("Element disappeared: " + locator);
        } catch (TimeoutException e) {
            ExtentReportManager.getTest().fail("Element didn't disappear: " + locator);
        }
    }

    public void waitForAttributeToBe(By locator, String attr, String value) {
        try {
            wait.until(ExpectedConditions.attributeToBe(locator, attr, value));
            ExtentReportManager.getTest().info("Attribute '" + attr + "' became '" + value + "' on: " + locator);
        } catch (Exception e) {
            ExtentReportManager.getTest().fail("Attribute wait failed: " + locator + " - " + e.getMessage());
            throw e;
        }
    }

    public List<WebElement> getElements(By locator) {
        try {
            List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            ExtentReportManager.getTest().info("Found " + elements.size() + " elements by: " + locator);
            return elements;
        } catch (Exception e) {
            ExtentReportManager.getTest().fail("Fetching multiple elements failed: " + locator + " - " + e.getMessage());
            throw e;
        }
    }
    
    public void pressTabMultipleTimes(int times) {
        try {
            for (int i = 0; i < times; i++) {
                actions.sendKeys(Keys.TAB);
            }
            actions.perform();
            ExtentReportManager.getTest().info("Pressed TAB " + times + " times");
        } catch (Exception e) {
            ExtentReportManager.getTest().fail("Failed to press TAB " + times + " times - " + e.getMessage());
            throw e;
        }
    }
    
    public void pressEnter() {
        try {
            actions.sendKeys(Keys.ENTER).perform();
            ExtentReportManager.getTest().info("Pressed ENTER");
        } catch (Exception e) {
            ExtentReportManager.getTest().fail("Failed to press ENTER - " + e.getMessage());
            throw e;
        }
    }
}