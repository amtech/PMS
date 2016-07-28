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
import com.credit.comm.util.TreeOptions;
import com.credit.comm.util.TreeUtils;
import com.credit.comm.util.page.PageForJqGrid;
import com.credit.entity.system.AuRole;
import com.credit.service.system.RoleService;
import com.credit.web.BaseController;

/**
 * @author: kevin
 * @pageName: com.credit.web.system
 * @fileName: RoleController.java
 * @date: 2014-3-28
 * @doc: Role Controller
 */
@Controller
@RequestMapping("/system/role")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RoleController extends BaseController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/")
	public ModelAndView index(){
		return new ModelAndView("system/role/role-index.jsp");
	}
	
	
	/**
	 * @author: kevin
	 * @date: 2014-3-31
	 * @doc: Select Menu
	 * @return
	 */
	@RequestMapping("/selectMenu")
	public ModelAndView selectMenu(String id){
		ModelAndView modelView = new ModelAndView("system/role/role-selectMenu.jsp");
		String menuIds = roleService.getAllMenuId(id);
		modelView.addObject("menuIds", menuIds);
		return modelView;
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-3-28
	 * @doc: Search
	 * @param page
	 * @param role
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public PageForJqGrid<AuRole> getList(PageForJqGrid<AuRole> page,AuRole role){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleName", role.getRoleName());
		map = page.pageToMap(page,map);
		List<AuRole> list = roleService.getRoleList(map);
		page.listToPage(page, list);
		return page;
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-3-28
	 * @doc: edit Page
	 * @return
	 */
	@RequestMapping("/input")
	public ModelAndView input(String id){
		ModelAndView view = new ModelAndView("system/role/role-input.jsp");
		AuRole role = roleService.getRoleById(id);
		view.addObject("role", role);
		return view;
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-3-28
	 * @doc: Save
	 * @param role
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	public JsonResult<AuRole> save(AuRole role){
		roleService.saveOrUpdate(role);
		JsonResult<AuRole> json = new JsonResult<AuRole>();
		json.setResult(role);
		return json;
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-3-28
	 * @doc: CheckExist
	 * @param roleName
	 * @param id
	 * @return
	 */
	@RequestMapping("validateExist")
	@ResponseBody
	public boolean validateExist(String roleName,String id){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleName", roleName);
		map.put("id", id);
		AuRole role = (AuRole)roleService.ifExists(map);
		if (EmptyUtil.isEmpty(role)) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-3-31
	 * @doc: CheckExistCode
	 * @param roleName
	 * @param id
	 * @return
	 */
	@RequestMapping("validateExistCode")
	@ResponseBody
	public boolean validateExistCode(String roleCode,String id){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleCode", roleCode);
		map.put("id", id);
		AuRole role = (AuRole)roleService.ifExistsRoleCode(map);
		if (EmptyUtil.isEmpty(role)) {
			return true;
		}else {
			return false;
		}
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
		roleService.deleteByIds(delIds);
		JsonResult<String> json = new JsonResult<String>();
		json.setResult("done");
		return json;
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-1
	 * @doc: 保存菜单关系
	 * @return
	 */
	@RequestMapping("saveRoleMenuRel")
	@ResponseBody
	public JsonResult<String> saveRoleMenuRel(String roleId,String menuIds,String menuSuperIds){
		List<String> menuIdList= new ArrayList<String>();
		Collections.addAll(menuIdList, menuIds.split(","));
		roleService.saveRoleMenuRel(roleId, menuIdList);
		JsonResult<String> json = new JsonResult<String>();
		json.setResult("done");
		return json;
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-3-27
	 * @doc: 取所有树的数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listForTree")
	@ResponseBody
	public List getTreeList() throws Exception{
		Map map  =  new HashMap();
		map.put("isValid", '1');
		List<AuRole> list = roleService.getRoleList(map);

		TreeOptions options = new TreeOptions(list);
		options.setId("id");
		options.setText("roleName");
		options.setNodeLevel("undefined");
		options.setHasChild("treeType");
		options.setParentId("undefined");
		
		return TreeUtils.getListForJsTree(options);
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
		roleService.updateIsValid(map);
		JsonResult<String> json = new JsonResult<String>();
		json.setResult("done");
		return json;
	}
}
