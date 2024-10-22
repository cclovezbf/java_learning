package com.chenchi.learning.java.generictype;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
//https://blog.csdn.net/weixin_45395059/article/details/126006369
public class GenericTypeDemo {
    private static final Logger logger = LoggerFactory.getLogger(GenericTypeDemo.class);

    /**
     * 在 ArrayList 集合中，可以放入所有类型的对象，假设现在需要一个只存储了 String 类型对象的 ArrayList 集合。
     */
    @Test
    public void test1() {
        ArrayList list = new ArrayList();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((String)list.get(i));
        }
    }

    /**
     * 但如果在添加 String 对象时，不小心添加了一个 Integer 对象，会发生什么？看下面代码：
     */
    @Test
    public void test2() {
        ArrayList list = new ArrayList();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add(111);
        for (int i = 0; i < list.size(); i++) {
            System.out.println((String)list.get(i));
        }
        //上述代码在编译时没有报错，但在运行时却抛出了一个 ClassCastException 异常，其原因是 Integer 对象不能强转为 String 类型。
        //此时有疑问了 为什么要强转string呢？
        //怎么不各个instance 呢？ 几十万个类型怎么都instance呢？
        //我们不知道传入的是什么类型 但是我们又要用这个数据
    }

    /**
     * < String > 是一个泛型，其限制了 ArrayList 集合中存放对象的数据类型只能是 String，当添加一个非 String 对象时，编译器会直接报错。
     * 这样，我们便解决了上面产生的 ClassCastException 异常的问题（这样体现了泛型的类型安全检测机制）。
     */
    @ Test
    public void test3() {
        ArrayList<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
//        list.add(111);// 在编译阶段，编译器会报错
        for (int i = 0; i < list.size(); i++) {
            System.out.println((String)list.get(i));
        }
    }
    //泛型概述小结
    //与使用 Object 对象代替一切引用数据类型对象这样简单粗暴方式相比，泛型使得数据类型的类别可以像参数一样由外部传递进来。它提供了一种扩展能力，更符合面向对象开发的软件编程宗旨。
    //当具体的数据类型确定后，泛型又提供了一种类型安全检测机制，只有数据类型相匹配的变量才能正常的赋值，否则编译器就不通过。所以说，泛型一定程度上提高了软件的安全性，防止出现低级的失误。
    //泛型提高了程序代码的可读性。在定义泛型阶段（类、接口、方法）或者对象实例化阶段，由于 < 类型参数 > 需要在代码中显式地编写，所以程序员能够快速猜测出代码所要操作的数据类型，提高了代码可读性。



    /**
     * 只在编译的时候有效
     * 在编译之后程序会采取去泛型化的措施。也就是说Java中的泛型，只在编译阶段有效。在编译过程中，正确检验泛型结果后，会将泛型的相关信息擦出，
     * 并且在对象进入和离开方法的边界处添加类型检查和类型转换的方法。也就是说，泛型信息不会进入到运行时阶段。
     */
    @Test
    public void test() {
        List<String> stringArrayList = new ArrayList<String>();
        List<Integer> integerArrayList = new ArrayList<Integer>();
        Class classStringArrayList = stringArrayList.getClass();
        Class classIntegerArrayList = integerArrayList.getClass();

        if (classStringArrayList.equals(classIntegerArrayList)) {
            logger.info("泛型测试,类型相同");
        } else {
            logger.info("不同");
        }
    }
}
