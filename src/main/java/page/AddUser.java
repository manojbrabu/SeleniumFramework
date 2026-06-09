package page;

import base.BasePage;
import Enum.Execution;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ElementUtil;

public class AddUser extends BasePage {

    @FindBy(xpath = "//form[@class='oxd-form']//following::label[text()='User Role']/parent::div/parent::div//i")
    WebElement drpUserRole;

    @FindBy(xpath = "//form[@class='oxd-form']//following::label[text()='Employee Name']/parent::div/parent::div//input")
    WebElement txtEmployeeName;

    private final String xPathEmployeeNameOption = "//div[@role='listbox']//*[normalize-space(text())='%s']";

    @FindBy(xpath = "//form[@class='oxd-form']//following::label[text()='Status']/parent::div/parent::div//i")
    WebElement drpStatus;

    @FindBy(xpath = "//form[@class='oxd-form']//following::label[text()='Username']/parent::div/parent::div//input")
    WebElement txtUsername;

    @FindBy(xpath = "//form[@class='oxd-form']//following::label[text()='Password']/parent::div/parent::div//input")
    WebElement txtPassword;

    @FindBy(xpath = "//form[@class='oxd-form']//following::label[text()='Confirm Password']/parent::div/parent::div//input")
    WebElement txtconfirmPassword;

    @FindBy(xpath = "//form[@class='oxd-form']//button[@type='submit']")
    WebElement btnSubmit;

    private final String vxPathDropdownList = "//*[@role='option' and normalize-space()='%s']";

    public AddUser(WebDriver driver) {
        super(driver);
    }

    public boolean selectUserRole(String vRole) {
        boolean clicked = ElementUtil.click(drpUserRole, "Role Dropdown", Execution.STOP);
        if (!clicked) {
            return false;
        }

        WebElement options = ElementUtil.findElement(
                driver,
                By.xpath(String.format(vxPathDropdownList, vRole)),
                vRole
        );
        if (options == null) {
            return false;
        }

        return ElementUtil.click(options, vRole + " option", Execution.STOP);
    }

    public boolean enterEmployeeName(String vEmployeeName) {
        boolean entered = ElementUtil.sendKeys(txtEmployeeName, vEmployeeName, "Employee Name textbox", Execution.STOP);
        if (!entered) {
            return false;
        }

        WebElement element = ElementUtil.findElement(
                driver,
                By.xpath(String.format(xPathEmployeeNameOption, vEmployeeName)),
                vEmployeeName + " lookup"
        );
        if (element == null) {
            return false;
        }

        return ElementUtil.click(element, "Select Employee Name " + vEmployeeName, Execution.STOP);
    }

    public boolean selectStatus(String vStatus) {
        boolean clicked = ElementUtil.click(drpStatus, "Status Dropdown", Execution.STOP);
        if (!clicked) {
            return false;
        }

        WebElement options = ElementUtil.findElement(
                driver,
                By.xpath(String.format(vxPathDropdownList, vStatus)),
                vStatus
        );
        if (options == null) {
            return false;
        }

        return ElementUtil.click(options, vStatus + " option", Execution.STOP);
    }

    public boolean enterUsername(String vUsername) {
        return ElementUtil.sendKeys(txtUsername, vUsername, "Username textbox", Execution.STOP);
    }

    public boolean enterPassword(String vPassword) {
        return ElementUtil.sendKeys(txtPassword, vPassword, "Password textbox", Execution.STOP);
    }

    public boolean enterConfirmPassword(String vConfirmPassword) {
        return ElementUtil.sendKeys(txtconfirmPassword, vConfirmPassword, "Confirm Password textbox", Execution.STOP);
    }

    public boolean saveUser() {
        return ElementUtil.click(btnSubmit, "Submit button", Execution.STOP);
    }
}
