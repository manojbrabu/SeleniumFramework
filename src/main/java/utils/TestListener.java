package utils;

import com.aventstack.extentreports.ExtentTest;
import org.testng.*;


public class TestListener implements ITestListener, ISuiteListener {

    @Override
    public void onStart(ISuite suite){
       ExtentManager.initReport(suite.getName());
    }
    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentManager.getInstance().createTest(result.getMethod().getDescription());
        ExtentManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.test().pass("Test Passed");
        ExtentManager.removeTest();
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
        ExtentManager.getInstance().flush();
        ExtentManager.unLoad();
    }
}