package page;

import base.BasePage;
import Enum.Execution;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.DriverManager;
import utils.ElementUtil;

public class AdminPage extends BasePage {

    @FindBy(xpath = "//h6[text()='Admin']")
    WebElement adminHeader;

    @FindBy(xpath = "//div[@class='orangehrm-header-container']/button")
    WebElement btnAdd;

    @FindBy(xpath = "//label[text()='Username']/ancestor::div[2]//input")
    WebElement txtUsername;

    @FindBy(xpath = "//label[text()='User Role']/ancestor::div[2]/div[2]")
    WebElement drpUserRole;

    @FindBy(xpath = "//label[text()='Employee Name']/ancestor::div[2]//input")
    WebElement txtEmployeeName;

    @FindBy(xpath = "//label[text()='Status']/ancestor::div[2]/div[2]")
    WebElement drpStatus;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSearch;

    private final By lblUsername = By.xpath("//div[@class='oxd-table-card'][1]//div[@role='cell'][2]");

    private final String dropdownOptionXpath = "//*[@role='option' and normalize-space()='%s']";

    private final By toastMessage = By.xpath("//div[@class='oxd-toast-start']//p[2]");

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    public boolean clickAdd() {
        return ElementUtil.click(btnAdd, "Add Button", Execution.STOP);
    }

    public boolean enterUsername(String username) {
        return ElementUtil.sendKeys(txtUsername, username, "Username", Execution.STOP);
    }

    public boolean selectRole(String role) {
        boolean clicked = ElementUtil.click(drpUserRole, "User Role Dropdown", Execution.STOP);
        if (!clicked) {
            return false;
        }

        WebElement option = DriverManager.getDriver().findElement(By.xpath(String.format(dropdownOptionXpath, role)));
        return ElementUtil.click(option, role + " Role Option", Execution.STOP);
    }

    public boolean enterEmployeeName(String employeeName) {
        boolean entered = ElementUtil.sendKeys(txtEmployeeName, employeeName, "Employee Name", Execution.STOP);
        if (!entered) {
            return false;
        }

        WebElement option = ElementUtil.findElement(
                driver,
                By.xpath(String.format(dropdownOptionXpath, employeeName)),
                employeeName + " option"
        );
        if (option == null) {
            return false;
        }

        return ElementUtil.click(option, employeeName + " Option", Execution.STOP);
    }

    public boolean selectStatus(String status) {
        boolean clicked = ElementUtil.click(drpStatus, "Status Dropdown", Execution.STOP);
        if (!clicked) {
            return false;
        }

        WebElement option = ElementUtil.findElement(
                driver,
                By.xpath(String.format(dropdownOptionXpath, status)),
                status + " option"
        );
        if (option == null) {
            return false;
        }

        return ElementUtil.click(option, status + " Status Option", Execution.STOP);
    }

    public boolean clickSearch() {
        return ElementUtil.click(btnSearch, "Search Button", Execution.STOP);
    }

    public boolean isAdminPageDisplayed() {
        return ElementUtil.isDisplayed(adminHeader, "Admin Header", Execution.STOP);
    }

    public boolean verifyUsernameDisplayed(String expectedUsername) {
        WebElement lblUser = ElementUtil.findElement(driver, lblUsername, "Username label");
        if (lblUser == null) {
            return false;
        }
        return ElementUtil.verifyText(lblUser, expectedUsername, Execution.STOP);
    }

    public boolean verifySuccessMessage() {
        WebElement element = ElementUtil.findElement(driver, toastMessage, "Toaster Message");
        if (element == null) {
            return false;
        }
        return ElementUtil.verifyText(element, "Successfully Saved", Execution.STOP);
    }
}
