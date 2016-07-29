package com.cjhb.pms.domain.home_pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @title: �û�ģ��
 * @author edgar
 * @date 2016-7-29 12:00:33
 * @version  1.0
 */
public class UserInfo implements Serializable {

	private String userId; //�û����
	private String userName;//��½�û�����
	private String password;//��½����
	private String trueName;//��ʵ����
	private String mail;//�û�����
	private String phone;//�û��ֻ���
	private String deptId;//���ű��
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//��ʽ��ʱ���ʽ
	private Date createTime;//����ʱ��
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;//����ʱ��
	private String userLevel;//�û�����
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