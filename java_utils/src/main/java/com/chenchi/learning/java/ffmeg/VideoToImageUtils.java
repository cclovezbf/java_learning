package com.chenchi.learning.java.ffmeg;

import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class VideoToImageUtils {

    private static Logger log= LoggerFactory.getLogger(VideoToImageUtils.class);
    private static String videoPath="C:\\Users\\coder\\Desktop\\交警\\视频\\1.mp4";
    private static String outPutPath =new File(videoPath).getParent();
    private static String fileName =new File(videoPath).getName();
    /**
     * 生成的图片的类型
     */
    private static final String IMAGE_FORMAT = "jpg";

    public static void main(String[] args) throws Exception {
        convert(videoPath);
    }

    public static void convert(String path) throws Exception {
        File imageFile=new File(path);
        FFmpegFrameGrabber imageGrabber = FFmpegFrameGrabber.createDefault(imageFile);
        imageGrabber.start();
        //视频总帧数
        int lengthInFrames = imageGrabber.getLengthInFrames();
        System.out.println("视频总帧数imageGrabber.getLengthInFrames()="+lengthInFrames);
        System.out.println("imageGrabber.getLengthInTime()="+imageGrabber.getLengthInTime()/(double)(1000*1000));
        System.out.println("imageGrabber.getLengthInVideoFrames()="+imageGrabber.getLengthInVideoFrames());
        System.out.println("imageGrabber.getLengthInAudioFrames()="+imageGrabber.getLengthInAudioFrames());
        Java2DFrameConverter converter = new Java2DFrameConverter();
        for (int i = 0; i < 40; i++) {
            Frame frame = imageGrabber.grabImage();
            if (frame.image==null||frame.image.length==0){
                System.out.println("视频第"+i+"帧图像信息为空");
                continue;
            }
            File file = new File(outPutPath + File.separator + fileName+"_" +i+".jpg" );
            if (!file.exists()){
                file.createNewFile();
            }
            // 绘制图片
            BufferedImage bi = converter.getBufferedImage(frame);
            ImageIO.write(bi,IMAGE_FORMAT,file);
        }
        imageGrabber.stop();

    }
}
