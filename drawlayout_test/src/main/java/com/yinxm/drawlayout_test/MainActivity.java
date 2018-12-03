package com.yinxm.drawlayout_test;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity implements OnItemClickListener{

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ArrayList<String> menuList;
    private ArrayAdapter<String> adapter;

    private String mtitle;

    //监听抽屉打开关闭，动态改变标题栏
    private ActionBarDrawerToggle mDrawerToggle;


//    private ActionBar mactionBar;
    private Toolbar toolbar;//取代ActionBar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mtitle = (String) getTitle();
        Log.d("my", "mtitle="+mtitle);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.draw_layout);
        mDrawerList = (ListView) findViewById(R.id.listView);
/*        mactionBar = getActionBar();//    <style name="AppBaseTheme" parent="android:Theme.Holo.Light.DarkActionBar">
        Log.d("my", "mactionBar="+mactionBar);*/

        menuList = new ArrayList<>();
        for (int i=0; i<6; i++) {
            menuList.add("菜单"+i);
        }
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menuList);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(this);

        //v4包已过时
//        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
//                R.drawable.ic_drawer, R.string.drawer_open,
//                R.string.drawer_close) {//ActionBarDrawerToggle 实现了DrawerLayout.DrawerListener
//        toolbar = new Toolbar(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar2);
//        mDrawerLayout.addView(toolbar,Toolbar.LayoutParams.MATCH_PARENT,Toolbar.LayoutParams.WRAP_CONTENT);
//        toolbar.setNavigationIcon(R.drawable.ic_drawer);
        toolbar.setLogo(R.drawable.ic_drawer);
//        setSupportActionBar(toolbar);//AppCompatActivity
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar, R.string.drawer_open,R.string.drawer_close) {
            //invalidateOptionsMenu 用于调用onPrepareOptionsMenu，再次绘制ActionBar
            @Override
            public void onDrawerOpened(View drawerView) {//监听抽屉打开
                super.onDrawerOpened(drawerView);
//                mactionBar.setTitle("请选择");
                toolbar.setTitle("请选择");
                invalidateOptionsMenu(); // Call onPrepareOptionsMenu() 重绘ActionBar
            }
            @Override
            public void onDrawerClosed(View drawerView) {//监听抽屉关闭
                Log.d("my", "[MainActivity.onPrepareOptionsMenu]");

                super.onDrawerClosed(drawerView);
//                mactionBar.setTitle(mtitle);
                toolbar.setTitle(mtitle);
                invalidateOptionsMenu();
            }
        };

        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,new Toolbar(this),R.string.drawer_open,R.string.drawer_close);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        //开启ActionBar上APP ICON的功能
//        mactionBar.setDisplayHomeAsUpEnabled(true);
//        mactionBar.setHomeButtonEnabled(true);


    }

    //重绘ActionBar上的menu
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.d("my", "[MainActivity.onPrepareOptionsMenu]");
        boolean isDrawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);//获取DrawerLayout打开状态
        menu.findItem(R.id.action_websearch).setVisible(!isDrawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    //屏幕发生旋转时调用
    @Override
    public void onConfigurationChanged(Configuration newConfig) {//对ActionBar进行重新配置
        Log.d("my", "[MainActivity.onConfigurationChanged]");
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        Log.d("my", "[MainActivity.onPostCreate]");
        super.onPostCreate(savedInstanceState);
        //需要将ActionDrawerToggle与DrawerLayout的状态同步
        //将ActionBarDrawerToggle中的drawer图标，设置为ActionBar中的Home-Button的Icon
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("my", "[MainActivity.onOptionsItemSelected]");
        //将ActionBar上的图标与Drawer结合起来
        if (mDrawerToggle.onOptionsItemSelected(item)){
            Log.d("my", "[MainActivity.onOptionsItemSelected], mDrawerToggle=true");
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_websearch:
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri uri = Uri.parse("http://www.jikexueyuan.com");
                intent.setData(uri);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("my", "[MainActivity.onItemClick], position=" + position);
        //每当点击一个item，在FrameLayout中动态插入一个Fragment
        Fragment contentFragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", menuList.get(position));
        contentFragment.setArguments(bundle);//向所点击的Fragment中传递数据

        FragmentManager fragmentManager = getFragmentManager();//android.app下面的,必须要求ContentFragment继承的是app下的Fragment
//        FragmentManager fragmentManager = getSupportFragmentManager();//必须继承FragmentActivity
        fragmentManager.beginTransaction().replace(R.id.content_frame, contentFragment).commit();
        mDrawerLayout.closeDrawer(mDrawerList);//点击完导航项，需要关闭导航
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("my", "[MainActivity.onCreateOptionsMenu]");
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
}
