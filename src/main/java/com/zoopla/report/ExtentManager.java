package com.zoopla.report;

import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {

	private ExtentManager() {
	}

	private static ThreadLocal<ExtentTest> extTest = new ThreadLocal<ExtentTest>();

	public static ExtentTest getExtendTest() {
		return extTest.get();
	}

	static void setExtendTest(ExtentTest test) {
		extTest.set(test);
	}

	static void unload() {
		extTest.remove();
	}

}
