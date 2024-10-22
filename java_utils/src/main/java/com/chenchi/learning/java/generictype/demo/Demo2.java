//package com.chenchi.learning.java.generictype.demo;
//
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//public class Demo2 {
//    public  void  get1(List<Father> t ){
//        for (Father father : t) {
//            father.say();
//        }
//    }
//    public <T> void  get2(List<? extends Father> t ){
//        for (Father father : t) {
//            father.say();
//        }
//        return ;
//    }
//    public <T> void  get3(List<? super Father> t ){
//        for (Object object : t) {
//            if (object instanceof Father){
//                Father object1 = (Father) object;
//                object1.say();
//            }else if(object instanceof Grandpa){
//                Grandpa object1 = (Grandpa) object;
//                object1.say();
//            }
//        }
//
//    }
//    public <T> void  get4(List<?> t ){
//        for (Object object : t) {
//            if (object instanceof Father){
//                Father object1 = (Father) object;
//                object1.say();
//            }else if(object instanceof Grandpa){
//                Grandpa object1 = (Grandpa) object;
//                object1.say();
//            }
//        }
//        }
//
//    /**
//     * 复制集合-泛型
//     * target目标   src来源
//     */
//    public void copy(List<? super T>  target,List<? extends T> src){
//        if (src.size() > target.size()){
//            for (int i = 0; i < src.size(); i++) {
//                target.set(i,src.get(i));
//            }
//        }
//    }
//    /**
//     * 复制集合-泛型
//     */
//    public List<T>  listCopy(Collection<T> collection){
//        List<T> newCollection = new ArrayList<>();
//        for (T t : collection) {
//            newCollection.add(t);
//        }
//        return newCollection;
//    }
//
//    /**
//     * 复制集合-上边界通配符
//     */
//    public  List<T>  listCopyTwo(Collection<? extends T> collection){
//        List<T> newCollection = new ArrayList<>();
//        for (T t : collection) {
//            newCollection.add(t);
//
//        }
//        return newCollection;
//    }
//    public static void main(String[] args) {
//        Grandpa grandpa = new Grandpa();
//        Father father = new Father();
//        Son son = new Son();
//        daughter daughter = new daughter();
//        ArrayList<Grandpa> grandpas =new ArrayList();
//        grandpas.add(grandpa);
//        ArrayList<Father> fathers =new ArrayList();
//        fathers.add(father);
//        ArrayList<Son> sons =new ArrayList();
//        sons.add(son);
//        ArrayList<Grandpa> list = new ArrayList<>();
//        list.add(grandpa);
//        list.add(father);
//        list.add(son);
//
//
//        ArrayList<Father> list1 = new ArrayList<>();
//        list1.add(son);
//        list1.add(father);
//
//
//        ArrayList<? extends Father> list2= new ArrayList<>();
//        // 上边界通配符，即?是继承自T的任意子类型 只读不写！
//        // <? extends T>上边界通配符不作为函数入参，只作为函数返回类型，比如List<? extends T>的使用add函数会编译不通过，get函数则没问题
////        list2.add(); 报错
//// 只能赋值位 继承于father的子类。 比如 list<father>  list<son> list<? extends son>
//
//
//        ArrayList<? super Father> list3 = new ArrayList<>();
//        //：下边界通配符，即?是T的任意父类型，遵守只写不读
//        //<? super T>下边界通配符不作为函数返回类型，只作为函数入参，比如List<? super T>的add函数正常调用，get函数也没问题，但只会返回Object，所以意义不大
////        list3=new ArrayList<Father>();
////        list3=new ArrayList<Grandpa>();
////        list3=new ArrayList<? super Grandpa>(); //报错
//        list3.add(father);
//        list3.add(son);
////        list3.add(grandpa); //这里报错因为 ？ supper Father 可能就是father 虽然能够是grandPa
//
//        ArrayList<? > list4 = new ArrayList<>();
//
//        Demo2 demo2 = new Demo2();
//        demo2.get3(list3);
//        demo2.get3(grandpas);
//        CollectionUtils<Father> utils = new CollectionUtils<>();
//        List<Father> fathers1 = utils.listCopyTwo(list2);
//    }
//}
//class CollectionUtils<T>{
//
//    /**
//     * 复制集合-泛型
//     */
//    public List<T>  listCopy(Collection<T> collection){
//        List<T> newCollection = new ArrayList<>();
//        for (T t : collection) {
//            newCollection.add(t);
//        }
//        return newCollection;
//    }
//
//    /**
//     * 复制集合-上边界通配符
//     */
//    public  List<T>  listCopyTwo(Collection<? extends T> collection){
//        List<T> newCollection = new ArrayList<>();
//        for (T t : collection) {
//            newCollection.add(t);
//
//        }
//        return newCollection;
//    }
//}
//
//class daughter {
//
//    protected void say() {
//        System.out.println("Son");
//    }
//}
///**
// * 儿子
// */
//class Son extends Father{
//
//    @Override
//    protected void say() {
//        System.out.println("Son");
//    }
//}
//
///**
// * 父亲
// */
//class Father  extends  Grandpa{
//    @Override
//    protected void say() {
//        System.out.println("Father");
//    }
//}
//
///**
// * 爷爷
// */
//class Grandpa {
//    protected void say(){
//        System.out.println("grandPa");
//    }
//}