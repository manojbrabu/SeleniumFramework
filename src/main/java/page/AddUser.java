package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.*;
import Enum.Execution;

public class AddUser {
    WebDriver driver;

    @FindBy(xpath = "//form[@class='oxd-form']//following::label[text()='User Role']/parent::div/parent::div//i")
    WebElement drpUserRole;
    @FindBy(xpath = "//form[@class='oxd-form']//following::label[text()='Employee Name']/parent::div/parent::div//input")
    WebElement txtEmployeeName;
    String xPathEmployeeNameOption = "//div[@role='listbox']//*[normalize-space(text())='***']";
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

    //Dropdown Listbox
    String vxPathDropdownList = "//*[@role='option' and normalize-space()='###']";
    String vxPatUserRecords = "//div[@class='oxd-table-card'][***]//div[@role='cell'][###]";


    public AddUser(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Dropdown list box selection
    public boolean selectUserRole(String vRole){
        boolean clicked =  ElementUtil.click(drpUserRole,"Role Dropdown", Execution.STOP);
        if (!clicked) return false;
            String vOption =  vxPathDropdownList.replace("###",vRole);
            WebElement options = ElementUtil.findElement(driver,By.xpath(vOption),vRole);
            return ElementUtil.click(options, vRole+" option", Execution.STOP);

    }

    public boolean enterEmployeeName(String vEmployeeName){
        Boolean exp = ElementUtil.sendKeys(txtEmployeeName, vEmployeeName, "Employee Name textbox", Execution.STOP);
        if(!exp) return false;
        xPathEmployeeNameOption = xPathEmployeeNameOption.replace("***",vEmployeeName);
        WebElement element = ElementUtil.findElement(driver,By.xpath(xPathEmployeeNameOption),vEmployeeName +" lookup");
        if(element==null) return false;
        return ElementUtil.click(element, "Select Employee Name"+ vEmployeeName, Execution.STOP);
    }

    public boolean selectStatus(String vStatus){
        boolean clicked =  ElementUtil.click(drpStatus,"Role Dropdown", Execution.STOP);
        if (!clicked) return false;
        String vOption =  vxPathDropdownList.replace("###",vStatus);
        WebElement options = ElementUtil.findElement(driver,By.xpath(vOption),vStatus);
        return ElementUtil.click(options, vStatus+" option", Execution.STOP);
    }
    public boolean enterUsername(String vUsername){
        return ElementUtil.sendKeys(txtUsername, vUsername, "Username textbox", Execution.STOP);
    }

    public boolean enterPassword(String vPassword){
        return ElementUtil.sendKeys(txtPassword, vPassword, "Password textbox", Execution.STOP);
    }

    public boolean enterConfirmPassword(String vConfirmPassword){
        return ElementUtil.sendKeys(txtconfirmPassword, vConfirmPassword, "Confirm Password textbox", Execution.STOP);
    }

    public boolean saveUser(){
        return ElementUtil.click(btnSubmit, "Submit button", Execution.STOP);
    }
}
