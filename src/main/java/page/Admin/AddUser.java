package page.Admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ExceptionHandling;
import utils.ExtentManager;
import utils.WaitUtils;

public class AddUser {
    WebDriver driver;

    @FindBy(xpath = "//form[@class='oxd-form']//following::label[text()='User Role']/parent::div/parent::div//i")
    WebElement drpUserRole;
    @FindBy(xpath = "//form[@class='oxd-form']//following::label[text()='Employee Name']/parent::div/parent::div//input")
    WebElement txtEmployeeName;
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
        try{
            drpUserRole.click();
            String vOption =  vxPathDropdownList.replace("###",vRole);
            WebElement options = driver.findElement(By.xpath(vOption));
            WaitUtils.waitForElementVisible(driver, options);
            options.click();
            ExtentManager.test().info("Role '"+vRole+"' Selected");
        }catch (Exception e){
            ExceptionHandling.handleCriticalException("Failed to Select User Role",e);
        }
        return true;
    }

    public boolean enterEmployeeName(String vEmployeeName){
         try{
             txtEmployeeName.sendKeys(vEmployeeName);
             String vOption =  vxPathDropdownList.replace("###",vEmployeeName);
             WebElement options = driver.findElement(By.xpath(vOption));
             WaitUtils.waitForElementVisible(driver, options);
             options.click();
             ExtentManager.test().info("Entered Employee Name : "+vEmployeeName);
        }catch (Exception e){
            ExceptionHandling.handleCriticalException("Failed to Enter Employee Name",e);
        }
        return true;
    }

    public boolean selectStatus(String vStatus){
        try{
            drpStatus.click();
            String vOption =  vxPathDropdownList.replace("###",vStatus);
            driver.findElement(By.xpath(vOption)).click();
            ExtentManager.test().info("Role '"+vStatus+"' Selected");
        }catch (Exception e){
            ExceptionHandling.handleCriticalException("Failed to select Status",e);
        }
        return true;
    }
    public boolean enterUsername(String vUsername){
        try{
            txtUsername.sendKeys(vUsername);
            ExtentManager.test().info("Entered Username : "+vUsername);
        }catch (Exception e){
            ExceptionHandling.handleCriticalException("Failed to enter Username",e);
        }
        return true;
    }

    public boolean enterPassword(String vPassword){
        try{
            txtPassword.sendKeys(vPassword);
            ExtentManager.test().info("Entered Password : "+vPassword);
        }catch (Exception e){
            ExceptionHandling.handleCriticalException("Failed to enter Password",e);
        }
        return true;
    }

    public boolean enterConfirmPassword(String vConfirmPassword){
        try{
            txtconfirmPassword.sendKeys(vConfirmPassword);
            ExtentManager.test().info("Entered Confirm Password : "+vConfirmPassword);
        }catch (Exception e){
            ExceptionHandling.handleCriticalException("Failed to enter Confirm Password",e);
        }
        return true;
    }

    public boolean saveUser(){
        try{
            btnSubmit.click();
            ExtentManager.test().info("User Saved");
        }catch (Exception e){
            ExceptionHandling.handleCriticalException("Failed to click Save",e);
        }
        return true;
    }
}
