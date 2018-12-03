package cn.itcast.mobilesafe.ui;

import java.util.List;

import cn.itcast.mobilesafe.R;
import cn.itcast.mobilesafe.db.dao.BlackNumberDao;
import cn.itcast.mobilesafe.domain.BlackNumber;
import cn.itcast.mobilesafe.engine.NumberAddressService2;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class Blacknumber extends Activity {
    private BlackNumberDao dao = null;
	private ListView lv_blacknumber = null;
	List<BlackNumber> numbers = null;
	private View view = null;
	private Button bt_blacknumber = null;
	private LinearLayout ll1 = null;
	private LinearLayout ll2 = null;
	private LinearLayout ll3 = null;
	private LinearLayout ll4 = null;
	private LinearLayout ll5 = null;
	private MyBlacknumber adapter = null; 
	private Dialog dialog = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.blacknumber);
		dao = new BlackNumberDao(this);
	    numbers = dao.getAllNumbers();
	    
		lv_blacknumber = (ListView) findViewById(R.id.lv_blacknumber);
		
		bt_blacknumber = (Button) findViewById(R.id.bt_blacknumber);
		/**
		 * ��listViewע�������Ĳ˵�
		 */
		registerForContextMenu(lv_blacknumber);
		
		
		/**
		 * ������
		 */
		bt_blacknumber.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 dialog = new Dialog(Blacknumber.this,R.style.MyDialog);
				View view = View.inflate(Blacknumber.this, R.layout.add_blacknumber, null);
				dialog.setContentView(view);
				dialog.show();
				/**
				 * ����ϵ�˵���
				 */
				ll1 = (LinearLayout) view.findViewById(R.id.tv_add_blacknumber1);
				ll1.setOnClickListener(new OnClickListener(){

					public void onClick(View v) {
						dialog.dismiss();
						Intent intent = new Intent(Blacknumber.this,SelectContactInfo.class);
					    //	startActivity(intent);
						
						startActivityForResult(intent, 0);
					//	finish();
					
						
					}
					
				});
				/**
				 * ��ͨ����¼����
				 */
				ll2 = (LinearLayout) view.findViewById(R.id.tv_add_blacknumber2);
				ll2.setOnClickListener(new OnClickListener(){

					public void onClick(View v) {
						dialog.dismiss();
						Intent intent = new Intent(Blacknumber.this,CallRecode.class);
					    	startActivity(intent);
					    	finish();
					}
					
				});
				/**
				 * �Ӷ��ż�¼����
				 */
				ll3 = (LinearLayout) view.findViewById(R.id.tv_add_blacknumber3);
				ll3.setOnClickListener(new OnClickListener(){

					public void onClick(View v) {
						// TODO Auto-generated method stub
						
					}
					
				});
				/**
				 * �ֶ�����
				 */
				ll4 = (LinearLayout) view.findViewById(R.id.tv_add_blacknumber4);
				ll4.setOnClickListener(new OnClickListener(){

					public void onClick(View v) {
						// TODO Auto-generated method stub
						
					}
					
				});
				/**
				 * �����������
				 */
				ll5 = (LinearLayout) view.findViewById(R.id.tv_add_blacknumber5);
				ll5.setOnClickListener(new OnClickListener(){

					public void onClick(View v) {
						// TODO Auto-generated method stub
						
					}
					
				});
				dialog.setContentView(view);
				dialog.show();
				
			}
			
		});
		adapter = new MyBlacknumber();
		lv_blacknumber.setAdapter(adapter);
	}
	/**
	 * ���������Ĳ˵�
	 * 
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
	  super.onCreateContextMenu(menu, v, menuInfo);
	  MenuInflater inflater = getMenuInflater();
	  inflater.inflate(R.menu.context_menu, menu);
	}
	/**
	 * ѡ�������Ĳ˵���Ŀ
	 */
	 @Override
		public boolean onContextItemSelected(MenuItem item) {
		 AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		  int id = (int) info.id;
		  String number = numbers.get(id).getNumber();
		  switch (item.getItemId()) {
		case R.id.update_blacknumber:
			
			break;

        case R.id.delete_blacknumber:
        	dao.delete(number);
  		  // ���»�ȡ����������
  		  numbers = dao.getAllNumbers();
  		  //  ֪ͨlistview���½���
  		  adapter.notifyDataSetChanged();
			break;
		}
			return super.onContextItemSelected(item);
		}

	 /**
     * ����ҳ����ɺ󣬵��õķ���
     */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==10){
			if(data!=null){
				String number = data.getStringExtra("number");
				String name = data.getStringExtra("name");
				System.out.println("xxxx"+number);
				dao.add(number, name);
				numbers = dao.getAllNumbers();
				adapter.notifyDataSetChanged();
			}
		}
		
	}
	
   
	private class MyBlacknumber extends BaseAdapter{

		public int getCount() {
			// TODO Auto-generated method stub
			return numbers.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return numbers.get(position);
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			
			view = View.inflate(Blacknumber.this, R.layout.blacknumber_item, null);
			ImageView iv_blacknumber =(ImageView) view.findViewById(R.id.iv_blacknumber);
			TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
			TextView tv_number = (TextView) view.findViewById(R.id.tv_number);
			TextView tv_show_address = (TextView) view.findViewById(R.id.tv_show_address);
			
			tv_name.setText(numbers.get(position).getName());
			tv_number.setText(numbers.get(position).getNumber());
			
			String address = NumberAddressService2.getAddress(numbers.get(position).getNumber());
			tv_show_address.setText(address);
			
			iv_blacknumber.setImageResource(R.drawable.desk_sms_btn_trash_normal);
			return view;
		}
    	
    }
}
