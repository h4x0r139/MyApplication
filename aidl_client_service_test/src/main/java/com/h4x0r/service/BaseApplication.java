package com.h4x0r.service;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import cn.yinxm.lib.utils.log.LogUtil;


/**
 * Created by yinxm on 2016/10/2.
 */

public class BaseApplication extends Application {
    private AudioPlayService audioPlayService;

    public AudioPlayService getAudioPlayService() {
        return audioPlayService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d("BaseApplication.onCreate");
        startAndBindAudioPlayService();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogUtil.e("BaseApplication create end");
    }

    private void startAndBindAudioPlayService() {
        startService(new Intent(this,AudioPlayService.class));//startService也是异步接口，（bind, unbind, startService, stopService）都是异步接口
        bindService(new Intent(this, AudioPlayService.class), audioPlayServiceConnection, BIND_AUTO_CREATE);
    }

    ServiceConnection audioPlayServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtil.d("BaseApplication.audioPlayServiceConnection onServiceConnected");
            audioPlayService = ((AudioPlayService.AudioPlayBinder) service).getService();
            LogUtil.d("audioPlayService="+audioPlayService);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtil.d("BaseApplication.onCreate onServiceDisconnected");
            audioPlayService = null;
            LogUtil.d("audioPlayService="+audioPlayService);
        }
    };

}
