package cn.itcast.mobilesafe.service;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.mobilesafe.db.dao.AppLockDao;
import cn.itcast.mobilesafe.ui.LockScreenActivity;
/**
 * 程序锁
 * @author Administrator
 *
 */
public class WatchDogService extends Service {
	 private List<String> lockInfoList = null;
	 private AppLockDao dao = null;
	 private ActivityManager am = null;
	 private Intent intent = null;
	 private boolean flag;
	 private List<String> tempstopapps;
	 private MyBinder myBinder;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return myBinder;
	}
	public class MyBinder extends Binder implements IService{

		public void callAppProtectStart(String packname) {
			// TODO Auto-generated method stub
			appProtectStart(packname);
		}

		public void callAppProtectStop(String packname) {
			// TODO Auto-generated method stub
			appProtectStop(packname);
		}

		

		
		
	}
	
    /**
     * 重新开启对应用程序的保护
     */
	public void appProtectStart(String packname){
		if(tempstopapps.contains(packname)){
			tempstopapps.remove(packname);
		}
	}
	 /**
     *停止对应用程序的保护
     */
	public void appProtectStop(String packname){
			tempstopapps.add(packname);
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		getContentResolver().registerContentObserver(Uri.parse("content://cn.itcast.applockprovider"), true, new mObserver(new Handler()));
		
		myBinder = new MyBinder();
		intent = new Intent(this,LockScreenActivity.class);
		// 服务是不存在任务栈的 要在服务里面开启activity的话 必须添加这样一个flag
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		lockInfoList = new ArrayList<String>();
		dao = new AppLockDao(this);
		flag = true;
		//获取所有被锁的程序
		lockInfoList = dao.getAllApps();
		tempstopapps = new ArrayList<String>();
		am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		new Thread(){

			@Override
			public void run() {
				
				while(flag){
					//返回系统里面的任务栈的信息，taskinfos的集合里面只有一个元素，内容就是当前正在运行的进程对应的任务栈
					//内容就是当前正在运行的进程对应的任务栈
					List<RunningTaskInfo> taskinfos = am.getRunningTasks(1);
					//获得当前的任务信息
					RunningTaskInfo currenttask = taskinfos.get(0);
//					获得最顶端的包名,就是用户开启的任务的包名
					String packname = currenttask.topActivity.getPackageName();
					System.out.println("当前运行"+packname);
					if(lockInfoList.contains(packname)){
						if (tempstopapps.contains(packname)) {

							try {
								sleep(1000);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							continue;
						}
						System.out.println("需要锁定"+packname);
						intent.putExtra("packagename", packname);
						startActivity(intent);
					}else{
						
					}
					try {
						sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
                
			}
			
		}.start();
		
		
		
	}
    private class mObserver extends ContentObserver{

		public mObserver(Handler handler) {
			super(handler);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onChange(boolean selfChange) {
			// TODO Auto-generated method stub
			super.onChange(selfChange);
			lockInfoList = dao.getAllApps();
		}
    	
    }
}












