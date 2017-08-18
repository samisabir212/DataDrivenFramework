package com.w2a.rough;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by sami on 8/16/17.
 */
public class printLinkToExtentReport {


    public static void main(String[] args) throws UnknownHostException {


        String messageBody = "http://" + InetAddress.getLocalHost().getHostAddress()
                + ":8080/job/DataDrivenFramework/EXTENT_Reports/";
        System.out.println(messageBody);


    }



}
