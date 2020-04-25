package com.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObject.AddcustomerPage;
import com.nopcommerce.pageObject.LoginPage;
import com.nopcommerce.pageObject.SearchCustomerPage;
import com.nopcommerce.testBase.BaseClass;

public class TC_SearchCustomerByName_005 extends BaseClass {
	
	@Test
	public void searchCustomerbyName() throws IOException, InterruptedException {

		driver.get(configPropObj.getProperty("baseURL"));

		LoginPage lp = new LoginPage(driver);

		logger.info("********Providing login details to application********");
		lp.setUserName(configPropObj.getProperty("useremail"));
		lp.setPassword(configPropObj.getProperty("password"));

		lp.clickbtnlogin();
		//Go to Search Page
		AddcustomerPage addcust = new AddcustomerPage(driver);
		addcust.clickOnCustomersMenu();
		addcust.clickOnCustomersMenuItem();
	
		logger.info("***********Providing First and Last Name of Customer*********");
		SearchCustomerPage searchcust=new SearchCustomerPage(driver);
		searchcust.setFirstName("Victoria");
		searchcust.setLastName("Terces");
		searchcust.clickSearch();
		Thread.sleep(5000);
		boolean status=searchcust.searchCustomerByName("Victoria Terces");
		logger.info("************Validating Search Customer by Name **************");
		Assert.assertEquals(true, status);
		
		
	}
	

	
	
	
	
	
}
