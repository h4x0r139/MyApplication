package cn.yinxm.mediaplayer_test;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class BaseMediaVioceVideoActivity extends AppCompatActivity {
    private SeekBar skb_audio = null;
    private Button btn_start_audio = null;
    private Button btn_stop_audio = null;

    private SeekBar skb_video = null;
    private Button btn_start_video = null;
    private Button btn_stop_video = null;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;

    private MediaPlayer m = null;
    private Timer mTimer;
    private TimerTask mTimerTask;

    private boolean isChanging = false;//互斥变量，防止定时器与SeekBar拖动时进度冲突

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_media_vioce_video);

        //----------Media控件设置---------//
        m = new MediaPlayer();

        //播放结束之后弹出提示
        m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer arg0) {
                Toast.makeText(BaseMediaVioceVideoActivity.this, "结束", 1000).show();
                m.release();
                stopUpdateProgress();
            }
        });

        //----------定时器记录播放进度---------//
        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                if (isChanging == true)
                    return;
                try {
                    if (m.getVideoHeight() == 0)
                        skb_audio.setProgress(m.getCurrentPosition());
                    else
                        skb_video.setProgress(m.getCurrentPosition());
                } catch (Exception e) {
                    if (e != null)
                        Log.e("yinxm", e.toString());
                }

            }
        };



        btn_start_audio = (Button) this.findViewById(R.id.Button01);
        btn_stop_audio = (Button) this.findViewById(R.id.Button02);
        btn_start_audio.setOnClickListener(new ClickEvent());
        btn_stop_audio.setOnClickListener(new ClickEvent());
        skb_audio = (SeekBar) this.findViewById(R.id.SeekBar01);
        skb_audio.setOnSeekBarChangeListener(new SeekBarChangeEvent());

        btn_start_video = (Button) this.findViewById(R.id.Button03);
        btn_stop_video = (Button) this.findViewById(R.id.Button04);
        btn_start_video.setOnClickListener(new ClickEvent());
        btn_stop_video.setOnClickListener(new ClickEvent());
        skb_video = (SeekBar) this.findViewById(R.id.SeekBar02);
        skb_video.setOnSeekBarChangeListener(new SeekBarChangeEvent());
        surfaceView = (SurfaceView) findViewById(R.id.SurfaceView01);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.setFixedSize(100, 100);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    /*
     * 按键事件处理
     */
    class ClickEvent implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v == btn_start_audio) {
                m.reset();//恢复到未初始化的状态
                m = MediaPlayer.create(BaseMediaVioceVideoActivity.this, R.raw.finish);//读取音频
                skb_audio.setMax(m.getDuration());//设置SeekBar的长度
//                try {
//                    m.prepare();    //准备
//                } catch (IllegalStateException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
                m.start();  //播放
                startUpdateProgress();
            } else if (v == btn_stop_audio || v == btn_stop_video) {
                m.stop();
                mTimerTask.cancel();
                mTimer.cancel();
            } else if (v == btn_start_video) {
                m.reset();//恢复到未初始化的状态
                m = MediaPlayer.create(BaseMediaVioceVideoActivity.this, R.raw.test_swf);//读取视频
                skb_video.setMax(m.getDuration());//设置SeekBar的长度
                m.setAudioStreamType(AudioManager.STREAM_MUSIC);
                m.setDisplay(surfaceHolder);//设置屏幕
//                try {
//                    m.prepare();
//
//                } catch (IllegalArgumentException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                } catch (IllegalStateException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
                m.start();
                startUpdateProgress();
                mTimer.schedule(mTimerTask, 0, 10);
            }
        }
    }

    /*
     * SeekBar进度改变事件
     */
    class SeekBarChangeEvent implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            isChanging = true;
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            m.seekTo(seekBar.getProgress());
            isChanging = false;
        }

    }

    private void startUpdateProgress() {
        if (mTimerTask != null && mTimer != null) {
            mTimer.schedule(mTimerTask, 0, 100);
        }
    }

    private void stopUpdateProgress() {
        if (mTimerTask != null) {
            mTimerTask.cancel();
        }
        if (mTimer != null) {
            mTimer.cancel();
        }
    }
}
