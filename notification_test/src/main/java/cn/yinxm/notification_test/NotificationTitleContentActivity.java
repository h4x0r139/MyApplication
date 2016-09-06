package cn.yinxm.notification_test;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class NotificationTitleContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_title_content);

        initView();
    }

    private void initView() {
        final Button showNotify = (Button) findViewById(R.id.showNotify);
        showNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotify();
            }
        });
    }

    private void showNotify() {
        NotificationManager notifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder notifiCompatBuilder = new NotificationCompat.Builder(this);
        Notification notification = notifiCompatBuilder.setContentTitle("这是标题").setContentText("这是内容").
                setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .build();
        notifyManager.notify(R.id.notify_play, notification);
    }
}
