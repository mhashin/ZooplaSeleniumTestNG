package com.zoopla.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LocalTest {

	
	public static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();
	
	By google=By.name("q");

	public static WebDriver getDriver() {
		return dr.get();
	}

	public static void setDriver(WebDriver driver) {
		dr.set(driver);
	}
	
	
	public void doSendKeys(By locator,String value) {
		
		getDriver().findElement(locator).sendKeys(value);
	}
	
	public void setup() {
		WebDriverManager.chromedriver().setup();
		setDriver(new ChromeDriver());
		getDriver().manage().window().maximize();
	    getDriver().get("https://www.google.co.in/");
	}
	
	@BeforeMethod
	public void openBrowser() {
		setup();
		}
	
	@Test
	public void test1() {
		
		String title=getDriver().getTitle();
		System.out.println(title);
	}
	
	@Test(priority=1)
	public void test2() {
		
		doSendKeys(google, "Testing");
	}
	
	@AfterMethod
	public void closedriver() {
		getDriver().quit();
	}

}
