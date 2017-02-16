package cn.yinxm.mediaplayer_test;

import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

/**
 * SurfaceHolder
 * 1. 简介 : 是 Surface 的控制器, 用于控制 SurfaceView 绘图, 处理画布上的动画, 渲染效果, 大小等;
 * 2. 常用方法 :
 * -- abstract void addCallback(SurfaceHolder.Callback callback) : 添加一个 SurfaceHolder.Callback 接口对象, 监听 Surface 的开始结束绘制大小改变事件;
 * -- abstract Canvas lockCanvas() : 锁定画布, 可以获得 Canvas 对象, 之后就可以在 Canvas 上绘图了;
 * <p>
 * SurfaceHolder.Callback接口 :
 * 1. Surface 绘图边界 : 所有的绘图工作都在 Surface 创建之后才能进行, 在 Surface 销毁之前结束;
 * 2. Callback 接口对应的 Surface 边界 : surfaceCreated() 方法在开始绘制时回调, surfaceDestroyed() 在 Surface 销毁前回调;
 * 3. 该接口中的方法 :
 * -- surfaceChanged() : 在 Surface 大小改变时回调;
 * -- surfaceCreated() : 在 Surface 创建时回调;
 * -- surfaceDestroyed() : 在 Surface 销毁时回调;
 *
 * @author octopus
 */
public class VideoPlay2Activity extends AppCompatActivity implements SurfaceHolder.Callback {


    private AutoCompleteTextView url;                           /* 地址输入框, 带自动提示功能 */
    private SurfaceView surface_view;                           /* 播放视频载体 */
    private TextView status;                                    /* 显示播放状态 */
    private Button play;                                        /* 播放按钮 */
    private Button pause;                                       /* 咱提供按钮 */
    private Button reset;                                       /* 重放按钮 */
    private Button stop;                                        /* 停止按钮 */

    private MediaPlayer mediaPlayer;                            /* 播放器 */
    private SurfaceHolder surface_holder;                       /* Surface 控制器 */

