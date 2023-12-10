package com.tutorialsNinja.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	public static ExtentReports generateExtentReport() {
		ExtentReports extentReport = new ExtentReports();
		
		File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\Extent Reports\\Extent-report.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Tutorials Ninja Test Automation report");
		sparkReporter.config().setDocumentTitle("TN Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReport.attachReporter(sparkReporter);
		
		Properties propfile = new Properties();
		File configFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsNinja\\config\\config.properties");
		try {
			FileInputStream fis = new FileInputStream(configFile);
			propfile.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		extentReport.setSystemInfo("Application URL", propfile.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", propfile.getProperty("browserName"));
		extentReport.setSystemInfo("Operation System", System.getProperty("os.name"));
		extentReport.setSystemInfo("Username", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));

		return extentReport;
	}
}
