package cn.yinxm.notification_test;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class PlayNotifiActivity extends AppCompatActivity {
    MusicNotification musicNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_notifi);
        initNotifi();
    }

    public void initNotifi() {
        musicNotification = new MusicNotification(this, (NotificationManager) getSystemService(NOTIFICATION_SERVICE));
    }

    public void showPlayNotify(View view) {
//        musicNotification.onUpdataMusicNotifi("hello", "hehe", "", true);
        startService(new Intent(this,AudioPlayService.class));

    }

    public void back(View view) {
        finish();
    }
}
