package cn.yinxm.lib.media.player.exo;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.AdaptiveMediaSourceEventListener;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.DefaultSsChunkSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Util;

import java.util.Map;

import cn.yinxm.lib.media.player.IPlayHelper;
import cn.yinxm.lib.media.player.OnPlayFinishCallback;
import cn.yinxm.lib.utils.LogUtil;


/**
 * Created by yinxm on 2017/1/12.
 * 功能: exo 播放器
 */

public class ExoAudioPlayHelper implements ExoPlayer.EventListener, IPlayHelper {
    public static final int BUFFERED_FULL_NOTIFY_TIMES = 3;


    private static final DefaultBandwidthMeter BANDWIDTH_METER = new DefaultBandwidthMeter();
    private int playTimes = 0;//检测ExoPlayer是否可用


    private SimpleExoPlayer player;
    Handler mainHandler;
    private DataSource.Factory mediaDataSourceFactory;

    boolean flag = false;//是否存在上一个MediaPlayer对象
    private String playUrl;
    private OnPlayFinishCallback playFinishCallback;
    private boolean playing = false;
    private ExoPlayer.EventListener eventListener;

    public SimpleExoPlayer getPlayer() {
        return player;
    }

    public ExoAudioPlayHelper(Context context) {
        playTimes = 0;
        ExoPlayerUtil.getInstance().init(context);//全局初始化一次

        mediaDataSourceFactory = ExoPlayerUtil.getInstance().buildDataSourceFactory(true, BANDWIDTH_METER);

 // 1. Create a default TrackSelector
        mainHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                LogUtil.d("handler msg="+msg);
            }
        };
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveVideoTrackSelection.Factory(bandwidthMeter);
        DefaultTrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

// 2. Create a default LoadControl
//        LoadControl loadControl = new DefaultLoadControl();
        LoadControl loadControl = new EcarxFastPlayLoadControl();

// 3. Create the player
        player = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);

        player.addListener(this);

