package com.chenchi.learning.java.jsch;

import cn.hutool.core.io.IoUtil;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Vector;

public class JSchDemo {

    private String ipAddress;   //主机ip
    private String username;   // 账号
    private String password;   // 密码
    private int port;  // 端口号

    Session session;

    public JSchDemo(String ipAddress, String username, String password, int port) {
        this.ipAddress = ipAddress;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    /**
     *  连接到指定的ip
     */
    public void connect() {
        try {
            JSch jsch = new JSch();
            if (port < 0 || port > 65535){
                //连接服务器，如果端口号错误，采用默认端口
                session = jsch.getSession(username, ipAddress);
            }else {
                session = jsch.getSession(username, ipAddress, port);
            }
            //设置登录主机的密码
            session.setPassword(password);
            //如果服务器连接不上，则抛出异常
            if (session == null) {
                throw new Exception("session is null");
            }
            //设置首次登录跳过主机检查
            session.setConfig("StrictHostKeyChecking", "no");
            //设置登录超时时间
            session.connect(3000);
        } catch (Exception e) {

        }

    }
    public int execute(String command) {
        int returnCode = 0;
        ChannelShell channel = null;
        PrintWriter printWriter = null;
        BufferedReader input = null;
        Vector<String> stdout = new Vector<String>();
        try {
            //建立交互式通道
            channel = (ChannelShell) session.openChannel("shell");
            channel.connect();

            //获取输入
            InputStreamReader inputStreamReader = new InputStreamReader(channel.getInputStream());
            input = new BufferedReader(inputStreamReader);

            //输出
            printWriter = new PrintWriter(channel.getOutputStream());
            printWriter.println(command);
            printWriter.println("exit");
            printWriter.flush();
            String line;
            while ((line = input.readLine()) != null) {
                stdout.add(line);
                System.out.println(line);
            }
        } catch (Exception e) {
            return -1;
        }finally {
            IoUtil.close(printWriter);
            IoUtil.close(input);
            if (channel != null) {
                //关闭通道
                channel.disconnect();
            }
        }
        return returnCode;
    }

    public void close(){
        if (session != null) {
            session.disconnect();
        }
    }

}

