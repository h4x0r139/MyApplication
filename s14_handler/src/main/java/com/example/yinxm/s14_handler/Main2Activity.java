package com.example.yinxm.s14_handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class Main2Activity extends AppCompatActivity {

    /** Called when the activity is first created. */
    //声明控件变量
    ProgressBar bar = null;
    Button startButton = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //根据控件的ID得到代表控件的对象,并为按钮设置监听器
        bar = (ProgressBar)findViewById(R.id.bar);
        startButton = (Button)findViewById(R.id.startButton);
        startButton.setOnClickListener(new ButtonListener());
    }
    //当点击startButton按钮时，就会执行ButtonListener的onClick方法
    class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            bar.setVisibility(View.VISIBLE);
            updateBarHandler.post(updateThread);
        }

    }
    //使用匿名内部类来复写Handler当中的handleMessage方法
    Handler updateBarHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {//每当向Handler中发送消息时执行
            bar.setProgress(msg.arg1);
            Bundle bundle = msg.getData();
            updateBarHandler.post(updateThread);
            System.out.println("test---->" + bundle.getString("test"));
        }

    };
    //线程类，该类使用匿名内部类的方式进行声明
    Runnable updateThread = new Runnable(){
        int i = 0 ;
        @Override
        public void run() {
            System.out.println("Begin Thread" + i);
            i = i + 10 ;
            //得到一个消息对象，Message类是有Android操作系统提供
            Message msg = updateBarHandler.obtainMessage();

            //将msg对象的arg1参数的值设置为i,用arg1和arg2这两个成员变量传递消息，优点是系统性能消耗较少
            msg.arg1 = i ;
            Bundle bundle = new Bundle();
            bundle.putString("test", "test bundle");
            msg.setData(bundle);
            try {
                //设置当前显示睡眠1秒
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //将msg对象加入到消息队列当中
            if( i > 100){
                //如果当i的值为100时，就将线程对象从handler当中移除
                updateBarHandler.removeCallbacks(updateThread);
                System.out.println(">>>>>>");
            }else{
                updateBarHandler.sendMessage(msg);
                System.out.println("<<<<<<");
            }
        }
    };

}
