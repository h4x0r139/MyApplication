package cn.itcast.mobilesafe.uitl;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTransition {

	/**
	 * ��ϵͳ�ĺ���ʱ��ת���������ܿ�����ʱ��
	 */
	public static String getTime(String systemTime) {
		// ��ȡ�������� ת���������ܿ�����ʱ��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date d = new Date(Long.parseLong(systemTime));
		String time = sdf.format(d);
		return time;
	}

}
