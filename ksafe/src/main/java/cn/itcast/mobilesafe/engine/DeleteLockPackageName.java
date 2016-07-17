package cn.itcast.mobilesafe.engine;

import cn.itcast.mobilesafe.db.dao.AppLockDao;
import android.content.Context;
import android.content.Intent;
/**
 * 当用户卸载程序的时候，把程序锁里面相对应锁住的包名去掉
 * @author Administrator
 *
 */
public class DeleteLockPackageName {
     private String packageName = null;
     private AppLockDao dao = null;
     private Context context = null;
	public DeleteLockPackageName(String packageName,Context context) {
		packageName = packageName;
		context = context;
	}
    public void getpackageName(String packageName){
    	dao = new AppLockDao(context);
    	if(dao.find(packageName)){
    		dao.delete(packageName);
    	}
    }
}
