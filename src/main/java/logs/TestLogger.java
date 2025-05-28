package logs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import extent.ExtentManager;

public class TestLogger {
    private static final Logger logger = LogManager.getLogger(TestLogger.class);
    private static ExtentTest extentTest;

    public static void setExtentTest(ExtentTest test) {
        extentTest = test;
    }

    public static void info(String message) {
        logger.info(message);
        if (extentTest != null) {
            extentTest.log(Status.INFO, message);
        }
    }

    public static void error(String message) {
        logger.error(message);
        if (extentTest != null) {
            extentTest.log(Status.FAIL, message);
        }
    }

    public static void warn(String message) {
        logger.warn(message);
        if (extentTest != null) {
            extentTest.log(Status.WARNING, message);
        }
    }

    public static void debug(String message) {
        logger.debug(message);
    }
}
	
	