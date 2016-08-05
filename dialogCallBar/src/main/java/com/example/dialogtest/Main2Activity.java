package com.example.dialogtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Main2Activity extends AppCompatActivity {
//    CallStatusBarDialog callStatusBarDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        if (MainActivity.showDialog) {
            MainActivity.showDialog = false;
        } else {
            MainActivity.showDialog = true;
        }
//        callStatusBarDialog = new CallStatusBarDialog(this, null);
        CallStatusBarManager.getInstance().addCallStatusBar(this, null);

        AppUtil.getActivityShowing(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
//        CallStatusBarUtil.showCallStatusBar(this, null);
//        callStatusBarDialog.show();
//        CallStatusBarManager.getInstance().updateVisibleCallStatusBar(this);
        CallStatusBarManager.getInstance().updateVisibleCallStatusBar();

    }

    public void back(View view) {
        finish();
    }
}
