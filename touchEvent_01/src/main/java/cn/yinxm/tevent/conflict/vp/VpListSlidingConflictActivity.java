package cn.yinxm.tevent.conflict.vp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cn.yinxm.tevent.R;

/**
 * ViewPager+Fragment+list列表滑动冲突
 */
public class VpListSlidingConflictActivity extends AppCompatActivity implements VpFragment.OnFragmentInteractionListener {

    private ViewPager vp;
    private MyPagerAdapter pagerAdapter;
    private List<Fragment> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp_list_sliding_conflict);

        vp = (ViewPager) findViewById(R.id.vp);

        for (int i = 0; i < 5; i++) {
            VpFragment fragment = VpFragment.newInstance(""+(i+1), null);
            list.add(fragment);
        }

        FragmentManager fm = getSupportFragmentManager();

        pagerAdapter = new MyPagerAdapter(fm, list);
        vp.setAdapter(pagerAdapter);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private static class MyPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> list;

        public MyPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list == null ? 0: list.size();
        }
    }
}
