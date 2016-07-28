package com.cjhb.ssm.credit.system.web;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author liuchuanhong
 * @since 2014/03/07
 *
 */
@Controller
public class BaseController {

	public static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

	protected final Logger log = Logger.getLogger(getClass().getName());
	
	/**
	 * @author: kevin
	 * @date: 2015年5月14日 下午4:10:58
	 * @Description: 处理页面参数序列化后数据类型的问题
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat dataSdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dataTimesdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dataSdf, true));
		binder.registerCustomEditor(Timestamp.class, new CustomDateEditor(dataTimesdf, true));
		binder.registerCustomEditor(List.class, new CustomCollectionEditor(List.class, true));
	}
	
	/**
	 * 获取客户端真实IP地址
	 */
	public String getIpAddr(HttpServletRequest request) {
		if (request == null) {
			return null;
		}
		String ip = request.getRemoteAddr();

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-forwarded-for");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		return ip;
	}
	
}
