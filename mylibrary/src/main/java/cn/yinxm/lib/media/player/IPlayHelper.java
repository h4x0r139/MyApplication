package cn.yinxm.lib.media.player;

import java.util.Map;

/**
 * Created by yinxm on 2017/1/12.
 * 功能: 播放接口
 */

public interface IPlayHelper {
    public static final int DEFAULT_FAST_FORWARD_MS = 15000;
    public static final int DEFAULT_REWIND_MS = 5000;


    /**
     * @param url 播放地址
     */
    public void play(String url);

    public void play(String url, OnPlayFinishCallback playFinishCallback);

    /**
     * @param url                播放地址
     * @param extendParams       播放额外需要的参数，可为null
     * @param playFinishCallback 播放完毕后回调，可为null
     */
    public void play(String url, Map<String, Object> extendParams, OnPlayFinishCallback playFinishCallback);

    public void play();

    public void pause();

    /**
     * 播放-暂停-播放 切换
     */
    public void playToggle();

    /**
     * 停止播放
     */
    public void stop();

    /**
     * 停止播放，停止了后player不能再被使用
     */
    public void release();

    /**
     * 获取音频时长
     * @return
     */
    public long getDuration();

    /**
     * 获取当前播放音频长度
     * @return
     */
    public long getCurrentPosition();

    /**
     * 获取缓冲百分比
     * @return
     */
    public int getBufferedPercentage();

    /**
     * 获取缓冲时长
     * @return
     */
    public long getBufferedPosition();

    public boolean isPlaying();

    //快退
    public void rewind();

    //快进
    public void fastForward();

    //播放进度移动到指定位置
    public void seekTo(long positionMs);

    /**
     * 获取播放地址
     * @return
     */
    public String getPlayUrl();
}
