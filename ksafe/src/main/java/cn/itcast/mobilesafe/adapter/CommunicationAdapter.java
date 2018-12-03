package cn.itcast.mobilesafe.adapter;

import java.util.List;

import cn.itcast.mobilesafe.R;
import cn.itcast.mobilesafe.domain.Communication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
//本源码免费下载自 http://javaapk.com
public class CommunicationAdapter extends BaseAdapter {
	private Context context = null;
	private List<Communication> list = null;
	private View view = null;
	private TextView tv_Communication = null;
	private TextView tv_Communication2 = null;
	private ImageView iv_communication = null;
	public CommunicationAdapter(Context context, List<Communication> list) {

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
		view = View.inflate(context, R.layout.communication, null);
		iv_communication = (ImageView) view.findViewById(R.id.iv_communication);
		tv_Communication = (TextView) view.findViewById(R.id.tv_Communication);
		tv_Communication2 = (TextView) view.findViewById(R.id.tv_Communication2);
		
		tv_Communication.setText(list.get(position).getItem().toString());
		tv_Communication2.setText(list.get(position).getItem2().toString());
		iv_communication.setImageDrawable(list.get(position).getIcon());
		return view;
	}

}
