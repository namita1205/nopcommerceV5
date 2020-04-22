package com.nopcommerce.pageObject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SearchCustomerPage {
	public WebDriver ldriver;

	public SearchCustomerPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
		ldriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@FindBy(how = How.ID, using = "SearchEmail")
	@CacheLookup
	WebElement txtEmail;

	@FindBy(how = How.ID, using = "SearchFirstName")
	@CacheLookup
	WebElement txtFirstName;

	@FindBy(how = How.ID, using = "SearchLastName")
	@CacheLookup
	WebElement txtLastName;

	@FindBy(how = How.ID, using = "search-customers")
	@CacheLookup
	WebElement btnSearch;

	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody//tr")
	@CacheLookup
	List<WebElement> tableRows;

	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody//tr//td")
	@CacheLookup
	List<WebElement> tableColumns;

	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody//tr//td[9]")
	List<WebElement> btnEdit;

	@FindBy(how = How.ID, using = "customer-delete")
	WebElement btndelete;

	public void setEmail(String email) {
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}

	public void setFirstName(String fname) {
		txtFirstName.clear();
		txtFirstName.sendKeys(fname);
	}

	public void setLastName(String lname) {
		txtLastName.clear();
		txtLastName.sendKeys(lname);
	}

	public void clickSearch() {
		btnSearch.click();
	}

	public int getNoOfRows() {
		return tableRows.size();
	}

	public void clickDelete() {
		btndelete.click();
	}

	public boolean searchCustomerByEmail(String email) {
		boolean flag = false;
		for (int i = 1; i <= tableRows.size(); i++) {
			String emailid = ldriver.findElement(By.xpath("//table[@id='customers-grid']//tbody//tr[" + i + "]//td[2]"))
					.getText();
			if (emailid.equals(email)) {
				flag = true;
				break;
			}

		}
		return flag;
	}

	public boolean searchCustomerByName(String Name) {
		boolean flag = false;
		for (int i = 1; i <= tableRows.size(); i++) {
			String name = ldriver.findElement(By.xpath("//table[@id='customers-grid']//tbody//tr[" + i + "]//td[3]"))
					.getText();
			if (Name.equals(name)) {
				System.out.println("found customer:"+name);
				flag = true;

			}
		}
		if(!flag) {
			System.out.println("found not customer:"+Name);
		}
		return flag;
	}

	public boolean deleteCustomerverify(String email) {

		for (int i = 1; i <= tableRows.size(); i++) {
			String value = ldriver.findElement(By.xpath("//table[@id='customers-grid']//tbody//tr[" + i + "]//td[2]"))
					.getText();
			if (email.equals(value)) {
				System.out.println("email found");
				ldriver.findElement(By.xpath("//table[@id='customers-grid']//tbody//tr[" + i + "]//td[9]")).click();
				System.out.println("going to click delete");
				clickDelete();
				ldriver.findElement(By.xpath("//button[contains (text(),'Delete')]")).click();
				String text = ldriver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissable']"))
						.getText();
				System.out.println("text:"+text);
				if (text.contains("The customer has been deleted successfully")) {
					return true;
				}

			} 
		}
		System.out.println("returning false for email: "+email);
		return false;
	}

}
