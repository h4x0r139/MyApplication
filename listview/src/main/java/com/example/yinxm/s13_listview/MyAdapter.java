package com.example.yinxm.s13_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

public class MyAdapter extends SimpleAdapter {
	private LayoutInflater inflater = null;
	private List<Map<String,Object>> styles = null;
	public List<Map<String, Object>> getStyles() {
		return styles;
	}

	public void setStyles(List<Map<String, Object>> styles) {
		this.styles = styles;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
//		if (convertView == null) {
//			convertView = inflater.inflate(R.layout.user1, null);
//		}
		View result = super.getView(position, convertView, parent);
		System.out.println("positon---->" + position);
		if(result != null){
			inflater.inflate(R.layout.user1, null);
		}
		return result;
	}



	public MyAdapter(Context context, List<? extends Map<String, ?>> data,
			int resource, String[] from, int[] to) {
		super(context, data, resource, from, to);
		inflater = LayoutInflater.from(context);
	}

}
