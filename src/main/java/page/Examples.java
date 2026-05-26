package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Examples {

    Duration TIMEOUT = Duration.ofSeconds(10);
    WebDriver driver = null;
    WebDriverWait wait = null;
    //Switch Tab/Window Examples
    private By newTab = By.linkText("Open New Tabbed Windows");
    private By newWindow = By.linkText("Open New Seperate Windows");
    private By newMultipleWindow = By.linkText("Open Seperate Multiple Windows");
    private By button1 = By.xpath("//div[@id='Tabbed']//button");
    private By button2 = By.cssSelector("button[onClick='newwindow()']");
    private By button3 = By.cssSelector("button[onClick='multiwindow()']");

    public Examples(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT);
    }

    public void openNewTab() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(newTab));
        element.click();
    }

    public void openNewWindow() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(newWindow));
        element.click();
    }

    public void openAnotherNewWindow() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(newMultipleWindow));
        element.click();
    }

    public void clickButton1() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(button1));
        element.click();
    }
    public void clickButton2() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(button2));
        element.click();
    }
    public void clickButton3() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(button3));
        element.click();
    }

}
