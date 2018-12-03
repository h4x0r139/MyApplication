package com.h4x0r.layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mcxtzhang.constraintlayoutdemo.Main2Activity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickConstraint(View view) {
        startActivity(new Intent(MainActivity.this, ConstraintLayoutActivity.class));
    }

    public void clickOtherCL(View view) {
        startActivity(new Intent(MainActivity.this, Main2Activity.class));
    }
}
