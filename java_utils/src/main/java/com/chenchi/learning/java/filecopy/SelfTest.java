package com.chenchi.learning.java.filecopy;

import java.io.FileInputStream;
import java.io.RandomAccessFile;

public class SelfTest {
    public static void main(String[] args) throws Exception {
       testFileInputStream();

    }

    public static void testFileInputStream() throws Exception {
        FileInputStream inputStream = new FileInputStream("C:\\Users\\coder\\Desktop\\com.chenchi.learning.java.filecopy\\read\\cc.mp4");
        System.out.println(inputStream.available());

    }

    public static void testRandomAccessFile() throws Exception {
        RandomAccessFile inputFile = null;
        RandomAccessFile outputFile = null;
        try {
            String input = "C:\\Users\\coder\\Desktop\\com.chenchi.learning.java.filecopy\\read\\cc.mp4";
            String output = "C:\\Users\\coder\\Desktop\\com.chenchi.learning.java.filecopy\\writer\\cc.mp4";
            inputFile = new RandomAccessFile(input, "r");
            outputFile = new RandomAccessFile(output, "rw");
            byte[] bytes = new byte[1024*1024];
            int length = 0;
            System.out.println(inputFile.length());
            //
//            inputFile.seek(2097152);
//            outputFile.seek(2097152);
            while ((length = inputFile.read(bytes)) != -1) {
                long filePointer = inputFile.getFilePointer();
                System.out.println("point="+filePointer);
                outputFile.write(bytes, 0, length);
//                if (filePointer>1048576){  //传到一半抛异常
//                    throw new Exception();
//                }
            }
        } finally {
            inputFile.close();
            outputFile.close();
        }
    }
}
