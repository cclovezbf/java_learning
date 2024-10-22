package com.chenchi.learning.java.thread.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 悲观锁就是读取的时候就觉得数据就会改变，所以我加个锁 直接不让你读了
 * 悲观锁与乐观锁恰恰相反，悲观锁从读取数据的时候就显示的加锁，直到数据更新完成，释放锁为止。
 * 在这期间只能有一个线程去操作，其他的线程只能等待。
 * 在JAVA中，悲观锁可以使用synchronized关键字或者ReentrantLock类来实现。还是上面的例子，我们分别使用这两种方式来实现一下。首先是使用synchronized关键字来实现：
 */
public class BeiGuanLock {

    private int i = 0;

    // 添加了ReentrantLock锁
    Lock lock = new ReentrantLock();

    public static void main(String[] args) {
//     sync();
     reentrantLock();
    }

    private static void sync(){
        BeiGuanLock test = new BeiGuanLock();
        ExecutorService es = Executors.newFixedThreadPool(50);
        CountDownLatch cdl = new CountDownLatch(5000);
        for (int i = 0; i < 5000; i++) {
            es.execute(() -> {
                //修改部分  开始
                synchronized (test) {
                    //我们唯一的改动就是增加了synchronized块，它锁住的对象是test，在所有线程中，谁获得了test对象的锁，
                    // 谁才能执行i++操作。我们使用了synchronized悲观锁的方式，使得i++线程安全。我们运行一下，看看结果如何。
                    test.i++;
                }
                //修改部分  结束
                cdl.countDown();
            });
        }
        es.shutdown();
        try {
            cdl.await();
            System.out.println("执行完成后，i=" + test.i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static void reentrantLock(){
        BeiGuanLock test = new BeiGuanLock();
            ExecutorService es = Executors.newFixedThreadPool(50);
            CountDownLatch cdl = new CountDownLatch(5000);
            for (int i = 0; i < 5000; i++){
                es.execute(()->{
                    //修改部分  开始
                    test.lock.lock();
                    test.i++;
                    test.lock.unlock();
                    //修改部分  结束
                    cdl.countDown();
                });
            }
            es.shutdown();
            try {
                cdl.await();
                System.out.println("执行完成后，i="+test.i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
