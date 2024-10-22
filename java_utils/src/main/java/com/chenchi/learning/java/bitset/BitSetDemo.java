package com.chenchi.learning.java.bitset;

import java.util.BitSet;
public class BitSetDemo {

    public static void main(String[] args) {

        //创建一个具有10000000位的bitset　初始所有位的值为false
        BitSet bitSet = new BitSet(100);
        //将指定位的值设为true
        bitSet.set(99);
        bitSet.set(0,10);
        bitSet.flip(10,20); //反转 原来是1现在变0 原来是0 变1
        bitSet.set(101);
        bitSet.set(9999,true);
        bitSet.clear(15);
        //输出指定位的值
        System.out.println("99:"+bitSet.get(99));
        System.out.println("98:"+bitSet.get(98));
        System.out.println("bitSet.size:"+bitSet.size());
        System.out.println("bitset有多少被设置为true了"+bitSet.cardinality());
        System.out.println( bitSet.toString());

    }
}
