/**
 * 
 */
package com.cjhb.ssm.credit.comm.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Title: WriteFile.java
 * 
 * @author 李刚
 * @Copyright: Copyright (c) 2007
 * @Createdate: May 21, 2007
 * @version 1.0
 * 
 */
public class WriteFile {
	private FileWriter writer;
    private OutputStreamWriter streamWriter;
	private PrintWriter pw;

	/**
	 * 构造新文件
	 * @param filePath 完整文件路径，如C:\\test.txt
	 */
	public WriteFile(String filePath) {
		try {
			writer = new FileWriter(filePath);
			pw = new PrintWriter(writer);
		} catch (IOException iox) {
			System.err.println(iox);
		}

	}
	
	public WriteFile(String filePath, String charset){
		try {
			streamWriter = new OutputStreamWriter(new FileOutputStream(filePath), charset);
			pw = new PrintWriter(streamWriter);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 写入一行内容
	 * @param in 内容
	 */
	public void aLine(String in) { 
		// 写入一行
		pw.print(in+"\n");
		pw.flush();
	}

	/**
	 * 关闭输入流，将文字从缓存写入文件
	 */
	public void finish() {
		try {
			
			if (writer!=null){
				writer.close();
			}
			
			if (streamWriter!=null){
				streamWriter.close();
			}
		} catch (IOException iox) {
			System.err.println(iox);
		}

	}
}
