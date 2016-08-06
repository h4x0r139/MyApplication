package cn.com.ecarx.lifecycle_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

public class LifecycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("yinxm", "LifecycleActivity.onCreate savedInstanceState="+savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        final View img = findViewById(R.id.myImg);
        img.addOnLayoutChangeListener(new View.OnLayoutChangeListener(){
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                Log.i("yinxm", "LifecycleActivity.onCreate img onLayoutChange");
            }
        });

        img.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                img.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                Log.i("yinxm", "LifecycleActivity.onCreate img onGlobalLayout");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("yinxm", "LifecycleActivity.onStart ");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("yinxm", "LifecycleActivity.onRestart ");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("yinxm", "LifecycleActivity.onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("yinxm", "LifecycleActivity.onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("yinxm", "LifecycleActivity.onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("yinxm", "LifecycleActivity.onDestroy");

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.i("yinxm", "LifecycleActivity.onWindowFocusChanged hasFocus="+hasFocus);
    }

    public void back(View view) {
        finish();
    }

    public void openNew(View view) {
        this.startActivity(new Intent(this, MainActivity.class));
    }
}
