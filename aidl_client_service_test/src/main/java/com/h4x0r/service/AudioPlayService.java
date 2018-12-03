package com.h4x0r.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;

import cn.yinxm.lib.utils.log.LogUtil;


public class AudioPlayService extends Service {
    public AudioPlayService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.d("com.h4x0r.service.AudioPlayService.onBind audioPlayBinder="+audioPlayBinder);
        return audioPlayBinder;
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
        LogUtil.d("com.h4x0r.service.AudioPlayService.unbindService");
        LogUtil.d("BaseApplication audioPlayServcie="+((BaseApplication) getApplication()).getAudioPlayService());
    }

    private AudioPlayBinder audioPlayBinder = new AudioPlayBinder();
    public class AudioPlayBinder extends Binder {
        public AudioPlayService getService() {
            return AudioPlayService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d("com.h4x0r.service.AudioPlayService.onCreate");
        LogUtil.d("BaseApplication audioPlayServcie="+((BaseApplication) getApplication()).getAudioPlayService());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogUtil.d("BaseApplication audioPlayServcie="+((BaseApplication) getApplication()).getAudioPlayService());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.d("com.h4x0r.service.AudioPlayService.onStartCommand");

        return super.onStartCommand(intent, flags, startId);
    }
}
