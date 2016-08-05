package com.yinxm.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by yinxm on 2016/8/5.
 */
public class SpeechInputDialog extends Dialog{
    Context mContext;
    View dialogContent;

    public SpeechInputDialog(Context context) {
        this(context,R.style.SpeechInputDialog);
    }

    public SpeechInputDialog(Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    protected SpeechInputDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialogContent = LayoutInflater.from(mContext).inflate(R.layout.dialog_speech_input, null);
        setContentView(dialogContent);
        dialogContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "开始监听语音输入", Toast.LENGTH_SHORT).show();
            }
        });
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;//不获取屏幕焦点
        dialogWindow.setAttributes(layoutParams);
    }
}
