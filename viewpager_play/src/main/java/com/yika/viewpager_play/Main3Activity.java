package com.yika.viewpager_play;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        List<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<10; i++) {
            list.add(i);
        }
        PlayViewPagerAdapter3 adapter3 = new PlayViewPagerAdapter3(this,  list);
        viewPager.setAdapter(adapter3);
        viewPager.setCurrentItem(2);
    }
}
