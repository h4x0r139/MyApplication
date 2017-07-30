package com.ecarx.bt;

import android.app.ActivityManager;
import android.app.Application;
import android.util.Log;

import com.yinxm.bt.AppBluetoothManager;

import java.util.List;

import ecarx.app.CarSignalManager;

/**
 * 功能：
 * Created by yinxm on 2017/7/30.
 */

public class BTPhoneApp extends Application {
    static final String TAG = BTPhoneApp.class.getSimpleName();

    private boolean defaultProcess = true;

    AppBluetoothManager mAppBluetoothManager;

    public enum State {
        IDLE, INCOMING, OUTGOING, ACTIVE
    }

    public static boolean mIsComingCall = false;


    private BTPhoneManager mBTPhoneManager;
    private CarSignalManager mService;


    public BTPhoneManager getBTPhoneManager() {
        return mBTPhoneManager;
    }

    public CarSignalManager getService() {
        return mService;
    }

    private static BTPhoneApp phoneApp;

    public static BTPhoneApp getInstance() {
        return phoneApp;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "----BTPhoneApp---");
        super.onCreate();
        mService = CarSignalManager.get(this);
        phoneApp = this;
//        init();
        if (isMainProcess()) {
//            initPhone();
//            startService(new Intent(this, BlueToothService.class));
            mAppBluetoothManager = new AppBluetoothManager(getApplicationContext());
        }
    }

    private boolean isMainProcess() {
        boolean flag = false;

        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfoList = activityManager.getRunningAppProcesses();
        if (runningAppProcessInfoList != null && runningAppProcessInfoList.size() > 0) {
            int myPid = android.os.Process.myPid();
            String mainProcessName = getPackageName();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningAppProcessInfoList) {
                if (processInfo != null && processInfo.pid == myPid
                        && mainProcessName != null && mainProcessName.equals(processInfo.processName)) {
                    flag = true;
                }
            }
        }

        return flag;
    }

    private void initPhone() {
        mBTPhoneManager = new BTPhoneManager(this);
//        mRinger = new Ringer(this);
//        mVoiceSvrMng = new VoiceSvrMng(this);
//        mBTNotifier = new BTCallNotifier(this, mRinger, mBTPhoneManager);
//
//
//        mDBManager = new DBManager(this);
//        mDBManager.registerObserver();
//        BTAppManager.init(this);
    }
}
