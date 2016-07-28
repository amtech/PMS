package com.cjhb.ssm.credit.system.web.system;

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
import com.credit.comm.util.LoginUserHelper;
import com.credit.comm.util.TreeOptions;
import com.credit.comm.util.TreeUtils;
import com.credit.comm.util.page.PageForJqGrid;
import com.credit.entity.system.AuMenu;
import com.credit.service.system.MenuService;
import com.credit.web.BaseController;

/**
 * @author: kevin
 * @pageName: com.credit.web.system
 * @fileName: MenuController.java
 * @date: 2014-3-25
 * @doc: Menu Controller
 */
@Controller
@RequestMapping("/system/menu")
@SuppressWarnings({"rawtypes"})
public class MenuController extends BaseController {
	
	@Autowired
	private MenuService menuService;

	/**
	 * @author: kevin
	 * @date: 2014-3-25
	 * @doc: Menu Index Page
	 * @return
	 */
	@RequestMapping("/")
	public ModelAndView index(){
		ModelAndView model = new ModelAndView("system/menu/menu-index.jsp");
		return model;
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-1
	 * @doc: 新增
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/inputNew")
	@ResponseBody
	public ModelAndView inputNew(String superId) throws Exception{
		ModelAndView view = new ModelAndView("system/menu/menu-input.jsp");
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("id", superId);
		AuMenu menu = menuService.getMenuById(map);
		view.addObject("superMenu", menu);
		return view;
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-1
	 * @doc: 修改
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/inputEdit")
	@ResponseBody
	public ModelAndView inputEdit(String menuId) throws Exception{
		ModelAndView view = new ModelAndView("system/menu/menu-input.jsp");
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("id", menuId);
		AuMenu menu = menuService.getMenuById(map);
		map.put("id", menu.getSuperId());
		AuMenu superMenu = menuService.getMenuById(map);
		view.addObject("menu", menu);
		view.addObject("superMenu", superMenu);
		return view;
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
	public List getTreeList(PageForJqGrid<AuMenu> page,AuMenu menu) throws Exception{
		List<AuMenu> list = menuService.selectAllMenus(new HashMap<String,Object>());
		
		TreeOptions options = new TreeOptions(list);
		options.setId("id");
		options.setText("menuName");
		options.setNodeLevel("menuLevel");
		options.setHasChild("hasChild");
		options.setParentId("superId");
		options.setExtendedFields(new String[]{"iconCode","menuPath","menuOrder","menuCode"});
		options.setShowRoot(true);
		options.setOpenAll(true);
		options.setRootText("所有");
		
		return TreeUtils.getListForJqgridTree(options);
	}
	
	
	/**
	 * @author: kevin
	 * @date: 2014-4-9 下午5:10:06
	 * @Description: 取出所有可用的菜单 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/allAvailableMenu")
	@ResponseBody
	public List getAllAvailableMenus() throws Exception{
		List<AuMenu> list = menuService.selectAllMenus(new HashMap<String,Object>());
		
		TreeOptions options = new TreeOptions(list);
		options.setId("id");
		options.setText("menuName");
		options.setNodeLevel("menuLevel");
		options.setHasChild("hasChild");
		options.setParentId("superId");
		options.setOpenAll(true);
		options.setExtendedFields(new String[]{"iconCode","menuPath"});
		
		List rsList = TreeUtils.getListForJsTree(options);
		return rsList;
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-3-27
	 * @doc: 取用户菜单
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/availableMenu")
	@ResponseBody
	public JsonResult<String> getUserAvailableMenus() throws Exception{
		JsonResult<String> json = new JsonResult<String>();
		json.setResult(LoginUserHelper.getLoginUser().getUserMenuJson());
		return json;
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-3-27
	 * @doc: 按ID取出AuMenu对象
	 * @param menu
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	@ResponseBody
	public JsonResult<AuMenu> save(AuMenu menu) throws Exception{
		menuService.save(menu);
		JsonResult<AuMenu> json = new JsonResult<AuMenu>();
		json.setMsg("done");
		return json;
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-3-28
	 * @doc: 删除
	 * @param delId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public JsonResult<String> delete(String delIds) throws Exception{
		menuService.deleteByIds(delIds);
		JsonResult<String> json = new JsonResult<String>();
		json.setMsg("done");
		return json;
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
	public boolean validateExistCode(String menuCode,String id){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("menuCode", menuCode);
		map.put("id", id);
		AuMenu menu = (AuMenu)menuService.ifExistsMenuCode(map);
		if (EmptyUtil.isEmpty(menu)) {
			return true;
		}else {
			return false;
		}
	}
}
