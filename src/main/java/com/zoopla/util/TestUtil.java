package com.zoopla.util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import com.zoopla.driverManager.DriverManager;


public class TestUtil {

	public static String getCurrentDateTime() {
		DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_hh_mm_ss");
		Date currentDate = new Date();
		return customFormat.format(currentDate);
	}

	public static String getScreenshot(ITestResult result) {
		File src = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
		String path =Constants.getResourcepath()+"/screenshots/Screenshot_" + result.getName()
				+ TestUtil.getCurrentDateTime() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
