package com.nopcommerce.testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObject.AddcustomerPage;
import com.nopcommerce.pageObject.ExportCustomersToExcelPage;
import com.nopcommerce.pageObject.LoginPage;
import com.nopcommerce.testBase.BaseClass;

public class TC_ExportCustomers_007 extends BaseClass {
	@Test
	public void exportCustomerTest() throws IOException, InterruptedException {
		driver.get(configPropObj.getProperty("baseURL"));

		LoginPage lp = new LoginPage(driver);
		lp.setUserName(configPropObj.getProperty("useremail"));
		lp.setPassword(configPropObj.getProperty("password"));
		lp.clickbtnlogin();
		AddcustomerPage addcust = new AddcustomerPage(driver);
		addcust.clickOnCustomersMenu();
		addcust.clickOnCustomersMenuItem();
		Thread.sleep(3000);
		ExportCustomersToExcelPage expcust = new ExportCustomersToExcelPage(driver);
		
		logger.info("***********Providing Email**************");
		
		expcust.setEmail("james_pan@nopCommerce.com");
		
		logger.info("************Clicking on ExportSelected**********");
		expcust.clickSearchbtn();
		Thread.sleep(3000);
		expcust.clickChkBox();
		Thread.sleep(3000);
		expcust.clickExport();
		Thread.sleep(3000);
		expcust.clickExportSelected();
		Thread.sleep(5000);
		
		logger.info("*************Validating File Exists***************");
		
		boolean statusFile=expcust.isFileExists("//Users//namita//Downloads//customers.xlsx");
		if(statusFile==true) {
			
			logger.info("File successfully downloaded");
		}
		else {
			logger.info("Download failed");
		}
		
		
		Thread.sleep(7000);
		boolean statusValue=expcust.verifyExportToExcel();
		//System.out.println(statusValue);
		Thread.sleep(3000);
		logger.info("**************Validating downloaded table values**********");
		Assert.assertEquals(statusValue, true);
		Thread.sleep(5000);
		
		logger.info("***************Validating file deleted**************");
		boolean statusDelete=expcust.deleteFile("//Users//namita//Downloads//customers.xlsx");
		if(statusDelete==true) {
			logger.info("File Deleted");
		}
		else {
			logger.info("File not Deleted");
		}

	}

}
