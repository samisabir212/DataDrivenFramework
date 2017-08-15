package com.w2a.testcases;

import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.util.Hashtable;

/**
 * Created by sami on 8/15/17.
 */
public class OpenAccountTestPrac1 extends TestBase {

    @Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
    public void openAccountTest(String customer, String currency) {

        click("bmlBtn_CSS");
        click("openaccount_CSS");
        select("customer_CSS", customer);
        select("currency_CSS", currency);
        click("process_CSS");


        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();

    }
}
