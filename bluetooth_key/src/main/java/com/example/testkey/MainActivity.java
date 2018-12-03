package com.example.testkey;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@SuppressLint("InlinedApi")
public class MainActivity extends Activity {
	private final static String LOG_TAG = "com.example.testkey";
	Context mContext;
	TextView mTextView;
	private CheckBox mCheckBox = null;
	public static boolean mIsEnableDSuspend = false;
	private final static int[] time = {10, 120, 125};
	private EditText connectTimeEditText;
	private EditText disconnectTimeEditText;
	private EditText disableTimeEditText;
	private String timeFile;
	private Button mButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d(LOG_TAG, "onCreate");
		mContext = this;
		displayMyself(mContext);
		mTextView = (TextView)findViewById(R.id.textView);

		IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
		filter.addAction(Intent.ACTION_SCREEN_ON);
		filter.addAction(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);
		// Power
		registerReceiver(mBatInfoReceiver, filter);
		// Home
		final IntentFilter homeFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
		registerReceiver(homePressReceiver, homeFilter);

		connectTimeEditText = (EditText) this.findViewById(R.id.connected_time);
		disconnectTimeEditText = (EditText) this.findViewById(R.id.disconnected_time);
		disableTimeEditText = (EditText) this.findViewById(R.id.disable_time);
		mButton = (Button) this.findViewById(R.id.edit_button);
		mButton.setText("编辑");

		timeFile = "time.txt";
		printToast("开始测试按键！");

