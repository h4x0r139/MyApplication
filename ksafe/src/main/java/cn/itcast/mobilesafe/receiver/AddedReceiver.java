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
 * ��װ��ж��Ӧ�ó��򷢳��Ĺ㲥
 * @author Administrator
 *
 */
public class AddedReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
        //���չ㲥���豸���°�װ��һ��Ӧ�ó�������Զ������°�װӦ�ó���       
        //���չ㲥��ϵͳ������ɺ����г���       
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {       
             Intent newIntent = new Intent(context, MainActivity.class);       
             newIntent.setAction("android.intent.action.MAIN");          
             newIntent.addCategory("android.intent.category.LAUNCHER");        
             newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);          
             context.startActivity(newIntent);       
        }       
        //���չ㲥���豸���°�װ��һ��Ӧ�ó�������Զ������°�װӦ�ó���       
        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {   
        	//Ĭ�ϻ�ȡ�İ���ǰ�����ǰ׺,������Ҫ��ȡ��ǰ���package��
            String packageName = intent.getDataString().substring(8);       
            System.out.println("---------------");       
            Intent newIntent = new Intent(context,KillPoisonService.class);
            newIntent.putExtra("packageName", packageName);
            context.startService(newIntent);
            //��һ�������ǰ������ڶ�������������
//            newIntent.setClassName(packageName.class,"xx");       
//            newIntent.setAction("android.intent.action.MAIN");                
//            newIntent.addCategory("android.intent.category.LAUNCHER");                
//            newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);       
//            context.startService(newIntent);       
        }       
        //���չ㲥���豸��ɾ����һ��Ӧ�ó������       
        if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {   
        	//Ĭ�ϻ�ȡ�İ���ǰ�����ǰ׺,������Ҫ��ȡ��ǰ���package��
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
