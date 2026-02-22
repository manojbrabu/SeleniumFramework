package utils;

import base.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.IOException;

public class ExceptionHandling extends ScreenshotUtils {
    private static final Logger log = LoggerFactory.getLogger(ExceptionHandling.class);

    public static void handleException(String message, Exception e) {
        throw new RuntimeException(message, e);
    }
}
