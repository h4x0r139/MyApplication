package com.recorder;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import cn.yinxm.lib.activity.BaseActivity;
import cn.yinxm.lib.media.AppRecorder;
import cn.yinxm.lib.media.AudioPlayHelper;
import cn.yinxm.lib.permission.PermissionCheck;
import cn.yinxm.lib.permission.PermissionGrantResult;
import cn.yinxm.lib.utils.StringUtil;
import cn.yinxm.lib.utils.log.LogUtil;

public class MainActivity extends BaseActivity {
    EaseVoiceRecorder recorder;
    AppRecorder workEcAudioManager;
    TextView tv;
    RadioGroup rg;
    int radioCheckedId = -1;
    String recordFilePath;
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
        rg = (RadioGroup) findViewById(R.id.rg);


        recorder = new EaseVoiceRecorder(micImageHandler);
        PathUtil.getInstance().initDirs(null, "temp", this);

        //workEc录音
        // 这里没有判断储存卡是否存在，有空要判断
        String dir =  null;
        try {
            dir = Environment.getExternalStorageDirectory()
                    + "/nickming_recorder_audios";
        } catch (Exception e) {
            LogUtil.e("获取dir异常",e);
        }
//        workEcAudioManager = AppRecorder.getInstance(this.getFilesDir().getAbsolutePath());
        workEcAudioManager = new AppRecorder(this, dir);
    }

    public void recordStart(View view) {
       radioCheckedId =  rg.getCheckedRadioButtonId();
        LogUtil.d("MainActivity.recordStart checkedId="+radioCheckedId);
        if (radioCheckedId != -1) {
            switch (radioCheckedId) {
                case R.id.rb_workEc:
                    LogUtil.d("WorkEc录音 ");
                    checkDangerPermissions(new PermissionCheck(Manifest.permission.READ_EXTERNAL_STORAGE, new PermissionGrantResult() {
                        @Override
                        public void permissionGranted(String permission) {
                            LogUtil.d("获取到SD卡权限，开始获取录音权限");
                            checkDangerPermissions(new PermissionCheck(Manifest.permission.RECORD_AUDIO, new PermissionGrantResult() {
                                @Override
                                public void permissionGranted(String permission) {
                                    LogUtil.d("获取到录音权限，开始录音");
//                        recorder.startRecording(MainActivity.this);
                                    workEcAudioManager.startRecord();

                                }
                            }));
                        }
                    }));
                    break;
                case R.id.rb_ease:
                    LogUtil.d("Ease录音 ");
                    recorder.startRecording(this);
                    break;
                default:
                    break;

            }
        }

    }



    public void recordStop(View view) {
//        recorder.stopRecoding();
//        LogUtil.i("录音文件"+recorder.getVoiceFilePath());
//        tv.setText(recorder.getVoiceFilePath());

        LogUtil.d("MainActivity.recordStop radioCheckedId="+radioCheckedId);
        if (radioCheckedId != -1) {
            switch (radioCheckedId) {
                case R.id.rb_workEc:
                    LogUtil.d("WorkEc停止录音 ");
                    workEcAudioManager.stopRecord();
                    recordFilePath = workEcAudioManager.getRecordFilePath();
                    break;
                case R.id.rb_ease:
                    LogUtil.d("Ease停止录音 ");
                    recorder.stopRecoding();
                    recordFilePath = recorder.getVoiceFilePath();
                    break;
                default:
                    break;

            }
            LogUtil.d("recordFilePath="+recordFilePath);
            tv.setText(recordFilePath);
        }


    }

    public void play(View view) {
        AudioPlayHelper playHelper = new AudioPlayHelper();
//        playHelper.play(recorder.getVoiceFilePath());
        LogUtil.d("recordFilePath="+recordFilePath);
        if (StringUtil.isNotBlank(recordFilePath)) {
            playHelper.play(recordFilePath);
        }

    }


}
