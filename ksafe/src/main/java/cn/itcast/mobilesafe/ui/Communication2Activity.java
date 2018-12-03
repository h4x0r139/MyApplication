package cn.itcast.mobilesafe.ui;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.mobilesafe.R;
import cn.itcast.mobilesafe.adapter.CommunicationAdapter;
import cn.itcast.mobilesafe.domain.Communication;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioButton;
import android.widget.Toast;

public class Communication2Activity extends Activity {
	private ListView lv_communication;
	private SharedPreferences sp = null;
	String auto_num = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.lv_communication);
		lv_communication = (ListView) findViewById(R.id.lv_communication);
		List<Communication> list = getCommunication();
		CommunicationAdapter adapter = new CommunicationAdapter(this,list);
		sp = getSharedPreferences("config", Context.MODE_PRIVATE);
		lv_communication.setAdapter(adapter);
		lv_communication.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			//获得通话管理对象	Communication = lv_communication.getItemAtPosition(position);
				if(position==0){
					System.out.println("IP拨号");
					
					final Dialog dialog = new Dialog(Communication2Activity.this,R.style.MyDialog);
					View v = View.inflate(Communication2Activity.this, R.layout.auto_ip_dialing_item3, null);
					final RadioButton rb_single_choice_dialog1 = (RadioButton) v.findViewById(R.id.rb_single_choice_dialog1);
					final RadioButton rb_single_choice_dialog2 = (RadioButton)v.findViewById(R.id.rb_single_choice_dialog2);
					final RadioButton rb_single_choice_dialog3 = (RadioButton)v.findViewById(R.id.rb_single_choice_dialog3);
					Button bt = (Button) v.findViewById(R.id.bt);
					final LinearLayout ll_auto_ip_num = (LinearLayout) v.findViewById(R.id.ll_auto_ip_num);
					final EditText et_auto_ip_num = (EditText) v.findViewById(R.id.et_auto_ip_num);
					String num = sp.getString("auto_ip_dialing_num", "");
					if(num.equals("")||num.equals(null)){
						
					}else{
						if("17951".equals(num)){
							rb_single_choice_dialog1.setChecked(true);
						}else if("12593".equals(num)){
							rb_single_choice_dialog2.setChecked(true);
						}
					}
					bt.setOnClickListener(new OnClickListener(){

						public void onClick(View v) {
							if(rb_single_choice_dialog3.isChecked()){
								auto_num.equals(null);
								Toast.makeText(Communication2Activity.this, "IP自动拨号设置波能为空", 0).show();
							}else{
								dialog.dismiss();
								return;
	
							}
													}
						
					});
					rb_single_choice_dialog1.setOnClickListener(new OnClickListener(){

						public void onClick(View v) {
							if(rb_single_choice_dialog1.isChecked()){
								rb_single_choice_dialog2.setChecked(false);
								rb_single_choice_dialog3.setChecked(false);
								
							}
							Editor editor = sp.edit();
							editor.putString("auto_ip_dialing_num", "17951");
							editor.commit();
							return;
						}
						
					});
					rb_single_choice_dialog2.setOnClickListener(new OnClickListener(){

					
							public void onClick(View v) {
								if(rb_single_choice_dialog2.isChecked()){
									rb_single_choice_dialog1.setChecked(false);
									rb_single_choice_dialog3.setChecked(false);
									
								}
								Editor editor = sp.edit();
								editor.putString("auto_ip_dialing_num", "12593");
								editor.commit();
								return;
							}
							
						
						
					});
					rb_single_choice_dialog3.setOnClickListener(new OnClickListener(){

						public void onClick(View v) {
							if(rb_single_choice_dialog3.isChecked()){
								rb_single_choice_dialog1.setChecked(false);
								rb_single_choice_dialog2.setChecked(false);
								
							}
							ll_auto_ip_num.setVisibility(View.VISIBLE);
						    auto_num = et_auto_ip_num.getText().toString().trim();
							if(!(auto_num.equals(""))||!(auto_num.equals(null))){
								Editor editor = sp.edit();
								editor.putString("auto_ip_dialing_num", auto_num);
								editor.commit();
								return;
							}
						}
						
					});
					dialog.setContentView(v);
					dialog.show();
					
				}else if(position==1){
					System.out.println("号码归属地查询");
					Intent intent = new Intent(Communication2Activity.this,QueryAddressActivity.class);
					startActivity(intent);
					finish();
					return;
				}else if(position==2){
					System.out.println("电话归属地显示");
					
					Intent intent = new Intent(Communication2Activity.this,QueryAddressShow.class);
					startActivity(intent);
					finish();
					return;
				}else if(position==3){
					System.out.println("常用号码显示");
					Intent intent = new Intent(Communication2Activity.this,CommonNumActivity.class);
					startActivity(intent);
					finish();
					return;
				}else if(position==4){
					System.out.println("一键呼叫");
				}
				
			}
			
		});
	}
	private List<Communication> getCommunication() {
		List<Communication> list = new ArrayList<Communication>();

		Communication communication1 = new Communication("ip拨号", "为您节省长途资费",getResources().getDrawable(R.drawable.kn_malware_scan_fast));
		list.add(communication1);
		Communication communication2 = new Communication("号码归属地查询","查询电话号码归属地信息", getResources().getDrawable(R.drawable.kn_malware_ignore));
		list.add(communication2);
		Communication communication3 = new Communication("电话归属地显示", "设置来去电归属地信息",getResources().getDrawable(R.drawable.kn_sync_account));
		list.add(communication3);
		Communication communication4 = new Communication("常用号码查询", "常用餐饮,银行及其他服务电话",getResources().getDrawable(R.drawable.kn_sync_contact));
		list.add(communication4);
		Communication communication5 = new Communication("一键呼叫", "将常用联系人号码设置到桌面",getResources().getDrawable(R.drawable.ic_firewall_sms_desk_setting));
		list.add(communication5);
		return list;
	}
}
