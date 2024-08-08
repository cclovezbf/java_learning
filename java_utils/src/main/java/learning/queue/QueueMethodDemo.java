package learning.queue;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;


//添加 add报错 put等待 offer返回false
//拿出 remove报错 task等待 poll返回null
//take和put是阻塞的获取和存储元素的方法，
//poll和offer是不阻塞的获取元素和存储元素的方法，并且poll和offer可以指定超时时间。
//add和remove存取元素，队列满时add抛异常，队列空时remove抛异常
//poll: 若队列为空，返回null。
//remove:若队列为空，抛出NoSuchElementException异常。
//take:若队列为空，发生阻塞，等待有元素。

public class QueueMethodDemo {

    private ArrayBlockingQueue<Integer> queue;

    {
        queue = new ArrayBlockingQueue<>(3);
        System.out.println("queue的初始化capacity=3");
    }



    public static void main(String[] args) throws InterruptedException {
        QueueMethodDemo demo = new QueueMethodDemo();
        demo.method();
        demo.doDrainTo();
//        demo.doAdd();
//        demo.doOffer();
//        demo.doPut();
    }

    //可以设置上限  如果不满就拿多少
    private void doDrainTo(){
        queue.clear();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            queue.add(i);
        }
        int i = queue.drainTo(list, 2);
        System.out.println("queue.drainTo后的list="+list);
        System.out.println("queue.drainTo 返回值=="+i);
    }

    private void method(){
        queue.clear();
        for (int i = 0; i < 3; i++) {
            queue.add(i);
        }
        System.out.println("queue.remainingCapacity()"+queue.remainingCapacity());
        System.out.println("queue.peek()只返回queue的第一个对象不删除，不存在返回null"+queue.peek());
        System.out.println("queue.poll()返回queue的第一个对象并删除"+queue.poll());
        System.out.println("queue.size()"+queue.size());
        System.out.println("queue.element()返回第一个元素，不存在则报错"+queue.element());

    }

    private void doPut() throws InterruptedException {
        queue.clear();
        for (int i = 0; i < 5; i++) {
            queue.put(i);
            System.out.println("queue.put "+i+" success");
        }
    }

    private void doOffer() {
        queue.clear();
        for (int i = 0; i < 5; i++) {
            boolean offer = queue.offer(i);
            System.out.println("queue.offer"+i+":result="+offer);
        }
    }

    private void doAdd() {
        queue.clear();
        ArrayList<Integer> list = new ArrayList<>();
        int i = 0;
        try {
            for (; i < 5; i++) {
                list.add(i);
            }
            queue.addAll(list);
            System.out.println(queue);
            System.out.println("此时queue的剩余空间queue.remainingCapacity=" + queue.remainingCapacity());
        } catch (Exception e) {
            System.out.println("add "+i+"error :" + e.getMessage());
        }
        System.out.println(queue);
    }

    private void clear() {
        queue.clear();
        System.out.println("清空queue。 queue=" + queue);
    }
}
