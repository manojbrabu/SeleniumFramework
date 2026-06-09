package utils;

import com.aventstack.extentreports.ExtentTest;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener, ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        ExtentManager.initReport(suite.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getDescription();
        if (testName == null || testName.isBlank()) {
            testName = result.getMethod().getMethodName();
        }

        ExtentTest test = ExtentManager.getInstance().createTest(testName);
        ExtentManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (ExtentManager.test() != null) {
            ExtentManager.test().pass("Test Passed");
            ExtentManager.removeTest();
        };
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (ExtentManager.test() != null) {
            ExtentManager.test().fail("Test Failed");
            ExtentManager.removeTest();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (ExtentManager.test() != null) {
            ExtentManager.test().skip("Test Skipped");
            ExtentManager.removeTest();
        }
    }

    @Override
    public void onFinish(ISuite suite) {
        ExtentManager.unload();
    }
}
