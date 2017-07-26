package cn.com.ecarx.lifecycle_activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * SingleInstace的Activity 独自占用一个Activity栈
 * 按home键退到后台，重新进入，也会走到主界面
 */
public class SingleInstanceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_instance);

        findViewById(R.id.btn_openMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SingleInstanceActivity.this, MainActivity.class));
            }
        });
    }
}
