package com.yinxm.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

public class PopupWindowActivity extends AppCompatActivity {
    Button btn_showWindow, btn_dismissWindow;
    PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);

        btn_showWindow = (Button) findViewById(R.id.btn_showWindow);
        btn_dismissWindow = (Button) findViewById(R.id.btn_dismissWindow);

        btn_showWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                popupWindow.showAsDropDown(v);//显示在控件下方
                popupWindow.showAtLocation(findViewById(R.id.layout_main), Gravity.BOTTOM, 0, 0);
            }
        });

        btn_dismissWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });

        View content = LayoutInflater.from(this).inflate(R.layout.dialog_speech_input, null);
        popupWindow = new PopupWindow(content, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        popupWindow.showAtLocation(findViewById(R.id.layout_main), Gravity.BOTTOM, 0, 0);//  Caused by: android.view.WindowManager$BadTokenException: Unable to add window -- token null is not valid; is your activity running?
//
//    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            popupWindow.showAtLocation(findViewById(R.id.layout_main), Gravity.BOTTOM, 0, 0);
        }
    }

    public void showPopupWindow() {

    }
}
