package cn.itcast.mobilesafe.engine;

import cn.itcast.mobilesafe.db.dao.AppLockDao;
import android.content.Context;
import android.content.Intent;
/**
 * ���û�ж�س����ʱ�򣬰ѳ������������Ӧ��ס�İ���ȥ��
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
