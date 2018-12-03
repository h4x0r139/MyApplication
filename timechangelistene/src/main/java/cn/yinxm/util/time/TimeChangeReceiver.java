package cn.yinxm.util.time;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TimeChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("yinxm", "TimeChangeReceiver.onReceive intent="+intent+", getExtras="+intent.getExtras());
    }
}
