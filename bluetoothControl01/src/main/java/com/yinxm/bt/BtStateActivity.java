package com.yinxm.bt;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

import cn.yinxm.lib.utils.log.LogUtil;


public class BtStateActivity extends Activity {
    private static final int REQUEST_ENABLE = 1;
    Button btnUpdate;
    TextView tv;
    Button openBt ,gotoBtSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt_state);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        tv = (TextView) findViewById(R.id.tv);
        openBt = findViewById(R.id.openBt);
        gotoBtSet = findViewById(R.id.gotoBtSet);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBluetoothState();
            }
        });


        openBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent enabler = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enabler, REQUEST_ENABLE);
            }
        });
        // TODO: 2018/9/27 不起作用
        startService(new Intent(this, BtService.class));

    }

    private void updateBluetoothState() {
        StringBuilder stringBuilder = new StringBuilder();

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();


        if (bluetoothAdapter != null) {

            if (bluetoothAdapter.isEnabled()) {
                openBt.setVisibility(View.INVISIBLE);
            } else {
                openBt.setVisibility(View.VISIBLE);
            }

            stringBuilder.append(tv.getText()).append("\n\n");
            stringBuilder.append("是否打开蓝牙，isEnabled=" + bluetoothAdapter.isEnabled()).append("\n");
            stringBuilder.append("state=" + bluetoothAdapter.getState()).append("\n");
            stringBuilder.append("getName=" + bluetoothAdapter.getName()).append("\n");
            stringBuilder.append("getAddress=" + bluetoothAdapter.getAddress()).append("\n");
            stringBuilder.append("getScanMode=" + bluetoothAdapter.getScanMode()).append("\n");
            //获取连接状态
            stringBuilder.append("A2DP con=" + bluetoothAdapter.getProfileConnectionState(BluetoothProfile.A2DP)).append("\n");
            stringBuilder.append("HEADSET con=" + bluetoothAdapter.getProfileConnectionState(BluetoothProfile.HEADSET)).append("\n");
            stringBuilder.append("HEALTH con=" + bluetoothAdapter.getProfileConnectionState(BluetoothProfile.HEALTH)).append("\n");

            //获取已配对列表
            Set<BluetoothDevice> bindDevices = bluetoothAdapter.getBondedDevices();
//            LogUtil.d("getBondedDevices="+bindDevices);
            for (BluetoothDevice device : bindDevices) {
                stringBuilder.append("getName=" + device.getName() + ", getAddress=" + device.getAddress() + ", getBondState=" + device.getBondState()).append("\n");
            }
        }
        tv.setText(stringBuilder.toString());
        LogUtil.d(stringBuilder.toString());
    }

    public void clearInfo(View view) {
        tv.setText("");
    }

    public void openBt(View view) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.d("onActivityResult requestCode=" + requestCode + ", resultCode=" + resultCode + ", data=" + data);
        if (REQUEST_ENABLE == requestCode) {

            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "打开蓝牙成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "打开蓝牙失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void gotoBtSet(View view) {
        Intent intent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
        // android.content.ActivityNotFoundException: No Activity found to handle Intent { act=android.settings.BLUETOOTH_SETTINGS }
        startActivity(intent);
    }
}
