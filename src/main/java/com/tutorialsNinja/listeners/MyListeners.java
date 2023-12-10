package com.tutorialsNinja.listeners;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsNinja.utils.ExtentReporter;

public class MyListeners implements ITestListener{

	ExtentReports extentReport;
	ExtentTest extentTest;
	
	@Override
	public void onStart(ITestContext context) {
		extentReport = ExtentReporter.generateExtentReport();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		String testname = result.getName();
		extentTest = extentReport.createTest(testname);
		extentTest.log(Status.INFO, testname+" execution started");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testname = result.getName();
		extentTest.log(Status.PASS, testname+" successfully passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testname = result.getName();	
		
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String target = System.getProperty(("user.dir")+"\\Screenshots\\"+testname+".png");
		try {
			FileHandler.copy(src, new File (target));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		extentTest.addScreenCaptureFromPath(target);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testname+" failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testname = result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testname+" skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
	}
	
}
