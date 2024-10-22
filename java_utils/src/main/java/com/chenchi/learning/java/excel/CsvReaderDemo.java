package com.chenchi.learning.java.excel;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import org.junit.Test;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

public class CsvReaderDemo {
    public static void main(String[] args) {
        String path="F:\\notepad\\wbp_info.csv";
        FileReader fileReader = FileReader.create(new File(path));
        List<String> list = fileReader.readLines();
        for (int i = 0; i < 10; i++) {
            System.out.println(list.get(i));
        }
    }
    @Test
    public  void  testCsv(){
        String path="F:\\notepad\\wbp_info.csv";
        CsvReader csvReader = new CsvReader();
        CsvData read = csvReader.read(new File(path));
        CsvRow row = read.getRow(3);
        System.out.println(row);
    }
}
