/**
* 
*
*@author：
*@since： JDK1.6
*@version：1.0
*@date：2015年6月10日 下午3:48:16
*/ 

package com.cjhb.ssm.credit.comm.poi;

/**
 * @ClassName: CellIsFixed
 * @Description: 是否是固定
 * @author 
 * @date 2015年6月10日 下午3:48:16
 *
 */
public enum CellIsFixed {
	TRUE(true),
	FALSE(false);
	
	boolean value;  
	
	private CellIsFixed( boolean value ) {  
		this.value = value;  
    }  
}
