package com.w2a.testcases;

import com.w2a.base.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


/**
 * Created by sami on 8/11/17.
 */


public class AddCustomerTestSimple extends TestBase {


    //with data providor we are adding the getData method
    @Test(dataProvider = "getData")
    public void addCustomer(String firstname, String lastname, String postcode) throws InterruptedException {


        driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();


        sleepFor(10);
        //driver.findElement(By.cssSelector(OR.getProperty("addCustBtn"))).click();
        driver.findElement(By.xpath(".//button[@ng-click='addCust()']")).click();

        sleepFor(10);
        driver.findElement(By.cssSelector(OR.getProperty("firstname"))).sendKeys(firstname);
        driver.findElement(By.cssSelector(OR.getProperty("lastname"))).sendKeys(lastname);
        driver.findElement(By.cssSelector(OR.getProperty("postcode"))).sendKeys(postcode);
        driver.findElement(By.cssSelector(OR.getProperty("addbtn"))).click();


        Thread.sleep(2000);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());


        Assert.assertTrue(alert.getText().contains(("alerttext")));
        alert.accept();

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

