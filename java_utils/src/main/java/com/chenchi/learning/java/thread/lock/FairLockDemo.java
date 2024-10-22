package com.chenchi.learning.java.thread.lock;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock有两个构造方法，默认的构造方法中，sync = new NonfairSync();
 * 我们可以从字面意思看出它是一个非公平锁。再看看第二个构造方法，它需要传入一个参数，参数是一个布尔型，true是公平锁，false是非公平锁。
 * 从上面的源码我们可以看出sync有两个实现类，分别是FairSync和NonfairSync，我们再看看获取锁的核心方法，首先是公平锁FairSync的，
 * 通过对比两个方法，我们可以看出唯一的不同之处在于!hasQueuedPredecessors()这个方法，
 * 很明显这个方法是一个队列，由此可以推断，公平锁是将所有的线程放在一个队列中，一个线程执行完成后，从队列中取出下一个线程，
 * 而非公平锁则没有这个队列。这些都是公平锁与非公平锁底层的实现原理，
 * 我们在使用的时候不用追到这么深层次的代码，只需要了解公平锁与非公平锁的含义，并且在调用构造方法时，传入true和false即可。
 * ————————————————
 * 版权声明：本文为CSDN博主「柯柯不会Java」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/qq_46601365/article/details/130188958
 */
public class FairLockDemo {
   static ReentrantLock noFairLock = new ReentrantLock(false);//默认 非公平锁
   static ReentrantLock fairLock = new ReentrantLock(true); //公平锁

    public static void main(String[] args) throws InterruptedException {
        System.out.println("========公平锁===========");
        testLock(fairLock);
        Thread.sleep(1000);
        System.out.println("========非公平锁===========");
        testLock(noFairLock);
    }

    private static void testLock(ReentrantLock lock){
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                for (int j = 0; j < 2; j++) {
                    lock.lock();
                    System.out.println("当前线程：" + Thread.currentThread().getName()+"获取锁");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.unlock();
                }
            }).start();
        }
    }

    @Test
    public void aa(){
        System.out.println(-53680912);
    }
}
