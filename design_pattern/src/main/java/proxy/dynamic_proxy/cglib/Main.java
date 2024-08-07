package proxy.dynamic_proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import proxy.dynamic_proxy.DatabaseDataQuery;

public class Main {
    public static void main(String[] args) {
    // cglib通过Enhancer
    Enhancer enhancer = new Enhancer();
    // 设置他的父类 ！！！！！！！！！！ CGLIB 的动态代理可以代理任意类
    enhancer.setSuperclass(DatabaseDataQuery.class);
    // 设置一个方法拦截器，用来拦截方法
    enhancer.setCallback(new CacheMethodInterceptor());
    // 创建代理类
    DatabaseDataQuery databaseDataQuery =
                (DatabaseDataQuery)enhancer.create();
    databaseDataQuery.query("key1");
    databaseDataQuery.query("key1");
    databaseDataQuery.query("key2");
  }
}
