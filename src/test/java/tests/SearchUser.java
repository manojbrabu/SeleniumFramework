package tests;

import BusinessFlow.LoginOrange;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import page.AdminPage;
import page.HomePage;
import utils.ConfigReader;
import utils.DriverManager;

public class SearchUser extends BaseTest {

    @Parameters("username")
    @Test(description = "TC03_Verify user is able to search Newly added user")
    public void searchUserTest(String userName) {
        LoginOrange login = new LoginOrange();
        login.loginOrangeApplication(
                ConfigReader.getSetting("ORANGE_APP_USERNAME", "username", ""),
                ConfigReader.getSetting("ORANGE_APP_PASSWORD", "password", "")
        );

        HomePage homepage = new HomePage(DriverManager.getDriver());
        homepage.navigateToAdmin();

        AdminPage adminPage = new AdminPage(DriverManager.getDriver());
        adminPage.isAdminPageDisplayed();

        homepage.navigateToMenu("User Management", "Users");

        adminPage.enterUsername(userName);
        adminPage.selectRole("Admin");
        adminPage.enterEmployeeName("Orange Test");
        adminPage.selectStatus("Enabled");
        adminPage.clickSearch();

        Assert.assertTrue(adminPage.verifyUsernameDisplayed(userName));
    }
}
