package com.yinxm.thread_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {
    private int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("[Main2Activity] num="+num++);
                }
            }
        }).start();
    }

    public void backBtn(View view) {
        finish();
    }
}
