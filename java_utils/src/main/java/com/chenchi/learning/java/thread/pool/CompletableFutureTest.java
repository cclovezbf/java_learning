package com.chenchi.learning.java.thread.pool;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CompletableFutureTest {
    public static void main(String[] args) {
        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程结果是"+Thread.currentThread().getName());
            }
        });
    }
    @Test
    public void testSupplyAsync() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(getNowTime() + Thread.currentThread() + " task start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //异常会在调用CompletableFuture.get()时重新抛出
            if (false) {
                throw new RuntimeException("some error");
            }
            System.out.println(getNowTime() + Thread.currentThread() + " task end");
            return 100;
        });
        System.out.println(getNowTime() + Thread.currentThread() + "main thread go on!");
        //阻塞等待任务执行完成,如果已完成则直接返回结果;如果执行任务异常,则get方法会重新抛出捕获的异常
        System.out.println("task result:" + future.get());
        System.out.println(getNowTime() + Thread.currentThread() + "main thread over!");
    }
    @Test
    public void testRunAsync() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(new MyRunnable());
        System.out.println(getNowTime() + Thread.currentThread() + "main thread go on!");
        //没有返回值的情况下返回null
        System.out.println("task result:" + future.get());
        System.out.println(getNowTime() + Thread.currentThread() + "main thread over!");
    }

    @Test
    public void testThenRunAndThenAccept() throws Exception {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            //执行有返回值的异步任务
            System.out.println(getNowTime() + Thread.currentThread() + " task1 start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //异常会在调用CompletableFuture.get()时重新抛出
            if (false) {
                throw new RuntimeException("some error");
            }
            System.out.println(getNowTime() + Thread.currentThread() + " task1 end");
            return 100;
        });

        CompletableFuture<Void> future2 = future1.thenAccept(res1 -> {
            //上一个任务的执行结果作为入参，无返回值
            System.out.println(getNowTime() + Thread.currentThread() + " task2 start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("res1=" + res1);
            System.out.println(getNowTime() + Thread.currentThread() + " task2 end");
        });

        CompletableFuture<Void> future3 = future2.thenRun(() -> {
            //无入参，也无返回值
            System.out.println(getNowTime() + Thread.currentThread() + " task3 start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getNowTime() + Thread.currentThread() + " task3 end");
        });
        Thread.sleep(200);
        System.out.println(getNowTime() + Thread.currentThread() + "main thread go on,wait task1!");
        //阻塞等待任务执行完成,如果已完成则直接返回结果;如果执行任务异常,则get方法会重新抛出捕获的异常
        System.out.println("task1 result:" + future1.get());
        System.out.println(getNowTime() + Thread.currentThread() + "main thread go on,wait task2!");
        System.out.println("task2 result:" + future2.get());
        System.out.println(getNowTime() + Thread.currentThread() + "main thread go on,wait task3!");
        System.out.println("task3 result:" + future3.get());
        System.out.println(getNowTime() + Thread.currentThread() + "main thread over!");
    }


    private static String getNowTime(){
        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        return format;
    }
    static class MyConsumer implements Consumer<Integer>{

        @Override
        public void accept(Integer integer) {

        }
    }

    static class MySupplier implements Supplier<Integer>{
        int id=1;

        public MySupplier(int id) {
            this.id = id;
        }

        public MySupplier() {
        }
        @Override
        public Integer get() {
            //执行有返回值的异步任务
            System.out.println(getNowTime() + Thread.currentThread() + " task"+id+" start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //异常会在调用CompletableFuture.get()时重新抛出
            if (false) {
                throw new RuntimeException("some error");
            }
            System.out.println(getNowTime() + Thread.currentThread() + " task"+id+" end");
            return 100;
        }
    }

    static class MyRunnable implements Runnable{
        int id=1;

        public MyRunnable(int id) {
            this.id = id;
        }

        public MyRunnable() {
        }

        @Override
        public void run() {
            //执行有返回值的异步任务
            System.out.println(getNowTime() + Thread.currentThread() + " task1 start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //异常会在调用CompletableFuture.get()时重新抛出
            if (false) {
                throw new RuntimeException("some error");
            }
            System.out.println(getNowTime() + Thread.currentThread() + " task1 end");
        }
    }
}
