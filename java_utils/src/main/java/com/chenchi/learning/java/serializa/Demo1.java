package com.chenchi.learning.java.serializa;

import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;

/**
 * https://blog.csdn.net/huxiang19851114/article/details/112991059
 */
public class Demo1 implements Serializable{
    class Person implements Serializable{
        private Integer id ;
        private String name ;
        private String sex ;

        private Role role ;

        public Person(Integer id, String name, String sex, Role role) {
            this.id = id;
            this.name = name;
            this.sex = sex;
            this.role = role;
        }

        public Person(Integer id, String name, String sex) {
            this.id = id;
            this.name = name;
            this.sex = sex;
        }

        public void setRole(Role role) {
            this.role = role;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", sex='" + sex + '\'' +
                    ", role=" + role +
                    '}';
        }
    }
    class Role implements  Serializable{
        private String role;

        public Role(String role) {
            this.role = role;
        }

        @Override
        public String toString() {
            return "Role{" +
                    "role='" + role + '\'' +
                    '}';
        }
    }
    @Test
    public void test() throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(new File("object.txt").toPath()));
        Person person = new Person(18,"laohu","men");
        oos.writeObject(person);
        oos.flush();
        oos.close();
        //从文件中读取字节序列，反序列化为对象
        ObjectInputStream ois= new ObjectInputStream(Files.newInputStream(new File("object.txt").toPath()));
        Object obj =  ois.readObject();
        ois.close();
        System.out.println(obj);
    }

    @Test
    public void test1() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //序列化到对象输出流中,文件类型可以为任何类型
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        Person person = new Person(18,"laohu","men");
        oos.writeObject(person);
        byte[] bytes = baos.toByteArray();
        System.out.println(Arrays.toString(bytes));
        oos.flush();
        oos.close();
        //从文件中读取字节序列，反序列化为对象
        ObjectInputStream ois= new ObjectInputStream(new ByteArrayInputStream(bytes));
        Object object = ois.readObject();
        System.out.println(object);
        System.out.println(object.getClass()); // class com.chenchi.learning.java.serializa.Demo1$Person
        ois.close();
        //这个地方先标注下打印出来的字节数组,等下跟protobuf的对比
       /*[-84, -19, 0, 5, 115, 114, 0, 17, 99, 111, 109, 46, 121, 100, 116, 46, 112, 111, 106, 111, 46, 85, 115, 101, 114, 108, 124, -59, -89, -28, 34, 123, -76, 2, 0, 3, 73, 0, 2, 105, 100, 76, 0, 8, 112, 97, 115, 115, 119, 111, 114, 100, 116, 0, 18, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 76, 0, 8, 117, 115, 101, 114, 110, 97, 109, 101, 113, 0, 126, 0, 1, 120, 112, 0, 0, 0, 1, 116, 0, 6, 49, 50, 51, 52, 53, 54, 116, 0, 5, 108, 97, 111, 104, 117]*/
    }

    @Test
    public void test2() throws IOException, ClassNotFoundException {
        //序列化到对象输出流中,文件类型可以为任何类型
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("object.txt")));
        Person person = new Person(18,"laohu","men");
        Role role = new Role("admin");
        person.setRole(role);
        oos.writeObject(person);
        oos.flush();
        oos.close();
        //从文件中读取字节序列，反序列化为对象
        ObjectInputStream ois= new ObjectInputStream(new FileInputStream(new File("object.txt")));
        System.out.println(ois.readObject());
        ois.close();
    }

//    4、声明为transient类型的成员数据不能被序列化。因为transient代表对象的临时数据。如果想让该字段再次可以被序列化（选择性序列化），
//    在类中添加两个方法：writeObject()与readObject(),需要手动对成员变量进行序列化！
    private transient String VALUE ="transient";
    // writeObject()会先调用ObjectOutputStream中的defaultWriteObject()方法，该方法会执行默认的序列化机制，此时会忽略掉VALUE字段。然后再调用writeObject()方法显示地将VALUE字段写入
    // ObjectOutputStream中。
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(VALUE);
    }
    // readObject()的作用则是针对对象的读取，其原理与writeObject()方法相同。
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        VALUE = (String) in.readObject();
    }
    /*必须注意地是，writeObject()与readObject()都是private方法，那么它们是如何被调用的呢？
    毫无疑问，使用反射。详情可以看看ObjectOutputStream中的writeSerialData方法，以及ObjectInputStream中的readSerialData方法。这两个方法会在序列化、反序列化的过程中被自动调用。且不能关闭流，否则会导致序列化操作失败*/
}
