package cn.itcast.mobilesafe.adapter;

import java.util.List;

import cn.itcast.mobilesafe.R;
import cn.itcast.mobilesafe.domain.MainItem;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SafetyBackupsAdapter extends BaseAdapter{
	private Context context = null;
    private List<MainItem> list = null;
	public SafetyBackupsAdapter(Context context,List<MainItem> list) {
		super();
		this.context = context;
		this.list = list;
	}

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
		View view = View.inflate(context, R.layout.safety_backups, null);
		TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
		TextView tv_number = (TextView) view.findViewById(R.id.tv_number);
		ImageView iv_blacknumber = (ImageView) view.findViewById(R.id.iv_blacknumber);
		
		iv_blacknumber.setImageDrawable(list.get(position).getIcon());
		tv_name.setText(list.get(position).getItem());
		tv_number.setText(list.get(position).getItem2());
		return view;
	}
}
