package com.chenchi.learning.java.thread.concurrent;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.*;

public class CountDownLauntAndCyclicBarrierDiff {

    @Test
    public void testCount() throws InterruptedException {
        ArrayList<String> list = Lists.newArrayList("cpu", "显示器", "主板", "电源", "机箱", "鼠标", "键盘");
        int size = list.size();
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        //程序计数器
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            int finalI = i;
            executorService.submit(() -> {
                System.out.println("快递【" + list.get(finalI) + "】已送达");
                countDownLatch.countDown();
                long count = countDownLatch.getCount();
                System.out.println("快递总共有" + size + "个,目前还剩下" + count + "个未到达");
            });
            Thread.sleep(10);
        }
        Thread.sleep(1000);
        countDownLatch.await();
        System.out.println("快递已经到达完毕,开始组装玩游戏");
        //线程池 等待10s
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        //关闭线程 其实是将线程状态设置为中断标志  必须等待所有线程处理完任务,才能完全关闭
        executorService.shutdown();
    }

    /**
     * 所有人都等，人到齐后 继续工作
     *
     * @throws InterruptedException
     */
    @Test
    public void testCyclicBarrier() throws InterruptedException {
        ArrayList<String> list = Lists.newArrayList("user1", "user2", "user3", "user4", "user5", "user6", "user7", "user8", "user9", "user10");
        int size = list.size();
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(size, () -> {
            System.out.println("所有人加载完毕,欢迎进入游戏！");
        });
        for (int i = 0; i < list.size(); i++) {
            int finalI = i;
            executorService.submit(
                    new Thread(() -> {
                        try {
                            System.out.println("用户【" + list.get(finalI) + "】选完英雄,进入加载界面,等待其他人加载.....");
                            cyclicBarrier.await();  // 等待其他线程到齐，到齐了之后就会执行一次
                            System.out.println("用户【" + list.get(finalI) + "】加载游戏完成，进入游戏，开始游戏");
                        } catch( Exception e) {
                            e.printStackTrace();
                        }
                    }));
        }
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        executorService.shutdown();
    }
    @Test
    public void testCyclicBarrier2() throws InterruptedException {
        ArrayList<String> list = Lists.newArrayList("user1", "user2", "user3", "user4", "user5", "user6", "user7", "user8", "user9", "user10");
        int size = list.size();
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(size, () -> {
            System.out.println("所有人加载完毕,欢迎进入游戏！");
        });
        for (int i = 0; i < list.size(); i++) {
            int finalI = i;
            executorService.submit(
                    new Thread(() -> {
                        try {

                            if (finalI==9){
                                System.out.println("用户【" + list.get(finalI) + "】选完英雄,进入加载界面,哎呀我卡了！！！！！！！！！！");
                                Thread.sleep(5*1000);
                            }else {
                                System.out.println("用户【" + list.get(finalI) + "】选完英雄,进入加载界面,等待其他人加载.....");
                            }
                            cyclicBarrier.await(10*1000,TimeUnit.MILLISECONDS);  // 等待其他线程到齐，到齐了之后就会执行一次
                            System.out.println("用户【" + list.get(finalI) + "】加载游戏完成，进入游戏，开始游戏");
                        } catch( Exception e) {
                            e.printStackTrace();
                        }
                    }));
        }
        executorService.awaitTermination(20, TimeUnit.SECONDS);
        executorService.shutdown();
    }
}
