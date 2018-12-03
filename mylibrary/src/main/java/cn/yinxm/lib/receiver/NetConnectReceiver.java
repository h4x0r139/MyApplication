package cn.yinxm.lib.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import cn.yinxm.lib.utils.log.LogUtil;


public class NetConnectReceiver extends BroadcastReceiver {
    public NetConnectReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.d("[NetConnectReceiver.onReceive] 网络状态改变");
        //初始化网络任务
//        try {
//            boolean isConnected = NetworkUtil.isNetworkConnected();
//            LogUtil.d("isConnected=" + isConnected);
//            if (isConnected) {
//                BaseApplication application = BaseApplication.getInstance();
//                LogUtil.d("work_ec 检测到网络已连接, application=" + application);
//                if (application != null) {
//                    boolean isSyncServerNetTaskSuccess = application.isSyncServerNetTaskSuccess();
//                    LogUtil.d("isSyncServerNetTaskSuccess" + isSyncServerNetTaskSuccess + ", getUserId=" + LoginUtil.getUserId());
//                    if (!isSyncServerNetTaskSuccess) {
//                        LogUtil.d("重新初始化网络连接任务");
//                        application.initOpenAppTask_NeedNet();
//                    }
//                }
//            }
//        } catch (Exception e) {
//            LogUtil.e(e);
//        }

        try {
            boolean isWifiConnected = false;
            //获得网络连接服务
            ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            // State state = connManager.getActiveNetworkInfo().getState();
            // 获取WIFI网络连接状态
            NetworkInfo.State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
            // 判断是否正在使用WIFI网络
            if (NetworkInfo.State.CONNECTED == state) {
                isWifiConnected = true;
            } else {
                isWifiConnected = false;
            }

//            Activity currentShowingActivity = AppActivityManager.getInstance().getCurrentShowingActivity();
//            if (currentShowingActivity instanceof BaseActivity) {
//                BaseActivity mBaseActivity = (BaseActivity) currentShowingActivity;
//                mBaseActivity.updateTopBarWifi(isWifiConnected);
//            }
        } catch (Exception e) {
            LogUtil.e(e);
        }
    }
}
