package com.chenchi.learning.java.filecopy.demo1;

public class FileCopy {


}

//接收方先启动
 class FileRecevieTest {
    public static void main(String[] args) {
        ReceiveFileThread ddxcreceiveFile = new ReceiveFileThread();
        ddxcreceiveFile.start();
    }
}

//发送方后启动
 class FileSendTest {
    public static void main(String[] args) {
         SendFileThread sendFileThread = new SendFileThread();
        sendFileThread.start();
    }
}