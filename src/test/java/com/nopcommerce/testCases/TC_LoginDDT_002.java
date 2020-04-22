package com.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nopcommerce.pageObject.LoginPage;
import com.nopcommerce.testBase.BaseClass;
import com.nopcommerce.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass {
	
	@Test(dataProvider = "LoginData")
	
	
	public void loginTest(String user, String pwd, String exp) throws InterruptedException {
		logger.info("********Starting TC_LoginTest_002********");

		System.out.println(user + pwd + exp);
		driver.get(configPropObj.getProperty("baseURL"));

		LoginPage lp = new LoginPage(driver);

		lp.setUserName(user);
		lp.setPassword(pwd);

		lp.clickbtnlogin();
		Thread.sleep(5000);

		String exp_title = "Dashboard / nopCommerce administration";
		String act_title = driver.getTitle();
		if (exp_title.equals(act_title)) {
			if (exp.equals("Pass")) {
				logger.info("********loginTest is Passed********");
				lp.clickLogout();

				Assert.assertTrue(true);
			} else if (exp.equals("Fail")) {
				logger.info("********loginTest is Failed********");
				lp.clickLogout();
				Assert.assertTrue(false);
			} else if (!exp_title.equals(act_title)) {
				if (exp.equals("Pass")) {
					logger.info("********loginTest is Failed********");
					Assert.assertTrue(false);
				} else if (exp.equals("Fail")) {
					logger.info("********loginTest is Failed********");
					Assert.assertTrue(true);
					lp.clickLogout();
				}
			}

		}
		logger.info("********Finished TC_LoginTest_002********");

	}

	@DataProvider(name = "LoginData")
	
	public String[][] getData() throws IOException {
		System.out.println("getData called");
		String path = System.getProperty("user.dir") + "/TestData/datalogin.xlsx";
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
		String logindata[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {

			for (int j = 0; j < colcount; j++) {
				logindata[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}

		}
		return logindata;
	}
}
