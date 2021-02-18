package com.zoopla.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.zoopla.driverManager.Driver;
import com.zoopla.driverManager.DriverManager;
import com.zoopla.pages.ForSalePage;
import com.zoopla.pages.HomePage;
import com.zoopla.util.ReadPropertyFile;

public class ForSalePageTest  {
	
	HomePage homepage;
	ForSalePage forsalepage;

	@BeforeMethod
	public void set() {
		Driver.initDriver();
		homepage = new HomePage();
		
	}
	
	@Test
	public void verifyPropertiesDescendingOrder() {
		
		forsalepage=homepage.searchLocation(ReadPropertyFile.get("location"));
		forsalepage.getPriceProperties();
		
	}
	
	@AfterMethod
	public void quit() {
		DriverManager.getDriver().quit();
	}


}
