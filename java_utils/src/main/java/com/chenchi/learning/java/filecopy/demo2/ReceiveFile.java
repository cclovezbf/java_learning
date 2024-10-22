package com.chenchi.learning.java.filecopy.demo2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;


public class ReceiveFile extends Thread {

    private ServerSocket connectSocket = null;
    private Socket socket = null;
    private DataInputStream dis;
    private DataOutputStream dos;
    private RandomAccessFile rad;


    public void run() {
        try {
            connectSocket = new ServerSocket(8080);
            socket = connectSocket.accept();
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            if (true) {
                String filename = dis.readUTF();//第一次交互查看文件名
                System.err.println(filename);
                dos.writeUTF("ok");//交互确认信息
                dos.flush();
                rad = new RandomAccessFile(filename, "rw");
                ReceiveFile receiveFile = new ReceiveFile();
                Long receiveFileSize = receiveFile.selectFile(filename);//查看已下载的文件大小
                dos.writeLong(receiveFileSize);//发送已接收的大小
                dos.flush();
                long sendFileSize = dis.readLong();//已发送出来的文件大小
                //如果文件已存在则重新下载
                if (receiveFileSize >= sendFileSize) {
                    System.err.println("文件已存在");
                } else {
                    rad.seek(receiveFileSize);
                    int length;
                    byte[] buf = new byte[1024];
                    while ((length = dis.read(buf)) != -1) {
                        rad.write(buf, 0, length);
                    }//接收文件
                    System.out.println("end");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dis.close();
                dos.close();
                rad.close();
                socket.close();
                connectSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Long selectFile(String filename) {
        Long fileLength = 0L;
        File file = new File(filename);
        if (file.exists() && file.isFile()) {
            fileLength = file.length();
        }

        return fileLength;
    }


}


