package com.chenchi;


import org.apache.tinkerpop.gremlin.driver.*;
import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection;
import org.apache.tinkerpop.gremlin.driver.ser.GryoMessageSerializerV3d0;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource.traversal;

public class KonisGraphTest {

    public static void main(String[] args) {
        // 设置正确的 serializer
//        val serializer = new GryoMessageSerializerV3d0
//        val cluster: Cluster = Cluster.build.addContactPoint("9.135.246.30").port(8182).credentials("stephen", "password").serializer(serializer).create
//        // 创建一个 GraphTraversalSource 实例用于查询数据
//        g = traversal.withRemote(DriverRemoteConnection.using(cluster))
        MessageSerializer serializer = new GryoMessageSerializerV3d0();
        Cluster cluster = Cluster.build().
                addContactPoint("9.135.246.30").port(8182).
                credentials("stephen", "password").
                serializer(serializer).
                create();
        Client client = cluster.connect();

        // 添加属性、点、边等元数据。submit 方法需要捕获异常处理
        try {
            ResultSet submit = client.submit("g.V().limit(1)");
            List<Result> results = submit.all().get();
            for (Result result : results) {
                System.out.println(result);
            }
//            client.submit("s.addP('id', 'T_LONG', '0')").all().get();
//            client.submit("s.addP('name', 'T_STRING', '')").all().get();
//            client.submit("s.addP('age', 'T_INT', '0')").all().get();
//            client.submit("s.addP('lang', 'T_STRING', '')").all().get();
//            client.submit("s.addP('weight', 'T_DOUBLE', '0.0')").all().get();
//            client.submit("s.addV('person', 'id', ['name', 'age'])").all().get();
//            client.submit("s.addV('software', 'id', ['name', 'lang'])").all().get();
//            client.submit("s.addE('knows', 'person', 'person', ['weight'])").all().get();
//            client.submit("s.addE('created', 'person', 'software', ['weight'])").all().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
//
//        // 创建一个 GraphTraversalSource 实例用于查询数据
        GraphTraversalSource g = traversal().withRemote(DriverRemoteConnection.using(cluster));
//
//        // property 必须分开写才能成功
//        g.addV("person").property("id", 1).property("name", "marko").property("age", 29).iterate();
//
//        g.addV("person").property("id", 2).property("name", "vadas").property("age", 27).
//                addV("person").property("id", 4).property("name", "josh").property("age", 32).
//                addV("person").property("id", 6).property("name", "peter").property("age", 35).iterate();
//
//        g.addV("software").property("id", 3).property("name", "lop").property("lang", "java").
//                addV("software").property("id", 5).property("name", "ripple").property("lang", "java").iterate();
//
//        g.V(1).addE("knows").to(V(2)).iterate();
//        g.V(1).addE("knows").to(V(4)).iterate();
//        g.V(1).addE("created").to(V(3)).property("weight", 0.4).iterate();
//        g.V(6).addE("created").to(V(3)).property("weight", 0.2).iterate();
//        g.V(4).addE("created").to(V(3)).property("weight", 0.4).iterate();
//        g.V(4).addE("created").to(V(5)).property("weight", 1.0).iterate();
//
//        System.out.println("marko: " + g.V().hasLabel("person").has("name", "marko").valueMap("name", "age").toList());
//
//        System.out.println("who marko knows: " + g.V().hasLabel("person").has("name", "marko").out("knows").valueMap("name", "age").toList());
//
//        System.out.println("who creates software lop: " + g.V().hasLabel("software").has("name", "lop").in("created").valueMap("name").toList());

        cluster.close();
    }
}