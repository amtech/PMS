package com.cjhb.pms.home.controller;/*
* Copyright (c) 2016 www.51cjhb.com. All Rights Reserved.
*/

import com.cjhb.pms.domain.home_pojo.UserInfo;
import com.cjhb.pms.service.home.user.IUserService;
import com.cjhb.pms.utils.IP.IPAddressUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title:公共模块管理类 </p>
 * <p>Description:实现公共模块功能 </p>
 * @author edgar
 * @version 1.0
 * @create 2016 07 29 10:32
 */
@Controller
@RequestMapping(value = "common")
public class CommonController extends BaseController{

	//公共模块类日志记录
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private IUserService userService;

	/**
	 * 系统主界面登陆
	 * @param request
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> login(HttpServletRequest request,/* @ModelAttribute("user") */UserInfo userInfo) {

		Map<String, Object> map = new HashMap<String, Object>();
		String ip = IPAddressUtils.getIPAddr(request);
		logger.info("登录用户IP:" + ip);
		String position = IPAddressUtils.getPosition(ip, "utf-8");
		logger.info("地理位置:" + position);

		UserInfo user = userService.login(userInfo.getUserName(), userInfo.getPassword());
		if (null != user) {
			logger.info("登录成功");
			super.setUserInfo(user);
			map.put("result", "success");
		} else {
			logger.info("登录失败");
			map.put("result", "failed");
		}
		return map;
	}



}
