package com.yinxm.bt;

import android.app.Service;
import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothA2dpSink;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.List;

import cn.yinxm.lib.utils.log.LogUtil;

public class BtService extends Service implements Ia2dpSinkApi {
    BluetoothAdapter mBluetoothAdapter;
    BluetoothHeadset mBluetoothHeadset;
    BluetoothA2dp mA2dp;
    // BluetoothA2dpSink是隐藏api
    BluetoothA2dpSink mA2dpSink;

    BluetoothDevice mDeviceConnected;

    private int btDeviceConnectState;
    private int a2dpSinkConnectState;
    private int a2dpPlayingState;

    public BtService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private IBinder mBinder = new BtBinder();

    @Override
    public boolean isConnected() {
        if (BluetoothA2dpSink.STATE_CONNECTED == a2dpSinkConnectState
                && mA2dpSink != null
                && mDeviceConnected != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isPlaying() {
        if (isConnected()) {
            return mA2dpSink.isA2dpPlaying(mDeviceConnected);
        }
        return false;
    }

    @Override
    public BluetoothDevice getConnectedDevice() {
        return mDeviceConnected;
    }

    @Override
    public List<BluetoothDevice> getCanConnectDevices() {
        // TODO: 2018/9/30

        return null;
    }

    public class BtBinder extends Binder {
        public BtService getBtService() {
            return BtService.this;
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();

        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED);
//        intentFilter.addAction(BluetoothHeadset.ACTION_AUDIO_STATE_CHANGED);

        intentFilter.addAction(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);  // 能收到
//        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
//        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

//        intentFilter.addAction(BluetoothA2dp.ACTION_AVRCP_CONNECTION_STATE_CHANGED);
//        intentFilter.addAction(BluetoothA2dp.ACTION_PLAYING_STATE_CHANGED);
//        intentFilter.addAction(BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED);

        intentFilter.addAction(BluetoothA2dpSink.ACTION_CONNECTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothA2dpSink.ACTION_PLAYING_STATE_CHANGED);
        intentFilter.addAction(BluetoothA2dpSink.ACTION_AUDIO_CONFIG_CHANGED);

        registerReceiver(btReceiver, intentFilter);

        initA2dpSink(getApplicationContext());
    }

    private BroadcastReceiver btReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                return;
            }

            LogUtil.e("BtService btReceiver action=" + action + ", " + intent);
            // TODO: 2018/9/30
            Toast.makeText(context, "收到广播=" + action, Toast.LENGTH_SHORT).show();

            switch (action) {
                case BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED:
                    btDeviceConnectState = intent.getIntExtra(BluetoothAdapter.EXTRA_CONNECTION_STATE, 0);
                    LogUtil.d("btDeviceConnectState=" + btDeviceConnectState);
                    switch (btDeviceConnectState) {
                        case BluetoothAdapter.STATE_CONNECTING:
                            break;
                        case BluetoothAdapter.STATE_CONNECTED:
                            mDeviceConnected = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                            LogUtil.d("btReceiver device=" + mDeviceConnected);
                            updateA2dpSink();
                            break;
                        case BluetoothAdapter.STATE_DISCONNECTING:
                            break;
                        case BluetoothAdapter.STATE_DISCONNECTED:
                            break;
                    }
                    break;
                case BluetoothAdapter.ACTION_STATE_CHANGED:
                    int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0);
                    LogUtil.d("state=" + state);
                    break;
                case BluetoothA2dpSink.ACTION_CONNECTION_STATE_CHANGED:
//                    EXTRA_STATE、EXTRA_PREVIOUS_STATE
                    a2dpSinkConnectState = intent.getIntExtra(BluetoothA2dpSink.EXTRA_STATE, 0);
                    LogUtil.d("a2dpSinkConnectState=" + a2dpSinkConnectState);
                    switch (a2dpSinkConnectState) {
                        case BluetoothA2dpSink.STATE_CONNECTING:
                            break;
                        case BluetoothA2dpSink.STATE_CONNECTED:
                            break;
                        case BluetoothA2dpSink.STATE_DISCONNECTING:
                            break;
                        case BluetoothA2dpSink.STATE_DISCONNECTED:
                            break;

                    }
                    break;
                case BluetoothA2dpSink.ACTION_PLAYING_STATE_CHANGED:
                    a2dpPlayingState = intent.getIntExtra(BluetoothA2dpSink.EXTRA_STATE, 0);
                    LogUtil.d("a2dpPlayingState=" + a2dpPlayingState);

                    break;
                case BluetoothA2dpSink.ACTION_AUDIO_CONFIG_CHANGED:
                    break;
                default:
                    break;
            }
        }
    };


    private void initA2dpSink(Context context) {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBluetoothAdapter.getProfileProxy(context, mProfileListener, BluetoothProfile.A2DP_SINK);
    }


    private void updateA2dpSink() {
        if (mA2dpSink == null) {
            return;
        }
        try {
            List<BluetoothDevice> deviceList = mA2dpSink.getConnectedDevices();
            LogUtil.e("devicesList=" + deviceList);
            for (BluetoothDevice device : deviceList) {
                LogUtil.d("name=" + device.getName() + ", address=" + device.getAddress());
                try {
                    Class clazz = Class.forName("android.bluetooth.BluetoothA2dpSink");
                    if (clazz != null) {
                        Method method = clazz.getMethod("isA2dpPlaying", BluetoothDevice.class);
                        if (method != null) {
                            boolean isPlaying = (boolean) method.invoke(mA2dpSink);
                            LogUtil.d(" isPlaying=" + isPlaying + "， " + mA2dpSink.isA2dpPlaying(device));
                        }
                    }
                    LogUtil.d("getPriority=" + mA2dpSink.getPriority(device)
                            + ", getAudioConfig=" + mA2dpSink.getAudioConfig(device));
                } catch (Exception e) {
                    LogUtil.e(e);
                }
            }
            if (mDeviceConnected != null) {
                LogUtil.d("mDeviceConnected isA2dpPlaying=" + mA2dpSink.isA2dpPlaying(mDeviceConnected));
            }
        } catch (Exception e) {
            LogUtil.e(e);
        }
    }

    private BluetoothProfile.ServiceListener mProfileListener = new BluetoothProfile.ServiceListener() {
        @Override
        public void onServiceConnected(int profile, BluetoothProfile proxy) {
            LogUtil.d("mProfileListener onServiceConnected " + profile + ", proxy=" + proxy);

            // 只有A2DP_SINK为车机端协议，HEADSET、A2DP都为手机端协议
            switch (profile) {
                case BluetoothProfile.A2DP_SINK:
                    mA2dpSink = (BluetoothA2dpSink) proxy;
                    updateA2dpSink();
                    break;
                default:
                    break;

            }
        }

        @Override
        public void onServiceDisconnected(int profile) {
            LogUtil.d("mProfileListener onServiceDisconnected " + profile);
            switch (profile) {
                case BluetoothProfile.A2DP_SINK:
                    mA2dpSink = null;
                default:
                    break;

            }
        }
    };

}
