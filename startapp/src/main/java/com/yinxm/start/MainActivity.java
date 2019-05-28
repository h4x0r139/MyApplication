package com.yinxm.start;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
//    ConnectionChangeReceiver networkStateReceiver = new ConnectionChangeReceiver();
    HomeKeyDownReceiver homeKeyDownReceiver = new HomeKeyDownReceiver();
    public IntentFilter intentFilterHomeKey = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "[MainActivity.onCreate]");
        setContentView(R.layout.activity_main);

        //注册网络监听
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
//        registerReceiver(networkStateReceiver, filter);
//        registerReceiver(homeKeyDownReceiver, intentFilterHomeKey);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "[MainActivity.onStart]");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "[MainActivity.onResume]");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "[MainActivity.onPause]");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "[MainActivity.onStop]");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "[MainActivity.onRestart]");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "[MainActivity.onDestroy]");

//        unregisterReceiver(networkStateReceiver);
//        unregisterReceiver(homeKeyDownReceiver);
    }
}
