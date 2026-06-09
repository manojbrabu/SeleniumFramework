package page;

import Annotations.Mandatory;
import base.BasePage;
import Enum.Execution;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ElementUtil;

public class LoginPage extends BasePage {

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
        super(driver);
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
