package com.zoopla.driverManager;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.zoopla.util.ReadPropertyFile;

import io.github.bonigarcia.wdm.WebDriverManager;

public final class DriverFactory {

	public static WebDriver getDriver() {
	
		String browser=ReadPropertyFile.get("browser").trim();
		String runMode = ReadPropertyFile.get("runMode").trim();

		if (browser.equalsIgnoreCase("chrome")) {

			if (runMode.equalsIgnoreCase("remote")) {
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setBrowserName(BrowserType.CHROME);
				try {
					DriverManager.setDriver(new RemoteWebDriver(new URL(ReadPropertyFile.get("hubURL")), cap));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			} else {
				WebDriverManager.chromedriver().setup();
				DriverManager.setDriver(new ChromeDriver());
			}

		} else if (browser.equalsIgnoreCase("ff")) {

			if (runMode.equalsIgnoreCase("remote")) {
				DesiredCapabilities dc = new DesiredCapabilities();
				dc.setBrowserName(BrowserType.FIREFOX);
			} else {
				WebDriverManager.firefoxdriver().setup();
				DriverManager.setDriver(new FirefoxDriver());	
			}

			DriverManager.setDriver(new FirefoxDriver());
		} else {
			System.out.println(browser + "not found.Please pass the correct browser");
		}
		return DriverManager.getDriver();
		
	}
	
	
	
}
