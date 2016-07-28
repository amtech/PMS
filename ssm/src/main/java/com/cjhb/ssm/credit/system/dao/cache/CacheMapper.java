/**
* 
*
*@author：
*@since： JDK1.6
*@version：1.0
*@date：2015年5月20日 下午10:07:26
*/ 
package com.cjhb.ssm.credit.system.dao.cache;

import com.cjhb.ssm.credit.system.dao.BaseMapper;
import com.cjhb.ssm.credit.system.entity.system.AuMenu;
import com.cjhb.ssm.credit.system.entity.system.BaseDataItem;

import java.util.List;
import java.util.Map;

/**
 * 
* @ClassName: CacheMapper
* @Description: CacheMapper
* @author 
* @date 2015年5月20日 下午10:07:26
*
 */
@SuppressWarnings("rawtypes")
public interface CacheMapper extends BaseMapper {
	
	/**
	 * @author: kevin
	 * @date: 2015年5月14日 上午10:24:16
	 * @Description: 按类型取城市(用在城市下拉列表)
	 * @param type
	 * @return
	 */
	public List getCityByType(String type);
	
	/**
	 * @author: kevin
	 * @date: 2015年5月21日 下午4:46:16
	 * @Description: 数据字典
	 * @param code
	 * @return
	 */
	public List<BaseDataItem> getBaseDataByCode(String code);
	
	/**
	 * @author: kevin
	 * @date: 2015年5月22日 上午10:34:27
	 * @Description: 获取民族数据
	 * @param map
	 * @return
	 */
	public List<Map<String,String>> getNationality(Map<String, Object> map);
	
	/**
	 * @author: kevin
	 * @date: 2014-4-2
	 * @doc: 取出所有菜单与权限的对应关系
	 * @param map
	 * @return
	 */
	public List getAllMenuRoleRel(Map<String, Object> map);
	
	/**
	 * @author: kevin
	 * @date: 2015年6月12日 下午3:43:16
	 * @Description: 取出所有菜单信息
	 * @return
	 */
	public List<AuMenu> getAllMenu(Map<String, Object> map);
}