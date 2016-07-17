package com.yinxm.tabhost_test;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

;

public class MainActivity extends TabActivity {
    private TabHost tabHost;
    private LayoutInflater layoutInflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_tab);
//        bindView_0();
//        bindView_1();
//        bindView_2();
        bindView_Custom();
    }

    //自定义样式
    private void bindView_Custom() {
        tabHost = getTabHost();
        layoutInflater = LayoutInflater.from(this);
        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("home");
        tabSpec1.setContent(new Intent(this,TestActivity.class));
        tabSpec1.setIndicator(getTabIndicatorView("主页",R.drawable.u20));

        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("my");
        tabSpec2.setContent(new Intent(this,TestActivity.class));
        tabSpec2.setIndicator(getTabIndicatorView("我的",R.drawable.u22));

        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("home");
        tabSpec3.setContent(new Intent(this,TestActivity.class));
        tabSpec3.setIndicator(getTabIndicatorView("主页",R.drawable.u24));

        //添加tab页
        tabHost.addTab(tabSpec1);
        tabHost.addTab(tabSpec2);
        tabHost.addTab(tabSpec3);

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

    //增加icon，默认样式
    private void bindView_2() {
        tabHost = (TabHost)findViewById(android.R.id.tabhost);
//        tabHost = getTabHost();
        TabHost.TabSpec spec1 = tabHost.newTabSpec("my");
        spec1.setIndicator("我的",getResources().getDrawable(R.drawable.ic_launcher));
        spec1.setContent(new Intent(this, TestActivity.class));

        TabHost.TabSpec spec2 = tabHost.newTabSpec("he");
        spec2.setIndicator("他的",getResources().getDrawable(R.drawable.ic_launcher));
        spec2.setContent(new Intent(this, TestActivity.class));
        tabHost.addTab(spec1);
        tabHost.addTab(spec2);

    }

    //只有文字，样式默认
    private void  bindView_1() {
        tabHost = getTabHost();
        // Tab for Tatva
        TabSpec t = tabHost.newTabSpec("Tatva");
        // setting Title and Icon for the Tab
        t.setIndicator("Tatva");
        Intent tatvaIntent = new Intent(this, TestActivity.class);
        t.setContent(tatvaIntent);

        // Tab for Moksh
        TabSpec m = tabHost.newTabSpec("Moksh");
        m.setIndicator("Moksh");
        Intent mokshIntent = new Intent(this, TestActivity.class);
        m.setContent(mokshIntent);

        tabHost.addTab(t); // Adding Tatva tab
        tabHost.addTab(m); // Adding Moksh tab
    }

    private void  bindView_0() {
        tabHost = getTabHost();
        //设置使用TabHost布局
        LayoutInflater.from(this).inflate(R.layout.tab_nav, tabHost.getTabContentView(), true);

        //添加第一个标签页
        tabHost.addTab(tabHost.newTabSpec("tab01").setIndicator("已接电话").setContent(R.id.tabText));

        //添加第二个标签页,并在其标签上添加一个图片
        tabHost.addTab(tabHost.newTabSpec("tab02").setIndicator("未接电话",getResources().getDrawable(R.drawable.u24)).setContent(R.id.tabText));
    }


}
