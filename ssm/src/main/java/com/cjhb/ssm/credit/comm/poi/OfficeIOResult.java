/**
* 
*
*@author：
*@since： JDK1.6
*@version：1.0
*@date：2015年6月11日 上午9:53:03
*/ 

package com.cjhb.ssm.credit.comm.poi;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * @ClassName: OfficeIOResult
 * @Description: office导入导出结果
 * @author 
 * @date 2015年6月11日 上午9:53:03
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class OfficeIOResult {
	
	/**
	 * 出错记录信息
	 */
	private final List<ErrorRecord> errors = new ArrayList<ErrorRecord>();
	
	/**
	 * 导入生成的结果集
	 */
	private final List importList = new ArrayList();
	
	/**
	 * 导出结果集
	 */
	private final SXSSFWorkbook resultWorkbook = new SXSSFWorkbook(10000);
	
	/**
	 * 返回成功的结果条数
	 */
	private long[] resultTotal;

	/**
	 * @author: jingleiw@rayootech.com
	 * @date: 2015年6月12日 下午1:10:45
	 * @Description:
	 */
	@SuppressWarnings("unused")
	private OfficeIOResult(){
		
	}
	
	/**
	 * @author: jingleiw@rayootech.com
	 * @date: 2015年6月12日 下午1:10:54
	 * @Description:
	 */
	public OfficeIOResult(SheetOptions[] sheets ){
		if (sheets != null){
			resultTotal = new long[sheets.length];
		}
	}
	
	/**
	 * @return the resultTotal
	 */
	public long[] getResultTotal() {
		return resultTotal;
	}

	/**
	 * @param resultTotal the resultTotal to set
	 */
	public void setResultTotal(long[] resultTotal) {
		this.resultTotal = resultTotal;
	}

	/**
	 * @return the errors
	 */
	public List<ErrorRecord> getErrors() {
		return errors;
	}

	/**
	 * @return the importList
	 */
	public List getImportList() {
		return importList;
	}
	
	public void addSheetList(List sheetList){
		importList.add(sheetList);
	}

	/**
	 * @return the resultWorkbook
	 */
	public SXSSFWorkbook getResultWorkbook() {
		return resultWorkbook;
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年6月12日 上午10:36:24
	 * @Description: 将ErrorRecord添加 到列表中
	 * @param errorRecord
	 */
	public void addErrorRecord(ErrorRecord errorRecord){
		this.errors.add(errorRecord);
	}
}
