package com.w2a.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;

public class BankManagerLoginTest extends TestBase {


	/*
	* testcase ment to fail to capture screenshot
	* */
	
	@Test(enabled = false)
	public void bankManagerLoginTest() throws InterruptedException, IOException{
		
		
		verifyEquals("abc", "xyz"); //update this to fail for screenshot
		sleepFor(3);
		log.debug("Inside Login Test");
		//click("bmlBtn_CSS");

		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn_CSS"))),"Login not successful");



		log.debug("Login successfully executed");
		Reporter.log("Login successfully executed");
		//Assert.fail("Login not successful");
		
		
		
	
	
	}
	
}
