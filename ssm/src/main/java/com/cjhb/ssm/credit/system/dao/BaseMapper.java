/**
 * 
 */
package com.cjhb.ssm.credit.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.credit.entity.BaseEntity;

/**
 * @author teanger
 * @since 2011-4-9
 */
@Component
public interface BaseMapper {
	
	public void insert(BaseEntity entity);
	
	public void update(BaseEntity entity);
	
	/**
	 * 删除一条记录
	 */
	public void delete(String id);
	
	/**
	 * 根据ID获取记录
	 */
	public BaseEntity get(String id);
	
	@SuppressWarnings("unchecked")
	public List getAll();
	
	/**
	 * 根据查询条件，获取记录数
	 * where:查询条件
	 */
	public int getCount(Map<String, String> map);
	
	/**
	 * 根据条件查询
	 * @param where 查询条件
	 * @param orderstr 排序条件
	 * @param limit 分页条件
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List selectWhere(Map<String, String> map);

}
