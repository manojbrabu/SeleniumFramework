package BusinessFlow;

import org.testng.Assert;
import page.HomePage;
import page.LoginPage;
import utils.ConfigReader;
import utils.DriverManager;

public class LoginOrange {

    public void loginOrangeApplication(String username, String Password){
        LoginPage login = new LoginPage(DriverManager.getDriver());
        login.enterUserName(username);
        login.enterPassword(Password);
        login.clickLogin();
        HomePage dashboard = new HomePage(DriverManager.getDriver());
        Assert.assertTrue(dashboard.isDashboardDisplayed());
    }
}
