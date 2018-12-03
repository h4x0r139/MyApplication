package com.yinxm.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 1、在onCreate中，子线程更新UI为什么不报错？
 * android.view.ViewRootImpl$CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.
 * (1)onCreate时，ViewRootIml还没有实例化，没法调用﻿ViewRootIml.checkThread
 * ﻿(2)ViewRootImpl是在WindowManagerGlobal的addView方法中创建的：可以简化的认为是在Activity的onResume回调时执行的，具体参考笔记《﻿子线程更新UI问题》
 * 2、Handler消息机制流程
 * 3、Handler子线程实例化
 * (1)子线程回调handleMessage方法，调用顺序：﻿Looper.prepare()-》new Handler-》Looper.loop()
 * (2)主线程回调handleMessage方法，调用顺序：new Handler(getMainLooper())即可
 */
public class HandlerActivity extends Activity {

    private Button btn1;
    private TextView tv1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("yinxm", "handleMessage msg=" + msg);
        }
    };

    private Handler handlerInitByThread, handlerInitByThread2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("yinxm", "-------onCreate SystemClock.uptimeMillis()=" + SystemClock.uptimeMillis());

        setContentView(R.layout.activity_handler);
        tv1 = (TextView) findViewById(R.id.textView);
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerInitByThread2.sendEmptyMessage(3);
                handlerInitByThread.sendEmptyMessage(3);
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("yinxm", "onCreate 子线程更新UI start ");
                tv1.setText("onCreate 子线程更新UI");//能够更新UI成功，不报错

                Log.d("yinxm", "onCreate 子线程更新UI  end ");
            }
        }).start();

        initHandler2();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("yinxm", "-------onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("yinxm", "-------onResume");
        //        new Thread(new Runnable() {
        //            @Override
        //            public void run() {
        //                Log.d("yinxm", "after resume 子线程更新UI start ");
        //                tv1.setText("after resume 子线程更新UI");//能够更新UI成功，不报错
        //
        //                Log.d("yinxm", "after resume 子线程更新UI end ");
        //            }
        //        }).start();

        //        handler.sendEmptyMessage(1);
        Message message = new Message();
        message.what = 1;
        message.arg1 = 1;
        Bundle bundle = new Bundle();
        bundle.putString("test1_key", "test1_value");
        message.setData(bundle);
        handler.sendMessage(message);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("yinxm", "-------onRestart");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.d("yinxm", "-------onWindowFocusChanged hasFocus=" + hasFocus);
        //        new Thread(new Runnable() {
        //            @Override
        //            public void run() {
        //                Log.d("yinxm", "after window has Focus 子线程更新UI start ");
        //                try {
        //                    Thread.sleep(1000);
        //                } catch (InterruptedException e) {
        //                    e.printStackTrace();
        //                }
        //                tv1.setText("after window has Focus 子线程更新UI");//能够更新UI成功，不报错
        //
        //                Log.d("yinxm", "after window has Focus 子线程更新UI end ");
        //            }
        //        }).start();

        initHandler();

    }

    /**
     * 子线程初始化Handler，子线程回调handleMessage
     */
    public void initHandler() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                handler.sendEmptyMessage(0);
                handlerInitByThread =
                        new Handler() {// 如果不调用Looper.prepare会报错Can't create handler inside thread that has not
                            // called Looper.prepare()
                            @Override
                            public void handleMessage(Message msg) {
                                super.handleMessage(msg);
                                // 注意，这里的回调都在子线程执行
                                Log.d("yinxm", "handlerInitByThread.handleMessage=" + msg + ", threadId=" + Thread
                                        .currentThread().getId() + ", threadName=" + Thread.currentThread().getName());
//                                tv1.setText("不能在子线程更新UI");
                                if (msg.what == 2) {
                                    //                            Looper.myLooper().quit();
                                }
                            }
                        };
                handlerInitByThread.sendEmptyMessage(1);//能接收到消息
                Looper.loop();//循环处理消息队列中的消息，这之后再调用sendMsg方法，不会起作用，因为loop方法中msg.next方法是阻塞方法

                /**********************在没有调用myLooper().quit();前 这后面的代码都不会执行**************************/
                Log.d("yinxm", "after Looper.loop(); ");
                handlerInitByThread.sendEmptyMessage(5);//不能接收到消息
                handler.sendEmptyMessage(6);//不能接收到消息
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(7);//能接收到消息

            }
        }).start();
    }

    /**
     * 子线程初始化Handler，主线程回调handleMessage
     */
    public void initHandler2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 即使Handler再线程初始化，也可以让它的handleMessage在主线程回调，只要绑定主线程的looper即可
                handlerInitByThread2 = new Handler(getMainLooper()) {
                    @Override
                    public void handleMessage(Message msg) {
                        Log.d("yinxm",
                                "handlerInitByThread2.handleMessage=" + msg + ", threadId=" + Thread
                                        .currentThread().getId() + ", threadName=" + Thread.currentThread().getName());
                    }
                };
                handlerInitByThread2.sendEmptyMessage(2);
            }
        }).start();

    }

    public void updateUIInThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("yinxm", "updateUIInThread 子线程更新UI start ");
                //                try {
                //                    Thread.sleep(1000);
                //                } catch (InterruptedException e) {
                //                    e.printStackTrace();
                //                }
                tv1.setText("updateUIInThread 子线程更新UI");//能够更新UI成功，不报错

                Log.d("yinxm", "updateUIInThread 子线程更新UI end ");
            }
        }).start();
    }
}
