package com.chenchi.learning.java.xml;

import cn.hutool.core.util.XmlUtil;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * <?xml version="1.0" encoding="UTF-8"?>
 * <sqls>
 *     <sql id="getId">
 *         select id from table1
 *     </sql>
 *     <sql id="getName">
 *         select name from table1
 *     </sql>
 * </sqls>
 */
public class XmlDemo {
    @Test
    public void readXml(){
        Document document = XmlUtil.readXML("D:\\install\\code\\learning\\bigdata_learining\\java\\src\\main\\resources\\xml\\test.xml");
        //获取 sqls节点
        Element documentElement = document.getDocumentElement();
        System.out.println(documentElement);
        //获取多个 sql节点
        NodeList sqlList = documentElement.getElementsByTagName("sql");
        //根据顺序去获取每个sql节点
        Node sql1 = sqlList.item(0);
        Node sql2 = sqlList.item(1);
        //直接获取sql节点的内容
        String textContent = sql1.getTextContent();
        //获取sql标签里属性
        Node id = sql2.getAttributes().getNamedItem("id");
        //获取属性的value
        String idValue = id.getNodeValue();

//        System.out.println(document);
//        NodeList sqls = document.getElementsByTagName("sqls");
//        sqls.
    }
}
