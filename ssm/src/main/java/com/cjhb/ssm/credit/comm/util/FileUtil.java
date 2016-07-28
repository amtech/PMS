/**
 * 
 */
package com.cjhb.ssm.credit.comm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Title:  FileUtil.java
 * @author 李刚
 * @Copyright: Copyright (c) 2008
 * @Createdate: 2008-9-5
 * @version 1.0
 *
 */
public class FileUtil {

	/**  
     *  新建目录  
     *  @param  folderPath  String  如  c:/test  
     *  @return  boolean  
     */  
   public static void newFolder(String folderPath) {
		try {
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.mkdir();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}  
   
   /**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/test.txt
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static void delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myDelFile = new File(filePath);
			myDelFile.delete();

		} catch (Exception e) {
			e.printStackTrace();
		}

	} 
	
	 /**
	 * 删除文件夹
	 * 
	 * @param filePathAndName
	 *            String 文件夹路径及名称 如c:/test
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete(); // 删除空文件夹

		} catch (Exception e) {
			e.printStackTrace();

		}
	} 
	
	/**  
     *  删除文件夹里面的所有文件  
     *  @param  path  String  文件夹路径  如  c:/test  
     */  
   public static void delAllFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
			}
		}
	}  
   
   /**  
    *  复制单个文件  
    *  @param  oldPath  String  原文件路径  如：c:/test.txt  
    *  @param  newPath  String  复制后路径  如：d:/test_new.txt  
    *  @return  boolean  
    */  
  public static  void  copyFile(String  oldPath,  String  newPath)  {  
      try  {  
          int  byteread  =  0;  
          File  oldfile  =  new  File(oldPath);  
          if  (oldfile.exists())  {  //文件存在时  
              InputStream  inStream  =  new  FileInputStream(oldPath);  //读入原文件  
              FileOutputStream  fs  =  new  FileOutputStream(newPath);  
              byte[]  buffer  =  new  byte[1444];  
              while  (  (byteread  =  inStream.read(buffer))  !=  -1)  {  
                  fs.write(buffer,  0,  byteread);  
              }  
              inStream.close();  
          }  
      }  
      catch  (Exception  e)  {  
          System.out.println("复制单个文件操作出错");  
          e.printStackTrace();  

      }  

  }  
}
