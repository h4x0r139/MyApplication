package com.yinxm.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.Html;
import android.widget.TextView;

/**
 * HandlerThread本质是一个Thread，这个Thread中包含Looper，并且已经调用了Looper.prepare和loop方法，简化子线程初始化Handler操作。
 */
public class HandlerThreadActivity extends Activity {

    private static final int MSG_UPADTE = 1;

    private TextView tvInfo;

    private HandlerThread mHandlerThread;
    // 与HandlerThread关联，回调在子线程处理
    private Handler workHandler;

    // 主线程实例化Handler
    private Handler uiHandler = new Handler();

    private boolean isCanUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);

        tvInfo = (TextView) findViewById(R.id.tv_info);

        init();
    }

    private void init() {
        mHandlerThread = new HandlerThread("Custom HandlerThread");
        mHandlerThread.start();
        workHandler = new Handler(mHandlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                checkForUpdate();
                if (isCanUpdate) {
                    // 每隔一段时间请求服务器，检查数据更新
                    workHandler.sendEmptyMessageDelayed(MSG_UPADTE, 1000);
                }
            }
        };
    }

    /**
     * 模拟请求网络，更新UI
     */
    private void checkForUpdate() {
        // 请求网络
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 更新UI
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                String result = "实时更新中，当前大盘指数：<font color='red'>%d</font>";
                result = String.format(result, (int) (Math.random() * 3000 + 1000));
                tvInfo.setText(Html.fromHtml(result));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        isCanUpdate = true;
        workHandler.sendEmptyMessageDelayed(MSG_UPADTE, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();

        isCanUpdate = false;
        workHandler.removeMessages(MSG_UPADTE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        workHandler.removeCallbacksAndMessages(null);
    }
}
