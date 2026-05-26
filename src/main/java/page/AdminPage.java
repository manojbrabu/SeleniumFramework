package page;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.*;
import Enum.Execution;

public class AdminPage {

    WebDriver driver;

    @FindBy(xpath ="//h6[text()='Admin']")
    WebElement adminHeader;

    @FindBy(xpath ="//div[@class='orangehrm-header-container']/button")
    WebElement btnAdd;

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

    private By lblUsername =
            By.xpath("//div[@class='oxd-table-card'][1]//div[@role='cell'][2]");

    private String dropdownOptionXpath =
            "//*[@role='option' and normalize-space()='###']";

    private By toastMessage =
            By.xpath("//div[@class='oxd-toast-start']//p[2]");

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // =========================
    // Actions
    // =========================

    public boolean clickAdd() {
        return ElementUtil.click(btnAdd, "Add Button", Execution.STOP);
    }

    public boolean enterUsername(String username) {
        return ElementUtil.sendKeys(txtUsername, username, "Username", Execution.STOP);
    }

    public boolean selectRole(String role) {

        boolean clicked = ElementUtil.click(drpUserRole, "User Role Dropdown");
        if (!clicked) return false;

        String optionXpath = dropdownOptionXpath.replace("###", role);
        WebElement option = DriverManager.getDriver().findElement(By.xpath(optionXpath));

        return ElementUtil.click(option, role + " Role Option");
    }

    public boolean enterEmployeeName(String employeeName) {

        boolean entered = ElementUtil.sendKeys(txtEmployeeName, employeeName, "Employee Name");

        if (!entered) return false;

        String optionXpath = dropdownOptionXpath.replace("###", employeeName);
        WebElement option = ElementUtil.findElement(driver,By.xpath(optionXpath),employeeName+" option");

        return ElementUtil.click(option, employeeName + " Option");
    }

    public boolean selectStatus(String status) {

        boolean clicked = ElementUtil.click(drpStatus, "Status Dropdown");
        if (!clicked) return false;

        String optionXpath = dropdownOptionXpath.replace("###", status);
        WebElement option = ElementUtil.findElement(driver,By.xpath(optionXpath),status+" option");
        return ElementUtil.click(option, status + " Status Option");
    }

    public boolean clickSearch() {
        return ElementUtil.click(btnSearch, "Search Button");
    }

    // =========================
    // Verifications
    // =========================

    public boolean isAdminPageDisplayed() {
        return ElementUtil.isDisplayed(adminHeader, "Admin Header", Execution.STOP);
    }

    public boolean verifyUsernameDisplayed(String expectedUsername) {

        WebElement lblUser = ElementUtil.findElement(driver, lblUsername, "Username label");
        if(lblUser==null) return false;
        return ElementUtil.verifyText(lblUser, expectedUsername);

    }

    public boolean verifySuccessMessage() {
        WebElement element = ElementUtil.findElement(driver,toastMessage,"Toaster Message");
        if(element!=null){
            return ElementUtil.verifyText(element, "Successfully Saved", Execution.STOP);
        }
        return false;
    }
}