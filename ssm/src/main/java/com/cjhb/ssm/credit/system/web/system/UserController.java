package com.cjhb.ssm.credit.system.web.system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.credit.comm.base.JsonResult;
import com.credit.comm.util.EmptyUtil;
import com.credit.comm.util.page.PageForJqGrid;
import com.credit.entity.system.AuUser;
import com.credit.service.system.UserService;
import com.credit.web.BaseController;


/**
 * @author: kevin
 * @pageName: com.credit.web.system
 * @fileName: UserController.java
 * @date: 2014-4-2
 * @doc: 用户
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * @author: kevin
	 * @date: 2014-4-2
	 * @doc: main page
	 * @return
	 */
	@RequestMapping("/")
	public ModelAndView index(){
		return new ModelAndView("system/user/user-index.jsp");
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-2
	 * @doc: Search
	 * @param page
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@ResponseBody
	public PageForJqGrid<AuUser> getList(PageForJqGrid<AuUser> page,AuUser user) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", user.getUsername());
		map.put("email", user.getEmail());
		map.put("isValid", user.getIsValid());
		map = page.pageToMap(page,map);
		List<AuUser> list = userService.getUserList(map);
		page.listToPage(page, list);
		return page;
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-2
	 * @doc: Edit page
	 * @param id
	 * @return
	 */
	@RequestMapping("/input")
	public ModelAndView input(String id){
		ModelAndView view = new ModelAndView("system/user/user-input.jsp");
		AuUser user = userService.getUserById(id);
		view.addObject("user", user);
		return view;
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-3-31
	 * @doc: Delete
	 * @param delIds
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public JsonResult<String> delete(String delIds){
		userService.deleteByIds(delIds);
		JsonResult<String> json = new JsonResult<String>();
		json.setResult("done");
		return json;
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-2
	 * @doc: checkExist
	 * @param userName
	 * @return
	 */
	@RequestMapping("validateExist")
	@ResponseBody
	public boolean validateExist(String username){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		AuUser user = (AuUser)userService.ifExists(map);
		if (EmptyUtil.isEmpty(user)) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-2
	 * @doc: Save
	 * @param user
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	public JsonResult<AuUser> save(AuUser user){
		userService.save(user);
		JsonResult<AuUser> json = new JsonResult<AuUser>();
		json.setResult(user);
		return json;
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-3-31
	 * @doc: Select Menu
	 * @return
	 */
	@RequestMapping("/selectMenu")
	public ModelAndView selectMenu(String id){
		ModelAndView modelView = new ModelAndView("system/user/user-selectMenu.jsp");
		String menuIds = userService.getAllMenuId(id);
		modelView.addObject("menuIds", menuIds);
		return modelView;
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-3-31
	 * @doc: Select Role
	 * @return
	 */
	@RequestMapping("/selectRole")
	public ModelAndView selectRole(String id){
		ModelAndView modelView = new ModelAndView("system/user/user-selectRole.jsp");
		String roleIds = userService.getAllRoleId(id);
		modelView.addObject("roleIds", roleIds);
		return modelView;
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-1
	 * @doc: 保存菜单关系
	 * @return
	 */
	@RequestMapping("saveUserMenuRel")
	@ResponseBody
	public JsonResult<String> saveUserMenuRel(String userId,String menuIds){
		List<String> menuIdList= new ArrayList<String>();
		Collections.addAll(menuIdList, menuIds.split(","));
		userService.saveUserMenuRel(userId, menuIdList);
		JsonResult<String> json = new JsonResult<String>();
		json.setResult("done");
		return json;
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-1
	 * @doc: 保存角色关系
	 * @return
	 */
	@RequestMapping("saveUserRoleRel")
	@ResponseBody
	public JsonResult<String> saveUserRoleRel(String userId,String roleIds){
		List<String> roleIdList= new ArrayList<String>();
		Collections.addAll(roleIdList, roleIds.split(","));
		userService.saveUserRoleRel(userId, roleIdList);
		JsonResult<String> json = new JsonResult<String>();
		json.setResult("done");
		return json;
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-1
	 * @doc: 保存组织机构关系
	 * @return
	 */
	@RequestMapping("saveUserOrgRel")
	@ResponseBody
	public JsonResult<String> saveUserOrgRel(String userId,String orgIds){
		List<String> orgIdList= new ArrayList<String>();
		Collections.addAll(orgIdList,orgIds.split(","));
		userService.saveUserOrgRel(userId, orgIdList);
		JsonResult<String> json = new JsonResult<String>();
		json.setResult("done");
		return json;
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-1
	 * @doc: 保存岗位关系
	 * @return
	 */
	@RequestMapping("saveUserPostRel")
	@ResponseBody
	public JsonResult<String> saveUserPostRel(String userId,String postIds){
		List<String> postIdList= new ArrayList<String>();
		Collections.addAll(postIdList,postIds.split(","));
		userService.saveUserPostRel(userId, postIdList);
		JsonResult<String> json = new JsonResult<String>();
		json.setResult("done");
		return json;
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年5月28日 上午11:52:33
	 * @Description: 无效与有效
	 * @param id
	 * @param flag
	 * @return
	 */
	@RequestMapping("changeValid")
	@ResponseBody
	public JsonResult<String>changeValid(String id,String flag){
		Map map = new HashMap();
		map.put("id", id);
		map.put("isValid", flag);
		userService.updateIsValid(map);
		JsonResult<String> json = new JsonResult<String>();
		json.setResult("done");
		return json;
	}
}
