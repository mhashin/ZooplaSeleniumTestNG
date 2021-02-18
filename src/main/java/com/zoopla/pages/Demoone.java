package com.zoopla.pages;

import org.openqa.selenium.By;

public class Demoone extends BasePage{
	
	By googlebox=By.name("q");
	
	public Demoone enterText() {
		
		doSendKeys(googlebox, "India", "googlebox");
		return this;
	}

}
