package com.yika.intent_test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSecondActivityClick(View view) {
        //显示启动1
//        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        Intent intent = new Intent();
        char c = '我';
        intent.setClassName("com.yika.intent_test", "com.yika.intent_test.Main2Activity");
        startActivity(intent);
    }

    private void startSecondActivityImplicitClick(View view) {
        Log.d("my", "view=" + ((Button) view).getText());
        Intent intent = new Intent();
        intent.setAction("com.yika.intent_test.ACTION_START");//会用默认的category
        startActivity(intent);
    }

    public void startThirdActivityClick(View v) {
        Intent intent = new Intent("com.yika.intent_test.c");
        intent.putExtra("time", System.currentTimeMillis());
        //如果多次调用intent.addCategory，必须每个都能和<intent-filter>中的<category>匹配上。
        intent.addCategory("com.yika.category.c");
//        intent.addCategory("com.yika.category.m");//匹配不上

        intent.setDataAndType(Uri.parse("file://abc"), "text/plain");
        startActivity(intent);
    }


}
