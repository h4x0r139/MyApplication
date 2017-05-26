package cn.yinxm.lib.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.yinxm.lib.utils.LogUtil;


public class TimeChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            LogUtil.d("TimeChangeReceiver.onReceive intent="+intent);
//            if (service != null) {
//                SystemTimeUtil.syncNetTime();
//            }
        }catch (Exception e) {
            LogUtil.e(e);
        }

    }
}
