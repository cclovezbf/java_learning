package com.chenchi.learning.java.thread.pool;

import cn.hutool.core.io.FileUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DaemonTest {

    public static void main(String[] args) throws InterruptedException {
        DaemonTest daemonTest = new DaemonTest();
        daemonTest.testDaemonWriteFile();
    }
    public void testDaemon1() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("----睡眠一秒-----");
            }
        });
        //默认为false,设置为false代表非守护线程,true为守护线程,守护线程在主方法结束时候结束
        thread.setDaemon(false);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程over");
    }
    public void testDaemonWriteFile() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutorFalse = new ThreadPoolExecutor(
                10,
                20,
                20, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(1000),
                new ThreadFactoryBuilder().setDaemon(false).build()
                //注意我这里核心2 最大是4
        );
        ThreadPoolExecutor threadPoolExecutorTrue = new ThreadPoolExecutor(
                10,
                20,
                20, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(1000),
                new ThreadFactoryBuilder().setDaemon(true).build()
                //注意我这里核心2 最大是4
        );
        Runnable daemonFalse = () -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            FileUtil.appendUtf8Lines(Collections.singleton("threadFactoryDaemonFalse"),
                    "D:\\install\\code\\learning\\bigdata_learining\\output\\java\\testDaemon.txt");
        };
        Runnable daemonTrue = () -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            FileUtil.appendUtf8Lines(
                    Collections.singleton("threadFactoryDaemonTrue"),
                    "D:\\install\\code\\learning\\bigdata_learining\\output\\java\\testDaemon.txt");
        };
        for (int i = -1; i < 10; i++) {
            int finalI = i;
            if (i % 2 == 0) {
                threadPoolExecutorFalse.submit(daemonFalse);
            } else {
                threadPoolExecutorTrue.submit(daemonTrue);
            }
        }

    }
}
