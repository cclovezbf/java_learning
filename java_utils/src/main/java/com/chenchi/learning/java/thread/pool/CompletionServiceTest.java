package com.chenchi.learning.java.thread.pool;

import java.util.concurrent.*;

public class CompletionServiceTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletionService<Integer> completionService=new ExecutorCompletionService<Integer>(executorService);
        for (int i = 0; i < 10; i++) {
            completionService.submit(new MyTask(i));
        }
        for (int i = 0; i < 11; i++) {
//            Future<Integer> result = completionService.take();
//            System.out.println("线程结果是"+result.get()); //会一直阻塞
//            System.out.println("线程结果是"+result.get(1,TimeUnit.SECONDS));
//            Future<Integer> result = completionService.poll();//而且任务还没完成就poll 会返回null 后面get的时候会空指针
            Future<Integer> result = completionService.poll(2000,TimeUnit.SECONDS);
            System.out.println("线程结果是"+result.get()); //多的会报错 ，
        }
        executorService.shutdown();
    }


    static class MyTask implements Callable<Integer>{
        Integer index;

        public MyTask(Integer index) {
            this.index = index;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println(Thread.currentThread().getName() + " task" + index + "--start--");
            Thread.sleep(1000);
            return index;
        }
    }
}
