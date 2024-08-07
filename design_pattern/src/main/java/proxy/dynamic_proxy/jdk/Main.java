package proxy.dynamic_proxy.jdk;

import proxy.dynamic_proxy.DataQuery;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
// jdk提供的代理实现，主要是使用Proxy类来完成  ！！！！！！！！代理类是一个接口，建议使用基于 JDK 的动态代理来实现
// 1、classLoader：被代理类的类加载器
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
// 2、代理类需要实现的接口数组
        Class[] interfaces = new Class[]{DataQuery.class};
// 3、InvocationHandler
        InvocationHandler invocationHandler = new CacheInvocationHandler();
        DataQuery dataQuery = (DataQuery) Proxy.newProxyInstance(
                classLoader, interfaces, invocationHandler
        );
// 事实上调用query方法的使用，他是调用了invoke
        String result = dataQuery.query("key1");
        System.out.println(result);
        System.out.println("--------------------");

        result = dataQuery.query("key1");
        System.out.println(result);
        System.out.println("--------------------");
        result = dataQuery.query("key2");
        System.out.println(result);
        System.out.println("++++++++++++++++++++++++++++++++++++");
// 事实上调用queryAll方法的使用，他是调用了invoke
        result = dataQuery.queryAll("key1");
        System.out.println(result);
        System.out.println("--------------------");
        result = dataQuery.queryAll("key1");
        System.out.println(result);
        System.out.println("--------------------");
        result = dataQuery.queryAll("key2");
        System.out.println(result);
        System.out.println("--------------------");
    }
}
//在这个示例中，我们使用 Proxy.newProxyInstance 方法生成代理对象，并将代
//        理对象转换成 DataQuery 接口类型，以便调用其代理的方法。在代理对象调用方法
//        时，会调用 CacheInvocationHandler 类中的 invoke 方法，实现对被代理对象
//        的方法的增强。