package cn.yinxm.notification_test;

/**
 * Created by yinxm on 2016/9/9.
 */
public class BroadcastReceiverConstant {
    // 点击播放Album时的action
    public static final String ACTION_PLAY_ALBUM = "cn.com.work.ec.music.ACTION_PLAY_ALBUM";
    // 点击了播放/暂停键的时候发这个action
    public static final String ACTION_PLAY_BUTTON = "cn.com.work.ec.music.ACTION_PLAY_BUTTON";
    // 上一首action
    public static final String ACTION_PLAY_PREVIOUS = "cn.com.work.ec.music.ACTION_PLAY_PREVIOUS";
    // 下一首action
    public static final String ACTION_PLAY_NEXT = "cn.com.work.ec.music.ACTION_PLAY_NEXT";
    // 关闭Notification action
    public static final String ACTION_CLOASE_NOTIFI = "cn.com.work.ec.music.ACTION_CLOASE_NOTIFI";
    //点击Notification 布局
    public static final String ACTION_NOTIFI_ONCLICK = "cn.com.work.ec.music.ACTION_NOTIFI_ONCLICK";


    //播放模式 0:智能；1:收藏；2:随机
    public static final String ACTION_PLAY_MODE = "cn.com.work.ec.music.ACTION_PLAY_MODE";
    //蓝牙连接状态 0:未连接；1:已连接
    public static final String ACTION_BLE_CONNECT = "cn.com.work.ec.music.ACTION_BLE_CONNECT";
    //播放提示音
    public static final String ACTION_PLAY_TONE = "cn.com.work.ec.music.ACTION_PLAY_TONE";

}
