package com.cjhb.ssm.credit.comm.util.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import com.credit.comm.util.EmptyUtil;
import com.credit.comm.util.LoginUserHelper;
import com.credit.comm.util.dialect.Dialect;
import com.credit.comm.util.dialect.MySql5Dialect;
import com.credit.comm.util.dialect.OracleDialect;
import com.credit.entity.BaseEntity;

@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
@SuppressWarnings({"unchecked","rawtypes"})
public class PaginationInterceptor implements Interceptor {

	private final static Log log = LogFactory.getLog(PaginationInterceptor.class);

	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();

	private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();

	
	private static final ThreadLocal<Integer> resultCountTL = new ThreadLocal<Integer>();
	
	public static void setResultCount(int count){
		resultCountTL.set(count);
	}
	
	public static int getResultCount(){
		return resultCountTL.get();
	}

	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		BoundSql boundSql = statementHandler.getBoundSql();

		log.info("SQL:" + boundSql.getSql().replace('\n',' '));
		
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		
		MappedStatement mappedStatement = (MappedStatement)metaStatementHandler.getValue("delegate.mappedStatement");
		
		SqlCommandType type = mappedStatement.getSqlCommandType();
		if (!SqlCommandType.SELECT.equals(type)) {
			//在insert和update时把操作人的信息输入
			//2014-04-08 kevin
			Date sysDate = new Date();
			if (SqlCommandType.UPDATE.equals(type)){
				if (boundSql.getParameterObject() instanceof BaseEntity) {
					BaseEntity entity = (BaseEntity) boundSql.getParameterObject();
					entity.setLastUpdateUserId(LoginUserHelper.getLoginUserId());
					entity.setLastUpdateUserName(LoginUserHelper.getLoginUserName());
					entity.setLastUpdateDate(sysDate);
				}else if(boundSql.getParameterObject() instanceof Map){
					Map map = (Map) boundSql.getParameterObject();
					map.put("lastUpdateUserId", LoginUserHelper.getLoginUserId());
					map.put("lastUpdateUserName", LoginUserHelper.getLoginUserName());
					map.put("lastUpdateDate", sysDate);
				}
			}
			if (SqlCommandType.INSERT.equals(type)){
				if (boundSql.getParameterObject() instanceof BaseEntity) {
					BaseEntity entity = (BaseEntity) boundSql.getParameterObject();
					entity.setCreateUserId(LoginUserHelper.getLoginUserId());
					entity.setCreateUserName(LoginUserHelper.getLoginUserName());
					entity.setCreateDate(sysDate);
				}else if(boundSql.getParameterObject() instanceof Map){
					Map map = (Map) boundSql.getParameterObject();
					map.put("createUserId", LoginUserHelper.getLoginUserId());
					map.put("createUserName", LoginUserHelper.getLoginUserName());
					map.put("createDate", sysDate);
				}
			}
			return invocation.proceed();
		}else{
			if (LoginUserHelper.isLogin()){
				if (boundSql.getParameterObject() instanceof BaseEntity) {
					BaseEntity entity = (BaseEntity) boundSql.getParameterObject();
				}else if(boundSql.getParameterObject() instanceof Map){
					Map map = (Map) boundSql.getParameterObject();
				}
			}
		}
		
		Object obj = boundSql.getParameterObject();
		if (!(obj instanceof Map) || EmptyUtil.isEmpty(obj)) {
			return invocation.proceed();
		}
		
		RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");

		Map<String, Object> parms = (Map<String, Object>) obj;
		
		Integer offset = null;
		Integer limit = null;
		
		if(parms.containsKey("offset")&& parms.containsKey("limit")){
			offset = (Integer) parms.get("offset");
			limit = (Integer) parms.get("limit");
		}else{
			return invocation.proceed();
		}

		if ((rowBounds == null || rowBounds == RowBounds.DEFAULT) && (offset == null || limit == null)) {
			return invocation.proceed();
		}

		Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");

		Dialect.Type databaseType = null;
		try {
			databaseType = Dialect.Type.valueOf(configuration.getVariables().getProperty("dialect").toUpperCase());
		} catch (Exception e) {
			// ignore
		}
		if (databaseType == null) {
			throw new RuntimeException("the value of the dialect property in configuration.xml is not defined : " + configuration.getVariables().getProperty("dialect"));
		}

		Dialect dialect = null;
		switch (databaseType) {
		case MYSQL:
			dialect = new MySql5Dialect();
			break;
		case ORACLE:
			dialect = new OracleDialect();
			break;
		}

		String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
		String countSql = originalSql.toUpperCase();
		int orderIdx = countSql.lastIndexOf("ORDER");
		if(countSql.lastIndexOf(')') > orderIdx){
			orderIdx = -1;
		}
		if(orderIdx > 0){
			countSql = countSql.substring(0, orderIdx -1);
		}
		
		countSql = "select count(*) from (" + countSql + ") t";
		
		BoundSql countBS = new BoundSql(configuration, countSql.toString(), boundSql.getParameterMappings(), parms);
		Connection conn = configuration.getEnvironment().getDataSource().getConnection();
		PreparedStatement countStmt = conn.prepareStatement(countSql.toString());
		setParameters(countStmt, configuration, countBS, parms);
		ResultSet rs = countStmt.executeQuery();
		int totpage = 0;
		if (rs.next()) {
			totpage = rs.getInt(1);
		}
		rs.close();
		countStmt.close();
		conn.close();
		
		setResultCount(totpage);

		metaStatementHandler.setValue("delegate.boundSql.sql", dialect.getLimitString(originalSql, offset, limit));
		metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
		metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
		if (log.isDebugEnabled()) {
			log.debug("进入分页拦截器：生成的SQL为: " + boundSql.getSql());
		}

		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
	}

	private void setParameters(PreparedStatement ps, Configuration configuration, BoundSql boundSql, Object parameterObject) throws SQLException {
//		ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if (parameterMappings != null) {
//			Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject.getValue(propertyName);
					}
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
					if (typeHandler == null) {
//						throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName + " of statement " + mappedStatement.getId());
						throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName + " of statement ");
					}
					typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
				}
			}
		}
	}
}