//        eventLogger = new EventLogger(trackSelector);
//        player.addListener(eventLogger);
//        player.setAudioDebugListener(eventLogger);
//        player.setVideoDebugListener(eventLogger);
//        player.setMetadataOutput(eventLogger);
    }

    public void setEventListener(ExoPlayer.EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void removeEventListener(ExoPlayer.EventListener eventListener) {
        if (eventListener != null && player != null) {
            player.removeListener(eventListener);
        }
    }

    /**
     * @param url 播放地址
     */
    public void play(String url) {
        play(url, null, null);
    }

    public void play(String url, OnPlayFinishCallback playFinishCallback) {
        play(url, null, playFinishCallback);
    }

    /**
     * @param url                播放地址
     * @param extendParams       播放额外需要的参数，可为null
     * @param playFinishCallback 播放完毕后回调，可为null
     */
    public void play(String url, Map<String, Object> extendParams, OnPlayFinishCallback playFinishCallback) {
        flag = false;
        LogUtil.d("ExoAudioPlayHelper player"+player+", url=" + url);
        if (url == null || url.trim().equals("") || player == null) {
            LogUtil.e("playUrl 不能为空 ");
            if (playFinishCallback != null) {
                playFinishCallback.onPlayfinish(OnPlayFinishCallback.CODE_FAIL);
            }
            return;
        }

        this.playFinishCallback = playFinishCallback;
        this.playUrl = url;
        try {
            flag = false;
            Uri uri = Uri.parse(url);
            MediaSource mediaSource = buildMediaSource(uri, null, mainHandler, mediaDataSourceFactory, null, null);
            player.setPlayWhenReady(true);
            playing = false;
            player.prepare(mediaSource);
            playTimes ++;
        } catch (Exception e) {
            LogUtil.e("播放器错误", e);
            if (playFinishCallback != null) {
                playFinishCallback.onPlayfinish(OnPlayFinishCallback.CODE_FAIL);
            }
        }
    }

    public void play() {
        if (player != null) {
            player.setPlayWhenReady(true);
        }
    }

    public void pause() {
        if (player != null) {
            player.setPlayWhenReady(false);
        }
    }

    /**
     * 播放-暂停-播放 切换
     */
    public void playToggle() {
        if (player != null) {
            player.setPlayWhenReady(!player.getPlayWhenReady());
        }
    }

    /**
     * 停止播放
     */
    public void stop() {
        if (player != null) {
            player.stop();
        }
    }

    /**
     * 停止播放，停止了后player不能再被使用
     */
    public void release() {
        if (player != null) {
            player.release();
        }
    }

    /**
     * 获取音频时长
     * @return
     */
    public long getDuration() {
        long duration = 0L;
        if (player != null) {
            duration = player.getDuration();
            if (duration < 0) {
                duration = 0L;
            }
        }
        return duration;
    }

  /**
     * 获取当前播放音频长度
     * @return
     */
    public long getCurrentPosition() {
        long  position = 0L;
        if (player != null) {
            position = player.getCurrentPosition();
        }
        if (position < 0) {
            position = 0L;
        }
        return position;
    }

    /**
     * 获取缓冲百分比
     * @return
     */
    public int getBufferedPercentage() {
        int bufferePercent = 0 ;
        if (player != null) {
            bufferePercent = player.getBufferedPercentage();
        }
        if (bufferePercent < 0){
            bufferePercent = 0;
        }
        return bufferePercent;
    }

    /**
     * 获取缓冲时长
     * @return
     */
    public long getBufferedPosition() {
        long bufferedPos = 0L;
        if (player != null) {
            bufferedPos = player.getBufferedPosition();
        }
        if (bufferedPos < 0){
            bufferedPos = 0L;
        }
        return bufferedPos;
    }

    public boolean isPlaying() {
        if (player != null) {
            return playing;
        } else {
            return false;
        }
    }

    //快退
    public void rewind() {
        int rewindMs = DEFAULT_REWIND_MS;
        if (rewindMs <= 0 || player == null) {
            return;
        }
        seekTo(Math.max(player.getCurrentPosition() - rewindMs, 0));
    }

    //快进
    public void fastForward() {
        int fastForwardMs = DEFAULT_FAST_FORWARD_MS;
        if (fastForwardMs <= 0 || player == null) {
            return;
        }
        seekTo(Math.min(player.getCurrentPosition() + fastForwardMs, player.getDuration()));
    }

    //播放进度移动到指定位置
    public void seekTo(long positionMs) {
        if (player != null) {
            player.seekTo(positionMs);
        }
    }

    @Override
    public String getPlayUrl() {
        return playUrl;
    }


    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {
        LogUtil.d("onTimelineChanged timeline="+timeline+", manifest="+manifest);

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
        LogUtil.d("onTracksChanged trackGroups="+trackGroups+", trackSelections="+trackSelections);
    }

    @Override
    public void onLoadingChanged(boolean isLoading) {
        LogUtil.d("onLoadingChanged isLoading="+isLoading);

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if (playWhenReady == true && playbackState == ExoPlayer.STATE_READY) {
            playing = true;
        } else {
            playing = false;
        }
        LogUtil.d("onPlayerStateChanged playWhenReady="+playWhenReady+", playbackState="+playbackState+", playing="+playing);

        if (playing) {
            //检测Service里面的状态是否是要播放的状态
//            AudioPlayService service = AudioPlayServiceManager.getInstance();
//            if (service != null && service.isPlaying()) {
//            } else {
//                LogUtil.i("用户已经暂停了播放");
//                pause();
//            }
        }

        if (playbackState == ExoPlayer.STATE_ENDED) {
//            showControls();
            if (playFinishCallback != null) {
                playFinishCallback.onPlayfinish(OnPlayFinishCallback.CODE_SUCCESS);
            }
        }
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {
        LogUtil.e("playTimes="+playTimes+", onPlayerError=", error);
        try {
            //Exo 播放失败，更换为系统自带播放器
//            AudioPlayService service = AudioPlayServiceManager.getInstance();
//            if (service != null) {
//                service.changeAudioPlayer(false);
//            }
            if (playFinishCallback != null) {
                playFinishCallback.onPlayfinish(OnPlayFinishCallback.CODE_FAIL);
            }
        }catch (Exception e) {
            LogUtil.e(e);
        }

    }

    @Override
    public void onPositionDiscontinuity() {
        LogUtil.d("onPositionDiscontinuity");
    }

    /**
     * 通过url构造MediaSource
     * @param uri
     * @param overrideExtension
     * @param mainHandler
     * @param adaptiveMediaSourceEventListener 直播流时使用
     * @param eventListener 普通流
     * @return
     */
    public MediaSource buildMediaSource(Uri uri, String overrideExtension, Handler mainHandler,
                                        DataSource.Factory mediaDataSourceFactory,
                                        AdaptiveMediaSourceEventListener adaptiveMediaSourceEventListener, ExtractorMediaSource.EventListener eventListener) {
        int type = Util.inferContentType(!TextUtils.isEmpty(overrideExtension) ? "." + overrideExtension
                : uri.getLastPathSegment());
        switch (type) {
            case C.TYPE_SS:
                return new SsMediaSource(uri, buildDataSourceFactory(false),
                        new DefaultSsChunkSource.Factory(mediaDataSourceFactory), mainHandler, adaptiveMediaSourceEventListener);
            case C.TYPE_DASH:
                return new DashMediaSource(uri, buildDataSourceFactory(false),
                        new DefaultDashChunkSource.Factory(mediaDataSourceFactory), mainHandler, adaptiveMediaSourceEventListener);
            case C.TYPE_HLS:
                return new HlsMediaSource(uri, mediaDataSourceFactory, mainHandler, adaptiveMediaSourceEventListener);
            case C.TYPE_OTHER:
                return new ExtractorMediaSource(uri, mediaDataSourceFactory, new DefaultExtractorsFactory(),
                        mainHandler, eventListener);
            default: {
                throw new IllegalStateException("Unsupported type: " + type);
            }
        }
    }


    /**
     * Returns a new DataSource factory.
     *
     * @param useBandwidthMeter Whether to set {@link #BANDWIDTH_METER} as a listener to the new
     *     DataSource factory.
     * @return A new DataSource factory.
     */
    private DataSource.Factory buildDataSourceFactory(boolean useBandwidthMeter) {
        return ExoPlayerUtil.getInstance().buildDataSourceFactory(useBandwidthMeter ? BANDWIDTH_METER : null);
    }

    /**
     * Returns a new HttpDataSource factory.
     *
     * @param useBandwidthMeter Whether to set {@link #BANDWIDTH_METER} as a listener to the new
     *     DataSource factory.
     * @return A new HttpDataSource factory.
     */
    private HttpDataSource.Factory buildHttpDataSourceFactory(boolean useBandwidthMeter) {
        return ExoPlayerUtil.getInstance().buildHttpDataSourceFactory(useBandwidthMeter ? BANDWIDTH_METER : null);
    }
}
