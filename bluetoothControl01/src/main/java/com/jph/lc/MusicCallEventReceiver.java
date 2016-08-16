package com.jph.lc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
* @Description: 音乐、电话等由蓝牙按键或者第三方触发的广播事件处理
* Author: yinxm
* Date: 2016/7/21 20:56
*/
public class MusicCallEventReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction() ;
        Log.i("yika", "com.jph.lc.MusicCallEventReceiver onReceive action="+intentAction);

    }


}
