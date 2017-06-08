package com.ssd.tanghengutil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatUtil {
	
	/**
	 * 返回一个格式化的字符串日期.
	 * yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String GetStringDate(Date date ){
		
		SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date); 
	}
	/**
	 * 返回一个格式化的字符串日期.
	 * yyyyMMddHHmmss
	 * @param date
	 * @return
	 */
	public static String GetStringDateNot(Date date ){
		
		SimpleDateFormat format  = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(date); 
	}
	

}
