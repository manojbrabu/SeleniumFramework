package tests;

import BusinessFlow.LoginOrange;
import BusinessFlow.LogoutOrange;
import base.BaseTest;
import org.testng.annotations.Test;
import utils.ConfigReader;

@Test(groups = {"CR123", "regression"})
public class LoginTest extends BaseTest {

    @Test(description = "TC01_Verify the valid login")
    public void validLoginTest() {
        LoginOrange login = new LoginOrange();
        String userName = ConfigReader.getSetting("ORANGE_APP_USERNAME", "username", "");
        String password = ConfigReader.getSetting("ORANGE_APP_PASSWORD", "password", "");
        login.loginOrangeApplication(userName, password);


        LogoutOrange logout = new LogoutOrange();
        logout.logoutOrangeApplication();
    }
}
