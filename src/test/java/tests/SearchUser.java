package tests;
import BusinessFlow.LoginOrange;
import base.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.AdminPage;
import page.HomePage;
import utils.ConfigReader;
import utils.DriverManager;

import java.io.IOException;

public class SearchUser extends BaseTest{
    private static final Logger log = LoggerFactory.getLogger(SearchUser.class);

    @Parameters("username")
    @Test(description = "TC03_Verify user is able to search Newly added user")
public void searchUserTest(String userName) throws InterruptedException, IOException {

        //Login to Application
        LoginOrange login = new LoginOrange();
        login.loginOrangeApplication(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        //Navigate to Admin Page
        HomePage homepage = new HomePage(DriverManager.getDriver());
        homepage.navigateToAdmin();

        //Verify Admin page is displayed
        AdminPage adminPage = new AdminPage(DriverManager.getDriver());
       adminPage.isAdminPageDisplayed();

        //Naigate to Usersmanagement>Users
        homepage.navigateToMenu("User Management","Users");

        SoftAssert softAssert = new SoftAssert();

        adminPage.enterUsername(userName);
        adminPage.selectRole("Admin");

       adminPage.enterEmployeeName("Orange Test");
        adminPage.selectStatus("Enabled");

      adminPage.clickSearch();
        Assert.assertTrue(adminPage.verifyUsernameDisplayed(userName));
    }
}
