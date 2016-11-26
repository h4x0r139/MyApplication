package com.example.testgridview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {
	private GridView gview;
	private List<Map<String, Object>> data_list;
	private SimpleAdapter sim_adapter;
	// 图片封装为一个数组
	private int[] icon = { R.drawable.address_book, R.drawable.calendar,
			R.drawable.camera, R.drawable.clock, R.drawable.games_control,
			R.drawable.messenger, R.drawable.ringtone, R.drawable.settings,
			R.drawable.speech_balloon, R.drawable.weather, R.drawable.world,
			R.drawable.youtube , R.drawable.ic_launcher};
	private String[] iconName = {"通讯录", "日历", "照相机", "时钟", "游戏", "短信", "铃声",
			"设置", "语音", "天气", "浏览器", "视频","测试Main2Activity焦点" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		gview = (GridView) findViewById(R.id.gview);
		//新建List
		data_list = new ArrayList<Map<String, Object>>();
		//获取数据
		getData();
		//新建适配器
		String [] from ={"image","text"};
		int [] to = {R.id.image,R.id.text};
		sim_adapter = new SimpleAdapter(this, data_list, R.layout.item, from, to);
		//配置适配器
		gview.setAdapter(sim_adapter);
		gview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Log.d("yinxm", "onItemClick position="+position);
				if (position == 12) {
					startActivity(new Intent(getApplicationContext(), Main2Activity.class));
				}
			}
		});
	}

	
	
	public List<Map<String, Object>> getData(){		
		//cion和iconName的长度是相同的，这里任选其一都可以
		for(int i=0;i<icon.length;i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("image", icon[i]);
			map.put("text", iconName[i]);
			data_list.add(map);
		}
			
		return data_list;
	}
	

}
