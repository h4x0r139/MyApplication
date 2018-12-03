package com.yinxm.bt.iml;

import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.yinxm.bt.BtService;
import com.yinxm.bt.Ia2dpSinkApi;

import java.util.List;

/**
 * 蓝牙音乐客户端协议
 * 蓝牙音乐手机和车机连接时，车机端相当于是client端，手机端是server端
 * <p>
 *
 * @author yinxuming
 * @date 2018/9/29
 */
public class BtMusicClientProfileManager implements Ia2dpSinkApi {
    private static final String TAG = "BtMusicClientProfileManager";

    private Context mContext;

    BtService mBtService;

    private BtMusicClientProfileManager() {
    }

    public static BtMusicClientProfileManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static BtMusicClientProfileManager INSTANCE = new BtMusicClientProfileManager();
    }


    public void initBt(Context context) {
        mContext = context.getApplicationContext();
        Intent intent = new Intent(mContext, BtService.class);
        mContext.startService(intent);
        mContext.bindService(intent, btServiceConnection, Context.BIND_AUTO_CREATE);
    }


    private ServiceConnection btServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder != null && iBinder instanceof BtService.BtBinder) {
                mBtService = ((BtService.BtBinder) iBinder).getBtService();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBtService = null;
        }
    };

    @Override
    public boolean isConnected() {
        return getA2dpSinkApi().isConnected();
    }

    @Override
    public boolean isPlaying() {
        return getA2dpSinkApi().isPlaying();
    }

    @Override
    public BluetoothDevice getConnectedDevice() {
        return getA2dpSinkApi().getConnectedDevice();
    }

    /**
     * 获取可连接的设备列表：已经配对过 && 在附近
     *
     * @return
     */
    @Override
    public List<BluetoothDevice> getCanConnectDevices() {
        return getA2dpSinkApi().getCanConnectDevices();
    }

    public BtService getBtService() {
        return mBtService;
    }

    public Ia2dpSinkApi getA2dpSinkApi() {
        if (mBtService != null) {
            return mBtService;
        } else {
            if (mContext != null) {
                initBt(mContext);
            }
            return new SimpleA2dpSinkImpl();
        }
    }

}