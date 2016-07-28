package com.cjhb.ssm.credit.system.dao.system;

import java.util.List;
import java.util.Map;

import com.credit.dao.BaseMapper;
import com.credit.entity.system.AuMenu;
import com.credit.entity.system.AuRole;
import com.credit.entity.system.AuUser;

@SuppressWarnings("rawtypes")
public interface AuUserMapper extends BaseMapper {

	int deleteByPrimaryKey(String id);

	int insert(AuUser record);

	int insertSelective(AuUser record);

	AuUser selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(AuUser record);

	int updateByPrimaryKey(AuUser record);
	
	public AuUser login(Map<String, Object> map);
	
	public List<AuUser> getUserList(Map<String, Object> map);
	
	public AuUser ifExists(Map<String, Object> map);
	
	/**
	 * @author: kevin
	 * @date: 2014-4-2
	 * @doc: 取用户下所有的权限
	 * @param map
	 * @return
	 */
	public List<Map<String,String>> getAllRole(String id);
	
	/**
	 * @author: kevin
	 * @date: 2014-3-31
	 * @doc: Delete
	 * @param list
	 */
	public void delete(List list);
	
	/**
	 * @author: kevin
	 * @date: 2014-4-1
	 * @doc: 按USERID删除菜单关系数据
	 * @param roleId
	 */
	public void deleteUserMenuRelByUserId(String userId);
	
	/**
	 * @author: kevin
	 * @date: 2014-4-1
	 * @doc: 按USERID删除权限关系数据
	 * @param roleId
	 */
	public void deleteUserRoleRelByUserId(String userId);
	
	/**
	 * @author: kevin
	 * @date: 2014-4-1
	 * @doc: 按USERID删除组织机构关系数据
	 * @param roleId
	 */
	public void deleteUserOrgRelByUserId(String userId);
	
	/**
	 * @author: kevin
	 * @date: 2014-4-1
	 * @doc: 按USERID删除岗位关系数据
	 * @param roleId
	 */
	public void deleteUserPostRelByUserId(String userId);
	
	/**
	 * @author: kevin
	 * @date: 2014-4-3
	 * @doc: 插入角色关系
	 * @param map
	 */
	public void insertUserRoleRel(Map<String, Object> map);
	
	/**
	 * @author: kevin
	 * @date: 2014-4-1
	 * @doc: 插入菜单关系
	 * @param map
	 */
	public void insertUserMenuRel(Map<String, Object> map);
	
	/**
	 * @author: kevin
	 * @date: 2014-4-1
	 * @doc: 插入组织机构关系
	 * @param map
	 */
	public void insertUserOrgRel(Map<String, Object> map);
	
	/**
	 * @author: kevin
	 * @date: 2014-4-1
	 * @doc: 插入岗位关系
	 * @param map
	 */
	public void insertUserPostRel(Map<String, Object> map);
	
	/**
	 * @author: kevin
	 * @date: 2014-4-3
	 * @doc: 获取当前用户所有的菜单
	 * @param userId
	 * @return
	 */
	public List<AuMenu> getAllUserMenusByUserId(String userId);
	
	/**
	 * @author: kevin
	 * @date: 2014-4-3
	 * @doc: 获取当前用户所有的菜单
	 * @param userId
	 * @return
	 */
	public List<AuMenu> getAllRoleMenusByUserId(String userId);
	
	/**
	 * @author: kevin
	 * @date: 2014-4-3
	 * @doc: 获取当前用户所有的权限
	 * @param userId
	 * @return
	 */
	public List<AuRole> getAllUserRoleByUserId(String userId);
	
	/**
	 * @author: kevin
	 * @date: 2014-4-3
	 * @doc: 取的当前用户所有可用的菜单（包括已配的角色中的菜单）
	 * @param userId
	 * @return
	 */
	public List<AuMenu> getAllUserMenuByid(String userId);
	
	
	/**
	 * @author: kevin
	 * @date: 2015年5月28日 下午1:21:22
	 * @Description: 改变是否有效
	 * @param map
	 */
	public void updateIsValid(Map map);
}