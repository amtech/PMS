/**
 * 
 */
package com.cjhb.pms.common;

/**
 * MyBatis实现物理分页
 * @author edgar
 * @create 2016-7-29 14:26:58
 * @version 1.0
 *
 */
public class MySQLDialect extends Dialect {

	@Override
	public String getLimitString(String sql, int offset, int limit) {
		sql = sql.trim();
		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		if (offset >= 0) {
			pagingSelect.append(sql).append(" limit ").append(offset).append(", ").append(limit);
		} else {
			pagingSelect.append(sql).append(" limit ").append(0).append(", ").append(limit);
		}
		// StringBuffer finalSQL = new StringBuffer("SELECT (@ROWNUM := @ROWNUM + 1) AS ROW_NUM, t.* FROM (");
		// finalSQL.append(pagingSelect.toString());
		// finalSQL.append(") t, (SELECT (@rownum := 0)) N ORDER BY ROW_NUM");
		// return finalSQL.toString();
		return pagingSelect.toString();
	}

}
