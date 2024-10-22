package com.chenchi.learning.java.generictype.interf;

import java.util.Random;

public interface Generator<T> {
    public T next();
}
/**
 * 未传入泛型实参时，与泛型类的定义相同，在声明类的时候，需将泛型的声明也一起加到类中
 * 即：class FruitGenerator<T> implements Generator<T>{
 * 如果不声明泛型，如：class FruitGenerator implements Generator<T>，编译器会报错："Unknown class"
 */
 class FruitGenerator<T> implements Generator<T>{
    @Override
    public T next() {
        return null;
    }
}

class FruitGenerator2 implements Generator<String> {

    private String[] fruits = new String[]{"Apple", "Banana", "Pear"};

    @Override
    public String next() {
        Random rand = new Random();
        return fruits[rand.nextInt(3)];
    }
}