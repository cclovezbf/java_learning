package com.chenchi.learning.java.thread.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 错误示范 ，未枷锁
 */
public class Starter {
    public static void main(String[] args) {
        test3();
    }

    /**
     * 不加锁
     */
    private static void test1() {
        Cabinet cabinet = new Cabinet();
        ExecutorService es = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            final int storeNumber = i;
            es.execute(() -> {
                User user = new User(cabinet, storeNumber);
                user.useCabinet();
                System.out.println("我是用户" + storeNumber + ",我存储的数字是：" + cabinet.getStoreNumber());
            });
        }
        es.shutdown();
    }

    /**
     * 我们可以看到在useCabinet和打印的方法是两个语句，并没有保持原子性，
     * 虽然在set方法上加了锁，但是在打印时又存在了一个并发，打印语句是有锁的，但是不能确定哪个线程去执行。
     *
     */
    private static void test2() {
        Cabinet cabinet = new Cabinet();
        ExecutorService es = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            final int storeNumber = i;
            es.execute(() -> {
                User user = new User(cabinet, storeNumber);
                user.useCabinet_sync();
                System.out.println("我是用户" + storeNumber + ",我存储的数字是：" + cabinet.getStoreNumber());
            });
        }
        es.shutdown();
    }


    /**
     *  所以这里，我们要保证useCabinet和打印的方法的原子性，我们使用synchronized块，但是synchronized块里的对象我们使用谁的？
     * 这又是一个问题，user?还是cabinet? 当然是cabinet，因为每个线程都初始化了user，
     * 总共有3个user对象了，而cabinet对象只有一个，所以synchronized要用cabinet对象。如下：
     *  synchronized (cabinet)
     *  synchronized (user)
     *  synchronized (o)
     *  都可以正确打印
     */
    private static void test3() {
        Object o = new Object();
        Cabinet cabinet = new Cabinet();
        ExecutorService es = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            final int storeNumber = i;
            User user = new User(cabinet, storeNumber);
            es.execute(() -> {
                        synchronized (cabinet) {
                            user.useCabinet_sync();
                            System.out.println("我是用户" + storeNumber + ",我存储的数字是：" + cabinet.getStoreNumber());
                        }
                    }
            );
        }
        es.shutdown();
    }
}

/**
 * 我们仔细的看一下这个main函数的过程，
 * <p>
 * 首先创建一个柜子的实例，由于场景中只有一个柜子，所以我们只创建了一个柜子实例。 new Cabinet();
 * 然后我们新建一个线程池，线程池中有3个线程，每个线程执行一个用户的操作。 Executors.newFixedThreadPool(3);
 * 再来看看每个线程具体的执行过程，新建用户实例，传入的是用户使用的柜子，我们这里只有一个柜子，所以传入这个柜子的实例，然后传入这个用户要存储的数字，分别是1，2，3，也分别对应着用户A，用户B，和用户C。
 * 再调用使用柜子的操作，也就是向柜子中放入要存储的数字，然后立刻从柜子中取出数字，并打印出来
 * 我是用户0,我存储的数字是：2
 * 我是用户1,我存储的数字是：1
 * 我是用户2,我存储的数字是：2
 * 这次又变成了1。这是为什么呢？问题就出在user.useCabinet()这个方法上，这是因为柜子这个实例没有加锁的原因，
 * 3个用户并行的执行，向柜子中存储他们的数字，虽然是3个用户并行的同时操作，但是在具体赋值时，也是有顺序的，
 * 因为变量storeNumber只占有一块内存，storeNumber只存储一个值，存储最后的线程所设置的值。
 * 至于哪个线程排在最后，则完全不确定。赋值语句执行完成后，进入到打印语句，打印语句取storeNumber的值并打印，这时storeNumber存储的是最后一个线程所设置的值，
 * 3个线程取到的值是相同的，就像上面打印的结果一样。
 * <p>
 * 那么如何解决这个问题？这就引出了我们本文的重点内容——锁。我们在赋值语句上加锁，这样当多个线程（本文当中的多个用户）同时赋值时，谁抢到了这把锁，谁才能赋值。这样保证同一时刻只能有一个线程进行赋值操作，避免了之前的混乱的情况。
 * ————————————————
 * 版权声明：本文为CSDN博主「柯柯不会Java」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/qq_46601365/article/details/130188958
 */
class Cabinet {
    // 柜子中存储的数字
    private int storeNumber;

    public void setStoreNumber(int storeNumber) {
        this.storeNumber = storeNumber;
    }

    public int getStoreNumber() {
        return this.storeNumber;
    }
}

class User {
    // 柜子
    private Cabinet cabinet;
    // 存储的数字
    private int storeNumber;

    public User(Cabinet cabinet, int storeNumber) {
        this.cabinet = cabinet;
        this.storeNumber = storeNumber;
    }

    // 使用柜子
    public void useCabinet() {
        cabinet.setStoreNumber(storeNumber);
    }

    // 使用柜子
    public synchronized void useCabinet_sync() {
        cabinet.setStoreNumber(storeNumber);
    }

}

