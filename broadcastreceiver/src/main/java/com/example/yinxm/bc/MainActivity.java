package com.example.yinxm.bc;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean isInitLocalBroadcast = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initLocalBroadcast() {
        IntentFilter intentFilter = new IntentFilter(CustomLocalBroadcastReceiver.ACTION);
        BroadcastReceiver receiver = new CustomLocalBroadcastReceiver();
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(receiver, intentFilter);
        isInitLocalBroadcast = true;
    }

    public void clickLocalBroadcast(View view) {
        if (!isInitLocalBroadcast) {
            initLocalBroadcast();
        }

        // 发送
        Intent intent = new Intent(CustomLocalBroadcastReceiver.ACTION);
//        intent.addCategory("test");
        intent.putExtra("key", "testValue");
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
        Toast.makeText(getApplicationContext(), "发送本地广播成功", Toast.LENGTH_SHORT).show();
    }

    public void clickDynamicBroadcast(View view) {
        startActivity(new Intent(MainActivity.this, TestBC2Activity.class));
    }

    public void clickThreadBroadcast(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                sendBroadcast(new Intent(Intent.ACTION_EDIT));
            }
        }).start();
    }
}
