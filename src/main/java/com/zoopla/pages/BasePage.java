package com.zoopla.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.zoopla.driverManager.DriverManager;
import com.zoopla.report.ExtentLogger;


public class BasePage {

	protected static void doSendKeys(By locator, String value, String elementname) {
		DriverManager.getDriver().findElement(locator).sendKeys(value);
		ExtentLogger.info("enter the " + elementname + " : " + value);
	}

	protected static void doClick(By locator, String elementname) {
		DriverManager.getDriver().findElement(locator).click();
		ExtentLogger.info("click the button : " + elementname);
	}

	public static String getTitle() {
		return DriverManager.getDriver().getTitle();
	}

	protected static String getText(By locator) {
		return getWebElement(locator).getText();
	}

	protected static WebElement getWebElement(By locator) {
		/*WebElement ele = null;
		try {
			ele = DriverManager.getDriver().findElement(locator);
		} catch (Exception e) {
			System.out.println("element could not be created..." + locator);
		}
		return ele;*/
		WebElement ele = DriverManager.getDriver().findElement(locator);
		return ele;
	}
	
	protected static List<WebElement> ListOfElements(By locator) {
		
		return DriverManager.getDriver().findElements(locator);
	}
	
	protected static void waitForElementVisible(By locator) {
		WebDriverWait wait=new WebDriverWait(DriverManager.getDriver(),20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

}
