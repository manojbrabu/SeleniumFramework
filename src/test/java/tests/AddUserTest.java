package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.Admin.AddUser;
import page.Admin.AdminPage;
import page.HomePage;
import page.LoginPage;
import utils.ConfigReader;

public class AddUserTest extends BaseTest {



    @Test(description = "TC02_Verify user is able to add new users")
    public void addUserTest(){

        //Login to Application
        LoginPage login = new LoginPage(BaseTest.getDriver());
        login.enterUserName(ConfigReader.getProperty("username"));
        login.enterPassword(ConfigReader.getProperty("password"));
        login.clickLogin();
        HomePage dashboard = new HomePage(BaseTest.getDriver());
        Assert.assertTrue(dashboard.dashboardIsDisplayed());

        //Navigate to Admin Page
        HomePage homepage = new HomePage(BaseTest.getDriver());
        homepage.navigateToAdmin();
        AdminPage admin = new AdminPage(BaseTest.getDriver());
        Assert.assertTrue(admin.adminpageIsDisplayed());

        //Click Add button
        admin.clickAdd();
        AddUser adduser = new AddUser(BaseTest.getDriver());
        adduser.selectUserRole("Admin");
        adduser.enterEmployeeName("Orange Test");
        adduser.selectStatus("Enabled");
        adduser.enterUsername("Manoj8345");
        adduser.enterPassword("Manoj1234");
        adduser.enterConfirmPassword("Manoj1234");
        adduser.saveUser();
        admin.fvVerifySuccess();
    }

}
