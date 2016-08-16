package cn.com.ecarx.lifecycle_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i("yinxm", "static_num="+LifecycleActivity.static_num);
    }

    public void gotoLifecycleActivity(View view) {
        this.startActivity(new Intent(this, LifecycleActivity.class));
    }
}
