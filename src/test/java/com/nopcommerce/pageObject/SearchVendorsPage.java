package com.nopcommerce.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SearchVendorsPage {
	public WebDriver ldriver;
	public SearchVendorsPage(WebDriver rdriver){
		ldriver=rdriver;
		PageFactory.initElements(ldriver, this);
	}
	
	//locators
	
By lnkVendors=By.xpath("//li[@class='treeview menu-open']//li[4]//a[1]");
By txtvendorName=By.id("SearchName");
By btnSearch=By.id("search-vendors");
By tablerows=By.xpath("//table[@id='vendors-grid']//tbody//tr");
By tablecolumns=By.xpath("//table[@id='vendors-grid']//tbody//tr//td");

public void clickVendors() {
	ldriver.findElement(lnkVendors).click();
}
public void setVendorsName(String vname) {
	ldriver.findElement(txtvendorName).sendKeys(vname);
}
public void clickSearch() {
	ldriver.findElement(btnSearch).click();
}
public int rowSize() {
	List<WebElement> rowNum=ldriver.findElements(tablerows);
	return rowNum.size();
	
}
public boolean searchByName(String name) {
	
	for(int i=1;i<=rowSize();i++) {
		String Name=ldriver.findElement(By.xpath("//table[@id='vendors-grid']//tbody//tr["+i+"]//td[1]")).getText();
		if(name.equals(Name)) {
		  return true;
		}
		
		
	}
	return false;
	
}





























}
