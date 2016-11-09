package cn.yinxm.lib.media;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;

import cn.yinxm.lib.interfaces.MyAppManager;

/**
 * 音乐播放管理类
 * Created by Administrator on 2015/8/27 0027.
 */
public class PlayerManager {

    /**
     * 外放模式
     */
    public static final int MODE_SPEAKER = 0;

    /**
     * 耳机模式
     */
    public static final int MODE_HEADSET = 1;

    /**
     * 听筒模式
     */
    public static final int MODE_EARPIECE = 2;

    private static PlayerManager instance;
    private AudioManager audioManager;
    private int currentMode = MODE_SPEAKER;

    public static PlayerManager getInstance() {
        if (instance == null) {
            synchronized (PlayerManager.class) {
                instance = new PlayerManager();
            }
        }
        return instance;
    }

    public static void setInstance(PlayerManager instance) {
        PlayerManager.instance = instance;
    }

    private PlayerManager(){
        initAudioManager();
    }


    /**
     * 初始化音频管理器
     */
    private void initAudioManager() {
        Context context = MyAppManager.getInstance().getApplicationContext();
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
        } else {
            audioManager.setMode(AudioManager.MODE_IN_CALL);
        }
        audioManager.setSpeakerphoneOn(true);			//默认为扬声器播放
    }



    /**
     * 切换到听筒模式
     */
    public void changeToEarpieceMode(){
        currentMode = MODE_EARPIECE;
        audioManager.setSpeakerphoneOn(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                    audioManager.getStreamMaxVolume(AudioManager.MODE_IN_COMMUNICATION), AudioManager.FX_KEY_CLICK);
        } else {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                    audioManager.getStreamMaxVolume(AudioManager.MODE_IN_CALL), AudioManager.FX_KEY_CLICK);
        }
    }

    /**
     * 切换到耳机模式
     */
    public void changeToHeadsetMode(){
        currentMode = MODE_HEADSET;
        audioManager.setSpeakerphoneOn(false);
    }

    /**
     * 切换到外放模式
     */
    public void changeToSpeakerMode(){
        currentMode = MODE_SPEAKER;
        audioManager.setSpeakerphoneOn(true);
    }

    public void resetPlayMode(){
        if (audioManager.isWiredHeadsetOn()){
            changeToHeadsetMode();
        } else {
            changeToSpeakerMode();
        }
    }

    /**
     * 调大音量
     */
    public void raiseVolume(){
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        if (currentVolume < audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)) {
            audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                    AudioManager.ADJUST_RAISE, AudioManager.FX_FOCUS_NAVIGATION_UP);
        }
    }

    /**
     * 调小音量
     */
    public void lowerVolume(){
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        if (currentVolume > 0) {
            audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                    AudioManager.ADJUST_LOWER, AudioManager.FX_FOCUS_NAVIGATION_UP);
        }
    }

}
