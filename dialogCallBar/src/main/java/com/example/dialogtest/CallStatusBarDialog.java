package com.example.dialogtest;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by yinxm on 2016/7/25.
 * 通话状态栏，调用方式，在onCreate里面new出来，在onResume里面调用show方法
 */
public class CallStatusBarDialog extends Dialog{
    Context mContext;
    View title;//title布局

    /**
     *
     * @param context
     * @param title 默认显示在title的下方
     */
    public CallStatusBarDialog(Context context, View title) {
        this(context, R.style.BaseCustomDialog, title);
    }

    protected CallStatusBarDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    public CallStatusBarDialog(Context context, int themeResId, View title) {
        super(context, themeResId);
        this.mContext = context;
        this.title = title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("[CallStatusBarDialog.onCreate]");
//            dialog.setCancelable(false);//点击dialog外区域不关闭
//            dialog.setCanceledOnTouchOutside(false);
        View dialogContentView = LayoutInflater.from(mContext).inflate(R.layout.call_status_bar_layout, null);
        setContentView(dialogContentView);
        dialogContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("[CallStatusBarDialog.onclick]");
                Toast.makeText(mContext, "点击dialog", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(mContext, CallActivity.class);
//                //读取保存本次通话信息
//                String voicJid = DataUtil.spReadStr(mContext,CallConstant.CALL_CONFIG, CallConstant.VOIC_JID);
//                String voic = DataUtil.spReadStr(mContext,CallConstant.CALL_CONFIG, CallConstant.VOIC);
//                String jid = DataUtil.spReadStr(mContext,CallConstant.CALL_CONFIG, CallConstant.JID);
//                String only = DataUtil.spReadStr(mContext,CallConstant.CALL_CONFIG, CallConstant.ONLY);
//                String name = DataUtil.spReadStr(mContext,CallConstant.CALL_CONFIG, CallConstant.NAME);
//                String dialing = DataUtil.spReadStr(mContext,CallConstant.CALL_CONFIG, CallConstant.DIALING);
//                String dialingphoneNum = DataUtil.spReadStr(mContext,CallConstant.CALL_CONFIG, CallConstant.DIALING_PHONE_NUM);
//                String callID = DataUtil.spReadStr(mContext,CallConstant.CALL_CONFIG, CallConstant.CALL_ID);
//
//                LogUtil.d("[CallStatusBarDialog.onclick] mContext="+mContext+", voicJid="+voicJid+", voic="+voic+", jid="+jid+", only="+only+", name="+name+", dialing="+dialing+", dialingphoneNum="+dialingphoneNum+", callID="+callID);
//
//                intent.putExtra(CallConstant.VOIC_JID, voicJid);
//                intent.putExtra(CallConstant.VOIC, voic);
//                intent.putExtra(CallConstant.JID, jid);
//                intent.putExtra(CallConstant.ONLY, only);
//                intent.putExtra(CallConstant.NAME, name);
//                intent.putExtra(CallConstant.DIALING, dialing);
//                intent.putExtra(CallConstant.DIALING_PHONE_NUM,dialingphoneNum);
//                intent.putExtra(CallConstant.CALL_ID, callID);
//                mContext.startActivity(intent);
            }
        });
        title.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                title.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                resetWidthHeight();
            }
        });

        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.TOP);
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;//不获取屏幕焦点
        dialogWindow.setAttributes(layoutParams);
    }

    private void resetWidthHeight() {
        LogUtil.d("[CallStatusBarDialog.resetWidthHeight]");
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        if (title == null) {
            LogUtil.d("CallStatusBarDialog title null");
            layoutParams.x = 0;
        } else {
            int[] showLocation = new int[2];
            title.getLocationOnScreen(showLocation);
            LogUtil.d("showLocation[0]=" + showLocation[0] + ", showLocation[1]=" + showLocation[1]+", height="+title.getHeight());
            layoutParams.x = showLocation[0];
            layoutParams.y = title.getHeight();
            if (showLocation[1] == 0) {//沉浸状态栏，标题要减去状态栏高度
               /* if (mContext instanceof Activity) {
                    layoutParams.y = layoutParams.y - ScreenUtil.getStatusBarHeight((Activity) mContext);
                    LogUtil.d("layoutParams.y="+layoutParams.y);
                    if (layoutParams.y < 0) {
                        layoutParams.y = 0;
                    }
                }*/
            }
        }
        dialogWindow.setAttributes(layoutParams);
    }


    @Override
    public void show() {
//        LogUtil.d("CallUtil.getCallState="+CallUtil.getCallState());
//        if (!isShowing()) {
//            LogUtil.d("CallStatusBarDialog show dialog");
//            if (CallUtil.getCallState() == CallConstant.CALL_STATUS_CALLING) {
                super.show();
//            }
//        } else {
//            LogUtil.d("CallStatusBarDialog dialog isShowing");
//            if (CallUtil.getCallState() != CallConstant.CALL_STATUS_CALLING) {
//                super.dismiss();
//            }
//        }
    }
}
