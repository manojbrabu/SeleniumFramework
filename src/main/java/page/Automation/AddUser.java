package page.Automation;

import Enum.Execution;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtil;

public class AddUser {
    WebDriver driver;

    @FindBy (css = "")
    WebElement email;


    public AddUser(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


}
