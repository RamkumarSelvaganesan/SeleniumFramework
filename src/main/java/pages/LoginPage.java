package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class LoginPage {

    private WebDriver driver;
    private ElementActions elementActions;

    // Locators for the Login Page
    private By usernameField = By.id("email");
    private By passwordField = By.id("password");
    private By notRobotCheckbox = By.cssSelector("div.recaptcha-checkbox-checkmark");
    private By loginButton = By.cssSelector("button[type='submit']");
    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.elementActions = new ElementActions(driver);
    }

    // Method to enter username
    public void enterUsername(String username) {
        elementActions.type(usernameField, username);
    }

    // Method to enter password
    public void enterPassword(String password) {
        elementActions.type(passwordField, password);
    }

    // Method to click login button
    public void clickLogin() {
        elementActions.click(loginButton);
    }

   
    // Method to perform a login action
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickNotRobotCheckbox();
        clickLogin();
    }

    public void clickNotRobotCheckbox() {
    	elementActions.pressTabMultipleTimes(3);
    	elementActions.pressEnter();
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
