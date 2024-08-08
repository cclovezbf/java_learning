package learning.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * https://www.yisu.com/zixun/464795.html
 */
public class WeakReferenceTest {

    static Map<String,WeakReference<String>> weakReferenceMap;
    static Map<String,String> map;

    public static void main(String[] args) throws InterruptedException {
        WeakReferenceTest weakReference=new WeakReferenceTest();
//        weakReference.doWeakMapTest();
        weakReference.doWeakMapTest2();
        weakReference.doMapTest();
        Map.Entry<String, WeakReference<String>> next = weakReferenceMap.entrySet().iterator().next();
        System.out.println("发生GC前,weakMap.size="+ weakReferenceMap.size()+",content=["+ next.getKey()+"="+ next.getValue().get()+"]");
        System.out.println("发生GC前,map.size="+ map);
        System.out.println("start GC");
        System.gc();
        Thread.sleep(1000*5);
        WeakReference<String> value = next.getValue();
        String valueStr=value==null?null:value.get();
        System.out.println("发生GC后,weakMap.size="+ weakReferenceMap.size()+",content=["+ next.getKey()+"="+valueStr+"]");
        System.out.println("发生GC后,map.size="+ map.size()+",content=["+map+"]");
    }


    private  void  doMapTest(){
        map =new HashMap<>();
        String key=new String("key");
        String value=new String("value");
        map.put(key,value);
        key=null;
        value=null;
    }
    //value没了
    private  void doWeakMapTest(){
        weakReferenceMap =new HashMap<>();
        String key=new String("key");
        String value=new String("value");
        weakReferenceMap.put(key,new WeakReference<>(value));
        key=null;
        value=null;
    }
    //key和value都没了
    private  void doWeakMapTest2(){
        weakReferenceMap =new WeakHashMap<>();
        ReferenceQueue<String> stringReferenceQueue = new ReferenceQueue<>();
        String key=new String("key");
        String value=new String("value");
        weakReferenceMap.put(key,new WeakReference<>(value,stringReferenceQueue));
        key=null;
        value=null;
        System.out.println(stringReferenceQueue.poll());
    }

}
