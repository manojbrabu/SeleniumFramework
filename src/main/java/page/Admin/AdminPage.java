package page.Admin;

import base.BaseTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import utils.ExceptionHandling;
import utils.ExtentManager;
import utils.ScreenshotUtils;
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

    By lblUsername = By.xpath( "//div[@class='oxd-table-card'][1]//div[@role='cell'][2]");

    String vxPathDropdownList = "//*[@role='option' and normalize-space()='###']";

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
            ExceptionHandling.handleCriticalException("Failed to enter Username", e);
        }
    }

    public void selectRole(String vRole) {
        try {
            WaitUtils.waitForElementClickable(driver, drpUserRole);
            drpUserRole.click();
            String vOption =  vxPathDropdownList.replace("###",vRole);
            WebElement options = driver.findElement(By.xpath(vOption));
            WaitUtils.waitForElementVisible(driver, options);
            options.click();
            ExtentManager.test().info("Role selected: " + vRole);
        } catch (Exception e) {
            ExceptionHandling.handleNonCriticalException("Failed to select role", e);
        }
    }

    public void enterEmployeeName(String vEmployeeName) {
        try {
            WaitUtils.waitForElementClickable(driver, txtEmployeeName);
            txtEmployeeName.sendKeys(vEmployeeName);
            String vOption =  vxPathDropdownList.replace("###",vEmployeeName);
            WebElement options = driver.findElement(By.xpath(vOption));
            WaitUtils.waitForElementVisible(driver, options);
            options.click();
            ExtentManager.test().info("Enter Employee Name: " + vEmployeeName);
        } catch (Exception e) {
            ExceptionHandling.handleNonCriticalException("Failed to enter Employee Name", e);
        }
    }

    public void selectStatus(String vStatus) {
        try {
            WaitUtils.waitForElementClickable(driver, drpStatus);
            drpStatus.click();
            String vOption =  vxPathDropdownList.replace("###",vStatus);
            WebElement options = driver.findElement(By.xpath(vOption));
            WaitUtils.waitForElementVisible(driver, options);
            options.click();
            ExtentManager.test().info("Status selected: " + vStatus);
        } catch (Exception e) {
            ExceptionHandling.handleNonCriticalException("Failed to select status", e);
        }
    }

    public void clickSearch() {
        try {
            WaitUtils.waitForElementClickable(driver, btnSearch);
            btnSearch.click();
            ExtentManager.test().info("Search button clicked");
        } catch (Exception e) {
            ExceptionHandling.handleNonCriticalException("Failed to click Search button", e);
        }
    }

    public void clickAdd() {
        try {
            WaitUtils.waitForElementClickable(driver, add);
            add.click();
            ExtentManager.test().info("Click Add button");
        } catch (Exception e) {
            ExceptionHandling.handleNonCriticalException("Failed to click Add button", e);
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
            ExtentManager.test().info("Navigated to "+menu+">"+submenu);
        } catch (Exception e) {
            ExceptionHandling.handleNonCriticalException("Failed to navigate " + menu + " > " + submenu, e);
        }
    }

    public boolean adminpageIsDisplayed() {
        boolean isDisplayed = false;
        try{
            isDisplayed = admin.isDisplayed();
        }catch (Exception e){
            ExceptionHandling.handleNonCriticalException("Failed to Navigate to Admin Page", e);
        }
        return isDisplayed;
    }

    public boolean fnVerifyUsernameAdded(String vUsername){
        try{
            WebElement lblUserName = driver.findElement(lblUsername);
            WaitUtils.waitForElementTextPresent(driver, lblUserName, vUsername);

            String actValue = lblUserName.getText().trim();
            Assert.assertEquals(actValue, vUsername);
            ExtentManager.test().pass("Username '"+actValue+"' is added and displayed", MediaEntityBuilder.createScreenCaptureFromPath(ScreenshotUtils.capture(driver, "VerifyUser")).build());

        }catch (Exception e){
            ExceptionHandling.handleNonCriticalException("Failed to verify username", e);
        }

        return true;
    }
}