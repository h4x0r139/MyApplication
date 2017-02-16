package com.yinxm.bt;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import java.util.Set;

import cn.yinxm.lib.utils.LogUtil;

public class BtStateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt_state);
        registerReceiver(btReceiver,new IntentFilter(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED));//只能通过代码动态注册监听
    }

    @Override
    protected void onResume() {
        super.onResume();

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter != null) {
            LogUtil.d("bt state="+bluetoothAdapter.getState());
            LogUtil.d("bt getName="+bluetoothAdapter.getName());
            LogUtil.d("bt getAddress="+bluetoothAdapter.getAddress());
            LogUtil.d("bt getScanMode="+bluetoothAdapter.getScanMode());
            //获取连接状态
            LogUtil.d("bt A2DP con="+bluetoothAdapter.getProfileConnectionState(BluetoothProfile.A2DP));
            LogUtil.d("bt HEADSET con="+bluetoothAdapter.getProfileConnectionState(BluetoothProfile.HEADSET));
            LogUtil.d("bt HEALTH con="+bluetoothAdapter.getProfileConnectionState(BluetoothProfile.HEALTH));

            //获取已配对列表
            Set<BluetoothDevice> bindDevices =  bluetoothAdapter.getBondedDevices();
//            LogUtil.d("getBondedDevices="+bindDevices);
            for (BluetoothDevice device : bindDevices) {
                LogUtil.d("getName="+device.getName()+", getAddress="+device.getAddress()+", getBondState="+device.getBondState());
            }
        }
    }

    private BroadcastReceiver btReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            LogUtil.d("btReceiver action="+intent.getAction());
            if (BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED.equals(intent.getAction())) {
                int state = intent.getIntExtra(BluetoothAdapter.EXTRA_CONNECTION_STATE, 0);
                LogUtil.d("btReceiver state="+state);
                switch (state) {
                    case BluetoothAdapter.STATE_CONNECTING:
                        break;
                    case BluetoothAdapter.STATE_CONNECTED:
                        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                        LogUtil.d("btReceiver device="+device);
                        break;
                    case BluetoothAdapter.STATE_DISCONNECTING:
                        break;
                    case BluetoothAdapter.STATE_DISCONNECTED:
                        break;
                }
            }
        }
    };
}
