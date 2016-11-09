package cn.yinxm.activityrebuildtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import cn.yinxm.lib.utils.LogUtil;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ((TextView)findViewById(R.id.tv)).setText(""+this);

    }

    public void openMain(View view) {
        startActivity(new Intent(this, Main5Activity.class));
        LogUtil.d("Main4Activity = "+this+", open Main5Activity");
    }

    public void finishThis(View view) {
        finish();
    }
}
