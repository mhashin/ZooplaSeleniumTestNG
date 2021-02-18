package com.zoopla.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ForSalePage extends BasePage {
	
	By heading=By.xpath("//h1[@data-testid='results-title']");
	By listPriceProperties=By.xpath("//div[contains(@id,'listing_')]//p[@class='css-18tfumg-Text eczcs4p0']");
	

	public boolean verifyLocation(String loc) {
		return getText(heading).contains(loc);
	}
	
	public void getPriceProperties() {
		
		List<Integer> pricelist=new ArrayList<Integer>();
		List<WebElement> propertyPriceList=ListOfElements(listPriceProperties);
		for(WebElement ele:propertyPriceList) {
			String price=ele.getText();
			pricelist.add(Integer.parseInt(price.replaceAll("[^0-9]", "")));
		}
		System.out.println("List the property pricing in descending order......");
		Collections.sort(pricelist,Collections.reverseOrder());
		System.out.println(pricelist);
	}

}
