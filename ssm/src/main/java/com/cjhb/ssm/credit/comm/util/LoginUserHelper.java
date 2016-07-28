package com.cjhb.ssm.credit.comm.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.credit.comm.security.FaUserDetails;
import com.credit.entity.system.AuUser;

/**
 * @author: kevin
 * @pageName: com.credit.comm.util
 * @fileName: LoginUserHelp.java
 * @date: 2014-4-3
 * @doc: 用户登录助手
 */
public class LoginUserHelper {
	
	private LoginUserHelper(){
		
	}
	
	public static boolean isSuperAdmin(){
		if (isLogin()){
			if("SUPER_ADMIN".equals(getLoginUserId()) 
					&& "ADMIN".equals(getLoginUserName().toUpperCase())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年5月27日 下午2:18:07
	 * @Description: 是否已经登陆
	 * @return
	 */
	public static boolean isLogin(){
		SecurityContext sc =SecurityContextHolder.getContext();
		if (sc == null){
			return false;
		}
		Authentication auth = sc.getAuthentication();
		if (auth == null){
			return false;
		}
		FaUserDetails userDetails = (FaUserDetails) auth.getPrincipal();
		if (userDetails == null){
			return false;
		}
		return true;
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年5月27日 下午1:27:02
	 * @Description: 获取登陆用户信息
	 * @return
	 */
	public static AuUser getLoginUser(){
		FaUserDetails userDetails = (FaUserDetails) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal();

		return userDetails.getUser();
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年5月27日 下午1:27:17
	 * @Description: 获取登陆用户ID
	 * @return
	 */
	public static String getLoginUserId(){
		FaUserDetails userDetails = (FaUserDetails) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal();

		return userDetails.getUser().getId();
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年5月27日 下午1:27:35
	 * @Description: 获取登陆用户名字
	 * @return
	 */
	public static String getLoginUserName(){
		FaUserDetails userDetails = (FaUserDetails) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal();

		return userDetails.getUser().getUsername();
	}
}
