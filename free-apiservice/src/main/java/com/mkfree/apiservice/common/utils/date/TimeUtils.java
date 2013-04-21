package com.mkfree.apiservice.common.utils.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 把时间格式化成字符格式,这里提供自定义格式
	 * 
	 * @param date
	 * @param pattern
	 *            自定义格式
	 * @return
	 */
	public static String tiemDateFormatToString(Date date, String pattern) {
		if (pattern != null) {
			dateFormat.applyPattern(pattern);
		}
		return dateFormat.format(date);
	}

	/**
	 * 把时间格式化成字符格式
	 * 
	 * @param date
	 * @return
	 */
	public static String tiemDateFormatToString(Date date) {
		return tiemDateFormatToString(date, null);
	}
}
