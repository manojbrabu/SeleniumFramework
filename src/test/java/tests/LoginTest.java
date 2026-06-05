package tests;

import BusinessFlow.LoginOrange;
import BusinessFlow.LogoutOrange;
import base.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginPage;
import utils.ConfigReader;
import utils.DriverManager;


@Test(groups = {"CR123","regression"})
public class LoginTest extends BaseTest {
    @Test(description = "TC01_Verify the valid login"/*, dataProvider = "apiData", dataProviderClass = utils.TestDataUtil.class*/)
    public void validLoginTest() {
        LoginOrange login = new LoginOrange();
        String userName = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");
        login.loginOrangeApplication(userName,password);
        LogoutOrange logout = new LogoutOrange();
        logout.logoutOrangeApplication();
    }
}
