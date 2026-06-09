package stepdefinitions;

import BusinessFlow.LoginOrange;
import BusinessFlow.LogoutOrange;
import com.aventstack.extentreports.MediaEntityBuilder;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page.HomePage;
import page.LoginPage;
import utils.*;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class LoginSteps {

    @Given("User is on Login Page")
    public void iAmOnTheOrangeHrmLoginPage() {
        new LoginPage(DriverManager.getDriver());
    }

    @When("I login with valid credentials")
    public void iLoginWithValidCredentials() {
        LoginOrange login = new LoginOrange();
        login.loginOrangeApplication(
                ConfigReader.getSetting("ORANGE_APP_USERNAME", "username", ""),
                ConfigReader.getSetting("ORANGE_APP_PASSWORD", "password", "")
        );
    }

    @When("User enter {string} and {string}")
    public void iLoginWithUsernameAndPassword(String username,String password) {
        LoginOrange login = new LoginOrange();
        login.loginOrangeApplication(username, password);
    }

    @Then("Dashboard page should be displayed")
    public void iShouldLandOnTheDashboard(){
        HomePage homePage = new HomePage(DriverManager.getDriver());
        Assert.assertTrue(homePage.isDashboardDisplayed(), "Dashboard was not displayed after login");
        ScreenshotUtils.captureScreenshotInCucmberReport(DriverManager.getDriver(), CucumberScenarioContext.getScenario(), "Dashboard");
    }

    @When("I logout from the application")
    public void iLogoutFromTheApplication() {
        LogoutOrange logout = new LogoutOrange();
        logout.logoutOrangeApplication();
    }
}
