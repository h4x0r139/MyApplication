package com.yinxuming.dragview_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CustomDragViewActivity extends AppCompatActivity {

    private CustomDragView customDragView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_drag_view);

        customDragView = (CustomDragView) findViewById(R.id.customDragView);
    }
}
