package com.h4x0r.work_ec.wifi.client;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import com.h4x0r.work_ec.LogUtil;
import com.h4x0r.work_ec.wifi.config.WifiConfig;
import com.h4x0r.work_ec.wifi.util.DataUtils;
import com.h4x0r.work_ec.wifi.util.WifiUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;



/**
 * Created by yinxm on 2016/8/1.
 */

public class WifiClient extends Service {

    private static Socket mClientSocket;//客户端socket
    private static PrintStream mOut;//客户端输出流
    private static InputStream mIn;//客户端输入流
    private static Map<String, IResponseCallBack> mCallBacks = new HashMap<>();//响应回调临时存储

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                Object[] objs = (Object[]) msg.obj;
                IResponseCallBack responseCallBack = (IResponseCallBack) objs[0];
                String jsonResponse = (String) objs[1];
                String requestCode = (String) objs[2];
                if (responseCallBack != null) {
                    responseCallBack.onCallBack(jsonResponse);
                    mCallBacks.remove(requestCode);
                }
            } catch (Exception e) {
               LogUtil.i("[wifi回调异常]" + e.toString());
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread() {
            @Override
            public void run() {
                super.run();
                initClient();
                initSendMessageIO();
                initReciverMessageIO();
            }
        }.start();
    }

    //向服务器发送消息
    public static void sendMessageToServer(final String requestStr, final String requestCode, final IResponseCallBack responseCallBack) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                if (mClientSocket != null && mClientSocket.isConnected()) {
                    if (mOut != null) {
                        if (responseCallBack != null) {
                            mCallBacks.put(requestCode, responseCallBack);
                        }
                        mOut.println(requestStr);
                    }
                }
            }
        }.start();
    }

    //初始化输入流,并且开始轮询接收消息
    private void initReciverMessageIO() {
        try {
            if (mClientSocket != null && mClientSocket.isConnected()) {
                mIn = mClientSocket.getInputStream();
                while (mClientSocket.isConnected()) {
                    try {
                        String response = WifiUtils.inputStreamToString(mIn);
                        if (response != null && !"".equals(response)) {
                            String jsonResponse = DataUtils.xmlToString(response);
                            JSONObject jsonObject = new JSONObject(jsonResponse);
                            String requestCode = jsonObject.getString(WifiConfig.RK.REQUEST_CODE);
                            IResponseCallBack responseCallBack = mCallBacks.get(requestCode);
                            if (responseCallBack != null) {
                                Object[] objs = new Object[3];
                                objs[0] = responseCallBack;
                                objs[1] = jsonResponse;
                                objs[2] = requestCode;
                                Message obtain = Message.obtain();
                                obtain.obj = objs;
                                mHandler.sendMessage(obtain);
                            }
                            //响应值
                            LogUtil.i("[wifi通信]response:" + response);
                        }
                    } catch (Exception e) {
                        LogUtil.i("[wifi通信]异常 : " + e.toString());
                        e.printStackTrace();
                    }
                    Thread.sleep(50);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //初始化输出流
    private void initSendMessageIO() {
        try {
            if (mClientSocket != null && mClientSocket.isConnected()) {
                mOut = new PrintStream(mClientSocket.getOutputStream(), true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //初始化socket连接
    private void initClient() {
        try {
            mClientSocket = new Socket(WifiConfig.IP, WifiConfig.PROT);
            mClientSocket.setSoTimeout(10);
            mClientSocket.setTcpNoDelay(true);
            mClientSocket.setSoLinger(true, 30);
            mClientSocket.setSendBufferSize(100);
            mClientSocket.setReceiveBufferSize(100);
            mClientSocket.setKeepAlive(true);
            mClientSocket.setOOBInline(true);
            mClientSocket.sendUrgentData(0x44);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
