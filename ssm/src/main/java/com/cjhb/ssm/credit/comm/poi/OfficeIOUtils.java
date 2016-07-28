/**
* 
*
*@author：
*@since： JDK1.6
*@version：1.0
*@date：2015年6月10日 下午5:57:20
*/ 

package com.cjhb.ssm.credit.comm.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ClassName: OfficeIOUtils
 * @Description: OFFICE导入导出工具类
 * @author 
 * @date 2015年6月10日 下午5:57:20
 *
 */
public class OfficeIOUtils {

	private final static OfficeIOFactory ioFactory = new OfficeIOFactory();
	
	/**
	 * @author: kevin
	 * @date: 2015年6月11日 下午5:11:22
	 * @Description: 导出
	 * @param sheets
	 * @return
	 */
	public static OfficeIOResult exportXlsx(SheetOptions[] sheets){
		return ioFactory.exportXlsx(sheets);
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年6月12日 上午11:38:40
	 * @Description: 导出模板
	 * @param sheets
	 * @return
	 */
	public static OfficeIOResult exportXlsxTemplet(SheetOptions[] sheets){
		return ioFactory.exportXlsxTemplet(sheets);
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年6月11日 下午5:11:41
	 * @Description: 自定义facotry导出
	 * @param ioFactory
	 * @param sheets
	 * @return
	 */
	public static OfficeIOResult exportXlsx(OfficeIOFactory ioFactory,SheetOptions[] sheets){
		return ioFactory.exportXlsx(sheets);
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年6月11日 上午10:29:30
	 * @Description: 导入
	 * @param file
	 * @param sheets
	 * @return
	 * @throws IOException 
	 */
	public static OfficeIOResult ImportXlsx(File file,SheetOptions[] sheets){
		return ioFactory.importXlsx(file, sheets);
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年6月11日 上午10:29:38
	 * @Description: 自定义facotry导入
	 * @param ioFactory
	 * @param file
	 * @param sheets
	 * @return
	 * @throws IOException 
	 */
	public static OfficeIOResult ImportXlsx(OfficeIOFactory ioFactory,File file,SheetOptions[] sheets){
		return ioFactory.importXlsx(file, sheets);
	}
	
	public FileOutputStream Export(){
		return null;
	}
}
