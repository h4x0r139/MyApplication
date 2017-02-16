package com.yinxm.bt;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;

/**
 * Created by yinxm on 2017/2/5.
 * 功能:
 */

public class BluetoothManager {


    /**
     * 蓝牙是否打开
     * @param context
     * @return
     */
    public static boolean isBluetoothOpen(Context context) {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter != null) {
            return mBluetoothAdapter.isEnabled();
        }
        return false;
    }
}
