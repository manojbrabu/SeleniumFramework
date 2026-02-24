package tests;

import base.BaseTest;
import org.testng.Assert;
import page.Admin.AdminPage;
public class AddOrganization extends BaseTest {

    public void addLocations(){
        AdminPage admin = new AdminPage(BaseTest.getDriver());
        Assert.assertTrue(admin.adminpageIsDisplayed());
        admin.navigateMenu("Organization","Locations");
    }
}
