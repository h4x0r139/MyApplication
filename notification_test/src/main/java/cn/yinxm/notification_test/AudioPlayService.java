package cn.yinxm.notification_test;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AudioPlayService extends Service {
    MusicNotification musicNotification;
    public AudioPlayService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw null;
    }

    @Override
    public void onCreate() {
        //通知栏
        musicNotification = new MusicNotification(this, (NotificationManager) getSystemService(NOTIFICATION_SERVICE));


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        updateNotifi();
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 更新通知栏
     */
    public void updateNotifi() {
        if (musicNotification != null) {
            musicNotification.onUpdataMusicNotifi("tx", "aasd", "asd", true);
        }
    }

    public MusicNotification getMusicNotification() {
        return musicNotification;
    }
}
