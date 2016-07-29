package com.cjhb.pms.home.controller;/*
* Copyright (c) 2016 www.51cjhb.com. All Rights Reserved.
*/

import com.cjhb.pms.domain.home_pojo.UserInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

/**
 * <p>Title:抽取公共模块功能类 </p>
 * <p>Description:公共通用方法抽取：session 会话管理等 </p>
 * @author edgar
 * @version 1.0
 * @create 2016 07 26 16:57
 */
@Controller
public class BaseController {

	//BaseController类 日志记录
	private Logger logger =Logger.getLogger(this.getClass());

	@Autowired //session 管理
	private HttpSession session;

	/**
	 * 将用户信息加入session
	 * @param userInfo
	 */
	public void setUserInfo(UserInfo userInfo) {
		if (null != userInfo) {
			session.setAttribute("userInfo", userInfo);
			session.setMaxInactiveInterval(1800);// 设置session超时时间 超过时间销毁session
		}
	}
}
