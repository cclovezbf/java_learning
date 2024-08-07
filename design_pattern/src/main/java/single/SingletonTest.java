package single;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingletonTest {
    ExecutorService executorService = Executors.newFixedThreadPool(50);
    CountDownLatch countDownLatch = new CountDownLatch(1000);
    Set<String> set = new ConcurrentSkipListSet<>();
    public static void main(String[] args) throws InterruptedException {
        SingletonTest singletonTest = new SingletonTest();
//        singletonTest.testSingleTon(singletonTest.getEagerSingleTon()); // size=1
//        singletonTest.testSingleTon(singletonTest.getLazySingleTon()); // size=3
//        singletonTest.testSingleTon(singletonTest.getLazySingleTon2()); // size=1
        singletonTest.testSingleTon(singletonTest.getLazySingleTon3()); // size=3
//        singletonTest.testSingleTon(singletonTest.getLazySingleTon4()); // size=3
    }

    private Runnable getEagerSingleTon(){
        Runnable runnable = () -> {
            set.add(EagerSingleton.getInstance().toString());
            countDownLatch.countDown();
        };
        return runnable;
    }

    private Runnable getLazySingleTon(){
        Runnable runnable = () -> {
            set.add(LazySingleton.getInstance().toString());
            countDownLatch.countDown();
        };
        return runnable;
    }
    private Runnable getLazySingleTon2(){
        Runnable runnable = () -> {
            set.add(LazySingleton2.getInstance().toString());
            countDownLatch.countDown();
        };
        return runnable;
    }
    private Runnable getLazySingleTon3(){
        Runnable runnable = () -> {
            set.add(LazySingleton3.getInstance().toString());
            countDownLatch.countDown();
        };
        return runnable;
    }
    private Runnable getLazySingleTon4(){
        Runnable runnable = () -> {
            set.add(LazySingleton4.getInstance().toString());
            countDownLatch.countDown();
        };
        return runnable;
    }
    private void testSingleTon(Runnable runnable) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executorService.submit(runnable);
        }
        countDownLatch.await();
        System.out.println(set);
        System.out.println("set.size()="+set.size());
        executorService.shutdown();
    }
}
