package proxy.dynamic_proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import proxy.dynamic_proxy.DatabaseDataQuery;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class CacheMethodInterceptor implements MethodInterceptor {
    private HashMap<String, String> cache = new LinkedHashMap<>(256);
    private DatabaseDataQuery databaseDataQuery;

    public CacheMethodInterceptor() {
        this.databaseDataQuery = new DatabaseDataQuery();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args,
                            MethodProxy proxy) throws Throwable {
        // 1、判断是哪一个方法
        String result = null;
        if ("query".equals(method.getName())) {
            // 2、查询缓存，命中直接返回
            result = cache.get(args[0].toString());
            if (result != null) {
                System.out.println("数据从缓存重获取。");
                return result;
            }
            // 3、未命中，查数据库（需要代理实例）
            result = (String) method.invoke(databaseDataQuery, args);
            // 4、如果查询到了,进行呢缓存
            cache.put(args[0].toString(), result);
            return result;
        }
        return method.invoke(databaseDataQuery, args);
    }
}
