package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    public static void implicitlyWait(){
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

    }

    public static void visibilityOf(WebElement element) {

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),TIMEOUT);

        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void elementToBeClickable(WebElement element) {

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),TIMEOUT);

        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForElementTextPresent(WebElement element, String text) {

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),TIMEOUT);

        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public static WebElement waitForPresence(By element) {

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),TIMEOUT);
        return wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }
    public static void alertPresent() {

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),TIMEOUT);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static void titleContains(String title) {

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),TIMEOUT);
        wait.until(ExpectedConditions.titleContains(title));
    }

    public static void titleIs(String title) {

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),TIMEOUT);
        wait.until(ExpectedConditions.titleIs(title));
    }
    public static void fluentWait(WebElement element){
        Wait<WebDriver> wait = new FluentWait<>(DriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class);
    }
}