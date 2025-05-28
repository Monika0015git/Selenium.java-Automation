package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import base.BaseTest;
import extent.ExtentManager;
import logs.TestLogger;

public class ExtentTestNGListener implements ITestListener {
    private static ExtentReports extent = ExtentManager.getInstance();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        ExtentManager.setTest(test);

        Object instance = result.getInstance();
        if (instance instanceof BaseTest) {
            ((BaseTest) instance).test = test;
        }
        // provide to TestLogger
        TestLogger.setExtentTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.getTest().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = ExtentManager.getTest();
        test.fail(result.getThrowable());

        Object instance = result.getInstance();
        if (instance instanceof BaseTest) {
            String path = ((BaseTest) instance).takeScreenshot(result.getMethod().getMethodName());
            if (path != null) {
                test.addScreenCaptureFromPath(path);
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentManager.getTest().skip("Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}

