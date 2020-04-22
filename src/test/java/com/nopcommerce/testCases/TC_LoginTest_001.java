package com.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObject.LoginPage;
import com.nopcommerce.testBase.BaseClass;

public class TC_LoginTest_001 extends BaseClass {

	@Test
	public void loginTest() throws IOException {
		logger.info("********Starting TC_LoginTest_001********");

		driver.get(configPropObj.getProperty("baseURL"));

		LoginPage lp = new LoginPage(driver);
		
		logger.info("********Providing login details to application********");
		lp.setUserName(configPropObj.getProperty("useremail"));
		lp.setPassword(configPropObj.getProperty("password"));
		
		logger.info("**********Clicking on the login button********");
		lp.clickbtnlogin();

		String exp_title = "Dashboard / nopCommerce administration";
		String act_title = driver.getTitle();
		
		logger.info("**********Login validation starting*********");
		if (exp_title.equals(act_title)) {
			logger.info("**********Login Test passed**********");
			
			Assert.assertTrue(true);
		} else {
			logger.error("*********Login Test failed**********");
			captureScreen(driver,"loginTest");
			Assert.assertTrue(false);
		}

	}

}
