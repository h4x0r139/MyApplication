package cn.yinxm.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import cn.yinxm.ui.adapter.SimpleFragmentPagerAdapter;
import cn.yinxm.ui.fragment.Test1Fragment;



public class TabLayoutTest1Activity extends FragmentActivity implements Test1Fragment.OnFragmentInteractionListener{
    private SimpleFragmentPagerAdapter pagerAdapter;
    private TabLayout tabLayout;
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_test1);

        tabLayout = (TabLayout) findViewById(R.id.tab);
        viewpager = (ViewPager) findViewById(R.id.viewpager);

        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewpager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewpager);
//        tab.setTabMode(TabLayout.MODE_FIXED);//default 均分
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//适用于tab页数量比较多的情况，可以出现横向滚动

//        tab.getTabAt(0).setIcon(R.drawable.collect);

        //使用自定义Tab Title Layout
        for(int i=0;i<tabLayout.getTabCount();i++){
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(pagerAdapter.getTabView(i,tab));
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d("yinxm", "onFragmentInteraction uri="+uri);
    }
}
