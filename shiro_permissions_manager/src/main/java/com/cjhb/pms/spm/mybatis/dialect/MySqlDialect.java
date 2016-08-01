package com.cjhb.pms.spm.mybatis.dialect;


import com.cjhb.pms.spm.mybatis.IDialect;

/**
 * MySql 数据库分页语句组装实现
 * 
 * @author ArchX[archx@foxmail.com]
 */
public class MySqlDialect implements IDialect {

    @Override
    public String buildPaginationSql(String originalSql, int offset, int limit) {
        StringBuilder sql = new StringBuilder(originalSql);
        sql.append(" LIMIT ").append(offset).append(",").append(limit);
        return sql.toString();
    }

}
