package tests;

import base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginPage;
//@Listeners(utils.TestListener.class)
@Slf4j
public class LoginTest extends BaseTest {
    @Test
    public void validLoginTest() {
       LoginPage login = new LoginPage(driver);
       login.enterUserName("Admin");
       login.enterPassword("admin123");
       login.clickLogin();
       HomePage dashboard = new HomePage(driver);
       Assert.assertTrue(dashboard.dashboardIsDisplayed());
    }
}
