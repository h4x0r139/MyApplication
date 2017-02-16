package com.yinxm.bt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.yinxm.lib.utils.LogUtil;

public class BtStateReceiver extends BroadcastReceiver {
    public BtStateReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        LogUtil.d("BtStateReceiver action="+action);
        if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
            //蓝牙开关状态
            int blueState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0);
            LogUtil.d("change blueState="+blueState);
            switch (blueState) {
                case BluetoothAdapter.STATE_TURNING_ON:
                    LogUtil.d("STATE_TURNING_ON");
                    break;
                case BluetoothAdapter.STATE_ON:
                    LogUtil.d("STATE_ON");
                    break;
                case BluetoothAdapter.STATE_TURNING_OFF:
                    LogUtil.d("STATE_TURNING_OFF");
                    break;
                case BluetoothAdapter.STATE_OFF:
                    LogUtil.d("STATE_OFF");
                    break;
            }
//        } else if (BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED.equals(action)) {
        } else if (BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED.equals(action)) {
            //连接状态
            int state = intent.getIntExtra(BluetoothProfile.EXTRA_STATE, 0);
            LogUtil.d("change state="+state);
            switch (state) {
                case BluetoothProfile.STATE_CONNECTING:
                    LogUtil.d("STATE_CONNECTING");
                    break;
                case BluetoothProfile.STATE_CONNECTED:
                    LogUtil.d("STATE_CONNECTED");
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    LogUtil.d("device="+device);
                    break;
                case BluetoothProfile.STATE_DISCONNECTING:
                    LogUtil.d("STATE_DISCONNECTING");
                    break;
                case BluetoothProfile.STATE_DISCONNECTED:
                    LogUtil.d("STATE_DISCONNECTED");
                    break;
            }
        }
    }
}
