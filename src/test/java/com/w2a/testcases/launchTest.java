package com.w2a.testcases;

import com.w2a.base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by sami on 8/10/17.
 */
public class launchTest extends TestBase {


    @Test(priority = 1, enabled = true)
    public void launchChrome() throws InterruptedException {


    }

    @Test()
    public void customerLogin() throws InterruptedException {


        log.debug("inside login test");
        driver.findElement(By.cssSelector(OR.getProperty("bmlBtn_CSS"))).click();
        log.debug("bmlBtn_class is present and clicked");



        Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn_CSS"))));

        log.debug("login successful");



    }


}
