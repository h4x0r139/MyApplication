package cn.yinxm.lib.media;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.SystemClock;

import java.io.File;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.yinxm.lib.utils.LogUtil;
import cn.yinxm.lib.utils.StringUtil;


/**
 * 录音
 *
 * @author yinxm
 */
public class EcarxRecorder {
    public static final int VOICE_LEVEL = 2;//2级音频
    private MediaRecorder mRecorder;
    private File file;
    private String recordFileDir;
    private String recordFilePath;
    private long startTime;
    private float totalSeconds = 0.0f;//总共录制时间
    private boolean isRecording = false;// 是否正在录音
    private Context mContext;

    private Handler handler;//声音大小回调

    private boolean isUserCancel = false;//由于外部原因，是否需要中途取消录音

    private ExecutorService executorService;
    private RecordThread recordThread;

    private boolean isUpdateVoiceLevel = false;//是否更新音量级别

    public EcarxRecorder(Context context, String fileDir) {
        this(context, fileDir, null);
    }

    public EcarxRecorder(Context context, String fileDir, Handler handler) {
        mContext=context;
        isRecording = false;
        if (StringUtil.isBlank(fileDir)) {
            fileDir = context.getFilesDir().getAbsolutePath();
        }
        LogUtil.d("fileDir=" + fileDir);
        this.recordFileDir = fileDir;

        File dir = new File(recordFileDir);
        if (dir == null) {
            return;
        }
        if (!dir.exists()) {
            dir.mkdirs();
        }
        LogUtil.d("dir=" + dir);
        if (handler != null) {
            this.handler = handler;
        }
        executorService = Executors.newSingleThreadExecutor();
        recordThread = new RecordThread();
    }

    public void setVoiceLevelHanlder(Handler handler) {
        if (handler != null) {
            this.handler = handler;
        }
    }

    public void startRecord() {
        LogUtil.d("[EcarxRecorder.startRecord] isRecording=" + isRecording + ", mRecorder=" + mRecorder);
//            new Thread(recordThread).start();
        executorService.submit(recordThread);
    }

    /**
     * 停止录音
     *
     * @return 录音时长
     */
    public float stopRecord() {
        LogUtil.d("[EcarxRecorder.stopRecord] isRecording=" + isRecording + "， mRecorder=" + mRecorder + ", recordFilePath=" + recordFilePath);
        isUpdateVoiceLevel = false;
        if (mRecorder != null) {
            try {
                if (isRecording) {
                    try {
                        mRecorder.stop();
                    } catch (Exception e) {
                    }
                    try {
                        mRecorder.release();
                    } catch (Exception e) {
                    }
                    totalSeconds = (new Date().getTime() - startTime) * 1.0f / 1000;
                    LogUtil.d("voice recording finished. seconds:" + totalSeconds + " file length:" + file.length());
                }
                if (file == null || !file.exists() || !file.isFile() || file.length() == 0) {
                    recordFilePath = null;
                    totalSeconds = 0;
                    if (file != null && file.length() == 0) {
                        file.delete();
                    }
                }
            } catch (Exception e) {
                LogUtil.e("stopRecord异常", e);
                totalSeconds = 0;
            }
        }
        resetData();
        LogUtil.d("结束录音 totalSeconds=" + totalSeconds + ", recordFilePath=" + recordFilePath);
        return totalSeconds;
    }


    /**
     * 取消录音
     *
     * @return seconds of the voice recorded
     */
    public void cancelRecord() {
        LogUtil.d("[EcarxRecorder.cancelRecord] isRecording=" + isRecording + "， mRecorder=" + mRecorder);
        isUpdateVoiceLevel = false;
        if (mRecorder != null) {
            try {
                if (isRecording) {
                    try {
                        mRecorder.stop();
                    } catch (Exception e) {
                        LogUtil.e("cancelRecord.stop可以忽略的异常");
                    }
                    try {
                        mRecorder.release();
                    } catch (Exception e) {
                        LogUtil.e("cancelRecord.release 可以忽略的异常");
                    }
                }
                if (file != null && file.exists() && !file.isDirectory()) {
//                    取消,因为prepare时产生了一个文件，所以cancel方法应该要删除这个文件，
                    file.delete();
                    recordFilePath = null;
                    totalSeconds = 0;
                }
            } catch (Exception e) {
                LogUtil.e("cancelRecord异常", e);
            }
        }
        totalSeconds = 0.0f;
        resetData();

    }

