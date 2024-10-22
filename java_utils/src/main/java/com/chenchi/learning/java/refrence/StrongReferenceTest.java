package com.chenchi.learning.java.refrence;

import java.util.HashMap;
import java.util.Map;

public class StrongReferenceTest {

    static Map<String,String> map;

    public static void main(String[] args) throws InterruptedException {
        StrongReferenceTest strongReference=new StrongReferenceTest();
        strongReference.doTest();
        System.out.println("发生GC前,map.size="+map.size()+",content="+map.entrySet());
        System.out.println("start GC");
        System.gc();
        Thread.sleep(1000*10);
        System.out.println("发生GC后,map.size="+map.size()+",content="+map.entrySet());
    }

    private  void  doTest(){
        map=new HashMap<>();
        String key=new String("key");
        String value=new String("value");
        map.put(key,value);
        key=null;
        value=null;
    }}
