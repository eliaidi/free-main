package com.mkfree.framework.common.utils.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 把时间格式化成字符格式,这里提供自定义格式
	 * 
	 * @param date
	 * @param pattern 自定义格式
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

	/**
	 * 获取vps时间对应中国时间,这里好傻,为了配合VPS的时间,而设计的(到时候换了服务器就要注意了)
	 * 
	 * @return
	 **/
	public static Date getVPSTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, 8);
		calendar.add(Calendar.MINUTE, 15);
		return calendar.getTime();
	}
}
