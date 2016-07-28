package com.cjhb.ssm.credit.system.dao.system;

import java.util.List;
import java.util.Map;

import com.credit.dao.BaseMapper;
import com.credit.entity.BaseEntity;
import com.credit.entity.system.AuCity;

@SuppressWarnings("rawtypes")
public interface AuCityMapper extends BaseMapper {

public List<AuCity> getCityListByParameter(Map<String, Object> map);
	

	public AuCity getByName(String name);

	public AuCity getByCode(String code);


	public List<AuCity> getAllCityList(Map<String, Object> map);
	
	public List<AuCity> getChildrenById(Map<String, Object> map);
	
	public AuCity getById(Map<String, Object> map);
	
	public AuCity ifExists(Map<String, Object> map);
	
	public void updateHasChild(Map<String, Object> map);
	
	public void delete(List list);
	
	public List<AuCity> getAllCity(Map<String, Object> map);
	
}