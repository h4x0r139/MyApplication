package com.yinxm.bt;

import android.content.Context;



/**
 * Created by yinxm on 2017/2/5.
 * 功能:
 */

public class AppBluetoothManager {
    private final static String TAG = AppBluetoothManager.class.getSimpleName();

    Context mContext;
//    private BluetoothCarHeadSet profile;
//
//    public AppBluetoothManager(Context context) {
//        LogUtil.d(TAG, "HeadSetProfileManager...");
//        this.mContext = context;
//
//
//        BluetoothAdapterCreateHelper.getDefaultAdapter(context, new BluetoothAdapterCreateHelper.AdapterListener() {
//            @Override
//            public void onAdapterCreate() {
//                LogUtil.d(TAG, "callback in onAdapterCreate ...");
//                profile = BluetoothCarHeadSet.getInstance(mContext);
//                profile.registerEventListener(getHeadsetEventListener());
//            }
//        });
//
//        // Set ECHO mode false;
//        CommonUtils.setECHOMode(context, false);
//    }
//
//    private BluetoothCarHeadSet.HeadsetEventListener getHeadsetEventListener() {
//        return new BluetoothCarHeadSet.HeadsetEventListener() {
//
//            @Override
//            public void audioEstablished() {
//                LogUtil.d(TAG, "audioEstablished ...");
//            }
//
//            @Override
//            public void audioReleased() {
//                LogUtil.d(TAG, "audioReleased ...");
//            }
//
//            @Override
//            public void inComingCall(String var1) {
//                LogUtil.e(TAG, "inComingCall ...");
//            }
//
//            @Override
//            public void outGoingCall(String var1) {
//                LogUtil.e(TAG, "outGoingCall ...");
//            }
//
//            @Override
//            public void onGoingCall(String var1) {
//                LogUtil.e(TAG, "onGoingCall ...");
//            }
//
//            @Override
//            public void serviceEstablished() {
//                LogUtil.d(TAG, "serviceEstablished ...");
//            }
//
//            @Override
//            public void serviceReleased() {
//                LogUtil.d(TAG, "serviceReleased ...");
//            }
//
//            @Override
//            public void hangup(String var1) {
//                LogUtil.e(TAG, "hangup ...");
//            }
//
//            @Override
//            public void ringFromPhone(boolean var1) {
//                LogUtil.d(TAG, "ringFromPhone ...");
//            }
//
//            @Override
//            public void onProfileStateChange(int var1) {
//                LogUtil.d(TAG, "onProfileStateChange ...");
//            }
//        };
//    }
//
//
//    /**
//     * 蓝牙是否打开
//     * @param context
//     * @return
//     */
//    public static boolean isBluetoothOpen(Context context) {
//        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//        if (mBluetoothAdapter != null) {
//            return mBluetoothAdapter.isEnabled();
//        }
//        return false;
//    }
}
