package com.h4x0r.work_ec.wifi.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.h4x0r.R;
import com.h4x0r.work_ec.wifi.client.IResponseCallBack;
import com.h4x0r.work_ec.wifi.client.WifiClient;
import com.h4x0r.work_ec.wifi.config.WifiConfig;
import com.h4x0r.work_ec.wifi.data.DefaultWifiRequest;
import com.h4x0r.work_ec.wifi.util.DataUtils;
import com.h4x0r.work_ec.wifi.util.GsonUtils;
import com.h4x0r.work_ec.wifi.util.UUIDutils;


/**
 * Created by yinxm on 2016/8/1.
 */

public class WifiTestActivity extends AppCompatActivity {

    private EditText et_test;
    private Button btn_test;
    private TextView tv_log;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonResponse = (String) msg.obj;
            tv_log.setText(tv_log.getText().toString() + "\r\n" + jsonResponse);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_test);

        Intent intent = new Intent(this, WifiClient.class);
        startService(intent);

        btn_test = (Button) findViewById(R.id.btn_test);
        et_test = (EditText) findViewById(R.id.et_test);
        tv_log = (TextView) findViewById(R.id.tv_log);

        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendMessage = et_test.getText().toString();
                DefaultWifiRequest mTestWifiRequest = new DefaultWifiRequest();
                mTestWifiRequest.requestCode = UUIDutils.createUUID();
                //action 类似于接口名
                mTestWifiRequest.action = WifiConfig.RA.TEST;
                mTestWifiRequest.data = sendMessage;
                String requestStr = new GsonUtils<DefaultWifiRequest>().toJson(mTestWifiRequest);
                WifiClient.sendMessageToServer(DataUtils.stringToXml(requestStr), mTestWifiRequest.requestCode, new IResponseCallBack() {
                    @Override
                    public void onCallBack(String jsonResponse) {
                        //正常开发的时候,拿到json该怎么解析就怎么解析
                        Message obtain = Message.obtain();
                        obtain.obj = jsonResponse;
                        handler.sendMessage(obtain);
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, WifiClient.class);
        stopService(intent);
    }
}
