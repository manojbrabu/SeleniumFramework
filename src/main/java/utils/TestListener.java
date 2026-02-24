package utils;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static base.BaseTest.driver;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentManager
                .getInstance()
                .createTest(result.getMethod().getDescription());

        ExtentManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.test().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        /*if (ExtentManager.test() != null) {
            ExtentManager.test().fail(result.getThrowable());
           ScreenshotUtils.capture(driver, result.getMethod().getMethodName());
        }`*/
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (ExtentManager.test() != null) {
            ExtentManager.test().skip(result.getThrowable());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getInstance().flush();
    }
}