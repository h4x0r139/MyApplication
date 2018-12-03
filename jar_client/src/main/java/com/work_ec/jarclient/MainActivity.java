package com.work_ec.jarclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn1,btn2;
    WorkEcIMManager imManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        //初始化sdk
        boolean isInitSuccess = WorkEcIMManager.init(getApplicationContext(), "cn.yinxm.test", "cn.yinxm.test.WorkEcIMApiIml");
        Log.d("yinxm", "isInitSuccess="+isInitSuccess);
        if (isInitSuccess) {
            imManager =   WorkEcIMManager.getInstance();
            Log.d("yinxm", "imManager="+imManager);
        } else {
            Toast.makeText(this,"WorkEcIMManager初始化失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        Log.d("yinxm", "imManager="+imManager+", click v="+v);

        switch (v.getId()) {
            case R.id.btn1:
               if (imManager != null) {
                   imManager.getImFace().gotoWorkEcMsg();
                   Log.d("yinxm", "gotoWorkEcMsg do");

               }
                break;
            case R.id.btn2:
                if (imManager != null) {
//                    imManager.getChat().sendMsgText("好友名称/群名称", "消息内容是你好", new ChatFinishCallback() {
//                        @Override
//                        public void finish(FinishCode finishCode, Map<String, Object> map) {
//                            Log.d("yinxm", "sendMsgText finish="+finishCode+", "+map);
//                        }
//                    });
                    imManager.getImFace().gotoMsgList();
                    Log.d("yinxm", "gotoMsgList do");

                }
                break;
        }
    }
}
