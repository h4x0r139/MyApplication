package com.ecarx.bt;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import cn.yinxm.lib.utils.log.LogUtil;
import ecarx.bluetooth.BluetoothAdapter;

public class CommonUtils {

    private static final String TAG = CommonUtils.class.getSimpleName();

    private static final String SP_NAME = "btphone";
    private static final String SP_BTPHONE_IDLE_KEY = "btphone_idle";

    public static void saveIdle(Context context, boolean isIdle) {
       LogUtil.d(TAG, "saveIdle isIdle = " + isIdle);
        SharedPreferences preferences = context.getSharedPreferences(SP_NAME, Activity.MODE_WORLD_READABLE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(SP_BTPHONE_IDLE_KEY, isIdle);
        editor.apply();
    }

    private static final String SP_ECHO_MODE_KEY = "btphone_echo_mode";

    /**
     * 设置是否回音消除模式
     *
     * @Title: isECHOMode
     */
    public static void setECHOMode(Context context, boolean isECHOMode) {
       LogUtil.d(TAG, "setECHOMode isECHOMode = " + isECHOMode);
        SharedPreferences preferences = context.getSharedPreferences(SP_NAME,
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(SP_ECHO_MODE_KEY, isECHOMode);
        editor.apply();
    }

    /**
     * 是否回音消除模式
     *
     * @Title: isECHOMode
     */
    public static boolean isECHOMode(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SP_NAME,
                Activity.MODE_PRIVATE);
        boolean value = preferences.getBoolean(SP_ECHO_MODE_KEY, false);
       LogUtil.d(TAG, "isECHOMode = " + value);
        return value;
    }

    /**
     * 检查蓝牙功能是否打开
     *
     * @return true打开，false关闭
     */
    public boolean checkBluetooth() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        final boolean isEnable = bluetoothAdapter.isEnabled();
        LogUtil.i(TAG, "checkBluetooth:" + isEnable);
        return isEnable;
    }

}
