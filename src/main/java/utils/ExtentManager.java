package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    //ExtendReports class - - Create an ExtentReports object
    //ExtentSparkReporter - - Attach a reporter (defines output format and location)
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    public static void initReport(String suiteName) {
        if (extent == null) {
            ExtentSparkReporter reporter =
                    new ExtentSparkReporter(System.getProperty("user.dir")
                            + "/Report/"+suiteName+".html");//defines output format and location
            reporter.config().setReportName(suiteName);
            reporter.config().setDocumentTitle("Extent Report");

            extent = new ExtentReports();
            extent.attachReporter(reporter);//Attach a reporter
        }
    }
    public static ExtentReports getInstance() {
        return extent;
    }

    // 🔹 Set current test
    public static void setTest(ExtentTest test) {
        testThread.set(test);// Create tests using ExtentTest, refer onStart
    }

    // 🔹 Get current test
    public static ExtentTest test() {
        return testThread.get();
    }//Get ExtendTest instance

    public static void removeTest(){
        testThread.remove();
    }

    // Set null to Extend object

    public static void unLoad(){
        extent = null;
    }
}

