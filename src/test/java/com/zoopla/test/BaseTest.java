package com.zoopla.test;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.zoopla.driverManager.Driver;


public class BaseTest {

	public SoftAssert softassert;

	@BeforeTest
	public void setUp() {

		Driver.initDriver();
		softassert = new SoftAssert();

	}

	@AfterTest
	public void tearDown() {		

		Driver.quitDriver();
	}

}
