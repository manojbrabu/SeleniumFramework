package tests;

import BusinessFlow.LoginOrange;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.AdminPage;
import page.HomePage;
import utils.ConfigReader;
import utils.DriverManager;

public class AddLocationTest extends BaseTest {

    @Test(description = "TC04_Verify User is able to Add location")
    public void addLocations(){

        //Login to Application
        LoginOrange login = new LoginOrange();
        login.loginOrangeApplication(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        HomePage dashboard = new HomePage(DriverManager.getDriver());
        dashboard.navigateToAdmin();
        AdminPage admin = new AdminPage(DriverManager.getDriver());
        Assert.assertTrue(admin.isAdminPageDisplayed());
        Assert.assertTrue(dashboard.navigateToMenu("Organization","Locations"));
    }
}
