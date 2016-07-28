package com.cjhb.pms.home.controller;/*
* Copyright (c) 2016 www.51cjhb.com. All Rights Reserved.
*/


import com.cjhb.pms.service.home.user.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Title: 用户登录类</p>
 * <p>Description: 后台用户登录管理模块，用户登录操作等</p>
 *
 * @author edgar
 * @version 1.0
 * @create 2016 07 28 13:49
 */
@Controller
@RequestMapping(value = "login")
public class LoginController {

	//日志记录
	private Logger logger = Logger.getLogger(LoginController.class);

	//service 服务接口注入
	@Autowired //注入用户服务接口
	private IUserService userService;

	@RequestMapping(value = "login")
	public String login(Model model){
		//String username = "";
		//String password = "";
		//userService.getLoginUser(username,password);
		return "login";

	}


}
