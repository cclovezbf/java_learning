package com.chenchi.learning.java.bitset;

import java.util.BitSet;
/**
 * （3）使用BitSet查找某个范围内的所有素数的个数
 * 素数：一个大于1的自然数，如果除了1和它本身外，不能被其他自然数整除(除0以外）的数称为素数(质数） ，否则称为合数。
 *
 * 如下为统计2000000以内的素数的个数：
 */

public class BitSetDemo3 {
    public static void main(String[] s)
    {
        int n = 2000000;
        long start = System.currentTimeMillis();
        BitSet sieve = new BitSet(n+1);
        int count=0;
        //先把所有的数都放到bitset里
        for (int i = 2; i <= n; i++){
            sieve.set(i);
        }

        int finalBit = (int) Math.sqrt(n);//开平方 1414
        for (int i = 2; i < finalBit; i++){
            if (sieve.get(i)){
                for (int j = 2 * i; j < n; j += i){
                    sieve.clear(j);
                }
            }
        }
        int counter = 0;
        for (int i = 1; i < n; i++) {
            if (sieve.get(i)) {
                count++;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(count + " primes");
        System.out.println((end - start) + " ms");
    }
}