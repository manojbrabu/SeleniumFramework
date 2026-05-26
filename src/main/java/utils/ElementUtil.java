package utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.Reporter;
import Enum.Execution;

public class ElementUtil {

    public static boolean click(WebElement element, String elementName) {
        try {
            WaitUtils.elementToBeClickable(element);
            element.click();
            ExtentManager.test().pass("Clicked on element: " + elementName);
            return true;
        } catch (Exception e) {
            ExtentManager.test().fail("Failed to click element: "
                    + elementName + " - " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromPath(ScreenshotUtils.capture(DriverManager.getDriver(),
                            Reporter.getCurrentTestResult().getTestName())).build());
            return false;
        }
    }
    public static boolean click(WebElement element, String elementName, Execution execution) {
        if (click(element, elementName)) {
            return true;
        }
        else if (execution == Execution.STOP){
           Assert.fail("Execution stopped due to failure in '"+ elementName + "'");
        }
        return false;
    }

    public static boolean sendKeys(WebElement element, String value, String elementName) {
        try {
            WaitUtils.visibilityOf(element);
            element.clear();
            element.sendKeys(value);
            ExtentManager.test().pass("Entered value '" + value + "' into " + elementName);
            return true;
        } catch (Exception e) {
            ExtentManager.test().fail("Failed to enter value '" + value
                    + "' into " + elementName + " - " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromPath(ScreenshotUtils.capture(DriverManager.getDriver(),
                            Reporter.getCurrentTestResult().getTestName())).build());
            return false;
        }
    }
    public static boolean sendKeys(WebElement element, String value, String elementName, Execution execution) {
        if (sendKeys(element, value, elementName)) {
            return true;
        }
        else if (execution == Execution.STOP){
            Assert.fail("Execution stopped due to failure in '"+ elementName + "'");
        }
        return false;
    }

    public static boolean isDisplayed(WebElement element, String elementName) {
        try {
            WaitUtils.visibilityOf(element);
            element.isDisplayed();
            ExtentManager.test().pass("Element '" + elementName + "' is displayed ");
            return true;
        } catch (Exception e) {
            ExtentManager.test().fail("Element '" + elementName + "' is NOT displayed "+ " - " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromPath(ScreenshotUtils.capture(DriverManager.getDriver(),
                            Reporter.getCurrentTestResult().getTestName())).build());
            return false;
        }
    }

    public static boolean isDisplayed(WebElement element, String expText, Execution execution) {
        if (isDisplayed(element, expText)) {
            return true;
        }
        else if (execution == Execution.STOP){
            Assert.fail("Execution stopped due to failure in '"+ expText + "'");
        }
        return false;
    }
    public static WebElement findElement(WebDriver driver, By byElement, String elementName){

        try{
            WebElement element = driver.findElement(byElement);
            WaitUtils.visibilityOf(element);
            return element;
        }catch (Exception e){
            ExtentManager.test().fail("Element '" + elementName + "' is NOT displayed "+ " - " + e.getMessage(),
            MediaEntityBuilder.createScreenCaptureFromPath(ScreenshotUtils.capture(DriverManager.getDriver(),
                    Reporter.getCurrentTestResult().getTestName())).build());
            return null;
        }
    }
    public static boolean verifyText(WebElement element, String expText){
        try{
            WaitUtils.waitForElementTextPresent(element, expText);
         String accText = element.getText().trim();
         if(accText.isEmpty()) return false;
         Boolean result = expText.equals(accText);
         if(result){
             ExtentManager.test().pass("Expected Text '"+expText+"' is matched with Actual text '"+accText+"'");
         }
         else{
             ExtentManager.test().pass("Expected Text '"+expText+"' is matched with Actual text '"+accText+"'");
         }
         return result;
        }catch (Exception e){
            ExtentManager.test().fail("Failed to get Text '" + expText +"' "+ e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromPath(ScreenshotUtils.capture(DriverManager.getDriver(),
                    Reporter.getCurrentTestResult().getTestName())).build());
            return false;
        }
    }
    public static boolean verifyText(WebElement element, String expText, Execution execution) {
        if (verifyText(element, expText)) {
            return true;
        }
        else if (execution == Execution.STOP){
            Assert.fail("Execution stopped due to failure in '"+ expText + "'");
        }
        return false;
    }

    public static void scrollToElement(WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    public static String scrollToElementAndTakeSnap(WebElement element){

        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
        js.executeScript("arguments[0].style.border='3px solid red'", element);

        String path = ScreenshotUtils.capture(
                DriverManager.getDriver(),
                "ScrollToElement"
        );

        js.executeScript("arguments[0].style.border='none'", element);

        return path;
    }
}