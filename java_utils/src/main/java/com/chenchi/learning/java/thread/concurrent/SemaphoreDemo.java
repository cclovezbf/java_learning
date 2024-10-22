package com.chenchi.learning.java.thread.concurrent;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

//        原文链接：https://blog.csdn.net/weixin_43847283/article/details/128517491
public class SemaphoreDemo {
//    //参数permits表示许可数目，即同时可以允许多少线程进行访问
//    public Semaphore(int permits) {
//        sync = new NonfairSync(permits);
//    }
//
//    //这个多了一个参数fair表示是否是公平的，即等待时间越久的越先获取许可
//    public Semaphore(int permits, boolean fair) {
//        sync = (fair)? new FairSync(permits) : new NonfairSync(permits);
//    }
//public void acquire() throws InterruptedException {  }     //获取一个许可
//    public void acquire(int permits) throws InterruptedException { }    //获取permits个许可
//    public void release() { }          //释放一个许可
//    public void release(int permits) { }    //释放permits个许可
//acquire()用来获取一个许可，若无许可能够获得，则会一直等待，直到获得许可。
//    release()用来释放许可。注意，在释放许可之前，要保证先获得许可。否则会导致总的许可数目就会错误
//    如果想立即得到执行结果，可以使用下面几个方法：
//public boolean tryAcquire() { };    //尝试获取一个许可，若获取成功，则立即返回true，若获取失败，则立即返回false
//    public boolean tryAcquire(long timeout, TimeUnit unit) throws InterruptedException { };  //尝试获取一个许可，若在指定的时间内获取成功，则立即返回true，否则则立即返回false
//    public boolean tryAcquire(int permits) { }; //尝试获取permits个许可，若获取成功，则立即返回true，若获取失败，则立即返回false
//    public boolean tryAcquire(int permits, long timeout, TimeUnit unit) throws InterruptedException { }; //尝试获取permits个许可，若在指定的时间内获取成功，则立即返回true，否则则立即返回false
//public int availablePermits();  // 返回此信号量中可用的许可证的当前数量。
//semaphore.drainPermits(); 这个回把所有许可排空
//semaphore.availablePermits();
//    三、Semaphore的常见应用场景：
//    有限资源的并发控制：Semaphore可以限制对有限资源的并发访问，例如数据库连接池或线程池中的资源管理。
//    控制并发线程数：Semaphore可以控制同时执行的线程数量，例如限制同时访问某个接口的请求数量。
//    实现互斥锁：Semaphore可以用于实现互斥锁的功能，通过设置permits为1，保证同一时间只有一个线程可以访问临界区。
//    控制任务流量：Semaphore可以限制任务的执行速率，例如限制某个任务在单位时间内的执行次数。

    //这个用于控制数量，比如抢车位，
@Test
    public void demo1() throws InterruptedException {
        // 停车场车位数量
        Semaphore semaphore = new Semaphore(5);
        for (int i = 1; i <= 10; i++) {
            Thread thread = new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "进入停车场,还剩"+semaphore.availablePermits()+"个停车位");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + "驶离停车场,还剩"+semaphore.availablePermits()+"个停车位");
                }
            }, i + "号车");
            thread.start();
        }
        TimeUnit.SECONDS.sleep(10000);
    }
}
