package com.yinxm.bt;

import android.bluetooth.BluetoothDevice;

import java.util.List;

/**
 * <p>
 *
 * @author yinxuming
 * @date 2018/9/30
 */
public interface Ia2dpSinkApi {
    /**
     * 获取a2dp sink 是否连接
     * @return
     */
    boolean isConnected();

    /**
     * 是否正在播放
     * @return
     */
    boolean isPlaying();

    /**
     * 获取连接设备
     * @return
     */
    BluetoothDevice getConnectedDevice();

    /**
     * 获取可连接的设备列表：已经配对过 && 在附近
     *
     * @return
     */
    List<BluetoothDevice> getCanConnectDevices();
}
