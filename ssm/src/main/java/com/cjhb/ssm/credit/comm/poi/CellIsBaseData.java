/**
* 
*
*@author：
*@since： JDK1.6
*@version：1.0
*@date：2015年6月10日 下午3:45:57
*/ 

package com.cjhb.ssm.credit.comm.poi;

/**
 * @ClassName: CellIsBaseData
 * @Description: 是否是数据字典数据
 * @author 
 * @date 2015年6月10日 下午3:45:57
 *
 */
public enum CellIsBaseData {
	TRUE(true),
	FALSE(false);
	
	boolean value;  
	
	private CellIsBaseData( boolean value ) {  
		this.value = value;  
    }  
}
