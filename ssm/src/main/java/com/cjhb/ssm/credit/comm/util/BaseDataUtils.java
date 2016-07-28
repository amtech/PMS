/**
* 
*
*@author：
*@since： JDK1.6
*@version：1.0
*@date：2014-4-24 下午3:21:35
*/ 

package com.cjhb.ssm.credit.comm.util;

import java.util.List;

import com.credit.comm.ehcache.CacheName;
import com.credit.comm.ehcache.CacheUtils;
import com.credit.entity.system.BaseDataItem;

/**
 * @ClassName: BaseDataUtils
 * @Description: 数据字典工具类
 * @author 
 * @date 2014-4-24 下午3:21:35
 *
 */
@SuppressWarnings("unchecked")
public class BaseDataUtils {
	
	/**
	 * @author: kevin
	 * @date: 2014-4-24 下午3:37:09
	 * @Description: 按CODE取项
	 * @param code
	 * @return
	 */
	public static List<BaseDataItem> getBaseDataItems(String code){
		return (List<BaseDataItem>) CacheUtils.get(CacheName.BASEDATACACHE, code);
	}
	
	/**
	 * @author: kevin
	 * @date: 2014-4-24 下午3:37:20
	 * @Description: 按CODE和VALUE与名称
	 * @param code
	 * @param value
	 * @return
	 */
	public static String getNameByValue(String code,String value){
		List<BaseDataItem> itemList = getBaseDataItems(code);
		if (itemList != null && itemList.size() > 0){
			for (BaseDataItem baseDataItem : itemList) {
				if (baseDataItem.getCode().equals(value)){
					return baseDataItem.getName();
				}
			}
		}
		return value;
	}
}
