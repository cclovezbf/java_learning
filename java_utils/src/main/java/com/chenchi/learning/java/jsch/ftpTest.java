//package com.chenchi.learning.java.jsch;
//
//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.net.ftp.FTPFile;
//
//import java.io.IOException;
//import java.util.Arrays;
//
//public class ftpTest {
//    public static void main(String[] args) throws IOException {
//        FTPClient ftpClient =  new FTPClient();
//        ftpClient.connect("9.134.70.1",21);
//        ftpClient.login("devuser","S2_fin_bigdata&123");
//        ftpClient.enterLocalActiveMode();
//        int reply = ftpClient.getReplyCode();
//        System.out.println(reply);
//        FTPFile[] ftpFiles = ftpClient.listFiles("/");
//        Arrays.stream(ftpFiles).forEach(System.out::println);
//    }
//}
