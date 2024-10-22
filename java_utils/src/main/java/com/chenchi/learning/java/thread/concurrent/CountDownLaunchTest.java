package com.chenchi.learning.java.thread.concurrent;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 一、什么是countDownlatch
 * CountDownLatch是一个同步工具类，它通过一个计数器来实现的,初始值为线程的数量。每当一个线程完成了自己的任务,计数器的值就相应得减1。当计数器到达0时,表示所有的线程都已执行完毕,然后在等待的线程就可以恢复执行任务。
 * <p>
 * 二、方法详解
 * CountDownLatch(int count)：count为计数器的初始值（一般需要多少个线程执行，count就设为几）。
 * countDown(): 每调用一次计数器值-1，直到count被减为0，代表所有线程全部执行完毕。
 * getCount()：获取当前计数器的值。
 * await(): 等待计数器变为0，即等待所有异步线程执行完毕。
 * boolean await(long timeout, TimeUnit unit)： 此方法与await()区别：
 * ①此方法至多会等待指定的时间，超时后会自动唤醒，若 timeout 小于等于零，则不会等待
 * ②boolean 类型返回值：若计数器变为零了，则返回 true；若指定的等待时间过去了，则返回 false
 * ————————————————
 * 版权声明：本文为CSDN博主「三寸旧城。」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/liu_da_da/article/details/124983187
 */
public class CountDownLaunchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLaunchTest test = new CountDownLaunchTest();
        test.demo1();
    }

    /**
     * 如果不加countDownlatch 你不知道线程池里的任务什么时候完成的？ 你也可以Thread.sleep(100s) 但是你这不是个大春比么
     * 你执行         System.out.println("count.get()="+count.get()); 的时候可能那两个线程才跑了几百条数据
     * @throws InterruptedException
     */
    private void demo1() throws InterruptedException {
        //程序计数器
        //2个线程
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        AtomicInteger count = new AtomicInteger(0);
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                count.getAndIncrement();//自增
                System.out.println(Thread.currentThread().getName() + " : " + count.get());
            });
        }
        System.out.println("count.get()="+count.get());
        //线程池 等待10s
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        //关闭线程 其实是将线程状态设置为中断标志  必须等待所有线程处理完任务,才能完全关闭
        executorService.shutdown();
        //必须等待两个线程执行完   会一直等待下去，当然也可以设置指定时间等待超时 await(timeout);
       ;

    }

    /*
        用法一: 等待所有的事情都做完
     */
    @Test
    public void demo2() throws InterruptedException {
        //程序计数器
        CountDownLatch countDownLatch = new CountDownLatch(10000);
        //2个线程
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        AtomicInteger count = new AtomicInteger(0);
        for (int i = 0; i < 10000; i++) {
            executorService.submit(() -> {
                count.getAndIncrement();//自增
                System.out.println(Thread.currentThread().getName() + " : " + count.get());
                countDownLatch.countDown();
            });
        }


        //线程池 等待10s
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        //关闭线程 其实是将线程状态设置为中断标志  必须等待所有线程处理完任务,才能完全关闭
        executorService.shutdown();

        //必须等待两个线程执行完   会一直等待下去，当然也可以设置指定时间等待超时 await(timeout);
        countDownLatch.await();

    }
}
