package com.yinxm.viewpager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class WelcomeAct extends AppCompatActivity {

    private boolean isFirstIn = false;
    private static final int TIME = 2000;
    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
    }

    Handler loadHander = new Handler() {//处理动画加载欢迎页面业务
        @Override
        public void handleMessage(Message msg) {
            Log.d("my", "[WelcomeAct.handleMessage]");
            super.handleMessage(msg);
            switch (msg.what) {
                case GO_HOME:
                    goHome();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
            }
        }
    };

    private void init() {//向handler发送消息，读取配置文件判断是用哪种启动方式
        SharedPreferences sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        boolean isFirstStart = sharedPreferences.getBoolean("isFirstStart", true);
        Log.d("my","isFirstStart="+isFirstStart);
        if (isFirstStart) {
//            Message message = loadHander.obtainMessage();
//            message.arg1 = GO_GUIDE;
//            loadHander.sendMessage(message);
            loadHander.sendEmptyMessageDelayed(GO_GUIDE, TIME);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isFirstStart", false);
            editor.commit();
        } else {
            loadHander.sendEmptyMessageDelayed(GO_HOME, TIME);
        }
    }



    private void goHome() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void goGuide() {
        Intent intent = new Intent(this,Guide.class);
        startActivity(intent);
        finish();
    }

}
