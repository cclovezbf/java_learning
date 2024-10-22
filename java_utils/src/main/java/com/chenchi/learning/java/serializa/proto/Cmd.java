package com.chenchi.learning.java.serializa.proto;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Cmd {
    // protoc的目录
//    private static final String PROTOC_FILE = System.getProperty("user.dir")+ "\\src\\main\\resources\\protoc.exe";
    private static final String PROTOC_FILE = "D:\\install\\java\\protoc-26.0-win64\\bin\\protoc.exe";
    // .proto文件所在项目根目录
    private static final String IMPOR_TPROTO = System.getProperty("user.dir");
    // 生成java类输出目录
    private static final String JAVA_OUT = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\chenchi\\learning\\java\\serializa\\proto";
    // 指定proto文件  D:\install\code\learning\bigdata_learining\java\src\main\java\com\chenchi\learning\java\serializa\proto\cc.proto
    private static final String PROTOS = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\chenchi\\learning\\java\\serializa\\proto\\cc.proto";
    @Test
    public void test(){
        String property = System.getProperty("user.dir"); //D:\install\code\learning\bigdata_learining\java
        System.out.println(property);
    }

    /**
     * 使用java process执行shell命令
     */
    @Test
    public void execute() {
        List<String> lCommand = new ArrayList<String>();
        lCommand.add(PROTOC_FILE);
        lCommand.add("-I=" + IMPOR_TPROTO );
        lCommand.add("--java_out=" + JAVA_OUT);
        lCommand.add(PROTOS);
        ProcessBuilder pb = new ProcessBuilder(lCommand);
        pb.redirectErrorStream(true);
        Process p;
        int i = 1;
        try {
            p = pb.start();
            try {
                //jdk实现process时，调用外部命令不是同步的调用，而是异步执行，需要等待执行完成
                i = p.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int iResult = p.exitValue();
        if (iResult == 0 && i == 0) {
            System.out.println(" result = " + p.exitValue() + ", execute command success! Command = " + lCommand);
        } else {
            System.out.println(" result = " + p.exitValue() + ", execute command failure! Command = " + lCommand);
        }
    }
    // D:\install\java\protoc-26.0-win64\bin\protoc.exe  -I=D:\install\code\learning\bigdata_learining\java  --java_out=D:\install\code\learning\bigdata_learining\java\src\main\java\com\chenchi\learning\java\serializa\proto  D:\install\code\learning\bigdata_learining\java\src\main\java\com\chenchi\learning\java\serializa\proto\cc.proto
}
