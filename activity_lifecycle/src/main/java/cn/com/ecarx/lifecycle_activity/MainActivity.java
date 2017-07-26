package cn.com.ecarx.lifecycle_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button singleInstance, singleTask, singleTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        singleInstance = (Button) findViewById(R.id.singleInstance);
        singleTask = (Button) findViewById(R.id.singleTask);
        singleTop = (Button) findViewById(R.id.singleTop);
        singleInstance.setOnClickListener(this);
        singleTask.setOnClickListener(this);
        singleTop.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i("yinxm", "static_num="+LifecycleActivity.static_num);
    }

    public void gotoLifecycleActivity(View view) {
        this.startActivity(new Intent(this, LifecycleActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.singleInstance:
                startActivity(new Intent(MainActivity.this, SingleInstanceActivity.class));
                break;
            case R.id.singleTask:
                startActivity(new Intent(MainActivity.this, SingleTaskActivity.class));
                break;
            case R.id.singleTop:
                startActivity(new Intent(MainActivity.this, SingleTopActivity.class));
                break;
        }
    }
}
