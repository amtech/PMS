/**
 * 
 */
package com.cjhb.pms.common;

/**
 * MyBatis实现物理分页
 * @author edgar
 * @create 2016-7-29 14:25:34
 * @version 1.0
 *
 */
public abstract class Dialect {
	public static enum Type {
		MYSQL, ORACLE
	}

	public abstract String getLimitString(String sql, int skipResults,
			int maxResults);
}
