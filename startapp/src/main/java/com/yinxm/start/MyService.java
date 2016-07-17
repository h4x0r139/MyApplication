package com.yinxm.start;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
//    ConnectionChangeReceiver networkStateReceiver = new ConnectionChangeReceiver();
    HomeKeyDownReceiver homeKeyDownReceiver = new HomeKeyDownReceiver();
    public IntentFilter intentFilterHomeKey = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       Log.d("yika", "Not yet implemented");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        registerReceiver(networkStateReceiver, intentFilter);

//           registerReceiver(homeKeyDownReceiver, intentFilterHomeKey);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("yika", "注册广播接收器homeKeyDownReceiver");
        registerReceiver(homeKeyDownReceiver, intentFilterHomeKey);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
//        unregisterReceiver(networkStateReceiver);

        unregisterReceiver(homeKeyDownReceiver);
        super.onDestroy();

    }
}
