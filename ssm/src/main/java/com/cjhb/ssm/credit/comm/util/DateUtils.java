package com.cjhb.ssm.credit.comm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
* @ClassName: DateUtils
* 
* @author xuy@rayootech.com
* @date 2015年6月6日 上午9:57:54
*
 */
public class DateUtils {


	/**
	 * 默认 yyyy-MM-dd
	 * @author: yangxu
	 * @date: 2015年6月6日 上午9:57:49
	 * @return Date
	 * @throws
	 */
    public static Date parseFromStr(String src, String patten) {
        if(patten == null){
            patten = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(patten);
        try {
            return sdf.parse(src);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 格式化当前时间
     * @param patten 默认 yyyy-MM-dd
     * @return
     */
    public static String getCurrentDate(String patten) {
        if(patten == null){
            patten = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(patten);
        return sdf.format(new Date());
    }
    
    public static Date addDate(Date date, int num) {
        return new Date(date.getTime() + num * (long) 24 * 3600 * 1000);
    }

    public static Date minusDate(Date date, int num) {
        return new Date(date.getTime() - num * (long) 24 * 3600 * 1000);
    }

    public static void main(String[] args) {


        String strDate = "2008-10-19";
        
        System.out.println(parseFromStr("201305", null).toLocaleString());
        
        
        //定义模板从字符串中提取数字
        String path1 = "yyyy-MM-dd";
        //定义模板将取出来的日期转换成指定格式
        String path2 = "yyyy年MM月dd日  HH时mm分ss秒SS毫秒";
        SimpleDateFormat sdf1 = new SimpleDateFormat(path1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(path2);
        Date d = null;
        try {
            d = sdf1.parse(strDate);//从给定的字符串中提取出来日期

            System.out.println(d.toLocaleString());

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        System.out.println(sdf2.format(d));//将日期变为新的格式

        
        Calendar current = Calendar.getInstance();
        
        
//        current.setTime(date)
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(current.getTime()));
        System.out.println(current.get(Calendar.DAY_OF_MONTH));
        current.add(Calendar.MONTH, -1);
//		current.set(Calendar.DAY_OF_MONTH, 1);
		   
        System.out.println(sdf.format(current.getTime()));
        
        
        
        
    }

}
