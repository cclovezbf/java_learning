package com.chenchi.learning.java.thread.pool;

import cn.hutool.core.io.FileUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolLearning {
    public static void main(String[] args) throws InterruptedException {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setDaemon(false)
                .setNameFormat("cclovezbf-pool")//
                .setUncaughtExceptionHandler((t, e) -> {
                    System.out.println(t.getName() + "==== error+" + e.getMessage());
                    System.out.println(t.getName() + "==== error+" + e.getCause().getMessage());
                })
//                .setPriority()
                .build();
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool(); //如果工作的太快，会产生线程复用
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10, threadFactory);
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        ExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);
        ThreadPoolLearning learning = new ThreadPoolLearning();
        //三种队列
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<Runnable>(100, false);//默认false
        SynchronousQueue synchronousQueue = new SynchronousQueue<Runnable>(false);
        LinkedBlockingQueue<Runnable> ArrayBlockingQueue = new LinkedBlockingQueue<Runnable>(); //默认无限大
        //四种拒绝策略
        //当触发拒绝策略时，如果线程池未关闭，则直接使用调用者线程，执行任务
        ThreadPoolExecutor.CallerRunsPolicy callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();
        //丢弃任务，并抛出异常信息。必须处理好异常，否则会打断当前执行的流程
        ThreadPoolExecutor.AbortPolicy abortPolicy = new ThreadPoolExecutor.AbortPolicy();
        //从源码中应该能看出来，此拒绝策略是对于当前任务不做任何操作，简单言之：直接丢弃。
        ThreadPoolExecutor.DiscardPolicy discardPolicy = new ThreadPoolExecutor.DiscardPolicy();
        //当触发拒绝策略时，如果线程池未关闭，则丢弃阻塞队列中最老的一个任务，并将新任务加入.
        ThreadPoolExecutor.DiscardOldestPolicy discardOldestPolicy = new ThreadPoolExecutor.DiscardOldestPolicy();
        // learning.testHandlerPolicy(callerRunsPolicy);
        learning.testDiscardPolicy();
    }

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            2,
            4,
            20, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(2),
            new MyThreadFactory("cclovezbf")
            //注意我这里核心2 最大是4
    );

    @Test
    public void testException() throws InterruptedException, ExecutionException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                4,
                20, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(2),
                new MyThreadFactory("cclovezbf")
                //注意我这里核心2 最大是4
        );

        for (int i = -1; i < 3; i++) {
            int finalI = i;
            threadPoolExecutor.submit(() -> {
                System.out.println(1.0 / finalI);
            });
        }
        threadPoolExecutor.awaitTermination(10000, TimeUnit.MILLISECONDS);
    }

    @Test
    public void testExceptionCatch() throws InterruptedException {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("cclovezbf-pool %d")//
                .setUncaughtExceptionHandler((t, e) -> {
                    System.out.println(t.getName() + "==== error+" + e.getMessage());
                    System.out.println(t.getName() + "==== error+" + e.getCause().getMessage());
                })
//                .setPriority()
                .build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                4,
                20, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(2),
                threadFactory
                //注意我这里核心2 最大是4
        );

        for (int i = -1; i < 3; i++) {
            int finalI = i;
            threadPoolExecutor.submit(() -> {
                System.out.println(1.0 / finalI);
            });
        }
        threadPoolExecutor.awaitTermination(10000, TimeUnit.MILLISECONDS);
    }




    @Test
    public void testCorePoolSize() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                4,
                20, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(2),
                new MyThreadFactory("cclovezbf")
                //注意我这里核心2 最大是4
        );
        for (int i = 1; i < 6; i++) {
            System.out.println("开始提交任务" + i);
            threadPoolExecutor.execute(new Task(i));
            Thread.sleep(1); //这里sleep 1 是为了让打印的更加直观
        }
        threadPoolExecutor.shutdown();
        Thread.sleep(10000);
    }

    @Test
    public void testKeepAlive() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1,
                3,
                5, TimeUnit.SECONDS, //只保留5s
                new ArrayBlockingQueue<Runnable>(1),
                new MyThreadFactory("cclovezbf")
        );
        for (int i = 1; i < 5; i++) {
            System.out.println("开始提交任务" + i);
            threadPoolExecutor.execute(new Task(i));
            Thread.sleep(1); //这里sleep 1 是为了让打印的更加直观
        }
        Thread.sleep(6000);
        System.out.println("=====休息一会 等非核心线程被回收=====");
        for (int i = 5; i < 8; i++) {
            System.out.println("开始提交任务" + i);
            threadPoolExecutor.execute(new Task(i));
            Thread.sleep(1); //这里sleep 1 是为了让打印的更加直观
        }
        Thread.sleep(20000);
        threadPoolExecutor.shutdown();
    }

    @Test
    public void testArrayBlockingQueueNoFair() throws InterruptedException {
        System.out.println("测试非公平队列");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                2,
                5, TimeUnit.SECONDS, //只保留5s
                new ArrayBlockingQueue<Runnable>(4, false),
                new MyThreadFactory("cclovezbf")
        );
        for (int i = 1; i <= 6; i++) {
            System.out.println("开始提交任务" + i);
            threadPoolExecutor.execute(new Task(i));
            Thread.sleep(1); //这里sleep 1 是为了让打印的更加直观
        }
        Thread.sleep(20000);
        threadPoolExecutor.shutdown();
    }

    @Test
    public void testArrayBlockingQueueFair() throws InterruptedException {
        System.out.println("测试公平队列");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                2,
                5, TimeUnit.SECONDS, //只保留5s
                new ArrayBlockingQueue<Runnable>(4, true),
                new MyThreadFactory("cclovezbf")
        );
        for (int i = 1; i <= 6; i++) {
            System.out.println("开始提交任务" + i);
            threadPoolExecutor.execute(new Task(i));
            Thread.sleep(1); //这里sleep 1 是为了让打印的更加直观
        }
        Thread.sleep(20000);
        threadPoolExecutor.shutdown();
    }


    private void testRejectHandler(RejectedExecutionHandler policy) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1,
                1,
                5, TimeUnit.SECONDS, //只保留5s
                new ArrayBlockingQueue<Runnable>(1, false),
                new MyThreadFactory("cclovezbf")
        );
        threadPoolExecutor.setRejectedExecutionHandler(policy);
        for (int i = 0; i < 4; i++) {
            threadPoolExecutor.execute(new Task(i));
        }
        threadPoolExecutor.shutdown();
        Thread.sleep(10000);
    }

    @Test
    public void testCallerRunsPolicyy() throws InterruptedException {
        ThreadPoolExecutor.CallerRunsPolicy callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();
        testRejectHandler(callerRunsPolicy);
    }

    @Test
    public void testAbortPolicy() throws InterruptedException {
        ThreadPoolExecutor.AbortPolicy abortPolicy = new ThreadPoolExecutor.AbortPolicy();
        testRejectHandler(abortPolicy);
    }

    @Test
    public void testDiscardPolicy() throws InterruptedException {
        ThreadPoolExecutor.DiscardPolicy discardPolicy = new ThreadPoolExecutor.DiscardPolicy();
        testRejectHandler(discardPolicy);
    }

    // 8号任务直接不见了
    @Test
    public void testDiscardOldestPolicy() throws InterruptedException {
        ThreadPoolExecutor.DiscardOldestPolicy discardOldestPolicy = new ThreadPoolExecutor.DiscardOldestPolicy();
        testRejectHandler(discardOldestPolicy);
    }

    @Test
    public void testMultiThreads() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                10,
                20,
                5, TimeUnit.SECONDS, //只保留5s
                new ArrayBlockingQueue<Runnable>(100000, false),
                new MyThreadFactory("cclovezbf")
        );
        for (int i = 0; i < 20000; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPoolExecutor.shutdown();
        Thread.sleep(20000);
    }

}


class MyThreadFactory implements ThreadFactory {
    private final String name = "thread-cclovezbf-%s";
    private final AtomicInteger nextId = new AtomicInteger(1);

    // 定义线程组名称，在利用 jstack 来排查问题时，非常有帮助
    public MyThreadFactory(String whatFeatureOfGroup) {
    }

    @Override
    public Thread newThread(Runnable task) {
        String threadName = String.format(name, nextId.toString());
        System.out.println("线程" + threadName + "已经被创建--");
        Thread thread = new Thread(task);
        thread.setName(threadName);
        nextId.addAndGet(1);
        return thread;
    }
}

class Task extends Thread {
    private final int num;

    public Task(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " task" + num + "--start--");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " task" + num + "--end--");
    }
}
