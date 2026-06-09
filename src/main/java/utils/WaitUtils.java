package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    public static void visibilityOf(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void visibilityOfElementLocated(By element) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public static void elementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForElementTextPresent(WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), TIMEOUT);
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public static WebElement waitForPresence(By element) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), TIMEOUT);
        return wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public static void alertPresent() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), TIMEOUT);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static void titleContains(String title) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), TIMEOUT);
        wait.until(ExpectedConditions.titleContains(title));
    }

    public static void titleIs(String title) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), TIMEOUT);
        wait.until(ExpectedConditions.titleIs(title));
    }
}
