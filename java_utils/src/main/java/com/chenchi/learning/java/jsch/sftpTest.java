package com.chenchi.learning.java.jsch;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class sftpTest {
    public static void main(String[] args) {
        Session session = null;
        ChannelSftp channelSftp = null;
        String host="9.134.70.1";
        String username="devuser";
        String password="S2_fin_bigdata&123";
        int port = 36000;
        int timeout=60000;
        String mode ="PASV";
        JSch jsch = new JSch(); // 创建JSch对象
        try {
            session = jsch.getSession(username, host, port);
            // 根据用户名，主机ip，端口获取一个Session对象
            // 如果服务器连接不上，则抛出异常
            if (session == null) {

            }
            session.setPassword(password); // 设置密码
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config); // 为Session对象设置properties
            session.setTimeout(timeout); // 设置timeout时间
            session.connect(); // 通过Session建立链接

            channelSftp = (ChannelSftp) session.openChannel("sftp"); // 打开SFTP通道
            channelSftp.connect(); // 建立SFTP通道的连接
            uploadFile(channelSftp);
            channelSftp.disconnect();
            session.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void uploadFile(ChannelSftp channelSftp) throws Exception {
        channelSftp.cd("/data/cc_test");
        OutputStream outputStream = channelSftp.put("初来乍到第三季-02.mp4", ChannelSftp.APPEND);
        outputStream.write(new byte[1],0,1);
        System.out.println(outputStream);
    }

    private static void copyFile(ChannelSftp channelSftp) throws Exception {
        InputStream inputStream = channelSftp.get("/data/cc_test/file/阳光电影www.ygdy8.com.脱单告急.HD.720p.国语中字.mkv");
        byte[] bytes = new byte[1024 * 1024];
        long start = System.currentTimeMillis();
        int length=0;
        FileOutputStream outputStream = new FileOutputStream("C:\\Users\\coder\\Desktop\\com.chenchi.learning.java.filecopy\\脱单告急.HD.720p.国语中字.mkv");
        while ((length=inputStream.read(bytes))!=-1){
            System.out.println("available="+inputStream.available()+",length="+length);
            outputStream.write(bytes,0,length);
        }
        System.out.println("copy finished cost time="+(System.currentTimeMillis()-start)/1000);
        inputStream.close();
        outputStream.close();
    }
}
