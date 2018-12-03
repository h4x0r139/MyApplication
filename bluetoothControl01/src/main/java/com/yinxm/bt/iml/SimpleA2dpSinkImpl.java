package com.yinxm.bt.iml;

import android.bluetooth.BluetoothDevice;

import com.yinxm.bt.Ia2dpSinkApi;

import java.util.List;

/**
 * <p>
 *
 * @author yinxuming
 * @date 2018/9/30
 */
public class SimpleA2dpSinkImpl implements Ia2dpSinkApi {
    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public BluetoothDevice getConnectedDevice() {
        return null;
    }

    @Override
    public List<BluetoothDevice> getCanConnectDevices() {
        return null;
    }
}
