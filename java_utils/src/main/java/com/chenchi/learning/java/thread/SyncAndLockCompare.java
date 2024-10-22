package com.chenchi.learning.java.thread;

import java.util.concurrent.locks.ReentrantLock;

public class SyncAndLockCompare {

    private static final int NUM_THREADS = 10;
    private static final int NUM_INCREMENTS = 1000000;

    private int count1 = 0;
    private int count2 = 0;

    private final ReentrantLock lock = new ReentrantLock();
    private final Object syncLock = new Object();

    public void increment1() {
        lock.lock();
        try {
            count1++;
        } finally {
            lock.unlock();
        }
    }

    public void increment2() {
        synchronized (syncLock) {
            count2++;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        SyncAndLockCompare test = new SyncAndLockCompare();

        // ReentrantLock性能测试
        long startTime = System.nanoTime();
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < NUM_INCREMENTS; j++) {
                    test.increment1();
                }
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        long endTime = System.nanoTime();
        System.out.println("ReentrantLock完成时间: " + (endTime - startTime) + " ns");

        // synchronized性能测试
        startTime = System.nanoTime();
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < NUM_INCREMENTS; j++) {
                    test.increment2();
                }
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        endTime = System.nanoTime();
        System.out.println("synchronized完成时间: " + (endTime - startTime) + " ns");
    }
}
