package com.h4x0r.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

//测试绑定服务数据通信
public class MyService2 extends Service {
    private String data = "默认信息";
    public MyService2() {
    }

    class Binder extends android.os.Binder {
        public void setData(String data) {
            MyService2.this.data = data;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new android.os.Binder();
    }
}
