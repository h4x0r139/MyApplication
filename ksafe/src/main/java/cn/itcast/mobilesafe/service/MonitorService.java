package cn.itcast.mobilesafe.service;

import cn.itcast.mobilesafe.R;
import cn.itcast.mobilesafe.ui.SplashActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

public class MonitorService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
/**
 * ÿ�ν��붼�������ķ���
 */
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		new Thread(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			    Notification notification = new Notification();
			    //���ᱻ���
			    notification.flags = Notification.FLAG_NO_CLEAR;
			    notification.icon = R.drawable.main_icon_notification;
			    notification.tickerText = "��ɽ�ֻ���ʿ���ڱ��������ֻ�";
			  //Զ�̵�view  ���ǵ�view���� ��Ҫ��ʾ������һ���������� ����һ���������� ���Ծ���Ҫһ��remote  view
			    RemoteViews rv = new RemoteViews(getPackageName(), R.layout.monitor_app);
			    rv.setTextViewText(R.id.textView1, "��ɽ�ֻ���ʿ���ڱ��������ֻ�");
			    rv.setProgressBar(R.id.progressBar1, 100, 0, false);
				notification.contentView = rv;
				Intent intent = new Intent(MonitorService.this,SplashActivity.class);
				PendingIntent  pendingIntent = PendingIntent.getActivity(MonitorService.this, 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
				notification.contentIntent = pendingIntent;
				
				manager.notify(2, notification);
			}
			
		}.start();
	}

}























