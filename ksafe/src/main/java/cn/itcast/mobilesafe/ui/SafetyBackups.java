package cn.itcast.mobilesafe.ui;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.mobilesafe.R;
import cn.itcast.mobilesafe.adapter.SafetyBackupsAdapter;
import cn.itcast.mobilesafe.domain.MainItem;
import cn.itcast.mobilesafe.provider.SMSProvider;
import cn.itcast.mobilesafe.service.RecoverService;
import cn.itcast.mobilesafe.service.SMSService;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class SafetyBackups extends Activity {
    private ListView iv_call_recode = null;
    private SafetyBackupsAdapter adapter = null;
    private List<MainItem> list = null;
    private SMSProvider mSmsInfoService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.safety_backups_listview);
		iv_call_recode = (ListView) findViewById(R.id.iv_call_recode);
		list = getItem();
		adapter = new SafetyBackupsAdapter(this,list);
		iv_call_recode.setAdapter(adapter);
		iv_call_recode.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				int itemId = (int) iv_call_recode.getItemIdAtPosition(position);
				//调用服务备份短信
				if(itemId==2){
					System.out.println("444");
					Intent smsIntent = new Intent(SafetyBackups.this,SMSService.class);
					startService(smsIntent);
				}else if(itemId==3){
					//读取备份的xml文件 
					//解析文件里面的信息 
					//插入到短信应用里面
					final ProgressDialog pd = new ProgressDialog(SafetyBackups.this);
					pd.setCancelable(false);
					pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
					pd.setMessage("正在还原短信");
					pd.show();
					mSmsInfoService = new SMSProvider(SafetyBackups.this);
					new Thread(){
						@Override
						public void run() {
							try {
								mSmsInfoService.restoreSms("/sdcard/smsbackup.xml",pd);
								pd.dismiss();
								Looper.prepare();
								Toast.makeText(getApplicationContext(), "还原成功", 0).show();
								Looper.loop();
							} catch (Exception e) {
								e.printStackTrace();
								pd.dismiss();
								Looper.prepare();
								Toast.makeText(getApplicationContext(), "还原失败", 0).show();
								Looper.loop();
							}
						}
					}.start();
				}
			}
			
		});
	}
	private List<MainItem> getItem(){
		List<MainItem> listItem = new ArrayList<MainItem>();
		MainItem mainItem1 = new MainItem("备份数据","将通讯录备份到云端服务器",getResources().getDrawable(R.drawable.kn_sync_contact));
		listItem.add(mainItem1);
		MainItem mainItem2 = new MainItem("恢复数据","将通讯录恢复到手机",getResources().getDrawable(R.drawable.kn_sync_restore));
		listItem.add(mainItem2);
		
//		MainItem mainItem3 = new MainItem("备份数据","将短信备份到云端服务器",getResources().getDrawable(R.drawable.ic_firewall_sms_desk_setting));
//		listItem.add(mainItem3);
//		MainItem mainItem4 = new MainItem("恢复数据","将短信恢复到手机",getResources().getDrawable(R.drawable.ic_firewall_sms_desk_setting));
//		listItem.add(mainItem4);
		MainItem mainItem3 = new MainItem("备份数据","将短信备份到手机存储卡",getResources().getDrawable(R.drawable.kn_sync_local));
		listItem.add(mainItem3);
		MainItem mainItem4 = new MainItem("恢复数据","将短信从存储卡恢复到手机",getResources().getDrawable(R.drawable.kn_sync_local));
		listItem.add(mainItem4);
		
		return listItem;
	}
}
