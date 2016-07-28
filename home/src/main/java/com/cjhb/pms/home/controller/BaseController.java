package com.cjhb.pms.home.controller;/*
* Copyright (c) 2016 www.51cjhb.com. All Rights Reserved.
*/

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Title:抽取 base Controller类 </p>
 * <p>Description:公共通用方法抽取 </p>
 * @author edgar
 * @version 1.0
 * @create 2016 07 26 16:57
 */
@Controller
@RequestMapping(value = "base")
public class BaseController {

	//日志记录
	private Logger logger =Logger.getLogger(BaseController.class);

}
