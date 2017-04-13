package com.yinxm.thread_test;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv_hello;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_hello = (TextView) findViewById(R.id.tv_hello);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("yinxm", "msg="+msg+",what="+msg.what);
            Log.d("yinxm", "handler threadId="+Thread.currentThread().getId()+", name="+Thread.currentThread().getName()+", myLooper="+Looper.myLooper()+", main="+Looper.getMainLooper());

        }
    };

    /**
     * 切换线程方式
     * 1.Handler成员
     * 2.Activity中的runOnUIThread
     * 3.
     * @param view
     */
    public void testChangeThreadToMain(View view) {
        Log.d("yinxm", "testChangeThreadToMain threadId="+Thread.currentThread().getId()+", name="+Thread.currentThread().getName()+", myLooper="+Looper.myLooper()+", main="+Looper.getMainLooper());
        handler.sendEmptyMessage(0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    handler.sendEmptyMessage(1);
                    Thread.sleep(2000);
                    handler.sendEmptyMessage(2);
                    Log.d("yinxm", "threadId="+Thread.currentThread().getId()+", name="+Thread.currentThread().getName()+", myLooper="+Looper.myLooper()+", main="+Looper.getMainLooper());
                    Looper.prepare();//并不能用来切换到线程，只是将当前线程的消息放入消息队列中
                    handler.sendEmptyMessage(3);
                    Toast.makeText(MainActivity.this,"我是提示", Toast.LENGTH_SHORT).show();//Can't create handler inside thread that has not called Looper.prepare()
                    Log.d("yinxm", "end threadId="+Thread.currentThread().getId()+", name="+Thread.currentThread().getName()+", myLooper="+Looper.myLooper()+", main="+Looper.getMainLooper());
//                    tv_hello.setText("这是更新后的数据");//Only the original thread that created a view hierarchy can touch its views.
                    Looper.loop();
                    handler.sendEmptyMessage(4);
                    Log.d("yinxm", "test end");

                }catch (Exception e) {
                    Log.e("yinxm", e.getMessage());
                }
            }
        }).start();
        Log.d("yinxm", "testChangeThreadToMain threadId="+Thread.currentThread().getId()+", name="+Thread.currentThread().getName()+", myLooper="+Looper.myLooper()+", main="+Looper.getMainLooper());

    }

    public void nextAct(View view) {
        Intent intent =  new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

}
