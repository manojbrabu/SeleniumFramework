package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.AfterSuite;

import java.io.File;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter reporter =
                    new ExtentSparkReporter(System.getProperty("user.dir")
                            + "/test-output/ExtentReport.html");
            reporter.config().setReportName("Automation Test Report");
            reporter.config().setDocumentTitle("Extent Report");
            System.out.println("Extent report will be generated at: "
                    + new File("test-output/ExtentReport.html").getAbsolutePath());

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }
}