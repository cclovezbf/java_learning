package com.chenchi.learning.java.http;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.*;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class HttpTest1 {

    /**
     * case1
     *
     * app_key：test
     *
     * app_token：c2123456-123-475e-aba5-10aa810f6666
     *
     * timestamp：1627027131
     *
     * sha256签名结果：9d5bbdd61965b5b93a0da9048c3b29dcc9eac07604dfa6eaefe31f0779b7bee6
     *
     * case2
     *
     * app_key：demo666
     *
     * app_token：abcdef56-123-475e-aba5-10aa810fzxcv
     *
     * timestamp：1627027349
     *
     * sha256签名结果：a2385e34fb35d879ad54e3b017c924f1b68e08ba9265e6e85991c78048e78e27
     *
     *     temp_body = {
     *         "condition": {
     *             "staff_name_alias": "kunkkawang"
     *         },
     *         "paginator": {
     *             "pageSize": 10,
     *             "pageIndex": 1
     *         }
     *     }
     *
     *     {"hrgw-timestamp": temp_timestamp, "hrgw-appname": iapp_key, "hrgw-signature": temp_signature}
     * @param args
     */
    public static void main(String[] args) throws Exception {
//        String appKey="s2_fdw_dafa_integration";
        String appKey="s2-fdw-dafa-integration";
        String appToken="832ebb87-8ba1-4b56-857d-56c0e5ec649f";
        String timestamp="1627027131";
        long timeStamp = System.currentTimeMillis() / 1000;
        String signature= sha256Hex(appKey+appToken+timeStamp);
        String url="http://ntsgw.oa.com/api/esb/hr-bidsplus-config/download/s2-fdw-dafa-integration/sub_s2_dw3_dwd_pmi_ps_product_staff_effort/v1/latest";
        System.out.println("appKey="+appKey+",appToken="+appToken+",timestamp="+timestamp);
        System.out.println("signature="+signature);
//        HttpResponse response = HttpRequest.post(url)
//                .header("hrgw-timestamp", String.valueOf(timeStamp))
//                .header("hrgw-appname", appKey)
//                .header("hrgw-signature", signature)
//                .header("Content-Type", ContentType.JSON.getValue())
//                .body(getBody()).execute();
//        getHeader()
        HttpResponse response = HttpRequest.get(url)
                .header("hrgw-timestamp", String.valueOf(timeStamp))
                .header("hrgw-appname", appKey)
                .header("hrgw-signature", signature).execute();
        FileUtil.writeUtf8String(response.body(),new File("\\out"));

    }

    public static String getBody(){
        HashMap<String, Object> condition = new HashMap<>();
        HashMap<String, Object> paginator = new HashMap<>();
        paginator.put("pageSize",1);
        paginator.put("pageIndex",10);
        HashMap<String, Map<String, Object>> body = new HashMap<>();
        body.put("condition",condition);
        body.put("paginator",paginator);
        String bodyString = JSONUtil.toJsonStr(body);
        System.out.println("body="+bodyString);
        return bodyString;
    }

    public static String getHeader(String appKey,String appToken) throws Exception {
        HashMap<String, Object> headers = new HashMap<>();
        long timeStamp = System.currentTimeMillis() / 1000;
        String signature= sha256Hex(appKey+appToken+timeStamp);
        headers.put("hrgw-timestamp",timeStamp);
        headers.put("hrgw-appname",appKey);
        headers.put("hrgw-signature",signature);
//        headers.put("Content-Type", ContentType.JSON.getValue());
        return JSONUtil.toJsonStr(headers);
    }

    public static String sha256Hex(String s) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] d = md.digest(s.getBytes(StandardCharsets.UTF_8));
        return DatatypeConverter.printHexBinary(d).toLowerCase();
    }

    @Test
    public void dopost(){
        String url="http://dev.jarvis-api.woa.com/api/FDWDataService/1.0/getSmsLabel?appid=59989&nonce=vJXtqx07&pageIndex=1&pageSize=20&timestamp=1686043357&vendorName=%E8%85%BE%E8%AE%AF&signature=4AFA1B28A0AAAE260BF8447468D9A90F5E8E8E8CB74560876696A596E9D7E791";
        HttpRequest httpRequest = HttpRequest.get(url);
        HttpResponse execute = httpRequest.execute();
        if (execute.isOk()){
            String body = execute.body();
            String s = JSONUtil.parse(body).toStringPretty();
            System.out.println(s);
        }
    }
}
