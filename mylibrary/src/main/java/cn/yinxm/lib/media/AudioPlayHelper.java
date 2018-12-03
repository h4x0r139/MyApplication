package cn.yinxm.lib.media;

import android.media.AudioManager;
import android.media.MediaPlayer;

import cn.yinxm.lib.utils.StringUtil;
import cn.yinxm.lib.utils.log.LogUtil;

/**
 * Created by yinxm on 2016/10/14.
 */

public class AudioPlayHelper {
    private MediaPlayer myMedia;
    boolean isMediaPlayerReady = false;
    public int bufferPercent = 0;

    /**
     * 播放函数
     *
     * @param musicpath 音乐路径
     */
    public synchronized void play(String musicpath) {
        isMediaPlayerReady = false;
        LogUtil.d("PlayerHelper paly url=" + musicpath);
        if (StringUtil.isBlank(musicpath)) {
            LogUtil.e("playUrl 不能为空");
            return;
        }
        try {
            if (myMedia != null) {
                try {
                    LogUtil.e("myMedia="+myMedia+", stop前");
                    myMedia.stop();
                    LogUtil.e("myMedia="+myMedia+", stop后");
                    myMedia.release();
                    LogUtil.e("myMedia="+myMedia+", release后");
                } catch (Exception e) {
                } finally {
                    myMedia = null;
                }
            }
            myMedia = new MediaPlayer();
            myMedia.setAudioStreamType(AudioManager.STREAM_MUSIC);
            myMedia.setDataSource(musicpath);
            bufferPercent = 0;
            myMedia.prepareAsync();
            myMedia.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    LogUtil.d("MediaPlayer onPrepared");
                    isMediaPlayerReady = true;
                    mp.start();

                }
            });

            myMedia.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    // 歌曲播放完毕，根据播放模式选择下一首播放歌曲的position
                    LogUtil.e("MediaPlayer当前播放完毕，自动下一首");
//                    if (NetworkUtil.isNetworkConnected(BaseApplication.getContext())) {
//                        PlayController.getInstance().playNext();
//                    }
                }

            });

            myMedia.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {//缓冲流改变回调
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    if (bufferPercent != 100) {
                        LogUtil.d("bufferPercent="+percent);
                    }
                    bufferPercent = percent;
                }
            });
            myMedia.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
//                    Toast.makeText(, "当前歌曲无法播放", Toast.LENGTH_SHORT).show();
//                       http://image.kaolafm.net/mz/filmchipsmp3_32/201507/9347a567-9df4-49f3-8966-0b975c272ed3.mp3?appid=kfxmv5890&deviceid=100016501&audioid=1000001732372
                    LogUtil.e("MediaPlayer播放异常mp="+mp+", what="+what+", extra="+extra);
                    return true;
                }
            });
        } catch (Exception e) {
            isMediaPlayerReady = false;
            LogUtil.e("播放器错误", e);
        }
    }

    /**
     * 暂停
     */
    public void pause() {
        if (isMediaPlayerReady && myMedia != null) {
            myMedia.pause();
        }
    }


    /**
     * 继续播放
     */
    public void continuePlay() {
        if (isMediaPlayerReady && myMedia != null) {
            myMedia.start();
        }
    }

    /**
     * 停止
     */
    public void stop() {
        try {
            if (myMedia != null) {
                myMedia.stop();
                myMedia.release();
            }
        } catch (Exception e) {
            LogUtil.e("播放器结束异常", e);
        } finally {
            myMedia = null;
        }
    }

    /**
     * 获取歌曲当前播放位置
     *
     * @return int 歌曲位置
     */
    public int getPlayCurrentDuration() {
        if (isMediaPlayerReady && myMedia != null) {
            return myMedia.getCurrentPosition();
        } else {
            return 0;
        }
    }

    /**
     * 获取歌曲时长
     *
     * @return int 歌曲时长
     */
    public int getPlayTotalDuration() {
        if (isMediaPlayerReady && myMedia != null) {
            return myMedia.getDuration();
        } else {
            return 0;
        }
    }

    /**
     * 按移动的指定的位置播放
     *
     * @param seek 指定的位置
     */
    public void seekToMusicAndPlay(int seek) {
        if (isMediaPlayerReady && myMedia != null) {
            myMedia.seekTo(seek);
            LogUtil.d("音频 seekTo "+seek);
            myMedia.start();
        }
    }

    /**
     * 判断当前是否在播放
     *
     * @return boolean
     */
    public boolean isPlaying() {
        if (isMediaPlayerReady && myMedia != null) {
            return myMedia.isPlaying();
        } else {
            return false;
        }
    }

}
