package com.yika.recorder.ecarx;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.yika.recorder.R;


public class DialogManager {

	/**
	 * 以下为dialog的初始化控件，包括其中的布局文件
	 */

	private Dialog mDialog;

	private ImageView mShow;
	private TextView mShowText;

	private Context mContext;

	public DialogManager(Context context) {
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	public void showRecordingDialog() {
		// TODO Auto-generated method stub

		mDialog = new Dialog(mContext, R.style.Theme_audioDialog);
		Window window = mDialog.getWindow();
		window.setGravity(Gravity.TOP);
		// 用layoutinflater来引用布局
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View view = inflater.inflate(R.layout.dialog_manager, null);
		mDialog.setContentView(view);

		mShow= (ImageView) mDialog.findViewById(R.id.show_voice);
		mShowText= (TextView) mDialog.findViewById(R.id.show_text);
		mDialog.show();
		
	}

	/**
	 * 设置正在录音时的dialog界面
	 */
	public void recording() {
		if (mDialog != null && mDialog.isShowing()) {
			mShow.setVisibility(View.VISIBLE);
			mShowText.setText("手指上滑，取消发送");
		}
	}

	/**
	 * 取消界面
	 */
	public void wantToCancel() {
		// TODO Auto-generated method stub
		if (mDialog != null && mDialog.isShowing()) {
			mShow.setImageResource(R.mipmap.chat_send_cancel);
			mShowText.setText("松开手指，取消发送");
		}

	}

	// 时间过短
	public void tooShort() {
		// TODO Auto-generated method stub
		if (mDialog != null && mDialog.isShowing()) {
			mShow.setImageResource(R.mipmap.chat_send_time_short);
			mShowText.setText("录音时间太短");
		}
	}

	// 隐藏dialog
	public void dimissDialog() {
		// TODO Auto-generated method stub

		if (mDialog != null && mDialog.isShowing()) {
			mDialog.dismiss();
			mDialog = null;
		}

	}

	public void updateVoiceLevel(int level) {
		// TODO Auto-generated method stub

		if (mDialog != null && mDialog.isShowing()) {
			//通过level来找到图片的id，也可以用switch来寻址，但是代码可能会比较长
			int resId = mContext.getResources().getIdentifier("v" + level,
					"mipmap", mContext.getPackageName());
			
			mShow.setImageResource(resId);
			mShowText.setText("手指上滑，取消发送");
		}

	}

}
