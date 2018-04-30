package com.yinxuming.dragview_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private String[] list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);


        list = new String[] {"自定义拖动DragView/拖拽回弹效果", "ViewDragHelper实现拖拽效果"};

        listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.item_list, list));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, CustomDragViewActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, ViewDragActivity.class));

                        break;
                    default:
                }
            }
        });



    }
}
