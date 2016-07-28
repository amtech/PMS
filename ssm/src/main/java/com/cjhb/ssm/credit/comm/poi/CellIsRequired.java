/**
* 
*
*@author：
*@since： JDK1.6
*@version：1.0
*@date：2015年6月10日 下午5:41:34
*/ 

package com.cjhb.ssm.credit.comm.poi;

/**
 * @ClassName: CellIsRequired
 * @Description: 是否是必填项
 * @author 
 * @date 2015年6月10日 下午5:41:34
 *
 */
public enum CellIsRequired {
	TRUE(true),
	FALSE(false);
	
	boolean value;  
	
	private CellIsRequired( boolean value ) {  
		this.value = value;  
    }  
}
