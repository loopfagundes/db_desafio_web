package app.netlify.bugbank.utils;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.service.ExtentTestManager;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Report implements ITestListener {

    public void onTestFailure(ITestResult result) {
        ExtentTestManager.getTest().fail("Ocorreu uma falha no teste.", Screenshot.capture());
    }

    public static void log(Status status, String message) {
        ExtentTestManager.getTest().log(status, message);
    }

    public static void logCapture(Status status, String message) {
        ExtentTestManager.getTest().log(status, message, Screenshot.capture());
    }
}