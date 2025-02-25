package com.chenchi;

import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.driver.MessageSerializer;
import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection;
import org.apache.tinkerpop.gremlin.driver.ser.GryoMessageSerializerV3d0;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;

import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.*;
import static org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource.traversal;


public class App {

 public static void main(String[] args) {
     // 设置正确的 serializer
     MessageSerializer serializer = new GryoMessageSerializerV3d0();
     Cluster cluster = Cluster.build().
         addContactPoint("9.135.246.30").port(8182).
         credentials("stephen", "password").
         serializer(serializer).
         create();
     Client client = cluster.connect();
     cluster.close();
     // 添加属性、点、边等元数据。submit 方法需要捕获异常处理
     GraphTraversalSource g = traversal().withRemote(DriverRemoteConnection.using(cluster));
     System.out.println("marko: " + g.V().hasLabel("v_person").has("person_name", "p_name1").valueMap("person_name", "pid").toList());


 }
}