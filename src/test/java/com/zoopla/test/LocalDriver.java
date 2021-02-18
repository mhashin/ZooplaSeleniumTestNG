package com.zoopla.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LocalDriver {
	
	public static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();
	
	public WebDriver getDriver() {
		return dr.get();
	}
	

	public void setup() {
		
		WebDriverManager.chromedriver().setup();
	    dr.set(new ChromeDriver());
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
		WebElement ele=getDriver().findElement(By.name("q"));
		ele.sendKeys("Testing");
	}
	
	@AfterMethod
	public void closedriver() {
		getDriver().quit();
	}

}
