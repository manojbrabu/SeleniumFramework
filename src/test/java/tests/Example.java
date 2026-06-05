package tests;

import io.restassured.RestAssured;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.Examples;
import utils.DriverManager;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class Example {
    Wait<WebDriver> wait;
    @Test(description="Window Operation")
    public void windowExamples1(){
        Examples exe = new Examples(DriverManager.getDriver());
        exe.openNewTab();
        exe.clickButton1();
        exe.openNewWindow();
        exe.clickButton2();
        exe.openAnotherNewWindow();
        exe.clickButton3();
    }

    @Test(description="Window Operation", groups ="Switch Window")
    public void windowExamples2(){
        WebDriver driver = DriverManager.getDriver();
        driver.findElement(By.linkText("Open New Tabbed Windows")).click();
        driver.findElement(By.xpath("//div[@id='Tabbed']//button")).click();
        String parentWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();

        for(String window:windows){
            driver.switchTo().window(window);
            if (driver.getTitle().equals("Selenium")){
                break;
            }
        }
        if (driver.findElement(By.cssSelector("svg[data-name='Layer 1']")).isDisplayed()){
            System.out.println("Pass");
        } else{
            System.out.println("Failed");;;
        }
    }
    @Test(description = "Actions Class", groups="Actions")
    public void mouseActions() throws InterruptedException {
        WebDriver driver = DriverManager.getDriver();
        WebElement element = driver.findElement(By.cssSelector("#doubleBtn"));

        Actions action = new Actions(driver);
       /* action.doubleClick(element).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("#doubleStatus")).getText().trim(),"Double Click Detected");
*/
        WebElement scroll =  driver.findElement(By.cssSelector("#handle_max"));
        WebElement value = driver.findElement(By.cssSelector("#sliderValueText"));
        action.clickAndHold(scroll).perform();
        int expVallue = 1116;
        int i = -1;
         while(Integer.parseInt(value.getText())<=expVallue) {
             action.moveByOffset(i, 0).perform();

             System.out.println(value.getText());
             if (Integer.parseInt(value.getText())==250) i = 1;
             if (Integer.parseInt(value.getText())==expVallue) {
                 System.out.println("Success");
                 action.release().perform();
                 break;
             }
         }
        Thread.sleep(5000);
   }

   @Test(description = "Fluent Wait", groups = "FluentWait")
   public void waitExample() throws IOException {
        By btn1 = By.xpath("//button[@id='btn1']");
        By btn2 = By.xpath("//button[@id='btn2']");
           By txt1 = By.cssSelector("#txt1");
           By txt2 = By.cssSelector("#txt2");
        WebDriver driver = DriverManager.getDriver();
        Duration TIMEOUT = Duration.ofSeconds(20);
        wait = new FluentWait<>(driver)
                .withTimeout(TIMEOUT)
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(TimeoutException.class);

        Actions actions = new Actions(driver);

       WebElement button1 =  wait.until(ExpectedConditions.elementToBeClickable(btn1));
       WebElement button2 = wait.until(ExpectedConditions.elementToBeClickable(btn2));

       actions.moveToElement(button1).click().perform();
       WebElement txtbox1 = wait.until(ExpectedConditions.visibilityOfElementLocated(txt1));
       System.out.println(txtbox1.isDisplayed() ? "Displayed" : "Not displayed");
       //Element Screenshot
       File file1 = txtbox1.getScreenshotAs(OutputType.FILE);

       actions.moveToElement(button2).click().perform();
       WebElement txtbox2 = wait.until(ExpectedConditions.visibilityOfElementLocated(txt2));
       System.out.println(txtbox2.isDisplayed() ? "Displayed" : "Not displayed");

       File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
       FileHandler.copy(file1,new File("./Report/TextBox1.png"));
       FileUtils.copyFile(file, new File("./Report/waitExample.png"));
   }

   @Test(description = "Scroll", groups="Scroll")
    public void scrollExample() throws InterruptedException {
       Duration TIMEOUT = Duration.ofSeconds(20);
       By iPhoneM = By.xpath("//div[contains(@class,'styled-hTqIrr wf-1iskvf1')][11]");
        WebDriver driver = DriverManager.getDriver();
       wait = new FluentWait<>(driver)
               .withTimeout(TIMEOUT)
               .pollingEvery(Duration.ofSeconds(2))
               .ignoring(TimeoutException.class);
       WebElement iPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(iPhoneM));
       JavascriptExecutor js = (JavascriptExecutor) driver;

       js.executeScript("arguments[0].scrollIntoView({behaviour:'smooth', block:'center'})",iPhone);
       Thread.sleep(3000);
       //Aligns the top of the element with the top of the visible screen area.|Equivalent to: scrollIntoView({block: 'start'})
       js.executeScript("arguments[0].scrollIntoView(true)", iPhone);
       Thread.sleep(3000);
       //Aligns the bottom of the element with the bottom of the visible screen area.|Equivalent to: scrollIntoView({block: 'end'})
       js.executeScript("arguments[0].scrollIntoView(false)", iPhone);
    }

    @Test(description = "List comparison", groups="listCompare")
    public static void fnCompareList(){
        List<String> value1 = new LinkedList<>();
        value1.add("Manoj");
        value1.add("Uma");
        List<String> value2 = new LinkedList<>();
        value2.add("Manoj");
        value2.add("uma");

        Assert.assertEquals(value1, value2);
    }
    //Data Table concepts
    public static void fnDataTable(){

                List<List<String>> table = new ArrayList<>();

                // Row 1
                table.add(Arrays.asList("ID", "Name", "Age"));

                // Row 2
                table.add(Arrays.asList("1", "Manoj", "25"));

                // Row 3
                table.add(Arrays.asList("2", "Ravi", "30"));

                // Retrieve data
                for (List<String> row : table) {
                    for (String col : row) {
                        System.out.print(col + " ");
                    }
                    System.out.println();
                }
            }
}
