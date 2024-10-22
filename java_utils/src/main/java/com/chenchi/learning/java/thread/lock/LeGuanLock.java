package com.chenchi.learning.java.thread.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 乐观锁呢，它是假设一个线程在取数据的时候不会被其他线程更改数据，就像上面的例子那样，但是在更新数据的时候会校验数据有没有被修改过。
 * 它是一种比较交换的机制，简称CAS（Compare And Swap）机制。一旦检测到有冲突产生，也就是上面说到的版本号或者最后更新时间不一致，它就会进行重试，直到没有冲突为止。
 * 个人理解就是乐观锁读取的时候觉得所有的数据都是可以的。但是更新的时候就会需要判断数据是否改变过
 * 执行完成后，i=4975
 * 执行完成后，i=4986
 * 执行完成后，i=4971
 *这是我们运行3次以后得到的结果，可以看到每次执行的结果都不一样，而且不是5000，
 * 这是为什么呢？这就说明i++并不是一个原子性的操作，在多线程的情况下并不安全。我们把i++的详细执行步骤拆解一下：
 *
 * 从内存中取出i的当前值；
 * 将i的值加1；
 * 将计算好的值放入到内存当中；
 *
 * 我们将变量i的类型改为AtomicInteger，AtomicInteger是一个原子类。
 * 我们在之前调用i++的地方改成了i.incrementAndGet()，
 * incrementAndGet()方法采用了CAS机制，也就是说使用了乐观锁。我们再运行一下程序，看看结果如何。
 */
public class LeGuanLock {

    private int i=0;

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
//        test1();
        test2();
    }
    private static void test1(){
        LeGuanLock test = new LeGuanLock();
        // 线程池：50个线程
        ExecutorService es = Executors.newFixedThreadPool(50);
        // 闭锁
        CountDownLatch cdl = new CountDownLatch(5000);
        for (int i = 0; i < 5000; i++){
            es.execute(()->{
                test.i++;
                cdl.countDown();
            });
        }
        es.shutdown();
        try {
            // 等待5000个任务执行完成后，打印出执行结果
            cdl.await();
            System.out.println("执行完成后，i="+test.i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void test2(){
        LeGuanLock test = new LeGuanLock();
        // 线程池：50个线程
        ExecutorService es = Executors.newFixedThreadPool(50);
        // 闭锁
        CountDownLatch cdl = new CountDownLatch(5000);
        for (int i = 0; i < 5000; i++){
            es.execute(()->{
                test.atomicInteger.incrementAndGet();
                cdl.countDown();
            });
        }
        es.shutdown();
        try {
            // 等待5000个任务执行完成后，打印出执行结果
            cdl.await();
            System.out.println("执行完成后，atomicInteger="+test.atomicInteger.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
