package cn.yinxm.lib.interfaces;

/**
 * Created by yinxm on 2016/11/16.
 * 功能: 音频播放回调
 */

public interface IAppAudioManager {


    public void addBeforePlayCallback();
    public void removeBeforePlayCallback();

    public void addAfterStopCallback();
    public void removeAfterStopCallback();
}
