package com.yinxm.aidl_test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service {
    private String data = "默认数据";
    private boolean isRunning = false;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
       return new IMyAidlInterfaceRemoteBinder.Stub() {
           @Override
           public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
                System.out.println("com.yinxm.aidl_test.MyService onBind.basicTypes");
           }

           @Override
           public void setData(String data) throws RemoteException {
                MyService.this.data = data;
           }
       };
    }
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("com.yinxm.aidl_test.MyService Service started");
        new Thread(){
            @Override
            public void run() {
                super.run();
                isRunning = true;
                while (isRunning) {
                    System.out.println(data);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("com.yinxm.aidl_test.MyService Service destory");
        isRunning = false;
    }
}
