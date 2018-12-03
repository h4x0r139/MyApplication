package cn.itcast.mobilesafe.service;

import java.util.List;

import cn.itcast.mobilesafe.R;
import cn.itcast.mobilesafe.uitl.MD5Encoder;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.os.Message;
/**
 * 当应用程序安装上来检测是否有病毒
 * @author Administrator
 *
 */
public class KillPoisonService extends Service {
	private SQLiteDatabase db = null;
	private PackageManager pm = null;
	private Intent intent = null;
	private ApplicationInfo appinfo = null;
	private ApplicationInfo info  =  null;
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		try {
			List<PackageInfo> infos = getPackageManager().getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES
					| PackageManager.GET_SIGNATURES);
			db = SQLiteDatabase.openDatabase("/sdcard/antivirus.db", null, SQLiteDatabase.OPEN_READONLY);
			String packageName = intent.getExtras().getString("packageName");
			System.out.println("KillPoisonService=="+packageName);
			pm = getPackageManager();
			NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			for(PackageInfo info :infos){
				String packname = info.packageName;
				if(packageName.equals(packname)){
					
					ApplicationInfo appinfo = pm.getPackageInfo(packname, 0).applicationInfo;
					String appName = appinfo.loadLabel(pm).toString();
					
					Signature [] signs = info.signatures;
					String str = signs[0].toCharsString();
					String md5 = MD5Encoder.encode(str);
					Cursor cursor = db.rawQuery("select desc from datable where md5=?",new String[] { md5 });
					
					if(cursor.moveToNext()){
						String desc = cursor.getString(0);
						Notification notification = new Notification(R.drawable.main_icon_notification, "检测"+appName+"有病毒,请卸载", System.currentTimeMillis());
						notification.flags = Notification.FLAG_AUTO_CANCEL;
						Intent mintent = new Intent();
			    		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
			    		notification.setLatestEventInfo(this, "检测"+appName+"有病毒,请卸载", "检测"+appName+"有病毒,请卸载", pendingIntent);
					    manager.notify(0, notification);
						cursor.close();
						db.close();
					}else{
						Notification notification = new Notification(R.drawable.main_icon_notification, "检测"+appName+"没有病毒 扫描结果安全", System.currentTimeMillis());
						notification.flags = Notification.FLAG_AUTO_CANCEL;
						Intent mintent = new Intent();
			    		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
			    		notification.setLatestEventInfo(this, "检测"+appName+"没有病毒", "检测"+appName+"没有病毒 扫描结果安全", pendingIntent);
					    manager.notify(0, notification);
					}
				}
			
			}
		
			//根据包名获取包的信息
//			PackageInfo info = pm.getPackageInfo(packageName, 0);
//			appinfo = pm.getPackageInfo(packageName, 0).applicationInfo;
//			Signature [] signs = info.signatures;
//			String str = signs[0].toCharsString();
//			String md5 = MD5Encoder.encode(str);
//			Cursor cursor = db.rawQuery(
//					"select desc from datable where md5=?",
//					new String[] { md5 });
//			String appName = appinfo.loadLabel(pm).toString();
			
			
			
			
			 
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
}














