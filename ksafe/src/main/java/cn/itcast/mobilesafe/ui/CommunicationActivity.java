package cn.itcast.mobilesafe.ui;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.mobilesafe.R;
import cn.itcast.mobilesafe.adapter.CommunicationAdapter;
import cn.itcast.mobilesafe.domain.Communication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class CommunicationActivity extends Activity {
	private ListView lv_communication;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.lv_communication);
		lv_communication = (ListView) findViewById(R.id.lv_communication);
		List<Communication> list = getCommunication();
		CommunicationAdapter adapter = new CommunicationAdapter(this,list);
		lv_communication.setAdapter(adapter);
		lv_communication.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			//获得通话管理对象	Communication = lv_communication.getItemAtPosition(position);
				if(position==0){
					System.out.println("短信管理");
				}else if(position==1){
					System.out.println("通话管理");
					Intent intent = new Intent(CommunicationActivity.this,Communication2Activity.class);
					startActivity(intent);
				}
				
			}
			
		});
	}

	private List<Communication> getCommunication() {
		List<Communication> list = new ArrayList<Communication>();

		Communication communication1 = new Communication("短信管理", "快捷短信.扣费查询",
				getResources().getDrawable(
						R.drawable.ic_firewall_sms_desk_setting));
		list.add(communication1);
		Communication communication2 = new Communication("通信管理",
				"ip拨号.号码归属地查询", getResources().getDrawable(
						R.drawable.firewall_tab_icon_phone));
		list.add(communication2);
		return list;
	}
}
