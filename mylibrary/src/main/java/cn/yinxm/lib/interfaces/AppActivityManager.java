package cn.yinxm.lib.interfaces;

import android.app.Activity;
import android.view.WindowManager;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.yinxm.lib.utils.SpUtil;
import cn.yinxm.lib.utils.log.LogUtil;


/**
 * Created by yinxm on 2016/10/6.
 * 获取当前正在显示的Activity
 */
public class AppActivityManager {
    //sp 文件名
    public static final String FILE_NAME_SET_CONFIG = "set_config";
    public static final String KEY_SCREEN_ALWAYS_ON = "screen_always_on";//屏幕常亮


    private AppActivityManager() {
    }

    private WeakReference<Activity> currentActivityWeakRef;
    //所有Activity
    private List<WeakReference<Activity>> allActivityList = new ArrayList<>();
    private int count;

    public static AppActivityManager getInstance() {
        return WorkEcActivityManagerFactory.instance;
    }

    private static class WorkEcActivityManagerFactory {
        static AppActivityManager instance = new AppActivityManager();
    }

    /**
     * 获取当前正在前台显示的Activity
     *
     * @return
     */
    public Activity getCurrentShowingActivity() {
        LogUtil.d("getCurrentActivity");
        Activity currentActivity = null;
        if (currentActivityWeakRef != null) {
            currentActivity = currentActivityWeakRef.get();
        }
        LogUtil.d("getCurrentActivity currentActivity=" + currentActivity);
        return currentActivity;
    }

    /**
     * 判断app是否在前台执行
     *
     * @return
     */
    public boolean isAppRunForeground() {
//        if (getCurrentShowingActivity() != null) {
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void addCount(int num) {
        count += num;
        LogUtil.d("count=" + count);
        setKeepScreenOnOff();
    }


    public void setCurrentShowingActivity(Activity activity) {
        if (activity != null) {
            currentActivityWeakRef = new WeakReference<Activity>(activity);
        }
    }

    private void setKeepScreenOnOff() {
        boolean check = SpUtil.readBoolean(AppManager.getInstance().getApplicationContext(), FILE_NAME_SET_CONFIG, KEY_SCREEN_ALWAYS_ON, true);
        LogUtil.d("check=" + check + ", isAppRunForeground=" + isAppRunForeground());
        if (check && isAppRunForeground()) {
            Activity activity = getCurrentShowingActivity();
            LogUtil.d("activity=" + activity);
            //常亮
            if (activity != null) {
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        } else {
            //关闭常亮
            Activity activity = getCurrentShowingActivity();
            LogUtil.d("activity=" + activity);
            if (activity != null) {
                activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        }
    }

    /**
     * Activity创建时添加Activity管理
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (allActivityList != null && activity != null && !activity.isFinishing()) {
            WeakReference<Activity> reference = new WeakReference<Activity>(activity);
            allActivityList.add(reference);
        }
    }

    /**
     * Activity销毁时，移除Activity
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (allActivityList != null && activity != null) {
            Iterator<WeakReference<Activity>> iterator = allActivityList.iterator();
            while (iterator != null && iterator.hasNext()) {
                WeakReference<Activity> reference = iterator.next();
                if (reference != null && reference.get() != null && !reference.get().isFinishing()) {
                    Activity activityTemp = reference.get();
                    if (activityTemp == activity) {
                        iterator.remove();
                        break;
                    }
                } else {
                    iterator.remove();
                }
            }
        }
    }

    /**
     * 获取所有已创建的Activity
     * @return
     */
    public List<WeakReference<Activity>> getAllActivity() {
        return allActivityList;
    }
}
