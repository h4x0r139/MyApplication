package cn.yinxm.notification_test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.yinxm.lib.LogUtil;
import cn.yinxm.lib.StringUtil;

/**
* @Description: 接收播放广播
* Author: yinxm
* Date: 2016/5/2 14:50
*/
public class AudioPlayReceiver extends BroadcastReceiver {

    public AudioPlayReceiver() {}

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        LogUtil.d("AudioPlayReceiver.onReceive action="+action);
        if (StringUtil.isBlank(action)) {
            return;
        }
        switch (action) {
            case BroadcastReceiverConstant.ACTION_PLAY_BUTTON:
                break;
            case BroadcastReceiverConstant.ACTION_PLAY_NEXT:
                break;
            case BroadcastReceiverConstant.ACTION_CLOASE_NOTIFI:
                MusicNotification.getInstance().onCancelMusicNotifi();
                break;
        }

    }
}
