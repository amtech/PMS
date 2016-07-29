package com.cjhb.pms.domain.home_pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @title: 用户模型
 * @author edgar
 * @date 2016-7-29 12:00:33
 * @version  1.0
 */
public class UserInfo implements Serializable {

	private String userId; //用户编号
	private String userName;//登陆用户姓名
	private String password;//登陆密码
	private String trueName;//真实姓名
	private String mail;//用户邮箱
	private String phone;//用户手机号
	private String deptId;//部门编号
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//格式化时间格式
	private Date createTime;//创建时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;//更新时间
	private String userLevel;//用户级别
	private String fileName;
	private byte[] fileByte;
	private DeptInfo dept;

	public UserInfo() {
	}

	public UserInfo(String userName, String password, String trueName, String mail,
	                String phone, String deptId, Date createTime, Date updateTime,
	                String userLevel) {
		super();
		this.userName = userName;
		this.password = password;
		this.trueName = trueName;
		this.mail = mail;
		this.phone = phone;
		this.deptId = deptId;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.userLevel = userLevel;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileByte() {
		return fileByte;
	}

	public void setFileByte(byte[] fileByte) {
		this.fileByte = fileByte;
	}

	public DeptInfo getDept() {
		return dept;
	}

	public void setDept(DeptInfo dept) {
		this.dept = dept;
	}

	private static final long serialVersionUID = -4339057713681022216L;
}