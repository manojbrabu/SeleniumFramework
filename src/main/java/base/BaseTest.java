package base;

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

public class BaseTest {

    WebDriver localDriver;

    @Parameters({"browser", "url"})
    @BeforeTest(alwaysRun = true)
    public void setUp(@Optional("") String browser, @Optional("") String url) throws MalformedURLException {

        String execution = getSetting("EXECUTION", "execution", "local");
        String resolvedBrowser = firstNonBlank(browser, getSetting("BROWSER", "browser", "chrome"));
        String resolvedUrl = firstNonBlank(url, getSetting("APP_URL", "url", ""));

        if (isBlank(resolvedUrl)) {
            throw new IllegalArgumentException(
                    "Application URL is required. Set TestNG parameter 'url', APP_URL env var, or config property 'url'."
            );
        }

        if (execution.equalsIgnoreCase("remote")) {
            String gridUrlValue = getSetting("SELENIUM_GRID_URL", "gridUrl", "");
            if (isBlank(gridUrlValue)) {
                throw new IllegalArgumentException(
                        "Selenium Grid URL is required for remote execution. Set SELENIUM_GRID_URL env var or config property 'gridUrl'."
                );
            }
            localDriver = createRemoteDriver(resolvedBrowser, new URL(gridUrlValue));
        } else {
            localDriver = createLocalDriver(resolvedBrowser);
        }

        DriverManager.setDriver(localDriver);
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().get(resolvedUrl);
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }

    private WebDriver createLocalDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            return new ChromeDriver(options);
        }
        if (browser.equalsIgnoreCase("firefox")) {
            return new FirefoxDriver();
        }
        throw new IllegalArgumentException("Unsupported browser: " + browser);
    }

    private WebDriver createRemoteDriver(String browser, URL gridUrl) {
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

    private String getSetting(String envName, String propertyName, String defaultValue) {
        String envValue = System.getenv(envName);
        if (!isBlank(envValue)) {
            return envValue;
        }

        String propertyValue = ConfigReader.getProperty(propertyName);
        if (!isBlank(propertyValue)) {
            return propertyValue;
        }

        return defaultValue;
    }

    private String firstNonBlank(String firstValue, String fallbackValue) {
        return isBlank(firstValue) ? fallbackValue : firstValue;
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
