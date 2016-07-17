package com.yinxm.drawlayout_test;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
* @Description: 抽屉布局
* Author: yinxm
* Date: 2016/5/22 21:01
*/
public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    FrameLayout content_frame2;
    DrawerLayout drawerLayout;
//    ListView listView2;
    LinearLayout leftMenu;
    List<String> menuList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main2);

        initView();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        content_frame2.addView(view);
    }

    private void initView() {
        content_frame2 = (FrameLayout) findViewById(R.id.content_frame2);

        drawerLayout = (DrawerLayout) findViewById(R.id.draw_layout2);
//        listView2 = (ListView) findViewById(R.id.listView2);
        menuList = new ArrayList<String>();
        menuList.add("打开带有侧滑菜单的Activity");
        for (int i=0; i<5; i++) {
            menuList.add("菜单0"+i);
        }
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menuList);
//        listView2.setAdapter(adapter);
//        listView2.setOnItemClickListener(this);

        leftMenu = (LinearLayout) findViewById(R.id.leftMenu);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("my", "[Main2Activity.onItemClick], position="+position);

        if (position == 0) {
            startActivity(new Intent(this, Main3Activity.class));
        } else {
            //清空原有framlayout值
            if (content_frame2 != null) {
                content_frame2.removeAllViews();
            }
            // 动态插入一个Fragment到FrameLayout当中
            Fragment contentFragment = new ContentFragment();
            Bundle args = new Bundle();
            args.putString("text", menuList.get(position));
            contentFragment.setArguments(args);

            FragmentManager fm = getFragmentManager();
            fm.beginTransaction().replace(R.id.content_frame2, contentFragment)
                    .commit();

        }
//        drawerLayout.closeDrawer(listView2);
        drawerLayout.closeDrawer(leftMenu);


    }
}
