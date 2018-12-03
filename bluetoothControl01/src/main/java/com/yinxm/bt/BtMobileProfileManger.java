package com.yinxm.bt;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * <p>
 *
 * @author yinxuming
 * @date 2018/9/27
 */
public class BtMobileProfileManger {
    private static final String TAG = "BtMusicProfileManger";
   private final String BT_NAME = "QCY-QY7";
    BluetoothAdapter mBtAdapter;
    BluetoothA2dp mA2dp;
    BluetoothDevice mConnectDevice;

    private BtMobileProfileManger() {}

    public static BtMobileProfileManger getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static BtMobileProfileManger INSTANCE = new BtMobileProfileManger();
    }


    private void processBindDevices() {
        // 获取配对的蓝牙设备
        Set<BluetoothDevice> bondDevice = mBtAdapter.getBondedDevices();
        for(BluetoothDevice device:bondDevice){
            //获取指定名称的设备
            if(BT_NAME.equals(device.getName())){
                mConnectDevice = device;
            }
        }
    }


    private void connectA2dp(BluetoothDevice device){
        setPriority(mConnectDevice, 100); //设置priority
        try {
            //通过反射获取BluetoothA2dp中connect方法（hide的），进行连接。
            Method connectMethod =BluetoothA2dp.class.getMethod("connect",
                    BluetoothDevice.class);
            connectMethod.invoke(mA2dp, device);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void disConnectA2dp(BluetoothDevice device){
        setPriority(mConnectDevice, 0);
        try {
            //通过反射获取BluetoothA2dp中connect方法（hide的），断开连接。
            Method connectMethod =BluetoothA2dp.class.getMethod("disconnect",
                    BluetoothDevice.class);
            connectMethod.invoke(mA2dp, device);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPriority(BluetoothDevice device, int priority) {
        if (mA2dp == null) return;
        try {//通过反射获取BluetoothA2dp中setPriority方法（hide的），设置优先级
            Method connectMethod =BluetoothA2dp.class.getMethod("setPriority",
                    BluetoothDevice.class,int.class);
            connectMethod.invoke(mA2dp, device, priority);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
