package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.Admin.AddUser;
import page.Admin.AdminPage;
import page.HomePage;

public class AddUserTest extends BaseTest {



    @Test
    public void fnAddUserTest(){

       //Navigate to Admin Page
        HomePage homepage = new HomePage(driver);
        homepage.navigateToAdmin();
        AdminPage admin = new AdminPage(driver);
        Assert.assertTrue(admin.adminpageIsDisplayed());

        //Click Add button
        admin.clickAdd();
        AddUser adduser = new AddUser(driver);
        adduser.selectUserRole("Admin");
        adduser.enterEmployeeName("Orange Test");
        adduser.selectStatus("Enabled");
        adduser.enterUsername("Manoj1234");
        adduser.enterPassword("Manoj1234");
        adduser.enterConfirmPassword("Manoj1234");
        adduser.saveUser();
    }

}
