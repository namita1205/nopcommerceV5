package com.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObject.AddcustomerPage;
import com.nopcommerce.pageObject.LoginPage;
import com.nopcommerce.pageObject.SearchCustomerPage;
import com.nopcommerce.testBase.BaseClass;

public class TC_SearchCustomerByEmail_004 extends BaseClass {

	@Test
	public void searchCustomerbyEmail() throws IOException {

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
		//Provide Email in Search Page
		
		SearchCustomerPage searchcust=new SearchCustomerPage(driver);
		searchcust.setEmail("victoria_victoria@nopCommerce.com");
		searchcust.clickSearch();
		boolean status=searchcust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		Assert.assertEquals(true, status);
		
		
	}
}
