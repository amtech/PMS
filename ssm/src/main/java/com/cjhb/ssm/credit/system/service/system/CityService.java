package com.cjhb.ssm.credit.system.service.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.credit.comm.ehcache.CacheName;
import com.credit.comm.ehcache.CacheUpdateMapping;
import com.credit.comm.ehcache.CacheUtils;
import com.credit.dao.system.AuCityMapper;
import com.credit.entity.system.AuCity;

@Component
@Transactional
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CityService {
	
	@Autowired
	private AuCityMapper auCityMapper;

	
	public List<AuCity> getCityListByParameter(Map<String, Object> map){
		return auCityMapper.getCityListByParameter(map);
	}
	
	public List<AuCity> getAllCityList(Map<String, Object> map){
		return auCityMapper.getAllCityList(map);
	}
	
	public List<AuCity> getChildrenById(Map<String, Object> map){
		return auCityMapper.getChildrenById(map);
	}
	
	public AuCity getById(Map<String, Object> map){
		return auCityMapper.getById(map);
	}
	
	public void save(AuCity city){
		auCityMapper.insert(city);
		
		//有父节点，父节点的hasChild改为1
		if(city.getSuperCityId()!=null && !"".equals(city.getSuperCityId())){
			Map<String, Object> map =new HashMap<String, Object>();
			map.put("id", city.getSuperCityId());
			map.put("hasChild", "1");
			auCityMapper.updateHasChild(map);
		}
		
		//更新缓存里的城市内容
		CacheUtils.updateByKey(CacheUpdateMapping.CitySelect);
	}
	
	public void update(AuCity city){
		auCityMapper.update(city);
		//更新缓存里的城市内容
		CacheUtils.updateByKey(CacheUpdateMapping.CitySelect);
	}
	
	public AuCity ifExists(Map<String, Object> map){
		return auCityMapper.ifExists(map);
	}
	
	public void deleteByIds(String delIds){
		List list =  new ArrayList();
		String[] ids = delIds.split(",");
		for (int i = 0; i < ids.length; i++) {
			list.add(ids[i]);
		}
		auCityMapper.delete(list);
		
		//判断这些节点的父节点是否还有子项， 没有了就把haschild改为0
		for (int i = 0; i < ids.length; i++) {
			String cityId = ids[i];
			//查询删除项对象
			Map<String, Object> map =new HashMap<String, Object>();
			map.put("cityId", cityId);
			AuCity city =  auCityMapper.getById(map);
			
			if(city.getSuperCityId()!=null && !"".equals(city.getSuperCityId())){
				Map<String, Object> map1 =new HashMap<String, Object>();
				map1.put("id", city.getSuperCityId());
				//查询父节点对象旗下的子节点
				List<AuCity> list1 = auCityMapper.getChildrenById(map1);
				//子节点为空，就把haschild改为0
				if(list1==null || list1.size()==0){
					Map<String, Object> map2 =new HashMap<String, Object>();
					map2.put("id", city.getSuperCityId());
					map2.put("hasChild", "0");
					auCityMapper.updateHasChild(map2);
				}
			}
		}
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年5月14日 上午10:29:37
	 * @Description: 按类型取城市(用在城市下拉列表)
	 * @param type
	 * @return
	 */
	public List<Map<String,Object>> getCityByType(String type){
		if ("1".equals(type)){
			return (List<Map<String, Object>>) CacheUtils.get(CacheName.COMMONSCACHE, "cityOfProvince");
		}else if ("2".equals(type)){
			return (List<Map<String, Object>>) CacheUtils.get(CacheName.COMMONSCACHE, "cityOfMunicipality");
		}else if ("3".equals(type)){
			return (List<Map<String, Object>>) CacheUtils.get(CacheName.COMMONSCACHE, "cityOfDistrict");
		}else{
			return null;
		}
	}
	
}
