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
 * @ClassName: CacheName
 * @Description: CacheName枚举类
 * @author 
 * @date 2015年5月21日 上午11:26:38
 *
 */
public enum CacheName {
	COMMONSCACHE("CommonsCache"),
	INDEXCACHE("IndexCache"),
	BASEDATACACHE("BaseDataCache");
	
	String name;  
	
	private CacheName( String name ) {  
		this.name = name;  
    }  
}
