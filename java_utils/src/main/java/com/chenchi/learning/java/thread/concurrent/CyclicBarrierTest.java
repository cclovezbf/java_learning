package com.chenchi.learning.java.thread.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    public static void main(String[] args) throws InterruptedException {
        // 初始化一个 CyclicBarrier，大小为 5，当 5 个线程满足条件之后，就执行一次：执行完毕！
        CyclicBarrier cyclicBarrier = new CyclicBarrier( 5, () -> {
            System.out.println("执行完毕！");
        });
        // 为了测试是否能循环，这里循环设置为 20，预期的结果应该是会执行 4 次：执行完毕！
        // 为了测试是否能循环，这里循环设置为 21，预期的结果应该是会执行 4 次：然后最后一个任务一直等待 其他4各任务
        for (int i=0; i<20; i++) {
            int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + ", 执行到：" + temp );
                try{
                    System.out.println("现在有["+cyclicBarrier.getNumberWaiting()+"]个人在等 ");
                    cyclicBarrier.await();  // 等待其他线程到齐，到齐了之后就会执行一次
                } catch (BrokenBarrierException | InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            Thread.sleep(500);
        }
    }
}