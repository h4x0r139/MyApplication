package com.yinxm.ui;

import android.app.Activity;
import android.view.View;

/**
 * Created by yinxm on 2016/7/27.
 */
public class BaseCustomDialogManager {
    private BaseCustomDialogManager() {
    }

    public static BaseCustomDialogManager getInstance() {
        return CallStatusBarManagerFactory.instance;
    }

    private static class CallStatusBarManagerFactory {
        private static BaseCustomDialogManager instance = new BaseCustomDialogManager();
    }

    /**
     *
     * @param activity
     * @param buttonOnClickListener 只有一个按钮的对话框,为按钮添加事件
     * @param toast 对话框提示信息，最多支持3行
     * @return
     */
    public static BaseCustomDialog getDialog(Activity activity, View.OnClickListener buttonOnClickListener,  String... toast) {
        return  getDialog(activity, buttonOnClickListener, null, toast);
    };

    /**
     *
     * @param activity
     * @param leftButtonOnClickListener 左按钮点击事件
     * @param rightButtonOnClickListener 右按钮点击事件
     * @param toast  对话框提示信息，最多支持3行
     * @return
     */
    public static BaseCustomDialog getDialog(Activity activity, View.OnClickListener leftButtonOnClickListener,
                                             View.OnClickListener rightButtonOnClickListener, String... toast) {
        BaseCustomDialog customDialog = new BaseCustomDialog(activity);

        //Dialog 按钮添加事件
        if (leftButtonOnClickListener != null && rightButtonOnClickListener != null) {
            //显示两个按钮 取消、确认
            customDialog.getTwo_button_layout().setVisibility(View.VISIBLE);
            customDialog.getLeftButton().setOnClickListener(leftButtonOnClickListener);
            customDialog.getRightButton().setOnClickListener(rightButtonOnClickListener);
        } else {
            //显示1个按钮确认
            customDialog.getOne_button_layout().setVisibility(View.VISIBLE);
            if (leftButtonOnClickListener != null) {
                customDialog.getOne_button_layout().setOnClickListener(leftButtonOnClickListener);
            } else if (rightButtonOnClickListener != null) {
                customDialog.getOne_button_layout().setOnClickListener(rightButtonOnClickListener);
            }
        }

        //设置Dialog提示信息
        if (toast != null && toast.length > 0 ) {
            switch (toast.length) {
                case 3:
                    customDialog.getTv3().setVisibility(View.VISIBLE);
                    customDialog.getTv3().setText(toast[2]);
                case 2:
                    customDialog.getTv2().setVisibility(View.VISIBLE);
                    customDialog.getTv2().setText(toast[1]);
                case 1:
                    customDialog.getTv1().setVisibility(View.VISIBLE);
                    customDialog.getTv1().setText(toast[0]);
                    break;
            }
        }

        return  customDialog;
    };




}
