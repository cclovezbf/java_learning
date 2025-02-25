package com.chenchi.learning.java.cache.cacheline;
import java.util.concurrent.CountDownLatch;

public class NoCacheLineFill {

    public volatile long x = 1L;
}

class MainDemo {

    public static void main(String[] args) throws InterruptedException {
        // CountDownLatch是在java1.5被引入的，它是通过一个计数器来实现的，计数器的初始值为线程的数量。
        // 每当一个线程完成了自己的任务后，调用countDown方法，计数器的值就会减1。
        // 当计数器值到达0时，它表示所有的线程已经完成了任务，然后调用await的线程就可以恢复执行任务了。
        CountDownLatch countDownLatch = new CountDownLatch(2);

        NoCacheLineFill[] arr = new NoCacheLineFill[2];
        arr[0] = new NoCacheLineFill();
        arr[1] = new NoCacheLineFill();

        Thread threadA = new Thread(() -> {
            for (long i = 0; i < 1_000_000_000L; i++) {
                arr[0].x = i;
            }
            countDownLatch.countDown();
        }, "ThreadA");

        Thread threadB = new Thread(() -> {
            for (long i = 0; i < 1_000_000_000L; i++) {
                arr[1].x = i;
            }
            countDownLatch.countDown();
        }, "ThreadB");

        final long start = System.nanoTime();
        threadA.start();
        threadB.start();
        //等待线程A、B执行完毕
        countDownLatch.await();
        final long end = System.nanoTime();
        System.out.println("耗时：" + (end - start) / 1_000_000 + "毫秒");

    }
}
