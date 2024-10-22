package com.chenchi.learning.java.refrence;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * https://www.yisu.com/zixun/464795.html
 */
public class WeakReferenceTest {

    static Map<String,WeakReference<String>> map;

    public static void main(String[] args) throws InterruptedException {
        WeakReferenceTest weakReference=new WeakReferenceTest();
        weakReference.doTest();
        System.out.println("发生GC前,map.size="+map.size()+",content=["+map.entrySet().iterator().next().getKey()+"="+map.entrySet().iterator().next().getValue().get()+"]");
        System.out.println("start GC");
        System.gc();
        Thread.sleep(1000*5);
        WeakReference<String> value = map.entrySet().iterator().next().getValue();
        String valueStr=value==null?null:value.get();
        System.out.println("发生GC后,map.size="+map.size()+",content=["+map.entrySet().iterator().next().getKey()+"="+valueStr+"]");
    }

    private  void  doTest(){
        map=new HashMap<>();
        String key=new String("key");
        String value=new String("value");
        map.put(key,new WeakReference<>(value));
        key=null;
        value=null;
    }
    private  void  doTest2(){
        map=new WeakHashMap<>();
        ReferenceQueue<String> stringReferenceQueue = new ReferenceQueue<>();
        String key=new String("key");
        String value=new String("value");
        map.put(key,new WeakReference<>(value,stringReferenceQueue));
        key=null;
        value=null;
        System.out.println(stringReferenceQueue.poll());
    }

}
