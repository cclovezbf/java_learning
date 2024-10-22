package com.chenchi.learning.java.excel;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.util.List;
import java.util.Map;

public class ExcelTest {

    public static void main(String[] args) {
        String path="D:\\install\\code\\learning\\bigdata_learining\\src\\main\\resources\\测试客户名单.xlsx";
        readExcel(path);
    }

    private static void readExcel(String filePath){
        ExcelReader reader = ExcelUtil.getReader(filePath);
        List<Map<String, Object>> maps = reader.readAll();
        int i=0 ;
        for (Map<String, Object> map : maps) {
            System.out.println("------------"+(i++) +"----------------");
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println(entry.getKey()+"---"+entry.getValue());

            }
        }
    }
    private static void  readExcel2(String filePath){
        ExcelReader reader = ExcelUtil.getReader(filePath);
        List<List<Object>> readAll = reader.read();
        int i=0 ;
        for (List<Object> objects : readAll) {
            System.out.println("------------"+(i++) +"----------------");
            for (Object object : objects) {
                System.out.println(object);
            }
        }
    }
}
