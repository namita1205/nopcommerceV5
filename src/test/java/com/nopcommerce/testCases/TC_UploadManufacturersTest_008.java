package com.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObject.LoginPage;
import com.nopcommerce.pageObject.UploadmanufacturersPage;
import com.nopcommerce.testBase.BaseClass;

public class TC_UploadManufacturersTest_008 extends BaseClass {
	@Test

	public void uploadTest() throws IOException, InterruptedException {
		driver.get(configPropObj.getProperty("baseURL"));

		LoginPage lp = new LoginPage(driver);

		lp.setUserName(configPropObj.getProperty("useremail"));
		lp.setPassword(configPropObj.getProperty("password"));
		lp.clickbtnlogin();
		

	UploadmanufacturersPage um= new UploadmanufacturersPage(driver);
	Thread.sleep(3000);
	um.clickOnCatalog();
	Thread.sleep(3000);
	um.clickCatalogMenu();
	Thread.sleep(3000);
	um.clickbtnImport();
	Thread.sleep(7000);
	um.clickChooseFile("C:\\Users\\namit\\Downloads\\manufacturers.xlsx");
	um.clickbtnImportFromExcel();
	Thread.sleep(5000);

	boolean statusUpload=um.verifyFileImport();
	Assert.assertEquals(statusUpload, true);
	Thread.sleep(5000);
	boolean statustableValue=um.verifyValuesFromTables();
	Assert.assertEquals( statustableValue, true);
	
	}
	
}
