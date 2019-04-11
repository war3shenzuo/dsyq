package com.etop.management.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {
	private static final long ONE_MINUTE = 60;
	private static final long ONE_HOUR = 3600;
	private static final long ONE_DAY = 86400;
	private static final long ONE_MONTH = 2592000;
	private static final long ONE_YEAR = 31104000;

	public static Calendar calendar = Calendar.getInstance();
	
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	
	public static SimpleDateFormat datetimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 
	 * @return yyyy-mm-dd
	 *  
	 */
	public static String getDate() {
		return getYear() + "-" + getMonth() + "-" + getDay();
	}
	/**
	 * @param format
	 * @return 
	 * yyyy年MM月dd HH:mm 
	 * 
	 * 
	 */
	public static String getDate(String format) {
		SimpleDateFormat simple = new SimpleDateFormat(format);
		return simple.format(calendar.getTime());
	}

	/**
	 * 
	 * @return yyyy-MM-dd HH:mm 
	 * 
	 */
	public static String getDateAndMinute() {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return simple.format(calendar.getTime());
	}

	/**
	 * 
	 * @return
	 *  yyyy-MM-dd HH:mm:ss 
	 *  
	 */
	public static String getFullDate() {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simple.format(calendar.getTime());
	}

	/**
	 * 距离今天多久
	 * @param date
	 * @return 
	 * 
	 */
	public static String fromToday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		long time = date.getTime() / 1000;
		long now = new Date().getTime() / 1000;
		long ago = now - time;
		if (ago <= ONE_HOUR)
			return ago / ONE_MINUTE + "分钟前";
		else if (ago <= ONE_DAY)
			return ago / ONE_HOUR + "小时" + (ago % ONE_HOUR / ONE_MINUTE)
					+ "分钟前";
		else if (ago <= ONE_DAY * 2)
			return "昨天" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
					+ calendar.get(Calendar.MINUTE) + "分";
		else if (ago <= ONE_DAY * 3)
			return "前天" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
					+ calendar.get(Calendar.MINUTE) + "分";
		else if (ago <= ONE_MONTH) {
			long day = ago / ONE_DAY;
			return day + "天前" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
					+ calendar.get(Calendar.MINUTE) + "分";
		} else if (ago <= ONE_YEAR) {
			long month = ago / ONE_MONTH;
			long day = ago % ONE_MONTH / ONE_DAY;
			return month + "个月" + day + "天前"
					+ calendar.get(Calendar.HOUR_OF_DAY) + "点"
					+ calendar.get(Calendar.MINUTE) + "分";
		} else {
			long year = ago / ONE_YEAR;
			int month = calendar.get(Calendar.MONTH) + 1;// JANUARY which is 0 so month+1
			return year + "年前" + month + "月" + calendar.get(Calendar.DATE)
					+ "日";
		}

	}
	
	public static String getYear() {
		return calendar.get(Calendar.YEAR) + "";
	}

	public static String getMonth() {
		int month = calendar.get(Calendar.MONTH) + 1;
		return month + "";
	}

	public static String getDay() {
		return calendar.get(Calendar.DATE) + "";
	}

	public static String get24Hour() {
		return calendar.get(Calendar.HOUR_OF_DAY) + "";
	}

	public static String getMinute() {
		return calendar.get(Calendar.MINUTE) + "";
	}

	public static String getSecond() {
		return calendar.get(Calendar.SECOND) + "";
	}
	


	//毫秒转换成年月日
	public static String  ConversionDateToString( Long date ){

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date d = new Date(date);
		
		return sdf.format(d);
		
	}
	
	public static String formatDateTime(Date date)
	{
		return datetimeFormatter.format(date);	
	}
	
	public static String formatDate(Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		return sdf.format(date);	
	}
	
	public static double between(Date start, Date end, TimeUnit unit) {
		return Math.ceil(1.0 * (end.getTime() - start.getTime()) / unit.toMillis(1));
	}
	
	public static Date toTodayNight(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	}
	
	public static String getFormatDate() {
		return dateFormat.format(new Date());
	}
}
