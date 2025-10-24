package com.chenchi;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static void main(String[] args) {
        //TIP 当文本光标位于高亮显示的文本处时按 <shortcut actionId="ShowIntentionActions"/>
        // 查看 IntelliJ IDEA 建议如何修正。
        File file = new File("D:\\code_bak\\code\\tencent\\dw_ia_portraitsearch");
        List<File> resDir = getResDir(file);
        List<File> sqls=new ArrayList<>();
        for (File file1 : resDir) {
            System.out.println(file1.getAbsolutePath());
            File[] files = file1.listFiles((dir, name) -> name.endsWith("sql"));
            if (files != null) {
                sqls.addAll(Arrays.asList(files));
            }
        }

        for (File sql : sqls) {
            System.out.println("sql="+sql.getAbsolutePath());
            String s = FileUtil.readString(sql, Charset.defaultCharset());
            if (s.contains("dwintdata.dw_apply_vendor_detail")){
                System.out.println("------------------------");
            }
        }
        File[] files = file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if(name.endsWith(".sql")){
                    System.out.println(name);
                }

                return false;
            }
        });
        if (files != null) {
            for (File file1 : files) {
                System.out.println(file1.getAbsolutePath());
            }
        }
    }
    private  static  List<File> list = new ArrayList<>();
    private static List<File> getResDir(File file){
        File[] listFiles = file.listFiles(File::isDirectory);
        if(listFiles != null){
            List<File> files = Arrays.asList(listFiles);
            list.addAll(files);
                for (File file1 : files) {
                    getResDir(file1);
                }
                return list;
        }
        return list;
    }

    }
