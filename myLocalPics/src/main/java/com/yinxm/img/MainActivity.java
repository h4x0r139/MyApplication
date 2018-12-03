package com.yinxm.img;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yinxm.img.browse.activity.AlbumsActivity;

public class MainActivity extends Activity {
    String[] items = {
            "自定义图片浏览器",
            "Test1Activity-ContentPrivder获取单张图片并裁剪",
            "Test2Activity"};
    ListView listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, items));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, AlbumsActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, Test1Activity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, Test2Activity.class));
                        break;
                }
            }
        });
    }
}
