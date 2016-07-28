/**
* 
*
*@author：
*@since： JDK1.6
*@version：1.0
*@date：2014-4-21 下午4:06:57
*/ 

package com.cjhb.ssm.credit.system.dao.system;

import java.util.List;
import java.util.Map;

import com.credit.dao.BaseMapper;
import com.credit.entity.system.BaseDataItem;

/**
 * @ClassName: DdBaseDataTypeMapper
 * @Description: 数据字典项目
 * @author 
 * @date 2014-4-21 下午4:06:57
 *
 */
public interface DdBaseDataItemMapper extends BaseMapper {

	/**
	 * @author: kevin
	 * @date: 2014-4-22 下午1:38:34
	 * @Description: 按typeCode取ITEM
	 * @param typeCode
	 * @return
	 */
	public List<BaseDataItem> getBaseDataItemByTypeCode(String typeCode);
	
	/**
	 * @author: kevin
	 * @date: 2014-4-22 下午1:38:34
	 * @Description: 按typeId取ITEM
	 * @param typeId
	 * @return
	 */
	public List<BaseDataItem> getBaseDataItemByTypeId(String typeId);
	
	/**
	 * @author: kevin
	 * @date: 2014-4-22 下午2:49:05
	 * @Description: 校验数据项是否存在
	 * @param map
	 * @return
	 */
	public BaseDataItem ifItemExists(Map<String, Object> map);
	
	/**
	 * @author: kevin
	 * @date: 2014-4-24 下午3:25:47
	 * @Description: 获取所有可以在用项
	 * @return
	 */
	public List<BaseDataItem> getAllAvailableItem(String isDel);
	
	/**
	 * @author: kevin
	 * @date: 2015年5月8日 下午1:39:59
	 * @Description: 更新ITEM的TYPECODE
	 * @param map
	 */
	public void updateCodeByTypeId(Map<String, Object> map);
}