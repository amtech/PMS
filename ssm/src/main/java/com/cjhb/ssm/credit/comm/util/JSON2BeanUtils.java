/**
* 
*
*@author：
*@since： JDK1.6
*@version：1.0
*@date：2015年6月6日 下午12:37:05
*/ 

package com.cjhb.ssm.credit.comm.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @ClassName: JSONUtils
 * @Description: JSONUtils
 * @author 
 * @date 2015年6月6日 下午12:37:05
 *
 */
public class JSON2BeanUtils {

	public static <T> List<T> json2List(String jsonStr,Class<T> beanClass){
		return json2List(jsonStr,beanClass,new String[]{"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd"});
	}
	
	public static <T> List<T> json2List(String jsonStr,Class<T> beanClass,String[] dateFormats){
		JSONArray array = JSONArray.fromObject(jsonStr);
		Object[] objects = array.toArray();
		List<T> list = new ArrayList<T>();
		for (Object object : objects) {
			T bean = json2Bean(object,beanClass,dateFormats);
			list.add(bean);
		}
		return list;
	}
	
	public static <T> T json2Bean(Object obj, Class<T> beanClass) {
		return json2Bean(obj, beanClass,new String[]{"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd"});
	 }
	
	public static <T> T json2Bean(Object obj, Class<T> beanClass,String[] dateFormats) {
		JSONObject jsonObject = JSONObject.fromObject(obj);
		T bean = (T) JSONObject.toBean(jsonObject, beanClass);
		Field[] fields = beanClass.getDeclaredFields();
		for (Field field : fields) {
			if (field.getType() == Date.class){
				if (jsonObject.get(field.getName()) != null){
					try {
						Method method = beanClass.getMethod("set"+toFirstLetterUpperCase(field.getName()) , new Class[]{Date.class});
						method.invoke(bean, str2Date((String) jsonObject.get(field.getName()),dateFormats));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}else if(field.getType() == Timestamp.class){
				if (jsonObject.get(field.getName()) != null){
					try {
						Method method = beanClass.getMethod("set"+toFirstLetterUpperCase(field.getName()) , new Class[]{Timestamp.class});
						method.invoke(bean, str2Timestamp((String) jsonObject.get(field.getName())));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return bean;         
	 }
	
	private static Date str2Date(String str,String[] dateFormats){
		for (String formatStr : dateFormats) {
			SimpleDateFormat sdf = new SimpleDateFormat(formatStr) ;
			try {
				return sdf.parse(str);
			} catch (ParseException e) {
				continue;
			}
		}
		return null;
	}
	
	private static Timestamp str2Timestamp(String str){
		return Timestamp.valueOf(str); 
	}
	
	private static String toFirstLetterUpperCase(String str) {
		if (str == null || str.length() < 2) {
			return str;
		}
		return str.substring(0, 1).toUpperCase()+ str.substring(1, str.length());
	}
}
