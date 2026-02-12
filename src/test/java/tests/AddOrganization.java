package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.AdminPage;
public class AddOrganization extends BaseTest {

    public void addLocations(){
        AdminPage admin = new AdminPage(driver);
        Assert.assertTrue(admin.adminpageIsDisplayed());
        admin.navigateMenu("Organization","Locations");
    }
}
