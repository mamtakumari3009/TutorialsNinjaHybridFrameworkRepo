package com.tutorialsninja.qa.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	public static ExtentReports generateExtentReport() {
		
		ExtentReports extentReport = new ExtentReports();
		File  extentReporFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReporFile);
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Tutorials Ninja Automation Results Report");
		sparkReporter.config().setDocumentTitle("TN Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReport.attachReporter(sparkReporter);
		
		Properties configProp = new Properties();
		File configPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		
		FileInputStream fisConficProp;
		try {
			fisConficProp = new FileInputStream(configPropFile);
		
		configProp.load(fisConficProp);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentReport.setSystemInfo("Application URL",configProp.getProperty("baseURL"));
		extentReport.setSystemInfo("Browser Name",configProp.getProperty("browserName"));
		extentReport.setSystemInfo("Email",configProp.getProperty("validEmail"));
		extentReport.setSystemInfo("Password",configProp.getProperty("validPassword"));
		extentReport.setSystemInfo("Operating System",System.getProperty("os.name"));
		extentReport.setSystemInfo("Operating System",System.getProperty("user.name"));
		extentReport.setSystemInfo("Operating System",System.getProperty("java.version"));
		
		return extentReport;
		
		
	}

}
