package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {

    public static String capture(WebDriver driver, String testName) {

        String folderPath = System.getProperty("user.dir") + "/Extend_20Report/screenshots/";
        String fileName = testName + "_" + System.currentTimeMillis() + ".png";
        String fullPath = folderPath + fileName;

        try {
            File folder = new File(folderPath);
            if (!folder.exists()) folder.mkdirs();

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(fullPath));

            // attach to extent
            if (ExtentManager.test() != null) {
                ExtentManager.test().addScreenCaptureFromPath(fullPath);
                ExtentManager.test().info("Screenshot: " + fullPath);
            }

        } catch (IOException e) {
            if (ExtentManager.test() != null) {
                ExtentManager.test().warning("Screenshot failed: " + e.getMessage());
            }
        }

        return fullPath;
    }
}