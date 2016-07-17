package cn.itcast.mobilesafe.receiver;

import cn.itcast.mobilesafe.engine.DeleteLockPackageName;
import cn.itcast.mobilesafe.service.DeleteLockPackageNameService;
import cn.itcast.mobilesafe.service.KillPoisonService;
import cn.itcast.mobilesafe.ui.MainActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
/**
 * 安装和卸载应用程序发出的广播
 * @author Administrator
 *
 */
public class AddedReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
        //接收广播：设备上新安装了一个应用程序包后自动启动新安装应用程序。       
        //接收广播：系统启动完成后运行程序       
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {       
             Intent newIntent = new Intent(context, MainActivity.class);       
             newIntent.setAction("android.intent.action.MAIN");          
             newIntent.addCategory("android.intent.category.LAUNCHER");        
             newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);          
             context.startActivity(newIntent);       
        }       
        //接收广播：设备上新安装了一个应用程序包后自动启动新安装应用程序。       
        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {   
        	//默认获取的包名前面带有前缀,所以需要截取掉前面的package：
            String packageName = intent.getDataString().substring(8);       
            System.out.println("---------------");       
            Intent newIntent = new Intent(context,KillPoisonService.class);
            newIntent.putExtra("packageName", packageName);
            context.startService(newIntent);
            //第一个参数是包名，第二个参数是类名
//            newIntent.setClassName(packageName.class,"xx");       
//            newIntent.setAction("android.intent.action.MAIN");                
//            newIntent.addCategory("android.intent.category.LAUNCHER");                
//            newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);       
//            context.startService(newIntent);       
        }       
        //接收广播：设备上删除了一个应用程序包。       
        if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {   
        	//默认获取的包名前面带有前缀,所以需要截取掉前面的package：
            String packageName = intent.getDataString().substring(8);
            System.out.println("=================");
            Intent newIntent = new Intent(context,DeleteLockPackageNameService.class);
            newIntent.putExtra("packageName", packageName);
            context.startService(newIntent);
//            DeleteLockPackageName del = new DeleteLockPackageName(packageName,context);
//            del.getpackageName(packageName);
        //    DatabaseHelper dbhelper = new DatabaseHelper();       
        //    dbhelper.executeSql("delete from users");       
        }       

	}

}
