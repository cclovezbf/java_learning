package learning.reference;

import java.lang.ref.WeakReference;
//https://blog.csdn.net/pony_maggie/article/details/124597982
public class ReferenceTest {

        public static WeakReference<String> weakReference1;

        public static void main(String[] args) {

            test1();
            //test1外部，hello对象作用域结束，没有强引用指向"value"了。只有一个弱引用指向"value"
            System.out.println("未进行gc时，只有弱引用指向value内存区域：" + weakReference1.get());

            //此时gc时会回收弱引用
            System.gc();

            //此时输出都为null
            System.out.println("进行gc时，只有弱引用指向value内存区域：" + weakReference1.get());

        }

        public static void test1() {
            //hello对象强引用"value"
            String hello = new String("value");

            //weakReference1对象弱引用指向"value"
            weakReference1 = new WeakReference<>(hello);

            //在test1内部调用gc，此时gc不会回收弱引用，因为hello对象强引用"value"
            System.gc();
            System.out.println("进行gc时，强引用与弱引用同时指向value内存区域：" + weakReference1.get());

        }


}
