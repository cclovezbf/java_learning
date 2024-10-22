package com.chenchi.learning.java.filecopy.demo2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.Socket;


public class SendFile extends Thread {

    private Socket socket = null;
    private DataOutputStream dos;
    private DataInputStream dis;
    private RandomAccessFile rad;

    public SendFile() {

        try {
            socket = new Socket("localhost", 8080);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void run() {

        try {
            String path = "C:\\Users\\coder\\Desktop\\com.chenchi.learning.java.filecopy\\read\\cc.mp4";
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            rad = new RandomAccessFile(path, "r");
            File file = new File(path);
            String filename = file.getName();
            dos.writeUTF(filename);//传输文件名
            dos.flush();
            String rsp = dis.readUTF();//获取响应状态
            if (rsp!=null&&rsp.equals("ok")) {
                long receiveFileSize = dis.readLong();//读取文件已发送的大小
                dos.writeLong(rad.length());
                dos.flush();
                int length;
                if (receiveFileSize < rad.length()) {
                    byte[] buf = new byte[1024];
                    System.err.println("发送文件传输");
                    rad.seek(receiveFileSize);
                    while ((length = rad.read(buf)) > 0) {
                        dos.write(buf, 0, length);
                        dos.flush();
                    }
                }
                else {
                    System.err.println("文件已存在");
                }
            } else {
                System.err.println("文件传输失败");
            }
        } catch (IOException e) {

        } finally {
            try {
                dis.close();
                dos.close();
                rad.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }


}
