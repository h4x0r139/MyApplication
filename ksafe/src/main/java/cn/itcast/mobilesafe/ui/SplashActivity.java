package cn.itcast.mobilesafe.ui;

import java.io.File;

import cn.itcast.mobilesafe.R;
import cn.itcast.mobilesafe.domain.UpdataInfo;
import cn.itcast.mobilesafe.engine.DownLoadFileTask;
import cn.itcast.mobilesafe.engine.UpdataInfoService;
import cn.itcast.mobilesafe.service.MonitorService;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends Activity {
	private LinearLayout ll = null;
	private TextView tv_splash = null;
	private UpdataInfo info = null;
	private ProgressDialog pd = null;
	private String version = null;
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			isNeedUpdate();
			super.handleMessage(msg);
		}
		
	};
		
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.kn_app_start);
		//��ʼ�Ϳ���������
		Intent intent= new Intent(this,MonitorService.class); 
		startService(intent);
		version = getVession();
		ll = (LinearLayout) findViewById(R.id.ll_splash);
		tv_splash = (TextView) findViewById(R.id.tv_splash);
		pd = new ProgressDialog(this);
		pd.setTitle("��������.....");
		//������ʽˮƽǰ������ʽ
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		tv_splash.setText(version);
		new Thread(){

			@Override
			public void run() {
				try {
					sleep(2000);
					
					handler.sendEmptyMessage(0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				super.run();
			}
			
		}.start();
		
		
		
		AlphaAnimation aa = new AlphaAnimation(0.0f, 1.0f);
		aa.setDuration(2000);
		ll.startAnimation(aa);
		//��ɴ����ȫ����ʾ
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
	}
	private void isNeedUpdate() {
		UpdataInfoService service = new UpdataInfoService(this);
		try {
		  info = service.getUpdataInfo(R.string.updataURL);
			/**
			 * �汾����ͬ
			 */
			if(info.getVersion().equals(version)){
				System.out.println("�汾��ֱͬ�ӽ���������");
				 mainUI();
			}else{
				showUpdataDialog();
			}
		} catch (Exception e) {
			 mainUI();
			e.printStackTrace();
		}
	}
    /**
     * show���Ի���
     */
	private void showUpdataDialog() {
		Builder builder = new Builder(this);
		builder.setTitle("�汾����");
		builder.setMessage(info.getDescription());
		builder.setCancelable(false);
		builder.setPositiveButton("��", new OnClickListener(){

			public void onClick(DialogInterface dialog, int which) {
				System.out.println("����");
				//�ж�SD��״̬�Ƿ����
				
				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
					//�����Ǻ�ʱ�Ĺ�������Ҫ���뵽���߳�����ȥִ��
					DownLoadFileThreadTask download = new DownLoadFileThreadTask(info.getApkurl(),"/sdcard/new.apk");
				    pd.show();
				    new Thread(download).start();
				    mainUI();
				}else{
					
				}
				
			}
			
		});
		builder.setNegativeButton("��", new OnClickListener(){

			public void onClick(DialogInterface dialog, int which) {
				System.out.println("������������������");
				 mainUI();
			}
			
		});
		builder.create().show();
	}
	/**
	 *  ��װapk
	 *  
	 */
	private void installAPK(File file){
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
		finish();
		startActivity(intent);
	}
/**
 * ����APK
 */
	private class DownLoadFileThreadTask implements Runnable{
		String apkurl;
		String fileurl;
		public DownLoadFileThreadTask(String apkurl, String fileurl) {
			
			this.apkurl = apkurl;
			this.fileurl = fileurl;
		}
		public void run() {
			DownLoadFileTask down = new DownLoadFileTask();
			try {
				File file = down.getFile(apkurl, fileurl, pd);
				System.out.println("������ɣ����а�װ");
				pd.dismiss();
				installAPK(file);
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(getApplicationContext(), "�����ļ�ʧ��", 0).show();
				pd.dismiss();
				mainUI();
			}
		}
	}
	/**
	 * ��ð汾��
	 */
	private String getVession() {

		try {
			PackageManager pm = getPackageManager();
			// ��һ��������ð�������
			PackageInfo info = pm.getPackageInfo(getPackageName(), 0);
			return info.versionName;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
/**
 * ����������
 */
	private void mainUI(){
		Intent intetn = new Intent(this,MainActivity.class);
		startActivity(intetn);
		finish();
	};
}
