package cn.bgxt.mediarecorderdemo;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.media.MediaRecorder;
import android.media.MediaRecorder.OnErrorListener;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class VideoActivity extends Activity {
	private Button btn_VideoStart, btn_VideoStop;
	private SurfaceView sv_view;
	private boolean isRecording;
	private MediaRecorder mediaRecorder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);

		btn_VideoStart = (Button) findViewById(R.id.btn_VideoStart);
		btn_VideoStop = (Button) findViewById(R.id.btn_VideoStop);
		sv_view = (SurfaceView) findViewById(R.id.sv_view);

		btn_VideoStop.setEnabled(false);

		btn_VideoStart.setOnClickListener(click);
		btn_VideoStop.setOnClickListener(click);
		
		// 声明Surface不维护自己的缓冲区，针对Android3.0以下设备支持
		sv_view.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	private View.OnClickListener click = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_VideoStart:
				start();
				break;
			case R.id.btn_VideoStop:
				stop();
				break;
			default:
				break;
			}
		}
	};

	protected void start() {
		try {
			File file = new File("/sdcard/video.mp4");
			if (file.exists()) {
				// 如果文件存在，删除它，演示代码保证设备上只有一个录音文件
				file.delete();
			}
			
			mediaRecorder = new MediaRecorder();
			mediaRecorder.reset();
			// 设置音频录入源
			mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			// 设置视频图像的录入源
			mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
			// 设置录入媒体的输出格式
			mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
			// 设置音频的编码格式
			mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
			// 设置视频的编码格式
			mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
			// 设置视频的采样率，每秒4帧
			mediaRecorder.setVideoFrameRate(4);
			// 设置录制视频文件的输出路径
			mediaRecorder.setOutputFile(file.getAbsolutePath());
			// 设置捕获视频图像的预览界面
			mediaRecorder.setPreviewDisplay(sv_view.getHolder().getSurface());
			
			mediaRecorder.setOnErrorListener(new OnErrorListener() {
				
				@Override
				public void onError(MediaRecorder mr, int what, int extra) {
					// 发生错误，停止录制
					mediaRecorder.stop();
					mediaRecorder.release();
					mediaRecorder = null;
					isRecording=false;
					btn_VideoStart.setEnabled(true);
					btn_VideoStop.setEnabled(false);
					Toast.makeText(VideoActivity.this, "录制出错", 0).show();
				}
			});
			
			// 准备、开始
			mediaRecorder.prepare();
			mediaRecorder.start();

			btn_VideoStart.setEnabled(false);
			btn_VideoStop.setEnabled(true);
			isRecording = true;
			Toast.makeText(VideoActivity.this, "开始录像", 0).show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void stop() {
		if (isRecording) {
			// 如果正在录制，停止并释放资源
			mediaRecorder.stop();
			mediaRecorder.release();
			mediaRecorder = null;
			isRecording=false;
			btn_VideoStart.setEnabled(true);
			btn_VideoStop.setEnabled(false);
			Toast.makeText(VideoActivity.this, "停止录像，并保存文件", 0).show();
		}
	}

	@Override
	protected void onDestroy() {
		if (isRecording) {
			mediaRecorder.stop();
			mediaRecorder.release();
			mediaRecorder = null;
		}
		super.onDestroy();
	}

}
