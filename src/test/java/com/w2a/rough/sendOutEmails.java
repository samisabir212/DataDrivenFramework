package com.w2a.rough;

import com.w2a.utilities.MonitoringMail;
import com.w2a.utilities.TestConfig;

import javax.mail.MessagingException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by sami on 8/17/17.
 */
public class sendOutEmails {





    public static void main(String[] args) throws UnknownHostException, MessagingException {


        //class to send out emails
        MonitoringMail mail = new MonitoringMail();
        String messageBody = "http://" + InetAddress.getLocalHost().getHostAddress()
                + ":8080/job/DataDrivenFramework/EXTENT_Reports/";
        System.out.println(messageBody);

        mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);



    }

}

