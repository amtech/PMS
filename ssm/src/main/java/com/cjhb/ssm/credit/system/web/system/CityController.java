package com.cjhb.ssm.credit.system.web.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
import com.credit.entity.system.AuCity;
import com.credit.service.system.CityService;
import com.credit.web.BaseController;

@Controller
@RequestMapping("/system/city")
@SuppressWarnings({"rawtypes"})
public class CityController extends BaseController {
	
	@Autowired
	private CityService cityService;
	
	@RequestMapping("/")
	public ModelAndView index(){
		return new ModelAndView("system/city/city-index.jsp");
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public PageForJqGrid<AuCity> getList(PageForJqGrid<AuCity> page,AuCity city) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cityName", city.getCityName());
		map = page.pageToMap(page,map);
		List<AuCity> list = cityService.getCityListByParameter(map);
		page.listToPage(page, list);
		return page;
	}
	
	@RequestMapping("/listForTree")
	@ResponseBody
	public List getTreeList(String showRoot) throws Exception{
		List<AuCity> list = cityService.getAllCityList(new HashMap<String,Object>());
		
		TreeOptions options = new TreeOptions(list);
		options.setId("id");
		options.setText("cityName");
		options.setNodeLevel("cityType");
		options.setHasChild("hasChild");
		options.setParentId("superCityId");
		options.setExtendedFields(new String[]{"cityCode"});
		if ("0".equals(showRoot)){
			options.setShowRoot(false);
		}else{
			options.setShowRoot(true);
			options.setRootText("全国");
		}
		
		return TreeUtils.getListForJsTree(options);
	}
	
	@RequestMapping("/input")
	@ResponseBody
	public ModelAndView input(String cityId) throws Exception{
		ModelAndView view = new ModelAndView("system/city/city-input.jsp");
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("cityId", cityId);
		AuCity city = cityService.getById(map);
		view.addObject("city", city);
		return view;
	}
	
	@RequestMapping("/getChildrenById")
	@ResponseBody
	public PageForJqGrid<AuCity> getChildrenById(PageForJqGrid<AuCity> page,AuCity city) throws Exception{
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("id", city.getId());
		map = page.pageToMap(page,map);
		List<AuCity> list = cityService.getChildrenById(map);
		page.listToPage(page, list);
		return page;
	}
	
	@RequestMapping("save")
	@ResponseBody
	public JsonResult<AuCity> save(AuCity city){
		city.setIsDel("0");
		if(city.getIsValid()!=null && "on".equals(city.getIsValid())){
			city.setIsValid("1");
		}else{
			city.setIsValid("0");
		}
		
		if(city.getId()==null || "".equals(city.getId())){
			//新增
			if(city.getSuperCityId()==null || "".equals(city.getSuperCityId())){
				//没有父ID，即为第一级组织机构
				city.setCityType("1");
			}
			cityService.save(city);
		}else{
			//修改的情况
			cityService.update(city);
		}
		JsonResult<AuCity> json = new JsonResult<AuCity>();
		json.setResult(city);
		return json;
	}
	
	@RequestMapping("validateExist")
	@ResponseBody
	public boolean validateExist(String cityCode,String cityId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cityCode", cityCode);
		map.put("id", cityId);
		map.put("isDel", "0");
		AuCity city = (AuCity)cityService.ifExists(map);
		if (EmptyUtil.isEmpty(city)) {
			return true;
		}else {
			return false;
		}
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public JsonResult<String> delete(String delIds){
		cityService.deleteByIds(delIds);
		JsonResult<String> json = new JsonResult<String>();
		json.setResult("done");
		return json;
	}
	
	@RequestMapping("/citySelect")
	@ResponseBody
	public List<Map<String,Object>> citySelect(String type){
		if (StringUtils.isBlank(type)){
			type = "3";
		}
		return cityService.getCityByType(type);
	}
}
