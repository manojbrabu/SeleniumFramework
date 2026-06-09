package tests;

import BusinessFlow.LoginOrange;
import base.BasePage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import page.AddUser;
import page.AdminPage;
import page.HomePage;
import utils.ConfigReader;
import utils.DriverManager;

public class AddUserPage extends BaseTest {

    @Parameters("userName")
    @Test(description = "TC02_Verify user is able to add new users")
    public void addUserTest(String userName){

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

        //Click Add button
        adminPage.clickAdd();

        AddUser adduser = new AddUser(DriverManager.getDriver());

        adduser.selectUserRole("Admin");
        adduser.enterEmployeeName("Orange Test");
        adduser.selectStatus("Enabled");
        adduser.enterUsername(userName);
        adduser.enterPassword("Manoj1234");
        adduser.enterConfirmPassword("Manoj1234");
        adduser.saveUser();
        Assert.assertTrue(adminPage.verifySuccessMessage());
    }

}
