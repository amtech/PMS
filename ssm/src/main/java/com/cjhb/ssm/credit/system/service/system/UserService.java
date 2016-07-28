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

import com.credit.comm.util.PasswordUtil;
import com.credit.dao.system.AuUserMapper;
import com.credit.entity.system.AuMenu;
import com.credit.entity.system.AuRole;
import com.credit.entity.system.AuUser;

/**
* @ClassName: UserService
* @Description: 用户Service
* @author 
* @date 2015年5月27日 上午10:45:39
*
*/
@Component
@Transactional
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UserService {
	
	@Autowired
	private AuUserMapper auUserMapper;
	
	/**
	 * @author: kevin
	 * @date: 2015年5月27日 上午10:45:53
	 * @Description: 登陆
	 * @param map
	 * @return
	 */
	public AuUser login(Map<String, Object> map){
		return auUserMapper.login(map);
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年5月27日 上午10:46:20
	 * @Description: 获提用户列表
	 * @param map
	 * @return
	 */
	public List<AuUser> getUserList(Map<String, Object> map){
		return auUserMapper.getUserList(map);
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年5月27日 上午10:46:31
	 * @Description: 校验用户是否存在
	 * @param map
	 * @return
	 */
	public AuUser ifExists(Map<String, Object> map){
		AuUser user = auUserMapper.ifExists(map);
		if (user != null){
			if (user.getUsername().toLowerCase().equals(String.valueOf(map.get("username")).toLowerCase())){
				return user;
			}
		}
		return null;
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年5月27日 上午10:50:21
	 * @Description: 保存
	 * @param user
	 */
	public void save(AuUser user){
		if (StringUtils.isBlank(user.getId())){
			user.setIsValid("1");
			user.setIsDel("0");
			user.setPasswd(PasswordUtil.md5Encode(user.getPasswd()));
			auUserMapper.insert(user);
		}else{
			if (!StringUtils.isBlank(user.getPasswd())){
				user.setPasswd(PasswordUtil.md5Encode(user.getPasswd()));
			}
			auUserMapper.update(user);
		}
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年5月27日 上午10:50:29
	 * @Description: 获提所有的角色
	 * @param id
	 * @return
	 */
	public List<Map<String,String>> getAllRole(String id){
		return auUserMapper.getAllRole(id);
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-3-31
	 * @doc: getRoleById
	 * @param id
	 * @return
	 */
	public AuUser getUserById(String id){
		return (AuUser) auUserMapper.get(id);
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
		auUserMapper.delete(list);
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-1
	 * @doc: 插入关系表
	 * @param userId
	 * @param menuIds
	 */
	public void saveUserMenuRel(String userId,List<String> menuIds){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId",userId);
		map.put("isValid", '1');
		map.put("isDel", '0');
		map.put("createDate",new Date());
		auUserMapper.deleteUserMenuRelByUserId(userId);
		for (String str : menuIds) {
			map.put("menuId", str);
			auUserMapper.insertUserMenuRel(map);
		}
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-1
	 * @doc: 插入关系表
	 * @param userId
	 * @param roleIds
	 */
	public void saveUserRoleRel(String userId,List<String> roleIds){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId",userId);
		map.put("isValid", '1');
		map.put("isDel", '0');
		map.put("createDate",new Date());
		auUserMapper.deleteUserRoleRelByUserId(userId);
		for (String str : roleIds) {
			map.put("roleId", str);
			auUserMapper.insertUserRoleRel(map);
		}
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-1
	 * @doc: 插入关系表
	 * @param userId
	 * @param roleIds
	 */
	public void saveUserOrgRel(String userId,List<String> orgIds){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId",userId);
		map.put("isValid", '1');
		map.put("isDel", '0');
		map.put("createDate",new Date());
		auUserMapper.deleteUserOrgRelByUserId(userId);
		for (String str : orgIds) {
			if (StringUtils.isBlank(str)){
				return;
			}
			map.put("orgId", str);
			auUserMapper.insertUserOrgRel(map);
		}
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-1
	 * @doc: 插入关系表
	 * @param userId
	 * @param roleIds
	 */
	public void saveUserPostRel(String userId,List<String> postIds){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId",userId);
		map.put("isValid", '1');
		map.put("isDel", '0');
		map.put("createDate",new Date());
		auUserMapper.deleteUserPostRelByUserId(userId);
		for (String str : postIds) {
			map.put("postId", str);
			auUserMapper.insertUserPostRel(map);
		}
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-1
	 * @doc: 取得所有菜单ID
	 * @param userId
	 * @return
	 */
	public String getAllMenuId(String userId){
		List<AuMenu> userMenus = auUserMapper.getAllUserMenusByUserId(userId);
		StringBuffer menuStr = new StringBuffer();
		for (AuMenu auMenu : userMenus) {
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
	 * @date: 2014-4-1
	 * @doc: 取得所有权限ID
	 * @param userId
	 * @return
	 */
	public String getAllRoleId(String userId){
		List<AuRole> roles = auUserMapper.getAllUserRoleByUserId(userId);
		StringBuffer rolesStr = new StringBuffer();
		for (AuRole role : roles) {
			if (role != null){
				rolesStr.append("," + role.getId());
			}
		}
		if (rolesStr.length() > 1){
			return rolesStr.substring(1);
		}else{
			return "";
		}
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-3
	 * @doc: 取的当前用户所有可用的菜单（包括已配的角色中的菜单）
	 * @param userId
	 * @return
	 */
	public List<AuMenu> getAllUserMenuByid(String userId){
		return auUserMapper.getAllUserMenuByid(userId);
	}
	/**
	 * @author: kevin
	 * @date: 2015年5月28日 下午1:21:22
	 * @Description: 改变是否有效
	 * @param map
	 */
	public void updateIsValid(Map map){
		auUserMapper.updateIsValid(map);
	}
}
