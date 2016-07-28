package com.cjhb.pms.service.home.user;/*
* Copyright (c) 2016 www.51cjhb.com. All Rights Reserved.
*/

/**
 * <p>Title: 用户管理服务接口</p>
 * <p>Description: 用户管理对外服务暴露入口</p>
 * @author edgar
 * @version 1.0
 * @create 2016 07 28 13:53
 */
public interface IUserService {

	void getLoginUser(String username, String password);
}
