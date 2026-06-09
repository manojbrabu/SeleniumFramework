package page;

import base.BasePage;
import Enum.Execution;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ElementUtil;

public class HomePage extends BasePage {

    @FindBy(xpath = "//h6[text()='Dashboard']")
    WebElement dashboard;

    @FindBy(xpath = "//span[text()='Admin']")
    WebElement admin;

    private final String xPathTopMenu = "//nav[@role='navigation' and @aria-label='Topbar Menu']//li/*[normalize-space(text())='%s']";
    private final String xPathSubmenu = "//ul[@class='oxd-dropdown-menu']/li/a[text()='%s']";

    @FindBy(xpath = "//span[text()='Leave']")
    WebElement leave;

    @FindBy(xpath = "//p[@class='oxd-userdropdown-name']")
    WebElement userdropdown;

    @FindBy(xpath = "//a[@href='/web/index.php/auth/logout']")
    WebElement logout;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isDashboardDisplayed() {
        return ElementUtil.isDisplayed(dashboard, "Dashboard", Execution.STOP);
    }

    public boolean navigateToAdmin() {
        return ElementUtil.click(admin, "Admin Tab", Execution.STOP);
    }

    public boolean navigateToLeave() {
        return ElementUtil.click(leave, "Leave Tab", Execution.STOP);
    }

    public boolean clickTopMenu(String vMenu) {
        String dynamicxPath = String.format(xPathTopMenu, vMenu);
        WebElement element = ElementUtil.findElement(driver, By.xpath(dynamicxPath), vMenu + " Top Menu");
        if (element == null) {
            return false;
        }
        return ElementUtil.click(element, vMenu + " Top Menu", Execution.STOP);
    }

    public boolean navigateToMenu(String vTopMenu, String vSubMenu) {
        boolean topMenu = clickTopMenu(vTopMenu);
        if (!topMenu) {
            return false;
        }
        if (vSubMenu.equals("")) {
            return true;
        }

        String dynamicxPath = String.format(xPathSubmenu, vSubMenu);
        WebElement element = ElementUtil.findElement(driver, By.xpath(dynamicxPath), vSubMenu);
        if (element == null) {
            return false;
        }
        return ElementUtil.click(element, vSubMenu + " Sub Menu", Execution.STOP);
    }

    public boolean clickUserDropdown() {
        return ElementUtil.click(userdropdown, "User dropdown", Execution.STOP);
    }

    public boolean clickLogout() {
        return ElementUtil.click(logout, "Logout Button", Execution.STOP);
    }
}
