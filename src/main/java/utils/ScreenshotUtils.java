package utils;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ScreenshotUtils {

    public static String capture(WebDriver driver, String testName) {
        String folderPath = System.getProperty("user.dir") + "/Report/screenshots/";
        String fileName = testName + "_" + System.currentTimeMillis() + ".png";
        String fullPath = folderPath + fileName;

        try {
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(fullPath));
        } catch (IOException e) {
            if (ExtentManager.test() != null) {
                ExtentManager.test().warning("Screenshot failed: " + e.getMessage());
            }
        }

        return fullPath;
    }

    public static void captureScreenshotInCucmberReport(WebDriver driver, Scenario scenario, String screenshotName) {
        String screenshotPath = ScreenshotUtils.capture(
                driver,
                scenario.getName().replaceAll("\\s+", "_")
        );
        try{
            scenario.attach(
                    Files.readAllBytes(Path.of(screenshotPath)),
                    "image/png",
                    screenshotName
            );
        }
        catch (IOException exception){
            Assert.fail("Failed to attach screenprint file" + screenshotPath);
        }

    }
}
