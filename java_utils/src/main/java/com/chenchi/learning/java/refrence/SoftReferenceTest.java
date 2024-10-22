package com.chenchi.learning.java.refrence;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class SoftReferenceTest {
    static class User {
        private int age;
        private String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "age:" + age + ",name:" + name;
        }
    }
    public static void main(String[] args) {
        //创建对象，建立软引用
        SoftReference<User> softReference = new SoftReference<>(new User(1, "张三"));
        //从软引用中重新获得强引用
        System.out.println(softReference.get());

        /**
         * 内存充足是，模拟垃圾回收
         */
        System.gc();
        System.out.println(" 第一次GC后");
        //垃圾回收之后获得软引用对象（如果未获取到，则说明已被回收）
        System.out.println(softReference.get());


        //消耗内存，让JVM认为内存资源紧张
        try {
            //增加7M
            List<byte[]> bytes = new ArrayList<>();
            while (true){
              byte[] b = new byte[1024 * 1024 * 1024];
                bytes.add(b);
          }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //垃圾回收之后获得软引用对象（如果未获取到，则说明已被回收）  内存设置的是10M
            System.out.println("内存资源不足后获取结果：" + softReference.get());
        }

    }
}
