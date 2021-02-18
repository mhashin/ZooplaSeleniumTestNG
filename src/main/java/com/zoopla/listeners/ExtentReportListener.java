package com.zoopla.listeners;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.zoopla.util.TestUtil;


public final class ExtentReportListener implements ITestListener {

	private static final String OUTPUT_FOLDER = "./extendReport/";
	private static final String FILE_NAME = "OrangeHRM_ExtentReport_" + TestUtil.getCurrentDateTime() + ".html";
	private ExtentReports extent = init();

	private static ThreadLocal<ExtentTest> extendtest = new ThreadLocal<ExtentTest>();

	public static ExtentTest getExtentTest() {
		return extendtest.get();
	}

	public static void setExtentTest(ExtentTest test) {
		extendtest.set(test);
	}
	
	public static void removeExtentTest() {
	     extendtest.remove();
 	}
	

	private ExtentReports init() {

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
		spark.viewConfigurer().viewOrder().as(new ViewName[]{
			ViewName.DASHBOARD,
			ViewName.TEST,
			ViewName.CATEGORY
		}).apply();
		extent.attachReporter(spark);
		return extent;

	}

	public synchronized void onStart(ITestContext context) {
		System.out.println("Test Suite started!");

	}

	public synchronized void onTestSuccess(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " passed!"));
		getExtentTest().pass("Test passed");
		getExtentTest().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	public synchronized void onTestFailure(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " failed!"));
		getExtentTest().fail(result.getThrowable(),
				MediaEntityBuilder.createScreenCaptureFromPath(TestUtil.getScreenshot(result)).build());
		getExtentTest().getModel().setEndTime(getTime(result.getEndMillis()));

	}

	public synchronized void onTestSkipped(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " skipped!"));
		getExtentTest().skip(result.getThrowable(),
				MediaEntityBuilder.createScreenCaptureFromPath(TestUtil.getScreenshot(result)).build());
		getExtentTest().getModel().setEndTime(getTime(result.getEndMillis()));

	}

	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));

	}

	public synchronized void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String qualifiedName = result.getMethod().getQualifiedName();
		int last = qualifiedName.lastIndexOf(".");
		int mid = qualifiedName.substring(0, last).lastIndexOf(".");
		String className = qualifiedName.substring(mid + 1, last);

		System.out.println(methodName + " started!");
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
				result.getMethod().getDescription());

		extentTest.assignCategory(result.getTestContext().getSuite().getName());
		extentTest.assignCategory(className);
		setExtentTest(extentTest);
		getExtentTest().getModel().setStartTime(getTime(result.getStartMillis()));
		

	}

	public synchronized void onFinish(ITestContext context) {
		System.out.println(("Test Suite is ending!"));
		extent.flush();
		removeExtentTest();
	}

	public static String getOutputFolder() {
		return OUTPUT_FOLDER;
	}

	public static String getFileName() {
		return FILE_NAME;
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

}
