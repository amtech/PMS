package com.cjhb.ssm.credit.system.dao.system;

import java.util.List;
import java.util.Map;

import javax.management.relation.Role;

import com.credit.dao.BaseMapper;
import com.credit.entity.system.AuMenu;

/**
 * @author: kevin
 * @pageName: com.credit.dao.system
 * @fileName: AuMenuMapper.java
 * @date: 2014-3-25
 * @doc: Menu Mapper
 */
public interface AuMenuMapper extends BaseMapper {

	/**
	 * @author: kevin
	 * @date: 2014-3-25
	 * @doc: selectRoles
	 * @param id
	 * @return List<Role>
	 */
	public List<Role> selectRoles(String id);
	
	/**
	 * @author: kevin
	 * @date: 2014-3-25
	 * @doc: 取所有的根节点
	 * @return List<AuMenu>
	 */
	public List<AuMenu> selectAllMenus(Map<String, Object> map);
	
	/**
	 * @author: kevin
	 * @date: 2014-3-31
	 * @doc: 
	 * @param list
	 */
	public void delete(List list);
	
	/**
	 * @author: kevin
	 * @date: 2014-3-27
	 * @doc: 按ID取菜单
	 * @param map
	 * @return
	 */
	public AuMenu getMenuById(Map<String, Object> map);
	
	/**
	 * @author: kevin
	 * @date: 2014-3-28
	 * @doc: 保存
	 * @param map
	 */
	public void insert(Map<String, Object> map);
	
	/**
	 * @author: kevin
	 * @date: 2014-3-28
	 * @doc: 更新
	 * @param map
	 */
	public void update(Map<String, Object> map);
	
	/**
	 * @author: kevin
	 * @date: 2014-3-28
	 * @doc: 
	 * @param map
	 */
	public void updateHasChild(Map<String, Object> map);
	
	/**
	 * @author: kevin
	 * @date: 2014-3-28
	 * @doc: 查看多少子数量
	 * @param map
	 * @return
	 */
	public int getChildCount(Map<String, Object> map);
	
	/**
	 * @author: kevin
	 * @date: 2014-4-1
	 * @doc: 按ID取子节点
	 * @param map
	 * @return
	 */
	public List<AuMenu> getChildrenById(Map<String, Object> map);
	
	/**
	 * @author: kevin
	 * @date: 2014-4-11 下午5:02:25
	 * @Description: 
	 * @param map
	 * @return
	 */
	public AuMenu ifExistsMenuCode(Map<String, Object> map);
	
}