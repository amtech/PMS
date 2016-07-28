/**
* 
*
*@author：
*@since： JDK1.6
*@version：1.0
*@date：2014-4-21 下午4:12:13
*/ 

package com.cjhb.ssm.credit.system.service.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.dao.system.DdBaseDataItemMapper;
import com.credit.dao.system.DdBaseDataTypeMapper;
import com.credit.entity.system.BaseDataItem;
import com.credit.entity.system.BaseDataType;

/**
 * @ClassName: BaseDataTypeService
 * @Description: 数据字典类型
 * @author 
 * @date 2014-4-21 下午4:12:13
 *
 */
@Transactional
@SuppressWarnings({ "rawtypes", "unchecked" })
@Service("baseDataService")
public class BaseDataService {

	@Autowired
	private DdBaseDataTypeMapper ddBaseDataTypeMapper;
	
	@Autowired
	private DdBaseDataItemMapper ddBaseDataItemMapper;
	
	/**
	 * @author: kevin
	 * @date: 2014-4-22 上午9:59:11
	 * @Description: 获取所有数据字典
	 * @param map
	 * @return
	 */
	public List<BaseDataType> getBaseDataTypeList(Map<String,Object> map){
		return ddBaseDataTypeMapper.getBaseDataTypeList(map);
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-22 上午9:59:36
	 * @Description: 按ID获取数据字典
	 * @param editId
	 * @return
	 */
	public BaseDataType getById(String editId){
		return (BaseDataType) ddBaseDataTypeMapper.get(editId);
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-22 上午10:01:24
	 * @Description: 保存数据字典类型
	 * @param baseDataType
	 */
	public void saveOrUpdateBaseDataType(BaseDataType baseDataType){
		if (StringUtils.isBlank(baseDataType.getId())){
			baseDataType.setIsValid("0");
			baseDataType.setIsDel("0");
			ddBaseDataTypeMapper.insert(baseDataType);
		}else{
			ddBaseDataTypeMapper.update(baseDataType);
			if (!baseDataType.getOldCode().equals(baseDataType.getCode())){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("typeId", baseDataType.getId());
				map.put("code", baseDataType.getCode());
				ddBaseDataItemMapper.updateCodeByTypeId(map);
			}
		}
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-22 上午10:01:24
	 * @Description: 保存数据字典项
	 * @param baseDataType
	 */
	public void saveOrUpdateBaseDataItem(BaseDataItem baseDataItem){
		if (StringUtils.isBlank(baseDataItem.getId())){
			baseDataItem.setIsValid("1");
			baseDataItem.setIsDel("0");
			ddBaseDataItemMapper.insert(baseDataItem);
		}else{
			ddBaseDataItemMapper.update(baseDataItem);
		}
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-22 上午10:10:20
	 * @Description: 检验类型编码是否已存在
	 * @param map
	 * @return
	 */
	public BaseDataType ifTypeExists(Map<String,Object> map){
		return ddBaseDataTypeMapper.ifTypeExists(map);
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-22 上午10:10:20
	 * @Description: 检验项编码是否已存在
	 * @param map
	 * @return
	 */
	public BaseDataItem ifItemExists(Map<String,Object> map){
		return ddBaseDataItemMapper.ifItemExists(map);
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-22 下午1:10:31
	 * @Description: 
	 * @param typeId
	 * @return
	 */
	public List<Map<String,Object>> getBaseDataTree(String typeId){
		List<Map<String,Object>> treeList = new ArrayList<Map<String,Object>>();
		BaseDataType baseDataType = (BaseDataType) ddBaseDataTypeMapper.get(typeId);
		Map<String,Object> rootMap = new HashMap<String,Object>();
		rootMap.put("id", baseDataType.getId());
		rootMap.put("text", baseDataType.getName());
		rootMap.put("parent", "#");
		Map<String,Object> stateMap = new HashMap<String,Object>();
		stateMap.put("opened",true);
		rootMap.put("state", stateMap);
		treeList.add(rootMap);
		List<BaseDataItem> itemList = ddBaseDataItemMapper.getBaseDataItemByTypeId(typeId);
		for (BaseDataItem baseDataItem : itemList) {
			Map<String,Object> childMap = new HashMap<String,Object>();
			childMap.put("id", baseDataItem.getId());
			childMap.put("text", baseDataItem.getName());
			childMap.put("parent", baseDataItem.getTypeId());
			childMap.put("code", baseDataItem.getCode());
			childMap.put("typeCode", baseDataItem.getTypeCode());
			childMap.put("seqNo", baseDataItem.getSeqNo());
			childMap.put("description", baseDataItem.getDescription());
			childMap.put("isValid", baseDataItem.getIsValid());
			treeList.add(childMap);
		}
		return treeList;
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-24 下午1:32:29
	 * @Description: deteleType
	 * @param delIds
	 */
	public void deleteTypeByIds(String delIds){
		List list =  new ArrayList();
		String[] ids = delIds.split(",");
		for (int i = 0; i < ids.length; i++) {
			list.add(ids[i]);
		}
		ddBaseDataTypeMapper.deleteType(list);
	}
	
	public List<BaseDataItem> getBaseDataItemByTypeCode(String code){
		return ddBaseDataItemMapper.getBaseDataItemByTypeCode(code);
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年5月28日 下午1:21:22
	 * @Description: 改变是否有效
	 * @param map
	 */
	public void updateIsValid(Map map){
		ddBaseDataTypeMapper.updateIsValid(map);
	}
}
