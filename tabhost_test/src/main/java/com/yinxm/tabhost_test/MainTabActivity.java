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

public class MainTabActivity extends TabActivity {
    private TabHost tabHost;
    private LayoutInflater layoutInflater;//布局填充器
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_tab);
        tabHost = getTabHost();//根据默认ID获取TabHost控件
        layoutInflater = LayoutInflater.from(this);
        //生成TabWidget内容
        tabHost.addTab(getMyDoubanTab());
        tabHost.addTab(getNewBookTab());
        //	tabHost.setCurrentTabByTag("newbook");
    }



    private TabSpec getMyDoubanTab(){
        TabSpec spec = tabHost.newTabSpec("mydouban");
        //指定标签显示的内容 , 激活的activity对应的intent对象
        Intent intent = new Intent(this,TestActivity.class);
        spec.setContent(intent);
        // 设置标签的文字和样式
        //系统默认样式
//        Drawable icon = getResources().getDrawable(R.drawable.ic_launcher);
//        spec.setIndicator("我的豆瓣",icon);
        spec.setIndicator(getIndicatorView("我的豆瓣", R.drawable.tab_main_nav_me));
        return spec;
    }

    private TabSpec getNewBookTab(){
        TabSpec spec = tabHost.newTabSpec("newbook");
        //指定标签显示的内容 , 激活的activity对应的intent对象
        Intent intent = new Intent(this,TestActivity.class);
        spec.setContent(intent);
        // 设置标签的文字和样式
        spec.setIndicator(getIndicatorView("豆瓣新书", R.drawable.tab_main_nav_book));
        return spec;
    }

    /**
     * 获取条目显示的view对象
     */
    private View getIndicatorView(String name, int iconid){
        View view = layoutInflater.inflate(R.layout.tab_main_nav, null);
        ImageView ivicon =	(ImageView) view.findViewById(R.id.ivIcon);
        TextView tvtitle =	(TextView) view.findViewById(R.id.tvTitle);
        ivicon.setImageResource(iconid);
        tvtitle.setText(name);
        return view;
    }

}
