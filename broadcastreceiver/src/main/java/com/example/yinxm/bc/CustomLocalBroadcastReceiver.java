package com.example.yinxm.bc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by yinxuming on 2018/7/29.
 */
public class CustomLocalBroadcastReceiver  extends BroadcastReceiver{

    public static final String ACTION = "com.test.local.receiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("yinxm","intent="+intent);
        Toast.makeText(context, "CustomLocalBroadcastReceiver收到广播 value="
                +intent.getExtras().get("key"),Toast.LENGTH_SHORT).show();
    }
}
