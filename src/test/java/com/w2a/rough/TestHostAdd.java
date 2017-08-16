package com.w2a.rough;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.w2a.utilities.MonitoringMail;
import com.w2a.utilities.TestConfig;



/*
* THIS USES THE UTILITIES PACKAGE>> TEST CONFIG CLASS
* */
public class TestHostAdd {

	public static void main(String[] args) throws UnknownHostException, AddressException, MessagingException {

		/*
		* gets the local host address number
		* */
		System.out.println(InetAddress.getLocalHost().getHostAddress());



		//the class which initiate the Emailing proccess
		MonitoringMail mail = new MonitoringMail();

		String messageBody = "http://" + InetAddress.getLocalHost().getHostAddress()
				+ ":8080/job/DataDrivenLiveProject/Extent_Reports/";
		System.out.println(messageBody);
		
		mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);

	}

}
