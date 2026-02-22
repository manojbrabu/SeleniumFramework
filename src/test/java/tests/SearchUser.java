package tests;
import base.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.AdminPage;
import page.HomePage;

import java.io.IOException;

public class SearchUser extends BaseTest{
    private static final Logger log = LoggerFactory.getLogger(SearchUser.class);

    @Test
public void searchUser() throws InterruptedException, IOException {
    //Navigate to Admin Page
    HomePage homepage = new HomePage(driver);
    homepage.navigateToAdmin();


    //Navigate to User Management>>User
    AdminPage adminPage = new AdminPage(driver);
    Assert.assertTrue(adminPage.adminpageIsDisplayed());


    //Search User
    //adminPage.navigateMenu("User Management", "Users");
    adminPage.enterUserName("Manoj1234");
    adminPage.selectRole("Admin");
    //Thread.sleep(2000);
    adminPage.enterEmployeeName("Manoj");
    adminPage.selectStatus("Enabled");
    //Thread.sleep(2000);
    adminPage.clickSearch();
    System.out.println("Done");
}
}
