package com.cjhb.pms.service.home.user;/*
* Copyright (c) 2016 www.51cjhb.com. All Rights Reserved.
*/

import com.cjhb.pms.domain.home_pojo.UserInfo;

/**
 * <p>Title: 用户管理服务接口</p>
 * <p>Description: 用户管理对外服务暴露入口</p>
 * @author edgar
 * @version 1.0
 * @create 2016 07 28 13:53
 */
public interface IUserService {

	/**
	 * 根据用户名和密码查询用户信息
	 * @param userName
	 * @param password
	 * @return
	 */
	UserInfo login(String userName, String password);
}
