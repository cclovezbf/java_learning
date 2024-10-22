package com.chenchi.learning.java;

import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class EncoderDemo {
    @Test
    public void test(){
        String name="陈池zbf";
        byte[] utf8Bytes = name.getBytes(StandardCharsets.UTF_8);
        byte[] gbkBytes = name.getBytes(Charset.forName("gbk"));
        for (int i = 0; i < utf8Bytes.length; i++) {
            System.out.print(utf8Bytes[i]+" ");
        }
        System.out.println();
        for (int i = 0; i < gbkBytes.length; i++) {
            System.out.print(gbkBytes[i]+" ");
        }
    }
}
