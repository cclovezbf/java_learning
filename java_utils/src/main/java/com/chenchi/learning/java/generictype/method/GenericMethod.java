package com.chenchi.learning.java.generictype.method;

public class GenericMethod {
    public static void main(String[] args) {
        Test<String> test = new Test<>();
        test.testMethod("aaaa");
        String aaaa = test.testMethod1("aaaa");
        Integer integer = test.testMethod1(1111);

        TestMethod<String> testMethod = new TestMethod<>();
        String aaaa1 = testMethod.testMethod("aaaa", 1223);
        String bbb = testMethod.testMethod1(1L, "bbb");
    }
}

 class Test<U> {
    // 该方法只是使用了泛型类定义的类型参数，不是泛型方法
    public void testMethod(U u){
        System.out.println(u);
    }

    // <T> 真正声明了下面的方法是一个泛型方法
    public <T> T testMethod1(T t){
        return t;
    }
}
 class TestMethod<U> {
    //泛型方法中也可以使用泛型类中定义的泛型参数
    public <T, S> T testMethod(T t, S s) {
        return t;
    }
    //泛型类中定义的类型参数和泛型方法中定义的类型参数是相互独立的，它们一点关系都没有。
     public <T> U testMethod1(T t, U u) {
         return u;
     }
}
