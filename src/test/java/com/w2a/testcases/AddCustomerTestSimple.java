package com.w2a.testcases;

import com.w2a.base.TestBase;
import com.w2a.listeners.CustomListeners;
import com.w2a.utilities.TestUtil;
import org.apache.regexp.RE;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;


/**
 * Created by sami on 8/11/17.
 */


public class AddCustomerTestSimple extends TestBase {

    CustomListeners customListeners = new CustomListeners();



    //with data providor we are adding the getData method
    @Test(dataProvider = "getData")
    public void addCustomer(String firstname, String lastname, String postcode) throws InterruptedException, IOException {


        //driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
        click("bmlBtn_CSS");
        Reporter.log("manager button log in clicked");

        sleepFor(10);
        //driver.findElement(By.cssSelector(OR.getProperty("addCustBtn"))).click();
        //driver.findElement(By.xpath(".//button[@ng-click='addCust()']")).click();
        click("addCustBtn_CSS");
        sleepFor(4);

        Reporter.log("add customer button clicked");

        sleepFor(4);
       // driver.findElement(By.cssSelector(OR.getProperty("firstname"))).sendKeys(firstname);
        type("firstname_CSS", firstname);
        Reporter.log("first name entered from excel");


        sleepFor(3);
       // driver.findElement(By.xpath(".//input[@ng-model='lName']")).sendKeys(lastname);
        type("lastname_XPATH", lastname);
        Reporter.log("last name entered from excel");

/*
        driver.findElement(By.cssSelector(OR.getProperty("lastname"))).sendKeys(lastname);
*/
        //driver.findElement(By.cssSelector(OR.getProperty("postcode"))).sendKeys(postcode);
        type("postcode_CSS", postcode);
        Reporter.log("post code entered from excel");

        //driver.findElement(By.cssSelector(OR.getProperty("addbtn"))).click();
        click("addbtn_CSS");
        Reporter.log("add button clicked");


        Thread.sleep(2000);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());


        //Assert.assertTrue(alert.getText().contains(("alerttext")));
        alert.accept();
        Reporter.log("alert accepted");


        Thread.sleep(2000);


    }


    //this method is set as a dataProvidor which will be linked to the addCustomer method
    @DataProvider
    public Object[][] getData() {


        String sheetName = "AddCustomerTest";
        int rows = excel.getRowCount(sheetName);
        int cols = excel.getColumnCount(sheetName);


        Object[][] data = new Object[rows - 1][cols];


        //@ data[0][0]
        for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2


            for (int colNum = 0; colNum < cols; colNum++) {

                //@ data[0][0]
                data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);


            }

        }

        return data;


    }


}

