package com.example.yinxm.s19_broadcastreceiver;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TestBC2Activity extends Activity {
	/** Called when the activity is first created. */
	private Button registerButton = null;
	private Button unregisterButton = null;
	private SMSReceiver smsReceiver = null;

	private static final String SMS_ACTION = "android.provider.Telephony.SMS_RECEIVED";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);
		registerButton = (Button)findViewById(R.id.register);
		registerButton.setOnClickListener(new RegisterReceiverListener());
		unregisterButton = (Button)findViewById(R.id.unregister);
		unregisterButton.setOnClickListener(new UnRegisterReceiverListener());
	}

	class RegisterReceiverListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			//生成一个BroiadcastReceiver对象
			smsReceiver = new SMSReceiver();
			//生成一个IntentFilter对象
			IntentFilter filter = new IntentFilter();
			//为IntentFilter添加一个Action
			filter.addAction(SMS_ACTION);
			//将BroadcastReceiver对象注册到系统当中
			TestBC2Activity.this.registerReceiver(smsReceiver, filter);
		}

	}

	class UnRegisterReceiverListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			//解除BroadcastReceiver对象的注册
			TestBC2Activity.this.unregisterReceiver(smsReceiver);
		}

	}
}