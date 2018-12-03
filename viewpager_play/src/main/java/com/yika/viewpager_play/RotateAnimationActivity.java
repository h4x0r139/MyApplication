package com.yika.viewpager_play;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class RotateAnimationActivity extends MainActivity {
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        List<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<10; i++) {
            list.add(i);
        }
        PlayViewPagerAdapterRotate adapter = new PlayViewPagerAdapterRotate(this,  list);
        viewPager.setAdapter(adapter);
//        viewPager.setCurrentItem(2);
        viewPager.setPageTransformer(false, new ScalePageTransformer());
        //设置预加载数目
        viewPager.setOffscreenPageLimit(1);
    }
}
