package com.cjhb.ssm.credit.comm.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author admin
 * 
 */
public class FormatUtil {

	/**
	 * 去掉空格
	 * 
	 * @param str
	 * @return String
	 */
	public static String trim(String str) {
		if (isEmpty(str)) {
			return "";
		}

		return str.trim();
	}

	/**
	 * 判断字符串是否为空值
	 * 
	 * @param s
	 * @return boolean
	 */
	public static boolean isEmpty(String s) {
		return s == null || s.length() == 0;
	}

	/**
	 * 字符串去空格后是否为空
	 * 
	 * @param s
	 * @param isTrim
	 *            是否需要进行Trim操作
	 * @return boolean
	 */
	public static boolean isEmpty(String s, boolean isTrim) {
		if (!isTrim) {
			return isEmpty(s);
		}
		return s == null || s.trim().length() == 0;
	}

	public static boolean isEmpty(Long l) {
		return l == null || l.longValue() == 0;
	}

	/**
	 * 判断Object 是否为null
	 * 
	 * @param o
	 * @return boolean
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object o) {
		if (o instanceof String) {
			return isEmpty((String) o);
		}
		if (o instanceof List) {
			return isEmpty((List) o);
		}
		if (o instanceof Long) {
			return isEmpty((Long) o);
		}
		return o == null;
	}

	/**
	 * 判断List是否为空值
	 * 
	 * @param list
	 * @return boolean
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(List list) {
		return (list == null || list.size() == 0);
	}

	public static boolean isEmpty(Object[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * Format date according to the pattern.For example:the pattern is yy/MM/dd
	 * ,the result is 03/09/15 Date and Time Pattern Result "yyyy.MM.dd G 'at'
	 * HH:mm:ss z" 2001.07.04 AD at 12:08:56 PDT "EEE, MMM d, ''yy" Wed, Jul 4,
	 * '01 "h:mm a" 12:08 PM "hh 'o''clock' a, zzzz" 12 o'clock PM, Pacific
	 * Daylight Time "K:mm a, z" 0:08 PM, PDT "yyyyy.MMMMM.dd GGG hh:mm aaa"
	 * 02001.July.04 AD 12:08 PM "EEE, d MMM yyyy HH:mm:ss Z" Wed, 4 Jul 2001
	 * 12:08:56 -0700 "yyMMddHHmmssZ" 010704120856-0700
	 * 
	 * @param dd
	 *            --Date
	 * @param format
	 * @return String
	 */
	public static String formatDate(Date dd, String pattern) {
		if (dd == null) {
			return "";
		}
		try {
			SimpleDateFormat simpleFormat = new SimpleDateFormat();
			simpleFormat.applyPattern(pattern);
			return simpleFormat.format(dd);
		} catch (RuntimeException e) {
			return "";
		}
	}

	/**
	 * 将字符串转化为Date
	 * 
	 * @param date
	 * @param pattern
	 * @return Date
	 */
	public static Date formatDate(String date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		if (isEmpty(date) ) {
			return null;
		}
		try {
			sdf.applyPattern(pattern);
			return sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 得到昨天的日期
	 * @param pattern 返回日期格式，如yyyyMMdd
	 */
	public static String getYesterday(String pattern) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat(pattern).format(cal
				.getTime());
		return yesterday;
	}
	
	/**
	 * 日期加减天数
	 * @param date 基准日期
	 * @param x 天数，正是加，负为减
	 * @return 加减后的日期
	 */
	public static Date addDate(Date date,int x) {
		if(date == null){
			return null;
		}
		
	    Calendar cal=Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.DAY_OF_MONTH,x);
	    return cal.getTime();
	  }

	/**
	 * 实现二纬数组过滤
	 * 
	 * @param paras
	 * @param count
	 * @return String[][]
	 */
	public static String[][] subArray(String[][] paras, int count) {
		String[][] newparas = new String[count][];
		for (int i = 0; i < count; i++) {
			newparas[i] = paras[i];
		}
		return newparas;
	}

	/**
	 * 实现数组过滤
	 * 
	 * @param paras
	 * @param count
	 *            取前count位数组
	 * @return Object[]
	 */
	public static Object[] subArray(Object[] paras, int count) {
		Object[] newparas = new Object[count];
		for (int i = 0; i < count; i++) {
			newparas[i] = paras[i];
		}
		return newparas;
	}

