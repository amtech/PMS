/**
* 
*
*@author：
*@since： JDK1.6
*@version：1.0
*@date：2015年6月10日 下午3:15:07
*/ 

package com.cjhb.ssm.credit.comm.poi;

import java.util.Map;

/**
 * @ClassName: CellOptions
 * @Description: 单元格设置
 * @author 
 * @date 2015年6月10日 下午3:15:07
 *
 */
public class CellOptions {
	
	/**
	 * 对象的属性名
	 */
	private String key;
	
	/**
	 * 列名
	 */
	private String colName;
	
	/**
	 * 是否是必填项
	 */
	private CellIsRequired isRequired = CellIsRequired.FALSE;
	
	/**
	 * 是否是数据字典
	 */
	private CellIsBaseData isBaseData = CellIsBaseData.FALSE;
	
	/**
	 * 数据字典CODE
	 */
	private String baseDataCode;
	
	/**
	 * 固定值 
	 */
	private CellIsFixed isFixedValue = CellIsFixed.FALSE;
	
	/**
	 * 固定值内容
	 */
	private Map<String,Object> fixedMap;

	
	/**
	 * @author: jingleiw@rayootech.com
	 * @date: 2015年6月10日 下午5:00:42
	 * @Description:隐藏构造方法
	 */
	@SuppressWarnings("unused")
	private CellOptions(){
		
	}
	
	/**
	 * @author: jingleiw@rayootech.com
	 * @date: 2015年6月10日 下午5:08:32
	 * @Description:设置关键字与显示列名
	 */
	public CellOptions(String key,String colName){
		this.key = key;
		this.colName = colName;
	}
	
	/**
	 * @author: jingleiw@rayootech.com
	 * @date: 2015年6月10日 下午5:08:32
	 * @Description:设置关键字与显示列名
	 */
	public CellOptions(String key,String colName,CellIsRequired isRequired){
		this.key = key;
		this.colName = colName;
		this.isRequired = isRequired;
	}
	
	/**
	 * @author: jingleiw@rayootech.com
	 * @date: 2015年6月10日 下午5:08:52
	 * @Description:设置关键字与显示列名、并标记为数据字典，及给出数据字典的CODE
	 */
	public CellOptions(String key,String colName,CellIsRequired isRequired,CellIsBaseData isBaseData,String baseDataCode){
		this.key = key;
		this.colName = colName;
		this.isRequired = isRequired;
		this.isBaseData = isBaseData;
		this.baseDataCode = baseDataCode;
	}
	
	/**
	 * @author: jingleiw@rayootech.com
	 * @date: 2015年6月10日 下午5:08:52
	 * @Description:设置关键字与显示列名、并标记为数据字典，及给出数据字典的CODE
	 */
	public CellOptions(String key,String colName,CellIsBaseData isBaseData,String baseDataCode){
		this.key = key;
		this.colName = colName;
		this.isBaseData = isBaseData;
		this.baseDataCode = baseDataCode;
	}
	
	/**
	 * @author: jingleiw@rayootech.com
	 * @date: 2015年6月10日 下午5:09:34
	 * @Description:设置关键字与显示列名、并标记为固定数据，及给出固定数据的值 
	 */
	public CellOptions(String key,String colName,CellIsFixed isFixedValue,Map<String,Object> fixedMap){
		this.key = key;
		this.colName = colName;
		this.isFixedValue = isFixedValue;
		this.fixedMap = fixedMap;
	}
	
	/**
	 * @author: jingleiw@rayootech.com
	 * @date: 2015年6月10日 下午5:09:34
	 * @Description:设置关键字与显示列名、并标记为固定数据，及给出固定数据的值 
	 */
	public CellOptions(String key,String colName,CellIsRequired isRequired,CellIsFixed isFixedValue,Map<String,Object> fixedMap){
		this.key = key;
		this.colName = colName;
		this.isRequired = isRequired;
		this.isFixedValue = isFixedValue;
		this.fixedMap = fixedMap;
	}
	
	/**
	 * @author: jingleiw@rayootech.com
	 * @date: 2015年6月10日 下午5:08:03
	 * @Description:设置是所有属性
	 */
	public CellOptions(String key,String colName,CellIsRequired isRequired,CellIsBaseData isBaseData,String baseDataCode,CellIsFixed isFixedValue,Map<String,Object> fixedMap){
		this.key = key;
		this.colName = colName;
		this.isRequired = isRequired;
		this.isBaseData = isBaseData;
		this.baseDataCode = baseDataCode;
		this.isFixedValue = isFixedValue;
		this.fixedMap = fixedMap;
	}
	
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @return the colName
	 */
	public String getColName() {
		return colName;
	}

	/**
	 * @return the isBaseData
	 */
	public CellIsBaseData getIsBaseData() {
		return isBaseData;
	}

	/**
	 * @return the baseDataCode
	 */
	public String getBaseDataCode() {
		return baseDataCode;
	}

	/**
	 * @return the isFixedValue
	 */
	public CellIsFixed getIsFixedValue() {
		return isFixedValue;
	}

	/**
	 * @return the fixedMap
	 */
	public Map<String, Object> getFixedMap() {
		return fixedMap;
	}

	/**
	 * @return the isRequired
	 */
	public CellIsRequired getIsRequired() {
		return isRequired;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return 
				"key : " + this.key + ", " + 
				"colName : " + this.colName + ", " + 
				"isBaseData : " + this.isBaseData.value + ", " + 
				"baseDataCode : " + this.baseDataCode + ", " + 
				"isFixedValue : " + this.isFixedValue.value + ", " + 
				"isRequired : " + this.isRequired.value;
	}

}