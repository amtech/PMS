package com.cjhb.pms.service.home.user.Impl;/*
* Copyright (c) 2016 www.51cjhb.com. All Rights Reserved.
*/

import com.cjhb.pms.dao.home.user.UserInfoMapper;
import com.cjhb.pms.domain.home_pojo.UserInfo;
import com.cjhb.pms.domain.home_pojo.model.UserInfoExample;
import com.cjhb.pms.service.home.user.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description: 用户管理服务接口
 * @author edgar
 * @create 2016-7-29 16:07:12
 * @version 1.0
 */
@Service("userService")
public class UserService implements IUserService{

	//UserService 日志记录
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private UserInfoMapper userInfoMapper;

	/**
	 * 根据登陆信息查询用户信息
	 * @param userName
	 * @param password
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public UserInfo login(String userName, String password) {
		logger.info("验证用户");
		UserInfoExample userInfoExample = new UserInfoExample();
		userInfoExample.createCriteria().andUserNameEqualTo(userName);
		userInfoExample.createCriteria().andPasswordEqualTo(password);
		List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);
		UserInfo userInfo = userInfos.get(0);

		return userInfo;
	}

}
