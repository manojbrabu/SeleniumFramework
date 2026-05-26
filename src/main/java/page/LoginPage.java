package page;

import Annotations.Mandatory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtil;
import utils.ExceptionHandling;
import utils.ExtentManager;
import utils.WaitUtils;
import Enum.Execution;

public class LoginPage {

    WebDriver driver;

    @Mandatory
    @FindBy(name = "username")
    WebElement txtUsername;

    @Mandatory
    @FindBy(name = "password")
    WebElement txtPassword;

    @Mandatory
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnLoginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean enterUserName(String user) {
         return ElementUtil.sendKeys(txtUsername, user, "Username", Execution.STOP);
    }

    public boolean enterPassword(String pass) {
          return ElementUtil.sendKeys(txtPassword, pass, "Password", Execution.STOP);
    }

    public boolean clickLogin() {
        return ElementUtil.click(btnLoginButton, "Login button", Execution.STOP);
    }
}