    /**
     * 随机生成文件的名称
     *
     * @return
     */
    private String generalFileName() {
        return UUID.randomUUID().toString() + ".m4a";
    }

    public String getRecordFilePath() {
        LogUtil.d("recordFilePath=" + recordFilePath);
        return recordFilePath;
    }

    public boolean isRecording() {
        return isRecording;
    }

    /**
     * 获取当前录制时间长度
     *
     * @return
     */
    public float getCurrentRecordTimeLength() {
        if (isRecording) {
            return startTime > 0 ? (new Date().getTime() - startTime) * 1.0f / 1000 : 0f;
        } else {
            return totalSeconds;
        }
    }

    public void resetData() {
        isUpdateVoiceLevel = false;
        isRecording = false;
        mRecorder = null;
        startTime = 0;
    }

    public void setUserCancel(boolean userCancel) {
        isUserCancel = userCancel;
    }

    public boolean isUpdateVoiceLevel() {
        return isUpdateVoiceLevel;
    }

    public void setUpdateVoiceLevel(boolean updateVoiceLevel) {
        isUpdateVoiceLevel = updateVoiceLevel;
    }

    class RecordThread implements Runnable {
        @Override
        public void run() {
            LogUtil.i("RecordThread startRecord recordFileDir=" + recordFileDir);

            if (isRecording) {//避免重复调用异常
                LogUtil.e("目前已经正在录音,取消之前的录音");
                cancelRecord();
                isRecording = false;
            }
            if (StringUtil.isBlank(recordFileDir)) {
                return;
            }
            try {
                String fileNameString = generalFileName();
                file = new File(recordFileDir, fileNameString);

                recordFilePath = file.getAbsolutePath();
                LogUtil.i("startRecord recordFilePath=" + recordFilePath);

                if (mRecorder != null) {
                    LogUtil.d("startRecording mRecorder=" + mRecorder);
                    try {
                        mRecorder.release();
                    } catch (Exception e) {
                    } finally {
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
                // 设置文件音频的输出格式为
                mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);//MediaRecorder.OutputFormat.DEFAULT
                // 设置音频的编码格式
                mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
                // 设置输出文件
                mRecorder.setOutputFile(file.getAbsolutePath());

//                LogUtil.d("sleep start");
//                if (SpUtil.readBoolean(mContext, SPConstant.FILE_NAME_SET_CONFIG, KEY_SPEAK_WAKE_UP, false)) {
//                    String phoneBrand = DeviceUtil.getPhoneBrand();
//                    LogUtil.d("phoneBrand"+phoneBrand);
//                    if ("OPPO".equals(phoneBrand)){
//                        Thread.sleep(200);
//                    }
//                }
//                LogUtil.d("sleep end");

                isUserCancel = false;
                LogUtil.d("EcarxRecorder.RecordThread mRecorder=" + mRecorder);
                try {
                    mRecorder.prepare();
                } catch (Exception e) {
                    LogUtil.e("mRecorder.prepare异常", e);
                }
                LogUtil.d("recorder prepared");
                try {
                    mRecorder.start();//部分手机会权限阻塞，导致ANR， 华为p8、Oppo会在此等待系统的授权，最长15s,只有获取到权限才会调用这个，才会真正执行
                } catch (Exception e) {
                    LogUtil.e("mRecorder.start异常", e);
                }
                LogUtil.d("recorder start");
                //【重要】 此时应该反过来检查用户是否还在按住按钮，如果没有按住就结束掉本次录音
                if (isUserCancel) {
                    LogUtil.d("检测到用户已经取消录音，cancel");
                    cancelRecord();
                    isUserCancel = false;
                    return;
                }
                LogUtil.d("录音中...");
                isUpdateVoiceLevel = true;
                isRecording = true;
                startTime = new Date().getTime();

                if (handler != null) {
                    LogUtil.d("开始处理声音音量大小回调");
                    try {
                        while (isRecording && mRecorder != null && isUpdateVoiceLevel) {
                            android.os.Message msg = new android.os.Message();
                            int maxAmp = mRecorder.getMaxAmplitude();
                            LogUtil.d("音量级别maxAmp=" + maxAmp);
                            msg.what = maxAmp * VOICE_LEVEL / 32768 + 1;//msg.what = mRecorder.getMaxAmplitude() * 13 / 0x7FFF;
//                            LogUtil.d("音量级别="+msg.what);
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

            } catch (Exception e) {
                LogUtil.e("EcarxRecorder.startRecord 录音异常", e);
            }
        }
    }

    ;
}
