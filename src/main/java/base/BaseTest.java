package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import utils.ConfigReader;
import utils.DriverManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

       WebDriver localDriver;

    @Parameters({"browser", "url"})
    @BeforeTest(alwaysRun = true)
    public void setUp(String browser, String url) throws MalformedURLException {

        String execution = ConfigReader.getProperty("execution");
        if(url.isEmpty()) url = ConfigReader.getProperty("url");

        if (execution.equalsIgnoreCase("remote")) {

            URL gridUrl = new URL("http://172.26.218.249:4444");

            if (browser.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.addArguments("--window-size=1920, 1080");
                localDriver = new RemoteWebDriver(gridUrl, options);
            } else if(browser.equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless");
                options.addArguments("--window-size=1920, 1080");
                localDriver = new RemoteWebDriver(gridUrl, options);
            }

        } else {

            if (browser.equalsIgnoreCase("chrome")) {
                //WebDriverManager.chromedriver().setup(); - this is not required after selenium 4.6
                ChromeOptions options = new ChromeOptions();
                //options.addArguments("--headless");
                //options.addArguments("--window-size=1920, 1080");
                localDriver = new ChromeDriver(options);
            } else if(browser.equalsIgnoreCase("firefox")) {
                localDriver = new FirefoxDriver();
            }
        }

        DriverManager.setDriver(localDriver);
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        DriverManager.getDriver().get(url);
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }
}