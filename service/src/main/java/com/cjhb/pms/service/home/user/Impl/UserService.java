package com.cjhb.pms.service.home.user.Impl;/*
* Copyright (c) 2016 www.51cjhb.com. All Rights Reserved.
*/

import com.cjhb.pms.dao.mapper.UserMapper;
import com.cjhb.pms.domain.home.model.User;
import com.cjhb.pms.service.home.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserMapper userMapper;

	@Override
	public void getLoginUser(String username, String password) {
		User user = userMapper.selectByPrimaryKey(1);
	}
}
