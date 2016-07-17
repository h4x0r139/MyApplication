package com.yarin.android.Examples_07_03;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class Activity01 extends Activity {
	ImageView iv_pre;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
		iv_pre = (ImageView) findViewById(R.id.iv_pre);

		/* 创建VideoView对象 */
		final VideoView videoView = (VideoView) findViewById(R.id.VideoView01);




		/* 操作播放的三个按钮 */
		Button PauseButton = (Button) this.findViewById(R.id.PauseButton);
		Button LoadButton = (Button) this.findViewById(R.id.LoadButton);
		Button PlayButton = (Button) this.findViewById(R.id.PlayButton);

		/* 装载按钮事件 */
		LoadButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
//				String url = "http://download.wavetlan.com/SVV/EmbededContent/BlackBerry.mp4";
				Uri videoUri = Uri.parse("android.resource://com.yarin.android.Examples_07_03/raw/test.mp4");


/*				videoView.setVideoPath(videoUri.getPath());
				/*//* 设置模式-播放进度条 *//**//*
				videoView.setMediaController(new MediaController(
						Activity01.this));
				videoView.requestFocus();*/


				// 创建MediaController对象
				MediaController mController = new MediaController(Activity01.this);
				File video = new File("android.resource://com.yarin.android.Examples_07_03/raw/test.mp4");
				if(video.exists())
				{
					videoView.setVideoPath(video.getAbsolutePath());
					// 设置videoView与mController建立关联
					videoView.setMediaController(mController);
					// 设置mController与videoView建立关联
					mController.setMediaPlayer(videoView);
					// 让VideoView获取焦点
					videoView.requestFocus();
				}


			}
		});
		/* 播放按钮事件 */
		PlayButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				/* 开始播放 */
				videoView.start();
			}
		});
		/* 暂停按钮 */
		PauseButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				/* 暂停 */
				videoView.pause();
			}
		});
	}

	/**
	 * 获取当前videoView截图
	 */
	public static Bitmap getCurrentVideoBitmap(String url, VideoView videoView){
		Bitmap bitmap = null;
		MediaMetadataRetriever retriever = null;
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.GINGERBREAD_MR1) {
			retriever = new MediaMetadataRetriever();


			try {
				retriever.setDataSource(url, new HashMap<String, String>());
				bitmap = retriever.getFrameAtTime(videoView.getCurrentPosition() * 1000); //取得指定时间的Bitmap，即可以实现抓图（缩略图）功能
			} catch (IllegalArgumentException ex) {
				// Assume this is a corrupt video file
			} catch (RuntimeException ex) {
				// Assume this is a corrupt video file.
			} finally {
				try {
					retriever.release();
				} catch (RuntimeException ex) {
					// Ignore failures while cleaning up.
				}
			}
		}

		if (bitmap == null) {
			return null;
		}

		//bitmap = Bitmap.createScaledBitmap(bitmap, 200, 200, true);
		bitmap = Bitmap.createBitmap(bitmap);
		return bitmap;
	}


	//网络图片转换成Bitmap
	public static Bitmap netImgToBitmap(String url) {
		URL mUrl = null;
		Bitmap bitmap = null;
		try {
			mUrl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) mUrl.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream inputStream = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(inputStream);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

}
