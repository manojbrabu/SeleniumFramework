package page;

import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ExceptionHandling;
import utils.ExtentManager;
import utils.WaitUtils;

import java.util.List;

public class AdminPage {

    private static final Logger log = LoggerFactory.getLogger(AdminPage.class);
    WebDriver driver;

    @FindBy(xpath ="//h6[text()='Admin']")
    WebElement admin;

    @FindBy(xpath ="//div[@class='orangehrm-header-container']/button")
    WebElement add;

    @FindBy(xpath ="//label[text()='Username']/ancestor::div[2]//input")
    WebElement txtUsername;

    @FindBy(xpath ="//label[text()='User Role']/ancestor::div[2]/div[2]")
    WebElement drpUserRole;

    @FindBy(xpath ="//label[text()='Employee Name']/ancestor::div[2]//input")
    WebElement txtEmployeeName;

    @FindBy(xpath ="//label[text()='Status']/ancestor::div[2]/div[2]")
    WebElement drpStatus;

    @FindBy(xpath ="//button[@type='submit']")
    WebElement btnSearch;

    public AdminPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUserName(String username) {
        try {
            WaitUtils.waitForElementClickable(driver, txtUsername);
            txtUsername.sendKeys(username);
            ExtentManager.test().info("Enter Username: " + username);
        } catch (Exception e) {
            ExceptionHandling.handleException("Failed to enter Username", e);
        }
    }

    public void selectRole(String role) {
        try {
            WaitUtils.waitForElementClickable(driver, drpUserRole);
            drpUserRole.click();
            ExtentManager.test().info("Role selected: " + role);
        } catch (Exception e) {
            ExceptionHandling.handleException("Failed to select role", e);
        }
    }

    public void enterEmployeeName(String employeeName) {
        try {
            WaitUtils.waitForElementClickable(driver, txtEmployeeName);
            txtEmployeeName.sendKeys(employeeName);
            ExtentManager.test().info("Enter Employee Name: " + employeeName);
        } catch (Exception e) {
            ExceptionHandling.handleException("Failed to enter Employee Name", e);
        }
    }

    public void selectStatus(String status) {
        try {
            WaitUtils.waitForElementClickable(driver, drpStatus);
            drpStatus.click();
            ExtentManager.test().info("Status selected: " + status);
        } catch (Exception e) {
            ExceptionHandling.handleException("Failed to select status", e);
        }
    }

    public void clickSearch() {
        try {
            WaitUtils.waitForElementClickable(driver, btnSearch);
            btnSearch.click();
            ExtentManager.test().info("Search button clicked");
        } catch (Exception e) {
            ExceptionHandling.handleException("Failed to click Search button", e);
        }
    }

    public void clickAdd() {
        try {
            WaitUtils.waitForElementClickable(driver, add);
            add.click();
            ExtentManager.test().info("Click Add button");
        } catch (Exception e) {
            ExceptionHandling.handleException("Failed to click Add button", e);
        }
    }

    public void navigateMenu(String menu, String submenu) {
        try {
            List<WebElement> topMenu =
                    driver.findElements(By.xpath("//nav[@aria-label='Topbar Menu']/ul/li"));

            for (WebElement menulist : topMenu) {
                if (menulist.getText().trim().equals(menu)) {
                    menulist.click();

                    List<WebElement> dropdown =
                            driver.findElements(By.xpath("//ul[@class='oxd-dropdown-menu']/li"));

                    for (WebElement sub : dropdown) {
                        if (sub.getText().trim().equals(submenu)) {
                            WaitUtils.waitForElementClickable(driver, sub);
                            sub.click();
                            break;
                        }
                    }
                    break;
                }
            }
        } catch (Exception e) {
            ExceptionHandling.handleException("Failed to navigate " + menu + " > " + submenu, e);
        }
    }

    public boolean adminpageIsDisplayed() {
        boolean isDisplayed = false;
        try{
            isDisplayed = admin.isDisplayed();
        }catch (Exception e){
            ExceptionHandling.handleException("Failed to Navigate to Admin Page", e);
        }
        return isDisplayed;
    }
}