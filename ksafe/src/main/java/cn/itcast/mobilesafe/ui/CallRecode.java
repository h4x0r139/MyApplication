package cn.itcast.mobilesafe.ui;

import java.util.List;

import cn.itcast.mobilesafe.R;
import cn.itcast.mobilesafe.db.dao.BlackNumberDao;
import cn.itcast.mobilesafe.domain.CallRecord;
import cn.itcast.mobilesafe.provider.CallRecordProcider;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class CallRecode extends Activity implements OnClickListener {
    private ListView iv_call_recode = null;
    private MyAdapter adapter = null;
    private CallRecordProcider service = null;
    private List<CallRecord> infos = null;
    private View view = null;
    private Button bt_call_recode = null;
    private CallRecord callRecord = null;
    private BlackNumberDao dao = null;
    private Button bt_call_cancel = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.call_recode_listview);
		iv_call_recode = (ListView) findViewById(R.id.iv_call_recode);
		bt_call_recode = (Button) findViewById(R.id.bt_call_recode);
		callRecord = new CallRecord();
		 service = new CallRecordProcider(this);
	 	infos =  service.queryCallLog();
		adapter = new MyAdapter();
		iv_call_recode.setAdapter(adapter);
		bt_call_recode.setOnClickListener(this);
		dao = new BlackNumberDao(this);
		bt_call_cancel = (Button) findViewById(R.id.bt_call_cancel);
		iv_call_recode.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			 callRecord = (CallRecord) iv_call_recode.getItemAtPosition(position);
			 CheckBox cb_call_recode = (CheckBox) view.findViewById(R.id.cb_call_recode);
			
			 if(callRecord.isIschecked()){
				 callRecord.setIschecked(false);
				 cb_call_recode.setChecked(false);	 
			 }else{
				 callRecord.setIschecked(true);
				 cb_call_recode.setChecked(true);
			 }
			
//			 view.setTag(callRecord);
			}
			
		});
	}
    private BlackNumberDao BlackNumberDao(CallRecode callRecode) {
		// TODO Auto-generated method stub
		return null;
	}
	private class MyAdapter extends BaseAdapter{

		public int getCount() {
			// TODO Auto-generated method stub
			return infos.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return infos.get(position);
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			
			
			view = View.inflate(CallRecode.this, R.layout.call_recode, null);
			
			TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
			TextView tv_number = (TextView) view.findViewById(R.id.tv_number);
			TextView tv_address = (TextView) view.findViewById(R.id.tv_address);
			TextView tv_date = (TextView) view.findViewById(R.id.tv_date);
			CheckBox cb_call_recode = (CheckBox) view.findViewById(R.id.cb_call_recode);
			
			tv_name.setText(infos.get(position).getName());
			tv_number.setText(infos.get(position).getNumber());
			tv_address.setText(infos.get(position).getAddress());
			tv_date.setText(infos.get(position).getDate());
			
			return view;
		}
    	
    }
    /**
     * 点击确定把通话记录的人导入到黑名单中
     */
	public void onClick(View v) {
		// TODO Auto-generated method stub
		for(CallRecord info : infos){
			if(info.isIschecked()){
				String number = info.getNumber();
				String name = info.getName();
				dao.add(number, name);
			}
		}
		Intent intent = new Intent(this,Blacknumber.class);
		startActivity(intent);
		finish();
	}
}
