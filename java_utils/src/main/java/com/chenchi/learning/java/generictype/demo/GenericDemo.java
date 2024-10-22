package com.chenchi.learning.java.generictype.demo;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GenericDemo {

    public static void main(String[] args) {
        ArrayList<Integer> intLists = Lists.newArrayList(1, 2, 3);
        ArrayList<String> strLists = Lists.newArrayList("1","22","3");
        GenericDemo genericDemo = new GenericDemo();
//        genericDemo.add(intLists);//失败
//        genericDemo.add1(intLists) ; //失败 规定了只能用Number类型
        genericDemo.add2(intLists);
        genericDemo.add3(intLists);
        genericDemo.add3(strLists);

    }

    @Test
    public  void test1(){
        ArrayList<? extends Number> list = new ArrayList<>();
//        list.add(new Integer(1));// 编译错误
//        list.add(new Float(1.0));// 编译错误
        //可以这样理解，ArrayList<? extends Number> 集合表示了：我这个集合可能是 ArrayList< Integer > 集合，也可能是 ArrayList< Float >
        // 集合，… ，还可能是 ArrayList< Number > 集合；但到底是哪一个集合，不能确定；程序员也不能指定。
     //所以，在上面代码中，创建了一个 ArrayList<? extends Number> 集合 list，但我们并不能往 list 中添加 Integer、Float 等对象，这也说明了 list 集合并不是某个确定了数据类型的集合。
    }
    private void add (List<Object> list){

    }
    private void add1 (List<Number> list){

    }
    private void add2 (List<? extends Number> list){

    }
    private <T> void add3 (List<T> list){

    }
    //无界通配符<?>：? 代表了任何一种数据类型，能代表任何一种数据类型的只有 null。
    // 需要注意的是： <?> 也是一个数据类型实参，它和 Number、String、Integer 一样都是一种实际的数据类型
    private  void add4 (List<?> list){

    }
    //注意：Object 本身也算是一种数据类型，但却不能代表任何一种数据类型，所以 ArrayList< Object > 和 ArrayList<?> 的含义是不同的，
    // 前者类型是 Object，也就是继承树的最高父类，而后者的类型完全是未知的；ArrayList<?> 是 ArrayList< Object > 逻辑上的父类。
    //ArrayList<?> 在逻辑上表示为所有数据类型的父类，它可以代表 ArrayList< Integer>、ArrayList< Number >、
    // ArrayList< Object >中的某一个集合，但实质上它们之间没有继承关系。
}
