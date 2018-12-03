package com.example.yinxm.s20_wifi;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class WifiActivity extends Activity {
	/** Called when the activity is first created. */
	private Button startButton = null;
	private Button stopButton = null;
	private Button checkButton = null;
	private WifiManager wifiManager = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		startButton = (Button)findViewById(R.id.startWifi);
		stopButton = (Button)findViewById(R.id.stopWifi);
		checkButton = (Button)findViewById(R.id.checkWifi);
		startButton.setOnClickListener(new StartWifiListener());
		stopButton.setOnClickListener(new StopWifiListener());
		checkButton.setOnClickListener(new CheckWifiListener());
	}
	class StartWifiListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			wifiManager = (WifiManager)WifiActivity.this.getSystemService(Context.WIFI_SERVICE);
			wifiManager.setWifiEnabled(true);
			System.out.println("wifi state --->" + wifiManager.getWifiState());
			Toast.makeText(WifiActivity.this, "当前Wifi网卡状态为" + wifiManager.getWifiState(), Toast.LENGTH_SHORT).show();
		}
	}
	class StopWifiListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			wifiManager = (WifiManager)WifiActivity.this.getSystemService(Context.WIFI_SERVICE);
			wifiManager.setWifiEnabled(false);
			System.out.println("wifi state --->" + wifiManager.getWifiState());
			Toast.makeText(WifiActivity.this, "当前Wifi网卡状态为" + wifiManager.getWifiState(), Toast.LENGTH_SHORT).show();
		}

	}

	class CheckWifiListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			wifiManager = (WifiManager)WifiActivity.this.getSystemService(Context.WIFI_SERVICE);
			System.out.println("wifi state --->" + wifiManager.getWifiState());
			Log.d("my","wifi state=");
			Toast.makeText(WifiActivity.this, "当前Wifi网卡状态为" + wifiManager.getWifiState(), Toast.LENGTH_SHORT).show();
		}

	}
}