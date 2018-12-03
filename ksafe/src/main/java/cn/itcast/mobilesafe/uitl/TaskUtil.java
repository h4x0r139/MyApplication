package cn.itcast.mobilesafe.uitl;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;

public class TaskUtil {
	/**
	 * ɱ�����еĽ�����Ϣ
	 * @param context
	 */
	public static void killAllProcess(Context context) {
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> runningapps = am.getRunningAppProcesses();
		for(RunningAppProcessInfo info : runningapps){
			String packname = info.processName;
			am.killBackgroundProcesses(packname);
		}
	}
}
















