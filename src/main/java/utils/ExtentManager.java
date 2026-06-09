package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    public static void initReport(String suiteName) {
        if (extent != null) {
            return;
        }

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportPath = System.getProperty("user.dir") + "/Report/" + suiteName +".html";// "_" + timestamp + ".html";

        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName(suiteName);
        reporter.config().setDocumentTitle("Extent Report");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    public static ExtentReports getInstance() {
        return extent;
    }

    public static void setTest(ExtentTest test) {
        testThread.set(test);
    }

    public static ExtentTest test() {
        return testThread.get();
    }

    public static void removeTest() {
        testThread.remove();
    }

    public static void unload() {
        if (extent != null) {
            extent.flush();
        }
        extent = null;
    }
}
