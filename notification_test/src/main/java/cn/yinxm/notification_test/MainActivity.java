package cn.yinxm.notification_test;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.listView);
        String[] items = {"Notification test 标题内容", "测试自定义播放通知栏","测试自定义Layout点击"};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, NotificationTitleContentActivity.class));
                        break;
                    case 1:
//                        showPlayNotify();
//                        showMusicNotifi();
//                        sendBroadcast(new Intent(BroadcastReceiverConstant.ACTION_PLAY_BUTTON));
                        startActivity(new Intent(MainActivity.this, PlayNotifiActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void showPlayNotify() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_play_notifi);
        remoteViews.setTextViewText(R.id.tv_title,"七里香");
        remoteViews.setTextViewText(R.id.tv_artist,"周杰伦");

//        mBuilder.setContent(mRemoteViews)
//                .setContentIntent(getDefalutIntent(Notification.FLAG_ONGOING_EVENT))
//                .setWhen(System.currentTimeMillis())// 通知产生的时间，会在通知信息里显示
//                .setTicker("正在播放")
//                .setPriority(Notification.PRIORITY_DEFAULT)// 设置该通知优先级
//                .setOngoing(true)
//                .setSmallIcon(R.drawable.sing_icon);
//        Notification notify = mBuilder.build();
//        notify.flags = Notification.FLAG_ONGOING_EVENT;
//
//        mNotificationManager.notify(200, notify);

        Notification notify =  builder.setContent(remoteViews)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .build();
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(200, notify);

    }

    public void showMusicNotifi() {
        MusicNotification musicNotification = new MusicNotification(this,
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE));
        musicNotification.onUpdataMusicNotifi("忘情水忘情水忘情水忘情水", "刘德华", "", true);
    }
}
