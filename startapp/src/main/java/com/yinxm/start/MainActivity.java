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
        Log.d("yika", "[MainActivity.onCreate]");
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
        Log.d("yika", "[MainActivity.onStart]");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("yika", "[MainActivity.onResume]");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("yika", "[MainActivity.onPause]");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("yika", "[MainActivity.onStop]");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("yika", "[MainActivity.onRestart]");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("yika", "[MainActivity.onDestroy]");

//        unregisterReceiver(networkStateReceiver);
//        unregisterReceiver(homeKeyDownReceiver);
    }
}
