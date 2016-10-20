package com.yika.recorder;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.SystemClock;
import android.text.format.Time;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import cn.yinxm.lib.utils.LogUtil;

public class EaseVoiceRecorder {
    MediaRecorder recorder;

    static final String PREFIX = "voice";
    static final String EXTENSION = ".amr";

    private boolean isRecording = false;
    private long startTime;
    private String voiceFilePath = null;
    private String voiceFileName = null;
    private File file;
    private Handler handler;

    public EaseVoiceRecorder(Handler handler) {
        this.handler = handler;
    }

    /**
     * start recording to the file
     */
    public String startRecording(Context context) {
        file = null;
        try {
            // need to create recorder every time, otherwise, will got exception
            // from setOutputFile when try to reuse
            if (recorder != null) {
                LogUtil.d("startRecording recorder="+recorder);
                recorder.release();
                recorder = null;
            }
            recorder = new MediaRecorder();
            LogUtil.d("recorder="+recorder);
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            recorder.setAudioChannels(1); // MONO
            recorder.setAudioSamplingRate(8000); // 8000Hz
            recorder.setAudioEncodingBitRate(64); // seems if change this to
                                                    // 128, still got same file
                                                    // size.
            // one easy way is to use temp file
            // file = File.createTempFile(PREFIX + userId, EXTENSION,
            // User.getVoicePath());
            voiceFileName = getVoiceFileName("1709721");
            LogUtil.i("voiceFileName="+voiceFileName);

//            voiceFilePath = PathUtil.getInstance().getVoicePath() + "/" + voiceFileName;
            //file=/storage/emulated/0/Android/data/com.hyphenate.chatuidemo/easemob-demo#chatdemoui/yinxm/voice/yinxm20161014T204805.amr
//            file = new File(voiceFilePath);
//            file = new File(appContext.getFilesDir(), voiceFileName);

//            file = File.createTempFile(voiceFileName, null, context.getCacheDir());
            file = File.createTempFile("1709721", EXTENSION, context.getFilesDir());
            voiceFilePath = file.getAbsolutePath();
            LogUtil.i("voiceFilePath="+voiceFilePath);

            if (!file.exists()) {
                LogUtil.i("文件路径不存在创建");
                file.mkdirs();
                file.createNewFile();
            }
            recorder.setOutputFile(file.getAbsolutePath());
            recorder.prepare();
            isRecording = true;
            recorder.start();
        } catch (IOException e) {
            LogUtil.e("prepare() failed",e);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isRecording) {
                        android.os.Message msg = new android.os.Message();
                        msg.what = recorder.getMaxAmplitude() * 13 / 0x7FFF;
                        handler.sendMessage(msg);
                        SystemClock.sleep(100);
                    }
                } catch (Exception e) {
                    // from the crash report website, found one NPE crash from
                    // one android 4.0.4 htc phone
                    // maybe handler is null for some reason
                    LogUtil.e("录音异常", e);
                }
            }
        }).start();
        startTime = new Date().getTime();
        LogUtil.d("start voice recording to file:" + file.getAbsolutePath());
        return file == null ? null : file.getAbsolutePath();
    }

    /**
     * stop the recoding
     * 取消录音
     * 
     * @return seconds of the voice recorded
     */
    public void discardRecording() {
        if (recorder != null) {
            try {
                recorder.stop();
                recorder.release();
                recorder = null;
                if (file != null && file.exists() && !file.isDirectory()) {
                    file.delete();
                }
            } catch (IllegalStateException e) {
            } catch (RuntimeException e){}
            isRecording = false;
        }
    }

    public int stopRecoding() {
        LogUtil.e("stopRecoding recorder="+recorder);
        if(recorder != null){
            isRecording = false;
            recorder.stop();
            recorder.release();
            recorder = null;
            
            if(file == null || !file.exists() || !file.isFile()){
                return 1;
            }
            if (file.length() == 0) {
                file.delete();
                return 1;
            }
            int seconds = (int) (new Date().getTime() - startTime) / 1000;
            LogUtil.d("voice recording finished. seconds:" + seconds + " file length:" + file.length());
            return seconds;
        }
        return 0;
    }

    protected void finalize() throws Throwable {
        super.finalize();
        if (recorder != null) {
            recorder.release();
        }
    }

    private String getVoiceFileName(String uid) {
        Time now = new Time();
        now.setToNow();
        return uid + now.toString().substring(0, 15) + EXTENSION;
    }

    public boolean isRecording() {
        return isRecording;
    }

    
    public String getVoiceFilePath() {
        return voiceFilePath;
    }
    
    public String getVoiceFileName() {
        return voiceFileName;
    }
}
