package com.cjhb.ssm.credit.system.dao.system;

import java.util.List;
import java.util.Map;

import com.credit.dao.BaseMapper;
import com.credit.entity.system.AuMenu;
import com.credit.entity.system.AuRole;

/**
 * @author: kevin
 * @pageName: com.credit.dao.system
 * @fileName: AuRoleMapper.java
 * @date: 2014-3-28
 * @doc: Role Mapper
 */
public interface AuRoleMapper extends BaseMapper {

	/**
	 * @author: kevin
	 * @date: 2014-3-28
	 * @doc: Search
	 * @param map
	 * @return
	 */
	public List<AuRole> getRoleList(Map<String, Object> map);
	
	/**
	 * @author: kevin
	 * @date: 2014-3-28
	 * @doc: checkExists
	 * @param map
	 * @return
	 */
	public AuRole ifExists(Map<String, Object> map);
	
	/**
	 * @author: kevin
	 * @date: 2014-3-31
	 * @doc: checkExistsCode
	 * @param map
	 * @return
	 */
	public AuRole ifExistsRoleCode(Map<String, Object> map);
	
	/**
	 * @author: kevin
	 * @date: 2014-3-31
	 * @doc: 
	 * @param list
	 */
	public void delete(List list);
	
	/**
	 * @author: kevin
	 * @date: 2014-4-1
	 * @doc: 插入关系
	 * @param map
	 */
	public void insertRoleMenuRel(Map<String, Object> map);
	
	/**
	 * @author: kevin
	 * @date: 2014-4-1
	 * @doc: 按ROLEID删除菜单关系数据
	 * @param roleId
	 */
	public void deleteRoleMenuRelByRoleId(String roleId);
	
	/**
	 * @author: kevin
	 * @date: 2014-4-1
	 * @doc: 按ROLEID取出所有菜单
	 * @param roleId
	 * @return
	 */
	public List<AuMenu> getAllMenusByRoleId(String roleId);
	
	/**
	 * @author: kevin
	 * @date: 2014-4-2
	 * @doc: 取出所有菜单与权限的对应关系
	 * @param map
	 * @return
	 */
	public List getAllMenuRoleRel(Map<String, Object> map);
	
	/**
	 * @author: kevin
	 * @date: 2015年5月28日 下午1:21:22
	 * @Description: 改变是否有效
	 * @param map
	 */
	public void updateIsValid(Map map);
} 