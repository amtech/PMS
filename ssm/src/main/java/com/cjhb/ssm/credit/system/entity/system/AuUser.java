package com.cjhb.ssm.credit.system.entity.system;

import java.util.Date;
import java.util.List;

import com.credit.entity.BaseEntity;

public class AuUser extends BaseEntity {

	private static final long serialVersionUID = 524139375929598046L;

	private String username;

    private String passwd;

    private String realname;

    private String email;

    private String loginIp;

    private Date loginTime;

    private String isValid;

    private String isDel;
    
    private List<AuRole> userRoleList;
    
    private List<AuMenu> userMenuList;
    
    private String userMenuJson;
    

	public String getUserMenuJson() {
		return userMenuJson;
	}

	public void setUserMenuJson(String userMenuJson) {
		this.userMenuJson = userMenuJson;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel == null ? null : isDel.trim();
    }

	public List<AuRole> getUserRoleList() {
		return userRoleList;
	}

	public void setUserRoleList(List<AuRole> userRoleList) {
		this.userRoleList = userRoleList;
	}

	public List<AuMenu> getUserMenuList() {
		return userMenuList;
	}

	public void setUserMenuList(List<AuMenu> userMenuList) {
		this.userMenuList = userMenuList;
	}
    

}