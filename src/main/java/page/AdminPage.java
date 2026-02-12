package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

import java.util.List;

public class AdminPage {
    WebDriver driver;
    @FindBy(xpath ="//h6[text()='Admin']")
    WebElement admin;
    @FindBy(xpath ="//div[@class='orangehrm-header-container']/button")
    WebElement add;
   // @FindBy(xpath = "//nav[@aria-label='Topbar Menu']/ul/li")
   // List<WebElement> topMenu = driver.findElements(By.xpath("/nav[@aria-label='Topbar Menu']/ul/li"));
   // @FindBy(xpath = "//ul[@class='oxd-dropdown-menu']/li")
    //List<WebElement> topMenudropdown = driver.findElements(By.xpath("//ul[@class='oxd-dropdown-menu']/li"));



    public AdminPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public boolean adminpageIsDisplayed(){
        return admin.isDisplayed();
    }
    public void clickAdd(){
        admin.click();
    }
    public void navigateMenu(String menu, String submenu){
        List<WebElement> topMenu = driver.findElements(By.xpath("/nav[@aria-label='Topbar Menu']/ul/li"));
        for(WebElement menulist : topMenu){
            if(menulist.getText().trim().equals(menu)){
                menulist.click();
                List<WebElement> topMenudropdown = driver.findElements(By.xpath("//ul[@class='oxd-dropdown-menu']/li"));
                for(WebElement subMenuList : topMenudropdown){
                        if(subMenuList.getText().trim().equals(submenu)){
                        WaitUtils.waitForElementClickable(driver, subMenuList);
                        subMenuList.click();
                    }
                }
            }
        }
    }

}
