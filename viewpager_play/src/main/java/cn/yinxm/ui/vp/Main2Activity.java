package cn.yinxm.ui.vp;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;


import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends MainActivity {
    ViewPager viewPager;
    List<View> viewList;
    private  int position =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        List<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<10; i++) {
            list.add(i);
        }
        PlayViewPagerAdapter2 adapter2 = new PlayViewPagerAdapter2(this,  list);
        viewPager.setAdapter(adapter2);
        viewPager.setCurrentItem(1);

    }
}
