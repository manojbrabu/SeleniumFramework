package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtil;
import Enum.Execution;

public class HomePage {

    WebDriver driver;

    @FindBy(xpath ="//h6[text()='Dashboard']")
    WebElement dashboard;

    @FindBy(xpath ="//span[text()='Admin']")
    WebElement admin;

    String xPathTopMenu = "//nav[@role='navigation' and @aria-label='Topbar Menu']//li/*[normalize-space(text())='***']";
    String xPathSubmenu = "//ul[@class='oxd-dropdown-menu']/li/a[text()='***']";

    @FindBy(xpath ="//span[text()='Leave']")
    WebElement leave;

    @FindBy(xpath = "//p[@class='oxd-userdropdown-name']")
    WebElement userdropdown;

    @FindBy(xpath = "//a[@href='/web/index.php/auth/logout']")
    WebElement logout;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isDashboardDisplayed(){
       return ElementUtil.isDisplayed(dashboard, "Dashboard", Execution.STOP);
    }

    public boolean navigateToAdmin(){
       return ElementUtil.click(admin, "Admin Tab", Execution.STOP);
    }

    public boolean navigateToLeave(){
        return ElementUtil.click(leave, "Leave Tab", Execution.STOP);
    }

   public boolean clickTopMenu(String vMenu){
        By byElement =By.xpath(xPathTopMenu.replace("***",vMenu));
        WebElement element = ElementUtil.findElement(driver, byElement,vMenu +" Top Menu");
        if(element==null) return false;
        return ElementUtil.click(element,vMenu+" Top Menu");
   }

    public boolean navigateToMenu(String vTopMenu, String vSubMenu){
        Boolean topMenu = clickTopMenu(vTopMenu);
        if(vSubMenu.equals("")) return topMenu;

        By byElement =By.xpath(xPathSubmenu.replace("***",vSubMenu));
        WebElement element = ElementUtil.findElement(driver, byElement,vSubMenu +" Top Menu");
        if(element==null) return false;
        return ElementUtil.click(element,vSubMenu+ "Sub Menu", Execution.STOP);
    }
    public boolean clickUserDropdown(){
        return ElementUtil.click(userdropdown,"Userdrodown",Execution.STOP);
    }

    public boolean clickLogout(){
        return ElementUtil.click(logout, "Logout Button");
    }
}