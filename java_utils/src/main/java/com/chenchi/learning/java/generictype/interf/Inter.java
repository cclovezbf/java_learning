package com.chenchi.learning.java.generictype.interf;

public interface Inter<T> {
    public abstract void show(T t) ;
}

interface IUsb<U, R> {

    int n = 10;
//    U name;// 报错！ 接口中的属性默认是静态的，因此不能使用类型参数声明

    R get(U u);// 普通方法中，可以使用类型参数

    void hi(R r);// 抽象方法中，可以使用类型参数

    // 在jdk8 中，可以在接口中使用默认方法, 默认方法可以使用泛型接口的类型参数
    default R method(U u) {
        return null;
    }
}

// 在继承泛型接口时，必须确定泛型接口的类型参数
interface IA extends IUsb<String, Double> {

}

// 当去实现 IA 接口时，因为 IA 在继承 IUsu 接口时，指定了类型参数 U 为 String，R 为 Double
// 所以在实现 IUsb 接口的方法时，使用 String 替换 U,用 Double 替换 R
class TypeUsb implements IA {
    @Override
    public Double get(String s) {
        return Double.parseDouble(s);
    }
    @Override
    public void hi(Double d) {
        System.out.println("hi:"+d);
    }
}
// 实现接口时，需要指定泛型接口的类型参数
// 给 U 指定 Integer， 给 R 指定了 Float
// 所以，当我们实现 IUsb 方法时，会使用 Integer 替换 U, 使用 Float 替换 R
class TypeAUsb implements IUsb<Integer, Float> {
    @Override
    public Float get(Integer integer) {
        return null;
    }
    @Override
    public void hi(Float afloat) {
        System.out.println("TypeAUsb->hi:"+afloat);
    }
}
// 实现泛型接口时没有确定类型参数，则默认为 Object
// 建议直接写成 IUsb<Object, Object>
class TypeCUsb implements IUsb {//等价 class CC implements IUsb<Object, Object>
    @Override
    public Object get(Object o) {
        return null;
    }
    @Override
    public void hi(Object o) {
        System.out.println("TypeCUsb->hi:"+o);
    }
}


