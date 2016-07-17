package cn.itcast.mobilesafe.service;

import cn.itcast.mobilesafe.R;
import cn.itcast.mobilesafe.db.dao.AppLockDao;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;

public class DeleteLockPackageNameService extends Service {
	private AppLockDao dao = null;
	private SQLiteDatabase db = null;
	private PackageManager pm = null;
	private Intent intent = null;
	private ApplicationInfo appinfo = null;
	private ApplicationInfo info  =  null;
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

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		try {
			dao = new AppLockDao(this);
			String packageName = intent.getExtras().getString("packageName");
			
			System.out.println("DeleteLockPackageNameService=="+packageName);
		//	PackageInfo packinfo = pm.getPackageInfo(packageName, 0);
			if(packageName.equals("cn.itcast.mobilesafe")){
				
			}else{
				pm = getPackageManager();
				
//				appinfo = pm.getPackageInfo(packageName, 0).applicationInfo;
//				String appName = appinfo.loadLabel(pm).toString();
				
				
				if(dao.find(packageName)){
					dao.delete(packageName);
				}
				NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				Notification notification = new Notification(R.drawable.main_icon_notification, "已经成功的卸载  : "+packageName, System.currentTimeMillis());
				notification.flags = Notification.FLAG_AUTO_CANCEL;
				//收到notification播放音乐
	    		notification.sound = Uri.parse(Environment.getExternalStorageDirectory()+"/"+"alarm.wav");
				Intent mintent = new Intent();
	    		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
	    		notification.setLatestEventInfo(this, "卸载成功", "卸载成功", pendingIntent);
	    		manager.notify(0, notification);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
