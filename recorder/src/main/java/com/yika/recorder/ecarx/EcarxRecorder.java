package com.yika.recorder.ecarx;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.SystemClock;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import cn.yinxm.lib.utils.LogUtil;
import cn.yinxm.lib.utils.StringUtil;


/**
 * 录音
 * @author yinxm
 */
public class EcarxRecorder {
    private MediaRecorder mRecorder;
    private File file;
    private String recordFileDir;
    private String recordFilePath;
    private long startTime;
    private boolean isRecording = false;// 是否正在录音

    public AudioStageListener mListener;
    private Handler handler;//声音大小回调

    public EcarxRecorder(Context context, String fileDir) {
        this(context,fileDir, null);
    }

    public EcarxRecorder(Context context, String fileDir, Handler handler) {
        isRecording = false;
        if (StringUtil.isBlank(fileDir)) {
            fileDir = context.getFilesDir().getAbsolutePath();
        }
        LogUtil.d("fileDir="+fileDir);
        this.recordFileDir = fileDir;

        File dir = new File(recordFileDir);
        if (dir == null) {
            return;
        }
        if (!dir.exists()) {
            dir.mkdirs();
        }
        LogUtil.d("dir="+dir);
        if (handler != null) {
            this.handler = handler;
        }
    }

    public void setVoiceLevelHanlder (Handler handler) {
        if (handler != null) {
            this.handler = handler;
        }
    }

    public void startRecord() {
        if (isRecording) {//避免重复调用异常
            LogUtil.e("目前已经正在录音isRecording="+isRecording);
            return;
        }
        LogUtil.i("startRecord recordFileDir="+recordFileDir);

        if (StringUtil.isBlank(recordFileDir)) {
            return;
        }
        try {
            String fileNameString = generalFileName();
            file = new File(recordFileDir, fileNameString);

            recordFilePath = file.getAbsolutePath();
            LogUtil.i("startRecord recordFilePath="+recordFilePath);

            if (mRecorder != null) {
                LogUtil.d("startRecording mRecorder="+mRecorder);
                try {
                    mRecorder.release();
                } catch (Exception e) {} finally {
                    mRecorder = null;
                    isRecording = false;
                }
            }
//        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//        recorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
//        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//        recorder.setAudioChannels(1); // MONO
//        recorder.setAudioSamplingRate(8000); // 8000Hz
//        recorder.setAudioEncodingBitRate(64); // seems if change this to
            // 128, still got same file
            // size.
            // one easy way is to use temp file
            // file = File.createTempFile(PREFIX + userId, EXTENSION,
            // User.getVoicePath());

            mRecorder = new MediaRecorder();
            // 设置meidaRecorder的音频源是麦克风
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            // 设置文件音频的输出格式为amr
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
            // 设置音频的编码格式为amr
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            // 设置输出文件
            mRecorder.setOutputFile(file.getAbsolutePath());

            try {
                LogUtil.d("recorder prepare");
                mRecorder.prepare();//华为p8会在此等待系统的授权，最长15s
                LogUtil.d("recorder prepared");
                isRecording = true;
                mRecorder.start();
                LogUtil.d("recorder start");
                startTime = new Date().getTime();
                // 已经准备好了，可以录制了
                if (mListener != null) {
                    mListener.wellPrepared();
                }
            } catch (IOException e) {
                LogUtil.e("EcarxRecorder.startRecord 录音异常", e);
            }

            if (handler != null) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (isRecording) {
                                android.os.Message msg = new android.os.Message();
                                msg.what = mRecorder.getMaxAmplitude() * 13 / 0x7FFF;
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
            }
        } catch (Exception e) {
            LogUtil.e("EcarxRecorder.startRecord录音异常",e);
        }

    }

    /**
     * 停止录音
     * @return 录音时长
     */
    public int stopRecord() {
        LogUtil.e("stopRecoding recorder="+mRecorder+", isRecording="+isRecording);
        int seconds = 0;
        if(mRecorder != null){
            try {
                mRecorder.stop();
                mRecorder.release();
                if(file == null || !file.exists() || !file.isFile()){
                    recordFilePath = null;
                    return 1;
                }
                if (file.length() == 0) {
                    file.delete();
                    recordFilePath = null;
                    return 0;
                }
                seconds = (int) (new Date().getTime() - startTime) / 1000;
                LogUtil.d("voice recording finished. seconds:" + seconds + " file length:" + file.length());
            } catch (Exception e) {
                LogUtil.e("stopRecord异常",e);
            } finally {
                isRecording = false;
                mRecorder = null;
            }
        } else {
            isRecording = false;
        }
        return seconds;
    }


    /**
     * 取消录音
     *
     * @return seconds of the voice recorded
     */
    public void cancelRecord() {
        if (mRecorder != null) {
            try {
                mRecorder.stop();
                mRecorder.release();
                if (file != null && file.exists() && !file.isDirectory()) {
//                    取消,因为prepare时产生了一个文件，所以cancel方法应该要删除这个文件，
                    file.delete();
                }
            } catch (Exception e) {
                LogUtil.e("cancelRecord异常",e);
            } finally {
                isRecording = false;
                mRecorder = null;
                recordFilePath = null;
            }
        }
    }

    /**
     * 随机生成文件的名称
     *
     * @return
     */
    private String generalFileName() {
        return UUID.randomUUID().toString() + ".m4a";
    }

    // 获得声音的level
    public int getVoiceLevel(int maxLevel) {
        // mRecorder.getMaxAmplitude()这个是音频的振幅范围，值域是1-32767
        if (isRecording) {
            try {
                // 取证+1，否则去不到7
                return maxLevel * mRecorder.getMaxAmplitude() / 32768 + 1;
            } catch (Exception e) {
                LogUtil.e("异常",e);
            }
        }
        return 1;
    }

    public String getRecordFilePath() {
        return recordFilePath;
    }

    public void setOnAudioStageListener(AudioStageListener listener) {
        mListener = listener;
    }

    public boolean isRecording() {
        return isRecording;
    }

    /**
     * 回调函数，准备完毕，准备好后，button才会开始显示录音框
     *
     * @author nickming
     */
    public interface AudioStageListener {
        void wellPrepared();
    }
}
