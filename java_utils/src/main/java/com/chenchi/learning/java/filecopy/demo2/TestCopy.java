package com.chenchi.learning.java.filecopy.demo2;

public class TestCopy {
}
 class FileReceiveTest{//接收方

    public static void main(String[] args) {

        ReceiveFile rf=new ReceiveFile();
        rf.start();
    }

}


 class FileSendTest{//发送方

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SendFile sf=new SendFile();
        sf.start();
    }

}

