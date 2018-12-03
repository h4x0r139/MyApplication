package cn.yinxm.lib.interfaces.iml;

import java.io.Serializable;

import cn.yinxm.lib.interfaces.IAudioPlayController;
import cn.yinxm.lib.interfaces.IAudioPlayService;
import cn.yinxm.lib.interfaces.IMusicNotification;


/**
 * Created by yinxm on 2016/12/14
 * 功能: 音频播放控制
 */

public class AudioPlayControllerIml implements IAudioPlayController, Serializable {
    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public void play() {

    }

    @Override
    public void pausePlay() {

    }

    @Override
    public void continuePlay() {

    }

    @Override
    public void playPauseContinue() {

    }

    @Override
    public void playNext() {

    }

    @Override
    public void playPrevious() {

    }

    @Override
    public void openAudioPlayingActivity() {

    }

    @Override
    public IAudioPlayService getAudioPlayService() {
        return null;
    }

    @Override
    public void startAudioPlayService() {

    }

    @Override
    public IMusicNotification getMusicNotification() {
        return null;
    }


}
