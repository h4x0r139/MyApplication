package cn.itcast.mobilesafe.ui;

import java.util.List;

import cn.itcast.mobilesafe.R;
import cn.itcast.mobilesafe.domain.ContactInfo;
import cn.itcast.mobilesafe.engine.ContactInfoService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SelectContactInfo extends Activity {
	private ListView lv_select_contact = null;
	private List<ContactInfo> list = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_contact_info);
		lv_select_contact = (ListView) findViewById(R.id.lv_select_contact);
		ContactInfoService info = new ContactInfoService(this);
	    list = info.getContactInfo();
		lv_select_contact.setAdapter(new SelectContact());
		lv_select_contact.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String phone = list.get(position).getPhone();
				String name = list.get(position).getName();
				System.out.println("phone="+phone);
				Intent intent  = new Intent();
				intent.putExtra("number", phone);
				intent.putExtra("name", name);
				setResult(10, intent);
				
				finish();
				
			}
			
		});
	}

	private class SelectContact extends BaseAdapter {

		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(SelectContactInfo.this,
					R.layout.select_contact_info_item, null);
			TextView tv_select_contact = (TextView) view
					.findViewById(R.id.tv_select_contact);
			TextView tv2_select_contact = (TextView) view
					.findViewById(R.id.tv2_select_contact);
			tv_select_contact.setText("ÐÕÃû:" + list.get(position).getName());
			tv2_select_contact.setText("µç»°:" + list.get(position).getPhone());
			return view;
		}

	}
}
