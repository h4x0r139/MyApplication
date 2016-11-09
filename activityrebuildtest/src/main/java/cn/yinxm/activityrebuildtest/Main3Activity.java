package cn.yinxm.activityrebuildtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.yinxm.lib.utils.LogUtil;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ((TextView)findViewById(R.id.tv)).setText(""+this);
        LogUtil.d("打开Main3Activity = singleTop");
    }

    public void openMain(View view) {
        startActivity(new Intent(this, Main4Activity.class));
        LogUtil.d("Main3Activity = "+this+", open Main4Activity");
    }

    public void finishThis(View view) {
        finish();
        LogUtil.d("Main3Activity = "+this+", 调用finish后, isFinishing="+Main3Activity.this.isFinishing()+"，new Thread延时10S");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    LogUtil.d("Thread id="+Thread.currentThread().getId()+" Start");
                    Thread.sleep(10000);
                    LogUtil.d("Thread id="+Thread.currentThread().getId()+" End， Main3Activity = "+Main3Activity.this+", isFinishing="+Main3Activity.this.isFinishing());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
