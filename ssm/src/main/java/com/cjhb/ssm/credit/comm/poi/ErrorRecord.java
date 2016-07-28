/**
* 
*
*@author：
*@since： JDK1.6
*@version：1.0
*@date：2015年6月11日 上午9:54:54
*/ 

package com.cjhb.ssm.credit.comm.poi;

/**
 * @ClassName: ErrorRecord
 * @Description: 出错记录
 * @author 
 * @date 2015年6月11日 上午9:54:54
 *
 */
public class ErrorRecord {
	
	/**
	 * sheet序号
	 */
	private int sheetNo;
	
	/**
	 * 行号
	 */
	private int row;
	
	/**
	 * 列号
	 */
	private int cell;
	
	/**
	 * 列参数
	 */
	private CellOptions cellOptions;
	
	/**
	 * 出错信息
	 */
	private String errorMsg;
	
	/**
	 * 处理方式
	 */
	private String handleType;

	/**
	 * @author: jingleiw@rayootech.com
	 * @date: 2015年6月12日 上午10:34:56
	 * @Description:
	 */
	@SuppressWarnings("unused")
	private ErrorRecord(){
		
	}
	
	/**
	 * @author: jingleiw@rayootech.com
	 * @date: 2015年6月12日 上午10:55:48
	 * @Description:
	 */
	public ErrorRecord(String errorMsg, String handleType) {
		this.errorMsg = errorMsg;
		this.handleType = handleType;
	}

	/**
	 * @author: jingleiw@rayootech.com
	 * @date: 2015年6月12日 上午11:08:42
	 * @Description:
	 */
	public ErrorRecord(int sheetNo, int row, String errorMsg, String handleType) {
		super();
		this.sheetNo = sheetNo;
		this.row = row;
		this.errorMsg = errorMsg;
		this.handleType = handleType;
	}

	/**
	 * @author: jingleiw@rayootech.com
	 * @date: 2015年6月12日 上午10:34:11
	 * @Description:
	 */
	public ErrorRecord(int sheetNo, int row, int cell, CellOptions cellOptions, String errorMsg, String handleType) {
		this.sheetNo = sheetNo;
		this.row = row;
		this.cell = cell;
		this.cellOptions = cellOptions;
		this.errorMsg = errorMsg;
		this.handleType = handleType;
	}

	/**
	 * @author: jingleiw@rayootech.com
	 * @date: 2015年6月12日 上午11:05:04
	 * @Description:
	 */
	public ErrorRecord(int sheetNo, String errorMsg, String handleType) {
		super();
		this.sheetNo = sheetNo;
		this.errorMsg = errorMsg;
		this.handleType = handleType;
	}

	/**
	 * @return the sheetNo
	 */
	public int getSheetNo() {
		return sheetNo;
	}

	/**
	 * @param sheetNo the sheetNo to set
	 */
	public void setSheetNo(int sheetNo) {
		this.sheetNo = sheetNo;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @return the cell
	 */
	public int getCell() {
		return cell;
	}

	/**
	 * @param cell the cell to set
	 */
	public void setCell(int cell) {
		this.cell = cell;
	}

	/**
	 * @return the cellOptions
	 */
	public CellOptions getCellOptions() {
		return cellOptions;
	}

	/**
	 * @param cellOptions the cellOptions to set
	 */
	public void setCellOptions(CellOptions cellOptions) {
		this.cellOptions = cellOptions;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * @return the handleType
	 */
	public String getHandleType() {
		return handleType;
	}

	/**
	 * @param handleType the handleType to set
	 */
	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}
}
