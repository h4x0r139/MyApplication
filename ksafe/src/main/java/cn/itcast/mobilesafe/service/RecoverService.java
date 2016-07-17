package cn.itcast.mobilesafe.service;

import cn.itcast.mobilesafe.provider.SMSProvider;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Toast;

public class RecoverService extends Service {
    private SMSProvider sms = null;
    private ProgressDialog pd = null;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
    /**
     * 恢复所有的短信
     */
	@Override
	public void onCreate() {
		sms =  new SMSProvider(this);
	    pd = new ProgressDialog(this);
		pd.setCancelable(false);
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pd.setMessage("正在还原短信");
	//	pd.show();
		super.onCreate();
		new Thread(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();

				
				try {
					sms.restoreSms("/sdcard/smsbackup.xml",pd);
					pd.dismiss();
					Looper.prepare();
					Toast.makeText(getApplicationContext(), "还原成功", 0).show();
					Looper.loop();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					pd.dismiss();
					Looper.prepare();
					Toast.makeText(getApplicationContext(), "还原失败", 1).show();
					Looper.loop();
				}
			}
			
		}.start();
	}
   
}
