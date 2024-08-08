package cache;

import com.google.common.cache.*;
import org.junit.Test;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public class CacheBuilderDemo {
    private static final LoadingCache<String, CacheBuilderDemo> cache = CacheBuilder
            .newBuilder()
            .expireAfterWrite(2, TimeUnit.SECONDS)
            .build(new CacheLoader<String, CacheBuilderDemo>() {
                @Override
                public CacheBuilderDemo load(String key) throws Exception {
                    return new CacheBuilderDemo();
                }
            });

    public static void main(String[] args) throws InterruptedException {
        System.out.println(cache);
        while (true){
            CacheBuilderDemo a = cache.getUnchecked(String.valueOf(new Random().nextInt()));
            ConcurrentMap<String, CacheBuilderDemo> stringCacheBuilderDemoConcurrentMap = cache.asMap();
            for (Map.Entry<String, CacheBuilderDemo> stringCacheBuilderDemoEntry : stringCacheBuilderDemoConcurrentMap.entrySet()) {
                System.out.println(stringCacheBuilderDemoEntry.getKey()+"==="+stringCacheBuilderDemoEntry.getValue());
            }
            System.out.println(a);
            Thread.sleep(1000);
        }
    }

    @Test
    public void demo1() throws InterruptedException {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(100)//缓存最大个数
                .expireAfterWrite(2, TimeUnit.SECONDS)//写入后5秒过期
                .build();
        cache.put("a", "1");
        int i = 1;
        while (true) {
            System.out.println("第" + i + "秒获取到的数据为：" + cache.getIfPresent("a"));
            i++;
            Thread.sleep(1000);
        }
    }

    /**
     * 只要这个时间范围内有人访问 就会一直存在
     *
     * @throws InterruptedException
     */
    @Test
    public void demo2() throws InterruptedException {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(10)//缓存最大个数
                .expireAfterAccess(2, TimeUnit.SECONDS)//写入后5秒过期
                .build();
        cache.put("a", "1");
        int i = 1;
        while (true) {
            if (i < 3) {
                System.out.println("第" + i + "秒获取到的数据为：" + cache.getIfPresent("a"));
            } else {
                System.out.println("第" + i + "秒获取到的数据为：" + cache.getIfPresent("b"));
            }
            i++;
            Thread.sleep(1000);
        }
    }

    @Test
    public void removeListenerTest() throws InterruptedException {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .expireAfterWrite(5, TimeUnit.SECONDS)//5秒后自动过期
                //添加一个remove的监听器
                .removalListener(new RemovalListener<Object, Object>() {
                    @Override
                    public void onRemoval(RemovalNotification<Object, Object> notification) {
                        System.out.println("[" + notification.getKey() + ":" + notification.getValue() + "] 被删除了");
                    }
                })
                .build();
        System.out.println("start");
        cache.put("a", "1");
        Thread.sleep(2000);
        cache.put("b", "2");
        cache.put("c", "3");
        Thread.sleep(2000);
        cache.put("d", "4");
        Thread.sleep(5000);
        cache.put("e", "5");
        cache.invalidate("e");
    }
}
