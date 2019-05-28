package cn.yinxm.ui.vp;

import android.os.Bundle;
import android.support.v4.view.ViewPager;


import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends MainActivity {
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
