package cn.yinxm.lib.utils;

import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;

import java.util.Map;

import cn.yinxm.lib.utils.log.LogUtil;

/**
 * Created by yinxm on 2017/1/11.
 * 功能: 音频播放工具类
 */

public class AudioPlayHelper {
    public static final int CODE_FINISH = 0;//操作完成
    public static final int CODE_SUCCESS = 1;//操作成功
    public static final int CODE_FAIL = 2;//操作失败
    public static final int CODE_NO_DATA = 3;//无数据

    public static final int BUFFERED_FULL_NOTIFY_TIMES = 3;
    private MediaPlayer myMedia;
    boolean isMediaPlayerReady = false;
    public int bufferPercent = 0;
    public int bufferedFullTims = 0;
    boolean flag = false;//是否存在上一个MediaPlayer对象
    private String playUrl;
    private OnPlayFinishCallback playFinishCallback;
    public AnimationDrawable mDrawable;

    private AudioPlayHelper() {
    }

    public static AudioPlayHelper getInstance() {
        return PlayerHelperFactory.playerHelper;
    }

    private static class PlayerHelperFactory {
        private static AudioPlayHelper playerHelper = new AudioPlayHelper();
    }
    /**
     * @param url 播放地址
     */
    public void play(String url) {
        play(url, null, null);
    }

    public void play(String url, final OnPlayFinishCallback playFinishCallback) {
        play(url, null, playFinishCallback);
    }

    /**
     * @param url                播放地址
     * @param extendParams       播放额外需要的参数，可为null
     * @param playFinishCallback 播放完毕后回调，可为null
     */
    public void play(String url, Map<String, Object> extendParams, final OnPlayFinishCallback playFinishCallback) {
        flag = false;
        isMediaPlayerReady = false;
        LogUtil.d("PlayerHelper paly url=" + url);
        if (StringUtil.isBlank(url)) {
            LogUtil.e("playUrl 不能为空");
            if (playFinishCallback != null) {
                playFinishCallback.onPlayfinish(CODE_FAIL);
            }
            return;
        }
        this.playFinishCallback = playFinishCallback;
        this.playUrl = url;
        try {
            if (myMedia != null) {
                try {
                    flag = true;
//                        LogUtil.e("myMedia="+myMedia+", stop前");
                    myMedia.stop();
//                        LogUtil.e("myMedia="+myMedia+", stop后");
                    myMedia.release();
//                        LogUtil.e("myMedia="+myMedia+", release后");
                } catch (Exception e) {
                    LogUtil.e("myMedia=" + myMedia + " stop release异常", e);
                } finally {
                    myMedia = null;
                }
            }

            flag = false;
            myMedia = new MediaPlayer();
            myMedia.setAudioStreamType(AudioManager.STREAM_MUSIC);
            myMedia.setDataSource(playUrl);
            ;
            myMedia.prepareAsync();
            myMedia.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    LogUtil.d("MediaPlayer onPrepared");
                    isMediaPlayerReady = true;
                    //反过来检测Service里面的状态是否是要播放的状态
//                        if (isPlaying()) {
                    mp.start();
//                        } else {
//                            LogUtil.i("用户已经暂停了播放");
//                        }

                }
            });

            myMedia.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    LogUtil.d("onCompletion");
                    // 歌曲播放完毕
                    if (playFinishCallback != null) {
                        playFinishCallback.onPlayfinish(CODE_SUCCESS);
                    }
                }

            });

            myMedia.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {//缓冲流改变回调
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    bufferPercent = percent;
                    if (bufferPercent >= 100) {
                        bufferPercent = 100;
                        bufferedFullTims++;
                    }
                    if (bufferedFullTims <= BUFFERED_FULL_NOTIFY_TIMES) {
//                            notifyBufferingUpdateListener(percent);
                    }
                }
            });
            myMedia.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
//                    ToastUtil.showTextToast("当前音频无法播放");
                    LogUtil.e("MediaPlayer播放异常mp=" + mp + ", what=" + what + ", extra=" + extra + ", playUrl=" + playUrl);
                    if (playFinishCallback != null) {
                        playFinishCallback.onPlayfinish(CODE_FAIL);
                    }
                    return true;
                }
            });
        } catch (Exception e) {
            isMediaPlayerReady = false;
            LogUtil.e("播放器错误", e);
            if (playFinishCallback != null) {
                playFinishCallback.onPlayfinish(CODE_FAIL);
            }
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
            LogUtil.d("音频 seekTo " + seek);
            myMedia.start();
        }
    }

    /**
     * 判断当前是否在播放
     *
     * @return boolean
     */
    public boolean isPlayingMP() {
        if (isMediaPlayerReady && myMedia != null) {
            return myMedia.isPlaying();
        } else {
            return false;
        }
    }

    public String getPlayUrl() {
        return playUrl;
    }

    /**
     * 播放完毕回调
     */
    public interface OnPlayFinishCallback {
        /**
         * @param code CODE_SUCCESS, CODE_FAIL
         */
        public void onPlayfinish(int code);
    }
}
