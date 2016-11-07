package cn.yinxm.tevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.yinxm.lib.utils.LogUtil;

/**
 * Activity中监听滑动，监听到右滑，返回上一页，左滑不处理
 */
public class ViewPagerEventActivity extends AppCompatActivity {
    private SlideRightRelativeLayout slideRightLayout;

    private View view1, view2, view3;
    private ViewPager viewPager;
    private PagerTitleStrip pagerTitleStrip;
    private PagerTabStrip pagerTabStrip;
    private List<View> viewList;
    private List<String> titleList;
    private Button weibo_button;
    private Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_event);
        initView();
    }

    private void initView() {
        slideRightLayout = (SlideRightRelativeLayout) findViewById(R.id.slideRightLayout);
        slideRightLayout.setOnSlideRightListener(new SlideRightRelativeLayout.OnSlideRightListener() {
            @Override
            public void onSlideRight() {
                LogUtil.d("右滑关闭页面");
                finish();
            }
        });

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    slideRightLayout.setInterceptSlideRightEvent(true);//拦截右滑
                } else {
                    slideRightLayout.setInterceptSlideRightEvent(false);//不拦截右滑
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //pagerTitleStrip = (PagerTitleStrip) findViewById(R.id.pagertitle);
        pagerTabStrip=(PagerTabStrip) findViewById(R.id.pagertab);
        pagerTabStrip.setTabIndicatorColor(getResources().getColor(R.color.gold));
        pagerTabStrip.setDrawFullUnderline(false);
        pagerTabStrip.setBackgroundColor(getResources().getColor(R.color.azure));
        pagerTabStrip.setTextSpacing(50);
		/*
		weibo_button=(Button) findViewById(R.id.button1);
		weibo_button.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				intent=new Intent(ViewPagerDemo.this,WeiBoActivity.class);
				startActivity(intent);
			}
		});
		*/

        view1 = findViewById(R.layout.layout1);
        view2 = findViewById(R.layout.layout2);
        view3 = findViewById(R.layout.layout3);

        LayoutInflater lf = getLayoutInflater().from(this);
        view1 = lf.inflate(R.layout.layout1, null);
        view2 = lf.inflate(R.layout.layout2, null);
        view3 = lf.inflate(R.layout.layout3, null);

        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

        titleList = new ArrayList<String>();// 每个页面的Title数据
        titleList.add("王鹏");
        titleList.add("姜语");
        titleList.add("结婚");

        PagerAdapter pagerAdapter = new PagerAdapter() {


            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {

                return arg0 == arg1;
            }

            @Override
            public int getCount() {

                return viewList.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(viewList.get(position));

            }

            @Override
            public int getItemPosition(Object object) {

                return super.getItemPosition(object);
            }

            @Override
            public CharSequence getPageTitle(int position) {

                return titleList.get(position);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                weibo_button=(Button) findViewById(R.id.button1);
                weibo_button.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        Toast.makeText(ViewPagerEventActivity.this, "点击了微博", Toast.LENGTH_SHORT).show();
//                        intent=new Intent(ViewPagerDemo.this,WeiBoActivity.class);
//                        startActivity(intent);
                    }
                });
                return viewList.get(position);
            }

        };
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_view_pager_demo, menu);
        return true;
    }

    float x_temp1 = 0.0f;
    float y_temp1 = 0.0f;
    float x_temp2 = 0.0f;
    float y_temp2 = 0.0f;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.d("[onTouchEvent] 滑动onTouchEvent");

        //获取当前坐标
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x_temp1 = x;
                y_temp1 = y;
                break;
            case MotionEvent.ACTION_UP:
                x_temp2 = x;
                y_temp2 = y;
                LogUtil.d("[BaseActivity.onTouchEvent] 滑动参数x1=" + x_temp1 + ", x2=" + x_temp2 + ", y1=" + y_temp1 + ", y2=" + y_temp2);
                if (x_temp2 - x_temp1 > 100) {
                    LogUtil.d("向右滑动,finish页面");
                    //finish();
                    //return true;
                } else if (x_temp2 - x_temp1 < -100) {
                    LogUtil.d("向左滑动");
                }
                break;
        }

        return super.onTouchEvent(event);
    }
}
