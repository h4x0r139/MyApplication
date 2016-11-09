package cn.yinxm.activityrebuildtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.yinxm.lib.utils.LogUtil;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ((TextView)findViewById(R.id.tv)).setText(""+this);
        LogUtil.d("打开Main2Activity = singleTask");
    }

    public void openMain(View view) {
        startActivity(new Intent(this, Main3Activity.class));
        LogUtil.d("Main2Activity = "+this+", open Main3Activity");
    }

    public void finishThis(View view) {
        finish();
        LogUtil.d("Main2Activity = "+this+", 调用finish后, isFinishing="+Main2Activity.this.isFinishing()+"，new Thread延时10S");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    LogUtil.d("Thread id="+Thread.currentThread().getId()+" Start");
                    Thread.sleep(10000);
                    LogUtil.d("Thread id="+Thread.currentThread().getId()+" End， Main2Activity = "+Main2Activity.this+", isFinishing="+Main2Activity.this.isFinishing());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
