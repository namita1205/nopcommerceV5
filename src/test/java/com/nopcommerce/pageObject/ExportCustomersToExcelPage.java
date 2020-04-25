package com.nopcommerce.pageObject;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nopcommerce.utilities.XLUtils;

public class ExportCustomersToExcelPage {
	public WebDriver ldriver;

	public ExportCustomersToExcelPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	// locators
	@FindBy(id = "SearchEmail")
	@CacheLookup
	WebElement txtEmail;

	@FindBy(id = "search-customers")
	@CacheLookup
	WebElement btnSearch;

	@FindBy(xpath = "//table[@id='customers-grid']//tbody//tr")
	@CacheLookup
	WebElement tablerow;

	@FindBy(xpath = "//table[@id='customers-grid']//tbody//tr//td[1]")
	@CacheLookup
	WebElement chkBox;

	@FindBy(xpath = "//button[@class='btn btn-success dropdown-toggle']")
	@CacheLookup
	WebElement btnExport;

	@FindBy(xpath = "//button[@id='exportexcel-selected']")

	@CacheLookup
	WebElement btnExportSelected;

	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}

	public void clickSearchbtn() {
		btnSearch.click();
	}

	public void countRows() {
		tablerow.getSize();
	}

	public void clickChkBox() {
		chkBox.click();
	}

	public void clickExport() {
		btnExport.click();
	}

	public void clickExportSelected() {
		btnExportSelected.click();
	}

	public boolean isFileExists(String locator) {
		File f = new File(locator);
		if (f.exists()) {
			return true;
		} else {
			return false;
		}

	}

	public boolean verifyExportToExcel() throws IOException {
		String exp_value = ldriver.findElement(By.xpath("//table[@id='customers-grid']//tbody//tr[1]//td[2]"))
				.getText();
		System.out.println(exp_value);
		String path = "//Users//namita//Downloads//customers.xlsx";

		int colnum = XLUtils.getCellCount(path, "Customer", 0);
		System.out.println(colnum);
		int rownum = XLUtils.getRowCount(path, "Customer");
		System.out.println(rownum);
		boolean found=false;
		for (int i = 1; i <= rownum; i++) {
			
			for (int j = 0; j < colnum; j++) {
				String data = XLUtils.getCellData(path, "Customer", i, j);
				System.out.println(data);
				if (exp_value.equals(data)) {
				found=true;
				System.out.println("found data:"+data);
				}

			}

		}
		return found;
	}

	public boolean deleteFile(String locator) {
		File f = new File(locator);
		if (f.delete()) {
			return true;
		} else {
			return false;
		}

	}

	

}
