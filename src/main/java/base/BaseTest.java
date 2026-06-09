package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.ConfigReader;
import utils.DriverManager;
import utils.TestListener;

import java.net.MalformedURLException;
import java.net.URL;

@Listeners(TestListener.class)
public class BaseTest {

    @Parameters({"browser", "url"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("") String browser, @Optional("") String url) throws MalformedURLException {
        startDriver(browser, url);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        stopDriver();
    }

    public static void startDriver() throws MalformedURLException {
        startDriver(null, null);
    }

    public static void startDriver(String browser, String url) throws MalformedURLException {
        String execution = ConfigReader.getSetting("EXECUTION", "execution", "local");
        String resolvedBrowser = firstNonBlank(browser, ConfigReader.getSetting("BROWSER", "browser", "chrome"));
        String resolvedUrl = firstNonBlank(url, ConfigReader.getSetting("APP_URL", "url", ""));

        if (isBlank(resolvedUrl)) {
            throw new IllegalArgumentException(
                    "Application URL is required. Set TestNG parameter 'url', APP_URL env var, or config property 'url'."
            );
        }

        WebDriver driver;
        if (execution.equalsIgnoreCase("remote")) {
            String gridUrlValue = ConfigReader.getSetting("SELENIUM_GRID_URL", "gridUrl", "");
            if (isBlank(gridUrlValue)) {
                throw new IllegalArgumentException(
                        "Selenium Grid URL is required for remote execution. Set SELENIUM_GRID_URL env var or config property 'gridUrl'."
                );
            }
            driver = createRemoteDriver(resolvedBrowser, new URL(gridUrlValue));
        } else {
            driver = createLocalDriver(resolvedBrowser);
        }

        DriverManager.setDriver(driver);
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().get(resolvedUrl);
    }

    public static void stopDriver() {
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }

    private static WebDriver createLocalDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver(new ChromeOptions());
        }
        if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }
        throw new IllegalArgumentException("Unsupported browser: " + browser);
    }

    private static WebDriver createRemoteDriver(String browser, URL gridUrl) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
            return new RemoteWebDriver(gridUrl, options);
        }
        if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
            return new RemoteWebDriver(gridUrl, options);
        }
        throw new IllegalArgumentException("Unsupported browser: " + browser);
    }

    private static String firstNonBlank(String firstValue, String fallbackValue) {
        return isBlank(firstValue) ? fallbackValue : firstValue;
    }

    private static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
