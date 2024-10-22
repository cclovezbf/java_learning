package com.chenchi.learning.java.ffmeg;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 视频文件处理工具类 https://blog.51cto.com/u_1197822/2455209
 */
public class VideoGraber {
    private static final Logger logger = LoggerFactory.getLogger(VideoGraber.class);
    private static final String ROTATE = "rotate";
    /**
     * 生成的图片的类型
     */
    private static final String IMAGE_FORMAT = "jpg";

    public static void main(String[] args) {
        createScreenShot("C:\\Users\\coder\\Desktop\\以图搜图\\235b8473c831fafca4b070a772f73489.mp4", 0);
    }

    /**
     * 在相同的目录下生成相同的名称的图片
     * @param videoFileName 视频文件地址
     * @param grabFrameIndex 抓取第几帧
     */
    public static void createScreenShot(String videoFileName, int grabFrameIndex) {
        // 第一帧图片存储位置(也是视频路径)
        String targetFilePath = new File(videoFileName).getParent();
        // 视频文件名
        String fileName = new File(videoFileName).getName();
        String saveImageFileName = targetFilePath + File.separator + fileName+ UUID.randomUUID() + "." + IMAGE_FORMAT;

        logger.info("{},{},{}", targetFilePath, fileName, saveImageFileName);

        createScreenShot(videoFileName, grabFrameIndex, saveImageFileName);
    }

    /**
     * 获取一张视频截图并保存同名的jpg文件到指定目录
     *
     * @param videoFileName 视频文件地址
     * @param saveImageFileName 截图的图片存放路径(绝对路径包含文件名称)
     * @param grabFrameIndex 抓取第几帧[from zero]
     */
    public static void createScreenShot(String videoFileName, int grabFrameIndex, String saveImageFileName) {
        logger.info("视频文件[{}]截图开始", videoFileName);

        FFmpegFrameGrabber grabber = null;
        try {
            grabber = FFmpegFrameGrabber.createDefault(videoFileName);
            grabber.start();
            // 视频总帧数
            int videoLength = grabber.getLengthInFrames();

            Frame frame = null;
            int i = 0;
            while (i < videoLength) {
                // 过滤前grabFrameIndex帧,避免出现全黑的图片,依自己情况而定(每循环一次取一帧)
                frame = grabber.grabFrame();
                if ((i > grabFrameIndex) && (frame.image != null)) {
                    break;
                }
                i++;
            }

            // 视频旋转度
            String rotate = grabber.getVideoMetadata(ROTATE);
            Java2DFrameConverter converter = new Java2DFrameConverter();
            // 绘制图片
            BufferedImage bi = converter.getBufferedImage(frame);
            if (rotate != null) {
                // 旋转图片
                bi = rotate(bi, Integer.parseInt(rotate));
            }
            ImageIO.write(bi, IMAGE_FORMAT, new File(saveImageFileName));

            /*Map<String, Object> result = new HashMap<>();
            // 拼接Map信息
            result.put("videoLength", videoLength); // 视频总帧数
            result.put("videoWide", bi.getWidth()); // 视频的宽
            result.put("videoHigh", bi.getHeight());// 视频的高
            result.put("rotate", (null == rotate || "".equals(rotate))? "0" : rotate); // 视频的旋转度
            result.put("format", grabber.getFormat()); // 视频的格式
            result.put("duration", grabber.getLengthInTime() / (1000 * 1000));// 此视频时长(s/秒)
            result.put("imageFile", saveImageFileName);*/

            logger.info("视频文件[{}]截图结束,图片地址为[{}]", videoFileName, saveImageFileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(null != grabber){
                try {
                    grabber.stop();
                } catch (FrameGrabber.Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * <h5>功能:根据视频旋转度来调整图片</h5>
     *
     * @param src 捕获的图像
     * @param angel 视频旋转度
     * @return BufferedImage
     */
    private static BufferedImage rotate(BufferedImage src, int angel) {
        int srcWidth = src.getWidth(null);
        int srcHeight = src.getHeight(null);
        int type = src.getColorModel().getTransparency();
        Rectangle rect_des = calcRotatedSize(new Rectangle(new Dimension(srcWidth, srcHeight)), angel);
        BufferedImage bi = new BufferedImage(rect_des.width, rect_des.height, type);
        Graphics2D g2 = bi.createGraphics();
        g2.translate((rect_des.width - srcWidth) / 2, (rect_des.height - srcHeight) / 2);
        g2.rotate(Math.toRadians(angel), srcWidth / 2, srcHeight / 2);
        g2.drawImage(src, 0, 0, null);
        g2.dispose();
        return bi;
    }

    /**
     * <h5>功能:计算图片旋转大小</h5>
     *
     * @param src 屏幕坐标中捕获的矩形区域
     * @param angel 视频旋转度
     */
    private static Rectangle calcRotatedSize(Rectangle src, int angel) {
        if (angel >= 90) {
            if (angel / 90 % 2 == 1) {
                int temp = src.height;
                src.height = src.width;
                src.width = temp;
            }
            angel = angel % 90;
        }
        double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
        double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
        double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
        double angel_dalta_width = Math.atan((double) src.height / src.width);
        double angel_dalta_height = Math.atan((double) src.width / src.height);
        int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_width));
        int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_height));
        int des_width = src.width + len_dalta_width * 2;
        int des_height = src.height + len_dalta_height * 2;
        return new Rectangle(new Dimension(des_width, des_height));
    }
}