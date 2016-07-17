package cn.itcast.mobilesafe.receiver;

import cn.itcast.mobilesafe.uitl.TaskUtil;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
/**
 * �����㲥����
 * @author Administrator
 *
 */
public class LockScreenReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		boolean killprocess = sp.getBoolean("killprocess", false);
		if(killprocess){
			TaskUtil.killAllProcess(context);
			System.out.println("ɱ�����еĽ���");
		}
	}

}
