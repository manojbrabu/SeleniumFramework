package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ExceptionHandling;
import utils.ExtentManager;
import utils.WaitUtils;

public class LoginPage {

    WebDriver driver;

    @FindBy(name = "username")
    WebElement txtUsername;

    @FindBy(name = "password")
    WebElement txtPassword;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnLoginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUserName(String user) {
        try {
            WaitUtils.waitForElementVisible(driver, txtUsername);
            txtUsername.clear();
            txtUsername.sendKeys(user);
            ExtentManager.test().info("Entered Username: " + user);
        } catch (Exception e) {
            ExceptionHandling.handleException("Failed to enter username", e);
        }
    }

    public void enterPassword(String pass) {
        try {
            WaitUtils.waitForElementVisible(driver, txtPassword);
            txtPassword.clear();
            txtPassword.sendKeys(pass);
            ExtentManager.test().info("Entered Password");
        } catch (Exception e) {
            ExceptionHandling.handleException("Failed to enter password", e);
        }
    }

    public void clickLogin() {
        try {
            WaitUtils.waitForElementClickable(driver, btnLoginButton);
            btnLoginButton.click();
            ExtentManager.test().info("Clicked Login button");
        } catch (Exception e) {
            ExceptionHandling.handleException("Failed to click login button", e);
        }
    }
}