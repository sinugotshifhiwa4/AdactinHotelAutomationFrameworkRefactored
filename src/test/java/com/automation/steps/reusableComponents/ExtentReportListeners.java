package com.automation.steps.reusableComponents;

import com.automation.steps.utilities.DriverFactory;
import com.automation.steps.utilities.ExtentFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class ExtentReportListeners implements ITestListener {

    // ExtentReports object for managing report generation
    ExtentReports reports;

    // ExtentTest object for managing individual test reports
    ExtentTest test;

    // Variable to hold the name of the current test method
    String methodName;

    // ExceptionHandler object for handling exceptions
    ExceptionHandler handler = new ExceptionHandler();

    // Method executed before each test case
    public void onTestStart(ITestResult result){

        // Retrieve the name of the current test method
        methodName = result.getMethod().getMethodName();

        // Create a new test entry in the Extent report
        test = reports.createTest(methodName);
        ExtentFactory.getInstance().setExtent(test);
    }

    // Method executed after each successful test case
    public void onTestSuccess(ITestResult result){

        // Log the success status of the test case in the Extent report
        ExtentFactory.getInstance().getExtent().log(Status.PASS, "Test Case " + methodName + " is Passed");

        // Remove the ExtentTest instance from ThreadLocal
        ExtentFactory.getInstance().removeExtentObject();
    }

    // Method executed after each failed test case
    public void onTestFailure(ITestResult result){

        // Capture screenshot upon test failure
        File screenshot = ((TakesScreenshot) DriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);

        try {
            // Convert screenshot to Base64
            byte[] fileScreenshot = Files.readAllBytes(screenshot.toPath());
            String screenshotEncoder = Base64.getEncoder().encodeToString(fileScreenshot);

            // Embed screenshot in the Extent report
            ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Test Case " + methodName + " is Failed", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotEncoder).build());
            ExtentFactory.getInstance().getExtent().log(Status.FAIL, result.getThrowable());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // Method executed after each skipped test case
    public void onTestSkipped(ITestResult result){

        // Log the skip status of the test case in the Extent report
        ExtentFactory.getInstance().getExtent().log(Status.SKIP, "Test Case " + methodName + " is Skip");

        // Remove the ExtentTest instance from ThreadLocal
        ExtentFactory.getInstance().removeExtentObject();
    }

    // Method executed before the start of test suite execution
    public void onStart(ITestContext context){

        // Initialize ExtentReports and configure report setup
        reports = ExtentReportConfiguration.extentReportSetUp();
    }

    // Method executed after the completion of test suite execution
    public void onFinish(ITestContext context){

        // Flush the ExtentReports object to generate the report
        reports.flush();
    }
}
