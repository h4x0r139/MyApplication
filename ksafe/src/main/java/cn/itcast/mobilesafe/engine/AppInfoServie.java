package cn.itcast.mobilesafe.engine;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.mobilesafe.domain.AppInfo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

/**
 * ��ȡ����Ӧ�ó���
 * @author Administrator
 *
 */

public class AppInfoServie {
	private static final String TAG = "AppInfoProvider";
	private Context context;
	private PackageManager packmanager;
	
	
	
	public AppInfoServie(Context context) {
		this.context = context;
		packmanager = context.getPackageManager();
	}

	/**
	 * ���ص�ǰ�ֻ����氲װ�����еĳ�����Ϣ�ļ���
	 * @return Ӧ�ó���ļ���
	 */
	public List<AppInfo> getAllApps(){
		List<AppInfo> appinfos = new ArrayList<AppInfo>();
		List<PackageInfo> packinfos = packmanager.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
		for(PackageInfo info :packinfos){
			AppInfo myApp = new AppInfo();
			String packname = info.packageName;
			myApp.setPackname(packname);
			ApplicationInfo appinfo = info.applicationInfo;
			Drawable icon = appinfo.loadIcon(packmanager);
			myApp.setIcon(icon);
			String appname = appinfo.loadLabel(packmanager).toString();
			myApp.setAppname(appname);
			 if(filterApp(appinfo)){
				 myApp.setSystemApp(false);
			 }else{
				 myApp.setSystemApp(true);
			 }
			appinfos.add(myApp);
		}
		return appinfos;
	}
	
	
	/**
	 * �ж�ĳ��Ӧ�ó����� ����������Ӧ�ó���
	 * @param info
	 * @return 
	 */
    public boolean filterApp(ApplicationInfo info) {
        if ((info.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
            return true;
        } else if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
            return true;
        }
        return false;
    }
}
