package com.w2a.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.w2a.base.TestBase;

public class TestUtil extends TestBase {

	public static String screenshotPath;
	public static String screenshotName;


	//this method is used well in the listeners class "onTestFailure"
	public static void captureScreenshot() throws IOException {


		//returns you a file
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Date d = new Date();


		//non hardcoded
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		//must copy file to particular location must use fileutils class
		//create a screenshot path called screenshotpath you dont have to use it.
		//the fill will be copied and saved in the surefire html folder.
		FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + "/target/surefire-reports/html/" + screenshotName));

	}

	//common base data providor utilizing excel data
	@DataProvider(name="dp")
	public Object[][] getData(Method m) { //m gets the method name and passed on

		String sheetName = m.getName(); //gets the method name as a string
		int rows = excel.getRowCount(sheetName); //getting the row count of the sheet of the testcase
		int cols = excel.getColumnCount(sheetName); //getting the column of the sheet of the test case

		Object[][] data = new Object[rows - 1][1];

		//initializing the hashtable
		//storing (Puting) the excel data into this hashtable
		Hashtable<String,String> table = null;

		//the for loop for rows rows of data starting at row 2 until empty
		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2

			//creating a table called table
			table = new Hashtable<String,String>();

			//the for loop for columns
			for (int colNum = 0; colNum < cols; colNum++) {

				// data[0][0]..... //data[row][col]
				//this is where its extracting and putting the data in the table
				//remember that you add objects to hashtable by puting th key and value into it
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				//the zero column and zero row should be the first table
				data[rowNum - 2][0] = table;
			}

		}

		return data;

	}
	

	//being utilized in the customlisteners class
	public static boolean isTestRunnable(String testName, ExcelReader excel){
		
		String sheetName = "test_suite";
		int rows = excel.getRowCount(sheetName);
		
		
		for(int rNum =  2; rNum <= rows; rNum++){
			
			String testCase = excel.getCellData(sheetName, "TCID", rNum);
			
			if(testCase.equalsIgnoreCase(testName)){
				
				String runmode = excel.getCellData(sheetName, "Runmode", rNum);
				
				if(runmode.equalsIgnoreCase("Y"))
					return true;
				else
					return false;
			}
			
			
		}
		return false;
	}
	
}
