package com.yinxm.start;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class ConnectionChangeReceiver extends BroadcastReceiver {
    public ConnectionChangeReceiver() {
    }


    private static final String TAG = ConnectionChangeReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "网络状态改变");
        boolean isConnected = false;
        //获得网络连接服务
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // State state = connManager.getActiveNetworkInfo().getState();
        // 获取WIFI网络连接状态
        NetworkInfo.State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        // 判断是否正在使用WIFI网络
        if (NetworkInfo.State.CONNECTED == state) {
            isConnected = true;
        } else {
            isConnected = false;
        }
        // 获取GPRS网络连接状态
        state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        // 判断是否正在使用GPRS网络
        if (NetworkInfo.State.CONNECTED == state) {
            isConnected = isConnected || true;
        }

        if (isConnected) {
            Toast.makeText(context, "ecarx 检测到网络已连接", Toast.LENGTH_SHORT).show();
//            Intent intentActivity = new Intent(context, MainActivity.class);
//            intentActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(intentActivity);
        } else {
            Toast.makeText(context, "ecarx 检测到网络连接断开", Toast.LENGTH_SHORT).show();
        }
    }
}
