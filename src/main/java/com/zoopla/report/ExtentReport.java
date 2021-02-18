package com.zoopla.report;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.zoopla.util.TestUtil;


public final class ExtentReport {

	private static final String OUTPUT_FOLDER = "./extendReport/";
	private static final String FILE_NAME = "OrangeHRM_ExtentReport_" + TestUtil.getCurrentDateTime() + ".html";
	private static ExtentReports extent = initExtentReport();

	private ExtentReport() {
	}

	public static String getOutputFolder() {
		return OUTPUT_FOLDER;
	}

	public static String getFileName() {
		return FILE_NAME;
	}

	public static ExtentReports initExtentReport() {
		Path path = Paths.get(getOutputFolder());
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(getOutputFolder() + getFileName());
		spark.config().setDocumentTitle("Automation Test Report");
		spark.config().setReportName("Orange HRM Report");
		spark.config().setTheme(Theme.STANDARD);
		spark.viewConfigurer().viewOrder().as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY })
				.apply();
		extent.attachReporter(spark);
		return extent;
	}

	public static void flushReports() {
		extent.flush();

	}

	public static void createTest(String testcaseName) {
		ExtentManager.setExtendTest(extent.createTest(testcaseName));
	}

}
