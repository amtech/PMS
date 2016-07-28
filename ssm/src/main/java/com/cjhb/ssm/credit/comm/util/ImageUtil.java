/**
 * 
 */
package com.cjhb.ssm.credit.comm.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @author teanger
 *
 */
public class ImageUtil {
	
	/**
	 *  图片宽和高的最大尺寸  
	 */
    public static final int IMAGEMAXBIG = 2000;  
    /**
     * 图片宽和高的最小尺寸  
     */
    public static final int IMAGEMINBIG = 10;  
    /**
     *  按原图大小生成新图  
     */
    public static final int CREATENEWIMAGETYPE_0 = 0;  
    /**
     *  按指定的大小生成新图  
     */
    public static final int CREATENEWIMAGETYPE_1 = 1;  
    /**
     * 按原图宽高比例生成新图-按指定的宽度  
     */
    public static final int CREATENEWIMAGETYPE_2 = 2;  
    /**
     *  按原图宽高比例生成新图-按指定的高度  
     */
    public static final int CREATENEWIMAGETYPE_3 = 3;  
    /**
     *  按原图宽高比例生成新图-按指定的宽和高中较大的尺寸  
     */
    public static final int CREATENEWIMAGETYPE_4 = 4;  
    /**
     * 按原图宽高比例生成新图-按指定的宽和高中较小的尺寸  
     */
    public static final int CREATENEWIMAGETYPE_5 = 5;
    
    /** 
     * @param _file 原图片 
     * @param prex 前缀
     * @param sourceName 源文件名
     * @param createType 处理类型 
     * @param newW 新宽度 
     * @param newH 新高度 
     */  
	public static String createNewImage(File _file, String prex,
			String sourceName, int createType, int newW, int newH)
			throws Exception {
        if (_file == null)  
            return null;  
        String fileName = _file.getPath();  
        if (fileName == null || "".equals(fileName)  
                || fileName.lastIndexOf(".") == -1)  
            return null;  
  
        String outFileName = fileName.replace(sourceName, prex+sourceName);
        String fileExtName = fileName.substring(  
                (fileName.lastIndexOf(".") + 1), fileName.length());  
        if (newW < IMAGEMINBIG)  
            newW = IMAGEMINBIG;  
        else if (newW > IMAGEMAXBIG)  
            newW = IMAGEMAXBIG;  
  
        if (newH < IMAGEMINBIG)  
            newH = IMAGEMINBIG;  
        else if (newH > IMAGEMAXBIG)  
            newH = IMAGEMAXBIG;  
  
        // 得到原图信息  
        if (!_file.exists() || !_file.isAbsolute() || !_file.isFile()  
                || !checkImageFile(fileExtName))  
            return null;  
        if ((new File(outFileName)).exists()) {  
            System.out.println("file [" + outFileName + "] already exists");  
            throw new Exception();  
        }  
        Image src = ImageIO.read(_file);  
        int w = src.getWidth(null);  
        int h = src.getHeight(null);  
  
        // 确定目标图片的大小  
        int nw = w;  
        int nh = h;  
        if (createType == CREATENEWIMAGETYPE_0)  
            ;  
        else if (createType == CREATENEWIMAGETYPE_1) {  
            nw = newW;  
            nh = newH;  
        } else if (createType == CREATENEWIMAGETYPE_2) {  
            nw = newW;  
            nh = (int) ((double) h / (double) w * nw);  
        } else if (createType == CREATENEWIMAGETYPE_3) {  
            nh = newH;  
            nw = (int) ((double) w / (double) h * nh);  
        } else if (createType == CREATENEWIMAGETYPE_4) {  
            if ((double) w / (double) h >= (double) newW / (double) newH) {  
                nh = newH;  
                nw = (int) ((double) w / (double) h * nh);  
            } else {  
                nw = newW;  
                nh = (int) ((double) h / (double) w * nw);  
            }  
        } else if (createType == CREATENEWIMAGETYPE_5) {  
            if ((double) w / (double) h <= (double) newW / (double) newH) {  
                nh = newH;  
                nw = (int) ((double) w / (double) h * nh);  
            } else {  
                nw = newW;  
                nh = (int) ((double) h / (double) w * nw);  
            }  
        }  
  
        // 构造目标图片  
        BufferedImage tag = new BufferedImage(nw, nh,  
                BufferedImage.TYPE_INT_RGB);  
  
        // 得到目标图片输出流  
        FileOutputStream out = new FileOutputStream(outFileName);  
  
        // 根据需求画出目标图片 方式1  
        tag.getGraphics().drawImage(src, 0, 0, nw, nh, null);  
  
        // 将画好的目标图输出到输出流 方式1  
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
        JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
        jep.setQuality(0.75f, true);
        encoder.encode(tag,jep);  
        out.close();  
        return outFileName;  
    }  
  
