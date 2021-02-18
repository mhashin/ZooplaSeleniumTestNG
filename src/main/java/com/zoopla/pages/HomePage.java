package com.zoopla.pages;

import org.openqa.selenium.By;


public final class HomePage extends BasePage{
	
	By txtSearch =By.xpath("//input[@id='search-input-location']");
	By btnSearch=By.id("search-submit");
	By btnSavePreferences=By.xpath("//button[contains(text(),'Save my preferences')]");
	
	public ForSalePage searchLocation(String loc) {
		
		doClick(btnSavePreferences,"save preferences");
		doSendKeys(txtSearch, loc, "location");
		doClick(btnSearch, "search");
		return new ForSalePage();
	}

}
