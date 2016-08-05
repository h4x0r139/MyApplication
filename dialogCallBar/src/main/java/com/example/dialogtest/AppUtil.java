package com.example.dialogtest;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;

/**
 * Created by yinxm on 2016/7/26.
 */
public class AppUtil {

    public static void getActivityShowing(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        LogUtil.d("pkg:"+cn.getPackageName());
        LogUtil.d("cls:"+cn.getClassName());
    }
}
