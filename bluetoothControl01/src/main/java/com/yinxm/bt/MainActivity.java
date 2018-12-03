package com.yinxm.bt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jph.lc.BtHeadSetActivity;

public class MainActivity extends Activity {
    String[] arrays = {"蓝牙耳机控制", "蓝牙状态检测", "蓝牙读取电话簿"};
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.listview);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrays);
        listview.setAdapter(arrayAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, BtHeadSetActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, BtStateActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, ReadPhoneBookActivity.class));
                        break;
                }
            }
        });


    }
}
