/**
* 
*
*@author：
*@since： JDK1.6
*@version：1.0
*@date：2015年5月21日 上午11:26:38
*/ 
package com.cjhb.ssm.credit.comm.ehcache;

/**
 * @ClassName: CacheUpdateMapping
 * @Description: CacheUpdateMapping枚举类
 * @author 
 * @date 2015年5月26日 下午1:49:38
 *
 */
public enum CacheUpdateMapping {
	
	AgentSelect("putAgent"),
	CitySelect("putCity"),
	NationalitySelect("putNationality"),
	RolePath("putRolePath");
	
	String name;  
	
	private CacheUpdateMapping( String name ) {  
		this.name = name;  
    }  
}
