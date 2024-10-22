package com.chenchi.learning.java.thread.pool;

import cn.hutool.core.io.file.FileWriter;
import org.junit.Test;

import java.io.File;
import java.util.concurrent.*;

public class ThreadLearning {

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            2,
            4,
            20, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(2),
            new MyThreadFactory("cclovezbf")
            //注意我这里核心2 最大是4
    );

    @Test
    public void testFile(){
        File file = new File("D:\\install\\code\\learning\\bigdata_learining\\java\\src\\main\\java\\com\\chenchi\\learning\\java\\thread\\pool\\cc.txt");
        FileWriter fileWriter = new FileWriter(file);
        File write = fileWriter.write(Thread.currentThread().getName() + " task[" + 1 + "]--start--", true);

    }
    @Test
    public void testThread() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " task [" + finalI + "]--start1--");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " task [" + finalI + "]--start2--");
//                    File file = new File("D:\\install\\code\\learning\\bigdata_learining\\java\\src\\main\\java\\com\\chenchi\\learning\\java\\thread\\pool\\cc.txt");
//                    FileWriter fileWriter = new FileWriter(file);
//                    fileWriter.write(Thread.currentThread().getName() + " task[" + finalI + "]--start--",true);
                }

            });
            thread.setDaemon(false);
            thread.start();
        }
        Thread.sleep(1000);
        System.out.println("over");
    }

    private static class Task extends Thread {
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


}

