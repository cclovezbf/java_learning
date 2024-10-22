package com.chenchi.learning.java.generictype.clazz;

import com.chenchi.learning.java.generictype.GenericTypeDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 泛型类
 * @param <T>
 */
public class Generic<T>{
    private static final Logger logger = LoggerFactory.getLogger(GenericTypeDemo.class);

    //key这个成员变量的类型为T,T的类型由外部指定
    private T key;

    public Generic(T key) { //泛型构造方法形参key的类型也为T，T的类型由外部指定
        this.key = key;
    }

    public T getKey(){ //泛型方法getKey的返回值类型为T，T的类型由外部指定
        return key;
    }

    public <T> void  getClass(T t ){

    }

    public <T> T  getClass1(T t ){
        System.out.println(t);
        return t;
    }

    public static void main(String[] args) {
        test2();
    }

    public static void test1(){
        Generic<Integer> genericInteger = new Generic<Integer>(10000);
//传入的实参类型需与泛型的类型参数类型相同，即为String.
        Generic<String> genericString = new Generic<>("猿码天地");
        logger.info("泛型测试{}","key is " + genericInteger.getKey());
        logger.info("泛型测试{}","key is " + genericString.getKey());
    }

    public static void test2(){
        Generic generic0 = new Generic("猿码天地");
        Generic generic1 = new Generic(10000);
        Generic generic2 = new Generic(100.00);
        Generic generic3 = new Generic(false);
        logger.info("泛型测试{}","key is " + generic0.getKey());
        logger.info("泛型测试{}","key is " + generic1.getKey());
        logger.info("泛型测试{}","key is " + generic2.getKey());
        logger.info("泛型测试{}","key is " + generic3.getKey());
    }
}