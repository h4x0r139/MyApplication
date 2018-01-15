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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Set;

import cn.yinxm.lib.utils.log.LogUtil;


public class BtStateActivity extends Activity {
    Button btnUpdate;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt_state);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        tv = (TextView) findViewById(R.id.tv);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBluetoothState();
            }
        });

        registerReceiver(btReceiver,new IntentFilter(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED));//只能通过代码动态注册监听
    }

    private void updateBluetoothState() {
        StringBuilder stringBuilder = new StringBuilder();

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter != null) {
            stringBuilder.append("bt state="+bluetoothAdapter.getState()).append("\n");
            stringBuilder.append("bt getName="+bluetoothAdapter.getName()).append("\n");
            stringBuilder.append("bt getAddress="+bluetoothAdapter.getAddress()).append("\n");
            stringBuilder.append("bt getScanMode="+bluetoothAdapter.getScanMode()).append("\n");
            //获取连接状态
            stringBuilder.append("bt A2DP con="+bluetoothAdapter.getProfileConnectionState(BluetoothProfile.A2DP)).append("\n");
            stringBuilder.append("bt HEADSET con="+bluetoothAdapter.getProfileConnectionState(BluetoothProfile.HEADSET)).append("\n");
            stringBuilder.append("bt HEALTH con="+bluetoothAdapter.getProfileConnectionState(BluetoothProfile.HEALTH)).append("\n");

            //获取已配对列表
            Set<BluetoothDevice> bindDevices =  bluetoothAdapter.getBondedDevices();
//            LogUtil.d("getBondedDevices="+bindDevices);
            for (BluetoothDevice device : bindDevices) {
                stringBuilder.append("getName="+device.getName()+", getAddress="+device.getAddress()+", getBondState="+device.getBondState()).append("\n");
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