		mCheckBox = (CheckBox) this.findViewById(R.id.enable_dsuspend_checkbox);
		mCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				mIsEnableDSuspend = isChecked;
				Intent intent = new Intent("android.intent.action.UPDATE_SUSPEND_TIME_BY_HAND");
				sendBroadcast(intent);
			}
		});

		// for service
		((AudioManager)getSystemService(AUDIO_SERVICE)).registerMediaButtonEventReceiver(new ComponentName(
				this,
				MusicIntentReceiver.class));

		// check intent
		Intent intent = getIntent();
		if (intent != null) {
			String act = getIntent().getAction();
			if (act != null) {
				if (act.equals(Intent.ACTION_VOICE_COMMAND)) {
					// voice command
					Log.d(LOG_TAG, "VOICE_COMMAND");
					printToast("get Key VOICE_COMMAND");
				} else if (act.equals("android.intent.action.CALL_PRIVILEGED")
						|| act.equals("android.intent.action.KANGEAR_LASTREDIAL_TO_VR")) {
					printToast("LAST_NUMBER_REDIAL(需要重定向:" + KeyService.isNeedRedirect(this) +")");
					if(KeyService.isNeedRedirect(this)) {
						startVoiceDial();
						displayMyself(mContext);
						finish();
					} else {
						printToast("DIAL_CUSTOM_NUMBER");
						dail(intent);
						displayMyself(mContext);
						finish();
					}
				} else {
					showMyself(mContext);
				}
			}
		}

		String content = getConfig(timeFile);
		if(content != null) {
			String ss[] = content.split(" ");
			if(ss != null && ss.length >= 3) {
				time[0] = Integer.valueOf(ss[0]);
				time[1] = Integer.valueOf(ss[1]);
				time[2] = Integer.valueOf(ss[2]);
			}
		}
		connectTimeEditText.setText(String.valueOf(time[0]));
		disconnectTimeEditText.setText(String.valueOf(time[1]));
		disableTimeEditText.setText(String.valueOf(time[2]));
		setEditTextEnable(false);
	}

	void updateConfig(String filename, String content) throws IOException {
		File file = new File(filename);
		file.delete();
		FileOutputStream outStream = openFileOutput(filename, Context.MODE_PRIVATE);
		Log.d("LOG_TAG", "write bytes:" + content.getBytes().length);
		outStream.write(content.getBytes());
		outStream.close();
	}

	String getConfig(String filename) {
		byte[] byteName = new byte[20];
		FileInputStream in;
		String ret = null;
		try {
			in = openFileInput(filename);
			int len = in.read(byteName);
			in.close();
			ret = new String(byteName, 0, len, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.d(LOG_TAG, "str:" + ret);
		return ret;
	}

	public static boolean isEnableDSuspend() {
		return mIsEnableDSuspend;
	}

	public static int[] getTime() {
		return time;
	}

	private void showMyself(Context context) {
		Window window=((Activity) context).getWindow();
		WindowManager.LayoutParams wl = window.getAttributes();
		wl.alpha=1.0f;
		window.setAttributes(wl);
	}

	private void displayMyself(Context context) {
		Window window=((Activity) context).getWindow();
		WindowManager.LayoutParams wl = window.getAttributes();
		wl.alpha=0.0f;
		window.setAttributes(wl);
	}

	private void startVoiceDial() {
		Intent intent = new Intent(Intent.ACTION_VOICE_COMMAND);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// Verify that the intent will resolve to an activity
		if (intent.resolveActivity(getPackageManager()) != null) {
		    startActivity(intent);
		} else {
			Log.e(LOG_TAG, "没有发现语音拨号器！");
		}
	}

	private void dail(Intent intent) {
		Intent intent2 = new Intent(Intent.ACTION_CALL, intent.getData());
		// Verify that the intent will resolve to an activity
		if (intent2.resolveActivity(getPackageManager()) != null) {
		    startActivity(intent2);
		} else {
			Log.e(LOG_TAG, "没有发现拨号器！");
		}
	}

	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.edit_button:
			if(mButton.getText().toString().equals("编辑")) {
				mButton.setText("保存");
				setEditTextEnable(true);
			} else {
				mButton.setText("编辑");
				setEditTextEnable(false);
				try {
					time[0] = Integer.valueOf(connectTimeEditText.getText().toString());
					time[1] = Integer.valueOf(disconnectTimeEditText.getText().toString());
					time[2] = Integer.valueOf(disableTimeEditText.getText().toString());
					updateConfig(timeFile, time[0] + " "
							+ time[1] + " " + time[2]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	void setEditTextEnable(boolean isEnable) {
		connectTimeEditText.setEnabled(isEnable);
		disconnectTimeEditText.setEnabled(isEnable);
		disableTimeEditText.setEnabled(isEnable);
	}

	@Override
	public void onDestroy() {

		// 解除注册 Power
		if (mBatInfoReceiver != null) {
			try {
				unregisterReceiver(mBatInfoReceiver);
			} catch (Exception e) {
				Log.e(LOG_TAG, "unregisterReceiver mBatInfoReceiver failure :"
						+ e.getCause());
			}
		};
		// 解除注册 Home
		if (homePressReceiver != null) {
			try {
				unregisterReceiver(homePressReceiver);
			} catch (Exception e) {
				Log.e(LOG_TAG,"unregisterReceiver homePressReceiver failure :"
								+ e.getCause());
			}
		};

		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		printToast(KeyService.parseKeyCode(keyCode));
		return true;
		//return super.onKeyDown(keyCode, event);
	}

	public void printToast(String str) {
		mTextView.setText(str);
		Log.i(LOG_TAG, str);
	}

	private final BroadcastReceiver homePressReceiver = new BroadcastReceiver() {
		final String SYSTEM_DIALOG_REASON_KEY = "reason";
		final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
				String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
				if (reason != null
						&& reason.equals(SYSTEM_DIALOG_REASON_HOME_KEY)) {
					// 自己随意控制程序，关闭...
				}
			}
		}

	};
	private final BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(final Context context, final Intent intent) {

			final String action = intent.getAction();
			if (Intent.ACTION_SCREEN_OFF.equals(action)) {
				printToast("get Key KEYCODE_POWER(KeyCode:26)-OFF");
			} else if(Intent.ACTION_SCREEN_ON.equals(action)){
				printToast("get Key KEYCODE_POWER(KeyCode:26)-NO");
			}
		}
	};
}
