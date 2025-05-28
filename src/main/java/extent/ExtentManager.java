package extent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentTest test;

    public synchronized static ExtentReports getInstance() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/reports/extent-report.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setDocumentTitle("Automation Results");
            spark.config().setReportName("Demo Blaze Report");
            spark.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester", "QA Monika");
        }
        return extent;
    }

    public synchronized static ExtentTest getTest() {
        return test;
    }

    public synchronized static void setTest(ExtentTest extentTest) {
        test = extentTest;
    }
}

	