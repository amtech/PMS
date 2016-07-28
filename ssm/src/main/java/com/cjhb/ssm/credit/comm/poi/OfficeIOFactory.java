/**
 * 
 *
 *@author：
 *@since： JDK1.6
 *@version：1.0
 *@date：2015年6月11日 上午9:46:36
 */

package com.cjhb.ssm.credit.comm.poi;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.credit.comm.util.BaseDataUtils;

/**
 * @ClassName: OfficeIOFactory
 * @Description: OfficeIOFactory
 * @author 
 * @date 2015年6月11日 上午9:46:36
 * 
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class OfficeIOFactory {
	
	private final static Logger log = Logger.getLogger(OfficeIOFactory.class);
	
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * @author: kevin
	 * @date: 2015年6月12日 上午11:41:37
	 * @Description: 导出模板
	 * @param sheets
	 * @return
	 */
	protected final OfficeIOResult exportXlsxTemplet(SheetOptions[] sheets) {
		//实例化返回对象
		OfficeIOResult result = new OfficeIOResult(sheets);
		//循环构建sheet
		for (int sheetIndex = 0; sheetIndex < sheets.length; sheetIndex++) {
			//创建sheet
			Sheet sheet = result.getResultWorkbook().createSheet(sheets[sheetIndex].getSheetName());
			//取出CellOptions
			CellOptions[] cells = sheets[sheetIndex].getCellOptions();
			//设置列头
			Row titleRow = sheet.createRow(0);
			for (int titleIndex = 0; titleIndex < cells.length; titleIndex++) {
				//构建一个CELL
				Cell titleCell = titleRow.createCell(titleIndex);
				//设置CELL为文本格式
				titleCell.setCellType(XSSFCell.CELL_TYPE_STRING);
        		//设置宽度
            	//sheet.setColumnWidth(cellIndex, 400);
        		//写入内容
				titleCell.setCellValue(cells[titleIndex].getColName());
			}
		}
		return result;
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年6月11日 上午10:01:54
	 * @Description:导出
	 * @return
	 */
	protected final OfficeIOResult exportXlsx(SheetOptions[] sheets) {
		//实例化返回对象
		OfficeIOResult result = new OfficeIOResult(sheets);
		//循环构建sheet
		for (int sheetIndex = 0; sheetIndex < sheets.length; sheetIndex++) {
			//创建sheet
			Sheet sheet = result.getResultWorkbook().createSheet(sheets[sheetIndex].getSheetName());
			//取出当前sheet所要导出的数据
			List dataList = sheets[sheetIndex].getExportData();
			//取出CellOptions
			CellOptions[] cells = sheets[sheetIndex].getCellOptions();
			//设置列头
			Row titleRow = sheet.createRow(0);
			for (int titleIndex = 0; titleIndex < cells.length; titleIndex++) {
				//构建一个CELL
				Cell titleCell = titleRow.createCell(titleIndex);
				//设置CELL为文本格式
				titleCell.setCellType(XSSFCell.CELL_TYPE_STRING);
        		//设置宽度
            	//sheet.setColumnWidth(cellIndex, 400);
        		//写入内容
				titleCell.setCellValue(cells[titleIndex].getColName());
			}
			//循环新增每一条数据
			long successCount = 0;
			rowLoop : for (int dataIndex = 1; dataIndex <= dataList.size(); dataIndex++) {
				//取出当前行的数据对象
				Object bean = dataList.get(dataIndex - 1);
				//新增行
				Row row = sheet.createRow(dataIndex);
				//循环列配置为第一列赋值 
				for (int cellIndex = 0; cellIndex < cells.length; cellIndex++) {
					//构建一个CELL
					Cell cell = row.createCell(cellIndex);
					//设置CELL为文本格式
            		cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            		//设置宽度
                	//sheet.setColumnWidth(cellIndex, 400);
            		//写入内容
            		try {
						cell.setCellValue(getValue(cells[cellIndex],bean));
					} catch (IllegalArgumentException e) {
						result.addErrorRecord(new ErrorRecord(sheetIndex, dataIndex - 1, cellIndex, cells[cellIndex], "数据异常(数据类型转换导致)", "跳过行处理"));
						continue rowLoop;
					} catch (NoSuchMethodException e) {
						result.addErrorRecord(new ErrorRecord(sheetIndex, dataIndex - 1, cellIndex, cells[cellIndex], "属性异常(无法找到相应的属性)", "跳过行处理"));
						continue rowLoop;
					} catch (InvocationTargetException e) {
						result.addErrorRecord(new ErrorRecord(sheetIndex, dataIndex - 1, cellIndex, cells[cellIndex], "数据集异常(集合中的单个数据集异常)", "跳过行处理"));
						continue rowLoop;
					} catch (IllegalAccessException e) {
						result.addErrorRecord(new ErrorRecord(sheetIndex, dataIndex - 1, cellIndex, cells[cellIndex], "Bean方法调用异常(无法正常调用方法)", "跳过行处理"));
						continue rowLoop;
					}
				}
				//记录成功结果
				successCount++;
			}
			//将成功条数放入result中
			result.getResultTotal()[sheetIndex] = successCount;
		}
		
		return result;
	}

	/**
	 * @author: kevin
	 * @date: 2015年6月11日 上午10:24:29
	 * @Description: 导入
	 * @param file
	 * @param cells
	 * @param skipRows
	 * @return
	 */
	protected final OfficeIOResult importXlsx(File file,SheetOptions[] sheets) {
		// 按文件取出工作簿
		XSSFWorkbook wb = null;
		try {
			wb = getXSSFWorkbook(file);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return loadWorkbook(wb, sheets);
	}

	/**
	 * @author: kevin
	 * @date: 2015年6月11日 上午10:48:34
	 * @Description: 得到工作本
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private final XSSFWorkbook getXSSFWorkbook(File file) throws IOException {
		FileInputStream input = new FileInputStream(file); 
		return new XSSFWorkbook(new BufferedInputStream(input));
	}

	/**
	 * @author: kevin
	 * @date: 2015年6月11日 上午11:17:50
	 * @Description: 按sheetOptions读取workbook中的数据
	 * @param wb
	 * @param sheets
	 * @param skipRows
	 * @return
	 */
	private OfficeIOResult loadWorkbook(XSSFWorkbook wb, SheetOptions[] sheets) {
		
		OfficeIOResult result =  new OfficeIOResult(sheets);
		
		//文件异常时处理
		if (wb == null){
			result.addErrorRecord(new ErrorRecord("文件无法读取或读取异常", "跳过所有处理"));
			return result;
		}
		
		int sheetNumbers = wb.getNumberOfSheets();
		
		long successCount = 0;
		for (int sheetIndex = 0; sheetIndex < sheets.length; sheetIndex++) {
			if (sheets[sheetIndex].getSheetSeq() > sheetNumbers) {
				result.addErrorRecord(new ErrorRecord(sheets[sheetIndex].getSheetSeq(), "无法在文件中找到指定的sheet序号", "跳过sheet处理"));
				continue;
			}
			// 取提对应的sheeet
			Sheet sheet = wb.getSheetAt(sheets[sheetIndex].getSheetSeq());
			List sheetList = new ArrayList();
			// 获取表中的总行数
			int rowsNum = sheet.getLastRowNum();
			// 对每张表中的列进行读取处理
			CellOptions[] cells = sheets[sheetIndex].getCellOptions();
			// 循环每一行
			rowLoop : for (int row = 0; row <= rowsNum; row++) {
				//判断是否是在skipRow之内
				if (row < sheets[sheetIndex].getSkipRows()){
					continue;
				}
				// 取的当前行
				Row activeRow = sheet.getRow(row);
				// 判断当前行记录是否有有效
				if (activeRow != null && !(activeRow.equals(""))) {
					// 第一行的各列放在一个MAP中
					Map resultMap = new HashMap();
					// 循环每一列按列所给的参数进行处理
					for (int cellIndex = 0; cellIndex < cells.length; cellIndex++) {
						Cell cell = activeRow.getCell(cellIndex);
						if (cell != null) {
							try {
								Object obj = getCellValue(cell, cells[cellIndex]);
								//判断导入时某些字段不能为空
								if (StringUtils.isBlank(String.valueOf(obj)) && cells[cellIndex].getIsRequired().value){
									result.addErrorRecord(new ErrorRecord(sheetIndex, row, cellIndex, cells[cellIndex], "当前列不能为空", "跳过行处理"));
									continue rowLoop;
								}else{
									resultMap.put(cells[cellIndex].getKey(), getCellValue(cell, cells[cellIndex]));
								}
							} catch (XSSFCellTypeException e) {
								result.addErrorRecord(new ErrorRecord(sheetIndex, row, cellIndex, cells[cellIndex], "当前列类型无法识别", "跳过行处理"));
								continue rowLoop;
							}
						}
					}
					//将前当行所对应的MAP放入List中
					sheetList.add(resultMap);
				} else {
					result.addErrorRecord(new ErrorRecord(sheetIndex, row,"导入的文件中空行数据", "跳过行处理"));
					continue;
				}
				
				//记录成功结果
				successCount++;
			}
			//将成功条数放入result中
			result.getResultTotal()[sheetIndex] = successCount;
			//将处理后的sheet的数据放入返回对象中
			result.addSheetList(sheetList);
		}
		return result;
	}

	/**
	 * @author: kevin
	 * @date: 2015年6月11日 下午1:22:06
	 * @Description: 按 options 取出列中的值 
	 * @param cell
	 * @param options
	 * @return
	 * @throws XSSFCellTypeException 
	 */
	private Object getCellValue(Cell cell, CellOptions options) throws XSSFCellTypeException {
		DecimalFormat df = (DecimalFormat) NumberFormat.getPercentInstance(); 
		switch (cell.getCellType()) {
		// 字符串
		case XSSFCell.CELL_TYPE_STRING: 
			String value = cell.getStringCellValue();
			if (StringUtils.isBlank(value)) {
				value = "";
			}
			return value;
		// 数字
		case XSSFCell.CELL_TYPE_NUMERIC: 
			double strCell = cell.getNumericCellValue();
			if (String.valueOf(strCell) == null) {
				return "";
			}

			df.applyPattern("0");
			String numberStr = df.format(strCell);
			if (Double.parseDouble(numberStr) != strCell) {
				df.applyPattern(Double.toString(strCell));
				numberStr = df.format(strCell);
			}
			return numberStr;
		//工式
		case XSSFCell.CELL_TYPE_FORMULA:
			try {
				return String.valueOf(cell.getNumericCellValue());
			} catch (IllegalStateException e) {
				return String.valueOf(cell.getRichStringCellValue());
			}
		// 空值
		case XSSFCell.CELL_TYPE_BLANK: // 空值
			return "";
		default:
			throw new XSSFCellTypeException("Cell Type error,cant read cell value.");
		}
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年6月11日 下午4:47:09
	 * @Description: 取出CELL所对应的值
	 * @param cellOptions
	 * @param clazz
	 * @param bean
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	private String getValue(CellOptions cellOptions,Object bean) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		Object returnObj;
		if (bean instanceof Map){
			returnObj = ((Map) bean).get(cellOptions.getKey());
		}else{
			Method method = bean.getClass().getMethod("get"+toFirstLetterUpperCase(cellOptions.getKey()) );
			returnObj = method.invoke(bean);
		}
		
		if(returnObj instanceof Date){
			return sdf.format(returnObj);
		}
		
		if (returnObj == null){
			return "";
		}
		
		//处理数据字典数据
		if(cellOptions.getIsBaseData().value){
			return BaseDataUtils.getNameByValue(cellOptions.getBaseDataCode(), String.valueOf(returnObj));
		}
		//处理固定数据
		if(cellOptions.getIsFixedValue().value){
			return (String) cellOptions.getFixedMap().get(String.valueOf(returnObj));
		}
		return String.valueOf(returnObj);
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年6月11日 下午4:44:23
	 * @Description: 首字母大写
	 * @param str
	 * @return
	 */
	private final String toFirstLetterUpperCase(String str) {
		if (str == null || str.length() < 2) {
			return str;
		}
		return str.substring(0, 1).toUpperCase()+ str.substring(1, str.length());
	}
}
