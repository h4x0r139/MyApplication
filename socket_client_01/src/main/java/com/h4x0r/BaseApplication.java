package com.h4x0r;

import android.app.Application;
import android.content.Intent;

import com.h4x0r.ecarx.wifi.client.WifiClient;

/**
 * Created by yinxm on 2016/8/27.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        startService(new Intent(this, WifiClient.class));
    }

}
