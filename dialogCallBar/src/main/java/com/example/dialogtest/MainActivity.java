package com.example.dialogtest;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static boolean showDialog = true;
//    CallStatusBarDialog callStatusBarDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        callStatusBarDialog = new CallStatusBarDialog(this, null);
        CallStatusBarManager.getInstance().addCallStatusBar(this, null);
        AppUtil.getActivityShowing(this);
    }

    public void showDialog(View view) {
        Log.d("yika", "[dialog.showDialog]");
//       CallStatusBarUtil.showCallStatusBar(this, null);
        CallStatusBarManager.getInstance().updateVisibleCallStatusBar(this);
    }
    public void showDialog2(View view) {
        Log.d("yika", "[dialog.showDialog2]");
        Toast.makeText(this, "点击测试", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("yika", "[dialog.onResume] showDialog="+showDialog);
//        CallStatusBarUtil.showCallStatusBar(this, null);
//        callStatusBarDialog.show();
//        CallStatusBarManager.getInstance().updateVisibleCallStatusBar(this);
        CallStatusBarManager.getInstance().updateVisibleCallStatusBar();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("yika", "[dialog.onPause]");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.d("yika", "[dialog.onWindowFocusChanged]");
    }

    public void gotoNewActivity(View view) {
        startActivity(new Intent(this, Main2Activity.class));
    }

    public  void checkActivityRunning() {
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        Log.d("", "pkg:"+cn.getPackageName());
        Log.d("", "cls:"+cn.getClassName());
    }
}
