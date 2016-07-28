package com.cjhb.ssm.credit.system.service.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.credit.comm.ehcache.CacheUpdateMapping;
import com.credit.comm.ehcache.CacheUtils;
import com.credit.dao.system.AuRoleMapper;
import com.credit.entity.system.AuMenu;
import com.credit.entity.system.AuRole;

/**
 * @author: kevin
 * @pageName: com.credit.service
 * @fileName: RoleService.java
 * @date: 2014-3-28
 * @doc: Role Service
 */
@Component
@Transactional
public class RoleService {
	
	@Autowired
	private AuRoleMapper auRoleMapper;
	
	/**
	 * @author: kevin
	 * @date: 2014-3-28
	 * @doc: Search
	 * @param map
	 * @return
	 */
	public List<AuRole> getRoleList(Map<String, Object> map){
		return auRoleMapper.getRoleList(map);
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-3-28
	 * @doc: saveOrUpdate
	 * @param role
	 */
	public void saveOrUpdate(AuRole role){
		if (StringUtils.isBlank(role.getId())){
			role.setIsValid("1");
			role.setIsDel("0");
			auRoleMapper.insert(role);
		}else{
			auRoleMapper.update(role);
		}
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-3-28
	 * @doc: checkExists
	 * @param map
	 * @return
	 */
	public AuRole ifExists(Map<String,Object> map){
		return auRoleMapper.ifExists(map);
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-3-31
	 * @doc: checkExistsCode
	 * @param map
	 * @return
	 */
	public AuRole ifExistsRoleCode(Map<String,Object> map){
		return auRoleMapper.ifExistsRoleCode(map);
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-3-31
	 * @doc: getRoleById
	 * @param id
	 * @return
	 */
	public AuRole getRoleById(String id){
		return (AuRole) auRoleMapper.get(id);
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-3-31
	 * @doc: Delete By Ids
	 * @param delIds
	 */
	public void deleteByIds(String delIds){
		List list =  new ArrayList();
		String[] ids = delIds.split(",");
		for (int i = 0; i < ids.length; i++) {
			list.add(ids[i]);
		}
		auRoleMapper.delete(list);
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-1
	 * @doc: 插入关系表
	 * @param roleId
	 * @param menuIds
	 */
	public void saveRoleMenuRel(String roleId,List<String> menuIds){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("roleId",roleId);
		map.put("isValid", '1');
		map.put("isDel", '0');
		map.put("createDate",new Date());
		auRoleMapper.deleteRoleMenuRelByRoleId(roleId);
		for (String str : menuIds) {
			map.put("menuId", str);
			auRoleMapper.insertRoleMenuRel(map);
		}
		CacheUtils.updateByKey(CacheUpdateMapping.RolePath);
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-1
	 * @doc: 取得所有菜单ID
	 * @param roleId
	 * @return
	 */
	public String getAllMenuId(String roleId){
		List<AuMenu> menus = auRoleMapper.getAllMenusByRoleId(roleId);
		StringBuffer menuStr = new StringBuffer();
		for (AuMenu auMenu : menus) {
			if (auMenu != null){
				menuStr.append("," + auMenu.getId());
			}
		}
		if (menuStr.length() > 1){
			return menuStr.substring(1);
		}else{
			return "";
		}
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-2
	 * @doc: 取出所有菜单与权限的对应关系
	 * @param map
	 * @return
	 */
	public List getAllMenuRoleRel(){
		return auRoleMapper.getAllMenuRoleRel(new HashMap());
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年5月28日 下午1:21:22
	 * @Description: 改变是否有效
	 * @param map
	 */
	public void updateIsValid(Map map){
		auRoleMapper.updateIsValid(map);
		CacheUtils.updateByKey(CacheUpdateMapping.RolePath);
	}
}
