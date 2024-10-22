package com.chenchi.learning.java.refrence;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class ReferenceQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        ReferenceQueueDemo demo=new ReferenceQueueDemo();
        demo.testWithoutGC();
//        demo.testBigObjectWithoutGC();
//        demo.testBigObjectWithoutGC();
    }
    private void testWithoutGC() throws InterruptedException {
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        Thread thread = new Thread(() -> {
            try {
                int cnt = 0;
                WeakReference<String> k;
                while ((k = (WeakReference) referenceQueue.remove()) != null) {
                    System.out.println((cnt++) + "回收了:" + k);
                }
            } catch (InterruptedException e) {
                // 结束循环
            }
        });
        thread.setDaemon(true);
        thread.start();

        Object value = new Object();
        Map<Object, Object> map = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            WeakReference<String> weakReference = new WeakReference<String>(String.valueOf(i), referenceQueue);
            map.put(weakReference, value);
        }
      //  System.gc();
        System.out.println("map.size->" + map.size());

    }

    private void testBigObjectWithoutGC() throws InterruptedException {
        int _1M = 1024 * 1024;

        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        Thread thread = new Thread(() -> {
            try {
                int cnt = 0;
                WeakReference<byte[]> k;
                while ((k = (WeakReference) referenceQueue.remove()) != null) {
                    System.out.println((cnt++) + "回收了:" + k);
                }
            } catch (InterruptedException e) {
                // 结束循环
            }
        });
        thread.setDaemon(true);
        thread.start();

        Object value = new Object();
        Map<Object, Object> map = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            byte[] bytes = new byte[_1M];
            WeakReference<byte[]> weakReference = new WeakReference<byte[]>(bytes, referenceQueue);
            map.put(weakReference, value);
        }
        System.out.println("map.size->" + map.size());
    }
    private void test2() throws InterruptedException {
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        Object value = new Object();
        Map<Object, Object> map = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            WeakReference<String> weakReference = new WeakReference<String>(String.valueOf(i), referenceQueue);
            System.out.println("创造了:"+weakReference+",value="+weakReference.get());
            map.put(weakReference, value);
        }
        System.gc();
        Thread.sleep(100);
        WeakReference<String> result=null;
        int cnt = 0;
        while ((result=(WeakReference)referenceQueue.poll())!=null){
            System.out.println((cnt++) + "回收了:" + result+",value="+result.get());
        }
    }


    private void test3() throws InterruptedException {
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        Object value = new Object();
        Map<Object, Object> map = new HashMap<>();
        for (int i = 0; i < 500; i++) {
            WeakReference<String> weakReference = new WeakReference<String>(String.valueOf(i), referenceQueue);
            System.out.println("创造了:"+weakReference+",value="+weakReference.get());
            map.put(weakReference, value);
        }
        WeakReference<String> result=null;
        int cnt = 0;
        while ((result=(WeakReference)referenceQueue.poll())!=null){
            System.out.println((cnt++) + "回收了:" + result+",value="+result.get());
        }
    }


}
