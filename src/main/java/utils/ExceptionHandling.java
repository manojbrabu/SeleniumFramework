package utils;

import base.BaseTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.testng.Assert;

public class ExceptionHandling {

    // 🔴 Critical → stop test
    public static void handleCriticalException(String message, Exception e) {
        try {
            String path = ScreenshotUtils.capture(
                    BaseTest.driver,
                    "CRITICAL_" + System.currentTimeMillis()
            );

            ExtentManager.test().fail(message + " - "+e, MediaEntityBuilder.createScreenCaptureFromPath(path).build());

        } catch (Exception ex) {
            ExtentManager.test().fail("Screenshot failed: " + ex.getMessage());
        }

        // ❗ stop execution
        Assert.fail(message, e);
    }

    // 🟡 Non-critical → continue test
    public static void handleNonCriticalException(String message, Exception e) {
        try {
            String path = ScreenshotUtils.capture(
                    BaseTest.driver,
                    "NONCRITICAL_" + System.currentTimeMillis()
            );

            ExtentManager.test().fail(message + " - "+e, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        } catch (Exception ex) {
            ExtentManager.test().fail("Screenshot failed: " + ex.getMessage());
        }
        // ❗ DO NOT throw → test continues
    }
}