package com.chenchi.learning.java.jsch;

import org.junit.Test;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class ProcessTest {
    @Test
    public void commandOnWindow() throws Exception {
        String command="java -version";
        String command1="java -v";
        print(command);
        print(command1);
    }

    private void print(String command) throws Exception {
        System.out.println("exec command :" +command);
        Process process = Runtime.getRuntime().exec(command);
        InputStream errorStream = process.getErrorStream();
        InputStream inputStream = process.getInputStream();
        OutputStream outStream = process.getOutputStream();
        boolean success = process.waitFor(10, TimeUnit.SECONDS);
        if (!success) {
            System.out.println("fail");
            System.out.println("in="+convertStream2String(inputStream));
            System.out.println("error="+convertStream2String(errorStream));
        } else {
            System.out.println("success");
            //执行成功但是失败了
            System.out.println("in="+convertStream2String(inputStream));
            System.out.println("error="+convertStream2String(errorStream));
        }
        System.out.println("status="+success+",exitValue="+process.exitValue());
    }


    private String convertStream2String(InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line;
        StringBuffer inputResult = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            inputResult.append(line + "\n");
        }
        return inputResult.toString();
    }

}
