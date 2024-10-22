package com.chenchi.learning.java.classdemo;

import java.util.Arrays;

public class ClassDemo1 {
    static class student{
        private String name;

        public student(String name) {
            this.name = name;
        }
    }
    public static void main(String[] args) throws ClassNotFoundException {
        ClassDemo1 classDemo1 = new ClassDemo1();
        classDemo1.getClassMethod();
    }

    private void createClass() throws ClassNotFoundException {
        //1.如果我们创建了某个类的对象
        Object o = new Object();
        Class<?> class1 = o.getClass();
        //2.类.class获得Class对象
        Class<Object> class2 = Object.class;
        //3.通过forName()方法获取到Class对象
        Class<?> class3 = Class.forName("java.lang.Object");
        System.out.println(class1);
        System.out.println(class2);
        System.out.println(class3);
    }

    private void getClassMethod(){
        student o = new student("cc");
        Class<?> clazz = o.getClass();
        //isInterface方法，判断是否为接口
        System.out.println(clazz.isInterface());
        //isArray方法，判断是否为数组
        System.out.println(clazz.isArray());
        //isPrimitive方法，判断是否是基本类型，例如int是基本类型，Integer为包装类
        System.out.println(clazz.isPrimitive());
        //isAnnotation方法，判断是否为注解，注解如常见的重写注解@Override，我们所用的单元测试@Test注解
        System.out.println(clazz.isAnnotation());
        //isInstance方法参数是一个对象，判断该对象 对应的类clazz是否是o的对象
        System.out.println(o instanceof Object);
        //getClassLoader方法，获取类加载器
        System.out.println(clazz.getClassLoader());
        //getSuperclass方法，获取父类信息
        System.out.println(clazz.getSuperclass());
        //getGenericSuperclass方法，也是获取父类信息
        System.out.println(clazz.getGenericSuperclass());
        //getComponentType方法，如果clazz的类型是数组，则获取数组里元素的类型
        System.out.println(clazz.getComponentType());
        //getDeclaredClasses方法，获取clazz继承于哪个类
        System.out.println(Arrays.toString(clazz.getDeclaredClasses()));


        //几个获取name的方法：
        //getName方法
        System.out.println(clazz.getName());
        //getTypeName方法
        System.out.println(clazz.getTypeName());
        //getCanonicalName方法
        System.out.println(clazz.getCanonicalName());
        //getSimpleName方法
        System.out.println(clazz.getSimpleName());

//        以下还有一些方法只列举出来
//        getTypeParameters方法，获取泛型类中的泛型参数数组
//        getClasses方法，获取Class对象中public修饰的内部类
//        getDeclaredClasses方法，获取Class对象中的内部类，继承成员不包含在内
//        getConstructors方法，获取public修饰的构造函数
//        getConstructor方法，查找对应的构造函数
//        getDeclaredConstructors方法，获取Class对象中的构造函数
    }
}
