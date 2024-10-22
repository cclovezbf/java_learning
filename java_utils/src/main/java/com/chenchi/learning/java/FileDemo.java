package com.chenchi.learning.java;

import java.io.*;

public class FileDemo {

    private void inputStream2File(InputStream inputStream, File file) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(fos,1024*8*16);
        byte[] buffer = new byte[1024*8*8];
        int length;
        while ((length = bis.read(buffer)) != -1) {
            bos.write(buffer, 0, length);
        }
        bos.close();
        fos.close();
        bis.close();
        inputStream.close();
    }
}
