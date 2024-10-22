package com.chenchi.learning.java.image.compressor;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class ImageCompressor {

    public static void compressImage(File inputFile, File outputFile, float quality) throws IOException {
        // 读取图片
        BufferedImage image = ImageIO.read(inputFile);

        // 获取图片写入工具
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpeg");
        ImageWriter writer = writers.next();

        // 设置写入参数
        ImageWriteParam param = writer.getDefaultWriteParam();
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(quality); // 设置压缩质量

        // 将BufferedImage写入ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        IIOImage iioImage = new IIOImage(image, null, null);
        writer.setOutput(ImageIO.createImageOutputStream(outputStream));
        writer.write(null, iioImage, param);

        // 将压缩后的图片写入文件
        ImageIO.write(image, "jpeg", outputFile);

        // 关闭输出流
        outputStream.close();
        writer.dispose();
    }

    public static void main(String[] args) {
        try {
            File inputFile = new File("input.jpg"); // 原始图片文件
            File outputFile = new File("output.jpg"); // 压缩后的图片文件
            float quality = 0.75f; // 压缩质量，范围0.0到1.0
            compressImage(inputFile, outputFile, quality);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}