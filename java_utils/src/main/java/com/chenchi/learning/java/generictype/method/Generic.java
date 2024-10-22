package com.chenchi.learning.java.generictype.method;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Generic<T>{
    private T key;

    public Generic(T key) {
        this.key = key;
    }

    //我想说的其实是这个，虽然在方法中使用了泛型，但是这并不是一个泛型方法。
    //这只是类中一个普通的成员方法，只不过他的返回值是在声明泛型类已经声明过的泛型。
    //所以在这个方法中才可以继续使用 T 这个泛型。
    public T getKey(){
        return key;
    }

    /**
     * 这个方法显然是有问题的，在编译器会给我们提示这样的错误信息"cannot reslove symbol E"
     * 因为在类的声明中并未声明泛型E，所以在使用E做形参和返回值类型时，编译器会无法识别。
     public E setKey(E key){
     this.key = key
     }
     */
}

class GenericTuple<IN,OUT>{

    private IN key;
    private OUT value;

    private Map<IN,OUT> map =new HashMap<>();

    public GenericTuple(IN key,OUT value) {
        this.key = key;
        this.value=value;
        map.put(key,value);
    }

    //我想说的其实是这个，虽然在方法中使用了泛型，但是这并不是一个泛型方法。
    //这只是类中一个普通的成员方法，只不过他的返回值是在声明泛型类已经声明过的泛型。
    //所以在这个方法中才可以继续使用 T 这个泛型。
    public OUT getKey(IN in){
        OUT out = map.get(in);
        return out;
    }

    public <T> T getKey2(Generic<T> in){
        T key = in.getKey();
        return key;
    }

    public <T> List< T> getKey3(String in ){
        return null;
    }

    public List<IN> getKey4(String in ){
        return null;
    }
}