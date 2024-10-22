package com.chenchi.learning.java.md5;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Test {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        getDffBetweenLongAndString();
    }

    private static void getDffBetweenLongAndString(){
     String s1 ="2";
        byte[] bytes1 = s1.getBytes(StandardCharsets.UTF_8);
        for (byte b : bytes1) {
            System.out.print(b);
        }
        System.out.println();
     long l1 = 9L;
        byte[] bytes = toBytes(l1);
        for (byte aByte : bytes) {
            System.out.print(aByte);
        }

    }

    private static void getlengthBetweenLongAndString() throws NoSuchAlgorithmException {
        long l = 1234567890L;
        byte[] lBytes = toBytes(l);
        System.out.println("long bytes length: " + lBytes.length);   // returns 8

        String s = String.valueOf(l);
        byte[] sBytes = s.getBytes();
        System.out.println("long as string length: " + sBytes.length);    // returns 10

// hash
//
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(sBytes);
        System.out.println("md5 digest "+new String(digest)+"bytes length: " + digest.length);    // returns 16

        String sDigest = new String(digest);
        byte[] sbDigest =sDigest.getBytes();
        System.out.println("md5 digest as "+new String(sbDigest)+"string length: " + sbDigest.length);    // returns 26
    }

    public static byte[] toBytes(long val) {
        byte [] b = new byte[8];
        for (int i = 7; i > 0; i--) {
            //强制转型，后留下长整形的低8位
            b[i] = (byte) val;
            String str = Long.toBinaryString( val) ;
            String lb = Long.toBinaryString( b[i] ) ;
            String lb2 = Long.toBinaryString( b[i]&0xff ) ;
//            System.out.println( lb );
//            System.out.println( lb2 );
            //向右移动8位，则第二次循环则计算第二个8位数
            val >>>= 8;
        }
        b[0] = (byte) val;
        return b;
    }

}
