package cn.yinxm.permission_req;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.yinxm.lib.media.AudioPlayHelper;
import cn.yinxm.lib.media.EcarxRecorder;
import cn.yinxm.lib.utils.LogUtil;

public class MainActivity extends AppCompatActivity {
    Button recordBtn;
    EcarxRecorder recorder;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recorder = new EcarxRecorder(getApplicationContext(), this.getFilesDir().getAbsolutePath());
        setContentView(R.layout.activity_main);
        recordBtn = (Button) findViewById(R.id.btn);
        textView = (TextView) findViewById(R.id.text);
        recordBtn.setText("开始录音");
        recordBtn.setTag(1);
        recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tagTemp = (int) recordBtn.getTag();
                LogUtil.d("tagTemp="+tagTemp);
                if (tagTemp == 1) {
                    LogUtil.d("permission="+ PermissionUtil.checkPermission(MainActivity.this, Manifest.permission.RECORD_AUDIO));
                    //start
                    recorder.startRecord();
                    recordBtn.setTag(0);
                    recordBtn.setText("停止录音");
                } else {
                    //stop
                    recorder.stopRecord();
                    textView.setText(recorder.getRecordFilePath());
                    recordBtn.setTag(1);
                    recordBtn.setText("开始录音");
                }

            }
        });
    }

    public void play(View view) {
        AudioPlayHelper playHelper = new AudioPlayHelper();
        playHelper.play(recorder.getRecordFilePath());
    }
}
