package cn.itcast.mobilesafe.adapter;

import java.util.List;

import cn.itcast.mobilesafe.R;
import cn.itcast.mobilesafe.domain.Prevention;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
//本源码免费下载自 http://javaapk.com
public class PreventionItem extends BaseAdapter {
    private List<Prevention> listItem = null;
    private Context context = null;
    private ImageView iv_pervention_item = null;
    private ImageView iv_pervention_item2 = null;
    private TextView tv_pervention_item = null;
	public PreventionItem(List<Prevention> listItem, Context context) {
		
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
		View view = View.inflate(context, R.layout.prevention_item, null);
		iv_pervention_item = (ImageView) view.findViewById(R.id.iv_pervention_item);
		iv_pervention_item2 = (ImageView) view.findViewById(R.id.iv_pervention_item2);
		tv_pervention_item = (TextView) view.findViewById(R.id.tv_pervention_item);
		iv_pervention_item.setImageDrawable(listItem.get(position).getIcon1());
		iv_pervention_item2.setImageDrawable(listItem.get(position).getIcon2());
		tv_pervention_item.setText(listItem.get(position).getPreventionItem());
		return view;
	}

}
