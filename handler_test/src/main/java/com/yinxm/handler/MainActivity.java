package com.yinxm.handler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handlerClick(View view) {
        startActivity(new Intent(MainActivity.this, HandlerActivity.class));
    }

    public void handlerThreadClick(View view) {
        startActivity(new Intent(MainActivity.this, HandlerThreadActivity.class));
    }
}
