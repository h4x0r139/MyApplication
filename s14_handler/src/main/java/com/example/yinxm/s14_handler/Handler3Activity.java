package com.example.yinxm.s14_handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

public class Handler3Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler3);
        //打印了当前线程的ID
        System.out.println("Activity-->" + Thread.currentThread().getId());
        //生成一个HandlerThread对象，实现了使用Looper来处理消息队列的功能，这个类由Android应用程序框架提供
        HandlerThread handlerThread = new HandlerThread("handler_thread");
        //在使用HandlerThread的getLooper()方法之前，必须先调用该类的start();
        handlerThread.start();
        MyHandler myHandler = new MyHandler(handlerThread.getLooper());
        Message msg = myHandler.obtainMessage();
        //将msg发送到目标对象，所谓的目标对象，就是生成该msg对象的handler对象
        Bundle b = new Bundle();
        b.putInt("age", 20);
        b.putString("name", "Jhon");
        msg.setData(b);
        msg.sendToTarget();
    }

    class MyHandler extends Handler {
        public MyHandler(){

        }
        public MyHandler(Looper looper){
            super(looper);
        }
        @Override
        public void handleMessage(Message msg) {
            Bundle b = msg.getData();
            int age = b.getInt("age");
            String name = b.getString("name");
            System.out.println("age is " + age + ", name is" + name);
            System.out.println("Handler--->" + Thread.currentThread().getId());
            System.out.println("handlerMessage");
        }
    }
}
