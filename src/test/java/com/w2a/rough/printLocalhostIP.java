package com.w2a.rough;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by sami on 8/16/17.
 */
public class printLocalhostIP {


    public static void main(String[] args) throws UnknownHostException {


        System.out.println(InetAddress.getLocalHost().getHostAddress());



    }
}
