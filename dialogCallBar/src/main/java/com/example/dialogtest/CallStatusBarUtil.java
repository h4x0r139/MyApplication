package com.example.dialogtest;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by yinxm on 2016/7/18.
 * 通话状态栏
 */
public class CallStatusBarUtil {
    private CallStatusBarUtil() {
    }
    private static Context mContext;
    private static Dialog mDialog;

    private static void initDialog(final Context context, View title) {
        LogUtil.d("[CallStatusBarUtil.initDialog]");
        mDialog = new Dialog(context, R.style.BaseCustomDialog);
//            dialog.setCancelable(false);//点击dialog外区域不关闭
//            dialog.setCanceledOnTouchOutside(false);
        View dialogContentView = LayoutInflater.from(context).inflate(R.layout.call_status_bar_layout, null);
//        dialog.setContentView(R.layout.call_status_bar_layout);
        mDialog.setContentView(dialogContentView);
        dialogContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("[CallStatusBarUtil.onclick]");
                Toast.makeText(context, "点击dialog", Toast.LENGTH_SHORT).show();
            }
        });

        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(Gravity.TOP);
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        if (title == null) {
            layoutParams.x = 0;
        } else {
            int[] showLocation = new int[2];
            title.getLocationOnScreen(showLocation);
            LogUtil.d("showLocation[0]=" + showLocation[0] + ", showLocation[1]=" + showLocation[1]);
            layoutParams.x = showLocation[0];
            layoutParams.y = showLocation[1];
        }
//        layoutParams.y = getStatusBarHeight() + getActionBarHeight();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;//不获取屏幕焦点
        dialogWindow.setAttributes(layoutParams);
    }


    public static void showCallStatusBar(final Context context, View title) {
        LogUtil.d("[CallStatusBarUtil.showCallStatusBar] context="+context);
        if (mDialog == null || mContext == null) {
            initDialog(context, title);
        }
        if (!mDialog.isShowing()) {
            LogUtil.d("CallStatusBarUtil show dialog");
            if (MainActivity.showDialog) {
                mDialog.show();
            }
        } else {
            LogUtil.d("CallStatusBarUtil dialog isShowing");
            if (!MainActivity.showDialog) {
                dismissCallStatusBar(mDialog);
            }
        }
    }

    private static void dismissCallStatusBar(Dialog dialog) {
        LogUtil.d("[CallStatusBarUtil.dismissCallStatusBar]");
        if (dialog != null && dialog.isShowing()) {
            LogUtil.d("CallStatusBarUtil dismiss dialog");
            dialog.dismiss();
        }
    }
}
