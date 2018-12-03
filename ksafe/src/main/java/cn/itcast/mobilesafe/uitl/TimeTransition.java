package cn.itcast.mobilesafe.uitl;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTransition {

	/**
	 * 把系统的毫秒时间转换成我们能看懂的时间
	 */
	public static String getTime(String systemTime) {
		// 获取到毫秒数 转换成我们能看懂的时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date d = new Date(Long.parseLong(systemTime));
		String time = sdf.format(d);
		return time;
	}

}
