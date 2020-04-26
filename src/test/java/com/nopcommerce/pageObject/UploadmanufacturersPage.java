package com.nopcommerce.pageObject;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nopcommerce.utilities.XLUtils;

public class UploadmanufacturersPage {
	public WebDriver ldriver;

	public UploadmanufacturersPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	// locators
	@FindBy(xpath = "/html/body/div[3]/div[2]/div/ul/li[2]/a/i[1]")
	@CacheLookup
	WebElement catalog;

	@FindBy(xpath = "//span[contains(text(),'Manufacturers')]")
	@CacheLookup
	WebElement catalogMenu;

	@FindBy(xpath = "//table[@id='manufacturers-grid']//tbody//tr")
	@CacheLookup
	List<WebElement> tablerows;

	@FindBy(xpath = "//table[@id='manufacturers-grid']//tbody//tr//td")
	@CacheLookup
	List<WebElement> tablecolumns;
	
	

	@FindBy(name ="importexcel")
	@CacheLookup
	WebElement btnImport;
	
	@FindBy(id="importexcelfile")
	@CacheLookup
	WebElement chooseFile;
	
	@FindBy(xpath="//button[@class='btn btn-primary']")
	@CacheLookup
	WebElement btnImportFromExcel;

	// action methods
	public void clickOnCatalog() {
		catalog.click();
	}

	public void clickCatalogMenu() {
		catalogMenu.click();
	}
	public int rownum() {
		return tablerows.size();
	}
	public int colnum() {
		return tablecolumns.size();
	}
	public void clickbtnImport() {
		btnImport.click();
	}
	public void clickChooseFile(String path) {
		chooseFile.sendKeys(path);
		
	}
	public void clickbtnImportFromExcel() {
		btnImportFromExcel.click();
	}
	public boolean verifyFileImport() {
		String exp_message="Manufacturers have been imported successfully.";
		String act_message=ldriver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissable']")).getText();
		System.out.println("act_message:"+act_message);
		if(act_message.contains(exp_message)) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean verifyValuesFromTables() throws IOException {
		System.out.println("in for loop");
		for(int r=1;r<=tablerows.size();r++) {
			
		String tableValues=ldriver.findElement(By.xpath("//table[@id='manufacturers-grid']//tbody//tr["+r+"]//td[1]")).getText();
		System.out.println(tableValues);
				if(tableValues.equals("Lenovo")) {
					return true;
				}
					
				
		}
		return false;
	}

}
