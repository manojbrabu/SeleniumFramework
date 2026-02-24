package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ExceptionHandling;
import utils.ExtentManager;
import utils.WaitUtils;

public class HomePage {

    WebDriver driver;

    @FindBy(xpath ="//h6[text()='Dashboard']")
    WebElement dashboard;

    @FindBy(xpath ="//span[text()='Admin']")
    WebElement admin;

    @FindBy(xpath ="//span[text()='Leave']")
    WebElement leave;

    @FindBy(xpath = "//p[@class='oxd-userdropdown-name]")
    WebElement userdropdown;

    @FindBy(xpath = "//a[@href='/web/index.php/auth/logout']")
    WebElement logout;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean dashboardIsDisplayed(){
        try {
            boolean displayed = dashboard.isDisplayed();
            //ExtentManager.test().info("Dashboard displayed: ");
            return displayed;
        } catch (Exception e) {
            ExceptionHandling.handleCriticalException("Dashboard not displayed", e);
            return false;
        }
    }

    public void navigateToAdmin(){
        try {
            WaitUtils.waitForElementClickable(driver, admin);
            admin.click();
            ExtentManager.test().info("Navigated to Admin tab");
        } catch (Exception e) {
            ExceptionHandling.handleCriticalException("Failed to click Admin tab", e);
        }
    }

    public void navigateToUsermenu(){
        try {
           userdropdown.click();
            ExtentManager.test().info("Click on user dropdown");
        } catch (Exception e) {
            ExceptionHandling.handleCriticalException("Failed to click user dropdown", e);
        }
    }

    public void clickLogout(){
        try {
            logout.click();
            ExtentManager.test().info("Click on logout");
        } catch (Exception e) {
            ExceptionHandling.handleCriticalException("Failed to click logout", e);
        }
    }

    public void navigateToLeave(){
        try {
            WaitUtils.waitForElementClickable(driver, leave);
            leave.click();
            ExtentManager.test().info("Navigated to Leave tab");
        } catch (Exception e) {
            ExceptionHandling.handleCriticalException("Failed to click Leave tab", e);
        }
    }
}