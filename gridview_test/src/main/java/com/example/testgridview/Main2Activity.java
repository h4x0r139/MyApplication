package com.example.testgridview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class Main2Activity extends Activity {
    GridView gridView;
    String[] items = {"第1个","第2个","第3个","第4个","第5个","第6个" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        gridView = (GridView) findViewById(R.id.gridView);
        Log.d("yinxm", "com.example.testgridview.Main2Activity gridView="+gridView);
        MyAdapter adapter = new MyAdapter();
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//             GridView中的item布局 使用Button的话，GridView item的点击事件，将不会触发，点击事件默认被Button消费了
                Log.d("yinxm", "onItemClick position="+position);
            }
        });
    }
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView =   View.inflate(getApplicationContext(), R.layout.item_gridview, null);
            }
            TextView fmname = (TextView) convertView.findViewById(R.id.fm_name);
            fmname.setText(items[position]);
            return convertView;
        }
    }
}
