/*
 *  COPYRIGHT NOTICE  
 *  Copyright (C) 2016, Jhuster <lujun.hust@gmail.com>
 *  https://github.com/Jhuster/Android
 *   
 *  @license under the Apache License, Version 2.0 
 *
 *  @file    AudioPlayer.java
 *  
 *  @version 1.0     
 *  @author  Jhuster
 *  @date    2016/03/13    
 */
package cn.yinxm.lib.utils.media.audio;

import android.util.Log;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

/**
 * 播放pcm流
 * http://ticktick.blog.51cto.com/823160/1748506
 */
public class AudioTrackPlayer {
    
    private static final String TAG = "AudioTrackPlayer";

    private static final int DEFAULT_STREAM_TYPE = AudioManager.STREAM_MUSIC;
    private static final int DEFAULT_SAMPLE_RATE = 44100;
    private static final int DEFAULT_CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_STEREO;
    private static final int DEFAULT_AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT;
    private static final int DEFAULT_PLAY_MODE = AudioTrack.MODE_STREAM;
            
    private boolean mIsPlayStarted = false;
    private int mMinBufferSize = 0;
    private AudioTrack mAudioTrack;  
    
    public boolean startPlayer() {
        return startPlayer(DEFAULT_STREAM_TYPE,DEFAULT_SAMPLE_RATE,DEFAULT_CHANNEL_CONFIG,DEFAULT_AUDIO_FORMAT);
    }
    
    public boolean startPlayer(int streamType, int sampleRateInHz, int channelConfig, int audioFormat) {
        
        if (mIsPlayStarted) {
            Log.e(TAG, "Player already started !");
            return false;
        }
        
        mMinBufferSize = AudioTrack.getMinBufferSize(sampleRateInHz,channelConfig,audioFormat);
        if (mMinBufferSize == AudioTrack.ERROR_BAD_VALUE) {
            Log.e(TAG, "Invalid parameter !");
            return false;
        }
        Log.d(TAG , "getMinBufferSize = "+mMinBufferSize+" bytes !");
        
        mAudioTrack = new AudioTrack(streamType,sampleRateInHz,channelConfig,audioFormat,mMinBufferSize,DEFAULT_PLAY_MODE);
        if (mAudioTrack.getState() == AudioTrack.STATE_UNINITIALIZED) {
            Log.e(TAG, "AudioTrack initialize fail !");
            return false;
        }            
        
        mIsPlayStarted = true;
        
        Log.d(TAG, "Start audio player success !");
        
        return true;
    }
    
    public int getMinBufferSize() {
        return mMinBufferSize;
    }
    
    public void stopPlayer() {
        
        if (!mIsPlayStarted) {
            return;
        }
        
        if (mAudioTrack.getPlayState() == AudioTrack.PLAYSTATE_PLAYING) {
            mAudioTrack.stop();                        
        }
        
        mAudioTrack.release();
        mIsPlayStarted = false;
           
        Log.d(TAG, "Stop audio player success !");
    }
    
    public boolean play(byte[] audioData, int offsetInBytes, int sizeInBytes) {
        
        if (!mIsPlayStarted) {
            Log.e(TAG, "Player not started !");
            return false;
        }
        
        if (sizeInBytes < mMinBufferSize) {
            Log.e(TAG, "audio data is not enough !");
            return false;
        }
        
        if (mAudioTrack.write(audioData,offsetInBytes,sizeInBytes) != sizeInBytes) {                
            Log.e(TAG, "Could not write all the samples to the audio device !");
        }                                   
                                                   
        mAudioTrack.play();
        
        Log.d(TAG , "OK, Played "+sizeInBytes+" bytes !");
        
        return true;
    }
}
