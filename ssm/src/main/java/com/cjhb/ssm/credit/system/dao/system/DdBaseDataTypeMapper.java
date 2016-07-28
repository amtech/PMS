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
import com.credit.entity.system.BaseDataType;

/**
 * @ClassName: DdBaseDataTypeMapper
 * @Description: 数据字典类型
 * @author 
 * @date 2014-4-21 下午4:06:57
 *
 */
@SuppressWarnings("rawtypes")
public interface DdBaseDataTypeMapper extends BaseMapper {

	/**
	 * @author: kevin
	 * @date: 2014-4-22 上午10:13:41
	 * @Description: 获取所有数据字典
	 * @param map
	 * @return
	 */
	public List<BaseDataType> getBaseDataTypeList(Map<String, Object> map);
	
	/**
	 * @author: kevin
	 * @date: 2014-4-22 下午1:08:43
	 * @Description: 按编码取数据类型
	 * @param code
	 * @return
	 */
	public BaseDataType getBaseDataTypeByCode(String code);
	
	/**
	 * @author: kevin
	 * @date: 2014-4-22 上午10:13:44
	 * @Description: 检验类型编码是否已存在
	 * @param map
	 * @return
	 */
	public BaseDataType ifTypeExists(Map<String, Object> map);
	
	/**
	 * @author: kevin
	 * @date: 2014-4-22 上午11:35:36
	 * @Description: 
	 * @param baseDataType
	 * @return
	 */
	public void insert(BaseDataType baseDataType);
	
	/**
	 * @author: kevin
	 * @date: 2014-4-24 下午1:32:06
	 * @Description: 
	 * @param list
	 */
	public void deleteType(List list);
	
	/**
	 * @author: kevin
	 * @date: 2015年5月28日 下午1:21:22
	 * @Description: 改变是否有效
	 * @param map
	 */
	public void updateIsValid(Map map);
}
