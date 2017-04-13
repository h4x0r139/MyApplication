package cn.yinxm.util.time;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Intent.ACTION_DATE_CHANGED;
//        Intent.ACTION_TIME_CHANGED;
//        Intent.ACTION_TIME_TICK;
//        Intent.ACTION_TIMEZONE_CHANGED;
//        Manifest.permission.SET_TIME;
//        Manifest.permission.SET_TIME_ZONE;

//        TimeChangeReceiver timeChangeReceiver = new TimeChangeReceiver();
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(Intent.ACTION_DATE_CHANGED);
//        intentFilter.addAction(Intent.ACTION_TIME_CHANGED);
////        intentFilter.addAction(Intent.ACTION_TIME_TICK);//监听时间走动，每隔1分钟
//        intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
//        registerReceiver(timeChangeReceiver,intentFilter);
    }
}
