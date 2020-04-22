package com.nopcommerce.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObject.AddcustomerPage;
import com.nopcommerce.pageObject.LoginPage;
import com.nopcommerce.pageObject.SearchCustomerPage;
import com.nopcommerce.testBase.BaseClass;

public class TC_AddCustomerTest_003 extends BaseClass {
	@Test
	public void addNewCustomer() throws InterruptedException {
		logger.info("********Starting TC_LoginTest_003********");

		driver.get(configPropObj.getProperty("baseURL"));

		LoginPage lp = new LoginPage(driver);

		lp.setUserName(configPropObj.getProperty("useremail"));
		lp.setPassword(configPropObj.getProperty("password"));

		lp.clickbtnlogin();
		
		AddcustomerPage addcust = new AddcustomerPage(driver);
		addcust.clickOnCustomersMenu();
		addcust.clickOnCustomersMenuItem();
		Thread.sleep(3000);
		addcust.clickAddNew();
		

		logger.info("***************  Providing customer details  *********** ");
		Thread.sleep(3000);
		addcust.setEmail("testing@jadoo.com");
		System.out.println("1");
		addcust.setPassword("abcd");
		System.out.println("2");
		addcust.setFirstName("John");
		System.out.println("3");
		addcust.setLastName("Ray");
		addcust.setGender("Male");
		addcust.setManagerOfVendor("Vendor 2");
				Thread.sleep(3000);
		addcust.setCustomerRoles("Administrators");
		addcust.setcompany("FutureCompany");
		addcust.setAdmincontent("Automation testing");
		System.out.println("4");
		Thread.sleep(5000);
		addcust.setDOB("November 2005", "14");
		addcust.clickOnSave();
		// Validation
		if (addcust.verifyConfirmationMsg()) {
			logger.info("***************  Customer added succesfully *********** ");
			Assert.assertTrue(true);
		}
		else {
			logger.error("*************** Customer Not added succesfully *********** ");
			Assert.assertTrue(false);
		}

	}
	
	@Test
	public void deleteCustomer() throws InterruptedException {
		Thread.sleep(5000);
		SearchCustomerPage searchcust=new SearchCustomerPage(driver);
		boolean status2=searchcust.deleteCustomerverify("testing@jadoo.com");
		Assert.assertEquals(status2, true);
	}
}
