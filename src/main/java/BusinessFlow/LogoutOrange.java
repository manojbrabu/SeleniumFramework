package BusinessFlow;

import page.HomePage;
import utils.DriverManager;

public class LogoutOrange {
    public void logoutOrangeApplication(){
        HomePage home = new HomePage(DriverManager.getDriver());
        home.clickUserDropdown();
        home.clickLogout();
    }
}
