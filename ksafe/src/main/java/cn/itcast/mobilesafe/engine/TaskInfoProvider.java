package cn.itcast.mobilesafe.engine;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Debug.MemoryInfo;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.mobilesafe.R;
import cn.itcast.mobilesafe.domain.TaskInfo;

public class TaskInfoProvider {
	private Context context = null;
	private PackageManager pm = null;
	private ActivityManager am = null;
	public TaskInfoProvider(Context context) {

		this.context = context;
		pm = context.getPackageManager();
		am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
	}
	public List<TaskInfo> getAllTasks( List<RunningAppProcessInfo> runingappinfos){
		List<TaskInfo> taskInfos = new ArrayList<TaskInfo>();
		
		for (RunningAppProcessInfo info : runingappinfos) {
			TaskInfo taskInfo = null;
			try {
				 taskInfo = new TaskInfo();
				int pid = info.pid;
				taskInfo.setPid(pid);
				String packname = info.processName;
				taskInfo.setPackname(packname);
				ApplicationInfo appinfo = pm.getPackageInfo(packname, 0).applicationInfo;
				Drawable appicon = appinfo.loadIcon(pm);
				taskInfo.setAppicon(appicon);
				
				if(filterApp(appinfo)){
					taskInfo.setSystemapp(false);
				}else{
					taskInfo.setSystemapp(true);
				}
				
				String appname = appinfo.loadLabel(pm).toString();
				taskInfo.setAppname(appname);
				//���ݵ�ǰ��pid����ȡ��ǰ���ڴ�ռ����Ϣ
				MemoryInfo[] memoryinfos = am.getProcessMemoryInfo(new int []{pid});
				int memorysize = memoryinfos[0].getTotalPrivateDirty();
				taskInfo.setMemorysize(memorysize);
				taskInfos.add(taskInfo);
				taskInfo = null;
			} catch (Exception e) {
				e.printStackTrace();
				taskInfo = new TaskInfo();
				String packname = info.processName;
				taskInfo.setPackname(packname);
				taskInfo.setAppname(packname);
				Drawable appicon = context.getResources().getDrawable(R.drawable.mmm);
				taskInfo.setAppicon(appicon);
				int pid = info.pid;
				taskInfo.setPid(pid);
				taskInfo.setSystemapp(true);
				MemoryInfo[] memoryinfos = am.getProcessMemoryInfo(new int[]{pid});
				int memorysize = memoryinfos[0].getTotalPrivateDirty();
				taskInfo.setMemorysize(memorysize);	
				taskInfos.add(taskInfo);
				taskInfo = null;
			}
		}
		return taskInfos;
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















