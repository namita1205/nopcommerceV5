package com.nopcommerce.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObject.AddcustomerPage;
import com.nopcommerce.pageObject.LoginPage;
import com.nopcommerce.pageObject.SearchVendorsPage;
import com.nopcommerce.testBase.BaseClass;

public class TC_SearchVendorsByName_006 extends BaseClass{
	@Test
	public void searchVendorsByName() throws InterruptedException {
		driver.get(configPropObj.getProperty("baseURL"));
		LoginPage lp=new LoginPage(driver);
		Thread.sleep(3000);
		lp.setUserName(configPropObj.getProperty("useremail"));
		lp.setPassword(configPropObj.getProperty("password"));
		lp.clickbtnlogin();
		
		AddcustomerPage addcust=new AddcustomerPage(driver);
		addcust.clickOnCustomersMenu();
		
		SearchVendorsPage vendors=new SearchVendorsPage(driver);
		vendors.clickVendors();
		Thread.sleep(5000);
		vendors.setVendorsName("Vendor 1");
		vendors.clickSearch();
		vendors.searchByName("Vendor 1");
		//Assert.assertEquals(vendors.searchByName("Vendor 1"), true);
		Assert.assertTrue(vendors.searchByName("Vendor 1"));
	}
	

}
