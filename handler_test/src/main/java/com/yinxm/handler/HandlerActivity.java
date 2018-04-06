package com.yinxm.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;

/**
 * 1、在onCreate中，子线程更新UI为什么不报错？
 * 2、Handler消息机制流程
 */
public class HandlerActivity extends Activity {

    private TextView tv1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("yinxm", "handleMessage msg="+msg);
        }
    };

    private Handler handlerInitByThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("yinxm", "-------onCreate SystemClock.uptimeMillis()="+ SystemClock.uptimeMillis());

        setContentView(R.layout.activity_handler);
        tv1 = (TextView) findViewById(R.id.textView);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("yinxm", "onCreate 子线程更新UI start ");
                tv1.setText("onCreate 子线程更新UI");//能够更新UI成功，不报错

                Log.d("yinxm", "onCreate 子线程更新UI  end ");
            }
        }).start();
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
        message.what=1;
        message.arg1=1;
        Bundle bundle = new Bundle();
        bundle.putString("test1_key","test1_value");
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
        Log.d("yinxm", "-------onWindowFocusChanged hasFocus="+hasFocus);
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

        HandlerThread

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                handler.sendEmptyMessage(0);
                handlerInitByThread = new Handler() {// 如果不调用Looper.prepare会报错Can't create handler inside thread that has not called Looper.prepare()
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        Log.d("yinxm", "handlerInitByThread.handleMessage="+msg);
                        if (msg.what == 2) {
//                            Looper.myLooper().quit();
                        }
                    }
                };
                handlerInitByThread.sendEmptyMessage(1);//能接收到消息
                handlerInitByThread.sendEmptyMessage(2);//能接收到消息
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
