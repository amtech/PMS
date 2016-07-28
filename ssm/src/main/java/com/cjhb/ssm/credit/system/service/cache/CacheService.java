/**
* 
*
*@author：
*@since： JDK1.6
*@version：1.0
*@date：2015年5月20日 下午10:07:26
*/ 
package com.cjhb.ssm.credit.system.service.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.credit.dao.cache.CacheMapper;
import com.credit.entity.system.AuMenu;
import com.credit.entity.system.BaseDataItem;

/**
 * 
* @ClassName: CacheService
* @Description: CacheService
* @author 
* @date 2015年5月20日 下午10:07:26
*
 */
@Component
@Transactional
@SuppressWarnings({"rawtypes","unchecked"})
public class CacheService {
	
	@Autowired
	public CacheMapper cacheMapper;
	
	private Map emptyMap = new HashMap();
	
	/**
	 * @author: kevin
	 * @date: 2015年5月22日 上午10:34:27
	 * @Description: 获取民族数据
	 * @param map
	 * @return
	 */
	public List getNationalitySelect(){
		return cacheMapper.getNationality(emptyMap);
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年5月14日 上午10:29:37
	 * @Description: 按类型取城市(用在城市下拉列表)
	 * @param type
	 * @return
	 */
	public List getCityByType(String type){
		return cacheMapper.getCityByType(type);
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-24 下午3:25:47
	 * @Description: 获取所有可以在用项
	 * @param code
	 * @return
	 */
	public List<BaseDataItem> getBaseDataByCode(String code){
		return cacheMapper.getBaseDataByCode(code);
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-2
	 * @doc: 取出所有菜单与权限的对应关系
	 * @param map
	 * @return
	 */
	public List getAllMenuRoleRel(){
		return cacheMapper.getAllMenuRoleRel(emptyMap);
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年6月12日 下午3:43:16
	 * @Description: 取出所有菜单信息
	 * @return
	 */
	public List<AuMenu> getAllMenu(){
		return cacheMapper.getAllMenu(emptyMap);
	}
}
