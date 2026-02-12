package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;
    @FindBy(xpath ="//h6[text()='Dashboard']")
    WebElement dashboard;
    @FindBy(xpath ="//span[text()='Admin']")
    WebElement admin;
    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public boolean dashboardIsDisplayed(){
        return dashboard.isDisplayed();
    }
    public void clickAdmin(){
        admin.click();
    }
}
