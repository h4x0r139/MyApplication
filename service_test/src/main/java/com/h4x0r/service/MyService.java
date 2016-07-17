package com.h4x0r.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

//启动服务方式2种：1、startService；2、bindService
public class MyService extends Service {
    private boolean serviceRunning = false;
    private  int num = 0;
    private Handler handler;
    public MyService() {
        this.handler = MainActivity.get
    }

    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("【onBind】");
        return new Binder();
    }

    //当创建一个Servcie对象之后，会首先调用这个函数
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("Service onCreate");
        serviceRunning = true;

        new Thread(){
            @Override
            public void run() {
                while (serviceRunning) {
                    System.out.println("服务正在运行...");
                    try {
                        num ++;
                        sleep(1000);
                        if (num % 10 == 0) {
                            //向UI传递数据
                            Message message = new Message();
                            message.arg1 = num;
                            handler.sendMessage(message);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("onStartCommand ");
        System.out.println("intent--->" + intent+", num="+intent.getIntExtra("num",0));
        System.out.println("flags--->" + flags);
        System.out.println("startId--->" + startId);
        this.num = intent.getIntExtra("num", 0);

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        System.out.println("Service onDestory");
        super.onDestroy();
        serviceRunning = false;
    }
}
