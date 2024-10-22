package com.chenchi.learning.java.queue.linkedBlockingQueue;

import java.util.concurrent.LinkedBlockingQueue;

public class LikedBlockingQueueDemo {
    public static void main(String[] args) {
        LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>(1000);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    queue.offer(new Object());
                    queue.remove();
                }
            }).start();
        }
        while (true) {
            System.out.println("begin scan, i still alive");
            queue.stream()
                    .filter(o -> o == null)
                    .findFirst()
                    .isPresent();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("finish scan, i still alive");
        }
    }
}
