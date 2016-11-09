package cn.yinxm.lib.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

import cn.yinxm.lib.media.PlayerManager;
import cn.yinxm.lib.utils.LogUtil;

/**
* @Description: 耳机插拔监听
* Author: yinxm
* Date: 2016/10/31 19:30
*/
public class HeadsetPlugReceiver extends BroadcastReceiver {
    public HeadsetPlugReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        LogUtil.d("action="+action);
        switch (action){
            //耳机拔出、插入时广播
            case Intent.ACTION_HEADSET_PLUG:
                int state = intent.getIntExtra("state", 0);
                if (state == 0) {// 耳机拔出——》外放打开
                    PlayerManager.getInstance().changeToSpeakerMode();
                } else if (state == 1) {// 耳机插入——》外放关闭
                    PlayerManager.getInstance().changeToHeadsetMode();
                }
                break;
            //拔出耳机会触发此广播,插入不会触发,且此广播比上一个早,故可在此暂停播放,收到上一个广播时在恢复播放
            case AudioManager.ACTION_AUDIO_BECOMING_NOISY:
                LogUtil.d("拔出耳机，先暂停播放，后继续播放");//解决：耳机切换到外放会出现丢失语音
                break;
        }
        if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {

        }

    }
}
