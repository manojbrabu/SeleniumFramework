package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import utils.ConfigReader;

import java.time.Duration;

public class BaseTest {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    @BeforeTest(alwaysRun = true)
    public void setUp() {

        if (driver.get() == null) {

            String browser = ConfigReader.getProperty("browser");

            if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
            } else {
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
            }

            getDriver().manage().window().maximize();
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            getDriver().get(ConfigReader.getProperty("url"));
        }
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {

        if (driver.get() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}