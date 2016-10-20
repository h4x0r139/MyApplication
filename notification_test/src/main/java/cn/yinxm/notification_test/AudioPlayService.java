package cn.yinxm.notification_test;

import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;

import cn.yinxm.lib.utils.LogUtil;

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
        LogUtil.d("AudioPlayService.onCreate");
        //通知栏
        musicNotification = new MusicNotification(this, (NotificationManager) getSystemService(NOTIFICATION_SERVICE));

        setForeground();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        updateNotifi();
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


    private AssistServiceConnection mConnection;

    public void setForeground() {
        // sdk < 18 , 直接调用startForeground即可,不会在通知栏创建通知
        if (Build.VERSION.SDK_INT < 18) {
            LogUtil.d("< 18");
            this.startForeground(MusicNotification.NOTIFICATION_ID, musicNotification.getMusicNotifi());
            return;
        }
        LogUtil.d(">=18");
        if (null == mConnection) {
            mConnection = new AssistServiceConnection();
        }

        this.bindService(new Intent(this, AssistService.class), mConnection,
                Service.BIND_AUTO_CREATE);
    }

    private class AssistServiceConnection implements ServiceConnection {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtil.i("AudioPlayService.AssistServiceConnection.onServiceDisconnected");
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            LogUtil.i("AudioPlayService.AssistServiceConnection.onServiceConnected");

            // sdk >=18的，会在通知栏显示service正在运行，这里不要让用户感知，所以这里的实现方式是利用2个同进程的service，利用相同的notificationID，
            // 2个service分别startForeground，然后只在1个service里stopForeground，这样即可去掉通知栏的显示
            Service assistService = ((AssistService.LocalBinder) binder).getService();
            AudioPlayService.this.startForeground(MusicNotification.NOTIFICATION_ID, musicNotification.getMusicNotifi());
            assistService.startForeground(MusicNotification.NOTIFICATION_ID, musicNotification.getMusicNotifi());
            assistService.stopForeground(true);

            AudioPlayService.this.unbindService(mConnection);
            mConnection = null;
        }
    }
}
