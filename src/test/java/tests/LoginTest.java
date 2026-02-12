package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginPage;
//@Listeners(utils.TestListener.class)
public class LoginTest extends BaseTest {
    @Test
    public void validLoginTest() {
       LoginPage login = new LoginPage(driver);
       login.login("Admin", "admin123");
       HomePage dashboard = new HomePage(driver);
       Assert.assertTrue(dashboard.dashboardIsDisplayed());
    }
}
