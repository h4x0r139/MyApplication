package cn.com.ecarx.lifecycle_activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.yinxm.lib.utils.log.LogUtil;

/**
 * 1、onNewIntent接收参数问题：需要setIntent，否则获取到的intent还是原来的
 * 2、startActivityForResult不执行问题:从A跳转到B，再回到A时，使用startActivityForResult时，如果B设置为SingleTask，将会出现，一调用完毕startActivityForResult
 * 立即回调onActivityResult，并且resultCode=0（RESULT_CANCELED） ,intent=null，当B调用setResult时，不会再回调onActivityResult
 */
public class SingleTaskActivity extends Activity {
    public  static final String INTENT_KEY1 = "test1";
    public  static final int REQUEST_CODE = 1;


    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String value = intent.getStringExtra(INTENT_KEY1);
        LogUtil.d("----SingleTaskActivity.onCreate----"+intent+", intent.hashcode="+intent.hashCode()+", value="+value);
        setContentView(R.layout.activity_single_task);

        tv = (TextView) findViewById(R.id.tv);

        findViewById(R.id.btn_openMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SingleTaskActivity.this, MainActivity.class));
            }
        });
        findViewById(R.id.btnOpenSelf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SingleTaskActivity.this, SingleTaskActivity.class));
            }
        });
        findViewById(R.id.btnResult).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleTaskActivity.this, ResultActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        processIntent();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String value = intent.getStringExtra(INTENT_KEY1);
        LogUtil.d("----SingleTaskActivity.onNewIntent----intent="+intent+", intent.hashcode="+intent.hashCode()+", value="+value);
        tv.setText("intent.hashcode="+intent.hashCode()+", value="+value);
        setIntent(intent);//如果不再onNewIntent里面setIntent，这里获取到的Intent任然是以前的intent
        processIntent();
    }

    private void processIntent() {
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.d("----SingleTaskActivity.onRestart----");

    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d("----SingleTaskActivity.onStart----");

    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d("----SingleTaskActivity.onResume----");
        final Intent intent = getIntent();//如果不再onNewIntent里面setIntent，这里获取到的Intent任然是以前的intent
        final String value = intent.getStringExtra(INTENT_KEY1);

        LogUtil.d("intent.hashcode="+intent.hashCode()+", value="+value);
        tv.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv.setText("intent.hashcode="+intent.hashCode()+", value="+value);
            }
        },1000);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.d("----SingleTaskActivity.onActivityResult----requestCode="+requestCode+", resultCode="+resultCode+" ,intent="+data);
        if (data != null) {
            String result = data.getStringExtra(ResultActivity.INTENT_KEY_RESULT);
            LogUtil.d("onActivityResult.result="+result);
        }

    }
}
