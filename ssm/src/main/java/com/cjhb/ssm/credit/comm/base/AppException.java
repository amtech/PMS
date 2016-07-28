package com.cjhb.ssm.credit.comm.base;

public class AppException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String msg;
	
	
	/**
	 * construct
	 */
	public AppException() {
		this.msg = null;
	}

	/**
	 * construct
	 */
	public AppException(Exception e) {
		this.msg = e.toString();
	}
	
	/**
	 * construct
	 */
	public AppException(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
