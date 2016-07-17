package com.example.yinxm.loginnavigation;


import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

/**
 * Created by yinxm on 2015/12/7.
 */
public class TitleBar {
    private static Activity mActivity;

    /**
     * @see [自定义标题栏]
     * @param activity
     * @param title
     */
    public static void getTitleBar(Activity activity,String title) {
        mActivity = activity;
        activity.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        activity.setContentView(R.layout.titlebar2);
        activity.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                R.layout.titlebar2);


    }

    public void backBtnOnClick(View view) {
//        TextView textView = (TextView) activity.findViewById(R.id.head_center_text);
//        textView.setText(title);

        KeyEvent newEvent = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK);
        mActivity.onKeyDown(KeyEvent.KEYCODE_BACK, newEvent);

    }
}
