package com.cjhb.pms.home.Validator;

import com.cjhb.pms.domain.home_pojo.UserInfo;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @Description: 登陆信息校验类
 * @author edgar
 * @create 2016-7-29 15:00:51
 * @version 1.0
 */
public class LoginValidator implements Validator {

	@Override
	public boolean supports(Class<?> cls) {
		return cls.equals(UserInfo.class);
	}

	@Override
	public void validate(Object object, Errors errors) {
		// User user = (User) object;
		
		 ValidationUtils.rejectIfEmpty(errors, "userName", "用户名不能为空");
		 
		 ValidationUtils.rejectIfEmpty(errors, "password", "密码不能为空");
	}

}
