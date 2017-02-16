package cn.yinxm.activityrebuildtest;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView)findViewById(R.id.tv)).setText(""+this);
    }

    public void openMain2(View view) {
        startActivity(new Intent(this, Main2Activity.class));
    }

    public void openMainNewApp(View view) {
        //打开新的应用
//        ComponentName componentName = new ComponentName("com.yika.recorder", "com.yika.recorder.MainActivity");
        ComponentName componentName = new ComponentName("cn.com.ecarx.xiaoka.carmusic", "cn.com.ecarx.xiaoka.carmusic.activity.AudioPlayActivity");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        startActivity(intent);
    }
}
