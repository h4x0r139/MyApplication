package com.yinxm.viewpager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by yinxm on 2016/1/21.
 * 用于承载欢迎页
 */
public class Guide extends Activity implements ViewPager.OnPageChangeListener {
    private ViewPager vp;
    private ViewPagerAdapter vpAdapter;
    private List<View> views;
    //导航点
    private ImageView[] dots;
    private int[] ids = {R.id.iv1, R.id.iv2, R.id.iv3};

    //进入主页面
    private Button btnStart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("[Guide.onCreate]");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);
        initViews();
        initDots();
    }

    private void initViews() {
        System.out.println("[Guide.initViews]");
        LayoutInflater inflater = LayoutInflater.from(this);
        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.welcome1, null));
        views.add(inflater.inflate(R.layout.welcome2, null));
        views.add(inflater.inflate(R.layout.welcome3, null));
        vpAdapter = new ViewPagerAdapter(views, this);
        vp = (ViewPager) findViewById(R.id.viewpager);
        vp.setAdapter(vpAdapter);
        vp.addOnPageChangeListener(this);
        //进入主页面
        btnStart = (Button) views.get(2).findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Guide.this, MainActivity.class);
                startActivity(intent);
                finish();//结束当前页面
            }
        });
    }

    private void initDots() {
        dots = new ImageView[views.size()];
        for (int i=0; i<views.size(); i++) {
            dots[i] = (ImageView) findViewById(ids[i]);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {//当页面被滑动时调用
        System.out.println("[Guide.onPageScrolled]");
    }

    @Override
    public void onPageSelected(int position) {//新的页面被选中时调用
        System.out.println("[Guide.onPageSelected]，position="+position);
        for (int i=0; i<ids.length; i++) {
            if (position == i) {//是被选中的图片
                dots[i].setImageResource(R.drawable.login_point_selected_meitu_1);
            } else {
                dots[i].setImageResource(R.drawable.login_point);
            }
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {//当滑动状态改变时调用
        System.out.println("[Guide.onPageScrollStateChanged]");
    }
}
