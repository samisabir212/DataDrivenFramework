package com.w2a.testcases;

import com.w2a.base.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * Created by sami on 8/10/17.
 */
public class launchTest extends TestBase {


    @Test(priority = 1, enabled = false)
    public void launchChrome() throws InterruptedException {


    }

    @Test()
    public void customerLogin() throws InterruptedException {


        driver.findElement(By.cssSelector(OR.getProperty("bmlBtn_CSS"))).click();
        sleepFor(5);


    }


}
