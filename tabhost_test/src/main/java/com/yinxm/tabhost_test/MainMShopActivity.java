package com.yinxm.tabhost_test;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class MainMShopActivity extends TabActivity {
    private TabHost mtabHost;
    private LayoutInflater layoutInflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_mshop);
//        bindView_1();
       bindView();
    }


    private void bindView() {
//        mtabHost = (TabHost) findViewById(android.R.id.tabhost);
        mtabHost = getTabHost();
        System.out.println(mtabHost);
        layoutInflater = LayoutInflater.from(this);

        mtabHost.addTab(getHomeTab());
        mtabHost.addTab(getMyCenterTab());
    }

    //只有文字，样式默认
//    private void  bindView_1() {
////        mtabHost = getTabHost();
//        mtabHost = (TabHost) findViewById(R.id.tabhost);
//        mtabHost.setup();
//        // Tab for Tatva
//        TabHost.TabSpec t = mtabHost.newTabSpec("Tatva");
//        // setting Title and Icon for the Tab
//        t.setIndicator("Tatva");
//        Intent tatvaIntent = new Intent(this, TestActivity.class);
//        t.setContent(tatvaIntent);
//
//        // Tab for Moksh
//        TabHost.TabSpec m = mtabHost.newTabSpec("Moksh");
//        m.setIndicator("Moksh");
//        Intent mokshIntent = new Intent(this, TestActivity.class);
//        m.setContent(mokshIntent);
//
//        mtabHost.addTab(t); // Adding Tatva tab
//        mtabHost.addTab(m); // Adding Moksh tab
//    }

    public TabHost.TabSpec getHomeTab() {
        //指定标签显示的内容 , 激活的activity对应的intent对象
        TabHost.TabSpec tabSpec = mtabHost.newTabSpec("home");
        Intent intent = new Intent(this,TestActivity.class);
        tabSpec.setContent(intent);
        System.out.println("intent=" + intent);
        tabSpec.setIndicator(getIndicatorView("首页", R.drawable.u24));
        return tabSpec;
    }
    //    public TabHost.TabSpec getMsgTab() {
//
//    }
//    public TabHost.TabSpec getGoodsTab() {
//
//    }
//    public TabHost.TabSpec getPicTab() {
//
//    }
    public TabHost.TabSpec getMyCenterTab() {
        TabHost.TabSpec tabSpec = mtabHost.newTabSpec("mycenter");
        Intent intent = new Intent(this, TestActivity.class);
        tabSpec.setContent(intent);
        tabSpec.setIndicator(getIndicatorView("我的", R.drawable.u32));//指定View样式
        return tabSpec;
    }

    /**
     * 获取条目显示的view对象
     */
    private View getIndicatorView(String name, int iconId) {
        View view = layoutInflater.inflate(R.layout.tab_nav,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.tabIcon);
        TextView textView = (TextView) view.findViewById(R.id.tabText);
        imageView.setImageResource(iconId);
        textView.setText(name);
        return view;
    }

    private View getTabIndicatorView(String nameText, int iconId) {
        //设置tab页样式
        View view = layoutInflater.inflate(R.layout.tab_nav,null);
        //设置tab页样式中各icon和文字
        TextView tv = (TextView) view.findViewById(R.id.tabText);
        ImageView iv = (ImageView) view.findViewById(R.id.tabIcon);
        tv.setText(nameText);
        iv.setImageResource(iconId);
        return view;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_mshop, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
