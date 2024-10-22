package com.chenchi.learning.java.http;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.http.webservice.SoapClient;
import cn.hutool.http.webservice.SoapProtocol;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.UUID;

public class SoapTest {
    @Test
    public void doTestEpo() {
// 新建客户端
//        SoapClient client = SoapClient.create("http://www.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx")
//                // 设置要请求的方法，此接口方法前缀为web，传入对应的命名空间
//                .setMethod("web:getCountryCityByIp", "http://WebXml.com.cn/")
//                // 设置参数，此处自动添加方法的前缀：web
//                .setParam("theIpAddress", "218.21.240.106");
        SoapClient client = SoapClient.create("http://gas.oa.com/gas/services/gas.asmx", SoapProtocol.SOAP_1_1,"http://tempuri.org/")
                // 设置要请求的方法，此接口方法前缀为web，传入对应的命名空间
                .setMethod("tem:GetAttachmentByAppSimple")
                // 设置参数，此处自动添加方法的前缀：web
                .setParam("appid", "AF0D765D0F7408DA9442131275A918FE")
                .setParam("sysid", "700");
        HashMap<String, String> headers = new HashMap<>();
        headers.put("SOAPAction", "http://tempuri.org/GetAttachmentByAppSimple");
        client.addHeaders(headers);
        // 发送请求，参数true表示返回一个格式化后的XML内容
        // 返回内容为XML字符串，可以配合XmlUtil解析这个响应
        String msgStr = client.getMsgStr(true);
        Console.log(msgStr);
        Console.log(client.send(true));
        Document document = XmlUtil.parseXml(msgStr);
        Element documentElement = document.getDocumentElement();
        System.out.println(documentElement.getPrefix());
        System.out.println(documentElement.getTextContent());

    }
@Test
public void test(){
        String formula="(FDC20 + (FDC27 + FDC28 + FDC4 + FDC52 + FDC53 + FDC54 + FDC55 ) )- FDD45 - FDD46 *1.2 ";
    String s = formula.replaceAll("[A-Z]+\\d+", "0");
    System.out.println(s);
}
    @Test
    public void testTpp() throws Exception {
        String appkey="";
        String token="";
        String secret="";
        String timestamp=String.valueOf(System.currentTimeMillis() / 1000);
        String nonce= UUID.randomUUID().toString().substring(24);
        String sign = sha256Hex(appkey + token + secret + timestamp + nonce);
        HashMap<String, String> headers = new HashMap<>();
        headers.put("x-rasse-appkey","");
        headers.put("x-rasse-token","");
        headers.put("x-rasse-timestamp",timestamp);
        headers.put("x-rasse-nonce",nonce);
        headers.put("x-rasse-signature",sign);

    }
    public  String sha256Hex(String s) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] d = md.digest(s.getBytes(StandardCharsets.UTF_8));
        return DatatypeConverter.printHexBinary(d).toLowerCase();
    }
}
