package com.yinxm.start;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class HomeKeyDownReceiver extends BroadcastReceiver {
    static final String SYSTEM_REASON = "reason";
    static final String SYSTEM_HOME_KEY = "homekey";//home key
    static final String SYSTEM_RECENT_APPS = "recentapps";//long home key

    private  long firstTime = 0;

    public HomeKeyDownReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.i(TAG, "[HomeKeyDownReceiver.onReceive] action="+action);
        if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
            String reason = intent.getStringExtra(SYSTEM_REASON);
            Log.i(TAG, "reason="+reason);
            if (reason != null) {
                if (reason.equals(SYSTEM_HOME_KEY)) {
                    // home key处理点
                    long currentTime = System.currentTimeMillis();
                    Log.d(TAG, "firstTime="+firstTime+", currentTime="+currentTime);
                    if (currentTime - firstTime > 2000) {//连续按键时间小于2s才有效
                        Toast.makeText(context,"再按一次启动workEc", Toast.LENGTH_SHORT).show();
                        firstTime = currentTime;
                    } else {
                        Log.d(TAG, "启动workEc");
                        Toast.makeText(context,"workEc启动中...", Toast.LENGTH_SHORT).show();
//                        Intent intentActivity = new Intent(context, MainActivity.class);//每次都新创建一个activity
//                        intentActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intentActivity);
//                        Intent intentActivity = new Intent(context, MainActivity.class);
//                        intentActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//会销毁以前的activity
//                        intentActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        context.startActivity(intentActivity);
                        //重新启动activity
                        Intent intentActivity = new Intent(context, MainActivity.class);
                        intentActivity.addCategory(Intent.CATEGORY_LAUNCHER);
                        intentActivity.setAction(Intent.ACTION_MAIN);
                        intentActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                        context.startActivity(intentActivity);
                    }

                } else if (reason.equals(SYSTEM_RECENT_APPS)) {
                    // long home key处理点
                }
            }
        }
    }
}
