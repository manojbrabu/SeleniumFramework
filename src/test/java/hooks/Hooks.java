package hooks;

import base.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import utils.CucumberScenarioContext;
import utils.DriverManager;
import utils.ExtentManager;
import utils.ScreenshotUtils;

import java.nio.file.Files;
import java.nio.file.Path;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) throws Exception {
        BaseTest.startDriver();
        CucumberScenarioContext.setScenario(scenario);
        ExtentTest test =
                ExtentManager.getInstance()
                        .createTest(scenario.getName());
        ExtentManager.setTest(test);
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                WebDriver driver = DriverManager.getDriver();
                if (driver != null) {
                    String screenshotPath = ScreenshotUtils.capture(
                            driver,
                            scenario.getName().replaceAll("\\s+", "_")
                    );
                    scenario.attach(
                            Files.readAllBytes(Path.of(screenshotPath)),
                            "image/png",
                            "Failure screenshot"
                    );
                }
            }
        } catch (Exception ignored) {
            // Screenshot is best-effort on failure
        } finally {
            if (ExtentManager.test() != null) {

                if (scenario.isFailed()) {
                    ExtentManager.test().fail("Scenario Failed");
                } else {
                    ExtentManager.test().pass("Scenario Passed");
                }
            }

            ExtentManager.removeTest();
            BaseTest.stopDriver();
            CucumberScenarioContext.removeScenario();
        }
    }
}
