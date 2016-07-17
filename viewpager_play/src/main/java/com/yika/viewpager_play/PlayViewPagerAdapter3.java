package com.yika.viewpager_play;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by yinxm on 2016/5/16.
 * //只用一个控件缓存所有结果The specified child already has a parent. You must call removeView
 * 必须至少要用3个,
 */
public class PlayViewPagerAdapter3 extends PagerAdapter {
    List<Integer> data;
    //    View viewCache;
    View[] viewCache = new View[3];//
    LayoutInflater inflater;

    PlayViewPagerAdapter3(Context context, List<Integer> data) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);

        for (int i = 0; i < 3; i++) {
            View tempView = inflater.inflate(R.layout.layout_music, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.img_audioIcon = (RoundImageViewByXfermode) tempView.findViewById(R.id.img_audioIcon);
            viewHolder.img_playTriangle = (ImageView) tempView.findViewById(R.id.img_playTriangle);
            viewHolder.player_seekbar = (RoundProgress) tempView.findViewById(R.id.player_seekbar);
            tempView.setTag(viewHolder);
            viewCache[i] = tempView;
        }
    }

    @Override
    public int getCount() {
        if (data == null) {
            return 0;
        } else {
            return data.size();
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {//利用缓存，不能再此移除
        Log.d("yika", "destroyItem position=" + position);
//        View convertView = (View) object;
//        container.removeView(convertView);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.d("yika", "instantiateItem position=" + position);
        position = position % 3;
        Log.d("yika", "position=" + position);

        ViewHolder viewHolder = (ViewHolder) viewCache[position].getTag();
        switch (position) {
            case 0:
                viewHolder.img_audioIcon.setImageResource(R.mipmap.number01);
                break;
            case 1:
                viewHolder.img_audioIcon.setImageResource(R.mipmap.number02);
                break;
            case 2:
                viewHolder.img_audioIcon.setImageResource(R.mipmap.number03);
                break;
            case 3:
                viewHolder.img_audioIcon.setImageResource(R.mipmap.number04);
                break;
            case 4:
                viewHolder.img_audioIcon.setImageResource(R.mipmap.number05);
                break;
            case 5:
                viewHolder.img_audioIcon.setImageResource(R.mipmap.number06);
                break;

        }
        ViewGroup parent = (ViewGroup)viewCache[position].getParent();
        if (parent != null) {
            parent.removeView(viewCache[position]);
        }
        container.addView(viewCache[position]);
        return viewCache[position];
    }

    public final class ViewHolder {
        public RoundImageViewByXfermode img_audioIcon;
        public ImageView img_playTriangle;
        public RoundProgress player_seekbar;
    }
}
