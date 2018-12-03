package com.yinxm.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by yinxm on 2016/7/27.
 */
public class BaseCustomDialog extends Dialog{

    Button leftButton, centerButton, rightButton;
    TextView tv1,tv2,tv3;
    ViewGroup one_button_layout, two_button_layout;

    private boolean isOnCreate = false;

    public BaseCustomDialog(Context context) {
        super(context, R.style.BaseCustomDialog);
    }

    public BaseCustomDialog(Context context, int themeResId) {
        super(context, themeResId);
        onCreate(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_custom_dialog);
        one_button_layout = (ViewGroup) findViewById(R.id.one_button_layout);
        two_button_layout = (ViewGroup) findViewById(R.id.two_button_layout);

        leftButton = (Button) findViewById(R.id.leftButton);
        rightButton = (Button) findViewById(R.id.rightButton);
        centerButton = (Button) findViewById(R.id.centerButton);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }


    public TextView getTv1() {
        return tv1;
    }

    public void setTv1(TextView tv1) {
        this.tv1 = tv1;
    }

    public TextView getTv2() {
        return tv2;
    }

    public void setTv2(TextView tv2) {
        this.tv2 = tv2;
    }

    public TextView getTv3() {
        return tv3;
    }

    public void setTv3(TextView tv3) {
        this.tv3 = tv3;
    }

    public ViewGroup getOne_button_layout() {
        return one_button_layout;
    }

    public ViewGroup getTwo_button_layout() {
        return two_button_layout;
    }

    public Button getLeftButton() {
        return leftButton;
    }

    public Button getCenterButton() {
        return centerButton;
    }

    public Button getRightButton() {
        return rightButton;
    }
}