	/**
	 * 在数组中添加新的对象
	 * 
	 * @param array
	 * @param o
	 * @return Object[]
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object[] addArray(Object[] array, Object o) {
		List l = new ArrayList();

		if (o != null) {
			l.add(o);
		}

		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				l.add(array[i]);
			}
		}
		return l.toArray();
	}

	/**
	 * 字符串数组转型为 Long 数组
	 * 
	 * @param array
	 * @return Long[]
	 */
	public static Long[] toLongArray(String[] array) {
		if (array == null) {
			return null;
		}

		Long[] tmp = new Long[array.length];
		for (int i = 0; i < array.length; i++) {
			tmp[i] = new Long(array[i]);
		}

		return tmp;
	}

	/**
	 * 判断一个值是否在数组里存在
	 * 
	 * @param key
	 * @param array
	 * @return boolean
	 */
	public static boolean isExist(String key, String[] array) {
		if (array == null || array.length == 0) {
			return false;
		}
		if (isEmpty(key)) {
			return false;
		}
		for (int i = 0; i < array.length; i++) {
			if (key.equals(array[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断字符串里是否包含另一个字符串
	 * 
	 * @param key
	 * @param values
	 * @return boolean
	 */
	public static boolean isExist(String key, String values) {
		boolean isRight = false;

		if (FormatUtil.isEmpty(values)) {
			return isRight;
		}

		StringTokenizer st = new StringTokenizer(values, "|");
		while (st.hasMoreElements()) {
			String dep = st.nextToken();
			if (key.trim().equals(dep.trim())) {
				return true;
			}
		}
		return isRight;
	}

	/**
	 * 对文件名进行重新编码，当文件已经存在时，自动加上随机数。
	 * 
	 * @param fileName
	 * @return String
	 */
	public static String reEncodeFileName(String fileName) {
		String tmp = fileName;
		File file = new File(tmp);

		String ext = fileName.substring(fileName.lastIndexOf('.')); // with dot
		// .
		String name = fileName.substring(0, fileName.lastIndexOf('.'));

		while (file.exists()) {
			tmp = name + ((int) (Math.random() * 10000)) + ext;
			file = new File(tmp);
		}

		return tmp;
	}

	/**
	 * @param fileName
	 * @return
	 */
	public static String getFileName(String fileName) {
		String newName = fileName.substring(fileName
				.lastIndexOf(File.separatorChar) + 1);
		return newName;
	}

	/**
	 * Set to List
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList(Set set) {
		List ret = new ArrayList();

		for (Iterator it = set.iterator(); it.hasNext();) {
			ret.add(it.next());
		}
		return ret;
	}

	/**
	 * 过滤字符中的HTML标签
	 * 
	 * @param element
	 * @return
	 */
	public static String getTxtWithoutHTML(String element) {
		// String reg="<[^<|^>]+>";
		// return element.replaceAll(reg,"");

		if (null == element || "".equals(element.trim())) {
			return element;
		}

		Pattern pattern = Pattern.compile("&[a-z]{1,10}+;|<[^<|^>]*>");

		Matcher matcher = pattern.matcher(element);
		StringBuffer txt = new StringBuffer();

		// 替换字符中HTML标签和特殊符号

		while (matcher.find()) {
			String group = matcher.group();

			if (group.matches("<[\\s]*>")) {
				matcher.appendReplacement(txt, group);
			} else if (group.matches("&nbsp;")) {
				matcher.appendReplacement(txt, " ");
			} else if (group.matches("&amp;")) {
				matcher.appendReplacement(txt, "&");
			} else if (group.matches("&lt;")) {
				matcher.appendReplacement(txt, "<");
			} else if (group.matches("&gt;")) {
				matcher.appendReplacement(txt, ">");
			} else if (group.matches("&quot;")) {
				matcher.appendReplacement(txt, "\"");
			} else if (group.matches("&apos;")) {
				matcher.appendReplacement(txt, "\'");
			} else {
				matcher.appendReplacement(txt, "");
			}

		}

		// 加裁最后字符

		matcher.appendTail(txt);
		/*
		 * txt.repaceEntities(txt,"&","&"); repaceEntities(txt,"<","<");
		 * repaceEntities(txt,">",">"); repaceEntities(txt,""","\"");
		 * repaceEntities(txt," ","");
		 */

		return txt.toString();

	}

	/**
	 * 过滤字符中的HTML标签
	 * 
	 * @param element
	 * @return
	 */
	public static String getTxtWithoutHTML(String element, int length) {
		// String reg="<[^<|^>]+>";
		// return element.replaceAll(reg,"");

		if (null == element || "".equals(element.trim())) {
			return element;
		}

		Pattern pattern = Pattern
				.compile("&[a-z]{1,10}+;|<[^<|^>|[^\\x00-\\xff]]*>");

		Matcher matcher = pattern.matcher(element);
		StringBuffer txt = new StringBuffer();

		// 替换字符中HTML标签和特殊符号

		while (matcher.find() && length > txt.length()) {
			String group = matcher.group();
			if (group.matches("&nbsp;")) {
				matcher.appendReplacement(txt, " ");
			} else if (group.matches("&amp;")) {
				matcher.appendReplacement(txt, "&");
			} else if (group.matches("&lt;")) {
				matcher.appendReplacement(txt, "<");
			} else if (group.matches("&gt;")) {
				matcher.appendReplacement(txt, ">");
			} else if (group.matches("&quot;")) {
				matcher.appendReplacement(txt, "\"");
			} else if (group.matches("&apos;")) {
				matcher.appendReplacement(txt, "\'");
			} else {
				matcher.appendReplacement(txt, "");
			}

		}

		// 加裁最后字符

		if (length > txt.length()) {
			matcher.appendTail(txt);
		}
		/*
		 * txt.repaceEntities(txt,"&","&"); repaceEntities(txt,"<","<");
		 * repaceEntities(txt,">",">"); repaceEntities(txt,""","\"");
		 * repaceEntities(txt," ","");
		 */
		return txt.toString().substring(0,
				txt.length() > length ? length : txt.length());
	}

	/**
	 * 
	 * @param context
	 * @param path
	 * @param fileMap
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public static String filterIMG(String context, String path, Map fileMap) {
		Pattern pattern = Pattern.compile("<IMG style[^<|^>|[^\\x00-\\xff]]*>");
		String imgString = "";
		Matcher matcher = pattern.matcher(context);
		StringBuffer txt = new StringBuffer();
		String temp = null;
		String group = null;
		while (matcher.find()) {
			group = matcher.group();
			temp = group;
			Set set = fileMap.keySet();
			for (Iterator it = set.iterator(); it.hasNext();) {
				String oldName = (String) it.next();
				if (group.indexOf(oldName + "');") != -1) {
					imgString = "src='" + path + fileMap.get(oldName) + "\'";
					temp = group.replaceFirst(
							"FILTER:[^<|^>|[^\\x00-\\xff]]*'\\);", "");
					temp = temp.replaceFirst(
							"src=\"[^<|^>|^']*editor/none.gif\"", imgString);
					// System.out.println(temp);

				}

			}
			matcher.appendReplacement(txt, temp);
		}
		matcher.appendTail(txt);

		return txt.toString();
	}

	/**
	 * 验证输入数据的长度
	 * 
	 * @param str
	 *            要验证的字符串
	 * @param len
	 *            规定的长度(数据库中的长度)
	 * @return boolean 超过长度返回true,否则返回false
	 */
	public static boolean checkTrueLength(String str, int len) {
		int length = 0;
		if (str != null) {
			try {
				length = str.getBytes("UTF-8").length;
			} catch (UnsupportedEncodingException uee) {
			}
		}
		if (len < length) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 得到真实的长度
	 * 
	 * @param str
	 * @return int
	 */
	public static int getTrueLength(String str) {
		int length = 0;
		if (str != null) {
			try {
				length = str.getBytes("GBK").length;
			} catch (UnsupportedEncodingException uee) {
			}
		}
		return length;
	}

	/**
	 * 表名只允许字母数字和下划线不能以数字开头
	 * 
	 * @param tableName
	 * @return boolean
	 */
	public static boolean checkTableName(String tableName) {
		String regex = "[a-zA-z_]+[0-9a-zA-z_]*";
		return tableName.matches(regex);
	}


	public static String getMethodName(String method) {
		String[] methodArray = (String[]) method.split("[(]");
		methodArray = methodArray[0].split(" ");
		method = methodArray[methodArray.length - 1];
		return method;
	}
	
	public static String getBaseFilePath(){
		String classPath = "";
		try {
			classPath = URLDecoder.decode(
					FormatUtil.class.getClassLoader()
							.getResource(
									FormatUtil.class.getName().replaceAll("[.]", "/")
											+ ".class").getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		//截掉盘符前面的"/"
//		if (classPath.startsWith("/")){
//			classPath = classPath.substring(1);
//		}
		//获取WEB-INF前面的路径
		if (classPath.indexOf("WEB-INF")>0){
		String[] temp = classPath.split("WEB-INF");
		//拼接完整路径
		return temp[0];
		}
		else{
			String[] temp = classPath.split("build");
			//拼接完整路径
			return temp[0]+"WebContent/";
		}
		
	}
	
	/**
	 * 格式化Where条件，将特殊字符转义
	 */
	public static String formatWhere(String where){
		String s = where.replace("'", "\\'");
		s = s.replace("%", "\\%");
		s = s.replace("\"", "\\\"");
		return s;
	}
	
	/**
	 * 数组去重
	 */
	public static String[] removeDuplicates(String[] array){
		if(FormatUtil.isEmpty(array)){
			return array;
		}
		
		LinkedHashSet<String> set = new LinkedHashSet<String>();
		for(String s : array){
			set.add(s);
		}
		String[] newArray = new String[set.size()];
		for(int i =0 ;i<set.size();i++){
			newArray[i] = set.toArray()[i].toString();
		}
		return newArray;
	}
	
	/**
	 * HTTP请求Get方法
	 */
	public static String sendGet(String url)
	 {
	  String result = "";
	  try{
	   String urlName = url;//

	   URL U = new URL(urlName);
	   URLConnection connection = U.openConnection();
	   connection.setRequestProperty("User-Agent","Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.0; Trident/5.0)");
	   connection.connect();
	   BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
	   String line;
	   while ((line = in.readLine())!= null)
	   {
	    result += line;
	   }
	   in.close();   
	  }catch(Exception e){
	   System.out.println("没有结果！"+e);
	  }
	  return result;
	 }
	
	 /**
	 * HTTP请求Post方法
	 */
	public static String sendPost(String url,String param)
	 {
	  String result="";
	  try{
	   URL httpurl = new URL(url);
	   HttpURLConnection httpConn = (HttpURLConnection)httpurl.openConnection();       
	   httpConn.setDoOutput(true);
	   httpConn.setDoInput(true);
	   PrintWriter out = new PrintWriter(httpConn.getOutputStream());
	   out.print(param);
	   out.flush();
	   out.close();
	   BufferedReader in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
	   String line;
	   while ((line = in.readLine())!= null)
	   {
	    result += line; 
	   }
	   in.close();
	  }catch(Exception e){
	   System.out.println("没有结果！"+e);
	  }
	  return result;
	 }
	
    /**
     * 过滤特殊字符  
     */
	public static String stringFilter(String str) {
		// 只允许字母、数字和空格
		 String regEx = "[^a-zA-Z0-9 ]";
		
		if(isEmpty(str)){
			return "";
		}
		// 清除掉所有特殊字符
//		String regEx = "[`~!@#$%^&*()+\\-=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}
	

	/**
	 * 生成随机字符串
	 * @param minLen 最小长度
	 * @param maxLen 最大长度
	 * @return 随机字符串
	 */
	public static String getRandomString(int minLen, int maxLen) {
	    String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";   
	    Random random = new Random();   
	    int length = random.nextInt(maxLen)%(maxLen-minLen+1) + minLen;
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 }  


	public static void main(String[] org) {
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.get(Calendar.YEAR));
		System.out.println(cal.get(Calendar.MONTH));
		System.out.println(cal.get(Calendar.DAY_OF_MONTH));
		System.out.println(FormatUtil.formatDate(FormatUtil.addDate(new Date(), -10), "yyyy-MM-dd'T'HH:mm:ssZ"));
		System.out.println(FormatUtil.formatDate("2011-05-16T23:59:59", "yyyy-MM-dd'T'HH:mm:ss"));
		
		System.out.println(FormatUtil.stringFilter("2011-05-16T23:59:59 yyyy-MM-dd'T'HH:mm:ss"));
		
	}

}
