package com.zoopla.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.zoopla.driverManager.Driver;
import com.zoopla.driverManager.DriverManager;
import com.zoopla.pages.BasePage;
import com.zoopla.pages.ForSalePage;
import com.zoopla.pages.HomePage;
import com.zoopla.util.ReadPropertyFile;

public class HomePageTest {

	HomePage homepage;
	ForSalePage forsalepage;

	@BeforeMethod(alwaysRun=true)
	public void setUp() {
		Driver.initDriver();
		homepage = new HomePage();
	}

	@Test(priority = 1)
	public void verifyHomePageTitleTest() {
		
		String pageTitle = BasePage.getTitle();
		Assert.assertEquals(pageTitle, ReadPropertyFile.get("title"));

	}

	@Test(priority = 2)
	public void verifyLocationSearchTest() {
		
		forsalepage=homepage.searchLocation(ReadPropertyFile.get("location"));
		Assert.assertTrue(forsalepage.verifyLocation(ReadPropertyFile.get("location")));
	}
	
	@AfterMethod
	public void quit() {
		DriverManager.getDriver().quit();
	}


}
