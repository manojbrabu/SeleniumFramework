package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.Admin.AdminPage;
import page.HomePage;

public class AddLocationTest extends BaseTest {

    @Test(description = "TC04_Verify User is able to Add location")
    public void addLocations(){
        HomePage dashboard = new HomePage(BaseTest.getDriver());
        dashboard.navigateToAdmin();
        AdminPage admin = new AdminPage(BaseTest.getDriver());
        Assert.assertTrue(admin.adminpageIsDisplayed());
        admin.navigateMenu("Organization","Locations");
    }
}
