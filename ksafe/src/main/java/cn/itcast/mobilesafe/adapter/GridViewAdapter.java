package cn.itcast.mobilesafe.adapter;

import cn.itcast.mobilesafe.R;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
//本源码免费下载自 http://javaapk.com
public class GridViewAdapter extends BaseAdapter {
	private Context context = null;
	private SharedPreferences sp = null;
    private static String names [] = {"手机防盗","进程管理","垃圾清理","软件管理","安全备份","程序锁","电池保养","通信管理","软件推荐"};
    private static int icons [] = {R.drawable.ic_firewall_location_query,R.drawable.main_protection_icon,R.drawable.main_clean_icon,
    	R.drawable.main_software_icon,R.drawable.kn_sync_history,R.drawable.main_private_space_icon,
    	R.drawable.main_battery_icon,R.drawable.main_sms_icon,R.drawable.main_software_recommand_icon};
	
    public GridViewAdapter(Context context) {
    
		this.context = context;
		sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return names.length;
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		View view = View.inflate(context, R.layout.main_item, null);
		ImageView iv_main_item = (ImageView) view.findViewById(R.id.iv_main_item);
		TextView tv_main_item = (TextView) view.findViewById(R.id.tv_main_item);
		iv_main_item.setImageResource(icons[position]);
		tv_main_item.setText(names[position]);
		if(position==0){
			String name = sp.getString("lost_name", "");
			if(!"".equals(name)){
				tv_main_item.setText(name);
			}
		}
		return view;
	}

}
