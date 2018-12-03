package com.yinxm.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by yinxm on 2016/1/21.
 */
public class ViewPagerAdapter extends PagerAdapter {
    private List<View> views;//用来承载所有的view
    private Context context;//

    public ViewPagerAdapter(List<View> views, Context context) {
        this.views = views;
        this.context = context;
    }

    @Override
    public int getCount() {//返回当前view数量
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {//当前view是不是需要的对象
       return (view == object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        System.out.println("[ViewPagerAdapter.instantiateItem]，position="+position);
//        ((ViewPager) container).addView(views.get(position));
        container.addView(views.get(position));
//        return super.instantiateItem(container, position);
        return  views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("[ViewPagerAdapter.destroyItem]，position="+position);
        //销毁不需要的对象
//        ((ViewPager) container).removeView(views.get(position));
        container.removeView(views.get(position));
 //        super.destroyItem(container, position, object);
    }

}
