package cn.yinxm.notification_test;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

/**
 * Created by yinxm on 2016/9/9.
 */
public class MusicNotification {

    // 通知id
    public static final int NOTIFICATION_ID = 10001;
    // 通知
    private Notification musicNotifi = null;
    // 管理通知
    private NotificationManager manager = null;
    // 界面实现
    private NotificationCompat.Builder builder = null;
    // 上下文
    private Context context;
    // 布局
    private RemoteViews remoteViews;
    private final int REQUEST_CODE = 30000;

    private Intent playIntent = null, nextIntent = null, closeIntent = null, notifiLayoutIntent;

    private static MusicNotification instance;

    public static MusicNotification getInstance() {
        if (instance == null) {
            instance = new MusicNotification();
        }
        return instance;
    }

    public void setInstance(MusicNotification instance) {
        this.instance = instance;
    }

    public MusicNotification() {}

    public MusicNotification(Context context, NotificationManager manager) {
        this.context = context;
        this.manager = manager;
        // 初始化操作
        remoteViews = new RemoteViews(context.getPackageName(),R.layout.layout_play_notifi);
        builder = new NotificationCompat.Builder(context);

        // 初始化控制的Intent
        playIntent = new Intent();
        playIntent.setAction(BroadcastReceiverConstant.ACTION_PLAY_BUTTON);
        nextIntent = new Intent();
        nextIntent.setAction(BroadcastReceiverConstant.ACTION_PLAY_NEXT);
        closeIntent = new Intent();
        closeIntent.setAction(BroadcastReceiverConstant.ACTION_CLOASE_NOTIFI);
        notifiLayoutIntent = new Intent(context, NotificationTitleContentActivity.class);
        notifiLayoutIntent.setAction(BroadcastReceiverConstant.ACTION_NOTIFI_ONCLICK);

        onCreateMusicNotifi();
        instance = this;
    }

    /**
     * 创建通知
     */
    private void onCreateMusicNotifi() {
        // 设置点击事件

        // 1.注册控制点击事件
        PendingIntent pplay = PendingIntent.getBroadcast(context, REQUEST_CODE,playIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.btn_custom_play,pplay);

        // 2.注册下一首点击事件
        PendingIntent pnext = PendingIntent.getBroadcast(context, REQUEST_CODE,nextIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.btn_custom_next,pnext);

        // 3.注册关闭点击事件
        PendingIntent pclose = PendingIntent.getBroadcast(context, REQUEST_CODE,closeIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.btn_custom_close,pclose);

        //点击Notifi打开界面
        PendingIntent pNotifiClick = PendingIntent.getActivity(context, REQUEST_CODE, notifiLayoutIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        musicNotifi = builder.setContent(remoteViews)
                .setContentIntent(pNotifiClick)
                .setWhen(System.currentTimeMillis())
                // 通知产生的时间，会在通知信息里显示
//              .setPriority(Notification.PRIORITY_DEFAULT)
                // 设置该通知优先级
                .setOngoing(true)
//                .setTicker("正在播放")
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();

        musicNotifi.flags = Notification.FLAG_ONGOING_EVENT;
//        manager.notify(NOTIFICATION_ID, musicNotifi);
    }

    /**
     * 更新通知
     */
    public void onUpdataMusicNotifi(String title, String artist, String logoUrl, boolean isPlay) {
        // 设置添加内容
        remoteViews.setTextViewText(R.id.tv_title, title);
        remoteViews.setTextViewText(R.id.tv_artist, artist);

        //判断是否播放
        if (isPlay) {
            remoteViews.setImageViewResource(R.id.btn_custom_play,
                    R.mipmap.notify_btn_pause);
        } else {
            remoteViews.setImageViewResource(R.id.btn_custom_play,
                    R.mipmap.notify_btn_play);
        }
        manager.notify(NOTIFICATION_ID, musicNotifi);
    }

    /**
     * 取消通知栏
     */
    public void onCancelMusicNotifi() {
        if (manager != null) {
            manager.cancel(NOTIFICATION_ID);
        }
    }

    public Notification getMusicNotifi() {
        return musicNotifi;
    }
}
