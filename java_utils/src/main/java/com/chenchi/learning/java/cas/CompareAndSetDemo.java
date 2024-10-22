package com.chenchi.learning.java.cas;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

public class CompareAndSetDemo {
    @Test
    public void test1(){
        AtomicReference<Integer> atomicReference = new AtomicReference<Integer>(10);
        //设置新值
        atomicReference.set(10);
        //如果=v1 就设置为v2
        atomicReference.compareAndSet(10,11);
        System.out.println(atomicReference.get());
        Integer andSet = atomicReference.getAndSet(12);
        System.out.println(andSet);
        System.out.println(atomicReference.get());
    }
}
