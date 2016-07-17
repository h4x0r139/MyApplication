package cn.itcast.mobilesafe.ui;

import cn.itcast.mobilesafe.R;
import cn.itcast.mobilesafe.service.AddressService;
import cn.itcast.mobilesafe.service.IService;
import cn.itcast.mobilesafe.service.WatchDogService;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LockScreenActivity extends Activity implements OnClickListener {
    private Intent intent = null;
    private PackageManager pm = null;
    private ImageView app_icon = null;
    private EditText edit = null;
    private Button ok = null;
    private IService iService;
    private MyConn myconn;
    private String packname;
    private SharedPreferences sp = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.app_lock_passwd_dialog);
		Intent intent = new Intent(this,WatchDogService.class);
		myconn = new MyConn();
		bindService(intent, myconn, BIND_AUTO_CREATE);
		app_icon = (ImageView) findViewById(R.id.app_icon);
		packname = getIntent().getStringExtra("packagename");
		ok = (Button) findViewById(R.id.ok);
		
		edit = (EditText) findViewById(R.id.edit);
        sp = getSharedPreferences("config", Context.MODE_PRIVATE);
		
		ApplicationInfo appinfo;
		try {
			appinfo = getPackageManager().getPackageInfo(packname, 0).applicationInfo;
			Drawable appicon = appinfo.loadIcon(getPackageManager());
			app_icon.setImageDrawable(appicon);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ok.setOnClickListener(this);
	}
	//点击确认
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ok:
			String number = edit.getText().toString().trim();
	    	if("".equals(number)){
	    		Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
	    		edit.startAnimation(shake);
	    	}else{
                 String pwd = sp.getString("pwd", "");
                 if(number.equals(pwd)){
                	 Toast.makeText(this, "密码正确", 0).show();
                	// 通知看门狗 临时的取消对这个程序的保护
     				iService.callAppProtectStop(packname);
     				finish();
                 }else{
                	 Toast.makeText(this, "请输入手机防盗密码错误", 0).show();
                 }
	    	}
			break;

		default:
			break;
		}
		
	}
    private class MyConn implements ServiceConnection{

		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			System.out.println("bind ----- success ");
			iService = (IService)service;
		}

		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    //销毁的时候解除绑定，不然控制台报错，看的不爽
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unbindService(myconn);
	}
}















