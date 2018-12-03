package com.example.clickevent_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    LinearLayout outId, inId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        outId = (LinearLayout) findViewById(R.id.outId);
        inId = (LinearLayout) findViewById(R.id.inId);
        outId.setOnClickListener(this);
        inId.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.d("yinxm", "onClick id="+v.getId());
        switch (v.getId()) {
            case R.id.outId:
                Toast.makeText(this, "out click", Toast.LENGTH_SHORT).show();
                break;
            case R.id.inId:
                Toast.makeText(this, "in click", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
