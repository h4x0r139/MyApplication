package com.jph.lc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;

import com.jph.lc.HeadSetUtil.OnHeadSetListener;

import java.util.Timer;
import java.util.TimerTask;

/**
 * MEDIA_BUTTON耳机媒体按键广播接收器
 * @author JPH
 * @Date2015-6-9 下午8:35:40
 */
public class MediaButtonReceiver extends BroadcastReceiver{

	private Timer timer = null;
	private OnHeadSetListener headSetListener = null;
	private static MTask myTimer = null;
	/**单击次数**/
	private static int clickCount;
	public MediaButtonReceiver(){
		timer = new Timer(true);
		this.headSetListener = HeadSetUtil.getInstance().getOnHeadSetListener();
	}
	@Override
	public void onReceive(Context context, Intent intent) {
		 String intentAction = intent.getAction() ;
		Log.i(TAG, "com.jph.lc.MediaButtonReceiver.onReceive action="+intentAction);

		if(Intent.ACTION_MEDIA_BUTTON.equals(intentAction)){
	        	KeyEvent keyEvent = (KeyEvent)intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT); //获得KeyEvent对象  
	        	if(headSetListener != null){
	        		try {
	        			if(keyEvent.getAction() == KeyEvent.ACTION_UP){
							Log.i(TAG, "com.jph.lc.MediaButtonReceiver.onReceive KeyEvent.ACTION_UP");

							if (clickCount==0) {//单击
	        					clickCount++;
	        					myTimer = new MTask();
	        					timer.schedule(myTimer,1000);
							}else if (clickCount==1) {//双击
								clickCount++;
							}else if (clickCount==2) {//三连击
								clickCount=0;
								myTimer.cancel();
								headSetListener.onThreeClick();
							}
	        			}
					} catch (Exception e) {
					}
	        	}	
	        }
//	        abortBroadcast();//终止广播(不让别的程序收到此广播，免受干扰)
	}
	/**
	 * 定时器，用于延迟1秒，判断是否会发生双击和三连击
	 */
	class MTask extends TimerTask{
			@Override
			public void run() {
				try {
					if (clickCount==1) {
						mhHandler.sendEmptyMessage(1);
					}else if (clickCount==2) {
						mhHandler.sendEmptyMessage(2);
					}
					clickCount=0;
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
	};
	/**
	 * 此handle的目的主要是为了将接口在主线程中触发
	 * ，为了安全起见把接口放到主线程触发
	 */
	Handler mhHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(msg.what==1){//单击
				headSetListener.onClick();
			}else if (msg.what==2) {//双击
				headSetListener.onDoubleClick();
			}else if (msg.what==3) {//三连击
				headSetListener.onThreeClick();
			}
		}
	};
		
}
