package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;
import utils.ConfigReader;

import java.time.Duration;

public class BaseTest {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    @Parameters("browser")
    @BeforeTest(alwaysRun = true)
    public void setUp(String browser) {
        if (driver.get() == null) {
            if (browser.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
            } else if (browser.equalsIgnoreCase("firefox")) {
                //FirefoxOptions options = new FirefoxOptions();
                //options.addArguments("headless");
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());

            }

            getDriver().manage().window().maximize();
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            getDriver().get(ConfigReader.getProperty("url"));
        }
    }
    //After Test
    @AfterTest(alwaysRun = true)
    public void tearDown() {
        if (driver.get() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}

