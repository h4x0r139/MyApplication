package com.yinxm.aidl_test;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

import cn.yinxm.lib.utils.log.LogUtil;

public class MyService extends Service {
    private String data = "默认数据";
    private boolean isRunning = false;

    Handler handler = new Handler();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this,"AIDL Server 收到一个连接请求 onBind do", Toast.LENGTH_LONG).show();

        LogUtil.d("com.yinxm.aidl_test.MyService onBind "
                +",  myPid="+android.os.Process.myPid()
                +",  myUid="+android.os.Process.myUid()
                +",  threadId="+Thread.currentThread().getId());
       return new IMyAidlInterfaceRemoteBinder.Stub() {
           @Override
           public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
                LogUtil.d("com.yinxm.aidl_test.MyService onBind.basicTypes");
           }

           @Override
           public void setData(final String data) throws RemoteException {
               LogUtil.d("com.yinxm.aidl_test.MyService setData="+data
                       +",  myPid="+android.os.Process.myPid()
                       +",  myUid="+android.os.Process.myUid()
                       +",  threadId="+Thread.currentThread().getId());
               MyService.this.data = data;
               //这里Toast不会出现,  Can't create handler inside thread that has not called Looper.prepare()
               handler.post(new Runnable() {
                   @Override
                   public void run() {
                       Toast.makeText(getApplicationContext(),"AIDL Server 收到数据="+data, Toast.LENGTH_SHORT).show();
                   }
               });

           }
       };
    }
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d("com.yinxm.aidl_test.MyService Service started");
        new Thread(){
            @Override
            public void run() {
                super.run();
                isRunning = true;
                while (isRunning) {
                    LogUtil.d(data);
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
        LogUtil.d("com.yinxm.aidl_test.MyService Service destory");
        isRunning = false;
    }
}
