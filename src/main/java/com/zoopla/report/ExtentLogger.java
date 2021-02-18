package com.zoopla.report;

public final class ExtentLogger {

	private ExtentLogger() {
	}

	public static void pass(String message) {
		ExtentManager.getExtendTest().pass(message);
	}

	public static void fail(String message) {
		ExtentManager.getExtendTest().fail(message);
	}

	public static void skip(String message) {
		ExtentManager.getExtendTest().skip(message);
	}

	public static void info(String message) {
		ExtentManager.getExtendTest().info(message);
	}
}
