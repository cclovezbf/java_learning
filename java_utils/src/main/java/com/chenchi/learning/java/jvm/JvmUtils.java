package com.chenchi.learning.java.jvm;

import java.util.ArrayList;

/**
 * vmOptions
 * -Xms10m -Xmx10m
 * 最小    最大
 * -XX:HeapDumpOnOutOfMemoryError
 */
public class JvmUtils {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Object> objects = new ArrayList<>(100);
        while (true){
            Byte[] bytes = new Byte[100];
            objects.add(bytes);
        }
    }
}