    private boolean isStartPlaying;     /* 是否开始了播放 */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play2);

        initViews();
        initData();
    }

    /**
     * 初始化成员变量中的组件变量
     */
    private void initViews() {
        /* 通过 findViewById 获取相关方法 */
        url = (AutoCompleteTextView) findViewById(R.id.url);
        surface_view = (SurfaceView) findViewById(R.id.surface_view);
        status = (TextView) findViewById(R.id.status);
        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);
        reset = (Button) findViewById(R.id.reset);
        stop = (Button) findViewById(R.id.stop);

        /* 设置一个列表适配器 */
        String[] urls = {
                "http://daily3gp.com/vids/747.3gp",
                "http://daily3gp.com/vids/Funny%20women%20cannot%20understand.3gp",
                "http://k.youku.com/player/getFlvPath/sid/9409280845322127f6c57_00/st/flv/fileid/0300020100540024BC9E5C08BD8A98D8200E2B-7950-B9A5-8669-DC283BDCC077?K=3a58dc2cdcc532df261dddec&ctype=12&ev=1&oip=1931322792&token=5696&ep=eyaUE0uFVsYE4CDdij8bYHrkJ3IIXP4J9h%2BFg9JjALshTOi%2FmzqjtJTFS4xCHottelMPGJ%2F5qdDnH0JmYfdKrGgQrUfZPPro%2BPbq5dkgxpgDFG1FAc3Qs1SbRTn3",
                "http://k.youku.com/player/getFlvPath/sid/9409280845322127f6c57_00/st/flv/fileid/030002040053FFB59E433100422C39BAFA46CC-4DED-E928-87B8-91706CDB5FF2?K=645d8478a3aa59052411eb8a&ctype=12&ev=1&oip=1931322792&token=5696&ep=eyaUE0uFVsYE4CDdij8bYHrkJ3IIXP4J9h%2BFg9JmALshS57J6zvYspmzTf5CFv0bcFEFGZmA3aHjbDNnYfQ33BwQqkeqMfro%2BYLr5aRSw5AGFW1Ed7uhtlSbRTn3"

        };
        /* 创建数组适配器 */
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, urls);
        /* 将适配器设置给 AutoCompleteTextView 组件对象 */
        url.setAdapter(adapter);

        /* 使窗口支持透明度, 把当前 Activity 窗口设置成透明, 设置了该选项就可以使用 setAlpha 等函数设置窗口透明度 */
        getWindow().setFormat(PixelFormat.TRANSPARENT);
    }

    /**
     * 初始化相关数据变量
     */
    private void initData() {

        /* 获取并设置 SurfaceHolder 对象 */
        surface_holder = surface_view.getHolder();                      /* 根据 SurfaceView 组件, 获取 SurfaceHolder 对象 */
        surface_holder.addCallback(this);                               /* 为 SurfaceHolder 设置回调函数, 即 SurfaceHolder.Callback 子类对象 */
        surface_holder.setFixedSize(160, 128);                          /* 设置视频大小比例 */
        surface_holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);/* 设置视频类型 */

    }

    /**
     * 设置点击事件
     *
     * @param view
     */
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.play:
            /* 播放视频直接从 AutoCompleteTextView 中获取字符串, 播放该 url 代表的网络视频 */
                playVideo(url.getText().toString());
                break;

            case R.id.pause:
                if (mediaPlayer != null) {
                    mediaPlayer.pause();
                    status.setText("暂停");
                }
                break;

            case R.id.reset:
                if (mediaPlayer != null) {
                    mediaPlayer.seekTo(0);
                    mediaPlayer.start();
                    status.setText("播放中");
                }
                break;

            case R.id.stop:
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    isStartPlaying = false;
                    status.setText("停止");
                }
                break;

            default:
                break;
        }
    }

    /**
     * 播放网络视频
     * a. 创建并配置 MediaPlayer 对象 (音量, SurfaceHolder)
     * b. 为 MediaPlayer 设置错误监听器, 缓冲进度监听器, 播放完毕监听器, 准备完毕监听器
     * c. 未 MediaPlayer 设置数据源
     * d. 调用 prepare() 进入 Prapared 状态
     * e. 调用 start() 进入 Started 状态
     *
     * @param dataSource 播放视频的网络地址
     */
    private void playVideo(final String dataSource) {

        /* 点击播放有两种情况
         * a. 第一次点击 : 需要初始化 MediaPlayer 对象, 设置监听器
         * b. 第二次点击 : 只需要 调用 mediaPlayer 的 start() 方法
         * 两种情况通过 isStartPlaying 点击时间判断 */

        if (isStartPlaying) {                             /* 如果已经开始了播放, 就直接开始播放 */
            mediaPlayer.start();
        } else {                                          /* 如果是第一次开始播放, 需要初始化 MediaPlayer 设置监听器等操作 */
            mediaPlayer = new MediaPlayer();            /* 创建 MediaPlayer 对象 */
            mediaPlayer.setAudioStreamType(2);          /* 设置播放音量 */
            mediaPlayer.setDisplay(surface_holder);     /* 设置播放载体 */

            /* 设置 MediaPlayer 错误监听器, 如果出现错误就会回调该方法打印错误代码 */
            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer arg0, int what, int extra) {
                    System.out.println("MediaPlayer 出现错误 what : " + what + " , extra : " + extra);
                    return false;
                }
            });

            /* 设置缓冲进度更新监听器 */
            mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer arg0, int percent) {
                    /* 打印缓冲的百分比, 如果缓冲 */
                    System.out.println("缓冲了的百分比 : " + percent + " %");
                }
            });

            /* 设置播放完毕监听器 */
            mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer arg0) {
                    System.out.println("播放完毕了");
                    status.setText("播放完毕");
                }
            });

            /* 设置准备完毕监听器 */
            mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer arg0) {
                    System.out.println("准备完毕");
                    /* 设置播放状态 */
                    status.setText("播放中");
                }
            });

            new Thread() {
                public void run() {
                    try {

                        System.out.println("设置数据源");

                        mediaPlayer.setDataSource(dataSource);
                        mediaPlayer.prepare();

                        /* 打印播放视频的时长 */
                        System.out.println("视频播放长度 : " + mediaPlayer.getDuration());

                        mediaPlayer.start();

                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                ;
            }.start();

            /* 设置 MediaPlayer 开始播放标识为 true */
            isStartPlaying = true;
            /* 设置播放状态 */
            status.setText("正在缓冲");
        }


    }


    /**
     * 在 Surface 大小发生改变的时候回调
     * 实现的 SurfaceHolder.Callback 接口方法
     */
    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        System.out.println("SurfaceHolder.Callback.surfaceChanged : Surface 大小发生改变");
    }

    /**
     * 在 Surface 创建的时候回调, 一般在该方法中开始绘图
     * 实现的 SurfaceHolder.Callback 接口方法
     */
    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        System.out.println("SurfaceHolder.Callback.surfaceCreated : Surface 开始创建");
    }

    /**
     * 在 Surface 销毁之前回调, 在该方法中停止渲染线程, 释放相关资源
     * 实现的 SurfaceHolder.Callback 接口方法
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        System.out.println("SurfaceHolder.Callback.surfaceDestroyed : Surface 销毁");
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null)
            mediaPlayer.release();
        super.onDestroy();
    }

}
