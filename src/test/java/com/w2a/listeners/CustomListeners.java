package com.w2a.listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.log4j.Logger;
import org.testng.*;

import com.relevantcodes.extentreports.LogStatus;
import com.w2a.base.TestBase;
import com.w2a.utilities.MonitoringMail;
import com.w2a.utilities.TestConfig;
import com.w2a.utilities.TestUtil;

public class CustomListeners extends TestBase implements ITestListener,ISuiteListener {



	public static class Retry implements IRetryAnalyzer {

		public static final Logger log = Logger.getLogger(Retry.class.getName());

		private int retryCount = 0;
		private int maxRetryCount = 3;

		public boolean retry(ITestResult result) {
			if (retryCount < maxRetryCount) {
				log("Retrying test " + result.getName() + " with status " + getResultStatusName(result.getStatus()) + " for the " + (retryCount + 1) + " time(s).");
				retryCount++;

				Reporter.log("total retry was: " + result );
				return true;
			}
			return false;
		}
		public String getResultStatusName(int status) {
			String resultName = null;
			if (status == 1)
				resultName = "SUCCESS";
			if (status == 2)
				resultName = "FAILURE";
			if (status == 3)
				resultName = "SKIP";
			return resultName;
		}

		public void log(String data){
			log.info(data);
			Reporter.log(data);
		}

	}



	public String messageBody; //used for mailing email for the body of message
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}


	//key componenet to capture screenshot on failure
	public void onTestFailure(ITestResult arg0) {

		System.setProperty("org.uncommons.reportng.escape-output","false");
		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//when failing it will say....
		test.log(LogStatus.FAIL, arg0.getName().toUpperCase()+" Failed with exception : "+arg0.getThrowable());

		//when failed add a screen cap and get the screen shot name
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		
		
		
		Reporter.log("Click to see Screenshot");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+" height=200 width=200></img></a>");
		//ending the test and flushing it
		extentREP.endTest(test);
		extentREP.flush();

	}

	public void onTestSkipped(ITestResult arg0) {


		test.log(LogStatus.SKIP, arg0.getName().toUpperCase()+" Skipped the test as the Run mode is NO");
		extentREP.endTest(test);
		extentREP.flush();
		
	}


	public void onTestStart(ITestResult arg0) {
		//generates the test
		//on test start we have to tell extent that this is my first test case
		//telling the test to start
		test = extentREP.startTest(arg0.getName().toUpperCase());

		if (!TestUtil.isTestRunnable(arg0.getName(), excel)) {

			throw new SkipException("Skipping the test "
					+ arg0.getName().toUpperCase() + " as the run mode is NO ");


		}
	
	}

	//the arguement arg) stores the arguement name
	public void onTestSuccess(ITestResult arg0) {


		test.log(LogStatus.PASS, arg0.getName().toUpperCase()+" PASS");
		extentREP.endTest(test);
		extentREP.flush(); //flush the report else report will not generate
		
	}

	public void onFinish(ISuite arg0) {
		
		MonitoringMail mail = new MonitoringMail();
		 
		try {
			messageBody = "http://" + InetAddress.getLocalHost().getHostAddress()
					+ ":8080/job/DataDrivenLiveProject/Extent_Reports/";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	public void onStart(ISuite arg0) {
		// TODO Auto-generated method stub
		
	}




}
