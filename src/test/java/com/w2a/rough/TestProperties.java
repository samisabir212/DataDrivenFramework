package com.w2a.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {


	/*
	* THIS IS TO JUST TEST THE PROPERTIES FILE
	* */
	
	public static void main(String[] args) throws IOException {
		
		
		System.out.println(System.getProperty("user.dir"));

		//creating the properties class instance objects congi and OR (Object Resources)
		Properties config = new Properties();
		Properties OR = new Properties();
		FileInputStream fis = null;

		//initialize the fileinputstream to utilize the properties file
		fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/properties/Config.properties");
		//load the file from the fileinputstream for the configeration properties file
		config.load(fis);


		//initialize the fileinputstream to utilize the properties file
		fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/properties/OR.properties");
		//loading the file from the fileinputstream for the configeration properties file
		OR.load(fis);
		
		System.out.println(config.getProperty("browser"));
		
		//driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
		System.out.println(OR.getProperty("bmlBtn"));
	}

}
