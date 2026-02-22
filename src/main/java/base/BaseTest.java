package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;
import utils.ConfigReader;
import utils.ExtentManager;

import java.lang.reflect.Method;
import java.time.Duration;

public class BaseTest {
    public static WebDriver driver;
    @BeforeSuite
    public void setUp() {
        System.out.println("Started");
        String browser = ConfigReader.getProperty("browser");
        System.out.println("Browser value: " + browser);

        if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();

            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");   // IMPORTANT
            options.addArguments("--disable-gpu"); // Optional for stability
            options.addArguments("--window-size=1920,1080"); // Optional

            driver = new FirefoxDriver(options);
        }
        else if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            //options.addArguments("--headless=new"); // works better with Chrome 111+
           // options.addArguments("--disable-gpu");
           // options.addArguments("--window-size=1920,1080");

            driver = new ChromeDriver(options);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfigReader.getProperty("url"));
    }
    @AfterSuite
    public void tearDown() {
        driver.quit();
    }

}
