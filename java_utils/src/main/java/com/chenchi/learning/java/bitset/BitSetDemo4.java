package com.chenchi.learning.java.bitset;
import  java.util.BitSet;
/**
 （4）使用BitSet进行排序
 如统计40亿个数据中没有出现的数据，将40亿个不同数据进行排序等。

 统计个数和上面两个类似，就不说了。使用BitSet对数据进行排序的代码如下：
 */

public class BitSetDemo4 {

    public static void main(String[] args) {

        int[] array = new int[] { 423, 700, 9999, 2323, 356, 6400, 1,2,3,2,2,2,2 };
        BitSet bitSet = new BitSet();
        //BitSet默认初始大小为64
        System.out.println("BitSet size: " + bitSet.size());

        for (int i = 0; i < array.length; i++) {
            bitSet.set(array[i]);
        }
        //public int cardinality()方法返回此BitSet中比特设置为true的数目
        //就是BitSet中存放的有效位的个数，如果有重复运算会进行自动去重
        int bitLen=bitSet.cardinality();
        System.out.println("bitLen = "+bitLen);
        System.out.println("Before ordering："+bitSet);

        //进行排序，即把bit为true的元素复制到另一个数组
        int[] orderedArray = new int[bitLen];
        int k = 0;
        //nextSetBit(int fromIndex)方法返回fromIndex之后的下一个值为true的索引
        //此循环就是按照顺序循环取出在BitSet存在的数，以此达到排序的目的
        for (int i = bitSet.nextSetBit(0); i >= 0; i = bitSet.nextSetBit(i + 1)) {
            orderedArray[k++] = i;
        }

        System.out.println("After ordering：");
        for (int i = 0; i < bitLen; i++) {
            System.out.print(orderedArray[i] + "\t");
        }
        System.out.println();
        //或者顺序访问的数据不放入数组而进行直接读取
        System.out.println("iterate over the true bits in a BitSet");
        //或直接迭代BitSet中bit为true的元素
        for (int i = bitSet.nextSetBit(0); i >= 0; i = bitSet.nextSetBit(i + 1)) {
            System.out.print(i+"\t");
        }

    }
}