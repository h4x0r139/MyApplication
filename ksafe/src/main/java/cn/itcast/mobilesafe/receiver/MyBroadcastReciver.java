package cn.itcast.mobilesafe.receiver;

import cn.itcast.mobilesafe.ui.T4_DemoActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBroadcastReciver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Intent intent2 = new Intent(context, T4_DemoActivity.class);
		intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent2);
	}

}
