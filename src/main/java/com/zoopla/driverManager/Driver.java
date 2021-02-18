package com.zoopla.driverManager;

import com.zoopla.util.ReadPropertyFile;

public final class Driver {

	public static void initDriver() {
		
			DriverManager.setDriver(DriverFactory.getDriver());
			DriverManager.getDriver().manage().window().maximize();
			DriverManager.getDriver().manage().deleteAllCookies();
			DriverManager.getDriver().get(ReadPropertyFile.get("url"));
		
	}

	public static void quitDriver() {
		
			DriverManager.getDriver().quit();
	
	}

}
