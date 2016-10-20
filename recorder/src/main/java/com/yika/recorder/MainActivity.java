package com.yika.recorder;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.yika.recorder.ecarx.EcarxRecorder;

import cn.yinxm.lib.activity.BaseActivity;
import cn.yinxm.lib.media.AudioPlayHelper;
import cn.yinxm.lib.permission.PermissionCheck;
import cn.yinxm.lib.permission.PermissionGrantResult;
import cn.yinxm.lib.utils.LogUtil;

public class MainActivity extends BaseActivity {
    EaseVoiceRecorder recorder;
    EcarxRecorder ecarxAudioManager;
    TextView tv;
    protected Handler micImageHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            // 切换msg切换图片
            LogUtil.d("pic="+msg.what);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);

        recorder = new EaseVoiceRecorder(micImageHandler);
        PathUtil.getInstance().initDirs(null, "temp", this);

        //ecarx录音
        // 这里没有判断储存卡是否存在，有空要判断
        String dir =  null;
        try {
            dir = Environment.getExternalStorageDirectory()
                    + "/nickming_recorder_audios";
        } catch (Exception e) {
            LogUtil.e("获取dir异常",e);
        }
//        ecarxAudioManager = EcarxRecorder.getInstance(this.getFilesDir().getAbsolutePath());
        ecarxAudioManager = new EcarxRecorder(this, dir);
    }

    public void recordStart(View view) {
        checkDangerPermissions(new PermissionCheck(Manifest.permission.READ_EXTERNAL_STORAGE, new PermissionGrantResult() {
            @Override
            public void permissionGranted(String permission) {
                LogUtil.d("获取到SD卡权限，开始获取录音权限");
                checkDangerPermissions(new PermissionCheck(Manifest.permission.RECORD_AUDIO, new PermissionGrantResult() {
                    @Override
                    public void permissionGranted(String permission) {
                        LogUtil.d("获取到录音权限，开始录音");
//                        recorder.startRecording(MainActivity.this);
                        ecarxAudioManager.startRecord();

                    }
                }));
            }
        }));

    }

    public void recordStop(View view) {
//        recorder.stopRecoding();
//        LogUtil.i("录音文件"+recorder.getVoiceFilePath());
//        tv.setText(recorder.getVoiceFilePath());
        ecarxAudioManager.stopRecord();
        tv.setText(ecarxAudioManager.getRecordFilePath());

    }

    public void play(View view) {
        AudioPlayHelper playHelper = new AudioPlayHelper();
//        playHelper.play(recorder.getVoiceFilePath());
        playHelper.play(ecarxAudioManager.getRecordFilePath());
        LogUtil.i("录音文件2="+ ecarxAudioManager.getRecordFilePath());

    }


}
