package cn.itcast.mobilesafe.ui;

import cn.itcast.mobilesafe.R;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class T4_DemoActivity extends Activity {

	/** Called when the activity is first created. */
	private ImageView iv_battery;
	private ImageView image;// 电池状态图片
	private TextView textCD;// 电池充电状态
	private TextView textRL;// 电池剩余容量
	private TextView textZT;// 电池状态
	private TextView textDY;// 电池电压mV
	private TextView textWD;// 电池温度
	private TextView textLX;// 电池类型
	private BroadcastReceiver myBroadcastReciver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			// 电池剩余容量
			int level = (int) (intent
					.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
					/ (float) intent.getIntExtra(BatteryManager.EXTRA_SCALE,
							100) * 100);
			textRL.setText(level + "%");
			//根据电量判断用什么图片
            if(level>45||level<=50){
            	iv_battery.setImageResource(R.drawable.battery9);
            }else if(level==0){
            	iv_battery.setImageResource(R.drawable.battery0);
            }else if(level>0||level<=10){
            	iv_battery.setImageResource(R.drawable.battery2);
            }else if(level>10||level<=15){
            	iv_battery.setImageResource(R.drawable.battery3);
            }else if(level>15||level<=20){
            	iv_battery.setImageResource(R.drawable.battery4);
            }else if(level>20||level<=25){
            	iv_battery.setImageResource(R.drawable.battery5);
            }else if(level>25||level<=30){
            	iv_battery.setImageResource(R.drawable.battery6);
            }else if(level>30||level<=35){
            	iv_battery.setImageResource(R.drawable.battery7);
            }else if(level>35||level<=45){
            	iv_battery.setImageResource(R.drawable.battery8);
            }else if(level>50||level<=60){
            	iv_battery.setImageResource(R.drawable.battery10);
            }else if(level>60||level<=65){
            	iv_battery.setImageResource(R.drawable.battery11);
            }else if(level>65||level<=70){
            	iv_battery.setImageResource(R.drawable.battery12);
            }else if(level>70||level<=75){
            	iv_battery.setImageResource(R.drawable.battery13);
            }else if(level>75||level<=80){
            	iv_battery.setImageResource(R.drawable.battery14);
            }else if(level>80||level<=85){
            	iv_battery.setImageResource(R.drawable.battery15);
            }else if(level>85||level<=90){
            	iv_battery.setImageResource(R.drawable.battery16);
            }else if(level>90||level<=100){
            	iv_battery.setImageResource(R.drawable.battery17);
            }
			// 电池当前使用状态
//			image.setImageResource(intent.getIntExtra(
//					BatteryManager.EXTRA_ICON_SMALL, 0));
			switch (intent.getIntExtra(BatteryManager.EXTRA_STATUS, 1)) {
			case BatteryManager.BATTERY_STATUS_CHARGING:
				if (intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 1) == BatteryManager.BATTERY_PLUGGED_AC)
					textCD.setText("充电器充电中");
				else
					textCD.setText("USB充电中");
				break;
			case BatteryManager.BATTERY_STATUS_DISCHARGING:
				textCD.setText("放电中");
				break;
			case BatteryManager.BATTERY_STATUS_FULL:
				textCD.setText("已充满");
				break;
			case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
				textCD.setText("未充满");
				break;
			}
			// 电池状态
			switch (intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 1)) {
			case BatteryManager.BATTERY_HEALTH_DEAD:
				textZT.setText("电池已损坏！");
				textZT.setTextColor(Color.RED);
				break;
			case BatteryManager.BATTERY_HEALTH_GOOD:
				textZT.setText("健康");
				break;
			case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
				textZT.setText("电压过高");
				break;
			case BatteryManager.BATTERY_HEALTH_OVERHEAT:
				textZT.setText("温度过高");
				break;
			case BatteryManager.BATTERY_HEALTH_UNKNOWN:
				textZT.setText("未知");
				break;
			case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
				textZT.setText("未知故障");
				break;
			}
			// 电池电压
			textDY.setText(intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 1)+"mV");
			// 电池温度
			textWD.setText((intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 1)/10.0)+"℃");
			// 电池类型
			textLX.setText(intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY));

		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.battery);
		textCD = (TextView) findViewById(R.id.textCD);
		textRL = (TextView) findViewById(R.id.textRL);
		textZT = (TextView) findViewById(R.id.textZT);
		textDY = (TextView) findViewById(R.id.textDY);
		textWD = (TextView) findViewById(R.id.textWD);
		textLX = (TextView) findViewById(R.id.textLX);
		image = (ImageView) findViewById(R.id.imageView1);
		iv_battery = (ImageView)findViewById(R.id.iv_battery);
		registerReceiver(myBroadcastReciver, new IntentFilter(
				Intent.ACTION_BATTERY_CHANGED));
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(myBroadcastReciver);
	}
}