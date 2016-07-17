package cn.itcast.mobilesafe.adapter;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.mobilesafe.R;
import cn.itcast.mobilesafe.domain.MainItem;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
//本源码免费下载自 http://javaapk.com
public class MainAdapterItem extends BaseAdapter {
    private List<MainItem> listItem = null;
    private View view = null;
	private Context context = null;
    private ImageView iv_main_below = null;
	private TextView tv_main_below = null;
    private TextView tv_main_below2 = null;
	public MainAdapterItem(List<MainItem> listItem, Context context) {
		
		this.listItem = listItem;
		this.context = context;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return listItem.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listItem.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		view = View.inflate(context, R.layout.main_below_item, null);
		iv_main_below = (ImageView) view.findViewById(R.id.iv_main_below);
		tv_main_below = (TextView) view.findViewById(R.id.tv_main_below);
		tv_main_below2 = (TextView) view.findViewById(R.id.tv_main_below2);
		tv_main_below.setText(listItem.get(position).getItem().toString());
		tv_main_below2.setText(listItem.get(position).getItem2().toString());
		iv_main_below.setImageDrawable(listItem.get(position).getIcon());
		return view;
	}

}
