package cn.itcast.mobilesafe;

import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;

import cn.itcast.mobilesafe.domain.TaskInfo;
import cn.itcast.mobilesafe.receiver.LockScreenReceiver;
//��Դ����������� http://javaapk.com
public class MyApplication extends Application {
	public TaskInfo taskinfo;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		LockScreenReceiver receiver = new LockScreenReceiver();
		IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
		filter.setPriority(1000);
		registerReceiver(receiver, filter);
	}

}
