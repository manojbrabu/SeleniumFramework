package tests;

import base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginPage;
import utils.ConfigReader;

//@Listeners(utils.TestListener.class)
@Slf4j
public class LoginTest extends BaseTest {
    @Test(description = "TC01_Verify the valid login")
    public void validLoginTest() {

       LoginPage login = new LoginPage(BaseTest.getDriver());
       login.enterUserName(ConfigReader.getProperty("username"));
       login.enterPassword(ConfigReader.getProperty("password"));
       login.clickLogin();
       HomePage dashboard = new HomePage(BaseTest.getDriver());
       Assert.assertTrue(dashboard.dashboardIsDisplayed());
    }
}
