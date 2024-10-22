package com.chenchi.learning.java.thread.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorsTest {
    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setDaemon(true)
                .setNameFormat("cclovezbf")
                .build();
        ThreadFactory threadFactory2 = new MyThreadFactory("groupCC"); //这个名字是group
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool(); //如果工作的太快，会产生线程复用
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 11; i++) {
            fixedThreadPool.execute(new Task(i));
        }
        fixedThreadPool.shutdown();
//        CompletionService<Object> service = new ExecutorCompletionService<Object>(cachedThreadPool );
//        Future<Object> submit = service.submit();
//        submit.get();
    }

    static class Task extends Thread{
        private int num;

        public Task(int num){
            this.num=num;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"--start--");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"--end--");
        }
    }

    static  class MyThreadFactory implements ThreadFactory{

        private final String name = "thread-cclovezbf-%s";
        private final AtomicInteger nextId = new AtomicInteger(1);

        // 定义线程组名称，在利用 jstack 来排查问题时，非常有帮助
        public MyThreadFactory(String whatFeatureOfGroup) {
        }

        @Override
        public Thread newThread(Runnable task) {
            String threadName = String.format(name, nextId.toString());
            Thread thread = new Thread(task);
            thread.setName(threadName);
            nextId.addAndGet(1);
            return thread;
        }
    }
}
