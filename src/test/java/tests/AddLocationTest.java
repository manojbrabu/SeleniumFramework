package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.Admin.AdminPage;
import page.HomePage;

public class AddLocationTest extends BaseTest {

    @Test
    public void addLocations(){
        HomePage dashboard = new HomePage(driver);
        dashboard.navigateToAdmin();
        AdminPage admin = new AdminPage(driver);
        Assert.assertTrue(admin.adminpageIsDisplayed());
        admin.navigateMenu("Organization","Locations");
    }
}
