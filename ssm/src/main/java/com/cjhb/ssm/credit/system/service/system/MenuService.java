package com.cjhb.ssm.credit.system.service.system;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.relation.Role;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.credit.comm.util.BeanToMapUtil;
import com.credit.dao.system.AuMenuMapper;
import com.credit.entity.system.AuMenu;

/**
 * @author: kevin
 * @pageName: com.credit.service
 * @fileName: MenuService.java
 * @date: 2014-3-25
 * @doc: Menu Service
 */
@Component
@Transactional
@SuppressWarnings({"unchecked","rawtypes"})
public class MenuService {

	@Autowired
	public AuMenuMapper auMenuMapper;
	
	/**
	 * @author: kevin
	 * @date: 2014-3-25
	 * @doc: selectRoles
	 * @param id
	 * @return List<Role>
	 */
	public List<Role> selectRoles(String id){
		return auMenuMapper.selectRoles(id);
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-3-25
	 * @doc: 取所有的根节点
	 * @return List<AuMenu>
	 */
	public List<AuMenu> selectAllMenus(Map<String,Object> map){
		map.put("isdel", "0");
		return auMenuMapper.selectAllMenus(map);
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
		auMenuMapper.delete(list);
		
		//判断这些节点的父节点是否还有子项， 没有了就把haschild改为0
		for (int i = 0; i < ids.length; i++) {
			String menuId = ids[i];
			//查询删除项对象
			Map<String, Object> map =new HashMap<String, Object>();
			map.put("id", menuId);
			AuMenu menu =  auMenuMapper.getMenuById(map);
			
			if(menu.getSuperId()!=null && !"".equals(menu.getSuperId())){
				Map<String, Object> map1 =new HashMap<String, Object>();
				map1.put("id", menu.getSuperId());
				//查询父节点对象旗下的子节点
				List<AuMenu> list1 = auMenuMapper.getChildrenById(map1);
				//子节点为空，就把haschild改为0
				if(list1==null || list1.size()==0){
					Map<String, Object> map2 =new HashMap<String, Object>();
					map2.put("id", menu.getSuperId());
					map2.put("hasChild", "0");
					auMenuMapper.updateHasChild(map2);
				}
			}
		}
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-3-27
	 * @doc: 
	 * @param map
	 * @return
	 */
	public AuMenu getMenuById(Map<String,Object> map){
		return auMenuMapper.getMenuById(map);
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-3-27
	 * @doc: insertOrUpdate
	 * @param menu
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws Exception 
	 */
	public void save(AuMenu menu) throws Exception{
		if (StringUtils.isBlank(menu.getId())){
			menu.setIsValid("1");
			menu.setIsDel("0");
			menu.setHasChild("0");
			
			if (menu.getSuperId() != null){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("hasChild", "1");
				map.put("id", menu.getSuperId());
				auMenuMapper.updateHasChild(map);
				
				//处理menu.menuLevel值 
				map.put("id", menu.getSuperId());
				AuMenu superMenu = auMenuMapper.getMenuById(map);
				if (superMenu != null && superMenu.getMenuLevel() != null){
					menu.setMenuLevel(String.valueOf(Integer.valueOf(superMenu.getMenuLevel()) + 1));
				}else{
					menu.setMenuLevel("1");
				}
			}else{
				menu.setMenuLevel("1");
			}
			auMenuMapper.insert(BeanToMapUtil.convertBean(menu));
		}else{
			auMenuMapper.update(BeanToMapUtil.convertBean(menu));
		}
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-3-28
	 * @doc: 查看多少子数量
	 * @param map
	 * @return
	 */
	public int getChildCount(Map<String,Object> map){
		map.put("isDel", "0");
		return auMenuMapper.getChildCount(map);
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-1
	 * @doc: 按ID取子节点
	 * @param map
	 * @return
	 */
	public List<AuMenu> getChildrenById(Map<String, Object> map){
		return auMenuMapper.getChildrenById(map);
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-11 下午5:01:37
	 * @Description: 
	 * @param map
	 * @return
	 */
	public AuMenu ifExistsMenuCode(Map<String,Object> map){
		return auMenuMapper.ifExistsMenuCode(map);
	}
}
