package com.h4x0r.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yinxm.aidl_test.IMyAidlInterfaceRemoteBinder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {

    private Button startServiceButton = null;
    private Button stopServiceButton = null;
    private TextView tv_service_count;

    private Intent intent;

    private Intent otherIntentService;

    private EditText etSyncData;
    private IMyAidlInterfaceRemoteBinder binder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startServiceButton = (Button) findViewById(R.id.startService);
        startServiceButton.setOnClickListener(new StartServiceListener());
        stopServiceButton = (Button) findViewById(R.id.stopService);
        stopServiceButton.setOnClickListener(new StopServiceListener());
        tv_service_count = (TextView) findViewById(R.id.tv_service_count);
        System.out.println("Activity onCreate");

        findViewById(R.id.btnBindService).setOnClickListener(this);
        findViewById(R.id.btnUnbindService).setOnClickListener(this);
        intent = new Intent(this,MyService.class);

        findViewById(R.id.startOtherService).setOnClickListener(this);
        findViewById(R.id.stopOtherService).setOnClickListener(this);
        otherIntentService = new Intent();
        otherIntentService.setComponent(new ComponentName("com.yinxm.aidl_test", "com.yinxm.aidl_test.MyService"));

        findViewById(R.id.btnBindOtherService).setOnClickListener(this);
        findViewById(R.id.btnUnbindOtherService).setOnClickListener(this);

        //aidl通信
        findViewById(R.id.btSyncData).setOnClickListener(this);
        etSyncData = (EditText) findViewById(R.id.etSyncData);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBindService:
                bindService(intent,this, Context.BIND_AUTO_CREATE);//Intent service, ServiceConnection conn,int flags
                break;
            case R.id.btnUnbindService:
                unbindService(this);
                break;
            case R.id.startOtherService:
                startService(otherIntentService);
                break;
            case R.id.stopOtherService:
                stopService(otherIntentService);
                break;
            case  R.id.btnBindOtherService:
                bindService(otherIntentService,this, Context.BIND_AUTO_CREATE);
                break;
            case  R.id.btnUnbindOtherService:
                unbindService(this);
                break;
            case R.id.btSyncData:
                String data = etSyncData.getText().toString();
                try {
                    binder.setData(data);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        System.out.println("[onServiceConnected] ComponentName="+name+", IBinder="+service);
        binder = IMyAidlInterfaceRemoteBinder.Stub.asInterface(service);
        System.out.println("binder="+service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        System.out.println("[onServiceDisconnected] ComponentName="+name);
    }

    class StartServiceListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            //向service传递数据
            intent.putExtra("num",5);
            intent.setClass(MainActivity.this, MyService.class);
            startService(intent);
        }
    }

    class StopServiceListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, MyService.class);
//            intent.setClass(MainActivity.this, MyService.class);
            stopService(intent);
        }
    }


    HandlerUpdateUIByService handlerUpdateUIByService = new HandlerUpdateUIByService();

    class HandlerUpdateUIByService extends Handler {
        @Override
        public void handleMessage(Message msg) {
            System.out.println("HandlerUpdateUIByService");
            int num = msg.arg1;
            System.out.println("num="+num);
            tv_service_count.setText(num);
        }
    }

}
