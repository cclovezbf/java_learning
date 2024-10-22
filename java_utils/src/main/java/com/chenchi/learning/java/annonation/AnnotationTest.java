package com.chenchi.learning.java.annonation;

import javax.annotation.Resource;
import java.lang.reflect.Method;

public class AnnotationTest {
    @MyTarget
    public void doSomething() {
    }

    @Resource
    public static void main(String[] args) throws Exception {
        Class<AnnotationTest> annotationTestClass = AnnotationTest.class;
        Method method = annotationTestClass.getMethod("doSomething", null);
        if (method.isAnnotationPresent(MyTarget.class)) {
            System.out.println(method.getAnnotation(MyTarget.class));
        }
    }

}
