package com.mkfree.framework.common.utils.date;

import java.util.Calendar;
import java.util.Date;

/**
 * 获取中国的VPS时间...由于有时间差,暂时VPS在美国(到时候,换的话,再改吧..傻吧)
 * 
 * @author hk
 * 
 *         2013-2-2 下午5:33:48
 */
public class VpsTimeUtil {
	/**
	 * 获取vps时间对应中国时间,这里好傻,为了配合VPS的时间,而设计的(到时候换了服务器就要注意了)
	 * 
	 * @return
	 */
	public static Date getVPSTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, 9);
		calendar.add(Calendar.MINUTE, 11);
		return calendar.getTime();
	}
}
