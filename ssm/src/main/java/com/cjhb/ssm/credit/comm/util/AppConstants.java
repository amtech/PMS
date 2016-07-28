/**
 * 
 */
package com.cjhb.ssm.credit.comm.util;

import java.util.ResourceBundle;

/**
 * @author teanger
 * @since 2011-5-7
 */
public class AppConstants {

	/**
	 * 常量
	 */
	public static ResourceBundle APP = ResourceBundle.getBundle("app");
	
	
	/**
	 * 上下文变量，用于返回前台JSP使用，作为整个项目的上下文
	 */
	public static String contextPath = APP.getString("CONTEXT_PATH");
	
	
}
