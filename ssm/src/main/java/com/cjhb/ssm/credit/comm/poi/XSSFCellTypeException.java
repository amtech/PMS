/**
* 
*
*@author：
*@since： JDK1.6
*@version：1.0
*@date：2015年6月12日 上午11:11:43
*/ 

package com.cjhb.ssm.credit.comm.poi;

/**
 * @ClassName: XSSFCellTypeException
 * @Description: 读取CELL时类型异常
 * @author 
 * @date 2015年6月12日 上午11:11:43
 *
 */
public class XSSFCellTypeException extends Exception {
	
	private static final long serialVersionUID = 264138895652354060L;

	public XSSFCellTypeException() {
		super();
	}
	public XSSFCellTypeException(String msg) {
		super(msg);
	}
	public XSSFCellTypeException(String msg, Throwable cause) {
		super(msg, cause);
	}
	public XSSFCellTypeException(Throwable cause) {
		super(cause);
	}
}
