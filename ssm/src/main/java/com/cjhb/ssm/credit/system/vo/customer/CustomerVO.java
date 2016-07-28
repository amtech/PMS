package com.cjhb.ssm.credit.system.vo.customer;

import java.util.Date;

public class CustomerVO {
	private String customerid;
	private String customername;
	private String encryptionkey;//加密字符串
	private String custonmerabbrname;
	private String customertype;//'01 : 企业    02 : 个人',
	private String customerlevel;
	private String risklevel;
	private Date opentime;
	private Date closetime;
	private Date updatetime;
	private String state;//00 ，正常    01 ，暂停    02 ，销户
	
	public String getCustomerid() {
		return customerid;
	}
	public String getEncryptionkey() {
		return encryptionkey;
	}
	public void setEncryptionkey(String encryptionkey) {
		this.encryptionkey = encryptionkey;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getCustonmerabbrname() {
		return custonmerabbrname;
	}
	public void setCustonmerabbrname(String custonmerabbrname) {
		this.custonmerabbrname = custonmerabbrname;
	}
	public String getCustomertype() {
		return customertype;
	}
	public void setCustomertype(String customertype) {
		this.customertype = customertype;
	}
	public String getCustomerlevel() {
		return customerlevel;
	}
	public void setCustomerlevel(String customerlevel) {
		this.customerlevel = customerlevel;
	}
	public String getRisklevel() {
		return risklevel;
	}
	public void setRisklevel(String risklevel) {
		this.risklevel = risklevel;
	}
	public Date getOpentime() {
		return opentime;
	}
	public void setOpentime(Date opentime) {
		this.opentime = opentime;
	}
	public Date getClosetime() {
		return closetime;
	}
	public void setClosetime(Date closetime) {
		this.closetime = closetime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}