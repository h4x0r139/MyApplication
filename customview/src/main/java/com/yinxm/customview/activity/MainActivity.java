package com.yinxm.customview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yinxm.customview.CircleProgress;
import com.yinxm.customview.Fragment_RotatingRect;
import com.yinxm.customview.MyFragment;
import com.yinxm.customview.R;
import com.yinxm.customview.SpecialProgressbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CircleProgress cp_view;

    SpecialProgressbar mSpecialProgressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.btnCustomAttr).setOnClickListener(this);
        findViewById(R.id.btnCustomSkin).setOnClickListener(this);
        findViewById(R.id.btnCustomDrawApi).setOnClickListener(this);
        findViewById(R.id.btnCustomView3).setOnClickListener(this);
        findViewById(R.id.btnMyCircle).setOnClickListener(this);
        cp_view = (CircleProgress) findViewById(R.id.cp_view);

        mSpecialProgressbar = findViewById(R.id.sp_progress);
        mSpecialProgressbar.setMaxProgress(100);
        mSpecialProgressbar.setCurrentProgress(5);
        findViewById(R.id.btn_add_pg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSpecialProgressbar.setCurrentProgress((int) (mSpecialProgressbar.getCurrentProgress() + 1));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCustomAttr://自定义属性
                System.out.println("btnCustomAttr click");
                getSupportFragmentManager().beginTransaction().replace(R.id.container,new MyFragment()).commit();
                break;
            case R.id.btnCustomSkin://自定义皮肤
                break;
            case R.id.btnCustomDrawApi://自定义绘图
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new Fragment_RotatingRect()).commit();
                break;
            case R.id.btnCustomView3://自定义绘图
//                getSupportFragmentManager().beginTransaction().replace(R.id.container,new MyCustomView3(this)).commit();
                break;
            case R.id.btnMyCircle://我自定义的圆形图片
                startActivity(new Intent(MainActivity.this, MyCircleActivity.class));
                break;
        }
    }

    int progress = 50;

    public void circleProgessClick(View view) {
//        if (cp_view.getVisibility() == View.VISIBLE) {
//            cp_view.setVisibility(View.GONE);
//        } else {
//            cp_view.setVisibility(View.VISIBLE);
//        }

        cp_view.updateProgress(++progress);
//        cp_view.invalidate();
//        Message message = new Message();
//        message.arg1 = progress++;
//        message.what = MSG_PROGRESS_UPDATE;
//        handler.sendMessage(message);

    }

    private static final int MSG_PROGRESS_UPDATE = 1;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MSG_PROGRESS_UPDATE) {
                int progress = msg.arg1;
//                if (progress >= 100) {
//                    handler.removeMessages(MSG_PROGRESS_UPDATE);
//                } else  {
                    cp_view.updateProgress(progress);
                cp_view.invalidate();
//                updateProgressHandler.sendEmptyMessageDelayed(MSG_PROGRESS_UPDATE);
//                }
            }


        }
    };
}