    public static boolean checkImageFile(String extName) {  
  
        if ("jpg".equalsIgnoreCase(extName))  
            return true;  
        if ("gif".equalsIgnoreCase(extName))  
            return true;  
        if ("bmp".equalsIgnoreCase(extName))  
            return true;  
        if ("jpeg".equalsIgnoreCase(extName))  
            return true;  
        if ("png".equalsIgnoreCase(extName))  
            return true;  
        return false;  
    }  

	/**  
     * 把图片印刷到图片上  
     * @param pressImg -- 水印文件  
     * @param targetImg -- 目标文件  
     * @param x  
     * @param y  
     */  
    public final static void pressImage(String pressImg, String targetImg,   
            int x, int y) {   
        try {   
            File _file = new File(targetImg);   
            Image src = ImageIO.read(_file);   
            int wideth = src.getWidth(null);   
            int height = src.getHeight(null);   
            BufferedImage image = new BufferedImage(wideth, height,   
                    BufferedImage.TYPE_INT_RGB);   
            Graphics g = image.createGraphics();   
            g.drawImage(src, 0, 0, wideth, height, null);   
  
            // 水印文件   
            File _filebiao = new File(pressImg);   
            Image src_biao = ImageIO.read(_filebiao);   
            int wideth_biao = src_biao.getWidth(null);   
            int height_biao = src_biao.getHeight(null);   
            g.drawImage(src_biao, wideth - wideth_biao - x, height   
                    - height_biao - y, wideth_biao, height_biao, null);   
            // /   
            g.dispose();   
            FileOutputStream out = new FileOutputStream(targetImg);   
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(image);
            jep.setQuality(0.75f, true);
            encoder.encode(image,jep);   
            out.close();   
        } catch (Exception e) {   
            e.printStackTrace();   
        }   
    }   
  
    /**  
     * 打印文字水印图片  
     * @param pressText  -- 文字  
     * @param targetImg -- 目标图片  
     * @param fontName -- 字体名  
     * @param fontStyle -- 字体样式  
     * @param color -- 字体颜色  
     * @param fontSize -- 字体大小  
     * @param x -- 偏移量  
     * @param y  
     */  
    public static void pressText(String pressText, String targetImg,   
            String fontName, int fontStyle, int color, int fontSize, int x,   
            int y) {   
        try {   
            File _file = new File(targetImg);   
            Image src = ImageIO.read(_file);   
            int wideth = src.getWidth(null);   
            int height = src.getHeight(null);   
            BufferedImage image = new BufferedImage(wideth, height,   
                    BufferedImage.TYPE_INT_RGB);   
            Graphics g = image.createGraphics();   
            g.drawImage(src, 0, 0, wideth, height, null);   
            // String s="www.qhd.com.cn";   
            g.setColor(Color.RED);   
            g.setFont(new Font(fontName, fontStyle, fontSize));   
  
            g.drawString(pressText, wideth - fontSize - x, height - fontSize   
                    / 2 - y);   
            g.dispose();   
            FileOutputStream out = new FileOutputStream(targetImg);   
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);   
            encoder.encode(image);   
            out.close();   
        } catch (Exception e) {   
            System.out.println(e);   
        }   
    }   
    
}
